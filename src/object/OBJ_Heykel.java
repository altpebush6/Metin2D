package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Heykel extends Entity{

    public OBJ_Heykel(GamePanel gp) {
		
		super(gp);
		
		name = "Heykel";
		type = objectType;
		down1 = setup("/objects/heykel", gp.tileSize*2, gp.tileSize*2);
		
	}
    
}
