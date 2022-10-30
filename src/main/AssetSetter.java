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

        gp.obj[2] = new OBJ_Dolunay(gp);
        gp.obj[2].worldX = 27 * gp.tileSize;
        gp.obj[2].worldY = 27 * gp.tileSize;

        index += 3;
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

    }
}
