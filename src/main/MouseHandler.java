package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseListener, MouseMotionListener {
	
	public int screenX, screenY;
	public int mouseOverX, mouseOverY;
	public boolean pressed = false;
	
	GamePanel gp;
	
	public MouseHandler(GamePanel gp){
	    this.gp = gp;
	    gp.addMouseMotionListener(this);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
        System.out.println("clicked");
    }

	@Override
	public void mousePressed(MouseEvent e) {
	    
        screenX = e.getPoint().x;
        screenY = e.getPoint().y;
	    
        pressed = true;
        gp.player.goalX = (screenX + gp.player.worldX - gp.player.screenX) / gp.tileSize;
        gp.player.goalY = (screenY + gp.player.worldY - gp.player.screenY) / gp.tileSize;
        
        // RE-SPAWN
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

}
