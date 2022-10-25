package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Coin extends Entity{
	
	public OBJ_Coin(GamePanel gp, int newValue) {
		
		super(gp);
		
		name = "Coin";
		down1 = setup("/objects/coin");
		coinValue = newValue;
		
	}
}
