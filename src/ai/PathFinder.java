package ai;

import java.util.ArrayList;

import entity.Entity;
import main.GamePanel;

public class PathFinder {

    GamePanel gp;
    Node[][] node;
    ArrayList <Node> openList = new ArrayList<>();
    public ArrayList<Node> pathList = new ArrayList<>();
    Node startNode, goalNode, currentNode;
    boolean goalReached = false;
    int step = 0;
    
    public PathFinder(GamePanel gp) {
        this.gp = gp;
        instantiateNodes();
    }
    
    public void instantiateNodes() {
        node = new Node[gp.maxWorldCol][gp.maxWorldRow];
        
        for(int row = 0; row < gp.maxWorldRow; row++) {       
            for(int col = 0; col < gp.maxWorldCol; col++) {
                node[col][row] = new Node(col, row);
            }
        }
    }
    
    public void resetNodes() {
        
        for(int row = 0; row < gp.maxWorldRow; row++) {         
            for(int col = 0; col < gp.maxWorldCol; col++) {     
                node[col][row].open = false;
                node[col][row].checked = false;
                node[col][row].solid = false;
            }
        }
        
        // Reset other settings
        openList.clear();
        pathList.clear();
        goalReached = false;
        step = 0;
    }
    
    public void setNodes(int startCol, int startRow, int goalCol, int goalRow, Entity entity) {
        
        resetNodes();
        
        // Set Start and Goal Node
        startNode = node[startCol][startRow];
        currentNode = startNode;
        goalNode = node[goalCol][goalRow];
        openList.add(currentNode);
        
        
        // CHECK NPC ON THE WAY
        for(int i=0; i < gp.npc.length; i++) {
            if(gp.npc[i] != null) { //  && gp.npc[i] != entity
                int npcCol = gp.npc[i].worldX / gp.tileSize;
                int npcRow = gp.npc[i].worldY / gp.tileSize;
                node[npcCol][npcRow].solid = true;
            }
        }
        
        // CHECK ENEMIES ON THE WAY
        for(int i=0; i < gp.enemy.length; i++) {
            if(gp.enemy[i] != null) { // && gp.enemy[i] != entity
                int enemyCol = gp.enemy[i].worldX / gp.tileSize;
                int enemyRow = gp.enemy[i].worldY / gp.tileSize;
                node[enemyCol][enemyRow].solid = true;
            }
        }
        
        for(int row = 0; row < gp.maxWorldRow; row++) {           
            for(int col = 0; col < gp.maxWorldCol; col++) {     
                // Set Solid Node
                // Check Tiles
                int tileNum = gp.tileM.mapTileNum[col][row]; // Change here After change MapTileNum
                if(gp.tileM.tile[tileNum-1].collision == true) {
                    node[col][row].solid = true;
                }
                
                // Check interactive tiles
                /*
                for(int i=0; i < gp.iTile[1].length; i++) {
                    if(gp.iTile[gp.currentMap][i] != null && gp.iTile[gp.currentMap][i].destructible == true) {
                        int itCol = gp.iTile[gp.currentMap][i].worldX / gp.tileSize;
                        int itRow = gp.iTile[gp.currentMap][i].worldY / gp.tileSize;
                        node[itCol][itRow].solid = true;
                    }
                }
                */
                
                // Set Cost
                getCost(node[col][row]);
                
            }
        }
    }
    
    public void getCost(Node node) {
        
        // G Cost
        int xDistance = Math.abs(node.col - startNode.col);
        int yDistance = Math.abs(node.row - startNode.row);
        node.gCost = xDistance + yDistance;
        
        // H Cost
        xDistance = Math.abs(node.col - goalNode.col);
        yDistance = Math.abs(node.row - goalNode.row);
        node.hCost = xDistance + yDistance;
        
        // F Cost
        node.fCost = node.gCost + node.hCost;
    }
    
    public boolean search(int goalCol, int goalRow, Entity entity) {
        while(!goalReached && step < 500 && !entity.reachedGoal) {
            
            int col = currentNode.col;
            int row = currentNode.row;
            
            //System.out.println(goalCol+" "+goalRow+" : "+col+" "+row);
            if(goalCol == col && goalRow == row && entity.type == entity.playerType) {
                entity.reachedGoal = true;
                return true;
            }
            // Check the Current Node
            currentNode.checked = true;
            openList.remove(currentNode);
                        
            // Open the up node
            if(row - 1 >= 0) {
                openNode(node[col][row - 1]);
            }
            // Open the left node
            if(col - 1 >= 0) {
                openNode(node[col - 1][row]);
            }
            // Open the down node
            if(row + 1 < gp.maxWorldRow) {
                openNode(node[col][row + 1]);
            }
            // Open the right node
            if(col + 1 < gp.maxWorldCol) {
                openNode(node[col + 1][row]);
            }
            
            // Find the best node
            int bestNodeIndex = 0;
            int bestNodeFCost = 999;
            
            for(int i=0; i < openList.size(); i++) {
                
                // Check if this node's F Cost is better
                if(openList.get(i).fCost < bestNodeFCost) {
                    bestNodeIndex = i;
                    bestNodeFCost = openList.get(i).fCost;
                }
                
                // If F cost is equal, check the G Cost
                else if(openList.get(i).fCost == bestNodeFCost) {
                    if(openList.get(i).gCost < openList.get(bestNodeIndex).gCost) {
                        bestNodeIndex = i;
                    }
                }                
            }
            
            // If there is no node in the openList, end the loop
            if(openList.size() == 0) {
                break;
            }
            
            // After the loop, openList[bestNodeIndex] is the next step (= currentNode)
            currentNode = openList.get(bestNodeIndex);
            
            if(currentNode == goalNode) {
                goalReached = true;
                trackPath();
            }
            
            step++;
        }
        return goalReached;
    }
    
    public void openNode(Node node) {
        if(!node.open && !node.checked && !node.solid) {
            
            node.open = true;
            node.parent = currentNode;
            openList.add(node);
        }
    }
    
    public void trackPath() {
        
        Node current = goalNode;
        
        while(current != startNode) {
            
            pathList.add(0,current);
            current = current.parent;
        }
    }
    
    
}
