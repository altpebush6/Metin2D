package main;

import java.awt.Rectangle;
import java.util.Random;

import enemy.ENEMY_Wolf;
import object.*;

public class AssetSetter {

    GamePanel gp;
    Random rand = new Random();
    public int index = 0;
    public int wolfCreateCounter = 300;
    public int aliveWolfNum = 0;
    
    int playerWorldX, playerWorldY, playerWorldWidth, playerWorldHeight, spawnWorldX ,spawnWorldY;
    

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObjectManually() {
        
    }

    public void createCoin(int worldX, int worldY) {
        gp.obj[index] = new OBJ_Coin(gp, rand.nextInt(100));
        gp.obj[index].worldX = worldX;
        gp.obj[index].worldY = worldY;
        index++;
    }

    public void createDeadWolf(int worldX, int worldY) {
        gp.obj[index] = new OBJ_DeadWolf(gp);
        gp.obj[index].worldX = worldX;
        gp.obj[index].worldY = worldY;
        gp.obj[index].deadIndex = index;
        index++;
    }

    public void setEnemy() {
        wolfCreateCounter++;
        if(wolfCreateCounter >= 300 && aliveWolfNum < 5) { // if 5 seconds past and there are wolf less than 5
             
            playerWorldX = gp.player.worldX - gp.tileSize * 5;
            playerWorldWidth = gp.player.worldX + gp.tileSize * 5;
            
            playerWorldY = gp.player.worldY - gp.tileSize * 5;
            playerWorldHeight = gp.player.worldY + gp.tileSize * 5;
            
            spawnWorldX = rand.nextInt(playerWorldX,playerWorldWidth); // new wolf worldX
            spawnWorldY = rand.nextInt(playerWorldY,playerWorldHeight);// new wolf worldY
            
            boolean collisionChecker = false;
            
            for(int i=0; i < gp.enemy.length; i++) {
                if(gp.enemy[i] != null) {
                    Rectangle enemySolidArea = new Rectangle(0, 0, 48, 48);
                    enemySolidArea.x = gp.enemy[i].worldX + gp.enemy[i].solidArea.x;
                    enemySolidArea.y = gp.enemy[i].worldY + gp.enemy[i].solidArea.y;
                    
                    Rectangle newEnemySolidArea = new Rectangle(0, 0, 48, 48);
                    newEnemySolidArea.x = spawnWorldX + gp.enemy[i].solidArea.x;
                    newEnemySolidArea.y = spawnWorldY + gp.enemy[i].solidArea.y;
                    
                    if(enemySolidArea.intersects(newEnemySolidArea)) {  // if wolf and newWolf intersects make collisionChecker true
                        collisionChecker = true;
                    }
                }
            }
            
            // If new enemy intersects with player
            if(gp.enemy[0] != null) {
                Rectangle newEnemySolidArea = new Rectangle(0, 0, 48, 48);
                newEnemySolidArea.x = spawnWorldX + gp.enemy[0].solidArea.x;
                newEnemySolidArea.y = spawnWorldY + gp.enemy[0].solidArea.y;
                
                Rectangle playerSolidArea = new Rectangle(0, 0, 48, 48);
                playerSolidArea.x = gp.player.worldX + gp.player.solidArea.x;
                playerSolidArea.y = gp.player.worldY + gp.player.solidArea.y;   
                
                if(playerSolidArea.intersects(newEnemySolidArea)) {  // if player and new enemy intersects make collisionChecker true
                    collisionChecker = true;
                }
            }
            
            if(!collisionChecker) { // if wolf and newWolf doesn't intersect
                
                int newEnemyNum = rand.nextInt(3) + 1;
                aliveWolfNum += newEnemyNum;
                
                for(int i=0; i < newEnemyNum; i++) {
                    
                    switch(i) {
                        case 0:
                            gp.enemy[index] = new ENEMY_Wolf(gp);
                            gp.enemy[index].worldX = spawnWorldX + i * gp.tileSize;
                            gp.enemy[index].worldY = spawnWorldY + i * gp.tileSize ;
                            index++;
                            break;
                        case 1:
                            gp.enemy[index] = new ENEMY_Wolf(gp);
                            gp.enemy[index].worldX = spawnWorldX + (i+1) * gp.tileSize;
                            gp.enemy[index].worldY = spawnWorldY ;
                            index++;
                        case 3:
                            gp.enemy[index] = new ENEMY_Wolf(gp);
                            gp.enemy[index].worldX = spawnWorldX + i * gp.tileSize;
                            gp.enemy[index].worldY = spawnWorldY + i * gp.tileSize ;
                            index++;
                    }
                }
            }
            wolfCreateCounter = 0;
        }
    }
}
