package enemy;

import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class ENEMY_Wolf extends Entity {

    public ENEMY_Wolf(GamePanel gp) {
        super(gp);
        
        name = "wolf";
        speed = 1;
        maxLife = 5;
        life = maxLife;
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
        up1 = setup("/wolf/up1");
        up2 = setup("/wolf/up2");
        up3 = setup("/wolf/up3");
        down1 = setup("/wolf/down1");
        down2 = setup("/wolf/down2");
        down3 = setup("/wolf/down3");
        left1 = setup("/wolf/left1");
        left2 = setup("/wolf/left2");
        left3 = setup("/wolf/left3");
        right1 = setup("/wolf/right1");
        right2 = setup("/wolf/right2"); 
        right3 = setup("/wolf/right3");
    }
    
    public void setAction() {
        
        actionLockCounter++;
        
        // after 5 seconds change direction
        if(actionLockCounter == 120) {
            
            standing = false;
            
            Random rand = new Random();
            int nextMove = rand.nextInt(8) + 1;
            
            switch(nextMove) {
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
        
        // stop for a while
        if(actionLockCounter == 240) {
            standing = true;
            actionLockCounter = 0;
        }
        
    }
}
