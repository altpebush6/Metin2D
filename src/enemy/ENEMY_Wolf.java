package enemy;

import java.util.Random;

import entity.Entity;
import main.GamePanel;

/**
 * <p>
 * This Class contains wolf specifications as an enemy
 * </p>
 */
public class ENEMY_Wolf extends Entity {

    /**
     * <p>
     * Provides to use random numbers
     * </p>
     */
    public Random rand = new Random();
    /**
     * <p>
     * For usage of GamePanel 
     * </p>
     */
    public GamePanel gp;

    /**
     * <p>
     * This is constructor instantiates the wolf specifications
     * </p>
     * 
     * @param gp is the game panel
     * @param id indicates the enemy id
     * @since 1.0
     */
    public ENEMY_Wolf(GamePanel gp, int id) {

        super(gp);

        this.gp = gp;
        name = "Wolf";
        wolfID = id;
        speed = 1;
        defaultSpeed = speed;
        maxLife = 100;
        life = maxLife;
        type = enemyType;
        standing = true;
        subType =1;

        int bornDirection = rand.nextInt(4) + 1;
        switch (bornDirection) {
            case 1:
                direction = "up";
                break;
            case 2:
                direction = "down";
                break;
            case 3:
                direction = "left";
                break;
            case 4:
                direction = "right";
                break;
        }

        if (direction == "up" || direction == "down") {
            solidArea.x = 10;
            solidArea.y = 0;
            solidArea.width = 25;
            solidArea.height = 48;
        } else {
            solidArea.x = 0;
            solidArea.y = 8;
            solidArea.width = 48;
            solidArea.height = 32;
        }

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    /**
     * <p>
     * Gets the wolf images from 4 different direction
     * </p>
     * 
     * @since 1.0
     */
    public void getImage() {
        up1 = setup("/wolf/up1", gp.tileSize, gp.tileSize);
        up2 = setup("/wolf/up2", gp.tileSize, gp.tileSize);
        up3 = setup("/wolf/up3", gp.tileSize, gp.tileSize);
        
        down1 = setup("/wolf/down1", gp.tileSize, gp.tileSize);
        down2 = setup("/wolf/down2", gp.tileSize, gp.tileSize);
        down3 = setup("/wolf/down3", gp.tileSize, gp.tileSize);
        
        left1 = setup("/wolf/left1", gp.tileSize, gp.tileSize);
        left2 = setup("/wolf/left2", gp.tileSize, gp.tileSize);
        left3 = setup("/wolf/left3", gp.tileSize, gp.tileSize);
        
        right1 = setup("/wolf/right1", gp.tileSize, gp.tileSize);
        right2 = setup("/wolf/right2", gp.tileSize, gp.tileSize);
        right3 = setup("/wolf/right3", gp.tileSize, gp.tileSize);
    }

    /**
     * <p>
     * This method sets wolf fight acts according to the distance to player
     * </p>
     * 
     * @since 1.0
     */
    public void update() {

        super.update();

        int xDistance = Math.abs(worldX - gp.player.worldX);
        int yDistance = Math.abs(worldY - gp.player.worldY);
        int tileDistance = (xDistance + yDistance) / gp.tileSize;

        if (onPath && tileDistance > 10) {
            onPath = false;
            inFight = false;
        }
    }

    /**
     * <p>
     * This method sets the action of wolf as wander or follow the player
     * </p>
     * 
     * @since 1.0
     */
    public void setAction() {

        if (onPath) {

            solidArea.x = 0;
            solidArea.y = 0;
            solidArea.width = 30;
            solidArea.height = 30;

            speed = 2;

            int goalCol = (gp.player.worldX + gp.player.solidArea.x) / gp.tileSize; // gp.player.worldX + gp.player.solidArea.x
            int goalRow = (gp.player.worldY + gp.player.solidArea.y) / gp.tileSize; // gp.player.worldY + gp.player.solidArea.y

            searchPath(goalCol, goalRow);
        } else {

            if (direction == "up" || direction == "down") {
                solidArea.x = 10;
                solidArea.y = 0;
                solidArea.width = 25;
                solidArea.height = 48;
            } else {
                solidArea.x = 0;
                solidArea.y = 8;
                solidArea.width = 48;
                solidArea.height = 32;
            }

            speed = defaultSpeed;
            actionLockCounter++;

            int firstChange = 60;
            int increaseAmount = 180;

            // 1 seconds after born, change direction
            if (actionLockCounter == firstChange) {
                standing = false;
                changeDirection();
            }

            // 3 seconds after first change direction, stop
            if (actionLockCounter == (firstChange + increaseAmount)) {
                standing = true;
            }

            // 3 seconds after, change direction again
            if (actionLockCounter == (firstChange + increaseAmount * 2)) {
                standing = false;
                changeDirection();
            }

            // stop for 3 seconds and enter to the second if
            if (actionLockCounter == (firstChange + increaseAmount * 3)) {
                standing = true;
                actionLockCounter = firstChange;
            }
        }
    }

    /**
     * <p>
     * Changes direction of wolf randomly
     * </p>
     * 
     * @since 1.0
     */
    public void changeDirection() {
        int nextMove = rand.nextInt(8) + 1;

        switch (nextMove) {
            case 1:
                direction = "up";
                break;
            case 2:
                direction = "upleft";
                break;
            case 3:
                direction = "upright";
                break;
            case 4:
                direction = "down";
                break;
            case 5:
                direction = "downleft";
                break;
            case 6:
                direction = "downright";
                break;
            case 7:
                direction = "left";
                break;
            case 8:
                direction = "right";
                break;
        }
    }

    /**
     * <p>
     * When player damage the wolf, sets inFight and onPath variables true
     * </p>
     * 
     * @since 1.0
     */
    public void damageReaction() {
        actionLockCounter = 0;
        onPath = true;
        inFight = true;
    }
}
