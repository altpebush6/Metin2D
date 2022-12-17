package entity;

import main.GamePanel;
import object.OBJ_EcelGetiren;
import object.OBJ_Staff;

public class Npc_Merchant extends Entity {

    public Npc_Merchant(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 0;
        type = npcType;
        level = 100;
        name = "Merchant";

        getNpcImage();

    }

    public void getNpcImage() {
        down1 = setup("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
        down3 = setup("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
    }

    public void setItems() {

        inventory.add(new OBJ_EcelGetiren(gp));
        inventory.add(new OBJ_Staff(gp));
        
    }

    public void speak() {

        super.speak();
        gp.gameState = gp.tradeState;
        
    }

}
