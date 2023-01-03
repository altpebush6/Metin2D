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


        solidArea.x = 10;
        solidArea.y = 0;
        solidArea.width = 25;
        solidArea.height = 48;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage() {
        down1 = setup("/wolf/down1", gp.tileSize, gp.tileSize);
    }

    public void update() {

        super.update();
    }

   
}
