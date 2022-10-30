package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class UI {

    GamePanel gp;
    Font arial_30;
    BufferedImage coinImage, dolunayImage, hpBarImage, emptyBarImage;

    public int healthBar;
    public int spBar;

    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public int itemIndex;

    public UI(GamePanel gp) {
        this.gp = gp;

        arial_30 = new Font("Arial", Font.PLAIN, 30);

        try {
            coinImage = ImageIO.read(getClass().getResourceAsStream("/objects/yang.png"));
            dolunayImage = ImageIO.read(getClass().getResourceAsStream("/objects/dolunayItem.png"));
            hpBarImage = ImageIO.read(getClass().getResourceAsStream("/UI/HpBar.png"));
            emptyBarImage = ImageIO.read(getClass().getResourceAsStream("/UI/emptyBar.png"));
        } catch (IOException e) {
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
        // spBar = gp.player.playerSp * 2;

        // Health Bar
        g2.setColor(Color.black);
        g2.drawImage(emptyBarImage, gp.tileSize / 3, gp.tileSize * (gp.maxScreenRow - 1), (int) barWidth, 15, null);
        g2.drawImage(hpBarImage, gp.tileSize / 3, gp.tileSize * (gp.maxScreenRow - 1), (int) healthBar, 15, null);

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
