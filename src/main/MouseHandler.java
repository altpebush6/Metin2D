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
