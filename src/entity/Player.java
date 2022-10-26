package entity;

import main.KeyHandler;
import main.MouseHandler;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Player extends Entity {
	
	GamePanel gp;
	KeyHandler keyH;
	MouseHandler mouseH;
	
	public final int screenX; // Where we draw player on screen X
	public final int screenY; // Where we draw player on screen Y
	
	// Player Specification
	public int playerCoin;
	public int playerHealth;
	public String playerWeapon;
	
	// Mouse Click Movement
	public int goalX;
	public int goalY;
	public int beforeClickX;
	public int beforeClickY;
	boolean goalReachedX = false;
	boolean goalReachedY = false;
	
	public Random rand = new Random();
	
	public int punchTimeOut = 0;
	
	public Player(GamePanel gp, KeyHandler keyH, MouseHandler mouseH) {
	    super(gp);
		this.gp = gp;
		this.keyH = keyH;
		this.mouseH = mouseH;
		
		
		
		screenX = gp.screenWidth / 2 - gp.tileSize / 2;
		screenY = gp.screenHeight / 2 - gp.tileSize / 2;
		
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 30;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 18; // change for mt2 char
	      
		getImage();
		setPlayer();
	}
	
	public void setPlayer() {
	    
	    // Player Movement
        worldX = 25 * gp.tileSize; // Where character will start on map X
        worldY = 25 * gp.tileSize; // Where character will start on map Y
        speed = 2;  
        direction = "down";
	    
        // Player Specifications
        playerHealth = 100;
        playerCoin = 0;
        playerWeapon = "";
	    
	}
	
	public void getImage() {
		up1 = setup("/player/up1");
		up2 = setup("/player/up2");
		up3 = setup("/player/up2");
		
		down1 = setup("/player/down1");
		down2 = setup("/player/down2");
		down3 = setup("/player/down2");
		
		left1 = setup("/player/left1");
		left2 = setup("/player/left2");
		left3 = setup("/player/left2");
		
		right1 = setup("/player/right1");
		right2 = setup("/player/right2");
		right3 = setup("/player/right2");
	}
	
	public void update() {
	    
	    if(!getDamage) {
	        damageCounter++;
	        if(damageCounter == 60) {
	            getDamage = true;
	            damageCounter = 0;
	        }
	    }

	    
	    punchTimeOut++;
	    if(keyH.spacePressed && punchTimeOut >= 120) {
	        gp.playSE(9);
	        System.out.println("Punched");
	        punchTimeOut = 0;
	    }
	    
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
		
        // CHECK ENEMY COLLISION
        int enemyIndex = gp.collisionChecker.checkFightArea(this, gp.enemy);
        startFight(enemyIndex);
		
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
			
	        // CHECK ENEMY COLLISION
	        gp.collisionChecker.checkEntity(this, gp.enemy);
			
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
				if(spriteNum == 3) {
					spriteNum = 1;
				}
				else {
					spriteNum++;
				}
				spriteCounter = 0; 
			}
		}else if(goalX != 0 || goalY != 0) { // if there is a point to go
						
			// Check Tile Collision 
			collisionOn = false;
			gp.collisionChecker.checkTile(this);
			
            // CHECK ENEMY COLLISION
            gp.collisionChecker.checkEntity(this, gp.enemy);
			
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
                if(spriteNum == 3)
                    image = up3;
                break;
            case "down":
            case "downleft":
            case "downright":
                if(spriteNum == 1)
                    image = down1;
                if(spriteNum == 2)
                    image = down2;
                if(spriteNum == 3)
                    image = down3;
                break;
            case "left":
                if(spriteNum == 1)
                    image = left1;
                if(spriteNum == 2)
                    image = left2;
                if(spriteNum == 3)
                    image = left3;
                break;
            case "right":
                if(spriteNum == 1)
                    image = right1;
                if(spriteNum == 2)
                    image = right2;
                if(spriteNum == 3)
                    image = right3;
                break;        
        }
        
        // Set player transparent after damage
        if(!getDamage) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        
        g2.drawImage(image, screenX, screenY, null);
        
        // Reset transparency
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
	
	public void pickUpObject(int index) {
	    
		if(index != -1) {
			
			String objName = gp.obj[index].name;
			
			switch(objName) {
				case "Coin":
					gp.playSE(2);
					playerCoin += gp.obj[index].coinValue;
					gp.ui.showMessage(gp.obj[index].coinValue + " yang collected.");
					gp.obj[index] = null;
					break;
				case "Dolunay":
					gp.playSE(3);
					playerWeapon = gp.obj[index].name;
					gp.ui.showMessage(gp.obj[index].name + " kılıcı kazanıldı.");
					gp.ui.itemIndex = 1;
					gp.obj[index] = null;
					break;
			}
		}
	}
	
	public void startFight(int index) {
	    
	    if(index != -1) {
	        
	        String enemyName = gp.enemy[index].name;
	        
	        switch(enemyName) {
	            case "wolf":
	                enemySoundCounter++;
	                if(enemySoundCounter == 40) {

	                    int soundChoice = rand.nextInt(2);
	                    
	                    if(soundChoice == 0) {
	                        gp.playSE(7);
	                    }else if(soundChoice == 1) {
	                        gp.playSE(8);
	                    }
	                    enemySoundCounter = 0;
	                }  
	                int enemyIndex = gp.collisionChecker.checkEntity(this, gp.enemy);
                    if(enemyIndex != -1) {
                           if(getDamage) {
                                int damage = rand.nextInt(5) + 1;
                                playerHealth -= damage;
                                
                                System.out.println("Get Damage: " + damage + " Health: " + playerHealth);
                                getDamage = false;
                            }
                    }
	                
	                break;
	        }
	    }
	}
}
