package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Coin extends SuperObject{
	
	GamePanel gp;
	
	public OBJ_Coin(int newValue, GamePanel gp) {
		
		this.gp = gp;
		
		name = "Coin";
		value = newValue;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/coin.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	
	}
}
