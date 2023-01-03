package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword extends Entity{

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
