package object;

import entity.Entity;
import main.GamePanel;

/**
 * <p>
 * This Class set the coin object and inherit from Entity class
 * </p>
 */
public class OBJ_Coin extends Entity{
	

    /**
     * <p>
     * this is constructor. Set name,type,image,price and details
     * </p>
     * 
     * @param gp is the game panel
     * @param newValue set coin  new value 
     * @since 1.0
     */
	public OBJ_Coin(GamePanel gp, int newValue) {
		
		super(gp);
		
		name = "Coin";
		type = objectType;
		down1 = setup("/objects/coin", gp.tileSize, gp.tileSize);
		coinValue = newValue;
		objDetailedType = 0;
	}
}
