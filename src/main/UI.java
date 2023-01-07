package main;

import java.awt.Cursor;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import data.SaveLoad;
import npc.Npc_Merchant;
import entity.Entity;

/**
 * <p>
 * This Class draws all UI elements in the screen.
 * </p>
 */
public class UI {

    GamePanel gp;

    Graphics2D g2;

    /**
     * <p>
     * e1 variable for using Entity's method.
     * </p>
     */
    Entity e1;

    /**
     * <p>
     * This variable for text's font in UI
     * </p>
     */
    Font arial_30;

    /**
     * <p>
     * This variable for text's font for damage.
     * </p>
     */
    Font damageFont;

    /**
     * <p>
     * This BufferedImage for Dolunay Sword.
     * </p>
     */
    BufferedImage dolunayImage;

    /**
     * <p>
     * This BufferedImage for empty health bar.
     * </p>
     */
    BufferedImage emptyBarImage;

    /**
     * <p>
     * This BufferedImage for cursor image.
     * </p>
     */
    BufferedImage cursorImage;

    /**
     * <p>
     * This BufferedImage array for health bar.
     * </p>
     */
    BufferedImage[] hpBarImages = new BufferedImage[8];

    /**
     * <p>
     * This BufferedImage array for sp bar.
     * </p>
     */
    BufferedImage[] spBarImages = new BufferedImage[8];

    /**
     * <p>
     * This BufferedImage for sword.
     * </p>
     */
    BufferedImage auraOfSwordImage;

    /**
     * <p>
     * This BufferedImage for skills.
     * </p>
     */
    BufferedImage swordSpinImage;

    /**
     * <p>
     * This BufferedImage array for skill.
     * </p>
     */
    BufferedImage[] swordSpinImageUsed = new BufferedImage[20];

    /**
     * <p>
     * This BufferedImage array for skill.
     * </p>
     */
    BufferedImage[] auraSwordImageUsed = new BufferedImage[20];

    /**
     * <p>
     * This BufferedImage for XP bar.
     * </p>
     */
    BufferedImage xpTupeBg;

    /**
     * <p>
     * This BufferedImage for Dragon Coin.
     * </p>
     */
    BufferedImage dragonCoin;

    /**
     * <p>
     * This BufferedImage for Bottom Bar.
     * </p>
     */
    BufferedImage bottomBar;

    /**
     * <p>
     * This BufferedImage for Bottom Bar.
     * </p>
     */
    BufferedImage bottomBar2;

    /**
     * <p>
     * This BufferedImage for skill and potion bar.
     * </p>
     */
    BufferedImage itemSkillBar;

    /**
     * <p>
     * This BufferedImage for inventory Bar.
     * </p>
     */
    BufferedImage inventoryBar;

    /**
     * <p>
     * This BufferedImage for button in UI.
     * </p>
     */
    BufferedImage btnBg;

    /**
     * <p>
     * This BufferedImage for inventory button.
     * </p>
     */
    BufferedImage inventoryBtn;

    /**
     * <p>
     * This BufferedImage for options button.
     * </p>
     */
    BufferedImage optionsBtn;

    /**
     * <p>
     * This BufferedImage for inventory.
     * </p>
     */
    BufferedImage inventory;

    /**
     * <p>
     * This BufferedImage for merchant NPC inventory.
     * </p>
     */
    BufferedImage merchantInventory;

    /**
     * <p>
     * This BufferedImage for Blacksmith's UI .
     * </p>
     */
    BufferedImage blackSmithSlot;

    /**
     * <p>
     * This BufferedImage for dead wolf image.
     * </p>
     */
    BufferedImage deadWolf;

    /**
     * <p>
     * This BufferedImage for kill wolf task image.
     * </p>
     */
    BufferedImage wolfPNG;

    /**
     * <p>
     * This BufferedImage for Task List screen's UI (press G).
     * </p>
     */
    BufferedImage taskList;

    /**
     * <p>
     * This BufferedImage for NPC UI screen.
     * </p>
     */
    BufferedImage abulbulImage;

    /**
     * <p>
     * This BufferedImage for button.
     * </p>
     */
    BufferedImage rectangle;

    /**
     * <p>
     * This BufferedImage for starting screen background.
     * </p>
     */
    BufferedImage aybu;

    /**
     * <p>
     * This BufferedImage for starting screen animation.
     * </p>
     */
    BufferedImage titleScreenPlayer;

    /**
     * <p>
     * This BufferedImage for starting screen animation.
     * </p>
     */
    BufferedImage titleScreenPlayer1;

    /**
     * <p>
     * This BufferedImage for starting screen animation.
     * </p>
     */
    BufferedImage titleScreenPlayer2;

    /**
     * <p>
     * This BufferedImage for starting screen animation.
     * </p>
     */
    BufferedImage titleScreenPlayer3;

    /**
     * <p>
     * This BufferedImage for starting screen animation.
     * </p>
     */
    BufferedImage titleScreenPlayer4;

    /**
     * <p>
     * This BufferedImage for char status.
     * </p>
     */
    BufferedImage charStatus;

    /**
     * <p>
     * This BufferedImage for right key image for NPC.
     * </p>
     */
    BufferedImage rightKey;

    /**
     * <p>
     * This BufferedImage for left key image for NPC.
     * </p>
     */
    BufferedImage leftKey;

    /**
     * <p>
     * This BufferedImage for enter key image for NPC.
     * </p>
     */
    BufferedImage enterKey;

    /**
     * <p>
     * This BufferedImage array for XP bar increases.
     * </p>
     */
    BufferedImage[] xpTupe = new BufferedImage[23];

    /**
     * <p>
     * en variable for using Entity's method.
     * </p>
     */
    Entity en;

    /**
     * <p>
     * This Rectangle variable for respawn here button.
     * </p>
     */
    public Rectangle respawnHereRec = new Rectangle();

    /**
     * <p>
     * This Rectangle variable for respawn city button.
     * </p>
     */
    public Rectangle respawnCityRec = new Rectangle();

    /**
     * <p>
     * This Rectangle variable for inventory button.
     * </p>
     */
    public Rectangle inventoryRec = new Rectangle(), optionsRec = new Rectangle();

    /**
     * <p>
     * This Rectangle variable for next page button.
     * </p>
     */
    public Rectangle nextPageRec = new Rectangle();

    /**
     * <p>
     * This Rectangle variable for starting button.
     * </p>
     */
    public Rectangle startRec = new Rectangle();

    /**
     * <p>
     * This Rectangle variable for exit button.
     * </p>
     */
    public Rectangle exitRec = new Rectangle();

    /**
     * <p>
     * This Rectangle variable for char status button.
     * </p>
     */
    public Rectangle charStatusRec = new Rectangle();

    /**
     * <p>
     * This Rectangle variable for save button.
     * </p>
     */
    public Rectangle saveRec = new Rectangle();

    /**
     * <p>
     * This variable for health;
     * </p>
     */
    public int healthBar;

    /**
     * <p>
     * This variable for SP;
     * </p>
     */
    public int spBar;

    /**
     * <p>
     * This variable is timer for health increases
     * </p>
     */
    public int hpBarCounter = 0;

    /**
     * <p>
     * This variable is timer for SP increases;
     * </p>
     */
    public int spBarCounter = 0;

    /**
     * <p>
     * This arraylist ensure the draw damage amount in screen.
     * </p>
     */
    public ArrayList<Damages> damages = new ArrayList<>();

    /**
     * <p>
     * This boolean variable for screen message NPC etc.
     * </p>
     */
    public boolean messageOn = false;

    /**
     * <p>
     * This boolean variable control the Task Screen opening.
     * </p>
     */
    public boolean openTaskScreen = false;

    /**
     * <p>
     * This ArrayList variable store the message and ensure the drawing.
     * </p>
     */
    public ArrayList<String> message = new ArrayList<>();

    /**
     * <p>
     * This ArrayList variable is timer for message.
     * </p>
     */
    public ArrayList<Integer> messageCounter = new ArrayList<>();

    /**
     * <p>
     * This variable is store the index for inventory arraylist.
     * </p>
     */
    public int itemIndex;

