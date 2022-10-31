package main;

import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

	public boolean upPressed, downPressed, leftPressed, rightPressed, quotePressed, openDebug, spacePressed;
	
   GamePanel gp;
    
    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			upPressed = true;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = true;	
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = true;	
		}
		if(code == KeyEvent.VK_S) {
			downPressed = true;	
		}
		if(code == KeyEvent.VK_QUOTEDBL) {
			quotePressed = true;
		}
		if(code == KeyEvent.VK_T) {
			if(openDebug) {
				openDebug = false;
			}else {
				openDebug = true;
			}
		}
		if(code == KeyEvent.VK_SPACE) {
		    spacePressed = true;
		}
			
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = false;	
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = false;	
		}
		if(code == KeyEvent.VK_S) {
			downPressed = false;	
		}
	    if(code == KeyEvent.VK_QUOTEDBL) {
	        quotePressed = false;    
	    }
       if(code == KeyEvent.VK_SPACE) {
            spacePressed = false;
            gp.player.attacking = false;
        }
	}

}
