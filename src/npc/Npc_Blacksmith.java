package npc;

import entity.Entity;
import main.GamePanel;
import java.lang.Math;

/**
 * <p>
 * This Class determines the Blacksmith NPC's property. The Blacksmith NPC make
 * the enchant for swords.
 * 
 * </p>
 */
public class Npc_Blacksmith extends Entity {

    GamePanel gp;

    /**
     * <p>
     * This is Constructor. The constructor assign variables. NPC's name, level etc:
     * </p>
     * 
     * @param gp is the game panel
     * @since 1.0
     */
    public Npc_Blacksmith(GamePanel gp) {
        super(gp);
        this.gp = gp;

        direction = "down";
        speed = 0;
        defaultSpeed = speed;
        type = npcType;
        level = 100;
        name = "BlackSmith";

        solidArea.x = 10;
        solidArea.y = 2;
        solidArea.width = 60;
        solidArea.height = 90;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getNpcImage();
        speak();
    }

    /**
     * <p>
     * This method sets the images of NPC. 4 images ensure the animation.
     * </p>
     */
    public void getNpcImage() {
        down1 = setup("/npc/blacksmith1", 96, 96);
        down2 = setup("/npc/blacksmith2", 96, 96);
        down3 = setup("/npc/blacksmith3", 96, 96);
        down4 = setup("/npc/blacksmith4", 96, 96);
    }

    /**
     * <p>
     * This method change the gameState.
     * </p>
     */
    public void speak() {
        super.speak();
        gp.gameState = gp.enchantState;
    }

    /**
     * <p>
     * This method determines the sword enchanting level increase or not. Gives 50%
     * chance.
     * </p>
     */
    public boolean increaseWeapon(Entity obj) {

        if (obj.enchantLevel >= 3) {
            gp.ui.addMessage("This weapon is already max level");
        } else {
            if (obj.objectType == 4) {
                if (obj.objDetailedType == 1) {

                    int max = 1, min = 0;
                    int range = max - min + 1;
                    int rand = (int) (Math.random() * range) + min;
                    if (rand == 0) {
                        gp.ui.addMessage("Weapon is not enhanced");
                        return false;

                    } else if (rand == 1) {
                        gp.ui.addMessage("Weapon is enhanced");
                        return true;
                    }
                } else {
                    gp.ui.addMessage("This item can not be enhanced");
                }
            }
        }

        return false;
    }

}
