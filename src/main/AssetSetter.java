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
    
    public boolean collisionOn;
    
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
            
            
            do {
                spawnWorldX = (int)(rand.nextInt(playerWorldX,playerWorldWidth) / gp.tileSize) * gp.tileSize;   // new wolf worldX
                spawnWorldY = (int)(rand.nextInt(playerWorldY,playerWorldHeight) / gp.tileSize * gp.tileSize);  // new wolf worldY
            }while(spawnWorldX <= 0 || spawnWorldX >= gp.maxWorldCol * gp.tileSize || spawnWorldY <= 0 || spawnWorldY >= gp.maxWorldRow * gp.tileSize);
            
            int newEnemyNum = rand.nextInt(3) + 1; // 1 2 3
            
            for(int i=0; i < newEnemyNum; i++) {
                
                switch(i) {
                    case 0:
                        collisionOn = false;
                        gp.collisionChecker.checkTileForNewEntity(spawnWorldX, spawnWorldY);
                        gp.collisionChecker.checkEntityForNewEntity(spawnWorldX, spawnWorldY, gp.enemy);
                        gp.collisionChecker.checkPlayerForNewEntity(spawnWorldX, spawnWorldY);                        
                        if(!collisionOn) {
                            gp.enemy[index] = new ENEMY_Wolf(gp);
                            gp.enemy[index].worldX = spawnWorldX;
                            gp.enemy[index].worldY = spawnWorldY;
                            index++;
                            aliveWolfNum++;
                        }
                        break;
                    case 1:
                        collisionOn = false;
                        gp.collisionChecker.checkTileForNewEntity(spawnWorldX + 2 * gp.tileSize, spawnWorldY);
                        gp.collisionChecker.checkEntityForNewEntity(spawnWorldX + 2 * gp.tileSize, spawnWorldY, gp.enemy);
                        gp.collisionChecker.checkPlayerForNewEntity(spawnWorldX + 2 * gp.tileSize, spawnWorldY); 
                        if(!collisionOn) {
                            gp.enemy[index] = new ENEMY_Wolf(gp);
                            gp.enemy[index].worldX = spawnWorldX + 2 * gp.tileSize;
                            gp.enemy[index].worldY = spawnWorldY;
                            index++;
                            aliveWolfNum++;
                        }
                        break;
                    case 2:
                        collisionOn = false;
                        gp.collisionChecker.checkTileForNewEntity(spawnWorldX + gp.tileSize, spawnWorldY + 2 * gp.tileSize);
                        gp.collisionChecker.checkEntityForNewEntity(spawnWorldX + gp.tileSize, spawnWorldY + 2 * gp.tileSize, gp.enemy);
                        gp.collisionChecker.checkPlayerForNewEntity(spawnWorldX + gp.tileSize, spawnWorldY + 2 * gp.tileSize); 
                        if(!collisionOn) {
                            gp.enemy[index] = new ENEMY_Wolf(gp);
                            gp.enemy[index].worldX = spawnWorldX + gp.tileSize;
                            gp.enemy[index].worldY = spawnWorldY + 2 * gp.tileSize ;
                            index++;
                            aliveWolfNum++;
                        }
                        break;
                }
               
            }
            System.out.println("");
            wolfCreateCounter = 0;
        }
    }
}
