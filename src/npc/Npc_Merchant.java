package npc;

import java.util.ArrayList;

import entity.Entity;
import main.GamePanel;
import object.OBJ_BluePotion;
import object.OBJ_Dolunay;
import object.OBJ_EcelGetiren;
import object.OBJ_RedPotion;
import object.OBJ_Staff;

public class Npc_Merchant extends Entity {
    
    GamePanel gp;
    public static ArrayList<Entity> npcInventory = new ArrayList<>();

    public Npc_Merchant(GamePanel gp) {
        super(gp);
        this.gp = gp;

        direction = "down";
        speed = 0;
        defaultSpeed = speed;
        type = npcType;
        level = 100;
        name = "Merchant";

        getNpcImage();
        setItems();
    }

    public void getNpcImage() {
        down1 = setup("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
        down3 = setup("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
    }

    public void setItems() {

        npcInventory.add(new OBJ_EcelGetiren(gp));
        npcInventory.add(new OBJ_Staff(gp));
        npcInventory.add(new OBJ_Dolunay(gp));
        npcInventory.add(new OBJ_RedPotion(gp));
        npcInventory.add(new OBJ_BluePotion(gp));
    }

    public void speak() {

        super.speak();
        gp.gameState = gp.tradeState;

    }

}
