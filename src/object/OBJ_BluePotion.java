package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_BluePotion extends Entity{

    public OBJ_BluePotion(GamePanel gp) {
        
        super(gp);
        
        name = "Blue Potion";
        type = objectType;
        down1 = setup("/objects/bluePotion", gp.tileSize, gp.tileSize);
        price = 20;
        objDetailedType = 0;
        
    }
}
