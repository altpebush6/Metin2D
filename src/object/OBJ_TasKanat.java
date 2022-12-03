package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_TasKanat extends Entity {

    public OBJ_TasKanat(GamePanel gp) {

        super(gp);

        name = "TasKanat";
        type = 3;
        down1 = setup("/objects/tasKanat", gp.tileSize, gp.tileSize);
    }

}