    /**
     * <p>
     * This string variable is store NPC's current dialogue.
     * </p>
     */
    public String currentDialogue = " ";

    /**
     * <p>
     * This string variable for kill wolf task.
     * </p>
     */
    public String wolfCountersString = "";

    /**
     * <p>
     * This boolean variable is store kill wolf task do or not.
     * </p>
     */
    public boolean wolfTaskDo = false;

    /**
     * <p>
     * This variable is store NPC dialogue screen's page number.
     * </p>
     */
    public int pageNum = 0;

    /**
     * <p>
     * This variable is store inventory slot's column number.
     * </p>
     */
    public int slotCol = 0;

    /**
     * <p>
     * This variable is store inventory slot's row number.
     * </p>
     */
    public int slotRow = 0;

    /**
     * <p>
     * This variable is store merchant NPC's inventory slot's column number.
     * </p>
     */
    public int npcSlotCol = 0;

    /**
     * <p>
     * This variable is store merchant NPC's inventory slot's row number.
     * </p>
     */
    public int npcSlotRow = 0;

    /**
     * <p>
     * This variable is store inventory cursor's current location.
     * </p>
     */
    public int cursorIndex = 0;

    /**
     * <p>
     * This variable store ESC screen's chooses.
     * </p>
     */
    public int commandNum = 0;

    /**
     * <p>
     * This variable is store merchant NPC's inventory cursor's current location.
     * </p>
     */
    public static int cursorNpcIndex = 0;

    /**
     * <p>
     * This variable store ESC screen's chooses.
     * </p>
     */
    public int subState = 0;

    /**
     * <p>
     * This variable is timer for blacksmith.
     * </p>
     */
    public int counter = 0;

    /**
     * <p>
     * This variable controls NPC's task screen.
     * </p>
     */
    public boolean pressed = false;

    /**
     * <p>
     * This variable for use entity class methods.
     * </p>
     */
    public Entity npc;

    /**
     * <p>
     * This variable for health tube.
     * </p>
     */
    public int fillTupeNum = 0;

    /**
     * <p>
     * This variable for health tube increases.
     * </p>
     */
    public int tupeImg;

    /**
     * <p>
     * This variable button's color.
     * </p>
     */
    public int btnHover = 0;

    /**
     * <p>
     * This variable store respawn button width.
     * </p>
     */
    public int respawnBtnWidth;

    /**
     * <p>
     * This variable store respawn button height.
     * </p>
     */
    public int respawnBtnHeight;

    /**
     * <p>
     * This variable store title screen text area width.
     * </p>
     */
    public int titleScreenBtnWidth;

    /**
     * <p>
     * This variable store title screen text area height.
     * </p>
     */
    public int titleScreenHeight;

    /**
     * <p>
     * This variable store next page button width.
     * </p>
     */
    public int nextPageBtnWidth;

    /**
     * <p>
     * This variable store next page button height.
     * </p>
     */
    public int nextPageBtnHeight;

    /**
     * <p>
     * This variable store player name.
     * </p>
     */
    public String playerName = "";

    /**
     * <p>
     * This variable control that is input given?
     * </p>
     */
    public boolean enterName = false;

    /**
     * <p>
     * This variable control sword's enchanting.
     * </p>
     */
    public boolean successEnch;

    /**
     * <p>
     * This variable timer for text area.
     * </p>
     */
    public int textAreaCounter = 0;

    /**
     * <p>
     * This variable store cursor's location in text area.
     * </p>
     */
    public int textCursorX = 690;

    /**
     * <p>
     * This variable control the blacksmith's sound.
     * </p>
     */
    public boolean enhanceSE = true;

    /**
     * <p>
     * This variable timer for title screen animation.
     * </p>
     */
    public int titleScreenPlayerCounter = 0;

    /**
     * <p>
     * This variable timer for title screen character image for animation.
     * </p>
     */
    public int titleScreenPlayerSpriteNum = 0;

    /**
     * <p>
     * This variable for save inventory.
     * </p>
     */
    SaveLoad saveLoad = new SaveLoad(gp);

    /**
     * <p>
     * This is constructor. Defines all image path for BufferedImage and assign the
     * other variables.
     * </p>
     * 
     * @param gp is the game panel
     * @since 1.0
     */
    public UI(GamePanel gp) {
        this.gp = gp;
        en = new Entity(gp);
        e1 = new Entity(gp);
        arial_30 = new Font("Arial", Font.PLAIN, 20);

        titleScreenPlayer = gp.uTool.setup("/UI/titleScreenPlayer", 650, 650);
        titleScreenPlayer1 = gp.uTool.setup("/UI/titleScreenPlayer1", 650, 650);
        titleScreenPlayer2 = gp.uTool.setup("/UI/titleScreenPlayer2", 650, 650);
        titleScreenPlayer3 = gp.uTool.setup("/UI/titleScreenPlayer3", 650, 650);
        titleScreenPlayer4 = gp.uTool.setup("/UI/titleScreenPlayer4", 650, 650);
        enterKey = gp.uTool.setup("/UI/enterKey", 32, 32);
        rightKey = gp.uTool.setup("/UI/rightKey", 32, 32);
        leftKey = gp.uTool.setup("/UI/leftKey", 32, 32);
        optionsBtn = gp.uTool.setup("/UI/optionsBtn", gp.tileSize, gp.tileSize);
        inventoryBtn = gp.uTool.setup("/UI/inventoryBtn", gp.tileSize, gp.tileSize);
        abulbulImage = gp.uTool.setup("/UI/abulbul", gp.screenWidth, gp.screenHeight);
        blackSmithSlot = gp.uTool.setup("/UI/blacksmithslot", 380, 456);
        merchantInventory = gp.uTool.setup("/UI/merchantInventory", 255, 491);
        rectangle = gp.uTool.setup("/titleScreen/rectangle", 474, 196);
        dolunayImage = gp.uTool.setup("/objects/dolunayItem", gp.tileSize, gp.tileSize);
        emptyBarImage = gp.uTool.setup("/UI/emptyBar", gp.tileSize, gp.tileSize);
        cursorImage = gp.uTool.setup("/UI/cursorImage", gp.tileSize, gp.tileSize);
        xpTupeBg = gp.uTool.setup("/UI/xpTupeBg", 130, gp.tileSize);
        bottomBar = gp.uTool.setup("/UI/bottomBar", 280, 24);
        btnBg = gp.uTool.setup("/UI/respawnBtn", 181, 29);
        bottomBar2 = gp.uTool.setup("/UI/bottomBar", 913, 24);
        dragonCoin = gp.uTool.setup("/UI/dragonCoin", gp.tileSize, gp.tileSize);
        itemSkillBar = gp.uTool.setup("/UI/itemSkillBar", 400, 30);
        inventory = gp.uTool.setup("/UI/inventory", 170, 504);
        taskList = gp.uTool.setup("/UI/taskList", 260, 300);
        aybu = gp.uTool.setup("/titleScreen/aybu", gp.screenWidth, gp.screenHeight);
        charStatus = gp.uTool.setup("/titleScreen/char", 370, 450);
        deadWolf = gp.uTool.setup("/wolf/deadWolf", gp.tileSize * 3 / 2, gp.tileSize * 3 / 2);
        wolfPNG = gp.uTool.setup("/ui/wolfPng", gp.tileSize, gp.tileSize);

        for (int i = 0; i < hpBarImages.length; i++) {
            hpBarImages[i] = gp.uTool.setup("/UI/HpBar" + (i + 1), gp.tileSize, gp.tileSize);
        }

        for (int i = 0; i < spBarImages.length; i++) {
            spBarImages[i] = gp.uTool.setup("/UI/SpBar" + (i + 1), gp.tileSize, gp.tileSize);
        }

        swordSpinImage = gp.uTool.setup("/skills/swordSpin", gp.tileSize, gp.tileSize);
        for (int i = 0; i < swordSpinImageUsed.length; i++) {
            swordSpinImageUsed[i] = gp.uTool.setup("/skills/swordSpin" + (swordSpinImageUsed.length - i),
                    gp.tileSize, gp.tileSize);
        }

        auraOfSwordImage = gp.uTool.setup("/skills/AuraOfSword", gp.tileSize, gp.tileSize);
        for (int i = 0; i < auraSwordImageUsed.length; i++) {
            auraSwordImageUsed[i] = gp.uTool.setup("/skills/auraSword" + (i + 1), gp.tileSize, gp.tileSize);
        }

        for (int i = 0; i < xpTupe.length; i++) {
            xpTupe[i] = gp.uTool.setup("/xpTupe/xpTupe" + (i + 1), gp.tileSize, gp.tileSize);
        }
    }

