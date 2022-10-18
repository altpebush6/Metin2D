package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Coin extends SuperObject{
	
	
	public OBJ_Coin(int newValue) {
		
		name = "Coin";
		value = newValue;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/coin.png"));
		} catch (IOException e) {
			// TODO: handle exception
		}
		
	
	}
}
