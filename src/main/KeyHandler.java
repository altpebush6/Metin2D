package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

	public boolean upPressed, downPressed, leftPressed, rightPressed;
	public boolean quotePressed, openDebug;
	public boolean spacePressed;
	
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
            gp.player.attacking = true;
        }   
        if(code == KeyEvent.VK_1) {

        }
        if(code == KeyEvent.VK_2) {
            if(gp.skills.swordSpinTimeOut == 0 && !gp.skills.swordSpinUsed) {
                gp.skills.swordSpinUsed = true;
                gp.skills.swordSpinCounter++;
                gp.skills.skillType = 2; 
                gp.playSE(19);
            }
        }
        if(code == KeyEvent.VK_3) {
        }
        if(code == KeyEvent.VK_4) {
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
            gp.player.punchTimeOut = 0;
            
            // to detect double click
            gp.player.doubleClicked = false;
            gp.player.spacePressed = true;
            if(gp.player.spacePressed && gp.player.clickCounter < 30 && gp.player.clickCounter != 0) {
                gp.player.doubleClicked = true;
                gp.player.spacePressed = false;
                gp.player.clickCounter = 0;
            }
            gp.player.clickCounter = 0;
            
        }
	}

}
