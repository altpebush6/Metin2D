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
	BufferedImage coinImage, dolunayImage;
	
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
		} catch (IOException e) {}
	}
	
	public void showMessage(String text) {
		
		message = text;
		messageOn = true;
	}
	
	public void draw(Graphics2D g2) {
		
		g2.setFont(arial_30);
		g2.setColor(Color.white);
		
		// COIN
		g2.drawImage(coinImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize,null);
		g2.drawString(" "+gp.player.playerCoin, 60, 60);

		switch(itemIndex) {
			case 1:
				g2.drawImage(dolunayImage, 335, 550, gp.tileSize/2, gp.tileSize/2,null);
				break;
		}

		// MESSAGE
		if(messageOn) {
			g2.setFont(g2.getFont().deriveFont(20F));
			g2.drawString(message, gp.tileSize, gp.tileSize * 11);
			
			messageCounter++;
			
			if(messageCounter > 120) { // Because of FPS is 60. 120 means 2 seconds
				messageCounter = 0;
				messageOn = false;
			}
			
		}
	}
	
}
