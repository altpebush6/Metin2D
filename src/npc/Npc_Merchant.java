package npc;

import java.util.ArrayList;

import entity.Entity;
import main.GamePanel;
import object.OBJ_BluePotion;
import object.OBJ_Dolunay;
import object.OBJ_KDP;
import object.OBJ_RedPotion;
import object.OBJ_SuPerisi;
import object.OBJ_GenisKilic;

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
        
        solidArea.x = 34;
        solidArea.y = 20;
        solidArea.width = 26;
        solidArea.height = 70;
        
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getNpcImage();
        setItems();
    }

    public void getNpcImage() {
        down1 = setup("/npc/merchant1", 96, 96);
        down2 = setup("/npc/merchant2", 96, 96);
        down3 = setup("/npc/merchant3", 96, 96);
        down4 = setup("/npc/merchant4", 96, 96);
    }

    public void setItems() {

        npcInventory.add(new OBJ_KDP(gp));
        npcInventory.add(new OBJ_GenisKilic(gp));
        npcInventory.add(new OBJ_Dolunay(gp));
        npcInventory.add(new OBJ_SuPerisi(gp));
        npcInventory.add(new OBJ_RedPotion(gp));
        npcInventory.add(new OBJ_BluePotion(gp));
    }

    public void speak() {

        super.speak();
        gp.gameState = gp.tradeState;

    }

}
