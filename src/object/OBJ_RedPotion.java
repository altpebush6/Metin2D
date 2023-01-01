package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_RedPotion extends Entity{

    public OBJ_RedPotion(GamePanel gp) {
        
        super(gp);
        
        name = "Red Potion";
        type = objectType;
        down1 = setup("/objects/redPotion", gp.tileSize, gp.tileSize);
        price = 20;
        objDetailedType = 0;
        
    }
}
