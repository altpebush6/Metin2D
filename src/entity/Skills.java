package entity;

import main.GamePanel;

public class Skills {
    
    GamePanel gp;
    
    public int skillType;
    public boolean skillUsed = false;
    public int skillSpriteCounter = 0;
    public int skillStandbyTime = 120; // 10s

    // Sword Spin
    public int swordSpinType = 1;
    public int swordSpinCounter = 0;
    public int swordSpinTimeOut = 0;
    public int swordSpinDuration = 75;
    public boolean swordSpinUsed = false;
    
    // Aura of the Sword
    public int auraSwordType = 2;
    public int auraSwordCounter = 0;
    public int auraSwordTimeOut = 0;
    public int auraSwordDuration = 600;
    public int auraSwordDrawDuration = 60;
    public boolean auraSwordActive = false;
    
    public int increaseAmount;
    
    public Skills(GamePanel gp) {
        this.gp = gp;
    }
    
    public void swordSpin() {
        if (swordSpinUsed && swordSpinTimeOut == 0) {
            
            swordSpinCounter++;
            
            if (swordSpinCounter > 20) {
                
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
                
                skillUsed = true;
            }

            if (swordSpinCounter == swordSpinDuration) {
                swordSpinCounter = 0;
                swordSpinUsed = false;
                skillUsed = false;
                gp.player.spriteNum = 1;
                swordSpinTimeOut++;
            }
        }
    }
    
    public void auraOfTheSword() {
        if (auraSwordActive && auraSwordTimeOut == 0) {         // if pressed F1 enter
            auraSwordCounter++;
            
            if(auraSwordCounter == auraSwordDrawDuration) {     // draw aura sword animation for auraSwordDrawDuration milliseconds
                skillUsed = false;
            }

            if (auraSwordCounter == auraSwordDuration) {        // deactivate aura sword after auraSwordDuration milliseconds
                auraSwordCounter = 0;
                auraSwordActive = false;
                auraSwordTimeOut++;
                gp.player.attackPower -= 10;
            }
        }
    }
    
    public void drawSwordSpin() {
        switch(gp.player.spriteNum) {
            case 1: gp.player.image = gp.player.attackRight2;   break;
            case 2: gp.player.image = gp.player.attackUp2;      gp.player.tempScreenY = gp.player.screenY - gp.tileSize;    break;
            case 3: gp.player.image = gp.player.attackLeft2;    gp.player.tempScreenX = gp.player.screenX - gp.tileSize;    break;
            case 4: gp.player.image = gp.player.attackDown2;    break;
        }
    }
    
    public void drawAuraSword() {
        switch(gp.player.direction) {
            case "up":  case "upleft":  case "upright":     gp.player.image = gp.player.auraSwordUp2;  gp.player.tempScreenY = gp.player.screenY - gp.tileSize; break;
            case "down":case "downleft":case "downright":   gp.player.image = gp.player.auraSwordDown2;    break;
            case "left":    gp.player.image = gp.player.auraSwordLeft2; gp.player.tempScreenX = gp.player.screenX - gp.tileSize;   break;
            case "right":   gp.player.image = gp.player.auraSwordRight2;   break;
        }
    }
    
    public void increaseTimeouts() {
        if (gp.skills.swordSpinTimeOut != 0) {
            gp.skills.swordSpinTimeOut++;
        }
        if(gp.skills.auraSwordTimeOut != 0) {
            gp.skills.auraSwordTimeOut++;
        }
    }
    
    public void resetSkills() {
        if (gp.skills.swordSpinTimeOut == gp.skills.skillStandbyTime) {
            gp.skills.swordSpinTimeOut = 0;
        }
        if (gp.skills.auraSwordTimeOut == gp.skills.skillStandbyTime) {
            gp.skills.auraSwordTimeOut = 0;
        }
    }
    
}
