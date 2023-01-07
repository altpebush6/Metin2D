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

/**
 * <p>
 * This Class determines the merchant NPC's property.
 * </p>
 */
public class Npc_Merchant extends Entity {

    GamePanel gp;
    /**
     * <p>
     * This array hold inventory
     * </p>
     */
    public static ArrayList<Entity> npcInventory = new ArrayList<>();

    /**
     * <p>
     * This is Constructor. The constructor assign variables. NPC's name, level etc:
     * </p>
     * 
     * @param gp is the game panel
     * @since 1.0
     */
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

    /**
     * <p>
     * This method sets the images of NPC. 4 images ensure the animation.
     * </p>
     */
    public void getNpcImage() {
        down1 = setup("/npc/merchant1", 96, 96);
        down2 = setup("/npc/merchant2", 96, 96);
        down3 = setup("/npc/merchant3", 96, 96);
        down4 = setup("/npc/merchant4", 96, 96);
    }

    /**
     * <p>
     * This method sets the NPC's items to sell.
     * </p>
     */
    public void setItems() {

        npcInventory.add(new OBJ_KDP(gp));
        npcInventory.add(new OBJ_GenisKilic(gp));
        npcInventory.add(new OBJ_Dolunay(gp));
        npcInventory.add(new OBJ_SuPerisi(gp));
        npcInventory.add(new OBJ_RedPotion(gp));
        npcInventory.add(new OBJ_BluePotion(gp));
    }

    /**
     * <p>
     * This method change the gameState.
     * </p>
     */
    public void speak() {
        super.speak();
        gp.gameState = gp.tradeState;

    }

}
