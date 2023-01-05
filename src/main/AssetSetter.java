package main;

import java.awt.Rectangle;
import java.util.Random;
import enemy.ENEMY_Satellite;
import enemy.ENEMY_Wolf;
import npc.Npc_Abulbul;
import npc.Npc_Blacksmith;
import npc.Npc_Merchant;
import object.*;

/**
 * <p>
 * This Class set the objects
 * </p>
 */
public class AssetSetter {

    GamePanel gp;
    Random rand = new Random();

    /**
     * <p>
     * Counts array index when setting objects
     * </p>
     */
    public int index = 0;

    /**
     * <p>
     * Acts as counter for setting wolves
     * </p>
     */
    public int wolfCreateCounter = 300;

    /**
     * <p>
     * Keeps the number of surviving wolves
     * </p>
     */
    public int aliveWolfNum = 0;

    /**
     * <p>
     * Keeps the collision status.If there is a collision, it takes the true value
     * otherwise, it takes the false value
     * </p>
     */
    public boolean collisionOn;

    /**
     * <p>
     * It holds satellite status is created takes the true value otherwise, it takes
     * the false value
     * </p>
     */
    public boolean createdSatallite = true;

    /**
     * <p>
     * A rectangle for the field boundaries of wolves
     * </p>
     */
    public Rectangle wolfBoundary;

    /**
     * <p>
     * It holds the coordinates of the player and the items to be loaded
     * </p>
     */
    int playerWorldX, playerWorldY, playerWorldWidth, playerWorldHeight, spawnWorldX, spawnWorldY;

    /**
     * <p>
     * this is constructor
     * </p>
     * 
     * @param gp is the game panel
     * @since 1.0
     */
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
        wolfBoundary = new Rectangle(20 * gp.tileSize, 20 * gp.tileSize, 30 * gp.tileSize, 30 * gp.tileSize);
    }

    /**
     * <p>
     * create coin object
     * </p>
     * 
     * @param worldX refers the coin object x coordinates
     * @param worldY refers the object y coordinates
     * @since 1.0
     */
    public void createCoin(int worldX, int worldY) {
        gp.obj[index] = new OBJ_Coin(gp, rand.nextInt(100));
        gp.obj[index].worldX = worldX;
        gp.obj[index].worldY = worldY;
        gp.obj[index].objIndex = index;
        index++;
    }
    
    /**
     * <p>
     * create dolunay sword object
     * </p>
     * 
     * @param worldX refers the coin object x coordinates 
     * @param worldY refers the object y coordinates
     * @since 1.0
     */
    public void createDolunay(int worldX, int worldY) {
        gp.obj[index] = new OBJ_Dolunay(gp);
        gp.obj[index].worldX = worldX;
        gp.obj[index].worldY = worldY;
        gp.obj[index].objIndex = index;
        index++;
    }


    /**
     * <p>
     * create dead wolf sword object
     * </p>
     * 
     * @param worldX refers the coin object x coordinates 
     * @param worldY refers the object y coordinates
     * @since 1.0
     */
    public void createDeadWolf(int worldX, int worldY) {
        gp.obj[index] = new OBJ_DeadWolf(gp);
        gp.obj[index].worldX = worldX;
        gp.obj[index].worldY = worldY;
        gp.obj[index].objIndex = index;
        index++;
    }

    /**
     * <p>
     * This method set the enemies.Crate new enemy different cordinates.
     * </p>
     * 
     * @since 1.0
     */
    public void setEnemy() {
        wolfCreateCounter++;

        if (wolfCreateCounter >= 180 && aliveWolfNum < 5) { // if 5 seconds past and there are wolf less than 5

            playerWorldX = wolfBoundary.x;
            playerWorldWidth = wolfBoundary.x + wolfBoundary.width;

            playerWorldY = wolfBoundary.y;
            playerWorldHeight = wolfBoundary.y + wolfBoundary.height;

            do {
                spawnWorldX = (int) (rand.nextInt(playerWorldX, playerWorldWidth) / gp.tileSize) * gp.tileSize; // new
                                                                                                                // wolf
                                                                                                                // worldX
                spawnWorldY = (int) (rand.nextInt(playerWorldY, playerWorldHeight) / gp.tileSize * gp.tileSize); // new
                                                                                                                 // wolf
                                                                                                                 // worldY
            } while (spawnWorldX <= 0 || spawnWorldX + 3 * gp.tileSize >= gp.maxWorldCol * gp.tileSize
                    || spawnWorldY <= 0 || spawnWorldY + 3 * gp.tileSize >= gp.maxWorldRow * gp.tileSize);

            int newEnemyNum = rand.nextInt(3) + 1; // 1 2 3

            for (int i = 0; i < newEnemyNum; i++) {
                int xPosition = rand.nextInt(3);
                int yPosition = rand.nextInt(3);

                collisionOn = false;
                gp.collisionChecker.checkTileForNewEntity(spawnWorldX + xPosition * gp.tileSize,
                        spawnWorldY + yPosition * gp.tileSize);
                gp.collisionChecker.checkEntityForNewEntity(spawnWorldX + xPosition * gp.tileSize,
                        spawnWorldY + yPosition * gp.tileSize, gp.enemy);
                gp.collisionChecker.checkEntityForNewEntity(spawnWorldX + xPosition * gp.tileSize,
                        spawnWorldY + yPosition * gp.tileSize, gp.npc);
                gp.collisionChecker.checkPlayerForNewEntity(spawnWorldX + xPosition * gp.tileSize,
                        spawnWorldY + yPosition * gp.tileSize);
                if (!collisionOn) {
                    gp.enemy[index] = new ENEMY_Wolf(gp, index);
                    gp.enemy[index].worldX = spawnWorldX + xPosition * gp.tileSize;
                    gp.enemy[index].worldY = spawnWorldY + yPosition * gp.tileSize;
                    gp.enemy[index].newBorn = true;
                    index++;
                    aliveWolfNum++;

                }

            }
            wolfCreateCounter = 0;
        }
    }
    
    /**
     * <p>
     * This method set the satellite.if createdSatallite is true create satellite
     * </p>
     * 
     * @since 1.0
     */
    public void setSatellite() {
        if (createdSatallite) {
            gp.enemy[0] = new ENEMY_Satellite(gp, 0);
            gp.enemy[0].worldX = gp.tileSize * 35;
            gp.enemy[0].worldY = gp.tileSize * 37;
            index++;
            createdSatallite = false;
        }

    }

    /**
     * <p>
     * This method set the Npc.All npc are created here
     * </p>
     * 
     * @since 1.0
     */
    public void setNpc() {

        gp.npc[0] = new Npc_Abulbul(gp);
        gp.npc[0].worldX = gp.tileSize * 14;
        gp.npc[0].worldY = gp.tileSize * 18;

        gp.npc[1] = new Npc_Merchant(gp);
        gp.npc[1].worldX = gp.tileSize * 24;
        gp.npc[1].worldY = gp.tileSize * 15;

        gp.npc[2] = new Npc_Blacksmith(gp);
        gp.npc[2].worldX = gp.tileSize * 34;
        gp.npc[2].worldY = gp.tileSize * 18;
    }

}
