package entity;

import main.KeyHandler;
import main.MouseHandler;
import main.MovePlayer;
import object.OBJ_Dolunay;
import object.OBJ_Sword;

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
/**
 * <p>
 * This Class adjusts player behavior
 * </p>
 */
public class Player extends Entity {
    /**
     * <p>
     * This field is instance of GamePanel class
     * </p>
     */
    GamePanel gp;
    /**
     * <p>
     * This field is instance of KeyHandler class
     * </p>
     */
    KeyHandler keyH;
    /**
     * <p>
     * This field is instance of MouseHandler class
     * </p>
     */
    MouseHandler mouseH;
    /**
     * <p>
     * This field stores the position of the player on the screen on the x-axis
     * </p>
     */
    public int screenX; // Where we draw player on screen X
    /**
     * <p>
     * This field stores the position of the player on the screen on the y-axis
     * </p>
     */
    public int screenY; // Where we draw player on screen Y
    /**
     * <p>
     * This field stores the default position of the player on the screen on the x-axis
     * </p>
     */
    public int defaultScreenX; // Where we draw player on screen X
    /**
     * <p>
     * This field stores the default position of the player on the screen on the y-axis
     * </p>
     */
    public int defaultScreenY; // Where we draw player on screen Y

    // Player Specification
    /**
     * <p>
     * This field stores the players coin
     * </p>
     */
    private int playerCoin;
    /**
     * <p>
     * This field stores the players weapon name
     * </p>
     */
    public String playerWeapon;
    /**
     * <p>
     * This field stores the player timer
     * </p>
     */
    public int playerTimer;
    /**
     * <p>
     * This field stores the increase life coefficient
     * </p>
     */
    public int increaseLife;
    /**
     * <p>
     * This field stores the special point coefficient
     * </p>
     */
    public double increaseSp;
    /**
     * <p>
     * This field stores default speed value
     * </p>
     */
    private int speedDefault;
    /**
     * <p>
     * This field stores the players experience point
     * </p>
     */
    private int playerXP;
    /**
     * <p>
     * This field stores the players attack power
     * </p>
     */
    private int attackPower;

    // Mouse Click Movement
    /**
     * <p>
     * This field stores the target position from mouse click at x-axis
     * </p>
     */
    public int goalX;
    /**
     * <p>
     * This field stores the target position from mouse click at y-axis
     * </p>
     */
    public int goalY;
    /**
     * <p>
     * This field is instance of Random class
     * </p>
     */
    public Random rand = new Random();
    /**
     * <p>
     * This field stores the counter of attack
     * </p>
     */
    public int attackCounter = 0;
    /**
     * <p>
     * This field stores position of holding number
     * </p>
     */
    public int holdingNum = 0;
    /**
     * <p>
     * This field stores position of holding counter
     * </p>
     */
    public int holdingCounter = 0;
    /**
     * <p>
     * This field stores position of no punch counter
     * </p>
     */
    public int noPunchCounter = 0;
    /**
     * <p>
     * This field stores click counter
     * </p>
     */
    public int clickCounter = 0;
    /**
     * <p>
     * This field stores whether auto hit or not
     * </p>
     */
    public boolean autoHit = false;
    /**
     * <p>
     * This field stores whether double clicked or not
     * </p>
     */
    public boolean doubleClicked = false;
    /**
     * <p>
     * This field stores attack walking speed
     * </p>
     */
    int attackWalkingSpeed = 1;
    /**
     * <p>
     * This field stores dead wolf counter
     * </p>
     */
    public int deadWolfCounter = 0;
    /**
     * <p>
     * This field stores whether reborn or not
     * </p>
     */
    public boolean reborn = false;
    /**
     * <p>
     * This field stores reborn counter
     * </p>
     */
    public int rebornCounter;
    /**
     * <p>
     * This field stores dead counter
     * </p>
     */
    public int deadCounter = 0;

    // INVENTORY
    /**
     * <p>
     * This field stores current weapon of player
     * </p>
     */
    public Entity currentWeapon;
    /**
     * <p>
     * This field stores enchant weapon
     * </p>
     */
    public Entity enchantWeapon;
    /**
     * <p>
     * This field stores whether enchant is accepted or not
     * </p>
     */
    public boolean enchantAccepted = false;
    /**
     * <p>
     * This field stores whether item enchant is selected or not
     * </p>
     */
    public boolean itemEnchSellected = false;
    /**
     * <p>
     * This field stores how many red potions does character have
     * </p>
     */
    public int redPotionNumber = 0;
    /**
     * <p>
     * This field stores how many blue potions does character have
     * </p>
     */
    public int bluePotionNumber = 0;
    /**
     * <p>
     * This field stores a buffered image
     * </p>
     */
    public BufferedImage image;

