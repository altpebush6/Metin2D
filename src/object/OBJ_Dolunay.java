package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Dolunay extends Entity{

	public OBJ_Dolunay(GamePanel gp) {
		
		super(gp);
		
		name = "Dolunay";
		type = objectType;
		down1 = setup("/objects/dolunay", gp.tileSize, gp.tileSize);
		price = 100;
		enchantLevel = 0;
		objDetailedType = 1;
		weaponAttackSize = 4;
	}
}
