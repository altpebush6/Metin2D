package object;

import entity.Entity;
import main.GamePanel;

/**
 * <p>
 * This Class set the dead wolf object and inherit from Entity class
 * </p>
 */
public class OBJ_DeadWolf extends Entity{
    
    /**
     * <p>
     * this is constructor. Set name,type,image and details
     * </p>
     * 
     * @param gp is the game panel
     * @since 1.0
     */
    public OBJ_DeadWolf(GamePanel gp) {
        
        super(gp);
        
        name = "deadWolf";
        deadObj = true;
        type = objectType;
        down1 = setup("/wolf/deadWolf", gp.tileSize, gp.tileSize);
        objDetailedType = 0;
    }
}
