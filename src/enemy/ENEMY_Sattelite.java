package enemy;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import ai.Node;
import ai.PathFinder;

public class ENEMY_Sattelite extends Entity {

    GamePanel gp;

    public ENEMY_Sattelite(GamePanel gp, int id) {

        super(gp);

        this.gp = gp;
        name = "Sattelite";
        speed = 1;
        defaultSpeed = speed;
        maxLife = 100;
        life = maxLife;
        type = enemyType;
        standing = true;


        solidArea.x = gp.tileSize*30;
        solidArea.y = gp.tileSize*30;
        solidArea.width = gp.tileSize*2;
        solidArea.height = gp.tileSize*3;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage() {
        down1 = setup("/satellite/satellite", gp.tileSize*2, gp.tileSize*3);
    }

    public void update() {

        super.update();
    }

   
}
