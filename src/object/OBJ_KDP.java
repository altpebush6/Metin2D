package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_KDP extends Entity {

    public OBJ_KDP(GamePanel gp) {
		
		super(gp);
		
		name = "Kırmızı Demir Pala";
		type = objectType;
		down1 = setup("/objects/kdp", gp.tileSize, gp.tileSize);
		price = 150;
		enchantLevel = 0;
		objDetailedType = 1;
	    weaponAttackSize = 3;
	}
    
}
