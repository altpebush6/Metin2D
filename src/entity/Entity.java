package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Entity {
    
    public Random rand = new Random();
    
    GamePanel gp;

    // Object Attributes
    public int coinValue;
    
    // States
	public int worldX,worldY,screenX,screenY,speed;
	public boolean collision = false;
	public boolean collisionOn = false;
	public Rectangle solidArea = new Rectangle(0,0,48,48);
	public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public String direction = "down";
    public boolean standing = false;
    public boolean attacking = false;
	
	// Character Attributes
	public int type;  // player=0, enemy=1
    public String name;
    public int maxLife;
    public int life; 
    public int actionLockCounter = 0;
	
	// Images
	public BufferedImage up1,up2,up3,down1,down2,down3,left1,left2,left3,right1,right2,right3;
	public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
	public BufferedImage image;
	
	// Counter
	public int stepCounter = 0;
	public int stepType = 0;
	public int enemySoundCounter = 0;
	public int invincibleCounter = 0;
	public boolean invincible = false;
	public int spriteNum = 1, spriteCounter = 0;
	
	
	public Entity(GamePanel gp) {
        this.gp = gp;
    }
	
	public void setAction() {}
	
	public void update() {
	    
	    setAction();
	    
        // CHECK TILE COLLISION
        collisionOn = false;
        gp.collisionChecker.checkTile(this);
        
        // CHECK ENEMY COLLISION
        gp.collisionChecker.checkEntity(this, gp.enemy);
        
        // CHECK PLAYER COLLISION 
        gp.collisionChecker.checkPlayer(this);
    
        
        // IF COLLISION IS FALSE, PLAYER CAN MOVE
        if(!collisionOn && !standing) {
            switch(direction) {
                case "upleft": worldY -= speed; worldX -= speed/2; break;
                
                case "upright": worldY -= speed; worldX += speed/2; break;
                    
                case "downleft": worldY += speed; worldX -= speed/2; break;
                    
                case "downright": worldY += speed; worldX += speed/2; break;
                
                case "up": worldY -= speed; break;
                
                case "down": worldY += speed;break;
                    
                case "left": worldX -= speed;break;
                    
                case "right": worldX += speed;break;
            }
        }
        
        if(!standing) {
            spriteCounter++;
            if(spriteCounter > 12) {
                if(spriteNum == 3) {
                    spriteNum = 1;
                }
                else {
                    spriteNum++;
                }
                spriteCounter = 0; 
            }
        }
        
        if(invincible) {
            invincibleCounter++;
            if(invincibleCounter == 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
	}

	public void draw(Graphics2D g2) {
	    
	    BufferedImage image = null;
	    
	    int screenX = worldX - gp.player.worldX + gp.player.screenX;
	    int screenY = worldY - gp.player.worldY + gp.player.screenY;
        
        if(worldX > gp.player.worldX - gp.player.screenX - gp.tileSize &&
           worldX < gp.player.worldX + gp.player.screenX + gp.tileSize &&
           worldY > gp.player.worldY - gp.player.screenY - gp.tileSize &&
           worldY < gp.player.worldY + gp.player.screenY + gp.tileSize) {
            
            switch(direction) {
                case "up":
                case "upleft":
                case "upright":
                    if(spriteNum == 1)  image = up1;
                    if(spriteNum == 2)  image = up2;
                    if(spriteNum == 3)  image = up3;
                    break;
                case "down":
                case "downleft":
                case "downright":
                    if(spriteNum == 1)  image = down1;
                    if(spriteNum == 2)  image = down2;
                    if(spriteNum == 3)  image = down3;
                    break;
                case "left":
                    if(spriteNum == 1)  image = left1;
                    if(spriteNum == 2)  image = left2;
                    if(spriteNum == 3)  image = left3;
                    break;
                case "right":
                    if(spriteNum == 1)  image = right1;
                    if(spriteNum == 2)  image = right2;
                    if(spriteNum == 3)  image = right3;
                    break;        
            }
            
            // Set entity transparent after damage
            if(invincible) {
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
            }
            
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            
            // Reset transparency
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            
            
        }
	}
	
	public BufferedImage setup(String imagePath, int width, int height) {
	        
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        
        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image, width, height);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return image;
    }
}
