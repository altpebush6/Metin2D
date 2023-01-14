package main;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * <p>
 * This Class ensure all interaction with mouse.
 * </p>
 */
public class MouseHandler implements MouseListener, MouseMotionListener {

    /**
     * <p>
     * This variable for x location of mouse.
     * </p>
     */
    public int screenX;

    /**
     * <p>
     * This variable for y location of mouse.
     * </p>
     */
    public int screenY;

    /**
     * <p>
     * This variable for mouse clicked X location.
     * </p>
     */
    public int clickedWorldX;

    /**
     * <p>
     * This variable for mouse clicked Y location.
     * </p>
     */
    public int clickedWorldY;

    /**
     * <p>
     * This variable for mouse clicked location column.
     * </p>
     */
    public int clickedCol;

    /**
     * <p>
     * This variable for mouse clicked location row.
     * </p>
     */
    public int clickedRow;

    /**
     * <p>
     * This variable ensure the mouse's X location interaction
     * </p>
     */
    public int mouseOverX;

    /**
     * <p>
     * This variable ensure the mouse's Y location interaction.
     * </p>
     */
    public int mouseOverY;

    /**
     * <p>
     * This boolean variable determined pressed or not pressed
     * </p>
     */
    public boolean pressed = false;

    /**
     * <p>
     * This boolean variable mouse click on enemy or not.
     * </p>
     */
    public boolean pressedOnEnemy = false;

    /**
     * <p>
     * This variable determine enemy array's index.
     * </p>
     */
    public int enemyIndex;

    GamePanel gp;

    /**
     * <p>
     * This is constructor
     * </p>
     * 
     * @param gp is the game panel
     * @since 1.0
     */
    public MouseHandler(GamePanel gp) {
        this.gp = gp;
        gp.addMouseMotionListener(this);
    }

