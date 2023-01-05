package object;

import entity.Entity;
import main.GamePanel;

/**
 * <p>
 * This Class set the dolunay sword object and inherit from Entity class
 * </p>
 */
public class OBJ_Dolunay extends Entity{
    
    /**
     * <p>
     * this is constructor. Set name,type,image, price ,enchantLevel, details and weapon attack size
     * </p>
     * 
     * @param gp is the game panel
     * @since 1.0
     */
	public OBJ_Dolunay(GamePanel gp) {
		
		super(gp);
		
		name = "Dolunay";
		type = objectType;
		down1 = setup("/objects/dolunay", gp.tileSize, gp.tileSize);
		price = 100;
		enchantLevel = 0;
		objDetailedType = 1;
		weaponAttackSize = 4;
	}
}
