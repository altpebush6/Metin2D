package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import npc.Npc_Merchant;
import object.OBJ_Dolunay;
import object.OBJ_KDP;
import object.OBJ_SuPerisi;
import object.OBJ_GenisKilic;

/**
 * <p>
 * This Class ensure all interaction in the game.
 * </p>
 */
public class KeyHandler implements KeyListener {

	/**
	 * <p>
	 * This variable for upPressed or not.
	 * </p>
	 */
	public boolean upPressed;

	/**
	 * <p>
	 * This variable for downPressed or not.
	 * </p>
	 */
	public boolean downPressed;

	/**
	 * <p>
	 * This variable for leftPressed or not.
	 * </p>
	 */
	public boolean leftPressed;

	/**
	 * <p>
	 * This variable for rightPressed or not.
	 * </p>
	 */
	public boolean rightPressed;

	/**
	 * <p>
	 * This variable for quote pressed or not.
	 * </p>
	 */
	public boolean quotePressed;

	/**
	 * <p>
	 * This variable for opening debug mode.
	 * </p>
	 */
	public boolean openDebug;

	/**
	 * <p>
	 * This variable for pressed space or not.
	 * </p>
	 */
	public boolean spacePressed;

	/**
	 * <p>
	 * This variable for enter pressed or not.
	 * </p>
	 */
	public boolean enterPressed;

	/**
	 * <p>
	 * This variable ensure the player take mission prize for one times.
	 * </p>
	 */
	public int missionPrize[] = { 1, 1, 1, 1, 1 };

	/**
	 * <p>
	 * This variable store the enchanting task do or not.
	 * </p>
	 */
	public int enchantMission = 0;

	/**
	 * <p>
	 * This variable for first task taken or not.
	 * </p>
	 */
	public int takeTask1 = 0;

	GamePanel gp;

	/**
	 * <p>
	 * This is constructor
	 * </p>
	 * 
	 * @param gp is the game panel
	 * @since 1.0
	 */
	public KeyHandler(GamePanel gp) {
		this.gp = gp;

	}

	/**
	 * <p>
	 * This is deafult method's keylistener
	 * </p>
	 * 
	 * @param e for interaction
	 * @since 1.0
	 */
	@Override
	public void keyTyped(KeyEvent e) {
	}

