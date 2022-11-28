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
	    
	    if(gp.ui.respawnHereRec.x < screenX && gp.ui.respawnHereRec.x + gp.ui.respawnHereRec.width > screenX &&
	       gp.ui.respawnHereRec.y < screenY && gp.ui.respawnHereRec.y + gp.ui.respawnHereRec.height > screenY) {
	        
	        gp.gameState = gp.playState;
	        gp.player.life = gp.player.increaseLife;
	        gp.reborn(false);        
	        
	    }else if(gp.ui.respawnCityRec.x < screenX && gp.ui.respawnCityRec.x + gp.ui.respawnCityRec.width > screenX &&
	             gp.ui.respawnCityRec.y < screenY && gp.ui.respawnCityRec.y + gp.ui.respawnCityRec.height > screenY) {
	        
            gp.gameState = gp.playState;
            gp.player.life = gp.player.increaseLife;
            gp.reborn(true);        
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
