package entity;

import main.KeyHandler;
import main.MouseHandler;
import main.UtilityTool;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Player extends Entity {
	
	GamePanel gp;
	KeyHandler keyH;
	MouseHandler mouseH;
	
	public final int screenX; // Where we draw player on screen X
	public final int screenY; // Where we draw player on screen Y
	
	public int playerCoin = 0;
	public String playerWeapon="";
	
	int goalX;
	int goalY;
	int beforeClickX;
	int beforeClickY;
	boolean goalReachedX = false;
	boolean goalReachedY = false;
	
	public Player(GamePanel gp, KeyHandler keyH, MouseHandler mouseH) {
	    super(gp);
		this.gp = gp;
		this.keyH = keyH;
		this.mouseH = mouseH;
		
		screenX = gp.screenWidth / 2 - gp.tileSize / 2;
		screenY = gp.screenHeight / 2 - gp.tileSize / 2;
		
		solidArea = new Rectangle();
		solidArea.x = 0;
		solidArea.y = 0;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = gp.tileSize;
		solidArea.height = gp.tileSize; // change for mt2 char
		
        worldX = 25 * gp.tileSize; // Where character will start on map X
        worldY = 25 * gp.tileSize; // Where character will start on map Y
        speed = 2;  
        direction = "down";
	      
		getPlayerImage();
	}
	
	public void getPlayerImage() {
		up1 = setup("/player/up1");
		up2 = setup("/player/up2");
		down1 = setup("/player/down1");
		down2 = setup("/player/down2");
		left1 = setup("/player/left1");
		left2 = setup("/player/left2");
		right1 = setup("/player/right1");
		right2 = setup("/player/right2");
	}
	
	public void update() {
		// Check Object Collision
		int objIndex = gp.collisionChecker.checkObject(this, true);
		if(objIndex != -1) {
			if(keyH.quotePressed) {
				pickUpObject(objIndex);
				keyH.quotePressed = false;
			}else {
				gp.ui.showMessage("Press \" to pick up item.");
			}
		}
		
		if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
			
			// Finish mouse event
			mouseH.pressed = false;
			goalReachedX = true;
			goalReachedY = true;
			
			if(keyH.upPressed && keyH.leftPressed) {
				direction = "upleft";
			}else if(keyH.upPressed && keyH.rightPressed) {
				direction = "upright";
			}else if(keyH.downPressed && keyH.leftPressed) {
				direction = "downleft";
			}else if(keyH.downPressed && keyH.rightPressed) {
				direction = "downright";
			}else if(keyH.upPressed) {
				direction = "up";
			}else if(keyH.downPressed) {
				direction = "down";
			}else if(keyH.leftPressed) {
				direction = "left";
			}else if(keyH.rightPressed) {
				direction = "right";
			}
			
			// CHECK TILE COLLISION
			collisionOn = false;
			gp.collisionChecker.checkTile(this);
			
			// IF COLLISION IS FALSE, PLAYER CAN MOVE
			if(!collisionOn) {
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
			
			// Step Sound
			stepCounter++;
			if(stepCounter == 20) {
				if(stepType == 0) {
					gp.playSE(4);
					stepType = 1;
				}else if(stepType == 1){
					gp.playSE(5);
					stepType = 0;
				}
				stepCounter = 0;
			}
			
			spriteCounter++;
			if(spriteCounter > 12) {
				if(spriteNum == 2) {
					spriteNum = 1;
				}
				else {
					spriteNum++;
				}
				spriteCounter = 0; 
			}
		}else if(mouseH.screenX != 0 || mouseH.screenY != 0) { // if there is a point to go
						
			// Check Tile Collision 
			collisionOn = false;
			gp.collisionChecker.checkTile(this);
			
			if(mouseH.pressed) { // if mouse pressed 
				
				beforeClickX = worldX; // save worldX position
				beforeClickY = worldY;	// save worldY position
				
				goalX = (screenX - mouseH.screenX + gp.tileSize / 2); // Find how much pixel we should go on x axis from worldX position
				goalY = (screenY - mouseH.screenY + gp.tileSize / 2); // Find how much pixel we should go on y axis from worldY position
				
				mouseH.pressed = false; // set mouse pressed false
			}
			
				
			if(goalX > 0) { // If X goal is on the left side of char
				
				if(worldX > beforeClickX - goalX) { // If goal point is still on the left of character
					if(!collisionOn) {
						if(goalY != 0) { // If player moves on x and y axis divide speed by 2
							worldX -= speed / 2;
						}else {
							worldX -= speed;
						}
					}
					direction = "left";	
				}else { // If character reaches the point
					goalReachedX = true;
				}
				
			}else if(goalX < 0){ // If X goal is on the right side of char
				
				if(worldX < beforeClickX - goalX) { // If goal point is still on the right of character
					if(!collisionOn) {
						if(goalY != 0) { // If player moves on x and y axis divide speed by 2
							worldX += speed / 2;
						}else {
							worldX += speed;
						}
					}
					direction = "right";
				}else { // If character reaches the point
					goalReachedX = true;
				}
			}
			
			if(goalY > 0) { // If Y goal is on the top side of char
				
				if(worldY > beforeClickY - goalY) { // If goal point is still on the top of character
					if(!collisionOn) {
						if(goalX != 0) { // If player moves on x and y axis divide speed by 2
							worldY -= speed / 2;
						}else {
							worldY -= speed;
						}
					}
					direction = "up";	
				}else { // If character reaches the point
					goalReachedY = true;
				}
				
			}else if(goalY < 0){ // If Y goal is on the bottom side of char
				
				if(worldY < beforeClickY - goalY) { // If goal point is still on the bottom of character
					if(!collisionOn) {
						if(goalX != 0) { // If player moves on x and y axis divide speed by 2
							worldY += speed / 2;
						}else {
							worldY += speed;
						}
					}
					direction = "down";
				}else { // If character reaches the point
					goalReachedY = true;
				}
			}
			
	        // Step Sound
            stepCounter++;
            if(stepCounter == 20) {
                if(stepType == 0) {
                    gp.playSE(4);
                    stepType = 1;
                }else if(stepType == 1){
                    gp.playSE(5);
                    stepType = 0;
                }
                stepCounter = 0;
            }
			
			// to animate character movement
			spriteCounter++;
			if(spriteCounter > 10) {
				if(spriteNum == 1) {
					spriteNum = 2;
				}
				else if(spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0; 
			}
			
			if(goalReachedX) {
				mouseH.screenX = 0;
				goalReachedX = false;
				goalX = 0;
			}
			if(goalReachedY) {
				mouseH.screenY = 0;
				goalReachedY = false;
				goalY = 0;
			}

		}
	}
	
	public void pickUpObject(int index) {
		if(index != -1) {
			
			String objName = gp.obj[index].name;
			
			switch(objName) {
				case "Coin":
					gp.playSE(2);
					playerCoin += gp.obj[index].value;
					gp.ui.showMessage(gp.obj[index].value + " yang collected.");
					gp.obj[index] = null;
					break;
				case "Dolunay":
					gp.playSE(3);
					playerWeapon = gp.obj[index].name;
					gp.ui.showMessage(gp.obj[index].name + " kılıcı kazanıldı.");
					gp.ui.itemIndex = 1;
					try {
						down1 = ImageIO.read(getClass().getResourceAsStream("/player/down1_dolunay.png"));
						down2 = ImageIO.read(getClass().getResourceAsStream("/player/down2_dolunay.png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					gp.obj[index] = null;
					break;
			}
		}
	}
	
	public void draw(Graphics2D g2) {
	
		BufferedImage image = null;
		
		switch(direction) {
			case "up":
			case "upleft":
			case "upright":
				if(spriteNum == 1)
					image = up1;
				if(spriteNum == 2)
					image = up2;
				break;
			case "down":
			case "downleft":
			case "downright":
				if(spriteNum == 1)
					image = down1;
				if(spriteNum == 2)
					image = down2;
				break;
			case "left":
				if(spriteNum == 1)
					image = left1;
				if(spriteNum == 2)
					image = left2;
				break;
			case "right":
				if(spriteNum == 1)
					image = right1;
				if(spriteNum == 2)
					image = right2;
				break; 
		}
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
	}
}