	/**
	 * <p>
	 * This is deafult method's keylistener. All interaction ensure keyPressed
	 * </p>
	 * 
	 * @param e for interaction
	 * @since 1.0
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int code = e.getKeyCode();

		// TITLE STATE
		if (gp.gameState == gp.titleState) {

			if (gp.ui.enterName) {
				if (code == KeyEvent.VK_ENTER) {
					gp.ui.enterName = false;
					gp.player.name = gp.ui.playerName;
				} else if (code == KeyEvent.VK_BACK_SPACE && gp.ui.playerName.length() > 0) {
					StringBuffer sb = new StringBuffer(gp.ui.playerName);
					sb.deleteCharAt(sb.length() - 1);
					gp.ui.playerName = sb.toString();
					gp.ui.textCursorX -= 12;
				} else {
					if (gp.ui.playerName.length() < 11 && ((e.getKeyChar() >= 'A' && e.getKeyChar() <= 'Z')
							|| (e.getKeyChar() >= 'a' && e.getKeyChar() <= 'z')
							|| (e.getKeyChar() >= '0' && e.getKeyChar() <= '9'))) {
						gp.ui.playerName += e.getKeyChar();
						gp.ui.textCursorX += 12;
					}
				}
			} else {
				if (code == KeyEvent.VK_ENTER && !gp.playBtn) {
					gp.playSE(26);
					gp.playSE(25);
					gp.playBtn = true;
				}
			}
		}

		// PLAY STATE
		if (gp.gameState == gp.playState) {

			if (code == KeyEvent.VK_ENTER) {
				enterPressed = true;
				for (int i = 0; i < gp.npc.length; i++) {
					if (gp.npc[i] != null
							&& (gp.npc[i].worldX < gp.player.worldX + gp.tileSize * 2
									&& gp.npc[i].worldX > gp.player.worldX - gp.tileSize * 2)
							&& (gp.npc[i].worldY < gp.player.worldY + gp.tileSize * 2
									&& gp.npc[i].worldY > gp.player.worldY - gp.tileSize * 2)) {
						gp.player.interactNPCIndex = i;
					}
				}
			}
			if (code == KeyEvent.VK_G) {
				if (gp.ui.openTaskScreen == false) {
					gp.ui.openTaskScreen = true;
				} else {
					gp.ui.openTaskScreen = false;
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
					gp.player.setAttackPower(gp.player.getAttackPower() + 10);
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

			if (code == KeyEvent.VK_RIGHT) {
				if (gp.player.taskLevel == 2 && gp.ui.pageNum == 0) {
					gp.ui.pageNum++;
				}

				if (gp.player.taskLevel == 3 && gp.ui.pageNum == 0) {
					gp.ui.pageNum++;
				}
			}

			if (code == KeyEvent.VK_LEFT) {
				if (gp.player.taskLevel == 2 && gp.ui.pageNum == 1) {
					gp.ui.pageNum--;
				}

				if (gp.player.taskLevel == 3 && gp.ui.pageNum == 1) {
					gp.ui.pageNum--;
				}
			}

			if (code == KeyEvent.VK_ENTER) {
				gp.gameState = gp.playState;
				if (gp.player.taskLevel == 0 && missionPrize[0] == 1) {
					gp.player.setPlayerCoin(gp.player.getPlayerCoin() + 150);
					missionPrize[0] = 0;
					gp.ui.addMessage("Commissioned.");
					gp.ui.addMessage("150 Coin received.");
				} else if (gp.player.taskLevel == 0 && missionPrize[0] == 0) {
					gp.ui.addMessage("Already Commissioned.");
					gp.ui.addMessage("Shop with Seller!");
				} else if (gp.player.taskLevel == 1 && missionPrize[1] == 1) {
					gp.player.setPlayerCoin(gp.player.getPlayerCoin() + 200);
					takeTask1++;
					missionPrize[1] = 0;
					gp.ui.addMessage("Commissioned.");
					gp.ui.addMessage("200 Coin received.");
				} else if (gp.player.taskLevel == 1 && missionPrize[1] == 0) {
					gp.ui.addMessage("Already Commissioned.");
					gp.ui.addMessage("Go to Blacksmith Rustem!");
				} else if (gp.player.taskLevel == 2 && missionPrize[2] == 1) {
					gp.player.setPlayerCoin(gp.player.getPlayerCoin() + 500);
					missionPrize[2] = 0;
					gp.ui.pageNum = 0;
					gp.ui.addMessage("Commissioned.");
					gp.ui.addMessage("500 Coin received.");
				} else if (gp.player.taskLevel == 2 && missionPrize[2] == 0 && gp.ui.wolfTaskDo == false) {
					gp.ui.pageNum = 0;
					gp.ui.addMessage("Already Commissioned.");
					gp.ui.addMessage("Kill 3 Wolves!");
				} else if (gp.player.taskLevel == 2 && missionPrize[2] == 0 && gp.ui.wolfTaskDo == true) {
					gp.player.setPlayerCoin(gp.player.getPlayerCoin() + 1000);
					gp.player.taskLevel++;
					gp.ui.pageNum = 0;
					gp.ui.addMessage("Mission Accomplished.");
					gp.ui.addMessage("500 Coin received.");
				} else if (gp.player.taskLevel == 3 && missionPrize[3] == 1) {
					gp.player.setPlayerCoin(gp.player.getPlayerCoin() + 1000);
					missionPrize[3] = 0;
					gp.ui.pageNum = 0;
					gp.ui.addMessage("Commissioned.");
					gp.ui.addMessage("1000 Coin received.");
				} else if (gp.player.taskLevel == 3 && missionPrize[3] == 0) {
					gp.ui.pageNum = 0;
					gp.ui.addMessage("Already Commissioned.");
					gp.ui.addMessage("Smash the Antenna!");
				} else if (gp.player.taskLevel == 4 && missionPrize[4] == 1) {
					gp.player.setPlayerCoin(gp.player.getPlayerCoin() + 5000);
					missionPrize[4] = 0;
					gp.ui.pageNum = 0;
					gp.ui.addMessage("Enjoy the village!");
					gp.ui.addMessage("10.000 Coin received.");
				} else if (gp.player.taskLevel == 4 && missionPrize[4] == 0) {
					gp.ui.pageNum = 0;
					gp.ui.addMessage("Enjoy the village!");
				}

			}

		} else if (gp.gameState == gp.tradeState) {
			if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_ESCAPE) {
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

					if (gp.player.getPlayerCoin() > Npc_Merchant.npcInventory.get(gp.ui.cursorNpcIndex).price) {
						if (gp.player.inventory.size() < gp.player.maxInventorySize) {
							if (Npc_Merchant.npcInventory.get(gp.ui.cursorNpcIndex).name == "Red Potion") {
								if (gp.player.redPotionNumber < 99) {
									if (gp.player.redPotionNumber == 0) {
										gp.player.inventory.add(Npc_Merchant.npcInventory.get(gp.ui.cursorNpcIndex));

										if (gp.player.taskLevel == 0) {
											gp.player.setPlayerCoin(gp.player.getPlayerCoin() + 100);
											gp.player.taskLevel++;
											gp.player.setPlayerXP(gp.player.getPlayerXP() + 200);
										}
									}
									gp.player.setPlayerCoin(gp.player.getPlayerCoin()
											- Npc_Merchant.npcInventory.get(gp.ui.cursorNpcIndex).price);
									gp.player.redPotionNumber++;
								}
							} else if (Npc_Merchant.npcInventory.get(gp.ui.cursorNpcIndex).name == "Blue Potion") {
								if (gp.player.bluePotionNumber < 99) {
									if (gp.player.bluePotionNumber == 0) {
										gp.player.inventory.add(Npc_Merchant.npcInventory.get(gp.ui.cursorNpcIndex));

										if (gp.player.taskLevel == 0) {
											gp.player.setPlayerCoin(gp.player.getPlayerCoin() + 100);
											gp.player.taskLevel++;
											gp.player.setPlayerXP(gp.player.getPlayerXP() + 200);
										}
									}
									gp.player.setPlayerCoin(gp.player.getPlayerCoin()
											- Npc_Merchant.npcInventory.get(gp.ui.cursorNpcIndex).price);
									gp.player.bluePotionNumber++;
								}
							} else {

								switch (gp.ui.cursorNpcIndex) {
									case 0:
										gp.player.inventory.add(new OBJ_KDP(gp));
										gp.player.setPlayerCoin(gp.player.getPlayerCoin()
												- Npc_Merchant.npcInventory.get(gp.ui.cursorNpcIndex).price);
										break;
									case 1:
										gp.player.inventory.add(new OBJ_GenisKilic(gp));
										gp.player.setPlayerCoin(gp.player.getPlayerCoin()
												- Npc_Merchant.npcInventory.get(gp.ui.cursorNpcIndex).price);
										break;
									case 2:
										gp.player.inventory.add(new OBJ_Dolunay(gp));
										gp.player.setPlayerCoin(gp.player.getPlayerCoin()
												- Npc_Merchant.npcInventory.get(gp.ui.cursorNpcIndex).price);
										break;
									case 3:
										gp.player.inventory.add(new OBJ_SuPerisi(gp));
										gp.player.setPlayerCoin(gp.player.getPlayerCoin()
												- Npc_Merchant.npcInventory.get(gp.ui.cursorNpcIndex).price);
										break;

								}
								// For different enchantLevel:
								// gp.player.inventory.add(Npc_Merchant.npcInventory.get(gp.ui.cursorNpcIndex));

								if (gp.player.taskLevel == 0) {
									gp.player.setPlayerCoin(gp.player.getPlayerCoin() + 100);
									gp.player.taskLevel++;
									gp.player.setPlayerXP(gp.player.getPlayerXP() + 200);
									gp.ui.addMessage("Görev Başarılı.");
									gp.ui.addMessage("XP kazanıldı.");
									gp.ui.addMessage("100 Coin received.");
								}
							}
						} else {
							gp.ui.addMessage("Inventory Full");
						}
					} else {
						gp.ui.addMessage("Not Enough Money to Buy It");
					}

				} else {

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
					if (gp.player.inventory.get(gp.ui.cursorIndex).name != "Red Potion"
							&& gp.player.inventory.get(gp.ui.cursorIndex).name != "Blue Potion") {
						gp.player.setAttackPower(gp.player.getAttackPower() - gp.player.currentWeapon.weaponAttackSize);
						gp.player.enchantWeapon = gp.player.inventory.get(gp.ui.cursorIndex);
						gp.player.itemEnchSellected = true;
						gp.player.setAttackPower(gp.player.getAttackPower()
								+ gp.player.inventory.get(gp.ui.cursorIndex).weaponAttackSize);
					}
				}

			}

			if (gp.player.itemEnchSellected == true) {
				if (code == KeyEvent.VK_Y) {
					if (gp.player.getPlayerCoin() >= 100) {

						gp.player.setPlayerCoin(gp.player.getPlayerCoin() - 100);
						gp.player.enchantAccepted = true;
						if (takeTask1 == 1) {
							enchantMission++;
						}
					} else {
						gp.ui.addMessage("Not Enough Money to Enchant");
					}
				}
			}

			if ((code == KeyEvent.VK_ENTER || code == KeyEvent.VK_ESCAPE) && !gp.player.enchantAccepted) {
				gp.player.enchantAccepted = false;
				gp.player.itemEnchSellected = false;
				gp.gameState = gp.playState;
				if (enchantMission >= 1 && gp.player.taskLevel == 1 && takeTask1 == 1) {
					gp.player.setPlayerCoin(gp.player.getPlayerCoin() + 100);
					gp.player.taskLevel++;
					gp.player.setPlayerXP(gp.player.getPlayerXP() + 400);
					gp.ui.addMessage("Görev başarılı.");
					gp.ui.addMessage("XP kazanıldı.");
					gp.ui.addMessage("100 Coin received.");
				}
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
					if (gp.player.inventory.get(gp.ui.cursorIndex).name != "Red Potion"
							&& gp.player.inventory.get(gp.ui.cursorIndex).name != "Blue Potion") {
						gp.player.setAttackPower(gp.player.getAttackPower() - gp.player.currentWeapon.weaponAttackSize);
						gp.player.currentWeapon = gp.player.inventory.get(gp.ui.cursorIndex);
						gp.player.setAttackPower(gp.player.getAttackPower()
								+ gp.player.inventory.get(gp.ui.cursorIndex).weaponAttackSize);
					}
				}
			}
			if (code == KeyEvent.VK_ESCAPE) {
				gp.gameState = gp.playState;
			}
		}

	}

	/**
	 * <p>
	 * This is deafult method's keylistener
	 * </p>
	 * 
	 * @param e for interaction
	 * @since 1.0
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

		int code = e.getKeyCode();

		/*
		 * if(gp.gameState == gp.enchantState) {
		 * 
		 * if(gp.player.itemEnchSellected == true) {
		 * if(code == KeyEvent.VK_Y) {
		 * //gp.player.enchantAccepted = false;
		 * gp.player.itemEnchSellected = false;
		 * }
		 * }
		 * }
		 */
		if (code == KeyEvent.VK_ENTER) {
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

	/**
	 * <p>
	 * This method ensure passing the option screen.
	 * </p>
	 * 
	 * @param code for interaction
	 * @since 1.0
	 */
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

		if (gp.ui.commandNum == 5) {
			if (enterPressed == true) {
				gp.saveLoad.save();
				gp.ui.addMessage("Saving...");
			}

		}
	}
}
