package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {
	
	public int screenX, screenY;
	public boolean pressed = false;
	
	GamePanel gp;
	
	public MouseHandler(GamePanel gp){
	    this.gp = gp;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	    
	    screenX = e.getPoint().x;
	    screenY = e.getPoint().y;
	    
	    int respawnTime = 180;
	    
	    if(gp.gameState == gp.deadState) {
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
	    
	    gp.player.beforeClickX = gp.player.worldX;
	    gp.player.beforeClickY = gp.player.worldY;
	    
	    gp.player.goalX = gp.player.screenX - screenX + gp.tileSize / 2;
	    gp.player.goalY = gp.player.screenY - screenY + gp.tileSize / 2;
		
		pressed = true;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	    pressed = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
