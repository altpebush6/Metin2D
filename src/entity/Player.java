package entity;

import main.KeyHandler;
import main.MouseHandler;
import main.MovePlayer;
import object.OBJ_Dolunay;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import main.Damages;
import main.GamePanel;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;
    MouseHandler mouseH;

    public int screenX; // Where we draw player on screen X
    public int screenY; // Where we draw player on screen Y
    public int defaultScreenX; // Where we draw player on screen X
    public int defaultScreenY; // Where we draw player on screen Y

    // Player Specification
    public int playerCoin;
    public String playerWeapon;
    public int playerTimer;
    public int increaseLife;
    public double increaseSp;
    public int speedDefault;
    public int playerXP;
    public int attackPower;

    // Mouse Click Movement
    public int goalX;
    public int goalY;

    public Random rand = new Random();

    public int punchTimeOut = 0;
    public int holdingNum = 0;
    public int holdingCounter = 0;
    public int noPunchCounter = 0;
    public int clickCounter = 0;
    public boolean autoHit = false;
    public boolean doubleClicked = false;
    int attackWalkingSpeed = 1;

    public boolean reborn = false;
    public int rebornCounter;

    public int deadCounter = 0;

    // INVENTORY
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;
    public Entity currentWeapon;
    
    public BufferedImage image;
    
    // To avoid sliding image when sizes are different
    public int tempScreenX;
    public int tempScreenY;

    public Player(GamePanel gp, KeyHandler keyH, MouseHandler mouseH) {
        super(gp);
        this.gp = gp;
        this.keyH = keyH;
        this.mouseH = mouseH;

        type = playerType;
        name = "xKralTr";

        defaultScreenX = screenX = gp.screenWidth / 2 - gp.tileSize / 2;
        defaultScreenY = screenY = gp.screenHeight / 2 - gp.tileSize / 2;

        // CHANGE THIS ACCORDING TO CHARACTER PIXEL ART @@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        solidArea = new Rectangle();
        solidArea.x = 9;
        solidArea.y = 20;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 30;
        solidArea.height = 28;

        // CHANGE THIS ACCORDING TO ATTACKING CHARACTER PIXEL ART
        // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        attackArea.width = 36;
        attackArea.height = 36;

        setPlayer();
        getPlayerImage();
        getPlayerAttackImage();
        getPlayerAuraSwordImage();
        setItems();

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
        maxSp = 100;
        sp = maxSp;
        increaseLife = 1;
        increaseSp = 1.0;
        playerTimer = 0;
        playerCoin = 0;
        playerWeapon = "";
        attackPower = 5;

        playerXP = 0;

    }

    public void setDefaultPositions() {
        worldX = 25 * gp.tileSize;
        worldY = 25 * gp.tileSize;
        screenX = defaultScreenX;
        screenY = defaultScreenY;
        direction = "down";
    }

    public void setItems() {
        int itemIndex2 = 0;
        System.out.println(gp.obj[0]);

        if (itemIndex2 != -1) {
            if (gp.collect[itemIndex2] != null) {
                inventory.add(gp.collect[itemIndex2]);
                itemIndex2++;
            }
        }
        if (itemIndex2 >= maxInventorySize) {
            itemIndex2 = -1;
            System.out.println("too much Items. ");
        }
        /*
         * if(currentWeapon != null){
         * inventory.add(currentWeapon);
         * }
         * if(gp.collect != null){
         * for(int i = 0; i<maxInventorySize;i++){
         * if(gp.collect[i] != null){
         * 
         * inventory.add(gp.collect[i]);
         * }
         * 
         * }
         * }
         */
        System.out.println(gp.obj[0]);

    }
    
    public void getPlayerAuraSwordImage() {
    
        auraSwordUp1 = setup("/player/playerAuraSwordUp1", gp.tileSize, gp.tileSize * 2);
        auraSwordUp2 = setup("/player/playerAuraSwordUp2", gp.tileSize, gp.tileSize * 2);
        
        auraSwordDown1 = setup("/player/playerAuraSwordDown1", gp.tileSize, gp.tileSize * 2);
        auraSwordDown2 = setup("/player/playerAuraSwordDown2", gp.tileSize, gp.tileSize * 2);
        
        auraSwordLeft1 = setup("/player/playerAuraSwordLeft1", gp.tileSize * 2, gp.tileSize);
        auraSwordLeft2 = setup("/player/playerAuraSwordLeft2", gp.tileSize * 2, gp.tileSize);
        
        auraSwordRight1 = setup("/player/playerAuraSwordRight1", gp.tileSize * 2, gp.tileSize);
        auraSwordRight2 = setup("/player/playerAuraSwordRight2", gp.tileSize * 2, gp.tileSize);
    }

    public void getPlayerImage() {
        up1 = setup("/player/up1", gp.tileSize, gp.tileSize);
        up2 = setup("/player/up2", gp.tileSize, gp.tileSize);
        up3 = setup("/player/up2", gp.tileSize, gp.tileSize);

        down1 = setup("/player/down1", gp.tileSize, gp.tileSize);
        down2 = setup("/player/down2", gp.tileSize, gp.tileSize);
        down3 = setup("/player/down3", gp.tileSize, gp.tileSize);
        down4 = setup("/player/down4", gp.tileSize, gp.tileSize);

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

        // clickCounter++; // to detect double click

        // If Player doesn't press space longer than damageTimeOut (45sec) second reset
        // holding

        noPunchCounter++;
        if (noPunchCounter >= damageTimeOut) {
            gp.player.holdingCounter = 0;
            gp.player.holdingNum = 0;
        }

        // When pressed space
        punchTimeOut++;
        if ((keyH.spacePressed || autoHit) && !gp.skills.skillUsed) {
            // System.out.println("noPunchCounter: "+ noPunchCounter+" punchTimeOut:
            // "+punchTimeOut+" holdingCounter: "+holdingCounter);
            noPunchCounter = 0;
            holdingCounter++;
            if (punchTimeOut >= damageTimeOut) {
                punchTimeOut = 0;
                attacking = true;
                gp.playSE(holdingNum + 10);
                holdingNum++;
                if (holdingNum == 4) {
                    holdingNum = 0;
                    holdingCounter = 0;
                }
            }
        }

        gp.skills.increaseTimeouts();
        
        gp.skills.resetSkills();

        /*
        if (gp.skills.swordSpinUsed && gp.skills.swordSpinTimeOut == 0) {
            if (gp.skills.swordSpinCounter > 20) {
                gp.skills.useSkill(gp.skills.swordSpinType);
                gp.skills.skillUsed = true;
            }
            speed = attackWalkingSpeed;
            gp.skills.swordSpinCounter++;
            if (gp.skills.swordSpinCounter == gp.skills.skillTimeOut) {
                gp.skills.swordSpinCounter = 0;
                gp.skills.swordSpinUsed = false;
                gp.skills.skillUsed = false;
                spriteNum = 1;
                gp.skills.swordSpinTimeOut++;
            }
        }
        */
        
        gp.skills.swordSpin();
        gp.skills.auraOfTheSword();
        
        if(gp.skills.skillUsed) {
            gp.player.speed = gp.player.attackWalkingSpeed;
        }else if(attacking) {
            attack();
            mouseH.pressed = false;
        } else {
            speed = speedDefault;
        }
        
        // To avoid player to get damage every frame. Instead waits for 1 seconds and
        // get damage.
        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter == 30) {
                invincible = false;
                invincibleCounter = 0;
            }
        }

        // Check Object Collision
        int objIndex = gp.collisionChecker.checkObject(this, true);
        if (objIndex != -1) {
            if (inventory.size() < maxInventorySize || gp.obj[objIndex].name == "Coin") {
                if (keyH.quotePressed) {
                    pickUpObject(objIndex);
                    keyH.quotePressed = false;
                } else {
                    gp.ui.showMessage("Press \" to pick up item.");
                }
            } else {
                gp.ui.showMessage("Daha fazla nesne taşıyamam.");
            }

        }

        // CHECK ENEMY COLLISION
        int enemyIndex = gp.collisionChecker.checkFightArea(this, gp.enemy);
        getDamage(enemyIndex);

        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            // Finish mouse event
            mouseH.pressed = false;
            autoHit = false;

            if (keyH.upPressed && keyH.leftPressed) {
                direction = "upleft";
            } else if (keyH.upPressed && keyH.rightPressed) {
                direction = "upright";
            } else if (keyH.downPressed && keyH.leftPressed) {
                direction = "downleft";
            } else if (keyH.downPressed && keyH.rightPressed) {
                direction = "downright";
            } else if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.downPressed) {
                direction = "down";
            } else if (keyH.leftPressed) {
                direction = "left";
            } else if (keyH.rightPressed) {
                direction = "right";
            }

            // CHECK TILE COLLISION
            collisionOn = false;
            gp.collisionChecker.checkTile(this);

            // CHECK ENEMY COLLISION
            gp.collisionChecker.checkEntity(this, gp.enemy);

            // CHECK NPC COLLISION
            int npcIndex = gp.collisionChecker.checkEntity(this, gp.npc);
            if (npcIndex != -1) {
                gp.npc[npcIndex].onPath = true;
            }
            
            //interactNpc(npcIndex);

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (!collisionOn) {
                MovePlayer.move(gp);
            }

            // Step Sound
            stepSound();

            // Animate Character
            animationCharacter();
            
        } else if ((goalX != 0 || goalY != 0) && (mouseH.pressed)) { // if there is a point to go

            // CHECK TILE COLLISION
            collisionOn = false;
            gp.collisionChecker.checkTile(this);

            // CHECK ENEMY COLLISION
            int enemyCollisionIndex = gp.collisionChecker.checkEntity(this, gp.enemy);

            // CHECK NPC COLLISION
            int npcIndex = gp.collisionChecker.checkEntity(this, gp.npc);
            if (npcIndex != -1) {
                gp.npc[npcIndex].onPath = true;
            }
            
            interactNpc(npcIndex);
            
            if(mouseH.pressedOnEnemy) {
                int enemyCol = (gp.enemy[mouseH.enemyIndex].worldX + gp.enemy[mouseH.enemyIndex].solidArea.x) / gp.tileSize; // gp.player.worldX + gp.player.solidArea.x
                int enemyRow = (gp.enemy[mouseH.enemyIndex].worldY + gp.enemy[mouseH.enemyIndex].solidArea.y) / gp.tileSize; // gp.player.worldY + gp.player.solidArea.y
                searchPath(enemyCol, enemyRow);
                
                if(enemyCollisionIndex == mouseH.enemyIndex) {
                    autoHit = true;
                }
            }else {
                searchPath(goalX, goalY);
            }

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (!collisionOn) {
                MovePlayer.move(gp);
            }

            // Step Sound
            stepSound();

            // to animate character movement
            animationCharacter();
        }
         
        if (reborn) {
            int rebornProccess = 300;
            int getHealRate = rebornProccess / (maxLife / increaseLife);
            getHeal(getHealRate);
            rebornCounter++;
            if (rebornCounter == rebornProccess) {
                reborn = false;
                rebornCounter = 0;
                bornCounter = 0;
            }
        }

    }

    public void draw(Graphics2D g2) {

        image = null;

        tempScreenX = screenX;
        tempScreenY = screenY;

        // Player Label
        g2.setFont(new Font("Courier New", Font.PLAIN, 11));
        g2.setColor(Color.green);
        g2.drawString("Lv " + level, screenX - 25, screenY - 10);

        g2.setColor(Color.yellow);
        g2.drawString(name, screenX + 20, screenY - 10);

        if (gp.skills.skillUsed && gp.skills.skillType == gp.skills.swordSpinType)      {   gp.skills.drawSwordSpin();  }
        else if (gp.skills.skillUsed && gp.skills.skillType == gp.skills.auraSwordType) {   gp.skills.drawAuraSword();  } 
        else {
            switch (direction) {
                case "up":
                case "upleft":
                case "upright":
                    if (attacking) {
                        if(gp.skills.auraSwordActive) {
                            if (spriteNum == 1)
                                image = auraSwordUp1;
                            if (spriteNum == 2)
                                image = auraSwordUp2;
                        }else {
                            if (spriteNum == 1)
                                image = attackUp1;
                            if (spriteNum == 2)
                                image = attackUp2;
                        }

                        tempScreenY = screenY - gp.tileSize; // To avoid sliding image when image sizes are different
                    } else {
                        if (spriteNum == 1)
                            image = up1;
                        if (spriteNum == 2)
                            image = up2;
                    }

                    break;
                case "down":
                case "downleft":
                case "downright":
                    if (attacking) {
                        if(gp.skills.auraSwordActive) {
                            if (spriteNum == 1)
                                image = auraSwordDown1;
                            if (spriteNum == 2)
                                image = auraSwordDown2;
                        }else {
                            if (spriteNum == 1)
                                image = attackDown1;
                            if (spriteNum == 2)
                                image = attackDown2;
                        }

                    } else {
                        if (spriteNum == 1)
                            image = down1;
                        if (spriteNum == 2)
                            image = down2;
                        if (spriteNum == 3)
                            image = down3;
                        if (spriteNum == 4)
                            image = down4;
                    }
                    break;
                case "left":
                    if (attacking) {
                        if(gp.skills.auraSwordActive) {
                            if (spriteNum == 1)
                                image = auraSwordLeft1;
                            if (spriteNum == 2)
                                image = auraSwordLeft2;
                        }else {
                            if (spriteNum == 1)
                                image = attackLeft1;
                            if (spriteNum == 2)
                                image = attackLeft2;
                        }

                        tempScreenX = screenX - gp.tileSize; // To avoid sliding image when image sizes are different
                    } else {
                        if (spriteNum == 1)
                            image = left1;
                        if (spriteNum == 2)
                            image = left2;
                    }

                    break;
                case "right":
                    if (attacking) {
                        if(gp.skills.auraSwordActive) {
                            if (spriteNum == 1)
                                image = auraSwordRight1;
                            if (spriteNum == 2)
                                image = auraSwordRight2;
                        }else {
                            if (spriteNum == 1)
                                image = attackRight1;
                            if (spriteNum == 2)
                                image = attackRight2;
                        }
                    } else {
                        if (spriteNum == 1)
                            image = right1;
                        if (spriteNum == 2)
                            image = right2;
                    }
                    break;
            }
        }

        // Set player transparent after damage
        if (invincible) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
        }

        if (reborn) {
            bornAnimation(g2);
        }

        if (dying) {
            dyingAnimation(g2);
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

    public void pickUpObject(int index) {

        if (index != -1) {

            String objName = gp.obj[index].name;
            int collectIndex = 0;
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
                    System.out.println("dolunay index : " + index);
                    gp.ui.showMessage(gp.obj[index].name + " kılıcı kazanıldı.");
                    gp.ui.itemIndex = 1;
                    gp.collect[collectIndex] = gp.obj[index];
                    collectIndex++;
                    setItems();
                    System.out.println("1.: " + gp.collect[index]);
                    // gp.obj[index] = null;
                    System.out.println("2. : " + gp.collect[index]);
                    break;
                case "TasKanat":
                    gp.playSE(3);
                    // playerWeapon = gp.obj[index].name;
                    gp.ui.showMessage(gp.obj[index].name + " kılıcı kazanıldı.");
                    System.out.println("taskanat index : " + index);
                    gp.ui.itemIndex = 1;
                    gp.collect[collectIndex] = gp.obj[index];
                    collectIndex++;
                    setItems();
                    break;
                case "EcelGetiren":
                    gp.playSE(3);
                    // playerWeapon = gp.obj[index].name;
                    gp.ui.showMessage(gp.obj[index].name + " kılıcı kazanıldı.");
                    System.out.println("ecelg index : " + index);
                    gp.ui.itemIndex = 1;
                    gp.collect[collectIndex] = gp.obj[index];
                    collectIndex++;
                    setItems();
                    break;
                case "Staff":
                    gp.playSE(3);
                    // playerWeapon = gp.obj[index].name;
                    gp.ui.showMessage(gp.obj[index].name + " asa kazanıldı.");
                    System.out.println("staff index : " + index);
                    gp.ui.itemIndex = 1;
                    gp.collect[collectIndex] = gp.obj[index];
                    collectIndex++;
                    setItems();
                    break;
            }
        }
    }

    // Interacting with npc
    public void interactNpc(int i) {

        if (i != -1) {
            gp.gameState = gp.dialogueState;
            gp.npc[0].speak();
        }
    }

    public void getDamage(int index) {
        if (index != -1 && gp.enemy[index].inFight) {
            String enemyName = gp.enemy[index].name;
            switch (enemyName) {
                case "Wolf":
                    // Wolf Barking
                    enemySoundCounter++;
                    if (enemySoundCounter == 40) {
                        int soundChoice = rand.nextInt(2) + 7;
                        gp.playSE(soundChoice);

                        enemySoundCounter = 0;
                    }
                    // Wolf Damaging
                    if (!invincible) {
                        reborn = false;
                        int damage = rand.nextInt(5) + 1;
                        if (life - damage >= 0) {
                            int soundChoice = rand.nextInt(5) + 14;
                            gp.playSE(soundChoice);
                            life -= damage;

                            int damagePosX = screenX;
                            int damagePosY = screenY;

                            gp.ui.damages.add(new Damages(damage, damagePosX, damagePosY, 60, Color.red));
                        } else {
                            dying = true;
                            setDead();
                        }
                        invincible = true;
                    }
                    break;
            }
        } else {
            if (life < maxLife) {
                getHeal(60);
            }
            if(sp < maxSp){
                getSp(60);
            }
            /*if(sp < maxSp) {
                getSp(90);
            }*/
            /*
             * if (gp.player.playerSp < 100) {
             * playerTimer++;
             * if (playerTimer == 3600) {
             * if (maxPlayerSp - playerSp < increaseSp) {
             * playerSp+= maxPlayerSp - playerSp;
             * playerTimer = 0;
             * } else {
             * playerSp += increaseSp;
             * playerTimer = 0;
             * }
             * }
             * 
             * }
             */
        }
    }

    public void setDead() {
        life = 0;
        gp.playSE(21);
        gp.gameState = gp.deadState;

        dyingCounter = 0;

        gp.skills.swordSpinUsed = false;
        gp.skills.skillUsed = false;
        gp.skills.skillSpriteCounter = gp.skills.increaseAmount * 10; // Skill içindeki son if'e girerek doğduktan sonra
                                                                      // skill kullanmaması için

        for (int i = 0; i < gp.enemy.length; i++) {
            if (gp.enemy[i] != null) {
                gp.enemy[i].inFight = false;
                gp.enemy[i].onPath = false;
            }
        }

        deadCounter = 0;
    }

    public void damageEnemy(int enemyIndex) {
        if (enemyIndex != -1) {
            if (!gp.enemy[enemyIndex].invincible) {

                // Don't get damage in the first giving damage
                if (!gp.enemy[enemyIndex].inFight) {
                    invincible = true;
                }

                int damageSize = attackPower + level * (rand.nextInt(3) + 3);

                gp.enemy[enemyIndex].damageCounter = 0;
                gp.enemy[enemyIndex].life -= damageSize;
                gp.enemy[enemyIndex].invincible = true;
                gp.enemy[enemyIndex].damageReaction();

                int damagePosX = gp.enemy[enemyIndex].worldX - worldX + screenX;
                int damagePosY = gp.enemy[enemyIndex].worldY - worldY + screenY;

                gp.ui.damages.add(new Damages(damageSize, damagePosX, damagePosY, 60, Color.green));

                if (gp.enemy[enemyIndex].life <= 0) {
                    gp.aSetter.aliveWolfNum--;
                    gp.playSE(6);
                    
                    autoHit = false;
                    
                    playerXP += rand.nextInt(10) + 100 / level;

                    // If level up
                    if (playerXP / 920 + 1 > level) {
                        life = maxLife;
                    }

                    level = (playerXP / 920) + 1;

                    int coinNumber = rand.nextInt(3) + 3;
                    for (int i = coinNumber; i > 0; i--) {

                        int xPosition = gp.enemy[enemyIndex].worldX + rand.nextInt(3) * gp.tileSize / 5;
                        int yPosition = gp.enemy[enemyIndex].worldY + rand.nextInt(3) * gp.tileSize / 5;

                        gp.aSetter.createCoin(xPosition, yPosition);
                    }

                    gp.enemy[enemyIndex].dying = true;
                    gp.enemy[enemyIndex].alive = false;
                    gp.aSetter.createDeadWolf(worldX, worldY + (gp.tileSize / 2));
                }
            }
        }
    }

    public void getHeal(int healCounter) {
        playerTimer++;
        if (playerTimer >= healCounter) {
            if (maxLife < life + increaseLife) {
                life += maxLife - life;
            } else {
                life += increaseLife;
            }
            playerTimer = 0;
        }
    }
    public void getSp(int spCounter) {
        playerTimer++;
        if (playerTimer >= spCounter) {
            if (maxSp < sp + increaseSp) {
                sp += maxSp - sp;
            } else {
                sp += increaseSp;
            }
            playerTimer = 0;
        }
    }
    
    public void stepSound() {
        stepCounter++;
        if (stepCounter == 10) {
            if (stepType == 0) {
                gp.playSE(4);
                stepType = 1;
            } else if (stepType == 1) {
                gp.playSE(5);
                stepType = 0;
            }
            stepCounter = 0;
        }
    }
    
    public void animationCharacter() {
        if (!gp.skills.skillUsed && !attacking) {
            spriteCounter++;
            if (spriteCounter > 5) {
                if (spriteNum == 2) {
                    spriteNum = 1;
                } else {
                    spriteNum++;
                }
                spriteCounter = 0;
            }
        }
    }
}
