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
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font arial_30, damageFont;
    BufferedImage coinImage, dolunayImage, spBarImage, hpBarImage, hpBarImage1, hpBarImage2, hpBarImage3, hpBarImage4,
            hpBarImage5, hpBarImage6, hpBarImage7, hpBarImage8, emptyBarImage, cursorImage;
    BufferedImage auroOfSwordImage, swordSpinImage, dialogueUI;
    BufferedImage[] swordSpinImageUsed = new BufferedImage[20];
    BufferedImage xpTupeBg, dragonCoin, bottomBar, itemSkillBar;
    BufferedImage[] xpTupe = new BufferedImage[23];

    public Rectangle respawnHereRec, respawnCityRec;

    public int healthBar;
    public int spBar;

    public int hpBarCounter = 0;

    public ArrayList<Damages> damages = new ArrayList<>();

    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public int itemIndex;

    public String currentDialogue = " ";
    
    public int slotCol = 0;
    public int slotRow = 0;

    public int fillTupeNum = 0;
    public int tupeImg;

    public UI(GamePanel gp) {
        this.gp = gp;

        arial_30 = new Font("Arial", Font.PLAIN, 30);

        coinImage = gp.uTool.setup("/objects/yang", gp.tileSize, gp.tileSize);
        dolunayImage = gp.uTool.setup("/objects/dolunayItem", gp.tileSize, gp.tileSize);
        hpBarImage1 = gp.uTool.setup("/UI/HpBar", gp.tileSize, gp.tileSize);
        hpBarImage2 = gp.uTool.setup("/UI/HpBar2", gp.tileSize, gp.tileSize);
        hpBarImage3 = gp.uTool.setup("/UI/HpBar3", gp.tileSize, gp.tileSize);
        hpBarImage4 = gp.uTool.setup("/UI/HpBar4", gp.tileSize, gp.tileSize);
        hpBarImage5 = gp.uTool.setup("/UI/HpBar5", gp.tileSize, gp.tileSize);
        hpBarImage6 = gp.uTool.setup("/UI/HpBar6", gp.tileSize, gp.tileSize);
        hpBarImage7 = gp.uTool.setup("/UI/HpBar7", gp.tileSize, gp.tileSize);
        hpBarImage8 = gp.uTool.setup("/UI/HpBar8", gp.tileSize, gp.tileSize);
        emptyBarImage = gp.uTool.setup("/UI/emptyBar", gp.tileSize, gp.tileSize);
        spBarImage = gp.uTool.setup("/UI/SpBar", gp.tileSize, gp.tileSize);
        cursorImage = gp.uTool.setup("/UI/cursorImage", gp.tileSize, gp.tileSize);
        xpTupeBg = gp.uTool.setup("/UI/xpTupeBg", gp.tileSize, gp.tileSize);
        bottomBar = gp.uTool.setup("/UI/bottomBar", gp.tileSize, gp.tileSize);
        itemSkillBar = gp.uTool.setup("/UI/itemSkillBar", gp.tileSize, gp.tileSize);
        dragonCoin = gp.uTool.setup("/UI/dragonCoin", gp.tileSize, gp.tileSize);
        auroOfSwordImage = gp.uTool.setup("/skills/AuroOfSword", gp.tileSize, gp.tileSize);
        // dialogueUI = gp.uTool.setup("/resources/UI/dialogueUI", gp.tileSize,
        // gp.tileSize);

        swordSpinImage = gp.uTool.setup("/skills/Kılıç_Çevirme", gp.tileSize, gp.tileSize);
        for (int i = 0; i < swordSpinImageUsed.length; i++) {
            swordSpinImageUsed[i] = gp.uTool.setup("/skills/Kılıç_Çevirme" + (swordSpinImageUsed.length - i),
                    gp.tileSize, gp.tileSize);
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
            skills(g2);

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
            g2.drawImage(coinImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
            g2.drawString(" " + gp.player.playerCoin, 60, 60);
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
            drawInventory();
        }

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
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenHeight - (gp.tileSize * 4);
        int height = gp.tileSize * 5;
        drawSubWindow(x, y, width, height);

        Color c2 = new Color(255, 255, 255);
        g2.setColor(c2);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        x += gp.tileSize;
        y += gp.tileSize;
        g2.drawString("Press Enter to Exit", x, y);
    }

    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 100);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        g2.drawImage(dialogueUI, null, x, y);
    }

    // INVENTORY METHOD
    public void drawInventory() {
        
        // FRAME
        int inventoryX = gp.tileSize*9;
        int inventoryY = gp.tileSize;
        int inventoryWidth = gp.tileSize*6;
        int inventoryHeight = gp.tileSize*5;
        drawSubWindow(inventoryX, inventoryY, inventoryWidth, inventoryHeight);
    
        // SLOT
        final int slotXstart = inventoryX + 20;
        final int slotYstart = inventoryY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;

        // DRAW PLAYER'S ITEM
       
        for(int i = 0; i< gp.player.inventory.size(); i++){
            g2.drawImage(gp.player.inventory.get(i).down1, slotX, slotY, null);
            slotX += gp.tileSize;
            if(i==4 || i == 9 || i == 14){
                slotX = slotXstart;
                slotY += gp.tileSize;
            }
        }
       


        // CURSOR
        int cursorX = slotXstart + (gp.tileSize * slotCol);
        int cursorY = slotYstart + (gp.tileSize * slotRow);
        int cursorWidth = gp.tileSize;
        int cursorHeight = gp.tileSize;
        // DRAW CURSOR
        g2.setColor(Color.white);
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 35, 35);
    
    
    }

    // DEAD MENU
    public void respawnButtons(Graphics2D g2) {
        String respawnHere = "Restart Here";
        String respawnCity = "Restart in the City";

        g2.setFont(g2.getFont().deriveFont(20F));
        g2.setColor(Color.white);
        g2.drawString(respawnHere, gp.tileSize, gp.tileSize);
        g2.drawString(respawnCity, gp.tileSize, gp.tileSize * 2);

        int respawnHereWidth = (int) g2.getFontMetrics().getStringBounds(respawnHere, g2).getWidth();
        int respawnHereHeight = (int) g2.getFontMetrics().getStringBounds(respawnHere, g2).getHeight();
        respawnHereRec = new Rectangle(gp.tileSize - 20, gp.tileSize - 20, respawnHereWidth + 30,
                respawnHereHeight + 10);

        int respawnCityWidth = (int) g2.getFontMetrics().getStringBounds(respawnCity, g2).getWidth();
        int respawnCityHeight = (int) g2.getFontMetrics().getStringBounds(respawnCity, g2).getHeight();
        respawnCityRec = new Rectangle(gp.tileSize - 20, gp.tileSize * 2 - 20, respawnCityWidth + 30,
                respawnCityHeight + 10);

        g2.drawRect(respawnHereRec.x, respawnHereRec.y, respawnHereRec.width, respawnHereRec.height);
        g2.drawRect(respawnCityRec.x, respawnCityRec.y, respawnCityRec.width, respawnCityRec.height);
    }

    // CENTERED TEXT
    public int getXForCenteredText(Graphics2D g2, String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth / 2 - length / 2;
    }

    // SKILLS
    public void skills(Graphics2D g2) {
        g2.drawImage(swordSpinImage, gp.tileSize * (gp.maxScreenCol - 1), gp.tileSize / 3, gp.tileSize / 2,
                gp.tileSize / 2, null);

        int i = gp.skills.skillStandbyTime / 20; // 15 (per 15 seconds)

        if (gp.skills.swordSpinTimeOut != 0) {
            g2.drawImage(swordSpinImageUsed[gp.skills.swordSpinTimeOut / i], gp.tileSize * (gp.maxScreenCol - 1),
                    gp.tileSize / 3, gp.tileSize / 2, gp.tileSize / 2, null);

        }
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
        double oneScale = (2.5 * gp.tileSize) / gp.player.maxLife;
        double healthBar = oneScale * gp.player.life;
        double barWidth = oneScale * gp.player.maxLife;
        int barHeight = 15;

        // spBar = gp.player.playerSp * 2;

        // Dragon Coin
        g2.drawImage(dragonCoin, 0, gp.tileSize * (gp.maxScreenRow) - gp.tileSize / 2 - 10, gp.tileSize - 7,
                gp.tileSize / 2 + 10, null);

        // Bottom Bar
        int bottomBarX = gp.tileSize * 5 + 24;
        g2.drawImage(bottomBar, bottomBarX, gp.tileSize * (gp.maxScreenRow) - gp.tileSize / 2 - 5, gp.tileSize * 3,
                gp.tileSize / 2 + 10, null);
        g2.drawImage(bottomBar, bottomBarX + gp.tileSize * 3, gp.tileSize * (gp.maxScreenRow) - gp.tileSize / 2 - 5,
                gp.tileSize * 3, gp.tileSize / 2 + 10, null);

        // XP Tupes
        int xpTupeY = gp.tileSize * (gp.maxScreenRow) - gp.tileSize / 2;

        g2.drawImage(xpTupeBg, gp.tileSize * 7 / 2 - 5, xpTupeY - 5, gp.tileSize * 2 + 5, gp.tileSize / 2 + 5, null);

        fillTupeNum = (gp.player.playerXP % 920) / 230;

        switch (fillTupeNum) {
            case 0:
                tupeImg = (gp.player.playerXP % 230) / 10;

                g2.drawImage(xpTupe[tupeImg], gp.tileSize * 7 / 2, xpTupeY, gp.tileSize / 2, gp.tileSize / 2, null);
                g2.drawImage(xpTupe[0], gp.tileSize * 8 / 2, xpTupeY, gp.tileSize / 2, gp.tileSize / 2, null);
                g2.drawImage(xpTupe[0], gp.tileSize * 9 / 2, xpTupeY, gp.tileSize / 2, gp.tileSize / 2, null);
                g2.drawImage(xpTupe[0], gp.tileSize * 10 / 2, xpTupeY, gp.tileSize / 2, gp.tileSize / 2, null);
                break;
            case 1:
                tupeImg = (gp.player.playerXP % 230) / 10;

                g2.drawImage(xpTupe[xpTupe.length - 1], gp.tileSize * 7 / 2, xpTupeY, gp.tileSize / 2, gp.tileSize / 2,
                        null);
                g2.drawImage(xpTupe[tupeImg], gp.tileSize * 8 / 2, xpTupeY, gp.tileSize / 2, gp.tileSize / 2, null);
                g2.drawImage(xpTupe[0], gp.tileSize * 9 / 2, xpTupeY, gp.tileSize / 2, gp.tileSize / 2, null);
                g2.drawImage(xpTupe[0], gp.tileSize * 10 / 2, xpTupeY, gp.tileSize / 2, gp.tileSize / 2, null);
                break;
            case 2:
                tupeImg = (gp.player.playerXP % 230) / 10;

                g2.drawImage(xpTupe[xpTupe.length - 1], gp.tileSize * 7 / 2, xpTupeY, gp.tileSize / 2, gp.tileSize / 2,
                        null);
                g2.drawImage(xpTupe[xpTupe.length - 1], gp.tileSize * 8 / 2, xpTupeY, gp.tileSize / 2, gp.tileSize / 2,
                        null);
                g2.drawImage(xpTupe[tupeImg], gp.tileSize * 9 / 2, xpTupeY, gp.tileSize / 2, gp.tileSize / 2, null);
                g2.drawImage(xpTupe[0], gp.tileSize * 10 / 2, xpTupeY, gp.tileSize / 2, gp.tileSize / 2, null);
                break;
            case 3:
                tupeImg = (gp.player.playerXP % 230) / 10;

                g2.drawImage(xpTupe[xpTupe.length - 1], gp.tileSize * 7 / 2, xpTupeY, gp.tileSize / 2, gp.tileSize / 2,
                        null);
                g2.drawImage(xpTupe[xpTupe.length - 1], gp.tileSize * 8 / 2, xpTupeY, gp.tileSize / 2, gp.tileSize / 2,
                        null);
                g2.drawImage(xpTupe[xpTupe.length - 1], gp.tileSize * 9 / 2, xpTupeY, gp.tileSize / 2, gp.tileSize / 2,
                        null);
                g2.drawImage(xpTupe[tupeImg], gp.tileSize * 10 / 2, xpTupeY, gp.tileSize / 2, gp.tileSize / 2, null);
                break;

            default:
                g2.drawImage(xpTupe[xpTupe.length - 1], gp.tileSize * 7 / 2, xpTupeY, gp.tileSize / 2, gp.tileSize / 2,
                        null);
                g2.drawImage(xpTupe[xpTupe.length - 1], gp.tileSize * 8 / 2, xpTupeY, gp.tileSize / 2, gp.tileSize / 2,
                        null);
                g2.drawImage(xpTupe[xpTupe.length - 1], gp.tileSize * 9 / 2, xpTupeY, gp.tileSize / 2, gp.tileSize / 2,
                        null);
                g2.drawImage(xpTupe[xpTupe.length - 1], gp.tileSize * 10 / 2, xpTupeY, gp.tileSize / 2, gp.tileSize / 2,
                        null);
                break;
        }

        // Item-Skill Bar
        g2.drawImage(itemSkillBar, bottomBarX + gp.tileSize * 6, gp.tileSize * (gp.maxScreenRow) - gp.tileSize / 2 - 5,
                30, 30, null);

        // Empty Health Bar
        g2.setColor(Color.black);
        g2.drawImage(emptyBarImage, gp.tileSize / 3 + 25, xpTupeY - 4, (int) barWidth + 2, barHeight, null);

        // Empty Sp Bar
        g2.drawImage(emptyBarImage, gp.tileSize / 3 + 25, xpTupeY + barHeight - 4, (int) barWidth + 2, barHeight, null);

        hpBarCounter++;
        if (hpBarCounter < 15) {
            hpBarImage = hpBarImage1;
        } else if (hpBarCounter < 30) {
            hpBarImage = hpBarImage2;
        } else if (hpBarCounter < 45) {
            hpBarImage = hpBarImage3;
        } else if (hpBarCounter < 60) {
            hpBarImage = hpBarImage4;
        } else if (hpBarCounter < 75) {
            hpBarImage = hpBarImage5;
        } else if (hpBarCounter < 90) {
            hpBarImage = hpBarImage6;
        } else if (hpBarCounter < 105) {
            hpBarImage = hpBarImage7;
        } else if (hpBarCounter < 120) {
            hpBarImage = hpBarImage8;
            hpBarCounter = 0;
        }

        // Health Bar
        g2.drawImage(hpBarImage, gp.tileSize / 3 + 25, xpTupeY - 4, (int) healthBar, barHeight, null);

        // Sp Bar
        g2.drawImage(spBarImage, gp.tileSize / 3 + 25, xpTupeY + barHeight - 4, (int) healthBar, barHeight, null);

        // Sp Bar
        /*
         * g2.setColor(Color.black);
         * g2.drawRoundRect(gp.tileSize / 3, gp.tileSize * (gp.maxScreenRow - 1) +
         * (gp.tileSize / 2),
         * gp.player.maxPlayerSp * (101 / 100) * 2, 15, 20, 20);
         * g2.setColor(Color.blue);
         * g2.fillRoundRect(gp.tileSize / 3, gp.tileSize * (gp.maxScreenRow - 1) +
         * (gp.tileSize / 2), spBar, 15, 20, 20);
         */
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

    // TRANSPARENCY
    public void changeAlpha(Graphics2D g2, float alphaValue) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }

}
