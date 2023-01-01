package main;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import entity.Player;
import npc.Npc_Merchant;

public class KeyHandler implements KeyListener {

	public boolean upPressed, downPressed, leftPressed, rightPressed;
	public boolean quotePressed, openDebug;
	public boolean spacePressed, enterPressed;
	

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
		
		// TITLE STATE
		if(gp.gameState == gp.titleState) {
		    
		    if(gp.ui.enterName) {
		        if(code == KeyEvent.VK_ENTER){
                    gp.ui.enterName = false;
                    gp.player.name = gp.ui.playerName;
		        }else if(code == KeyEvent.VK_BACK_SPACE && gp.ui.playerName.length() > 0) {
		            StringBuffer sb = new StringBuffer(gp.ui.playerName);
		            sb.deleteCharAt(sb.length() - 1);
		            gp.ui.playerName = sb.toString();
		        }else {
		            if(gp.ui.playerName.length() < 12 && ((e.getKeyChar() >= 'A' && e.getKeyChar() <= 'Z') || (e.getKeyChar() >= 'a' && e.getKeyChar() <= 'z') || (e.getKeyChar() >= '0' && e.getKeyChar() <= '9'))) {
		                gp.ui.playerName += e.getKeyChar();
		            }
		        }
		    }else {
		        if(code == KeyEvent.VK_ENTER && !gp.playBtn){
                    gp.playSE(26);
                    gp.playSE(25);
                    gp.playBtn = true;
		        }
		    }
		}

		// PLAY STATE
		if (gp.gameState == gp.playState) {
		    
		    if(code == KeyEvent.VK_ENTER) {
                enterPressed = true;
                for(int i = 0; i < gp.npc.length; i++) {
                    if(gp.npc[i] != null && (gp.npc[i].worldX < gp.player.worldX + gp.tileSize * 2 && gp.npc[i].worldX > gp.player.worldX - gp.tileSize * 2) && (gp.npc[i].worldY < gp.player.worldY + gp.tileSize * 2 && gp.npc[i].worldY > gp.player.worldY - gp.tileSize * 2)) {
                        gp.player.interactNPCIndex = i;
                    }
                }
            }

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
			    gp.player.useRedPotion();
			}
			if (code == KeyEvent.VK_2) {
			    gp.player.useBluePotion();
			}
			if (code == KeyEvent.VK_3) {
	             if (gp.skills.swordSpinTimeOut == 0 && !gp.skills.swordSpinUsed && gp.player.sp >= 10) {
                    gp.skills.swordSpinUsed = true;
                    gp.skills.swordSpinCounter++;
                    gp.skills.skillType = gp.skills.swordSpinType;
                    gp.player.sp -= 10;
                    gp.playSE(19);
	             }
			}
			if (code == KeyEvent.VK_4) {
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
			if (code == KeyEvent.VK_F1) {

			}
			if (code == KeyEvent.VK_ESCAPE) {
				gp.gameState = gp.optionsState;
			}
			if (code == KeyEvent.VK_9) {
			    gp.saveLoad.save();
			    gp.ui.addMessage("Saving...");
			    
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
					gp.ui.npcSlotRow = 8;
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
				if (gp.ui.npcSlotRow == 8) {
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
					
					 if(gp.player.playerCoin > Npc_Merchant.npcInventory.get(gp.ui.cursorNpcIndex).price) {
					     if(gp.player.inventory.size() < gp.player.maxInventorySize) {
    					     if(Npc_Merchant.npcInventory.get(gp.ui.cursorNpcIndex).name == "Red Potion") {
    					         if(gp.player.redPotionNumber < 99) {
    	                            if(gp.player.redPotionNumber == 0) {
    	                                gp.player.inventory.add(Npc_Merchant.npcInventory.get(gp.ui.cursorNpcIndex));
    
    	                                 if(gp.player.taskLevel == 0 ) {
    	                                     gp.player.playerCoin +=100;
    	                                     gp.player.taskLevel ++;
    	                                     gp.player.playerXP += 200;
    	                                 }
    	                             }
    	                             gp.player.playerCoin -= Npc_Merchant.npcInventory.get(gp.ui.cursorNpcIndex).price;
    	                             gp.player.redPotionNumber++; 
    					         }
    					     }else if(Npc_Merchant.npcInventory.get(gp.ui.cursorNpcIndex).name == "Blue Potion"){
    		                           if(gp.player.bluePotionNumber < 99) {
    		                                if(gp.player.bluePotionNumber == 0) {
    		                                    gp.player.inventory.add(Npc_Merchant.npcInventory.get(gp.ui.cursorNpcIndex));
    
    		                                     if(gp.player.taskLevel == 0 ) {
    		                                         gp.player.playerCoin +=100;
    		                                         gp.player.taskLevel ++;
    		                                         gp.player.playerXP += 200;
    		                                     }
    		                                 }
    		                                 gp.player.playerCoin -= Npc_Merchant.npcInventory.get(gp.ui.cursorNpcIndex).price;
    		                                 gp.player.bluePotionNumber++; 
    		                             }
    					     }else {
    					         gp.player.inventory.add(Npc_Merchant.npcInventory.get(gp.ui.cursorNpcIndex));
    		                     gp.player.playerCoin -= Npc_Merchant.npcInventory.get(gp.ui.cursorNpcIndex).price;
    		                         
    		                     if(gp.player.taskLevel == 0 ) {
    		                         gp.player.playerCoin +=100;
    		                         gp.player.taskLevel ++;
    		                         gp.player.playerXP += 200;
    		                     }
    					     }
					     }else {
					         gp.ui.addMessage("Inventory Full");
					     }
					 } else {
							gp.ui.addMessage("Not Enough Money to Buy It");
					 }
					 	
					System.out.println("filled the slot");
				} else {
					System.out.println("not filled");
				}

			}

		} else if (gp.gameState == gp.optionsState) { // OPTIONS STATE
			optionsState(code);
		} else if (gp.gameState == gp.enchantState) { // ENCHANT STATE
			
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
					gp.player.enchantWeapon = gp.player.inventory.get(gp.ui.cursorIndex);
					gp.player.itemEnchSellected = true;
					System.out.println("filled the slot");
				} else {
					System.out.println("not filled");
				}

			}
			if(gp.player.itemEnchSellected == true) {
				if(code == KeyEvent.VK_Y) {
					gp.player.enchantAccepted = true;
				}
			}
			
			if(code == KeyEvent.VK_ENTER) {
				gp.player.enchantAccepted = false;
				gp.player.itemEnchSellected = false;
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

		/*
		if(gp.gameState == gp.enchantState) {

			if(gp.player.itemEnchSellected == true) {
				if(code == KeyEvent.VK_Y) {
					gp.player.enchantAccepted = false;
				}
			}
		}
		*/
	        if(code == KeyEvent.VK_ENTER) {
	            gp.player.interactNPCIndex = -1;
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
		
		    
		
		if(gp.ui.commandNum == 5) {
            if(enterPressed == true) {
                gp.saveLoad.save();
                gp.ui.addMessage("Saving...");
            }

	}
	}
}
