package object;

import entity.Entity;
import main.GamePanel;

/**
 * <p>
 * This Class set the redPotion and inherit from Entity class
 * </p>
 */
public class OBJ_RedPotion extends Entity{
    
    /**
     * <p>
     * this is constructor. Set name,type,image,price and details
     * </p>
     * 
     * @param gp is the game panel
     * @since 1.0
     */
    public OBJ_RedPotion(GamePanel gp) {
        
        super(gp);
        
        name = "Red Potion";
        type = objectType;
        down1 = setup("/objects/redPotion", gp.tileSize, gp.tileSize);
        price = 20;
        objDetailedType = 0;
        
    }
}
