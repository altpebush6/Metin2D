package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_DeadWolf extends Entity{
    
    public OBJ_DeadWolf(GamePanel gp) {
        
        super(gp);
        
        name = "deadWolf";
        deadObj = true;
        type = 3;
        down1 = setup("/wolf/deadWolf", gp.tileSize, gp.tileSize);
        
    }
}
