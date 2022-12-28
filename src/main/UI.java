package main;

import java.awt.Cursor;
import java.awt.event.*;
import javax.swing.*;
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import entity.Entity;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font arial_30, damageFont;
    BufferedImage coinImage, dolunayImage, emptyBarImage, cursorImage;
    BufferedImage[] hpBarImages = new BufferedImage[8];
    BufferedImage[] spBarImages = new BufferedImage[8];
    BufferedImage auraOfSwordImage, swordSpinImage;
    BufferedImage[] swordSpinImageUsed = new BufferedImage[20];
    BufferedImage[] auraSwordImageUsed = new BufferedImage[20];
    BufferedImage xpTupeBg, dragonCoin, bottomBar, bottomBar2, itemSkillBar, inventoryBar, btnBg;
    BufferedImage inventory;
    BufferedImage dialogueUI;
    BufferedImage newspaper;
    BufferedImage[] xpTupe = new BufferedImage[23];

    public Rectangle respawnHereRec = new Rectangle(), respawnCityRec = new Rectangle();

    public int healthBar;
    public int spBar;

    public int hpBarCounter = 0;
    public int spBarCounter = 0;

    public ArrayList<Damages> damages = new ArrayList<>();

    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public int itemIndex;

    public String currentDialogue = " ";

    public int slotCol = 0;
    public int slotRow = 0;
    public int cursorIndex = 0;
    public int commandNum = 0;

    public int subState = 0;

    public Entity npc;

    public int fillTupeNum = 0;
    public int tupeImg;

    public int btnHover = 0;
    public int respawnBtnWidth, respawnBtnHeight;

    public UI(GamePanel gp) {
        this.gp = gp;

        arial_30 = new Font("Arial", Font.PLAIN, 20);

        coinImage = gp.uTool.setup("/objects/yang", gp.tileSize, gp.tileSize);
        dolunayImage = gp.uTool.setup("/objects/dolunayItem", gp.tileSize, gp.tileSize);
        dialogueUI = gp.uTool.setup("/UI/dialogueUI", gp.tileSize * 6, gp.tileSize * 10);
        emptyBarImage = gp.uTool.setup("/UI/emptyBar", gp.tileSize, gp.tileSize);
        cursorImage = gp.uTool.setup("/UI/cursorImage", gp.tileSize, gp.tileSize);
        xpTupeBg = gp.uTool.setup("/UI/xpTupeBg", 130, gp.tileSize);
        bottomBar = gp.uTool.setup("/UI/bottomBar", 280, 24);
        btnBg = gp.uTool.setup("/UI/respawnBtn", 181, 29);
        bottomBar2 = gp.uTool.setup("/UI/bottomBar", 375, 24);
        dragonCoin = gp.uTool.setup("/UI/dragonCoin", gp.tileSize, gp.tileSize);
        itemSkillBar = gp.uTool.setup("/UI/itemSkillBar", 400, 30);
        inventoryBar = gp.uTool.setup("/UI/inventoryBar", 538, 30);
        inventory = gp.uTool.setup("/UI/inventory", 172, 492);
        dialogueUI = gp.uTool.setup("/UI/dialogueUI", 700, 600);
        newspaper = gp.uTool.setup("/UI/newspaper", gp.screenWidth, gp.screenHeight);

        for (int i = 0; i < hpBarImages.length; i++) {
            hpBarImages[i] = gp.uTool.setup("/UI/HpBar" + (i + 1), gp.tileSize, gp.tileSize);
        }

        for (int i = 0; i < spBarImages.length; i++) {
            spBarImages[i] = gp.uTool.setup("/UI/SpBar" + (i + 1), gp.tileSize, gp.tileSize);
        }

        swordSpinImage = gp.uTool.setup("/skills/Kılıç_Çevirme", gp.tileSize, gp.tileSize);
        for (int i = 0; i < swordSpinImageUsed.length; i++) {
            swordSpinImageUsed[i] = gp.uTool.setup("/skills/Kılıç_Çevirme" + (swordSpinImageUsed.length - i),
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

    public void showMessage(String text) {

        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {

        this.g2 = g2;

        // PLAY STATE
        if (gp.gameState == gp.playState) {

            // Skills
            drawSkills(g2);

            // Bottom Bar
            drawBottomBar(g2);

            // Change Cursor
            changeCursor();

            // DAMAGE
            for (int i = 0; i < damages.size(); i++) {
                if (damages.get(i) != null) {
                    damageAnimation(g2, i);
                }
            }

            changeAlpha(g2, 1f);

            g2.setFont(arial_30);
            g2.setColor(Color.white);

            // Message
            messages(g2);

            // Coin
            g2.drawImage(coinImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize / 2, null);
            g2.drawString(" " + gp.player.playerCoin, 45, 45);
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

            // Message
            messages(g2);

            // Re-spawn Button
            respawnButtons(g2);

        }

        // PAUSE STATE
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }

        // DIALOGUE STATE
        if (gp.gameState == gp.dialogueState) {
            drawDialogueScreen();
        }

        // INVENTORY STATE
        if (gp.gameState == gp.inventoryState) {
            drawBottomBar(g2);

            drawInventory();
        }

        // TRADE STATE
        if (gp.gameState == gp.tradeState) {
            drawTradeScreen();
        }

        // OPTIONS STATE
        if (gp.gameState == gp.optionsState) {
            drawOptionsScreen();
        }

    }

    // OPTIONS SCREEN METHOD
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

        }
        gp.keyH.enterPressed = false;
    }

    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 200);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        // g2.drawImage(dialogueUI, null, x, y);
    }

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

        // BACK
        textY += gp.tileSize * 2;
        g2.drawString("Back", textX, textY);
        if (commandNum == 5) {
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

    // PAUSE SCREEN METHOD
    public void drawPauseScreen() {

        String text = "PAUSED";
        int x = getXForCenteredText(g2, text);
        int y = gp.screenHeight / 2;
        // will be changed
        g2.setFont(g2.getFont().deriveFont(20F));
        g2.setColor(Color.white);

        g2.drawString(text, x, y);
    }

    // DIALOGUE SCREEN METHOD
    public void drawDialogueScreen() {

        // WINDOW
        /* 
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        */
        int x = gp.screenWidth/2 - dialogueUI.getWidth()/2;
        int y = gp.screenHeight/2 - dialogueUI.getHeight()/2;
        /*
         * int width = gp.screenHeight - (gp.tileSize * 4);
         * int height = gp.tileSize * 5;
         * drawSubWindow(x, y, width, height);
         */

        g2.drawImage(dialogueUI, null, x, y);
        //g2.drawImage(newspaper,null,0,0);
        JTextField tf = new JTextField("Merhaba Koyumuze hosgeldin",4);
        

        
        /* 
        Color c2 = new Color(255, 255, 255);
        g2.setColor(c2);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        x += gp.tileSize;
        y += gp.tileSize;
        g2.drawString("currentDialogue", x, y);
        */
    }

    // INVENTORY METHOD
    public void drawInventory() {

        // FRAME
        int inventoryX = gp.screenWidth - 258;
        int inventoryY = 95;
        int inventoryWidth = 258;
        int inventoryHeight = 738;

        // Inventory
        g2.drawImage(inventory, inventoryX, inventoryY, inventoryWidth, inventoryHeight, null);

        // SLOT
        final int slotXstart = inventoryX + 8;
        final int slotYstart = inventoryY + 300;
        int slotX = slotXstart;
        int slotY = slotYstart;

        // DRAW PLAYER'S ITEM
        // System.out.println(gp.player.inventory.size());
        for (int i = 0; i < gp.player.inventory.size(); i++) {

            if (gp.player.inventory.get(i) == gp.player.currentWeapon) {
                // System.out.println("equal");
                g2.setColor(new Color(240, 190, 90));
                g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
                g2.drawImage(gp.player.inventory.get(i).down1, inventoryX + 168, inventoryY + 78, gp.tileSize,
                        gp.tileSize, null);
                g2.setColor(new Color(240, 190, 90));
                g2.drawRect(inventoryX + 168, inventoryY + 78, gp.tileSize, gp.tileSize);
            }

            g2.drawImage(gp.player.inventory.get(i).down1, slotX, slotY, gp.tileSize, gp.tileSize, null);
            slotX += gp.tileSize;
            if (i % 5 == 4) {
                // i == 4 || i == 9 || i == 14 || i == 19 || i == 24 || i == 29 || i == 34 || i
                // == 39
                slotX = slotXstart;
                slotY += gp.tileSize;
            }
        }

        // CURSOR
        int cursorX = slotXstart + (gp.tileSize * slotCol);
        int cursorY = slotYstart + (gp.tileSize * slotRow);
        int cursorWidth = gp.tileSize;
        int cursorHeight = gp.tileSize;
        cursorIndex = (slotRow * 5) + slotCol;
        // System.out.println("cursor2: "+ cursorIndex2);

        // DRAW CURSOR
        g2.setStroke(new java.awt.BasicStroke(2));
        g2.setColor(Color.white);
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
        g2.setStroke(new java.awt.BasicStroke(1));

    }

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

    // DRAW TRADE SCREEN
    public void drawTradeScreen() {
        switch (subState) {
            case 0:
                trade_select();
                break;
            case 1:
                trade_buy();
                break;
            case 2:
                trade_sell();
                break;
        }
    }

    public void trade_select() {
    }

    public void trade_buy() {
    }

    public void trade_sell() {
    }

    // DEAD MENU
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

    // CENTERED TEXT
    public int getXForCenteredText(Graphics2D g2, String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth / 2 - length / 2;
    }

    // SKILLS
    public void drawSkills(Graphics2D g2) {

        // SWORD SPIN
        int swordSpinImg = gp.skills.skillStandbyTime / 20; // 15 (per 15 seconds)
        if (gp.skills.swordSpinTimeOut != 0) {
            g2.drawImage(swordSpinImageUsed[gp.skills.swordSpinTimeOut / swordSpinImg],
                    gp.tileSize * (gp.maxScreenCol - 1), gp.tileSize / 3, gp.tileSize / 2, gp.tileSize / 2, null);
        } else {
            g2.drawImage(swordSpinImage, gp.tileSize * (gp.maxScreenCol - 1), gp.tileSize / 3, gp.tileSize / 2,
                    gp.tileSize / 2, null);
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

    // CURSOR
    public void changeCursor() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Point point = new Point(0, 0);
        Cursor cursor = toolkit.createCustomCursor(cursorImage, point, "Cursor");
        gp.setCursor(cursor);
    }

    // BOTTOM BAR
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

        fillTupeNum = (gp.player.playerXP % 920) / 230;

        switch (fillTupeNum) {
            case 0:
                tupeImg = (gp.player.playerXP % 230) / 10;

                g2.drawImage(xpTupe[tupeImg], 195, xpTupeY + 2, bottomBarHeight - 3, bottomBarHeight - 3, null);
                g2.drawImage(xpTupe[0], 225, xpTupeY + 2, bottomBarHeight - 3, bottomBarHeight - 3, null);
                g2.drawImage(xpTupe[0], 255, xpTupeY + 2, bottomBarHeight - 3, bottomBarHeight - 3, null);
                g2.drawImage(xpTupe[0], 285, xpTupeY + 2, bottomBarHeight - 3, bottomBarHeight - 3, null);
                break;
            case 1:
                tupeImg = (gp.player.playerXP % 230) / 10;

                g2.drawImage(xpTupe[xpTupe.length - 1], 195, xpTupeY + 2, bottomBarHeight - 3, bottomBarHeight - 3,
                        null);
                g2.drawImage(xpTupe[tupeImg], 225, xpTupeY + 2, bottomBarHeight - 3, bottomBarHeight - 3, null);
                g2.drawImage(xpTupe[0], 255, xpTupeY + 2, bottomBarHeight - 3, bottomBarHeight - 3, null);
                g2.drawImage(xpTupe[0], 285, xpTupeY + 2, bottomBarHeight - 3, bottomBarHeight - 3, null);
                break;
            case 2:
                tupeImg = (gp.player.playerXP % 230) / 10;

                g2.drawImage(xpTupe[xpTupe.length - 1], 195, xpTupeY + 2, bottomBarHeight - 3, bottomBarHeight - 3,
                        null);
                g2.drawImage(xpTupe[xpTupe.length - 1], 225, xpTupeY + 2, bottomBarHeight - 3, bottomBarHeight - 3,
                        null);
                g2.drawImage(xpTupe[tupeImg], 255, xpTupeY + 2, bottomBarHeight - 3, bottomBarHeight - 3, null);
                g2.drawImage(xpTupe[0], 285, xpTupeY + 2, bottomBarHeight - 3, bottomBarHeight - 3, null);
                break;
            case 3:
                tupeImg = (gp.player.playerXP % 230) / 10;

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

        // Bottom Bar
        g2.drawImage(bottomBar2, bottomBarX + 680, gp.screenHeight - bottomBarHeight, 375, bottomBarHeight, null);

        // Inventory Bar
        g2.drawImage(inventoryBar, bottomBarX + 1055, gp.screenHeight - bottomBarHeight, 163, bottomBarHeight, null);

    }

    // MESSAGES
    public void messages(Graphics2D g2) {
        if (messageOn) {
            g2.setFont(g2.getFont().deriveFont(20F));
            g2.drawString(message, gp.tileSize, gp.tileSize * 11);

            messageCounter++;

            if (messageCounter > 120) { // Because of FPS is 60. 120 means 2 seconds
                messageCounter = 0;
                messageOn = false;
            }
        }
    }

    // DAMAGE
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

    public void options_fullScreenNotification(int frameX, int frameY) {
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize * 3;

        currentDialogue = "The change will\ntake effect after \nrestarting the game.";

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
            }
        }

    }

    public void options_endGameConfirmation(int frameX, int frameY) {

        int textX = frameX + gp.tileSize - 32;
        int textY = frameY + gp.tileSize * 3;
        g2.setColor(Color.white);
        currentDialogue = "Quit the game and \nreturn to the title\nscreen?";

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
                // ANA EKRAN GEREKİYOR
                // gp.gameState = gp.titleS
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
                // ANA EKRAN GEREKİYOR
            }
        }

    }

    public void options_control(int frameX, int frameY) {

        int textX;
        int textY;

        // TITLE
        String text = "Control";
        textX = getXForCenteredText(g2, text);
        textY = frameY + gp.tileSize;
        g2.setColor(Color.WHITE);
        g2.drawString(text, textX, textY);

        frameX -= 32;

        textX = frameX + gp.tileSize;
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
        textX = frameX + gp.tileSize;
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

    // TRANSPARENCY
    public void changeAlpha(Graphics2D g2, float alphaValue) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }

}
