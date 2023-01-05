package object;

import entity.Entity;
import main.GamePanel;

/**
 * <p>
 * This Class set the su perisi sword and inherit from Entity class
 * </p>
 */
public class OBJ_SuPerisi extends Entity {
    
    /**
     * <p>
     * this is constructor. Set name,type,image, price ,enchantLevel, details and weapon attack size
     * </p>
     * 
     * @param gp is the game panel
     * @since 1.0
     */
    public OBJ_SuPerisi(GamePanel gp) {

        super(gp);

        name = "Su Perisi";
        type = objectType;
        down1 = setup("/objects/suPerisi", gp.tileSize, gp.tileSize);
        price = 200;
        enchantLevel = 0;
        weaponAttackSize = 5;

    }

}
