package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

	public boolean upPressed, downPressed, leftPressed, rightPressed;
	public boolean quotePressed, openDebug;
	public boolean spacePressed;

	GamePanel gp;

	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int code = e.getKeyCode();

		// PLAY STATE
		if (gp.gameState == gp.playState) {
			if (code == KeyEvent.VK_W) {
				upPressed = true;
			}
			if (code == KeyEvent.VK_D) {
				rightPressed = true;
			}
			if (code == KeyEvent.VK_A) {
				leftPressed = true;
			}
			if (code == KeyEvent.VK_S) {
				downPressed = true;
			}
			if (code == KeyEvent.VK_QUOTEDBL) {
				quotePressed = true;
			}
			if (code == KeyEvent.VK_T) {
				if (openDebug) {
					openDebug = false;
				} else {
					openDebug = true;
				}
			}
			if (code == KeyEvent.VK_SPACE) {
				spacePressed = true;
				gp.player.attacking = true;
				gp.player.autoHit = false;
			}
			if (code == KeyEvent.VK_1) {

			}
			if (code == KeyEvent.VK_2) {
				if (gp.skills.swordSpinTimeOut == 0 && !gp.skills.swordSpinUsed) {
					gp.skills.swordSpinUsed = true;
					gp.skills.swordSpinCounter++;
					gp.skills.skillType = gp.skills.swordSpinType;
					gp.playSE(19);
				}
			}
			if (code == KeyEvent.VK_3) {
			}
			if (code == KeyEvent.VK_4) {
			}
			if (code == KeyEvent.VK_F1) {
			    if (gp.skills.auraSwordTimeOut == 0 && !gp.skills.auraSwordUsed) {
                    gp.skills.auraSwordUsed = true;
                    gp.skills.auraSwordCounter++;
                    gp.skills.skillType = gp.skills.auraSwordType;
                    gp.playSE(22);
                }
			}

		} else if (gp.gameState == gp.dialogueState) { // DIALOGUE STATE
			if (code == KeyEvent.VK_ENTER) {
				gp.gameState = gp.playState;
			}
		}

		if (code == KeyEvent.VK_P) {
			if (gp.gameState == gp.playState) {
				gp.gameState = gp.pauseState;
			} else if (gp.gameState == gp.pauseState) {
				gp.gameState = gp.playState;
			}
		}

		if (code == KeyEvent.VK_I) {
			if (gp.gameState == gp.playState) {
				gp.gameState = gp.inventoryState;

			
			} else if (gp.gameState == gp.inventoryState) {
				gp.gameState = gp.playState;
			}
		}
		if(gp.gameState == gp.inventoryState){
			if (code == KeyEvent.VK_W) {
				if(gp.ui.slotRow == 0){
					gp.ui.slotRow = 3;
				}else{
					gp.ui.slotRow--;
				}
				
			}
			if (code == KeyEvent.VK_A) {
				if(gp.ui.slotCol == 0){
					gp.ui.slotCol = 4;
				}else{
					gp.ui.slotCol--;
				}
				
			}
			if (code == KeyEvent.VK_S) {
				if(gp.ui.slotRow == 3){
					gp.ui.slotRow =0;
				}else{
					gp.ui.slotRow++;
				}

			}
			if (code == KeyEvent.VK_D) {
				if(gp.ui.slotCol == 4){
					gp.ui.slotCol =0;
				}else{
					gp.ui.slotCol++;
				}
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

		int code = e.getKeyCode();

		if (gp.gameState == gp.playState) {

		}

		if (code == KeyEvent.VK_W) {

			upPressed = false;
		}
		if (code == KeyEvent.VK_D) {
			rightPressed = false;
		}
		if (code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if (code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if (code == KeyEvent.VK_QUOTEDBL) {
			quotePressed = false;
		}
		if (code == KeyEvent.VK_SPACE) {
			spacePressed = false;

			// to detect double click

			/*
			 * gp.player.doubleClicked = false;
			 * gp.player.spacePressed = true;
			 * if(gp.player.spacePressed && gp.player.clickCounter < 30 &&
			 * gp.player.clickCounter != 0) {
			 * gp.player.doubleClicked = true;
			 * gp.player.spacePressed = false;
			 * gp.player.clickCounter = 0;
			 * }
			 * gp.player.clickCounter = 0;
			 */
		}
	}

}
