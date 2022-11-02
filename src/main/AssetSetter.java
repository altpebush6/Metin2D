package main;

import java.util.Random;

import enemy.ENEMY_Wolf;
import object.*;

public class AssetSetter {

    GamePanel gp;
    Random rand = new Random();
    public int index = 0;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObjectManually() {

        gp.obj[0] = new OBJ_Coin(gp, rand.nextInt(100));
        gp.obj[0].worldX = 32 * gp.tileSize;
        gp.obj[0].worldY = 22 * gp.tileSize;

        gp.obj[1] = new OBJ_Coin(gp, rand.nextInt(100));
        gp.obj[1].worldX = 28 * gp.tileSize;
        gp.obj[1].worldY = 18 * gp.tileSize;

        index += 5;
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

        gp.enemy[0] = new ENEMY_Wolf(gp);
        gp.enemy[0].worldX = gp.tileSize * 30;
        gp.enemy[0].worldY = gp.tileSize * 24;
        
        gp.enemy[1] = new ENEMY_Wolf(gp);
        gp.enemy[1].worldX = gp.tileSize * 25;
        gp.enemy[1].worldY = gp.tileSize * 24;
        
        gp.enemy[2] = new ENEMY_Wolf(gp);
        gp.enemy[2].worldX = gp.tileSize * 26;
        gp.enemy[2].worldY = gp.tileSize * 24;
        
        gp.enemy[3] = new ENEMY_Wolf(gp);
        gp.enemy[3].worldX = gp.tileSize * 27;
        gp.enemy[3].worldY = gp.tileSize * 24;
        
        gp.enemy[4] = new ENEMY_Wolf(gp);
        gp.enemy[4].worldX = gp.tileSize * 22;
        gp.enemy[4].worldY = gp.tileSize * 24;
        
        gp.enemy[5] = new ENEMY_Wolf(gp);
        gp.enemy[5].worldX = gp.tileSize * 28;
        gp.enemy[5].worldY = gp.tileSize * 24;

    }
}