    /**
     * <p>
     * This is deafult method's mouselistener
     * </p>
     * 
     * @param e for interaction
     * @since 1.0
     */
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    /**
     * <p>
     * This is deafult method's mouselistener
     * </p>
     * 
     * @param e for interaction
     * @since 1.0
     */
    @Override
    public void mousePressed(MouseEvent e) {

        screenX = e.getPoint().x;
        screenY = e.getPoint().y;

        // Move
        if (gp.gameState == gp.playState) {

            clickedWorldX = screenX + gp.player.worldX - gp.player.screenX;
            clickedWorldY = screenY + gp.player.worldY - gp.player.screenY;

            clickedCol = clickedWorldX / gp.tileSize;
            clickedRow = clickedWorldY / gp.tileSize;

            pressed = true;
            pressedOnEnemy = false;
            gp.player.autoHit = false;
            gp.player.reachedGoal = false;

            int tileNum = gp.tileM.mapTileNum[clickedCol][clickedRow] - 1;

            if (tileNum > gp.maxWorldCol)
                tileNum = gp.maxWorldCol - 1;
            if (tileNum < 0)
                tileNum = 0;

            if (gp.tileM.tile[tileNum].collision) {
                pressed = false;
            } else {
                for (int i = 0; i < gp.enemy.length; i++) {
                    if (gp.enemy[i] != null) {
                        int enemyLeft = gp.enemy[i].worldX + gp.enemy[i].solidArea.x;
                        int enemyRight = gp.enemy[i].worldX + gp.enemy[i].solidArea.x + gp.enemy[i].solidArea.width;
                        int enemyTop = gp.enemy[i].worldY + gp.enemy[i].solidArea.y;
                        int enemyBottom = gp.enemy[i].worldY + gp.enemy[i].solidArea.y + gp.enemy[i].solidArea.height;

                        if (clickedWorldX >= enemyLeft && clickedWorldY >= enemyTop &&
                                clickedWorldX <= enemyRight && clickedWorldY <= enemyBottom) {
                            pressedOnEnemy = true;
                            enemyIndex = i;
                        }
                    }
                }

                if (pressedOnEnemy) {
                    gp.player.goalX = (gp.enemy[enemyIndex].worldX + gp.enemy[enemyIndex].solidArea.x) / gp.tileSize;
                    gp.player.goalY = (gp.enemy[enemyIndex].worldX + gp.enemy[enemyIndex].solidArea.x) / gp.tileSize;
                } else {
                    gp.player.goalX = clickedCol;
                    gp.player.goalY = clickedRow;
                }
            }
        }

        // Inventory Btn
        if (gp.ui.inventoryRec.x < mouseOverX
                && (gp.ui.inventoryRec.x + gp.ui.inventoryRec.width) > mouseOverX
                && gp.ui.inventoryRec.y < mouseOverY
                && (gp.ui.inventoryRec.y + gp.ui.inventoryRec.height) > mouseOverY) {
            if (gp.gameState == gp.playState) {
                gp.gameState = gp.inventoryState;
            } else if (gp.gameState == gp.inventoryState) {
                gp.gameState = gp.playState;
            }
        }

        // Options Btn
        if (gp.ui.optionsRec.x < mouseOverX
                && (gp.ui.optionsRec.x + gp.ui.optionsRec.width) > mouseOverX
                && gp.ui.optionsRec.y < mouseOverY
                && (gp.ui.optionsRec.y + gp.ui.optionsRec.height) > mouseOverY) {
            gp.gameState = gp.optionsState;
        }

        // RE-SPAWN
        if (gp.gameState == gp.deadState) {
            int respawnTime = 180;

            if (gp.ui.respawnHereRec.x < mouseOverX
                    && (gp.ui.respawnHereRec.x + gp.ui.respawnHereRec.width) > mouseOverX
                    && gp.ui.respawnHereRec.y < mouseOverY
                    && (gp.ui.respawnHereRec.y + gp.ui.respawnHereRec.height) > mouseOverY) {

                System.out.println(gp.player.deadCounter);
                if (gp.player.deadCounter >= respawnTime) {
                    gp.gameState = gp.playState;
                    gp.player.life = gp.player.increaseLife;
                    gp.reborn(false);
                } else {
                    int timeRemaining = (respawnTime - gp.player.deadCounter) / 60 + 1;
                    gp.ui.addMessage("Wait " + timeRemaining + "s to respawn");
                }

            } else if (gp.ui.respawnCityRec.x < mouseOverX
                    && (gp.ui.respawnCityRec.x + gp.ui.respawnCityRec.width) > mouseOverX
                    && gp.ui.respawnCityRec.y < mouseOverY
                    && (gp.ui.respawnCityRec.y + gp.ui.respawnCityRec.height) > mouseOverY) {

                if (gp.player.deadCounter >= respawnTime) {
                    gp.gameState = gp.playState;
                    gp.player.life = gp.player.increaseLife;
                    gp.reborn(true);
                } else {
                    int timeRemaining = (respawnTime - gp.player.deadCounter) / 60 + 1;
                    gp.ui.addMessage("Wait " + timeRemaining + "s to respawn");
                }
            }
        }

        if (gp.gameState == gp.titleState) {
            if (gp.ui.enterName) {
                if (gp.ui.saveRec.x < mouseOverX
                        && (gp.ui.saveRec.x + gp.ui.saveRec.width) > mouseOverX
                        && gp.ui.saveRec.y < mouseOverY
                        && (gp.ui.saveRec.y + gp.ui.saveRec.height) > mouseOverY) {
                    gp.ui.enterName = false;
                    gp.player.name = gp.ui.playerName;
                }
            } else {
                if (gp.ui.startRec.x < mouseOverX
                        && (gp.ui.startRec.x + gp.ui.startRec.width) > mouseOverX
                        && gp.ui.startRec.y < mouseOverY
                        && (gp.ui.startRec.y + gp.ui.startRec.height) > mouseOverY && !gp.playBtn) {

                    gp.playSE(26);
                    gp.playSE(25);
                    gp.playBtn = true;

                }
                if (gp.ui.exitRec.x < mouseOverX
                        && (gp.ui.exitRec.x + gp.ui.exitRec.width) > mouseOverX
                        && gp.ui.exitRec.y < mouseOverY
                        && (gp.ui.exitRec.y + gp.ui.startRec.height) > mouseOverY) {

                    System.exit(0);
                }
            }
        }

    }

    /**
     * <p>
     * This is deafult method's mouselistener
     * </p>
     * 
     * @param  e for interaction
     * @since 1.0
     */
    @Override
    public void mouseReleased(MouseEvent e) {
    }

    /**
     * <p>
     * This is deafult method's mouselistener
     * </p>
     * 
     * @param  e for interaction
     * @since 1.0
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * <p>
     * This is deafult method's mouselistener
     * </p>
     * 
     * @param e for interaction
     * @since 1.0
     */
    @Override
    public void mouseExited(MouseEvent e) {
    }

    /**
     * <p>
     * This is deafult method's mouselistener
     * </p>
     * 
     * @param e for interaction
     * @since 1.0
     */
    @Override
    public void mouseDragged(MouseEvent e) {
    }

