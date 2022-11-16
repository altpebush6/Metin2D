package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Coin extends Entity{
	
	public OBJ_Coin(GamePanel gp, int newValue) {
		
		super(gp);
		
		name = "Coin";
		type = 3;
		down1 = setup("/objects/coin", gp.tileSize, gp.tileSize);
		coinValue = newValue;
		
	}
}
