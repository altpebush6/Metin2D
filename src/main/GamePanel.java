package main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ai.PathFinder;
import data.SaveLoad;
import entity.Entity;
import entity.Player;
import entity.Skills;
import tile.TileManager;
import main.Main;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 32;
    public final int maxScreenRow = 18;

    public final int screenWidth = tileSize * maxScreenCol; // 960 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 1080 pixels

    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public boolean isPlayerAtLeftEdge = false;
    public boolean isPlayerAtRightEdge = false;
    public boolean isPlayerAtTopEdge = false;
    public boolean isPlayerAtBottomEdge = false;
    // FOR FULL SCREEN
    int screenWidth2 = screenWidth;
    int screenHeight2 = screenHeight;
    BufferedImage tempScreen;
    public Graphics2D g2;
    public boolean fullScreenOn = false;

    // FPS
    public int FPS = 500;

    // SYSTEM
    public KeyHandler keyH = new KeyHandler(this);
    public MouseHandler mouseH = new MouseHandler(this);;
    Sound soundtrack = new Sound();
    Sound se = new Sound(); // sound effects
    public TileManager tileM = new TileManager(this);
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UtilityTool uTool = new UtilityTool();
    public PathFinder pathFinder = new PathFinder(this);

    Thread gameThread;
    public UI ui = new UI(this);
    SaveLoad saveLoad = new SaveLoad(this);

    // ENTITY AND OBJECT

    public Entity obj[] = new Entity[99999];
    public Entity collect[] = new Entity[99999];
    public Player player = new Player(this, keyH, mouseH);
    public Entity enemy[] = new Entity[99999];
    public Entity npc[] = new Entity[10];
    ArrayList<Entity> entityList = new ArrayList<>();
    public Skills skills = new Skills(this);

    // GAME STATE
    public int gameState;
    public final int loadingState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int inventoryState = 4;
    public final int optionsState = 5;
    public final int deadState = 6;
    public final int tradeState = 7;
    public final int titleState = 8;
    public final int enchantState = 9;

    public boolean playBtn = false;
    public boolean gameLoad = true;
    public boolean loadScreen = false;
    public int loadScreenControl = 0;

    public boolean endGame = false, exit = false;
    public int endGameCounter = 0, endGameTime = 180;
    
    public boolean loadMusic = false;
    public boolean loadCharMusic = false;
    
    public int btnCounter = 0;
    public int btnTimeOut = 120;

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // Set the size of this class (JPanel)
        // this.setBackground(Color.black);
        this.setDoubleBuffered(true);// If set true, all the drawing from this component will be done in an offscreen
                                     // pointing buffer. IMPROVE GAME RENDERING PERFORMANS

        this.addKeyListener(keyH);
        this.addMouseListener(mouseH);
        this.setFocusable(true);
    }

    public void setupGame() {

        aSetter.setNpc();
        
        
        tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D) tempScreen.getGraphics();

        setFullScreen();

        gameState = titleState;

        saveLoad.load();

    }

    public void reborn(boolean rebornInCenter) {
        player.reborn = true;
        player.rebornCounter = 0;
        player.attacking = false;
        player.direction = "down";
        player.dying = false;
        player.onPath = false;
        mouseH.pressed = false;
        mouseH.pressedOnEnemy = false;
        player.autoHit = false;

        if (rebornInCenter) {
            player.setDefaultPositions();
        }
    }

    public void setFullScreen() {
        // GET LOCAL SCREEN DEVICE
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(Main.window);

        // GET FULL SCREEN WIDTH AND HEIGHT

        screenWidth2 = Main.window.getWidth();
        screenHeight2 = Main.window.getHeight();

    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        // when press a key button computer catches it too many times. to avoid this, we
        // use 2 different methods.

        // FIRST METHOD to avoid
        double drawInterval = 1000000000 / FPS; // 0.01666 seconds = 16,666,666 nanoseconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime); // To show FPS
            lastTime = currentTime;

            if (delta > 1) {
                update();
                drawToTempScreen(); // draw everything to the buffered image
                drawToScreen(); // draw the buffered image to the screen
                delta--;
                drawCount++; // To show FPS
            }
            if (timer >= 1000000000) {
                // System.out.println("FPS:"+drawCount);
                drawCount = 0;
                timer = 0;
            }
        }

        /*
         * // SECOND METHOD to avoid
         * double drawInterval = 1000000000/FPS; // 0.01666 seconds
         * double nextDrawTime = System.nanoTime() + drawInterval;
         * 
         * while(gameThread != null) {
         * //System.out.println("The Game Loop is running!");
         * 
         * long currentTime = System.nanoTime(); // Returns current value of the running
         * Java Virtual Machine's high-resolution time source in nanoseconds
         * System.out.println("current time: "+currentTime);
         * 
         * 
         * // 1 UPDATE: update information such as character position
         * update();
         * // 2 DRAW: draw the screen with the updated information
         * repaint();
         * 
         * try { // it won't do anything until sleep time is over.
         * 
         * double remainingTime = nextDrawTime - System.nanoTime();
         * remainingTime = remainingTime/1000000; // nano to mili seconds
         * 
         * if(remainingTime < 0) {
         * remainingTime = 0;
         * }
         * 
         * Thread.sleep((long) remainingTime);
         * 
         * nextDrawTime += drawInterval;
         * 
         * } catch (InterruptedException e) {
         * e.printStackTrace();
         * }
         * 
         */

    }

    public void update() {
        
        mouseH.hoverInventoryOptionsBtn(g2);

        if(playBtn) {
            btnCounter++;
            if(btnCounter == btnTimeOut) {
                loadScreen = true;
                gameLoad = false;
                gameState = loadingState;
            }
        }
        
        if(gameState == titleState) {
            mouseH.hoverTitleScreenBtn(g2);
            mouseH.hoverNameBtn(g2);
        }
        
        if(loadScreen && loadScreenControl == 0) {
            stopMusic();
            tileM.loadScreen(g2);
            loadScreenControl = 1;
        }

        if (!gameLoad) {
            tileM.getTileImage(g2);
        }
        
        if (gameLoad && loadScreenControl == 1 && !loadMusic) {
            playMusic(0);
            loadMusic = true;
        }
        
        if (gameState == titleState && !loadCharMusic) {
            playMusic(28);
            loadCharMusic = true;
        }
        
        if (endGame) {

            if (endGameCounter % 60 == 0) {
                ui.addMessage("The game closes in " + (Math.abs(endGameTime - endGameCounter) / 60) + " seconds.");
            }

            endGameCounter++;

            if (endGameCounter == endGameTime) {
                exit = true;
            }
        }

        if (exit) {
            System.exit(0);         
        }


        if (gameState == playState) {
            aSetter.setEnemy();
            aSetter.setSatellite();

            player.update();

            // NPC
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].update();
                }
            }

            // Enemy
            for (int i = 0; i < enemy.length; i++) {
                if (enemy[i] != null) {
                    if (enemy[i].alive) {
                        enemy[i].update();
                    } else {
                        enemy[i] = null;
                    }
                }
            }

        }
        if (gameState == deadState) {

            player.draw(g2);

            mouseH.hoverRespawnBtn(g2);

            player.deadCounter++;

            aSetter.setEnemy();

            // NPC
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].update();
                }
            }

            // Enemy
            for (int i = 0; i < enemy.length; i++) {
                if (enemy[i] != null) {
                    if (enemy[i].alive) {
                        enemy[i].update();
                    } else {
                        enemy[i] = null;
                    }
                }
            }
        }
        if (gameState == pauseState) {
            // Nothing
        }

    }

    public void changeAlpha(Graphics2D g2, float alphaValue) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }

    public void drawToTempScreen() {

        if (gameState != loadingState) {

            // Title State
            if (gameState == titleState) {
                ui.draw(g2);
            } else {

                // TILE
                tileM.draw(g2);

                // OBJECTS

                for (int i = 0; i < obj.length; i++) {
                    if (obj[i] != null) {
                        obj[i].draw(g2);
                    }
                }

                // NPC
                for (int i = 0; i < npc.length; i++) {
                    if (npc[i] != null) {
                        npc[i].draw(g2);
                    }
                }

                // ADD ENTITIES TO THE LIST
                entityList.add(player);

                /*
                 * for(int i=0; i < obj.length; i++) {
                 * if(obj[i] != null) {
                 * entityList.add(obj[i]);
                 * }
                 * }
                 */

                for (int i = 0; i < enemy.length; i++) {
                    if (enemy[i] != null) {
                        entityList.add(enemy[i]);
                    }
                }

                // SORT ENTITIES
                Collections.sort(entityList, new Comparator<Entity>() {

                    @Override
                    public int compare(Entity e1, Entity e2) {
                        int result = Integer.compare(e1.worldY, e2.worldY);
                        return result;
                    }
                });

                // DRAW ENTITIES
                for (int i = 0; i < entityList.size(); i++) {
                    entityList.get(i).draw(g2);
                }
                // EMPTY ENTITY LIST
                entityList.clear();

                // UI
                ui.draw(g2);
            }

            // DEBUG
            long startTime = 0;
            if (keyH.openDebug) {
                startTime = System.nanoTime();
            }

            // DEBUG (How many seconds it takes to draw the things above)
            if (keyH.openDebug) {
                long endTime = System.nanoTime();
                long diff = endTime - startTime;
                g2.drawString("Drawing Time: " + (diff * 1.0 / 1000000000), 10, 300);
            }
        }

    }

    public void drawToScreen() {
        Graphics g = getGraphics();
        g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
        g.dispose();
    }

    public void playMusic(int index) {

        soundtrack.setFile(index);
        soundtrack.play();
        soundtrack.loop();
    }

    public void stopMusic() {

        soundtrack.stop();
    }

    public void playSE(int index) { // Sound Effects

        se.setFile(index);
        se.play();
    }

}
