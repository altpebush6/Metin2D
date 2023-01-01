package npc;

import java.util.ArrayList;

import entity.Entity;
import main.GamePanel;
import object.OBJ_BluePotion;
import object.OBJ_Dolunay;
import object.OBJ_EcelGetiren;
import object.OBJ_RedPotion;
import object.OBJ_Staff;
import java.lang.Math;


public class Npc_Blacksmith extends Entity {

    GamePanel gp;

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

    public void getNpcImage() {
        down1 = setup("/npc/blacksmith1", 96, 96);
        down2 = setup("/npc/blacksmith2", 96, 96);
        down3 = setup("/npc/blacksmith3", 96, 96);
        down4 = setup("/npc/blacksmith4", 96, 96);
    }

    public void speak() {
        System.out.println("npc");
        super.speak();
        gp.gameState = gp.enchantState;

    }

    public void increaseWeapon(Entity obj) {
      
        if (obj.objectType == 4) {
            if(obj.objDetailedType == 1) {
                if(obj.enchantLevel == 0) {
                    int max = 1;
	                int min = 0;
	                int range = max - min + 1;
	                int rand = (int)(Math.random() * range) + min;
                    if(rand == 0) {
                        System.out.println("Maalesef başarısz oldu");
                    } else if(rand == 1) {
                        System.out.println("+ Basma işlemi başarılı. ");
                    }
                }
            }

        }


    }
    
}
