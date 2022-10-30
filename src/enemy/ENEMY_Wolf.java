package enemy;

import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class ENEMY_Wolf extends Entity {

    Random rand = new Random();
    GamePanel gp;
    
    public ENEMY_Wolf(GamePanel gp) {
        
        super(gp);
        
        this.gp = gp;
        
        name = "wolf";
        speed = 1;
        maxLife = 3;
        life = maxLife;
        type = 1;
        direction = "down";
        
        if(direction == "up" || direction == "down") {
            solidArea.x = 10;
            solidArea.y = 0;
            solidArea.width = 25;
            solidArea.height = 48;
        }else {
            solidArea.x = 0;
            solidArea.y = 8;
            solidArea.width = 48;
            solidArea.height = 32;
        }
        
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        getImage();
    }
    
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
        
        deadImage = setup("/wolf/deadWolf", gp.tileSize, gp.tileSize);
    }
    
    public void setAction() {
        
        actionLockCounter++;
        
        // after 5 seconds change direction
        if(actionLockCounter == 120) {
            
            standing = false;

            int nextMove = rand.nextInt(8) + 1;
            
            switch(nextMove) {
                case 1: direction = "up";       break;
                case 2: direction = "upleft";   break;
                case 3: direction = "upright";  break;
                case 4: direction = "down";     break;
                case 5: direction = "downleft"; break;
                case 6: direction = "downright";break;
                case 7: direction = "left";     break;
                case 8: direction = "right";    break;
            }
        }
        
        // stop for a while
        if(actionLockCounter == 240) {
            standing = true;
            actionLockCounter = 0;
        }
        
    }
}
