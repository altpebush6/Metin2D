package entity;

import main.GamePanel;

public class Skills {
    
    GamePanel gp;
    
    public int skillType;
    public boolean skillUsed = false;
    public int skillSpriteCounter = 0;
    public int skillStandbyTime = 120; // 10s

    public int swordSpinType = 2;
    public int swordSpinCounter = 0;
    public int swordSpinTimeOut = 0;
    public boolean swordSpinUsed = false;
    public int skillTimeOut = 75;
    
    public int increaseAmount;
    
    public Skills(GamePanel gp) {
        this.gp = gp;
    }
    
    public void useSkill(int skillType) {
        
        // Save the current worldX, worldY, solidArea
        int currentWorldX = gp.player.worldX;
        int currentWorldY = gp.player.worldY;
        int solidAreaWidth = gp.player.solidArea.width;
        int solidAreaHeight = gp.player.solidArea.height;
        
        
        increaseAmount = 3;
        if(skillSpriteCounter < increaseAmount) {
            gp.player.spriteNum = 1;
            gp.player.worldX += gp.player.attackArea.width;
        }else if(skillSpriteCounter < increaseAmount * 2) {
            gp.player.spriteNum = 2;
            gp.player.worldY -= gp.player.attackArea.height;
        }else if(skillSpriteCounter < increaseAmount * 3) {
            gp.player.spriteNum = 3;
            gp.player.worldX -= gp.player.attackArea.width;
        }else if(skillSpriteCounter < increaseAmount * 4) {
            gp.player.spriteNum = 4;
            gp.player.worldY += gp.player.attackArea.height;
        }else {
            gp.player.spriteNum = 1;
            skillSpriteCounter = 0;
        }
        skillSpriteCounter++;
        

        // Attack area becomes solidArea
        gp.player.solidArea.width = gp.player.attackArea.width;
        gp.player.solidArea.height = gp.player.attackArea.height;

        int enemyIndex = gp.collisionChecker.checkEntity(gp.player, gp.enemy);    // check enemy collision with the updated
                                                                                  // worldX, worldY and solidArea
        gp.player.damageEnemy(enemyIndex);

        gp.player.worldX = currentWorldX;
        gp.player.worldY = currentWorldY;
        gp.player.solidArea.width = solidAreaWidth;
        gp.player.solidArea.height = solidAreaHeight;
    
    }

    
}
