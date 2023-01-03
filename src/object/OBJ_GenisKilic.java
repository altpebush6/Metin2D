package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_GenisKilic extends Entity {

    public OBJ_GenisKilic(GamePanel gp) {

        super(gp);

        name = "Geniş Kılıç";
        type = objectType;
        down1 = setup("/objects/genisKilic", gp.tileSize, gp.tileSize);
        price = 100;
        enchantLevel =0;
        objDetailedType = 1;
        weaponAttackSize = 2;

    }

}
