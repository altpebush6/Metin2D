package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import entity.Player;
import npc.Npc_Merchant;

public class KeyHandler implements KeyListener {

	public boolean upPressed, downPressed, leftPressed, rightPressed;
	public boolean quotePressed, openDebug;
	public boolean spacePressed, enterPressed;
	public boolean isSaveButtonPressod;

	GamePanel gp;
	Player p11;

	public KeyHandler(GamePanel gp) {
		this.gp = gp;
		/*
		 * MouseHandler mouseH1 = new MouseHandler(gp);
		 * p11 = new Player(gp,this,mouseH1);
		 */
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
				if (gp.skills.swordSpinTimeOut == 0 && !gp.skills.swordSpinUsed && gp.player.sp >= 10) {
					gp.skills.swordSpinUsed = true;
					gp.skills.swordSpinCounter++;
					gp.skills.skillType = gp.skills.swordSpinType;
					gp.player.sp -= 10;
					gp.playSE(19);
				}
			}
			if (code == KeyEvent.VK_3) {
			}
			if (code == KeyEvent.VK_4) {
			}
			if (code == KeyEvent.VK_F1) {
				if (gp.skills.auraSwordTimeOut == 0 && !gp.skills.auraSwordActive && gp.player.sp >= 10) {
					gp.skills.auraSwordActive = true;
					gp.skills.skillUsed = true;
					gp.skills.auraSwordCounter++;
					gp.skills.skillType = gp.skills.auraSwordType;
					gp.player.sp -= 10;
					gp.playSE(22);
					gp.player.attackPower += 10;
				}
			}
			if (code == KeyEvent.VK_ESCAPE) {
				gp.gameState = gp.optionsState;
			}
			if (code == KeyEvent.VK_9) {
			    gp.saveLoad.save();
			    gp.ui.showMessage("Saving...");
			    
			}

		} else if (gp.gameState == gp.dialogueState) { // DIALOGUE STATE
			if (code == KeyEvent.VK_ENTER) {
				gp.gameState = gp.playState;
			}
		} else if (gp.gameState == gp.tradeState) {
			if (code == KeyEvent.VK_ENTER) {
				gp.gameState = gp.playState;
			}

			if (code == KeyEvent.VK_W) {
				if (gp.ui.npcSlotRow == 0) {
					gp.ui.npcSlotRow = 3;
				} else {
					gp.ui.npcSlotRow--;
				}

			}
			if (code == KeyEvent.VK_A) {
				if (gp.ui.npcSlotCol == 0) {
					gp.ui.npcSlotCol = 4;
				} else {
					gp.ui.npcSlotCol--;
				}

			}
			if (code == KeyEvent.VK_S) {
				if (gp.ui.npcSlotRow == 3) {
					gp.ui.npcSlotRow = 0;
				} else {
					gp.ui.npcSlotRow++;
				}

			}
			if (code == KeyEvent.VK_D) {
				if (gp.ui.npcSlotCol == 4) {
					gp.ui.npcSlotCol = 0;
				} else {
					gp.ui.npcSlotCol++;
				}
			}

			// BUY SYSTEM
			if (code == KeyEvent.VK_B) {

				if (gp.ui.controlNpcCursor() == true) {
					/*
					 * if(p11.playerCoin > 100) {
					 * //p11.inventory.add(Npc_Merchant.npcInventory.get(gp.ui.cursorNpcIndex));
					 * }
					 */
					System.out.println("filled the slot");
				} else {
					System.out.println("not filled");
				}

			}

		} else if (gp.gameState == gp.optionsState) { // OPTIONS STATE
			optionsState(code);
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
		if (gp.gameState == gp.inventoryState) {
			if (code == KeyEvent.VK_W) {
				if (gp.ui.slotRow == 0) {
					gp.ui.slotRow = 8;
				} else {
					gp.ui.slotRow--;
				}

			}
			if (code == KeyEvent.VK_A) {
				if (gp.ui.slotCol == 0) {
					gp.ui.slotCol = 4;
				} else {
					gp.ui.slotCol--;
				}

			}
			if (code == KeyEvent.VK_S) {
				if (gp.ui.slotRow == 8) {
					gp.ui.slotRow = 0;
				} else {
					gp.ui.slotRow++;
				}

			}
			if (code == KeyEvent.VK_D) {
				if (gp.ui.slotCol == 4) {
					gp.ui.slotCol = 0;
				} else {
					gp.ui.slotCol++;
				}
			}
			if (code == KeyEvent.VK_E) {

				if (gp.ui.controlCursor() == true) {
					gp.player.currentWeapon = gp.player.inventory.get(gp.ui.cursorIndex);
					if (gp.player.inventory.get(gp.ui.cursorIndex) == gp.player.currentWeapon) {
						System.out.println("same weapon");
					}
					System.out.println("filled the slot");
				} else {
					System.out.println("not filled");
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

	public void optionsState(int code) {

		if (code == KeyEvent.VK_ESCAPE) {
			gp.gameState = gp.playState;
		}
		if (code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}

		int maxCommandNum = 0;
		switch (gp.ui.subState) {
			case 0:
				maxCommandNum = 6;
				break;
			case 3:
				maxCommandNum = 1;
				break;

		}
		if (code == KeyEvent.VK_W) {
			gp.ui.commandNum--;
			gp.playSE(9);
			if (gp.ui.commandNum < 0) {
				gp.ui.commandNum = maxCommandNum;
			}
		}
		if (code == KeyEvent.VK_S) {
			gp.ui.commandNum++;
			gp.playSE(9);
			if (gp.ui.commandNum > maxCommandNum) {
				gp.ui.commandNum = 0;
			}
		}
		if (code == KeyEvent.VK_A) {
			if (gp.ui.subState == 0) {
				if (gp.ui.commandNum == 1 && gp.soundtrack.volumeScale > 0) {
					gp.soundtrack.volumeScale--;
					gp.soundtrack.checkVolume();
					gp.playSE(9);
				}
				if (gp.ui.commandNum == 2 && gp.se.volumeScale > 0) {
					gp.se.volumeScale--;
					gp.playSE(9);
				}
			}
		}
		if (code == KeyEvent.VK_D) {
			if (gp.ui.subState == 0) {
				if (gp.ui.commandNum == 1 && gp.soundtrack.volumeScale < 5) {
					gp.soundtrack.volumeScale++;
					gp.soundtrack.checkVolume();
					gp.playSE(9);
				}
				if (gp.ui.commandNum == 2 && gp.se.volumeScale < 5) {
					gp.se.volumeScale++;
					gp.playSE(9);
				}
			}
		}

	}

}
