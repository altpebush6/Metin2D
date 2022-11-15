package main;

import java.awt.Cursor;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class UI {

    GamePanel gp;
    Font arial_30;
    BufferedImage coinImage, dolunayImage,hpBarImage, hpBarImage1,hpBarImage2,hpBarImage3,hpBarImage4,hpBarImage5,hpBarImage6,hpBarImage7,hpBarImage8, emptyBarImage, cursorImage;
    BufferedImage auroOfSwordImage, swordSpinImage;
    BufferedImage[] swordSpinImageUsed = new BufferedImage[20];
    BufferedImage xpTupeBg, dragonCoin, bottomBar;
    BufferedImage[] xpTupe = new BufferedImage[23];
    
    public int healthBar;
    public int spBar;
    
    public int hpBarCounter = 0;

    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public int itemIndex;
    
    public int fillTupeNum = 0;
    public int tupeImg;
    
    public UI(GamePanel gp) {
        this.gp = gp;

        arial_30 = new Font("Arial", Font.PLAIN, 30);

        coinImage           = gp.uTool.setup("/objects/yang", gp.tileSize, gp.tileSize);
        dolunayImage        = gp.uTool.setup("/objects/dolunayItem", gp.tileSize, gp.tileSize);
        hpBarImage1         = gp.uTool.setup("/UI/HpBar", gp.tileSize, gp.tileSize);
        hpBarImage2         = gp.uTool.setup("/UI/HpBar2", gp.tileSize, gp.tileSize);
        hpBarImage3         = gp.uTool.setup("/UI/HpBar3", gp.tileSize, gp.tileSize);
        hpBarImage4         = gp.uTool.setup("/UI/HpBar4", gp.tileSize, gp.tileSize);
        hpBarImage5         = gp.uTool.setup("/UI/HpBar5", gp.tileSize, gp.tileSize);
        hpBarImage6         = gp.uTool.setup("/UI/HpBar6", gp.tileSize, gp.tileSize);
        hpBarImage7         = gp.uTool.setup("/UI/HpBar7", gp.tileSize, gp.tileSize);
        hpBarImage8         = gp.uTool.setup("/UI/HpBar8", gp.tileSize, gp.tileSize);
        emptyBarImage       = gp.uTool.setup("/UI/emptyBar", gp.tileSize, gp.tileSize);
        cursorImage         = gp.uTool.setup("/UI/cursorImage", gp.tileSize, gp.tileSize);
        xpTupeBg            = gp.uTool.setup("/UI/xpTupeBg", gp.tileSize, gp.tileSize);
        bottomBar           = gp.uTool.setup("/UI/bottomBar", gp.tileSize, gp.tileSize);
        dragonCoin          = gp.uTool.setup("/UI/dragonCoin", gp.tileSize, gp.tileSize);
        auroOfSwordImage    = gp.uTool.setup("/skills/AuroOfSword", gp.tileSize, gp.tileSize);
        
        swordSpinImage      = gp.uTool.setup("/skills/Kılıç_Çevirme", gp.tileSize, gp.tileSize);
        for(int i = 0; i < swordSpinImageUsed.length; i++) {
            swordSpinImageUsed[i] = gp.uTool.setup("/skills/Kılıç_Çevirme"+(swordSpinImageUsed.length-i), gp.tileSize, gp.tileSize);
        }
        
        for(int i = 0; i < xpTupe.length; i++) {
            xpTupe[i] = gp.uTool.setup("/xpTupe/xpTupe"+(i+1), gp.tileSize, gp.tileSize);
        }
        
    }

    public void showMessage(String text) {

        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        double oneScale = (2.5 * gp.tileSize) / gp.player.maxLife;
        double healthBar = oneScale * gp.player.life;
        double barWidth = oneScale * gp.player.maxLife;
        int barHeight = 15;
        
        // spBar = gp.player.playerSp * 2;
        
        // Skills        
        g2.drawImage(swordSpinImage, gp.tileSize * (gp.maxScreenCol - 1), gp.tileSize / 3, gp.tileSize / 2, gp.tileSize / 2, null);
        
        int i = gp.skills.skillStandbyTime / 20; // 15 (per 15 seconds)
        
        if(gp.skills.swordSpinTimeOut != 0) {    
                g2.drawImage(swordSpinImageUsed[gp.skills.swordSpinTimeOut / i], gp.tileSize * (gp.maxScreenCol - 1), gp.tileSize / 3, gp.tileSize / 2, gp.tileSize / 2, null);   

        }
        
        // Dragon Coin
        g2.drawImage(dragonCoin, 0, gp.tileSize * (gp.maxScreenRow) - gp.tileSize / 2 - 10, gp.tileSize - 7, gp.tileSize / 2 + 10, null);

        // Bottom Bar
        int bottomBarX = gp.tileSize * 5 + 24;
        g2.drawImage(bottomBar, bottomBarX, gp.tileSize * (gp.maxScreenRow) - gp.tileSize / 2 - 5, gp.tileSize * 3, gp.tileSize / 2 + 10, null);
        g2.drawImage(bottomBar, bottomBarX + gp.tileSize * 3, gp.tileSize * (gp.maxScreenRow) - gp.tileSize / 2 - 5, gp.tileSize * 3, gp.tileSize / 2 + 10, null);
        g2.drawImage(bottomBar, bottomBarX + gp.tileSize * 6, gp.tileSize * (gp.maxScreenRow) - gp.tileSize / 2 - 5, gp.tileSize * 3, gp.tileSize / 2 + 10, null);

        // XP Tupes
        int xpTupeY = gp.tileSize * (gp.maxScreenRow) - gp.tileSize / 2;
        
        g2.drawImage(xpTupeBg, gp.tileSize * 7/2 - 5, xpTupeY - 5, gp.tileSize * 2 + 5, gp.tileSize / 2 + 5, null);

        fillTupeNum = (gp.player.playerXP % 920) / 230;
        
        switch (fillTupeNum) {
            case 0:
                tupeImg = (gp.player.playerXP % 230) / 10;

                g2.drawImage(xpTupe[tupeImg], gp.tileSize * 7/2, xpTupeY, gp.tileSize / 2, gp.tileSize / 2, null);   
                g2.drawImage(xpTupe[0], gp.tileSize * 8/2, xpTupeY, gp.tileSize / 2, gp.tileSize / 2, null);   
                g2.drawImage(xpTupe[0], gp.tileSize * 9/2, xpTupeY, gp.tileSize / 2, gp.tileSize / 2, null);   
                g2.drawImage(xpTupe[0], gp.tileSize * 10/2, xpTupeY, gp.tileSize / 2, gp.tileSize / 2, null);  
                break;
            case 1:
                tupeImg = (gp.player.playerXP % 230) / 10;

                g2.drawImage(xpTupe[xpTupe.length - 1], gp.tileSize * 7/2,xpTupeY, gp.tileSize / 2, gp.tileSize / 2, null);   
                g2.drawImage(xpTupe[tupeImg], gp.tileSize * 8/2, xpTupeY, gp.tileSize / 2, gp.tileSize / 2, null);   
                g2.drawImage(xpTupe[0], gp.tileSize * 9/2, xpTupeY, gp.tileSize / 2, gp.tileSize / 2, null);   
                g2.drawImage(xpTupe[0], gp.tileSize * 10/2, xpTupeY, gp.tileSize / 2, gp.tileSize / 2, null);  
                break;
            case 2:
                tupeImg = (gp.player.playerXP % 230) / 10;

                g2.drawImage(xpTupe[xpTupe.length - 1], gp.tileSize * 7/2,xpTupeY, gp.tileSize / 2, gp.tileSize / 2, null);   
                g2.drawImage(xpTupe[xpTupe.length - 1], gp.tileSize * 8/2, xpTupeY, gp.tileSize / 2, gp.tileSize / 2, null);   
                g2.drawImage(xpTupe[tupeImg], gp.tileSize * 9/2, xpTupeY, gp.tileSize / 2, gp.tileSize / 2, null);   
                g2.drawImage(xpTupe[0], gp.tileSize * 10/2, xpTupeY, gp.tileSize / 2, gp.tileSize / 2, null);  
                break;
            case 3:
                tupeImg = (gp.player.playerXP % 230) / 10;

                g2.drawImage(xpTupe[xpTupe.length - 1], gp.tileSize * 7/2, xpTupeY, gp.tileSize / 2, gp.tileSize / 2, null);   
                g2.drawImage(xpTupe[xpTupe.length - 1], gp.tileSize * 8/2, xpTupeY, gp.tileSize / 2, gp.tileSize / 2, null);   
                g2.drawImage(xpTupe[xpTupe.length - 1], gp.tileSize * 9/2, xpTupeY, gp.tileSize / 2, gp.tileSize / 2, null);   
                g2.drawImage(xpTupe[tupeImg], gp.tileSize * 10/2, xpTupeY, gp.tileSize / 2, gp.tileSize / 2, null);  
                break;
                
            default:
                g2.drawImage(xpTupe[xpTupe.length - 1], gp.tileSize * 7/2, xpTupeY, gp.tileSize / 2, gp.tileSize / 2, null);   
                g2.drawImage(xpTupe[xpTupe.length - 1], gp.tileSize * 8/2 , xpTupeY, gp.tileSize / 2, gp.tileSize / 2, null);   
                g2.drawImage(xpTupe[xpTupe.length - 1], gp.tileSize * 9/2, xpTupeY, gp.tileSize / 2, gp.tileSize / 2, null);   
                g2.drawImage(xpTupe[xpTupe.length - 1], gp.tileSize * 10/2, xpTupeY, gp.tileSize / 2, gp.tileSize / 2, null);  
                break;
        }
   
        // Change Cursor
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Point point = new Point(0,0);
        Cursor cursor = toolkit.createCustomCursor(cursorImage, point, "Cursor");
        gp.setCursor(cursor);
        
        // Empty Health Bar
        g2.setColor(Color.black);
        g2.drawImage(emptyBarImage, gp.tileSize / 3 + 25, xpTupeY - 4, (int) barWidth + 2, barHeight, null);
        g2.drawImage(emptyBarImage, gp.tileSize / 3 + 25, xpTupeY + barHeight - 4, (int) barWidth + 2, barHeight, null);

        hpBarCounter++;
        if(hpBarCounter < 15) {
            hpBarImage = hpBarImage1;
        }else if(hpBarCounter < 30) {
            hpBarImage = hpBarImage2;
        }else if(hpBarCounter < 45) {
            hpBarImage = hpBarImage3;
        }else if(hpBarCounter < 60) {
            hpBarImage = hpBarImage4;
        }else if(hpBarCounter < 75) {
            hpBarImage = hpBarImage5;
        }else if(hpBarCounter < 90) {
            hpBarImage = hpBarImage6;
        }else if(hpBarCounter < 105) {
            hpBarImage = hpBarImage7;
        }else if(hpBarCounter < 120) {
            hpBarImage = hpBarImage8;  
            hpBarCounter = 0;
        }
        
        // Health Bar
        g2.drawImage(hpBarImage, gp.tileSize / 3 + 25, xpTupeY - 4, (int) healthBar, barHeight, null);
        g2.drawImage(hpBarImage, gp.tileSize / 3 + 25, xpTupeY + barHeight - 4, (int) healthBar, barHeight, null);

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

        // Game State yapan kişi buradaki kodları alıp gp.playState if inin içine atsın

        g2.setFont(arial_30);
        g2.setColor(Color.white);

        // COIN
        g2.drawImage(coinImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
        g2.drawString(" " + gp.player.playerCoin, 60, 60);

        switch (itemIndex) {
            case 1:
                g2.drawImage(dolunayImage, 335, 550, gp.tileSize / 2, gp.tileSize / 2, null);
                break;
        }

        // MESSAGE
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

}
