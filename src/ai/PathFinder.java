package ai;

import java.util.ArrayList;

import entity.Entity;
import main.GamePanel;


/**
 * <p>
 * This class using path Finder
 * </p>
 */
public class PathFinder {

    /**
     * <p>
     * for usage of GamePanel 
     * </p>
     */
    GamePanel gp;
    /**
     * <p>
     * Node array to store all nodes
     * </p>
     */
    Node[][] node;
    /**
     * <p>
     *  This array list stores the open node list
     * </p>
     */
    public ArrayList <Node> openList = new ArrayList<>();
    /**
     * <p>
     *  This array list stores the path list
     * </p>
     */
    public ArrayList<Node> pathList = new ArrayList<>();
    /**
     * <p>
     *  This is the node where given starting point is in.
     * </p>
     */
    public Node startNode;
    /**
     * <p>
     *  This is the node of given goal point 
     * </p>
     */
    public Node goalNode;
    /**
     * <p>
     *  This node keeps the current nodes.
     * </p>
     */
    public Node currentNode;
    /**
     * <p>
     *  If entity reaches its goal this indicates the situation
     * </p>
     */
    public boolean goalReached = false;
    /**
     * <p>
     *  This is a counter for each step
     * </p>
     */
    public int step = 0;
    
    /**
     * <p>
     * This is constructor
     * </p>
     * 
     * @param gp means GamePanel
     * @since 1.0
     */
    public PathFinder(GamePanel gp) {
        this.gp = gp;
        instantiateNodes();
    }
    
    /**
     * <p>
     * This method instantiate the nodes with assigning new Node to each node
     * </p>
     * 
     * @since 1.0
     */
    public void instantiateNodes() {
        node = new Node[gp.maxWorldCol][gp.maxWorldRow];
        
        for(int row = 0; row < gp.maxWorldRow; row++) {       
            for(int col = 0; col < gp.maxWorldCol; col++) {
                node[col][row] = new Node(col, row);
            }
        }
    }
    
    /**
     * <p>
     * Resets the nodes as open, checked and solid properties are false
     * </p>
     * 
     * @since 1.0
     */
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
    
    /**
     * <p>
     * This method sets the nodes as if there is a obstacle on a node, makes node solid
     * </p>
     * @param startCol indicates starting column
     * @param startRow indicates starting row
     * @param goalCol indicates starting goal column
     * @param goalRow indicates starting goal row
     * @param entity gets the entity
     * @since 1.0
     */
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
                int tileNum = gp.tileM.mapTileNum[col][row];
                if(gp.tileM.tile[tileNum-1].collision == true) {
                    node[col][row].solid = true;
                }
                
                // Set Cost
                getCost(node[col][row]);
                
            }
        }
    }
    
    /**
     * <p>
     * This method calculates the G, H and F costs for each node
     * </p>
     * 
     * @param node gets node
     * @since 1.0
     */
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
    
    
    /**
     * <p>
     * This method searches the best node according to the costs. Returns goalReached if entity reaches to the given goal
     * </p>
     * 
     * @param goalCol indicates goal column
     * @param goalRow indicates goal row
     * @param entity gets entity object
     * @since 1.0
     * @return if return true goes the area
     */
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
    
    /**
     * <p>
     * Opens given node
     * </p>
     * 
     * @param node gets  object
     * @since 1.0
     */
    public void openNode(Node node) {
        if(!node.open && !node.checked && !node.solid) {
            
            node.open = true;
            node.parent = currentNode;
            openList.add(node);
        }
    }
    
    /**
     * <p>
     *  Finally this method provides to track the path
     * </p>
     * 
     * @since 1.0
     */
    public void trackPath() {
        
        Node current = goalNode;
        
        while(current != startNode) {
            
            pathList.add(0,current);
            current = current.parent;
        }
    }
    
    
}
