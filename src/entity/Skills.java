package entity;

import main.GamePanel;
/**
 * <p>
 * This Class sets skills
 * </p>
 */
public class Skills {
    /**
     * <p>
     * This field is instance of GamePanel class 
     * </p>
     */
    GamePanel gp;
    /**
     * <p>
     * This field stores type of skill
     * </p>
     */
    public int skillType;
    /**
     * <p>
     * This field stores whether skill is used or not
     * </p>
     */
    public boolean skillUsed = false;
    /**
     * <p>
     * This field stores sprite counter of skill
     * </p>
     */
    public int skillSpriteCounter = 0;
    /**
     * <p>
     * This field stores standby time of skill
     * </p>
     */
    public int skillStandbyTime = 120; // 10s

    // Sword Spin
    /**
     * <p>
     * This field stores sword spin type
     * </p>
     */
    public int swordSpinType = 1;
    /**
     * <p>
     * This field stores sword spin counter
     * </p>
     */
    public int swordSpinCounter = 0;
    /**
     * <p>
     * This field stores sword spin time out
     * </p>
     */
    public int swordSpinTimeOut = 0;
    /**
     * <p>
     * This field stores sword spin duration
     * </p>
     */
    public int swordSpinDuration = 75;
    /**
     * <p>
     * This field stores whether sword spin is used or not
     * </p>
     */
    public boolean swordSpinUsed = false;
    
    // Aura of the Sword
    /**
     * <p>
     * This field stores aura sword type
     * </p>
     */
    public int auraSwordType = 2;
    /**
     * <p>
     * This field stores aura sword counter
     * </p>
     */
    public int auraSwordCounter = 0;
    /**
     * <p>
     * This field stores aura sword time out
     * </p>
     */
    public int auraSwordTimeOut = 0;
    /**
     * <p>
     * This field stores aura sword duration
     * </p>
     */
    public int auraSwordDuration = 600;
    /**
     * <p>
     * This field stores aura sword draw duration
     * </p>
     */
    public int auraSwordDrawDuration = 60;
    /**
     * <p>
     * This field stores whether aura sword is active or not
     * </p>
     */
    public boolean auraSwordActive = false;
    /**
     * <p>
     * This field stores increase amount
     * </p>
     */
    public int increaseAmount;
    /**
     * <p>
     * This is constructor 
     * </p>
     * @param gp instance of game panel class
     */
    public Skills(GamePanel gp) {
        this.gp = gp;
    }
    /**
     * <p>
     * This method sets sword how to spin
     * </p>
     */
    public void swordSpin() {
        if (swordSpinUsed && swordSpinTimeOut == 0) {
            
            swordSpinCounter++;
            
            if (swordSpinCounter > 20) {
                
                // Save the current worldX, worldY, solidArea
                int currentWorldX = gp.player.worldX;
                int currentWorldY = gp.player.worldY;
                int solidAreaWidth = gp.player.solidArea.width;
                int solidAreaHeight = gp.player.solidArea.height;
                
                increaseAmount = 2;
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
  
    /**
     * <p>
     * This method sets behavior of aura sword
     * </p>
     */
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
                    gp.player.setAttackPower(gp.player.getAttackPower() - 10);
                    
                }
            }
            
        }
        
    /**
     * <p>
     * This method draws sword spin
     * </p>
     */
    public void drawSwordSpin() {
        switch(gp.player.spriteNum) {
            case 1: 
                if (gp.skills.auraSwordActive) {
                    gp.player.image = gp.player.auraSwordRight[9];  
                }else {
                    gp.player.image = gp.player.attackRight[9];  
                }    
                gp.player.tempScreenX = gp.player.screenX + 20;    
                break;
            case 2: 
                if (gp.skills.auraSwordActive) {
                    gp.player.image = gp.player.auraSwordUp[9];  
                }else {
                    gp.player.image = gp.player.attackUp[9];  
                }     
                gp.player.tempScreenY = gp.player.screenY - 10;
                break;
            case 3:
                if (gp.skills.auraSwordActive) {
                    gp.player.image = gp.player.auraSwordLeft[9];  
                }else {
                    gp.player.image = gp.player.attackLeft[9];  
                }
                gp.player.tempScreenX = gp.player.screenX - 20;
                break;
            case 4: 
                if (gp.skills.auraSwordActive) {
                    gp.player.image = gp.player.auraSwordDown[9];  
                }else {
                    gp.player.image = gp.player.attackDown[9];      
                }
                gp.player.tempScreenY = gp.player.screenY + 10;    
                break;
        }
    }
    /**
     * <p>
     * This method draw aura sword
     * </p>
     */
    public void drawAuraSword() {
        switch(gp.player.direction) {
            case "up":  case "upleft":  case "upright":     gp.player.image = gp.player.auraSwordUp[9];  gp.player.tempScreenY = gp.player.screenY - 10; break;
            case "down":case "downleft":case "downright":   gp.player.image = gp.player.auraSwordDown[9];  gp.player.tempScreenY = gp.player.screenY + 10;  break;
            case "left":    gp.player.image = gp.player.auraSwordLeft[9]; gp.player.tempScreenX = gp.player.screenX - 20;   break;
            case "right":   gp.player.image = gp.player.auraSwordRight[9]; gp.player.tempScreenX = gp.player.screenX + 20;  break;
        }
    }
    /**
     * <p>
     * This method increase timeouts
     * </p>
     */
    public void increaseTimeouts() {
        if (gp.skills.swordSpinTimeOut != 0) {
            gp.skills.swordSpinTimeOut++;
        }
        if(gp.skills.auraSwordTimeOut != 0) {
            gp.skills.auraSwordTimeOut++;
        }
    }
    /**
     * <p>
     * This method resets skills
     * </p>
     */
    public void resetSkills() {
        if (gp.skills.swordSpinTimeOut == gp.skills.skillStandbyTime) {
            gp.skills.swordSpinTimeOut = 0;
        }
        if (gp.skills.auraSwordTimeOut == gp.skills.skillStandbyTime) {
            gp.skills.auraSwordTimeOut = 0;
        }
    }
    
}