    // To avoid sliding image when sizes are different
    /**
     * <p>
     * This field stores position of x-axis to avoid sliding image when sizes are different
     * </p>
     */
    public int tempScreenX;
    /**
     * <p>
     * This field stores position of y-axis to avoid sliding image when sizes are different
     * </p>
     */
    public int tempScreenY;
    /**
     * <p>
     * This field stores interact NPC index
     * </p>
     */
    public int interactNPCIndex = -1;
    /**
     * <p>
     * This field stores close NPC index
     * </p>
     */
    public int closeNPCIndex = -1;

    // Tasks Name
    /**
     * <p>
     * This field stores tasks name 
     * </p>
     */
    public ArrayList<String> taskNameList = new ArrayList<String>();
    /**
     * <p>
     * This is constructor
     * </p>
     * @param gp instance of GamePanel class 
     * @param keyH instance of KeyHandler class
     * @param mouseH instance of MouseHandler class
     */
    public Player(GamePanel gp, KeyHandler keyH, MouseHandler mouseH) {
        super(gp);
        this.gp = gp;
        this.keyH = keyH;
        this.mouseH = mouseH;

        type = playerType;
        name = "";

        defaultScreenX = screenX = gp.screenWidth / 2 - gp.tileSize / 2;
        defaultScreenY = screenY = gp.screenHeight / 2 - gp.tileSize / 2;

        // CHANGE THIS ACCORDING TO CHARACTER PIXEL ART @@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        solidArea = new Rectangle();

        solidArea.x = 25;
        solidArea.y = 31;
        solidArea.width = 31;
        solidArea.height = 49;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidAreaDefaultWidth = solidArea.width;
        solidAreaDefaultHeight = solidArea.height;

        attackArea.width = 36;
        attackArea.height = 36;

        setPlayer();

        getPlayerImage();
        getPlayerAttackImage();
        getPlayerAuraSwordImage();
        getPlayerAttackAuraSwordImage();
        getPlayerDyingImage();
        setItems();
        setTaskList();
    }
    /**
     * <p>
     * This method gets player images
     * </p>
     */
    public void getPlayerImage() {
        for (int i = 0; i < 4; i++) {
            up[i] = setup("/player/up" + (i + 1), 80, 80);
        }
        for (int i = 0; i < 4; i++) {
            down[i] = setup("/player/down" + (i + 1), 80, 80);
        }
        for (int i = 0; i < 4; i++) {
            left[i] = setup("/player/left" + (i + 1), 80, 80);
        }
        for (int i = 0; i < 4; i++) {
            right[i] = setup("/player/right" + (i + 1), 80, 80);
        }
    }
    /**
     * <p>
     * This method gets player attack images
     * </p>
     */
    public void getPlayerAttackImage() {
        for (int i = 0; i < 16; i++) {
            attackUp[i] = setup("/player/attackup" + (i + 1), 80, 92);
        }
        for (int i = 0; i < 16; i++) {
            attackDown[i] = setup("/player/attackdown" + (i + 1), 80, 92);
        }
        for (int i = 0; i < 16; i++) {
            attackLeft[i] = setup("/player/attackleft" + (i + 1), 81, 80);
        }
        for (int i = 0; i < 16; i++) {
            attackRight[i] = setup("/player/attackright" + (i + 1), 81, 80);
        }
    }
    /**
     * <p>
     * This method gets player images on when using Aura Sword
     * </p>
     */
    public void getPlayerAuraSwordImage() {
        for (int i = 0; i < 4; i++) {
            auraUp[i] = setup("/player/auraUp" + (i + 1), 80, 80);
        }
        for (int i = 0; i < 4; i++) {
            auraDown[i] = setup("/player/auraDown" + (i + 1), 80, 80);
        }
        for (int i = 0; i < 4; i++) {
            auraLeft[i] = setup("/player/auraLeft" + (i + 1), 80, 80);
        }
        for (int i = 0; i < 4; i++) {
            auraRight[i] = setup("/player/auraRight" + (i + 1), 80, 80);
        }
    }
    /**
     * <p>
     * This method gets player attack images on when using Aura Sword
     * </p>
     */
    public void getPlayerAttackAuraSwordImage() {
        for (int i = 0; i < 16; i++) {
            auraSwordUp[i] = setup("/player/playerAuraSwordUp" + (i + 1), 80, 92);
        }
        for (int i = 0; i < 16; i++) {
            auraSwordDown[i] = setup("/player/playerAuraSwordDown" + (i + 1), 80, 92);
        }
        for (int i = 0; i < 16; i++) {
            auraSwordLeft[i] = setup("/player/playerAuraSwordLeft" + (i + 1), 81, 80);
        }
        for (int i = 0; i < 16; i++) {
            auraSwordRight[i] = setup("/player/playerAuraSwordRight" + (i + 1), 81, 80);
        }
    }
    /**
     * <p>
     * This method gets player dying images 
     * </p>
     */
    public void getPlayerDyingImage() {
        for (int i = 0; i < 5; i++) {
            dyingUp[i] = setup("/player/deathUp" + (i + 1), 80, 80);
        }
        for (int i = 0; i < 5; i++) {
            dyingDown[i] = setup("/player/deathDown" + (i + 1), 80, 80);
        }
        for (int i = 0; i < 5; i++) {
            dyingLeft[i] = setup("/player/deathLeft" + (i + 1), 80, 80);
        }
        for (int i = 0; i < 5; i++) {
            dyingRight[i] = setup("/player/deathRight" + (i + 1), 80, 80);
        }
    }
    /**
     * <p>
     * This method sets player states 
     * </p>
     */
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
        playerCoin = 200;
        playerWeapon = "Sword";
        attackPower = 5;
        taskLevel = 0;
        playerXP = 0;

