package object;

import entity.Entity;
import main.GamePanel;

/**
 * <p>
 * This Class set the geniş sword and inherit from Entity class
 * </p>
 */
public class OBJ_GenisKilic extends Entity {
    
    /**
     * <p>
     * this is constructor. Set name,type,image, price ,enchantLevel, details and weapon attack size
     * </p>
     * 
     * @param gp is the game panel
     * @since 1.0
     */
    public OBJ_GenisKilic(GamePanel gp) {

        super(gp);

        name = "Geniş Kılıç";
        type = objectType;
        down1 = setup("/objects/genisKilic", gp.tileSize, gp.tileSize);
        price = 100;
        enchantLevel =0;
        objDetailedType = 1;
        weaponAttackSize = 2;

    }

}
