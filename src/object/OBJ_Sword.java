package object;

import entity.Entity;
import main.GamePanel;

/**
 * <p>
 * This Class set the  swords and inherit from Entity class
 * </p>
 */
public class OBJ_Sword extends Entity{
    
    /**
     * <p>
     * this is constructor. Set name,type,image, price ,enchantLevel, details and weapon attack size
     * </p>
     * 
     * @param gp is the game panel
     * @since 1.0
     */
    public OBJ_Sword(GamePanel gp) {
        
        super(gp);
        
        name = "Sword";
        type = objectType;
        down1 = setup("/objects/sword", gp.tileSize, gp.tileSize);
        price = 0;
        enchantLevel = 0;
        objDetailedType = 1;
        weaponAttackSize = 1;
    }
}
