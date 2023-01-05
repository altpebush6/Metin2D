package object;

import entity.Entity;
import main.GamePanel;

/**
 * <p>
 * This Class set the KDP sword and inherit from Entity class
 * </p>
 */
public class OBJ_KDP extends Entity {
    
    /**
     * <p>
     * this is constructor. Set name,type,image, price ,enchantLevel, details and weapon attack size
     * </p>
     * 
     * @param gp is the game panel
     * @since 1.0
     */
    public OBJ_KDP(GamePanel gp) {
		
		super(gp);
		
		name = "Kırmızı Demir Pala";
		type = objectType;
		down1 = setup("/objects/kdp", gp.tileSize, gp.tileSize);
		price = 150;
		enchantLevel = 0;
		objDetailedType = 1;
	    weaponAttackSize = 3;
	}
    
}
