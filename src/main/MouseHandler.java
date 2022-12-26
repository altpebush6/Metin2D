package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseListener, MouseMotionListener {
	
	public int screenX, screenY, clickedWorldX, clickedWorldY, clickedCol, clickedRow;
	public int mouseOverX, mouseOverY;
	public boolean pressed = false;
	
	public boolean pressedOnEnemy = false;
	public int enemyIndex;
	
	GamePanel gp;
	
	public MouseHandler(GamePanel gp){
	    this.gp = gp;
	    gp.addMouseMotionListener(this);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
	    
        screenX = e.getPoint().x;
        screenY = e.getPoint().y;
        
        // Move
        if(gp.gameState == gp.playState) {
            
            clickedWorldX = screenX + gp.player.worldX - gp.player.screenX;
            clickedWorldY = screenY + gp.player.worldY - gp.player.screenY;
            
            clickedCol = clickedWorldX / gp.tileSize;
            clickedRow = clickedWorldY / gp.tileSize;
    	    
            pressed = true;
            pressedOnEnemy = false;
            gp.player.autoHit = false;
            gp.player.reachedGoal = false;
            
            int tileNum = gp.tileM.mapTileNum[clickedCol][clickedRow];
            if(gp.tileM.tile[tileNum].collision) {
                pressed = false;
            }else {
                for(int i = 0; i < gp.enemy.length; i++) {
                    if(gp.enemy[i] != null) {
                        int enemyLeft = gp.enemy[i].worldX + gp.enemy[i].solidArea.x;
                        int enemyRight = gp.enemy[i].worldX + gp.enemy[i].solidArea.x + gp.enemy[i].solidArea.width;
                        int enemyTop = gp.enemy[i].worldY + gp.enemy[i].solidArea.y;
                        int enemyBottom = gp.enemy[i].worldY + gp.enemy[i].solidArea.y + gp.enemy[i].solidArea.height;
                        
                        if(clickedWorldX >= enemyLeft && clickedWorldY >= enemyTop &&
                           clickedWorldX <= enemyRight && clickedWorldY <= enemyBottom) {
                            pressedOnEnemy = true;
                            enemyIndex = i;
                        }
                    }
                }
                    
                if(pressedOnEnemy) {
                    gp.player.goalX = (gp.enemy[enemyIndex].worldX + gp.enemy[enemyIndex].solidArea.x) / gp.tileSize;
                    gp.player.goalY = (gp.enemy[enemyIndex].worldX + gp.enemy[enemyIndex].solidArea.x) / gp.tileSize;
                }else {
                    gp.player.goalX = clickedCol;
                    gp.player.goalY = clickedRow;
                }
            }
        }

        // RE-SPAWN
        if(gp.gameState == gp.deadState) {
            int respawnTime = 180;
            
            if(gp.ui.respawnHereRec.x < screenX && gp.ui.respawnHereRec.x + gp.ui.respawnHereRec.width > screenX &&
               gp.ui.respawnHereRec.y < screenY && gp.ui.respawnHereRec.y + gp.ui.respawnHereRec.height > screenY) {
                
                System.out.println(gp.player.deadCounter);
                if(gp.player.deadCounter >= respawnTime) {
                    gp.gameState = gp.playState;
                    gp.player.life = gp.player.increaseLife;
                    gp.reborn(false);         
                }else {
                       int timeRemaining = (respawnTime - gp.player.deadCounter) / 60 + 1;
                       gp.ui.showMessage("Wait " + timeRemaining + "s to respawn");
                }
      
                
            }else if(gp.ui.respawnCityRec.x < screenX && gp.ui.respawnCityRec.x + gp.ui.respawnCityRec.width > screenX &&
                     gp.ui.respawnCityRec.y < screenY && gp.ui.respawnCityRec.y + gp.ui.respawnCityRec.height > screenY) {
                
                if(gp.player.deadCounter >= respawnTime) {
                    gp.gameState = gp.playState;
                    gp.player.life = gp.player.increaseLife;
                    gp.reborn(true);  
                }else {
                    int timeRemaining = (respawnTime - gp.player.deadCounter) / 60 + 1;
                    gp.ui.showMessage("Wait " + timeRemaining + "s to respawn");
                }
            }
        }
        
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseOverX = e.getX();
        mouseOverY = e.getY();
        

    }
    
    public void drawRespawnBtn(Graphics2D g2) {
        if(gp.ui.respawnHereRec.x < mouseOverX && gp.ui.respawnHereRec.x + gp.ui.respawnHereRec.width > mouseOverX &&
                gp.ui.respawnHereRec.y < mouseOverY && gp.ui.respawnHereRec.y + gp.ui.respawnHereRec.height > mouseOverY) {
                 
                    gp.ui.btnHover = 1;
                
        }else if(gp.ui.respawnCityRec.x < mouseOverX && gp.ui.respawnCityRec.x + gp.ui.respawnCityRec.width > mouseOverX &&
                gp.ui.respawnCityRec.y < mouseOverY && gp.ui.respawnCityRec.y + gp.ui.respawnCityRec.height > mouseOverY) {
               
            System.out.println("sa");
            gp.ui.btnHover = 2;
            
        }else {
            gp.ui.btnHover = 0;
        }
    }

}