    /**
     * <p>
     * This method ensure UI elements drawing.
     * </p>
     * 
     * @param g2 Graphics2D for GamePanel
     * @since 1.0
     */
    public void draw(Graphics2D g2) {

        this.g2 = g2;

        // Message
        drawMessage(g2);

        // NPC
        if (gp.player.closeNPCIndex != -1) {
            g2.setFont(g2.getFont().deriveFont(11F));
            g2.setColor(Color.black);
            g2.drawString("Press     to interact with " + gp.npc[gp.player.closeNPCIndex].name,
                    gp.npc[gp.player.closeNPCIndex].drawX - 75, gp.npc[gp.player.closeNPCIndex].drawY + 120);
            g2.setColor(Color.white);
            g2.drawString("Press     to interact with " + gp.npc[gp.player.closeNPCIndex].name,
                    gp.npc[gp.player.closeNPCIndex].drawX - 75, gp.npc[gp.player.closeNPCIndex].drawY + 120);
            g2.drawImage(enterKey, gp.npc[gp.player.closeNPCIndex].drawX - 29,
                    gp.npc[gp.player.closeNPCIndex].drawY + 104, 20, 20, null);
        }

        // TITLE STATE
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
            changeCursor();
        }

        // PLAY STATE
        if (gp.gameState == gp.playState) {

            drawTaskList();

            // Skills
            drawSkills(g2);

            // Bottom Bar
            drawBottomBar(g2);

            // Change Cursor
            changeCursor();

            drawWolfCounter();

            // DAMAGE
            for (int i = 0; i < damages.size(); i++) {
                if (damages.get(i) != null) {
                    damageAnimation(g2, i);
                }
            }

            changeAlpha(g2, 1f);
        }

        // DEAD STATE
        if (gp.gameState == gp.deadState) {

            // Dark Frame
            g2.setColor(new Color(0, 0, 0, 70));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            // Bottom Bar
            drawBottomBar(g2);

            // Change Cursor
            changeCursor();

            g2.setFont(arial_30);
            g2.setColor(Color.white);

            // Re-spawn Button
            respawnButtons(g2);

        }

