package entity;

import java.util.Random;

import main.GamePanel;

public class Npc_Blacksmith extends Entity{

    
    public Npc_Blacksmith(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;
        type = npcType;
        level = 100;
        name = "Guard";

        getNpcImage();

    }

    public void getNpcImage() {
        up1 = setup("/npc/oldman_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/npc/oldman_up_2", gp.tileSize, gp.tileSize);
        up3 = setup("/npc/oldman_up_1", gp.tileSize, gp.tileSize);

        down1 = setup("/npc/oldman_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/npc/oldman_down_2", gp.tileSize, gp.tileSize);
        down3 = setup("/npc/oldman_down_1", gp.tileSize, gp.tileSize);

        left1 = setup("/npc/oldman_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/npc/oldman_left_2", gp.tileSize, gp.tileSize);
        left3 = setup("/npc/oldman_left_1", gp.tileSize, gp.tileSize);

        right1 = setup("/npc/oldman_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/npc/oldman_right_2", gp.tileSize, gp.tileSize);
        right3 = setup("/npc/oldman_right_1", gp.tileSize, gp.tileSize);
    }

    public void setAction() {
        
        if (onPath) {
            
            solidArea.x = 0;
            solidArea.y = 0;
            solidArea.width = 48;
            solidArea.height = 47; 
            
            int goalCol = (gp.player.worldX + gp.player.solidArea.x) / gp.tileSize; // gp.player.worldX + gp.player.solidArea.x
            int goalRow = (gp.player.worldY + gp.player.solidArea.y) / gp.tileSize; // gp.player.worldY + gp.player.solidArea.y

            searchPath(goalCol, goalRow);
        } else {
            /*
                         npcActionCounter++;
    
            if (npcActionCounter == 120) {
                Random random = new Random();
                int i = random.nextInt(100) + 1; // pick up a number from 1 to 100
    
                if (i <= 25) {
                    direction = "up";
                }
                if (i > 25 && i <= 50) {
                    direction = "down";
                }
                if (i > 50 & i <= 75) {
                    direction = "left";
                }
                if (i > 75 && i <= 100) {
                    direction = "right";
                }
    
                npcActionCounter = 0;
    
            }
             */
        }

    }

    public void setDialogue(){

        dialogues[0] = "Hello";
        dialogues[1] = "welcome";
        dialogues[2] = "wizard";
        dialogues[3] = "sword";
    }

    public void speak(){
        gp.ui.currentDialogue = dialogues[0];
    }

}