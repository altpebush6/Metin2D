package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_SuPerisi extends Entity {

    public OBJ_SuPerisi(GamePanel gp) {

        super(gp);

        name = "Su Perisi";
        type = objectType;
        down1 = setup("/objects/suPerisi", gp.tileSize, gp.tileSize);
        price = 200;
        enchantLevel = 0;
        weaponAttackSize = 5;

    }

}