        currentWeapon = new OBJ_Sword(gp);
        inventory.add(currentWeapon);
        attackPower += currentWeapon.weaponAttackSize;
    }
    /**
     * <p>
     * This method sets default position for player 
     * </p>
     */
    public void setDefaultPositions() {
        worldX = 25 * gp.tileSize;
        worldY = 25 * gp.tileSize;
        screenX = defaultScreenX;
        screenY = defaultScreenY;
        direction = "down";
    }
    /**
     * <p>
     * This method sets items
     * </p>
     */
    public void setItems() {
        int itemIndex2 = 0;

        if (itemIndex2 != -1) {
            if (gp.collect[itemIndex2] != null) {
                inventory.add(gp.collect[itemIndex2]);
                itemIndex2++;
            }
        }
        if (itemIndex2 >= maxInventorySize) {
            itemIndex2 = -1;
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
    }
    /**
     * <p>
     * This method sets task list
     * </p>
     */
    public void setTaskList() {
        taskNameList.add("Teşkilata katıl!");
        taskNameList.add("Teşkilatın Direği!");
        taskNameList.add("Fatih Bey'e Tahribat!");
        taskNameList.add("Köyün Bekası");
        taskNameList.add("Köyün Sefası");
    }
    /**
     * <p>
     * This method updates player 
     * </p>
     */
    public void update() {

        // CHECK NPC COLLISION for msg
        boolean noNPC = false;
        for (int i = 0; i < gp.npc.length; i++) {
            if (gp.npc[i] != null
                    && (gp.npc[i].worldX < gp.player.worldX + gp.tileSize * 2
                            && gp.npc[i].worldX > gp.player.worldX - gp.tileSize * 2)
                    && (gp.npc[i].worldY < gp.player.worldY + gp.tileSize * 2
                            && gp.npc[i].worldY > gp.player.worldY - gp.tileSize * 2)) {
                closeNPCIndex = i;
                noNPC = true;
            }
        }

        if (!noNPC) {
            closeNPCIndex = -1;
        }

        noPunchCounter++;
        if (noPunchCounter >= damageTimeOut) {
            gp.player.holdingCounter = 0;
            gp.player.holdingNum = 0;
        }

        // When pressed space
        attackCounter++;
        if ((keyH.spacePressed || autoHit) && !gp.skills.skillUsed) {
            // System.out.println("noPunchCounter: "+ noPunchCounter+" punchTimeOut:
            // "+punchTimeOut+" holdingCounter: "+holdingCounter);
            noPunchCounter = 0;
            holdingCounter++;
            if (attackCounter >= damageTimeOut) {

                if (gp.endGame) {
                    gp.endGameCounter = 0;
                    gp.endGame = false;
                    gp.ui.addMessage("Game shutdown has been cancelled.");
                }

                attackCounter = 0;
                attacking = true;

            }
        }

        gp.skills.increaseTimeouts();

        gp.skills.resetSkills();

        /*
         * if (gp.skills.swordSpinUsed && gp.skills.swordSpinTimeOut == 0) {
         * if (gp.skills.swordSpinCounter > 20) {
         * gp.skills.useSkill(gp.skills.swordSpinType);
         * gp.skills.skillUsed = true;
         * }
         * speed = attackWalkingSpeed;
         * gp.skills.swordSpinCounter++;
         * if (gp.skills.swordSpinCounter == gp.skills.skillTimeOut) {
         * gp.skills.swordSpinCounter = 0;
         * gp.skills.swordSpinUsed = false;
         * gp.skills.skillUsed = false;
         * spriteNum = 1;
         * gp.skills.swordSpinTimeOut++;
         * }
         * }
         */

        gp.skills.swordSpin();
        gp.skills.auraOfTheSword();

        if (gp.skills.skillUsed) {
            gp.player.speed = gp.player.attackWalkingSpeed;
        } else if (attacking) {
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
            if (keyH.quotePressed) {
                if (inventory.size() < maxInventorySize || gp.obj[objIndex].name == "Coin") {
                    pickUpObject(objIndex);
                } else {
                    gp.ui.addMessage("Daha fazla nesne taşıyamam.");
                }
                keyH.quotePressed = false;

            } else {
                // gp.ui.showMessage("Press \" to pick up item.");
            }
        }

        // CHECK NPC COLLISION
        if (interactNPCIndex != -1) {
            interactNpc(interactNPCIndex);
            keyH.enterPressed = false;
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
            gp.collisionChecker.checkEntity(this, gp.npc);

            // interactNpc(npcIndex);

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
            gp.collisionChecker.checkEntity(this, gp.npc);

            if (mouseH.pressedOnEnemy) {
                int enemyCol = (gp.enemy[mouseH.enemyIndex].worldX + gp.enemy[mouseH.enemyIndex].solidArea.x)
                        / gp.tileSize; // gp.player.worldX + gp.player.solidArea.x
                int enemyRow = (gp.enemy[mouseH.enemyIndex].worldY + gp.enemy[mouseH.enemyIndex].solidArea.y)
                        / gp.tileSize; // gp.player.worldY + gp.player.solidArea.y
                searchPath(enemyCol, enemyRow);

                if (enemyCollisionIndex == mouseH.enemyIndex) {
                    autoHit = true;
                }
            } else {
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
    /**
     * <p>
     * This method draws player
     * </p>
     */
    public void draw(Graphics2D g2) {

        image = null;

        tempScreenX = screenX;
        tempScreenY = screenY;

        // Player Label
        g2.setFont(new Font("Courier New", Font.PLAIN, 12));

        g2.setColor(Color.green);
        g2.drawString("Lv " + level, screenX, screenY - 10);

        g2.setColor(Color.yellow);
        g2.drawString(name, screenX + 40, screenY - 10);

        if (keyH.spacePressed) {
            attacking = true;
        }

        if (gp.skills.skillUsed && gp.skills.skillType == gp.skills.swordSpinType) {
            gp.skills.drawSwordSpin();
        } else if (gp.skills.skillUsed && gp.skills.skillType == gp.skills.auraSwordType) {
            gp.skills.drawAuraSword();
        } else {
            switch (direction) {
                case "up":
                case "upleft":
                case "upright":
                    if (attacking) {
                        if (gp.skills.auraSwordActive) {
                            image = auraSwordUp[holdingNum * 4 + spriteNum - 1];
                        } else {
                            image = attackUp[holdingNum * 4 + spriteNum - 1];
                        }
                        tempScreenY = screenY - 12; // To avoid sliding image when image sizes are different
                    } else {
                        if (gp.skills.auraSwordActive) {
                            image = auraUp[spriteNum - 1];
                        } else {
                            image = up[spriteNum - 1];
                        }
                    }

                    break;
                case "down":
                case "downleft":
                case "downright":
                    if (attacking) {
                        if (gp.skills.auraSwordActive) {
                            image = auraSwordDown[holdingNum * 4 + spriteNum - 1];
                        } else {
                            image = attackDown[holdingNum * 4 + spriteNum - 1];
                        }

                    } else {
                        if (gp.skills.auraSwordActive) {
                            image = auraDown[spriteNum - 1];
                        } else {
                            image = down[spriteNum - 1];
                        }
                    }
                    break;
                case "left":
                    if (attacking) {
                        if (gp.skills.auraSwordActive) {
                            image = auraSwordLeft[holdingNum * 4 + spriteNum - 1];
                        } else {
                            image = attackLeft[holdingNum * 4 + spriteNum - 1];
                        }
                        tempScreenX = screenX - 20; // To avoid sliding image when image sizes are different
                    } else {
                        if (gp.skills.auraSwordActive) {
                            image = auraLeft[spriteNum - 1];
                        } else {
                            image = left[spriteNum - 1];
                        }
                    }

                    break;
                case "right":
                    if (attacking) {
                        if (gp.skills.auraSwordActive) {
                            image = auraSwordRight[holdingNum * 4 + spriteNum - 1];
                        } else {
                            image = attackRight[holdingNum * 4 + spriteNum - 1];
                        }
                        tempScreenX = screenX + 20; // To avoid sliding image when image sizes are different
                    } else {
                        if (gp.skills.auraSwordActive) {
                            image = auraRight[spriteNum - 1];
                        } else {
                            image = right[spriteNum - 1];
                        }
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
            dyingAnimation();
        }

        g2.drawImage(image, tempScreenX, tempScreenY, null);

        // Reset transparency
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
    /**
     * <p>
     * This method sets player attack behavior
     * </p>
     */
    public void attack() {

        // spriteCounter yürürken 2 olduğu için 3 te iken ses çal
        if (spriteCounter == 3) {
            gp.playSE(holdingNum + 10);
        }

        spriteCounter++;
        if (spriteCounter <= 4) {
            spriteNum = 1;
        } else if (spriteCounter > 4 && spriteCounter <= 8) {
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

        } else if (spriteCounter > 8 && spriteCounter <= 12) {
            spriteNum = 3;
        } else if (spriteCounter > 12 && spriteCounter <= 16) {
            spriteNum = 4;
        } else {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
            speed = speedDefault;

            if (holdingNum == 3) {
                holdingNum = 0;
                holdingCounter = 0;
            } else {
                holdingNum++;
            }
        }
    }
    /**
     * <p>
     * This method adjusts player how to pick up object
     * </p>
     * @param index refers object index
     */
    public void pickUpObject(int index) {

        if (index != -1) {

            String objName = gp.obj[index].name;
            int collectIndex = 0;
            switch (objName) {
                case "Coin":
                    gp.playSE(2);
                    playerCoin += gp.obj[index].coinValue;
                    gp.ui.addMessage(gp.obj[index].coinValue + " yang collected.");
                    gp.obj[index] = null;
                    break;
                case "Dolunay":
                    gp.playSE(3);
                    playerWeapon = gp.obj[index].name;
                    gp.ui.addMessage(gp.obj[index].name + " sword obtained.");
                    gp.ui.itemIndex = 1;
                    gp.collect[collectIndex] = gp.obj[index];
                    collectIndex++;
                    setItems();
                    gp.obj[index] = null;
                    break;
                case "Su Perisi":
                    gp.playSE(3);
                    // playerWeapon = gp.obj[index].name;
                    gp.ui.addMessage(gp.obj[index].name + " obtained.");
                    gp.ui.itemIndex = 1;
                    gp.collect[collectIndex] = gp.obj[index];
                    collectIndex++;
                    setItems();
                    gp.obj[index] = null;
                    break;
                case "Kırmızı Demir Pala":
                    gp.playSE(3);
                    // playerWeapon = gp.obj[index].name;
                    gp.ui.addMessage(gp.obj[index].name + " obtained.");
                    gp.ui.itemIndex = 1;
                    gp.collect[collectIndex] = gp.obj[index];
                    collectIndex++;
                    setItems();
                    gp.obj[index] = null;
                    break;
                case "Geniş Kılıç":
                    gp.playSE(3);
                    // playerWeapon = gp.obj[index].name;
                    gp.ui.addMessage(gp.obj[index].name + " obtained.");
                    gp.ui.itemIndex = 1;
                    gp.collect[collectIndex] = gp.obj[index];
                    collectIndex++;
                    setItems();
                    gp.obj[index] = null;
                    break;
            }
        }
    }

    // Interacting with npc
    /**
     * <p>
     * This method adjusts player how to interact NPC
     * </p>
     * @param i refers the state for dialogue, trade or enchant
     */
    public void interactNpc(int i) {

        onPath = false;

        if (i != -1) {
            if (i == 0) {
                gp.gameState = gp.dialogueState;
                gp.npc[0].speak();
            } else if (i == 1) {
                gp.gameState = gp.tradeState;
            } else if (i == 2) {
                gp.gameState = gp.enchantState;
            }

        }
    }
    /**
     * <p>
     * This method sets player how to get damage
     * </p>
     * @param index referes enemy index
     */
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
                case "Satellite":
                
                    if(!gp.enemy[0].dying) {
                        // Wolf Barking
                        enemySoundCounter++;
                        if (enemySoundCounter == 40) {
                            int sougdChoice = rand.nextInt(2) + 7;
                            //gp.playSE(soundChoice);
    
                            enemySoundCounter = 0;
                        }
    
                        if (gp.collisionChecker.checkEntity(this, gp.enemy) == 0) {
                            reborn = false;
                            int damage = rand.nextInt(5) + 1;
                            if (life - damage >= 0 && !gp.enemy[0].dying) {
    
                                int soundChoice = rand.nextInt(5) + 14;
                                //gp.playSE(soundChoice);
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
                    }

            }
        } else {
            if (life < maxLife) {
                getHeal(60);
            }
            if (sp < maxSp) {
                getSp(60);
            }
            /*
             * if(sp < maxSp) {
             * getSp(90);
             * }
             */
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
    /**
     * <p>
     * This method sets player dead
     * </p>
     */
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
    /**
     * <p>
     * This method sets enemy damage
     * </p>
     * @param enemyIndex refers enemy index on the array
     */
    public void damageEnemy(int enemyIndex) {
        if (enemyIndex != -1) {

            if (!gp.enemy[enemyIndex].invincible && !gp.enemy[enemyIndex].dying) {

                // Don't get damage in the first giving damage
                if (!gp.enemy[enemyIndex].inFight) {

                    invincible = true;
                }
                if (holdingNum == 3 && !gp.skills.skillUsed) {
                    if (gp.enemy[enemyIndex].name != "Satellite") {
                        knockBack(gp.enemy[enemyIndex]);
                    }
                }

                int damageSize = attackPower + level * (rand.nextInt(3) + 3);

                gp.enemy[enemyIndex].damageCounter = 0;
                if(gp.enemy[enemyIndex].name == "Satellite") {
                    if(gp.player.taskLevel ==3 && gp.keyH.missionPrize[3]==0) {
                        gp.enemy[enemyIndex].life -= damageSize;
                    }
                }else {
                    gp.enemy[enemyIndex].life -= damageSize;
                }
                
                gp.enemy[enemyIndex].invincible = true;
                gp.enemy[enemyIndex].damageReaction();

                int damagePosX = gp.enemy[enemyIndex].worldX - worldX + screenX;
                int damagePosY = gp.enemy[enemyIndex].worldY - worldY + screenY;

                gp.ui.damages.add(new Damages(damageSize, damagePosX, damagePosY, 60, Color.green));

                if (gp.enemy[enemyIndex].life <= 0) {

                    if (gp.enemy[enemyIndex].name != "Satellite") {
                        gp.aSetter.aliveWolfNum--;
                        gp.playSE(6);
                        if (taskLevel == 2) {
                            deadWolfCounter++;
                        }

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

                        // Dolunay 28% Luck
                        int dolunayLuck = rand.nextInt(100);
                        if (dolunayLuck < 28) {
                            int xPosition = gp.enemy[enemyIndex].worldX + rand.nextInt(3) * gp.tileSize / 5;
                            int yPosition = gp.enemy[enemyIndex].worldY + rand.nextInt(3) * gp.tileSize / 5;
                            gp.aSetter.createDolunay(xPosition, yPosition);
                        }

                        gp.aSetter.createDeadWolf(worldX, worldY + (gp.tileSize / 2));
                        
                        gp.enemy[enemyIndex].dying = true;
                        gp.enemy[enemyIndex].alive = false;
                        
                    } else if (gp.enemy[enemyIndex].name == "Satellite" && !gp.enemy[enemyIndex].dying) {
                        if (taskLevel == 3 && gp.keyH.missionPrize[3] == 0) {
                            taskLevel++;
                            gp.ui.addMessage("Mission Accomplished!");
                            gp.ui.addMessage("Turn back Abulbul!");
                        }
                    }



                    /*
                     * switch(gp.enemy[enemyIndex].name){
                     * case "Wolf":
                     * 
                     * gp.aSetter.aliveWolfNum--;
                     * gp.playSE(6);
                     * if (taskLevel == 2) {
                     * deadWolfCounter++;
                     * }
                     * 
                     * autoHit = false;
                     * 
                     * playerXP += rand.nextInt(10) + 100 / level;
                     * 
                     * // If level up
                     * if (playerXP / 920 + 1 > level) {
                     * life = maxLife;
                     * }
                     * 
                     * level = (playerXP / 920) + 1;
                     * 
                     * int coinNumber = rand.nextInt(3) + 3;
                     * for (int i = coinNumber; i > 0; i--) {
                     * 
                     * int xPosition = gp.enemy[enemyIndex].worldX + rand.nextInt(3) * gp.tileSize /
                     * 5;
                     * int yPosition = gp.enemy[enemyIndex].worldY + rand.nextInt(3) * gp.tileSize /
                     * 5;
                     * 
                     * gp.aSetter.createCoin(xPosition, yPosition);
                     * }
                     * 
                     * // Dolunay 28% Luck
                     * int dolunayLuck = rand.nextInt(100);
                     * if (dolunayLuck < 28) {
                     * int xPosition = gp.enemy[enemyIndex].worldX + rand.nextInt(3) * gp.tileSize /
                     * 5;
                     * int yPosition = gp.enemy[enemyIndex].worldY + rand.nextInt(3) * gp.tileSize /
                     * 5;
                     * gp.aSetter.createDolunay(xPosition, yPosition);
                     * }
                     * 
                     * gp.enemy[enemyIndex].dying = true;
                     * gp.enemy[enemyIndex].alive = false;
                     * gp.aSetter.createDeadWolf(worldX, worldY + (gp.tileSize / 2));
                     * break;
                     * 
                     * case "Satellite":
                     * 
                     * autoHit = false;
                     * 
                     * playerXP += rand.nextInt(10) + 100 / level;
                     * 
                     * // If level up
                     * if (playerXP / 920 + 1 > level) {
                     * life = maxLife;
                     * }
                     * 
                     * level = (playerXP / 920) + 1;
                     * 
                     * coinNumber = rand.nextInt(3) + 3;
                     * for (int i = coinNumber; i > 0; i--) {
                     * 
                     * int xPosition = gp.enemy[enemyIndex].worldX + rand.nextInt(3) * gp.tileSize /
                     * 5;
                     * int yPosition = gp.enemy[enemyIndex].worldY + rand.nextInt(3) * gp.tileSize /
                     * 5;
                     * 
                     * gp.aSetter.createCoin(xPosition, yPosition);
                     * }
                     * 
                     * // Dolunay 28% Luck
                     * dolunayLuck = rand.nextInt(100);
                     * if (dolunayLuck < 28) {
                     * int xPosition = gp.enemy[enemyIndex].worldX + rand.nextInt(3) * gp.tileSize /
                     * 5;
                     * int yPosition = gp.enemy[enemyIndex].worldY + rand.nextInt(3) * gp.tileSize /
                     * 5;
                     * gp.aSetter.createDolunay(xPosition, yPosition);
                     * }
                     * 
                     * gp.enemy[enemyIndex].dying = true;
                     * gp.enemy[enemyIndex].alive = false;
                     * 
                     * }
                     */
                }
            }
        }
    }
    /**
     * <p>
     * This method sets knock back 
     * </p>
     * @param entity is referes player
     */
    public void knockBack(Entity entity) {
        entity.direction = direction;
        entity.speed += 5;
        entity.knockBack = true;
    }
    /**
     * <p>
     * This method sets get heal
     * </p>
     * @param healCounter count get health time
     */
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
    /**
     * <p>
     * This method sets use red potion
     * </p>
     */
    public void useRedPotion() {
        if (redPotionNumber > 0) {
            if (life != maxLife) {
                increaseLife = 5;
                if (maxLife < life + increaseLife) {
                    life += maxLife - life;
                } else {
                    life += increaseLife;
                }
                redPotionNumber--;
                gp.playSE(24);

                if (redPotionNumber == 0) {
                    int redPotionIndex = 0, i = 0;
                    while (i < inventory.size()) {
                        if (inventory.get(i) != null && inventory.get(i).name == "Red Potion") {
                            redPotionIndex = i;
                            break;
                        }
                        i++;
                    }

                    inventory.remove(redPotionIndex);
                }
            } else {
                gp.ui.addMessage("Full Health!");
            }
        } else {
            gp.ui.addMessage("Not Enough Potion to Drink!");
        }
    }
    /**
     * <p>
     * This method sets use blue potion
     * </p>
     */
    public void useBluePotion() {
        if (bluePotionNumber > 0) {
            if (sp != maxSp) {
                increaseSp = 5;
                if (maxSp < sp + increaseSp) {
                    sp += maxSp - sp;
                } else {
                    sp += increaseSp;
                }
                bluePotionNumber--;
                gp.playSE(24);

                if (bluePotionNumber == 0) {
                    int bluePotionIndex = 0, i = 0;
                    while (i < inventory.size()) {
                        if (inventory.get(i) != null && inventory.get(i).name == "Blue Potion") {
                            bluePotionIndex = i;
                            break;
                        }
                        i++;
                    }

                    inventory.remove(bluePotionIndex);
                }
            } else {
                gp.ui.addMessage("Full Stamina!");
            }
        } else {
            gp.ui.addMessage("Not Enough Potion to Drink!");
        }
    }
    /**
     * <p>
     * This method sets get special point
     * </p>
     * @param spCounter count get sp time 
     */
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
    /**
     * <p>
     * This method sets sound of step
     * </p>
     */
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
    /**
     * <p>
     * This method sets dying animation
     * </p>
     */
    public void dyingAnimation() {
        dyingCounter++;

        switch (direction) {
            case "up":
            case "upleft":
            case "upright":
                if (dyingCounter < 60)
                    image = dyingUp[dyingCounter / 12];
                else
                    image = dyingUp[4];
                break;
            case "down":
            case "downleft":
            case "downright":
                if (dyingCounter < 60)
                    image = dyingDown[dyingCounter / 12];
                else
                    image = dyingDown[4];
                break;
            case "left":
                if (dyingCounter < 60)
                    image = dyingLeft[dyingCounter / 12];
                else
                    image = dyingLeft[4];
                break;
            case "right":
                if (dyingCounter < 60)
                    image = dyingRight[dyingCounter / 12];
                else
                    image = dyingRight[4];
                break;
        }

    }
    /**
     * <p>
     * This method sets animation character
     * </p>
     */
    public void animationCharacter() {
        if (!gp.skills.skillUsed && !attacking) {
            spriteCounter++;
            if (spriteCounter > 2) {
                if (spriteNum == 4) {
                    spriteNum = 1;
                } else {
                    spriteNum++;
                }
                spriteCounter = 0;
            }
        }
    }
    /**
     * <p>
     * This method returns player coin
     * </p>
     * @return players coin
     */
    public int getPlayerCoin() {
        return playerCoin;
    }
    /**
     * <p>
     * This method returns player default speed
     * </p>
     * @return default speed
     */
    public int getSpeedDefault() {
        return speedDefault;
    }
    /**
     * <p>
     * This method returns player experience point
     * </p>
     * @return player XP
     */
    public int getPlayerXP() {
        return playerXP;
    }
    /**
     * <p>
     * This method returns player attack power
     * </p>
     * @return player attack power
     */
    public int getAttackPower() {
        return attackPower;
    }
    
    /**
     * <p>
     * This method sets player coin
     * </p>
     * @param playerCoin referes player total coin
     */
    public void setPlayerCoin(int playerCoin) {
        this.playerCoin = playerCoin;
    }
    
    /**
     * <p>
     * This method sets default speed
     * </p>
     * @param speedDefault is refers player's run speed default value
     */
    public void setSpeedDefault(int speedDefault) {
        this.speedDefault = speedDefault;
    }
    
    /**
     * <p>
     * This method sets player experience point
     * </p>
     * @param playerXP set the player level xp
     */
    public void setPlayerXP(int playerXP) {
        this.playerXP = playerXP;
    }
    
    /**
     * <p>
     * This method sets attack power
     * </p>
     * @param attackPower is refers player's attack damage
     */
    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }
}
