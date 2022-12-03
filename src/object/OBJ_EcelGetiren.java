package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_EcelGetiren extends Entity {

    public OBJ_EcelGetiren(GamePanel gp) {
		
		super(gp);
		
		name = "EcelGetiren";
		type = objectType;
		down1 = setup("/objects/ecelGetiren", gp.tileSize, gp.tileSize);
	}
    
}