        // PAUSE STATE
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }

        // DIALOGUE STATE
        if (gp.gameState == gp.dialogueState) {
            g2.drawImage(abulbulImage, null, 0, 0);
            drawStory(650, 500);
        }

        // INVENTORY STATE
        if (gp.gameState == gp.inventoryState) {
            drawBottomBar(g2);

            drawInventory(true);
        }

        // ENCHANT STATE
        if (gp.gameState == gp.enchantState) {
            drawBottomBar(g2);
            drawInventory(true);
            drawEnchantment();
        }

        // TRADE STATE
        if (gp.gameState == gp.tradeState) {
            drawTradeScreen();
            drawInventory(false);
            drawBottomBar(g2);
        }

        // OPTIONS STATE
        if (gp.gameState == gp.optionsState) {
            drawOptionsScreen();
        }

    }

    /**
     * <p>
     * This method draw the title screen elements.
     * </p>
     */
    public void drawTitleScreen() {

        BufferedImage image = null;

        g2.drawImage(aybu, null, 0, 0);

        if (gp.player.name.equals("")) {
            textAreaCounter++;
            enterName = true;
            g2.drawImage(rectangle, null, 510, 350);
            g2.setColor(Color.white);
            g2.fillRect(680, 430, 150, 35);
            g2.setColor(Color.black);
            g2.drawString(playerName, 690, 455);

            if (textAreaCounter < 20) {
                g2.drawLine(textCursorX, 433, textCursorX, 460);
            } else if (textAreaCounter <= 40) {
                if (textAreaCounter == 40) {
                    textAreaCounter = 0;
                }
            }

            if (playerName == "") {
                g2.setColor(Color.gray);
                g2.drawString("Enter name", 690, 455);
            }

            enterNameButton(g2);
        } else {
            if (gp.playBtn) {
                titleScreenPlayerCounter++;
                if (titleScreenPlayerCounter % 2 == 0) {
                    if (titleScreenPlayerSpriteNum == 3) {
                        titleScreenPlayerSpriteNum = 0;
                    } else {
                        titleScreenPlayerSpriteNum++;
                    }
                }

                switch (titleScreenPlayerSpriteNum) {

                    case 0:
                        image = titleScreenPlayer1;
                        break;
                    case 1:
                        image = titleScreenPlayer2;
                        break;
                    case 2:
                        image = titleScreenPlayer3;
                        break;
                    case 3:
                        image = titleScreenPlayer4;
                        break;

                }

                g2.drawImage(image, null, 650 + titleScreenPlayerCounter * 10, 220);
            } else {
                g2.drawImage(titleScreenPlayer, null, 650, 220);
            }

            g2.drawImage(charStatus, null, 50, 350);
            titleScreenCharStatus(g2);
            titleScreenButtons(g2);
        }

    }

    /**
     * <p>
     * This method for kill wolf task. It Draws dead wolf counter.
     * </p>
     */
    public void drawWolfCounter() {
        if (gp.player.taskLevel == 2 && gp.keyH.missionPrize[2] == 0) {
            if (gp.player.deadWolfCounter < 3) {
                int wolfCounter = 3 - gp.player.deadWolfCounter;
                wolfCountersString = "Remaining Wolf Amount: " + wolfCounter;
                g2.drawImage(wolfPNG, null, gp.tileSize, gp.tileSize);
                g2.setFont(g2.getFont().deriveFont(32F));
                g2.drawString(wolfCountersString, gp.tileSize * 3 - 40, gp.tileSize * 2 - 15);
            } else if (gp.player.deadWolfCounter >= 3) {
                wolfCountersString = "3 wolves killed!";
                g2.drawImage(deadWolf, null, gp.tileSize, gp.tileSize);
                g2.setFont(g2.getFont().deriveFont(32F));
                g2.drawString(wolfCountersString, gp.tileSize * 3, gp.tileSize * 2);
                addMessage("Submit the Mission!");
                wolfTaskDo = true;
            }

        }
    }

    /**
     * <p>
     * This method draws Option screen.
     * </p>
     */
    public void drawOptionsScreen() {

        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        // SUB WINDOW
        int frameX = gp.tileSize * 12;
        int frameY = gp.tileSize * 4;
        int frameWidth = gp.tileSize * 8;
        int frameHeight = gp.tileSize * 10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        switch (subState) {
            case 0:
                options_top(frameX, frameY);
                break;
            case 1:
                options_fullScreenNotification(frameX, frameY);
                break;
            case 2:
                options_control(frameX, frameY);
                break;
            case 3:
                options_endGameConfirmation(frameX, frameY);
                break;
            case 4:
                gp.saveLoad.save();
                break;
        }
        gp.keyH.enterPressed = false;
    }

    /**
     * <p>
     * This method draw special rounded rectangle for UI
     * </p>
     * @param x is x coordinates
     * @param y is y coordinates
     * @param width is refers drawing shape's width 
     * @param height is refers drawing shape's height
     */
    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 200);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        // g2.drawImage(dialogueUI, null, x, y);
    }

    /**
     * <p>
     * This method draw option menu top.
     * </p>
     * @param frameX is x location
     * @param frameY is y location
     *                
     */
    public void options_top(int frameX, int frameY) {

        int textX;
        int textY;

        // TITLE
        String text = "Options";
        textX = getXForCenteredText(g2, text);
        textY = frameY + gp.tileSize;
        g2.setColor(Color.white);
        g2.drawString(text, textX, textY);

        // FULL SCREEN ON/OFF
        textX = frameX + gp.tileSize;
        textY += gp.tileSize * 2;
        g2.drawString("Full Screen", textX, textY);
        if (commandNum == 0) {
            g2.drawString((">"), textX - 25, textY);
            if (gp.keyH.enterPressed == true) {
                if (gp.fullScreenOn == false) {
                    gp.fullScreenOn = true;
                } else if (gp.fullScreenOn == true) {
                    gp.fullScreenOn = false;
                }
                subState = 1;
            }
        }

        // MUSIC
        textY += gp.tileSize;
        g2.drawString("Music", textX, textY);
        if (commandNum == 1) {
            g2.drawString((">"), textX - 25, textY);
        }

        // SE
        textY += gp.tileSize;
        g2.drawString("SE", textX, textY);
        if (commandNum == 2) {
            g2.drawString((">"), textX - 25, textY);
        }

        // CONTROL
        textY += gp.tileSize;
        g2.drawString("Control", textX, textY);
        if (commandNum == 3) {
            g2.drawString((">"), textX - 25, textY);
            if (gp.keyH.enterPressed == true) {
                subState = 2;
                commandNum = 0;
            }
        }

        // END GAME
        textY += gp.tileSize;
        g2.drawString("End Game", textX, textY);
        if (commandNum == 4) {
            g2.drawString((">"), textX - 25, textY);
            if (gp.keyH.enterPressed == true) {
                subState = 3;
                commandNum = 0;
            }
        }
        // SAVE
        textY += gp.tileSize;
        g2.drawString("Save", textX, textY);
        if (commandNum == 5) {
            g2.drawString((">"), textX - 25, textY);
            if (gp.keyH.enterPressed == true) {
                // g2.drawString(("Saving..."), textX + 25, textY);
                // subState = 4;
                commandNum = 0;
                gp.gameState = gp.playState;
            }
        }

        // BACK
        textY += (int) (gp.tileSize * 1.5);
        g2.drawString("Back", textX, textY);
        if (commandNum == 6) {
            g2.drawString((">"), textX - 25, textY);
            if (gp.keyH.enterPressed == true) {
                gp.gameState = gp.playState;
                commandNum = 0;
            }
        }

        // FULL SCREEN CHECK BOX
        textX = frameX + gp.tileSize * 6;
        textY = frameY + gp.tileSize * 2 + 32;
        g2.drawRect(textX, textY, 24, 24);
        if (gp.fullScreenOn == true) {
            g2.fillRect(textX, textY, 24, 24);
        }

        // MUSIC VOLUME
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 80, 16);
        int volumeWidth = 16 * gp.soundtrack.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 16);

        // SE VOLUME
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 80, 16);
        volumeWidth = 16 * gp.se.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 16);

    }

    /**
     * <p>
     * This method draw pause screen UI.
     * </p>
     */
    public void drawPauseScreen() {

        String text = "PAUSED";
        int x = getXForCenteredText(g2, text);
        int y = gp.screenHeight / 2;
        // will be changed
        g2.setFont(g2.getFont().deriveFont(20F));
        g2.setColor(Color.white);

        g2.drawString(text, x, y);
    }

    /**
     * <p>
     * This method draw save screen.
     * </p>
     */
    public void drawSaveScreen() {

        String text = "SAVED";
        int x = getXForCenteredText(g2, text);
        int y = gp.screenHeight / 2;
        // will be changed
        g2.setFont(g2.getFont().deriveFont(20F));
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
    }

    /**
     * <p>
     * This method draw NPC's dialogue.
     * </p>
     * @param x is x coordinates
     * @param y is y coordinates
     */
    public void drawStory(int x, int y) {
        Color c2 = new Color(255, 255, 255);
        g2.setColor(c2);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 18F));
        x += gp.tileSize;
        y += gp.tileSize + 10;
        int lineCount = 0;
        int index = en.taskLevel;
        if (index == 0) {
            for (String line : en.dialogues[0].split("\n")) {
                g2.drawString(line, x, y);
                y += 30;
                lineCount++;
            }
        } else if (index == 1) {
            for (String line : en.dialogues[1].split("\n")) {
                g2.drawString(line, x, y);
                y += 30;
                lineCount++;
            }
        } else if (index == 2 && pageNum == 0 && wolfTaskDo == false) {
            for (String line : en.dialogues[2].split("\n")) {
                g2.drawString(line, x, y);
                y += 30;
                lineCount++;
            }
        } else if (index == 2 && pageNum == 1 && wolfTaskDo == false) {
            for (String line : en.dialogues[3].split("\n")) {
                g2.drawString(line, x, y);
                y += 30;
                lineCount++;
            }
        } else if (index == 2 && wolfTaskDo == true) {
            for (String line : en.dialogues[4].split("\n")) {
                g2.drawString(line, x, y);
                y += 30;
                lineCount++;
            }
        } else if (index == 3 && pageNum == 0) {
            for (String line : en.dialogues[5].split("\n")) {
                g2.drawString(line, x, y);
                y += 30;
                lineCount++;
            }
        } else if (index == 3 && pageNum == 1) {
            for (String line : en.dialogues[6].split("\n")) {
                g2.drawString(line, x, y);
                y += 30;
                lineCount++;
            }
        } else if (index == 4) {
            for (String line : en.dialogues[7].split("\n")) {
                g2.drawString(line, x, y);
                y += 30;
                lineCount++;
            }
        }

        g2.setFont(g2.getFont().deriveFont(15F));

        if (gp.player.taskLevel == 2 || gp.player.taskLevel == 3) {
            if (pageNum == 0) {
                g2.drawString("Press       to read next page", 1230, 850);
                g2.drawImage(rightKey, null, 1290, 830);
            } else {
                g2.drawString("Press       to read previous page", 700, 850);
                g2.drawImage(leftKey, null, 760, 830);

                g2.drawString("Press       to take up the task", 1230, 850);
                g2.drawImage(enterKey, null, 1290, 830);
            }
        } else {
            g2.drawString("Press       to take up the task", 1230, 850);
            g2.drawImage(enterKey, null, 1290, 830);
        }
    }

    /**
     * <p>
     * This method draw inventory.
     * </p>
     * @param cursorState for in merchant NPC inventory or not.
     *                    
     */
    public void drawInventory(boolean cursorState) {

        // FRAME
        int inventoryX = gp.screenWidth - 255;
        int inventoryY = 77;
        int inventoryWidth = 255;
        int inventoryHeight = 756;

        // Inventory
        g2.drawImage(inventory, inventoryX, inventoryY, inventoryWidth, inventoryHeight, null);

        String coin = "";

        if (gp.player.getPlayerCoin() > 999) {
            coin = Integer.toString(gp.player.getPlayerCoin() / 1000) + "."
                    + String.format("%03d", gp.player.getPlayerCoin() % 1000);
        } else {
            coin = Integer.toString(gp.player.getPlayerCoin());
        }

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 14F));
        g2.drawString(coin, inventoryX + 120, inventoryY + 750);
        g2.drawString("Yang", inventoryX + 210, inventoryY + 750);

        // SLOT
        final int slotXstart = inventoryX + 8;
        final int slotYstart = inventoryY + 300;
        int slotX = slotXstart;
        int slotY = slotYstart;

        // DRAW PLAYER'S ITEM
        // System.out.println(gp.player.inventory.size());

        for (int i = 0; i < gp.player.inventory.size(); i++) {
            if (gp.player.inventory.get(i) != null) {

                if (gp.player.inventory.get(i) == gp.player.currentWeapon) {

                    g2.setColor(new Color(240, 190, 90));
                    g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
                    if (gp.player.inventory.get(i).enchantLevel == 1) {
                        g2.setColor(new Color(150, 116, 68));
                        g2.fillRoundRect(inventoryX + 168, inventoryY + 78, gp.tileSize, gp.tileSize, 10, 10);
                    } else if (gp.player.inventory.get(i).enchantLevel == 2) {
                        g2.setColor(new Color(192, 192, 192));
                        g2.fillRoundRect(inventoryX + 168, inventoryY + 78, gp.tileSize, gp.tileSize, 10, 10);
                    } else if (gp.player.inventory.get(i).enchantLevel >= 3) {
                        g2.setColor(new Color(255, 215, 0));
                        g2.fillRoundRect(inventoryX + 168, inventoryY + 78, gp.tileSize, gp.tileSize, 10, 10);
                    }
                    g2.drawImage(gp.player.inventory.get(i).down1, inventoryX + 168, inventoryY + 78, gp.tileSize,
                            gp.tileSize, null);
                    g2.setColor(new Color(240, 190, 90));
                    g2.drawRect(inventoryX + 168, inventoryY + 78, gp.tileSize, gp.tileSize);
                }

                g2.drawImage(gp.player.inventory.get(i).down1, slotX, slotY, gp.tileSize, gp.tileSize, null);

                g2.setStroke(new java.awt.BasicStroke(4));

                if (gp.player.inventory.get(i).enchantLevel == 1) {
                    g2.setColor(new Color(150, 116, 68));
                    g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
                    g2.drawImage(gp.player.inventory.get(i).down1, slotX, slotY, gp.tileSize, gp.tileSize, null);
                } else if (gp.player.inventory.get(i).enchantLevel == 2) {
                    g2.setColor(new Color(192, 192, 192));
                    g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
                    g2.drawImage(gp.player.inventory.get(i).down1, slotX, slotY, gp.tileSize, gp.tileSize, null);
                } else if (gp.player.inventory.get(i).enchantLevel >= 3) {
                    g2.setColor(new Color(255, 215, 0));
                    g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
                    g2.drawImage(gp.player.inventory.get(i).down1, slotX, slotY, gp.tileSize, gp.tileSize, null);
                }

                if (gp.player.inventory.get(i).name == "Red Potion"
                        || gp.player.inventory.get(i).name == "Blue Potion") {
                    g2.setColor(Color.white);
                    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 11F));
                    // g2.drawRect(slotX + 25, slotY + 25, 20, 20);

                    if (gp.player.inventory.get(i).name == "Red Potion") {
                        if (gp.player.redPotionNumber < 10) {
                            g2.drawString(Integer.toString(gp.player.redPotionNumber), slotX + 32, slotY + 35);
                        } else {
                            g2.drawString(Integer.toString(gp.player.redPotionNumber), slotX + 27, slotY + 35);
                        }
                    }

                    if (gp.player.inventory.get(i).name == "Blue Potion") {
                        if (gp.player.bluePotionNumber < 10) {
                            g2.drawString(Integer.toString(gp.player.bluePotionNumber), slotX + 32, slotY + 35);
                        } else {
                            g2.drawString(Integer.toString(gp.player.bluePotionNumber), slotX + 27, slotY + 35);
                        }
                    }
                }

                slotX += gp.tileSize;
                if (i % 5 == 4) {
                    // i == 4 || i == 9 || i == 14 || i == 19 || i == 24 || i == 29 || i == 34 || i
                    // == 39
                    slotX = slotXstart;
                    slotY += gp.tileSize;
                }
            }
        }

        /**
         * <p>
         * This method for cursor's location
         * </p>
         */
        int cursorX = slotXstart + (gp.tileSize * slotCol);

        /**
         * <p>
         * This method for cursor's location
         * </p>
         */
        int cursorY = slotYstart + (gp.tileSize * slotRow);

        /**
         * <p>
         * This method for cursor's width.
         * </p>
         */
        int cursorWidth = gp.tileSize;

        /**
         * <p>
         * This method for cursor's height.
         * </p>
         */
        int cursorHeight = gp.tileSize;

        /**
         * <p>
         * This method store cursors current location .
         * </p>
         */
        cursorIndex = (slotRow * 5) + slotCol;

        // DRAW CURSOR
        if (cursorState) {
            g2.setStroke(new java.awt.BasicStroke(2));
            g2.setColor(Color.white);
            g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
            g2.setStroke(new java.awt.BasicStroke(1));
        } else {

        }

    }

    /**
     * <p>
     * This method draws blacksmith's UI.
     * </p>
     */
    public void drawEnchantment() {

        int enchantmentX = 100;
        int enchantmentY = 100;

        int eFrameWidth = 152;
        int eFrameHeight = 156;

        g2.drawImage(blackSmithSlot, null, enchantmentX, enchantmentY);

        for (int i = 0; i < gp.player.inventory.size(); i++) {
            if (gp.player.inventory.get(i) != null) {
                if (gp.player.itemEnchSellected) {

                    if (gp.player.inventory.get(i) == gp.player.enchantWeapon) {
                        g2.drawImage(gp.player.inventory.get(i).down1, 260, 260, 60, 60, null);
                        if (gp.player.itemEnchSellected) {
                            g2.setColor(Color.white);
                        }

                        if (gp.player.enchantAccepted == true) {

                            if (enhanceSE) {
                                gp.playSE(27);
                                enhanceSE = false;
                            }

                            g2.setColor(Color.white);
                            g2.setFont(g2.getFont().deriveFont(22F));

                            counter++;
                            if (counter < 30)
                                g2.drawString("Weapon is enhancing.", 165, 400);
                            else if (counter < 60)
                                g2.drawString("Weapon is enhancing..", 165, 400);
                            else if (counter < 90)
                                g2.drawString("Weapon is enhancing...", 165, 400);
                            else if (counter < 120)
                                g2.drawString("Weapon is enhancing.", 165, 400);
                            else if (counter < 150)
                                g2.drawString("Weapon is enhancing..", 165, 400);
                            else if (counter < 180)
                                g2.drawString("Weapon is enhancing...", 165, 400);

                            if (counter >= 180) {
                                successEnch = gp.npc[2].increaseWeapon(gp.player.inventory.get(i));
                                gp.player.enchantAccepted = false;
                                enhanceSE = true;
                                pressed = true;
                                counter = 0;
                            }

                        }

                        if (pressed) {
                            g2.setColor(new Color(148, 0, 211));

                            if (successEnch) {
                                gp.player.inventory.get(i).enchantLevel++;
                            }

                            pressed = false;

                        }

                    }
                }

            }

        }
    }

    /**
     * <p>
     * This method ensure the cursor's moving.
     * </p>
     * @return if cursor on the  inventory true otherwise false
     */
    public boolean controlCursor() {
        if (gp.player.inventory.size() > cursorIndex) {
            if (gp.player.inventory.get(cursorIndex) != null) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * <p>
     * This method draws merchant screen.
     * </p>
     */
    public void drawTradeScreen() {

        int inventoryX = 100;
        int inventoryY = 100;

        // Inventory
        g2.drawImage(merchantInventory, inventoryX, inventoryY, null);

        // SLOT
        final int npcSlotXstart = inventoryX + 8;
        final int npcSlotYstart = inventoryY + 24;
        int npcSlotX = npcSlotXstart;
        int npcSlotY = npcSlotYstart;

        // CURSOR
        int npcCursorX = npcSlotXstart + (gp.tileSize * npcSlotCol);
        int npcCursorY = npcSlotYstart + (gp.tileSize * npcSlotRow);
        int npcCursorWidth = gp.tileSize;
        int npcCursorHeight = gp.tileSize;
        cursorNpcIndex = (npcSlotRow * 5) + npcSlotCol;
        // DRAW CURSOR
        g2.setColor(Color.white);
        g2.drawRoundRect(npcCursorX, npcCursorY, npcCursorWidth, npcCursorHeight, 10, 10);

        for (int i = 0; i < Npc_Merchant.npcInventory.size(); i++) {
            if (Npc_Merchant.npcInventory.get(i) != null) {
                g2.drawImage(Npc_Merchant.npcInventory.get(i).down1, npcSlotX, npcSlotY, gp.tileSize, gp.tileSize,
                        null);
                npcSlotX += gp.tileSize;
                if (i % 5 == 4) {
                    npcSlotX = npcSlotXstart;
                    npcSlotY += gp.tileSize;
                }
            }

        }
    }

    /**
     * <p>
     * This method for merchant NPC's inventory cursor's moving.
     * </p>
     * @return  if cursor on the  npc true otherwise false
     */
    public boolean controlNpcCursor() {
        if (Npc_Merchant.npcInventory.size() > cursorNpcIndex) {
            if (Npc_Merchant.npcInventory.get(cursorNpcIndex) != null) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * <p>
     * This method draws respawn buttons.
     * </p>
     * @param g2 Gaphics2D for GamePanel
     *           
     */
    public void respawnButtons(Graphics2D g2) {
        String respawnHere = "    Restart Here";
        String respawnCity = "Restart in the City";

        respawnBtnWidth = gp.tileSize * 4;
        respawnBtnHeight = (int) (gp.tileSize * 0.65);

        respawnHereRec = new Rectangle(gp.tileSize / 2, gp.tileSize / 2, respawnBtnWidth, respawnBtnHeight);
        respawnCityRec = new Rectangle(gp.tileSize / 2, (int) (gp.tileSize * 1.2), respawnBtnWidth, respawnBtnHeight);

        g2.drawImage(btnBg, respawnHereRec.x, respawnHereRec.y, respawnHereRec.width, respawnHereRec.height, null);
        g2.drawImage(btnBg, respawnCityRec.x, respawnCityRec.y, respawnCityRec.width, respawnCityRec.height, null);

        if (btnHover == 1) {
            g2.setColor(new Color(238, 238, 238, 40));
            g2.fillRect(respawnHereRec.x, respawnHereRec.y, respawnHereRec.width, respawnHereRec.height);
        } else if (btnHover == 2) {
            g2.setColor(new Color(238, 238, 238, 40));
            g2.fillRect(respawnCityRec.x, respawnCityRec.y, respawnCityRec.width, respawnCityRec.height);
        }

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 13F));
        g2.setColor(Color.white);
        g2.drawString(respawnHere, 65, 44);
        g2.drawString(respawnCity, 65, 76);

        g2.setColor(new Color(0, 0, 0, 0));
        g2.drawRect(respawnHereRec.x, respawnHereRec.y, respawnHereRec.width, respawnHereRec.height);
        g2.drawRect(respawnCityRec.x, respawnCityRec.y, respawnCityRec.width, respawnCityRec.height);
        g2.setColor(Color.white);

    }

    /**
     * <p>
     * This method draws title screen buttons.
     * </p>
     * @param g2 Gaphics2D for GamePanel
     *           
     */
    public void enterNameButton(Graphics2D g2) {
        String text = "Save";

        saveRec = new Rectangle(700, 475, 100, 29);

        g2.drawImage(btnBg, saveRec.x, saveRec.y, saveRec.width, saveRec.height, null);

        if (btnHover == 1) {
            g2.setColor(new Color(238, 238, 238, 40));
            g2.fillRect(saveRec.x, saveRec.y, saveRec.width, saveRec.height);
        }

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 18F));
        g2.setColor(Color.white);
        g2.drawString(text, 730, 495);

        g2.setColor(new Color(0, 0, 0, 0));
        g2.drawRect(saveRec.x, saveRec.y, saveRec.width, saveRec.height);
        g2.setColor(Color.white);

    }

    /**
     * <p>
     * This method draws title screen buttons.
     * </p>
     * @param g2 Gaphics2D for GamePanel
     *           
     */
    public void titleScreenButtons(Graphics2D g2) {
        String start = "         Start";
        String exit = "          Exit";

        titleScreenBtnWidth = gp.tileSize * 4;
        titleScreenHeight = (int) (gp.tileSize * 0.75);

        startRec = new Rectangle(gp.tileSize * 3, (int) (gp.tileSize * 14.7), titleScreenBtnWidth, titleScreenHeight);
        exitRec = new Rectangle(gp.tileSize * 3, (int) (gp.tileSize * 15.6), titleScreenBtnWidth, titleScreenHeight);

        g2.drawImage(btnBg, startRec.x, startRec.y, startRec.width, startRec.height, null);
        g2.drawImage(btnBg, exitRec.x, exitRec.y, exitRec.width, exitRec.height, null);

        if (btnHover == 1) {
            g2.setColor(new Color(238, 238, 238, 40));
            g2.fillRect(startRec.x, startRec.y, startRec.width, startRec.height);
        } else if (btnHover == 2) {
            g2.setColor(new Color(238, 238, 238, 40));
            g2.fillRect(exitRec.x, exitRec.y, exitRec.width, exitRec.height);
        }

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 18F));
        g2.setColor(Color.white);
        g2.drawString(start, 174, 729);
        g2.drawString(exit, 174, 772);

        g2.setColor(new Color(0, 0, 0, 0));
        g2.drawRect(startRec.x, startRec.y, startRec.width, startRec.height);
        g2.drawRect(exitRec.x, exitRec.y, exitRec.width, exitRec.height);
        g2.setColor(Color.white);

    }

    /**
     * <p>
     * This method draws title screen name area.
     * </p>
     * @param g2 Gaphics2D for GamePanel
     *           
     */
    public void titleScreenCharStatus(Graphics2D g2) {

        String userNameField = gp.player.name;
        String clanNameField = "AYBU";
        String hpField = Integer.toString(gp.player.maxLife);
        String spField = Integer.toString((int) (gp.player.maxSp));

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 25F));
        g2.setColor(Color.white);

        g2.drawString(userNameField, 210, 432);
        g2.drawString(clanNameField, 210, 500);
        g2.drawString(hpField, 210, 590);
        g2.drawString(spField, 210, 660);

    }

    /**
     * <p>
     * This method draws centered text.
     * </p>
     * @param g2 Gaphics2D for GamePanel
     * @param text is refers string
     * @return center coordinates
     *           
     */
    public int getXForCenteredText(Graphics2D g2, String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth / 2 - length / 2;
    }

    /**
     * <p>
     * This method draws skills.
     * </p>
     * @param g2 Gaphics2D for GamePanel
     *           
     */
    public void drawSkills(Graphics2D g2) {

        // SWORD SPIN
        int swordSpinImg = gp.skills.skillStandbyTime / 20; // 15 (per 15 seconds)
        if (gp.skills.swordSpinTimeOut != 0) {
            g2.drawImage(swordSpinImageUsed[gp.skills.swordSpinTimeOut / swordSpinImg],
                    gp.tileSize * (gp.maxScreenCol - 1), gp.tileSize / 3, gp.tileSize / 2, gp.tileSize / 2, null);
        } else {
            // g2.drawImage(swordSpinImage, gp.tileSize * (gp.maxScreenCol - 1), gp.tileSize
            // / 3, gp.tileSize / 2, gp.tileSize / 2, null);
        }

        // AURA SWORD
        int auroSwordImg = gp.skills.skillStandbyTime / 20;
        if (gp.skills.auraSwordActive) {
            g2.drawImage(auraOfSwordImage, gp.tileSize * (gp.maxScreenCol - 1) - 30, gp.tileSize / 3, gp.tileSize / 2,
                    gp.tileSize / 2, null);
        }
        /*
         * if (gp.skills.auraSwordTimeOut != 0) {
         * g2.drawImage(auraSwordImageUsed[gp.skills.auraSwordTimeOut / auroSwordImg],
         * gp.tileSize * (gp.maxScreenCol - 1) - 30,gp.tileSize / 3, gp.tileSize / 2,
         * gp.tileSize / 2, null);
         * }
         */
    }

    /**
     * <p>
     * This method change cursors features.
     * </p>
     */
    public void changeCursor() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Point point = new Point(0, 0);
        Cursor cursor = toolkit.createCustomCursor(cursorImage, point, "Cursor");
        gp.setCursor(cursor);
    }

    /**
     * <p>
     * This method draw task list screen.
     * </p>
     */
    public void drawTaskList() {
        if (openTaskScreen) {
            g2.drawImage(taskList, 100, 100, null);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 24F));
            g2.setColor(Color.white);
            g2.drawString("Task List", 150, 150);

            int taskX = 130, taskY = 180;
            for (int i = 0; i <= gp.player.taskLevel; i++) {
                drawTaskRec(taskX, taskY + i * 20, 200, 60, i);
            }

        }

    }

    /**
     * <p>
     * This method task screen rectangle
     * </p>
     * @param taskRecX is refers rectangle x coordinates
     * @param taskRecY is refers rectangle y coordinates
     * @param taskRecWidth is refers rectangle width
     * @param taskRecHeight is refers rectangle height
     * @param taskLevel is refers player's task level
     */
    public void drawTaskRec(int taskRecX, int taskRecY, int taskRecWidth, int taskRecHeight, int taskLevel) {

        g2.setFont(g2.getFont().deriveFont(14F));
        String currentTask = gp.player.taskNameList.get(taskLevel);

        if (taskLevel == gp.player.taskLevel) {
            g2.drawOval(taskRecX, taskRecY, 10, 10);
        } else {
            int textWidth = g2.getFontMetrics().stringWidth(currentTask);
            g2.drawLine(taskRecX + 15, taskRecY + 6, taskRecX + 15 + textWidth, taskRecY + 6);
            g2.fillOval(taskRecX, taskRecY, 10, 10);
        }

        g2.drawString(currentTask, taskRecX + 15, taskRecY + 10);
    }

    /**
     * <p>
     * This method draw bottom bar.
     * </p>
     * @param g2 draws 2 dimensional text or shapes
     */
    public void drawBottomBar(Graphics2D g2) {

        int bottomBarHeight = 32;

        // Dragon Coin
        Rectangle dragonCoinRec = new Rectangle(0, gp.screenHeight - 40, 50, 40);
        g2.drawImage(dragonCoin, dragonCoinRec.x, dragonCoinRec.y, dragonCoinRec.width, dragonCoinRec.height, null);

        double oneScaleHealthBar = (2.7 * gp.tileSize) / gp.player.maxLife;
        double oneScaleSpBar = (2.7 * gp.tileSize) / gp.player.maxSp;
        double healthBar = oneScaleHealthBar * gp.player.life;
        double spBar = oneScaleSpBar * gp.player.sp;
        double healthBarWidth = oneScaleHealthBar * gp.player.maxLife;
        double spBarWidth = oneScaleSpBar * gp.player.maxSp;
        int barHeight = 16;

        // Health Bar
        g2.drawImage(emptyBarImage, 50, gp.screenHeight - 2 * barHeight, (int) healthBarWidth + 9, barHeight, null);

        hpBarCounter++;

        if (hpBarCounter == 120) {
            hpBarCounter = 0;
        }

        int hpBarIndex = hpBarCounter / 15;

        g2.drawImage(hpBarImages[hpBarIndex], 56, gp.screenHeight - 2 * barHeight, (int) healthBar, barHeight, null);

        // Sp Bar
        g2.drawImage(emptyBarImage, 50, gp.screenHeight - barHeight, (int) spBarWidth + 9, barHeight, null);

        spBarCounter++;

        if (spBarCounter == 120) {
            spBarCounter = 0;
        }

        int spBarIndex = spBarCounter / 15;

        g2.drawImage(spBarImages[spBarIndex], 56, gp.screenHeight - barHeight, (int) spBar, barHeight, null);

        // XP Tupes
        int xpTupeY = gp.screenHeight - bottomBarHeight;

        g2.drawImage(xpTupeBg, 188, xpTupeY, 130, bottomBarHeight, null);

        fillTupeNum = (gp.player.getPlayerXP() % 920) / 230;

        switch (fillTupeNum) {
            case 0:
                tupeImg = (gp.player.getPlayerXP() % 230) / 10;

                g2.drawImage(xpTupe[tupeImg], 195, xpTupeY + 2, bottomBarHeight - 3, bottomBarHeight - 3, null);
                g2.drawImage(xpTupe[0], 225, xpTupeY + 2, bottomBarHeight - 3, bottomBarHeight - 3, null);
                g2.drawImage(xpTupe[0], 255, xpTupeY + 2, bottomBarHeight - 3, bottomBarHeight - 3, null);
                g2.drawImage(xpTupe[0], 285, xpTupeY + 2, bottomBarHeight - 3, bottomBarHeight - 3, null);
                break;
            case 1:
                tupeImg = (gp.player.getPlayerXP() % 230) / 10;

                g2.drawImage(xpTupe[xpTupe.length - 1], 195, xpTupeY + 2, bottomBarHeight - 3, bottomBarHeight - 3,
                        null);
                g2.drawImage(xpTupe[tupeImg], 225, xpTupeY + 2, bottomBarHeight - 3, bottomBarHeight - 3, null);
                g2.drawImage(xpTupe[0], 255, xpTupeY + 2, bottomBarHeight - 3, bottomBarHeight - 3, null);
                g2.drawImage(xpTupe[0], 285, xpTupeY + 2, bottomBarHeight - 3, bottomBarHeight - 3, null);
                break;
            case 2:
                tupeImg = (gp.player.getPlayerXP() % 230) / 10;

                g2.drawImage(xpTupe[xpTupe.length - 1], 195, xpTupeY + 2, bottomBarHeight - 3, bottomBarHeight - 3,
                        null);
                g2.drawImage(xpTupe[xpTupe.length - 1], 225, xpTupeY + 2, bottomBarHeight - 3, bottomBarHeight - 3,
                        null);
                g2.drawImage(xpTupe[tupeImg], 255, xpTupeY + 2, bottomBarHeight - 3, bottomBarHeight - 3, null);
                g2.drawImage(xpTupe[0], 285, xpTupeY + 2, bottomBarHeight - 3, bottomBarHeight - 3, null);
                break;
            case 3:
                tupeImg = (gp.player.getPlayerXP() % 230) / 10;

                g2.drawImage(xpTupe[xpTupe.length - 1], 195, xpTupeY + 2, bottomBarHeight - 3, bottomBarHeight - 3,
                        null);
                g2.drawImage(xpTupe[xpTupe.length - 1], 225, xpTupeY + 2, bottomBarHeight - 3, bottomBarHeight - 3,
                        null);
                g2.drawImage(xpTupe[xpTupe.length - 1], 255, xpTupeY + 2, bottomBarHeight - 3, bottomBarHeight - 3,
                        null);
                g2.drawImage(xpTupe[tupeImg], 285, xpTupeY + 2, bottomBarHeight - 3, bottomBarHeight - 3, null);
                break;

            default:
                g2.drawImage(xpTupe[xpTupe.length - 1], 195, xpTupeY + 2, bottomBarHeight - 3, bottomBarHeight - 3,
                        null);
                g2.drawImage(xpTupe[xpTupe.length - 1], 225, xpTupeY + 2, bottomBarHeight - 3, bottomBarHeight - 3,
                        null);
                g2.drawImage(xpTupe[xpTupe.length - 1], 255, xpTupeY + 2, bottomBarHeight - 3, bottomBarHeight - 3,
                        null);
                g2.drawImage(xpTupe[xpTupe.length - 1], 285, xpTupeY + 2, bottomBarHeight - 3, bottomBarHeight - 3,
                        null);
                break;
        }

        // Bottom Bar
        int bottomBarX = 318;
        g2.drawImage(bottomBar, bottomBarX, gp.screenHeight - bottomBarHeight, 280, bottomBarHeight, null);

        // Item-Skill Bar
        g2.drawImage(itemSkillBar, bottomBarX + 280, gp.screenHeight - bottomBarHeight, 400, bottomBarHeight, null);

        // Draw Red-Potion
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 10F));
        g2.setColor(Color.white);
        if (gp.player.redPotionNumber < 10) {
            g2.drawString(Integer.toString(gp.player.redPotionNumber), bottomBarX + 348,
                    gp.screenHeight - bottomBarHeight + 27);
        } else {
            g2.drawString(Integer.toString(gp.player.redPotionNumber), bottomBarX + 344,
                    gp.screenHeight - bottomBarHeight + 27);
        }

        // Draw Blue-Potion
        if (gp.player.bluePotionNumber < 10) {
            g2.drawString(Integer.toString(gp.player.bluePotionNumber), bottomBarX + 384,
                    gp.screenHeight - bottomBarHeight + 27);
        } else {
            g2.drawString(Integer.toString(gp.player.bluePotionNumber), bottomBarX + 380,
                    gp.screenHeight - bottomBarHeight + 27);
        }

        // Bottom Bar
        g2.drawImage(bottomBar2, bottomBarX + 680, gp.screenHeight - bottomBarHeight, 913, bottomBarHeight, null);

        inventoryOptionsBtn(g2);

    }

    /**
     * <p>
     * This method draws inventory button.
     * </p>
     * @param g2 draws 2 dimensional text or shapes
     */
    public void inventoryOptionsBtn(Graphics2D g2) {

        int btnPosX = 1470;

        inventoryRec = new Rectangle(btnPosX, gp.screenHeight - 32, 32, 32);
        optionsRec = new Rectangle(btnPosX + 35, gp.screenHeight - 32, 32, 32);

        g2.drawImage(inventoryBtn, inventoryRec.x, inventoryRec.y, inventoryRec.width, inventoryRec.height, null);
        g2.drawImage(optionsBtn, optionsRec.x, optionsRec.y, optionsRec.width, optionsRec.height, null);

        if (btnHover == 1) {
            g2.setColor(new Color(238, 238, 238, 40));
            g2.fillRect(inventoryRec.x, inventoryRec.y, inventoryRec.width, inventoryRec.height);
        } else if (btnHover == 2) {
            g2.setColor(new Color(238, 238, 238, 40));
            g2.fillRect(optionsRec.x, optionsRec.y, optionsRec.width, optionsRec.height);
        }
    }

    /**
     * <p>
     * This method draws message.
     * </p>
     * @param text for which message draws
     *             
     */
    public void addMessage(String text) {

        if (message.size() == 5) {
            message.remove(0);
        }

        message.add(text);
        messageCounter.add(0);

    }

    /**
     * <p>
     * This method draws message list
     * </p>
     * @param g2 drawing 2 dimensional text or shape
     */
    public void drawMessage(Graphics2D g2) {

        int messageX = gp.tileSize;
        int messageY = gp.tileSize * 12;

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20F));

        for (int i = 0; i < message.size(); i++) {
            if (message.get(i) != null) {
                g2.setColor(Color.black);
                g2.drawString(message.get(i), messageX + 2, messageY + 2);
                g2.setColor(Color.white);
                g2.drawString(message.get(i), messageX, messageY);

                int counter = messageCounter.get(i) + 1;
                messageCounter.set(i, counter);
                messageY += 30;

                if (messageCounter.get(i) > 180) {
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
        }

    }

    /**
     * <p>
     * This method draws damage amount
     * </p>
     * @param damageIndex determines current damage
     * @param g2 draws 2 dimensional text or shapes
     *                    
     */
    public void damageAnimation(Graphics2D g2, int damageIndex) {

        damages.get(damageIndex).damagePosX += 10;
        damages.get(damageIndex).damagePosY -= 10;

        if (damages.get(damageIndex).damageFontSize > 5) {
            damages.get(damageIndex).damageFontSize -= 2;
        }

        damageFont = new Font("Arial", Font.PLAIN, damages.get(damageIndex).damageFontSize);

        g2.setFont(damageFont);
        g2.setColor(damages.get(damageIndex).color);
        g2.drawString("" + damages.get(damageIndex).damageSize, damages.get(damageIndex).damagePosX,
                damages.get(damageIndex).damagePosY);

        damages.get(damageIndex).damageCounter++;
        int increaseAmount = 2;
        if (damages.get(damageIndex).damageCounter < increaseAmount) {
            changeAlpha(g2, 1f);
        } else if (damages.get(damageIndex).damageCounter < increaseAmount * 2) {
            changeAlpha(g2, 0.9f);
        } else if (damages.get(damageIndex).damageCounter < increaseAmount * 3) {
            changeAlpha(g2, 0.8f);
        } else if (damages.get(damageIndex).damageCounter < increaseAmount * 4) {
            changeAlpha(g2, 0.7f);
        } else if (damages.get(damageIndex).damageCounter < increaseAmount * 5) {
            changeAlpha(g2, 0.6f);
        } else if (damages.get(damageIndex).damageCounter < increaseAmount * 6) {
            changeAlpha(g2, 0.5f);
        } else if (damages.get(damageIndex).damageCounter < increaseAmount * 7) {
            changeAlpha(g2, 0.4f);
        } else if (damages.get(damageIndex).damageCounter < increaseAmount * 8) {
            changeAlpha(g2, 0.3f);
        } else if (damages.get(damageIndex).damageCounter < increaseAmount * 9) {
            changeAlpha(g2, 0.2f);
        } else if (damages.get(damageIndex).damageCounter < increaseAmount * 10) {
            changeAlpha(g2, 0.1f);
        } else {
            changeAlpha(g2, 0f);
            damages.remove(damageIndex);
        }

    }

    /**
     * <p>
     * This method draw option full screen.
     * </p>
     * @param frameX : Location
     * @param frameY : Location
     *               
     */
    public void options_fullScreenNotification(int frameX, int frameY) {
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize * 3;

        currentDialogue = "The change is not available for now.";

        for (String line : currentDialogue.split("\n")) {
            g2.setFont(arial_30);
            g2.setColor(Color.white);
            g2.drawString(line, textX, textY);

            textY += 40;
        }

        // BACK
        textY = frameY + gp.tileSize * 9;
        g2.drawString("Back", textX, textY);

        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed == true) {
                subState = 0;
                commandNum = 3;
            }
        }

    }

    /**
     * <p>
     * This method determines end game message
     * </p>
     * @param frameX : Location
     * @param frameY : Location
     */
    public void options_endGameConfirmation(int frameX, int frameY) {

        int textX = frameX + gp.tileSize - 32;
        int textY = frameY + gp.tileSize * 3;
        g2.setColor(Color.white);
        currentDialogue = "Are you sure\nyou want to\nexit the game?";

        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        // YES
        String text = "Yes";
        textX = getXForCenteredText(g2, text);
        textY += gp.tileSize * 3;
        g2.drawString(text, textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed == true) {
                subState = 0;
                gp.endGame = true;
                gp.gameState = gp.playState;
            }
        }
        // NO
        text = "No";
        textX = getXForCenteredText(g2, text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed == true) {
                subState = 0;
                commandNum = 4;
            }
        }

    }

    /**
     * <p>
     * This method ensure using option screen.
     * </p>
     * @param frameX : Location
     *  @param frameY : Location
     */
    public void options_control(int frameX, int frameY) {

        int textX;
        int textY;

        // TITLE
        String text = "Control";
        textX = getXForCenteredText(g2, text);
        textY = frameY + gp.tileSize;
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(18F));
        g2.drawString(text, textX + 20, textY);

        frameX -= 32;

        textX = frameX + gp.tileSize * 2;
        textY += gp.tileSize;
        g2.drawString("Move", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Confirm/Attack", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Shoot/Cast", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Character Screen", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Pause", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Options", textX, textY);
        textY += gp.tileSize;

        textX = frameX + gp.tileSize * 6;
        textY = frameY + gp.tileSize * 2;
        g2.drawString("WASD", textX, textY);
        textY += gp.tileSize;
        g2.drawString("ENTER", textX, textY);
        textY += gp.tileSize;
        g2.drawString("F", textX, textY);
        textY += gp.tileSize;
        g2.drawString("C", textX, textY);
        textY += gp.tileSize;
        g2.drawString("P", textX, textY);
        textY += gp.tileSize;
        g2.drawString("ESC", textX, textY);
        textY += gp.tileSize;

        // BACK
        textX = frameX + gp.tileSize * 2;
        textY = frameY + gp.tileSize * 9;
        g2.drawString("Back", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed == true) {
                subState = 0;
                commandNum = 3;
            }
        }
    }

    /**
     * <p>
     * This method change the transparency.
     * </p>
     * @param g2 drawing 2 dimensional text or shapes
     * @param alphaValue refers opacity value
     */
    public void changeAlpha(Graphics2D g2, float alphaValue) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }

}
