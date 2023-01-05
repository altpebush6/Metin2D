package object;

import entity.Entity;
import main.GamePanel;

/**
 * <p>
 * This Class set the bluePotion and inherit from Entity class
 * </p>
 */

public class OBJ_BluePotion extends Entity{
    
    /**
     * <p>
     * this is constructor. Set name,type,image,price and details
     * </p>
     * 
     * @param gp is the game panel
     * @since 1.0
     */
    public OBJ_BluePotion(GamePanel gp) {
        
        super(gp);
        
        name = "Blue Potion";
        type = objectType;
        down1 = setup("/objects/bluePotion", gp.tileSize, gp.tileSize);
        price = 20;
        objDetailedType = 0;
        
    }
}
