package entity;

import main.KeyHandler;
import main.MouseHandler;
import main.MovePlayer;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;
import main.GamePanel;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;
    MouseHandler mouseH;

    public int screenX; // Where we draw player on screen X
    public int screenY; // Where we draw player on screen Y

    // Player Specification
    public int playerCoin;
    public String playerWeapon;
    public int playerTimer;
    public int increaseLife;
    public int speedDefault;

    // Mouse Click Movement
    public int goalX;
    public int goalY;
    public int beforeClickX;
    public int beforeClickY;
    boolean goalReachedX = false;
    boolean goalReachedY = false;

    public Random rand = new Random();
    
    public int punchTimeOut = 0;
    public int holdingNum = 0;
    public int holdingCounter = 0;
    public int noPunchCounter = 0;
    public int skillSpriteCounter = 0;
    public int skillStandbyTime = 120; // 10s
    public int clickCounter = 0;
    public boolean spacePressed = false;
    public boolean doubleClicked = false;
    int attackWalkingSpeed = 1;
    
    
    public Player(GamePanel gp, KeyHandler keyH, MouseHandler mouseH) {
        super(gp);
        this.gp = gp;
        this.keyH = keyH;
        this.mouseH = mouseH;
        
        type = 2;
        name = "xKralTr";

        screenX = gp.screenWidth / 2 - gp.tileSize / 2;
        screenY = gp.screenHeight / 2 - gp.tileSize / 2;

        // CHANGE THIS ACCORDING TO CHARACTER PIXEL ART @@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 18;

        // CHANGE THIS ACCORDING TO ATTACKING CHARACTER PIXEL ART
        // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        attackArea.width = 36;
        attackArea.height = 36;

        setPlayer();
        getPlayerImage();
        getPlayerAttackImage();

    }

    public void setPlayer() {

        // Player Movement
        worldX = 25 * gp.tileSize; // Where character will start on map X
        worldY = 25 * gp.tileSize; // Where character will start on map Y
        speed = 5;
        speedDefault = speed;
        direction = "down";

        // Player Specifications
        maxLife = 100;
        life = maxLife;
        increaseLife = 1;
        playerTimer = 0;
        playerCoin = 0;
        playerWeapon = "";

    }

    public void getPlayerImage() {
        up1 = setup("/player/up1", gp.tileSize, gp.tileSize);
        up2 = setup("/player/up2", gp.tileSize, gp.tileSize);
        up3 = setup("/player/up2", gp.tileSize, gp.tileSize);

        down1 = setup("/player/down1", gp.tileSize, gp.tileSize);
        down2 = setup("/player/down2", gp.tileSize, gp.tileSize);
        down3 = setup("/player/down2", gp.tileSize, gp.tileSize);

        left1 = setup("/player/left1", gp.tileSize, gp.tileSize);
        left2 = setup("/player/left2", gp.tileSize, gp.tileSize);
        left3 = setup("/player/left2", gp.tileSize, gp.tileSize);

        right1 = setup("/player/right1", gp.tileSize, gp.tileSize);
        right2 = setup("/player/right2", gp.tileSize, gp.tileSize);
        right3 = setup("/player/right2", gp.tileSize, gp.tileSize);
    }

    public void getPlayerAttackImage() {
        attackUp1 = setup("/player/boy_attack_up_1", gp.tileSize, gp.tileSize * 2);
        attackUp2 = setup("/player/boy_attack_up_2", gp.tileSize, gp.tileSize * 2);

        attackDown1 = setup("/player/boy_attack_down_1", gp.tileSize, gp.tileSize * 2);
        attackDown2 = setup("/player/boy_attack_down_2", gp.tileSize, gp.tileSize * 2);

        attackLeft1 = setup("/player/boy_attack_left_1", gp.tileSize * 2, gp.tileSize);
        attackLeft2 = setup("/player/boy_attack_left_2", gp.tileSize * 2, gp.tileSize);

        attackRight1 = setup("/player/boy_attack_right_1", gp.tileSize * 2, gp.tileSize);
        attackRight2 = setup("/player/boy_attack_right_2", gp.tileSize * 2, gp.tileSize);
    }

    public void update() {

        //clickCounter++;  // to detect double click
        
        
        
        // If Player doesn't press space longer than damageTimeOut (45sec) second reset holding
        
        noPunchCounter++;
        if(noPunchCounter >= damageTimeOut) {
            gp.player.holdingCounter = 0;
            gp.player.holdingNum = 0;
        }
        
        // When pressed space
        punchTimeOut++;
        if (keyH.spacePressed && !gp.skills.skillUsed) {
            //System.out.println("noPunchCounter: "+ noPunchCounter+" punchTimeOut: "+punchTimeOut+" holdingCounter: "+holdingCounter);
            noPunchCounter = 0;
            holdingCounter++;
            if (punchTimeOut >= damageTimeOut) {
                punchTimeOut = 0;
                attacking = true;
                gp.playSE(holdingNum+10);
                holdingNum++;
                if (holdingNum == 4) {
                    holdingNum = 0;
                    holdingCounter = 0;
                }
            }
        }
        
        
        if(gp.skills.swordSpinTimeOut != 0) {
            gp.skills.swordSpinTimeOut++;
        }
        
        if(gp.skills.swordSpinTimeOut == skillStandbyTime) {
            gp.skills.swordSpinTimeOut = 0;
        }
        int skillTimeOut = 75;
        if (gp.skills.swordSpinUsed && gp.skills.swordSpinTimeOut == 0) {
            if(gp.skills.swordSpinCounter > 20) {
                useSkill(gp.skills.skillType);  
                gp.skills.skillUsed = true;
            }
            speed = attackWalkingSpeed;
            gp.skills.swordSpinCounter++;
            if(gp.skills.swordSpinCounter == skillTimeOut) {
                gp.skills.swordSpinCounter = 0;
                gp.skills.swordSpinUsed = false;
                gp.skills.skillUsed = false;
                spriteNum = 1;
                gp.skills.swordSpinTimeOut++;
            }
        }else if (attacking) {
            attack();
        }else {
            speed = speedDefault;
        }
        
        // To avoid player to get damage every frame. Instead waits for 1 seconds and get damage.
        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter == 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }

        // Check Object Collision
        int objIndex = gp.collisionChecker.checkObject(this, true);
        if (objIndex != -1) {
            if (keyH.quotePressed) {
                pickUpObject(objIndex);
                keyH.quotePressed = false;
            } else {
                gp.ui.showMessage("Press \" to pick up item.");
            }
        }

        // CHECK ENEMY COLLISION
        int enemyIndex = gp.collisionChecker.checkFightArea(this, gp.enemy);
        startFight(enemyIndex);

        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            // Finish mouse event
            mouseH.pressed = false;
            goalReachedX = true;
            goalReachedY = true;

            if (keyH.upPressed && keyH.leftPressed) {           direction = "upleft";} 
            else if (keyH.upPressed && keyH.rightPressed) {     direction = "upright";} 
            else if (keyH.downPressed && keyH.leftPressed) {    direction = "downleft";} 
            else if (keyH.downPressed && keyH.rightPressed) {   direction = "downright";}
            else if (keyH.upPressed) {                          direction = "up";}
            else if (keyH.downPressed) {                        direction = "down";}
            else if (keyH.leftPressed) {                        direction = "left";}
            else if (keyH.rightPressed) {                       direction = "right";}

            // CHECK TILE COLLISION
            collisionOn = false;
            gp.collisionChecker.checkTile(this);

            // CHECK ENEMY COLLISION
            gp.collisionChecker.checkEntity(this, gp.enemy);
            
            
            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (!collisionOn) {
                MovePlayer.move(gp);
            }

            // Step Sound
            stepCounter++;
            if (stepCounter == 20) {
                if (stepType == 0) {
                    gp.playSE(4);
                    stepType = 1;
                } else if (stepType == 1) {
                    gp.playSE(5);
                    stepType = 0;
                }
                stepCounter = 0;
            }

            if(!gp.skills.skillUsed && !attacking) {
                spriteCounter++;
                if (spriteCounter > 12) {
                    if (spriteNum >= 3) {
                        spriteNum = 1;
                    } else {
                        spriteNum++;
                    }
                    spriteCounter = 0;
                }
            }
        } else if (goalX != 0 || goalY != 0) { // if there is a point to go

            // Check Tile Collision
            collisionOn = false;
            gp.collisionChecker.checkTile(this);

            // CHECK ENEMY COLLISION
            gp.collisionChecker.checkEntity(this, gp.enemy);

            if (goalX > 0) { // If X goal is on the left side of char
                if (worldX > beforeClickX - goalX) { // If goal point is still on the left of character
                    if (!collisionOn) {
                        if (goalY != 0) { // If player moves on x and y axis divide speed by 2
                            worldX -= speed / 2;
                        } else {
                            worldX -= speed;
                        }
                    }
                    direction = "left";
                } else { // If character reaches the point
                    goalReachedX = true;
                }
            } else if (goalX < 0) { // If X goal is on the right side of char

                if (worldX < beforeClickX - goalX) { // If goal point is still on the right of character
                    if (!collisionOn) {
                        if (goalY != 0) { // If player moves on x and y axis divide speed by 2
                            worldX += speed / 2;
                        } else {
                            worldX += speed;
                        }
                    }
                    direction = "right";
                } else { // If character reaches the point
                    goalReachedX = true;
                }
            }
            if (goalY > 0) { // If Y goal is on the top side of char

                if (worldY > beforeClickY - goalY) { // If goal point is still on the top of character
                    if (!collisionOn) {
                        if (goalX != 0) { // If player moves on x and y axis divide speed by 2
                            worldY -= speed / 2;
                        } else {
                            worldY -= speed;
                        }
                    }
                    direction = "up";
                } else { // If character reaches the point
                    goalReachedY = true;
                }
            } else if (goalY < 0) { // If Y goal is on the bottom side of char

                if (worldY < beforeClickY - goalY) { // If goal point is still on the bottom of character
                    if (!collisionOn) {
                        if (goalX != 0) { // If player moves on x and y axis divide speed by 2
                            worldY += speed / 2;
                        } else {
                            worldY += speed;
                        }
                    }
                    direction = "down";
                } else { // If character reaches the point
                    goalReachedY = true;
                }
            }

            // Step Sound
            stepCounter++;
            if (stepCounter == 20) {
                if (stepType == 0) {
                    gp.playSE(4);
                    stepType = 1;
                } else if (stepType == 1) {
                    gp.playSE(5);
                    stepType = 0;
                }
                stepCounter = 0;
            }

            // to animate character movement
            if(!gp.skills.skillUsed && !attacking) {
                spriteCounter++;
                if (spriteCounter > 10) {
                    if (spriteNum == 1) {
                        spriteNum = 2;
                    } else if (spriteNum == 2) {
                        spriteNum = 1;
                    }
                    spriteCounter = 0;
                }
            }

            if (goalReachedX) {
                mouseH.screenX = 0;
                goalReachedX = false;
                goalX = 0;
            }
            if (goalReachedY) {
                mouseH.screenY = 0;
                goalReachedY = false;
                goalY = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        // To avoid sliding image when sizes are different
        int tempScreenX = screenX;
        int tempScreenY = screenY;
        
        // Player Label
        g2.setFont(new Font("Courier New",Font.BOLD,13));
        g2.setColor(Color.green);
        g2.drawString("Lv " + level, screenX - 25, screenY - 20);
        
        g2.setColor(Color.yellow);
        g2.drawString(name, screenX + 20, screenY - 20);
        
        if(gp.skills.skillUsed) {
            if (spriteNum == 1)
                image = attackRight2;
            if (spriteNum == 2) {
                image = attackUp2;
                tempScreenY = screenY - gp.tileSize;
            }if (spriteNum == 3) {
                tempScreenX = screenX - gp.tileSize;
                image = attackLeft2;
            }if (spriteNum == 4)
                image = attackDown2;
        }else {       
            switch (direction) {
                case "up":
                case "upleft":
                case "upright":
                    if (attacking) {
                        if (spriteNum == 1)
                            image = attackUp1;
                        if (spriteNum == 2)
                            image = attackUp2;
    
                        tempScreenY = screenY - gp.tileSize; // To avoid sliding image when image sizes are different
                    } else {
                        if (spriteNum == 1)
                            image = up1;
                        if (spriteNum == 2)
                            image = up2;
                        if (spriteNum == 3)
                            image = up3;
                    }
    
                    break;
                case "down":
                case "downleft":
                case "downright":
                    if (attacking) {
                        if (spriteNum == 1)
                            image = attackDown1;
                        if (spriteNum == 2)
                            image = attackDown2;
                    } else {
                        if (spriteNum == 1)
                            image = down1;
                        if (spriteNum == 2)
                            image = down2;
                        if (spriteNum == 3)
                            image = down3;
                    }
                    break;
                case "left":
                    if (attacking) {
                        if (spriteNum == 1)
                            image = attackLeft1;
                        if (spriteNum == 2)
                            image = attackLeft2;
    
                        tempScreenX = screenX - gp.tileSize; // To avoid sliding image when image sizes are different
                    } else {
                        if (spriteNum == 1)
                            image = left1;
                        if (spriteNum == 2)
                            image = left2;
                        if (spriteNum == 3)
                            image = left3;
                    }
    
                    break;
                case "right":
                    if (attacking) {
                        if (spriteNum == 1)
                            image = attackRight1;
                        if (spriteNum == 2)
                            image = attackRight2;
                    } else {
                        if (spriteNum == 1)
                            image = right1;
                        if (spriteNum == 2)
                            image = right2;
                        if (spriteNum == 3)
                            image = right3;
                    }
                    break;  
              }
        }

 
      

        // Set player transparent after damage
        if (invincible) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
        }

        g2.drawImage(image, tempScreenX, tempScreenY, null);

        // Reset transparency
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

    public void attack() {
        spriteCounter++;
        if (spriteCounter <= 5) {
            spriteNum = 1;
        } else if (spriteCounter > 5 && spriteCounter <= 20) {
            spriteNum = 2;
            
            speed = attackWalkingSpeed;

            // Save the current worldX, worldY, solidArea
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            // Adjust player's worldX and worldY for the attackArea
            switch (direction) {
                case "up":
                case "upleft":
                case "upright":
                    worldY -= attackArea.height;
                    break;
                case "down":
                case "downleft":
                case "downright":
                    worldY += attackArea.height;
                    break;
                case "left":
                    worldX -= attackArea.width;
                    break;
                case "right":
                    worldX += attackArea.width;
                    break;
            }

            // Attack area becomes solidArea
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            int enemyIndex = gp.collisionChecker.checkEntity(this, gp.enemy); // check enemy collision with the updated
                                                                              // worldX, worldY and solidArea
            damageEnemy(enemyIndex);

            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;

        } else {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
            speed = speedDefault;
        }
    }
    
    public void useSkill(int skillType) {
        
        // Save the current worldX, worldY, solidArea
        int currentWorldX = worldX;
        int currentWorldY = worldY;
        int solidAreaWidth = solidArea.width;
        int solidAreaHeight = solidArea.height;
        
        
        int increaseAmount = 3;
        if(skillSpriteCounter < increaseAmount) {
            spriteNum = 1;
            worldX += attackArea.width;
        }else if(skillSpriteCounter < increaseAmount * 2) {
            spriteNum = 2;
            worldY -= attackArea.height;
        }else if(skillSpriteCounter < increaseAmount * 3) {
            spriteNum = 3;
            worldX -= attackArea.width;
        }else if(skillSpriteCounter < increaseAmount * 4) {
            spriteNum = 4;
            worldY += attackArea.height;
        }else {
            spriteNum = 1;
            skillSpriteCounter = 0;
        }
        skillSpriteCounter++;
        

        // Attack area becomes solidArea
        solidArea.width = attackArea.width;
        solidArea.height = attackArea.height;

        int enemyIndex = gp.collisionChecker.checkEntity(this, gp.enemy); // check enemy collision with the updated
                                                                          // worldX, worldY and solidArea
        damageEnemy(enemyIndex);

        worldX = currentWorldX;
        worldY = currentWorldY;
        solidArea.width = solidAreaWidth;
        solidArea.height = solidAreaHeight;
    
    }

    public void pickUpObject(int index) {

        if (index != -1) {

            String objName = gp.obj[index].name;

            switch (objName) {
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
        if (index != -1) {
            String enemyName = gp.enemy[index].name;

            switch (enemyName) {
                case "Wolf":
                    
                    // Wolf Barking
                    enemySoundCounter++;
                    if (enemySoundCounter == 40) {
                        int soundChoice = rand.nextInt(2)+7;
                        gp.playSE(soundChoice);
                        
                        enemySoundCounter = 0;
                    }
                    // Wolf Damaging
                    int enemyIndex = gp.collisionChecker.checkEntity(this, gp.enemy);
                    if (enemyIndex != -1) {
                        if (!invincible) {
                            int soundChoice = rand.nextInt(5)+14;
                            gp.playSE(soundChoice);
                            int damage = rand.nextInt(5) + 1;
                            life -= damage;
                            invincible = true;
                        }
                    }

                    break;
            }
        } else {
            if ( life < maxLife) {
                playerTimer++;
                if (playerTimer == 120) {
                    if (maxLife - life < increaseLife) {
                        life += maxLife - life;
                        playerTimer = 0;
                    } else {
                        life += increaseLife;
                        playerTimer = 0;
                    }
                }

            }
            /*if (gp.player.playerSp < 100) {
                playerTimer++;
                if (playerTimer == 3600) {
                    if (maxPlayerSp - playerSp < increaseSp) {
                        playerSp+= maxPlayerSp - playerSp;
                        playerTimer = 0;
                    } else {
                        playerSp += increaseSp;
                        playerTimer = 0;
                    }
                }

            }*/
        }
    }

    public void damageEnemy(int enemyIndex) {
        if (enemyIndex != -1) {
            if (!gp.enemy[enemyIndex].invincible) {
                gp.enemy[enemyIndex].damageCounter = 0;
                gp.enemy[enemyIndex].life -= 1;
                gp.enemy[enemyIndex].invincible = true;
                gp.enemy[enemyIndex].damageReaction();
                
                if (gp.enemy[enemyIndex].life <= 0) {
                    gp.aSetter.aliveWolfNum--;
                    gp.playSE(6);
                    int coinNumber = rand.nextInt(3) + 1;

                    int coinCounter = 0;
                    for (int i = coinNumber; i > 0; i--) {
                        if (coinCounter == 0) {
                            gp.aSetter.createCoin(gp.enemy[enemyIndex].worldX + i * gp.tileSize / 10,
                                    gp.enemy[enemyIndex].worldY + i * gp.tileSize / 10);
                        } else if (coinCounter == 1) {
                            gp.aSetter.createCoin(gp.enemy[enemyIndex].worldX - i * gp.tileSize / 10,
                                    gp.enemy[enemyIndex].worldY - i * gp.tileSize / 10);
                        } else if (coinCounter == 2) {
                            gp.aSetter.createCoin(gp.enemy[enemyIndex].worldX - i * gp.tileSize / 10,
                                    gp.enemy[enemyIndex].worldY + i * gp.tileSize / 10);
                        } else if (coinCounter == 3) {
                            gp.aSetter.createCoin(gp.enemy[enemyIndex].worldX + i * gp.tileSize / 10,
                                    gp.enemy[enemyIndex].worldY - i * gp.tileSize / 10);
                            coinCounter = 0;
                        }
                        coinCounter++;
                    }

                    gp.enemy[enemyIndex].dying = true;
                    gp.enemy[enemyIndex].alive = false;
                    gp.aSetter.createDeadWolf(worldX,worldY + (gp.tileSize /2));
                }
            }
        }
    }
}