    /**
     * <p>
     * This method using for perceiving mouse moving
     * </p>
     * 
     * @param e for interaction
     * @since 1.0
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        mouseOverX = e.getX();
        mouseOverY = e.getY();
        // System.out.println(gp.ui.respawnHereRec.x+" "+gp.ui.respawnHereRec.y+"
        // "+(gp.ui.respawnHereRec.x + gp.ui.respawnHereRec.width)+"
        // "+(gp.ui.respawnHereRec.y + gp.ui.respawnHereRec.height)+ " ::: "+
        // mouseOverX+" "+mouseOverY);
    }

    /**
     * <p>
     * This method perceive mouse moving and change the button's color.
     * </p>
     * 
     * @param  g2 for drawing 2 dimentional text or shape 
     * @since 1.0
     */
    public void hoverRespawnBtn(Graphics2D g2) {
        if (gp.ui.respawnHereRec.x < mouseOverX && (gp.ui.respawnHereRec.x + gp.ui.respawnHereRec.width) > mouseOverX
                && gp.ui.respawnHereRec.y < mouseOverY
                && (gp.ui.respawnHereRec.y + gp.ui.respawnHereRec.height) > mouseOverY)
            gp.ui.btnHover = 1;
        else if (gp.ui.respawnCityRec.x < mouseOverX
                && (gp.ui.respawnCityRec.x + gp.ui.respawnCityRec.width) > mouseOverX
                && gp.ui.respawnCityRec.y < mouseOverY
                && (gp.ui.respawnCityRec.y + gp.ui.respawnCityRec.height) > mouseOverY)
            gp.ui.btnHover = 2;
        else
            gp.ui.btnHover = 0;
    }

    /**
     * <p>
     * This method perceive mouse moving on title screen button and change the
     * button's color.
     * </p>
     * 
     * @param g2 for Graphics2D
     * @since 1.0
     */
    public void hoverTitleScreenBtn(Graphics2D g2) {
        if (gp.ui.startRec.x < mouseOverX && (gp.ui.startRec.x + gp.ui.startRec.width) > mouseOverX
                && gp.ui.startRec.y < mouseOverY
                && (gp.ui.startRec.y + gp.ui.startRec.height) > mouseOverY)
            gp.ui.btnHover = 1;
        else if (gp.ui.exitRec.x < mouseOverX
                && (gp.ui.exitRec.x + gp.ui.exitRec.width) > mouseOverX
                && gp.ui.exitRec.y < mouseOverY
                && (gp.ui.exitRec.y + gp.ui.exitRec.height) > mouseOverY)
            gp.ui.btnHover = 2;
        else
            gp.ui.btnHover = 0;
    }

    /**
     * <p>
     * This method perceive mouse moving on name text area.
     * </p>
     * 
     * @param g2 for Graphics2D
     * @since 1.0
     */
    public void hoverNameBtn(Graphics2D g2) {
        if (gp.ui.enterName) {
            if (gp.ui.saveRec.x < mouseOverX && (gp.ui.saveRec.x + gp.ui.saveRec.width) > mouseOverX
                    && gp.ui.saveRec.y < mouseOverY
                    && (gp.ui.saveRec.y + gp.ui.saveRec.height) > mouseOverY)
                gp.ui.btnHover = 1;
            else
                gp.ui.btnHover = 0;
        }
    }

    /**
     * <p>
     * This method perceive mouse moving on inventory button.
     * </p>
     * 
     * @param g2 for Graphics2D
     * @since 1.0
     */
    public void hoverInventoryOptionsBtn(Graphics2D g2) {
        if (gp.ui.inventoryRec.x < mouseOverX
                && (gp.ui.inventoryRec.x + gp.ui.inventoryRec.width) > mouseOverX
                && gp.ui.inventoryRec.y < mouseOverY
                && (gp.ui.inventoryRec.y + gp.ui.inventoryRec.height) > mouseOverY) {
            gp.ui.btnHover = 1;
        } else if (gp.ui.optionsRec.x < mouseOverX
                && (gp.ui.optionsRec.x + gp.ui.optionsRec.width) > mouseOverX
                && gp.ui.optionsRec.y < mouseOverY
                && (gp.ui.optionsRec.y + gp.ui.optionsRec.height) > mouseOverY) {
            gp.ui.btnHover = 2;
        } else {
            gp.ui.btnHover = 0;
        }
    }
}
