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
        name = "Satellite";
        speed = 0;
        defaultSpeed = speed;
        maxLife = 300;
        life = maxLife;
        type = enemyType;
        standing = true;
        hpBarOn = true;
        direction = "down";
        subType = 0;
        // hpBarCounter = 0;

        solidArea.x = 50;
        solidArea.y = 20;
        solidArea.width = 74;
        solidArea.height = 244;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage() {
        down1 = setup("/satellite/satellite", gp.tileSize * 4, gp.tileSize * 6);
        down2 = setup("/satellite/satellite", gp.tileSize * 4, gp.tileSize * 6);
        down3 = setup("/satellite/satellite", gp.tileSize * 4, gp.tileSize * 6);
        
        downCracked1 = setup("/satellite/deathsatellite", gp.tileSize * 4, gp.tileSize * 6);
        downCracked2 = setup("/satellite/deathsatellite", gp.tileSize * 4, gp.tileSize * 6);
        downCracked3 = setup("/satellite/deathsatellite", gp.tileSize * 4, gp.tileSize * 6);
    }

    public void update() {

        super.update();
    }

    public void damageReaction() {
        inFight = true;
    }

}
