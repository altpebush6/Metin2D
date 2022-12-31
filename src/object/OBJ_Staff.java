package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Staff extends Entity {

    public OBJ_Staff(GamePanel gp) {

        super(gp);

        name = "Staff";
        type = objectType;
        down1 = setup("/objects/staff", gp.tileSize, gp.tileSize);
        price = 500;
    }

}
