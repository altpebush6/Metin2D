package main;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import ai.PathFinder;
import data.SaveLoad;
import entity.Entity;
import entity.Player;
import entity.Skills;
import tile.TileManager;

/**
 * <p>
 * This Class include gaming main methods.
 * İmplements Runnable interfaces and inheritance Jpanel
 * </p>
 */
public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    /**
     * <p>
     * This is original Tile size set the 16
     * </p>
     */
    final int originalTileSize = 16;
    /**
     * <p>
     * This is scale for different situation
     * </p>
     */
    final int scale = 3;
    /**
     * <p>
     * This is tile size
     * </p>
     */
    public final int tileSize = originalTileSize * scale; // 48x48 tile
    /**
     * <p>
     * This is max screen column size
     * </p>
     */
    public final int maxScreenCol = 32;
    /**
     * <p>
     * This is max screen row size
     * </p>
     */
    public final int maxScreenRow = 18;
    /**
     * <p>
     * This is screen width. It is obtained by multiplying the tile size by the
     * number of columns.
     * </p>
     */
    public final int screenWidth = tileSize * maxScreenCol; // 960 pixels
    /**
     * <p>
     * This is screen height. It is obtained by multiplying the tile size by the
     * number of rows.
     * </p>
     */
    public final int screenHeight = tileSize * maxScreenRow; // 1080 pixels

    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    /**
     * <p>
     * This is maximum world row size
     * </p>
     */
    public final int maxWorldRow = 50;
    /**
     * <p>
     * Checks if the character is on the left edge of the map.
     * </p>
     */
    public boolean isPlayerAtLeftEdge = false;
    /**
     * <p>
     * Checks if the character is on the right edge of the map.
     * </p>
     */
    public boolean isPlayerAtRightEdge = false;
    /**
     * <p>
     * Checks if the character is on the top edge of the map.
     * </p>
     */
    public boolean isPlayerAtTopEdge = false;
    /**
     * <p>
     * Checks if the character is on the bottom edge of the map.
     * </p>
     */
    public boolean isPlayerAtBottomEdge = false;
    // FOR FULL SCREEN
    /**
     * <p>
     * For full screen screen width
     * </p>
     */
    int screenWidth2 = screenWidth;
    /**
     * <p>
     * For full screen screen height
     * </p>
     */
    int screenHeight2 = screenHeight;
    /**
     * <p>
     * For full screen temporary screen image
     * </p>
     */
    public BufferedImage tempScreen;

    /**
     * <p>
     * Main drawing component
     * </p>
     */
    public Graphics2D g2;

    /**
     * <p>
     * This keeps full screen status
     * </p>
     */
    public boolean fullScreenOn = false;

    // FPS
    /**
     * <p>
     * This is frame per second
     * </p>
     */
    public int FPS = 500;

    // SYSTEM
    /**
     * <p>
     * This is key handler. This handling keyboard input
     * </p>
     */
    public KeyHandler keyH = new KeyHandler(this);

    /**
     * <p>
     * This is mouse handler. This handling mouse input.
     * </p>
     */
    public MouseHandler mouseH = new MouseHandler(this);

    /**
     * <p>
     * This is sound track for game sound instance
     * </p>
     */
    Sound soundtrack = new Sound();

    /**
     * <p>
     * This is sound track for effects sound instance
     * </p>
     */
    Sound se = new Sound(); // sound effects

    /**
     * <p>
     * This is tile mananager instance
     * </p>
     */
    public TileManager tileM = new TileManager(this);

    /**
     * <p>
     * This is tile collision checker instance
     * </p>
     */
    public CollisionChecker collisionChecker = new CollisionChecker(this);

    /**
     * <p>
     * This is tile assetsetter class instance
     * </p>
     */
    public AssetSetter aSetter = new AssetSetter(this);

    /**
     * <p>
     * This is tile utility tool instance
     * </p>
     */
    public UtilityTool uTool = new UtilityTool();

    /**
     * <p>
     * This is tile pathFinder instance
     * </p>
     */
    public PathFinder pathFinder = new PathFinder(this);

    /**
     * <p>
     * This is game thread
     * </p>
     */
    public Thread gameThread;

    /**
     * <p>
     * This is ui instance
     * </p>
     */
    public UI ui = new UI(this);

    /**
     * <p>
     * This is save load instance
     * </p>
     */
    public SaveLoad saveLoad = new SaveLoad(this);

    // ENTITY AND OBJECT

    /**
     * <p>
     * This is object array
     * </p>
     */
    public Entity obj[] = new Entity[99999];

    /**
     * <p>
     * This is collected object array
     * </p>
     */
    public Entity collect[] = new Entity[99999];

    /**
     * <p>
     * This is player instance
     * </p>
     */
    public Player player = new Player(this, keyH, mouseH);
    /**
     * <p>
     * This is enemy array
     * </p>
     */
    public Entity enemy[] = new Entity[99999];

    /**
     * <p>
     * This is npc array.
     * </p>
     */
    public Entity npc[] = new Entity[10];

    /**
     * <p>
     * this series keeps players and enemies
     * </p>
     */
    ArrayList<Entity> entityList = new ArrayList<>();

    /**
     * <p>
     * This is skills instance. Player has specific skills
     * </p>
     */
    public Skills skills = new Skills(this);

    // GAME STATE
    /**
     * <p>
     * Game State Check the game status
     * </p>
     */
    public int gameState;

    /**
     * <p>
     * This is loading page state. If game state equals loading state show the
     * loading page
     * </p>
     */
    public final int loadingState = 0;

    /**
     * <p>
     * This is player state. If game state equals player state play game.
     * </p>
     */
    public final int playState = 1;

    /**
     * <p>
     * This is pause state. If game state equals pause state, game paused
     * </p>
     */
    public final int pauseState = 2;

    /**
     * <p>
     * This is dialogue state. If game state equals dialogue state, show dialogue
     * </p>
     */
    public final int dialogueState = 3;

    /**
     * <p>
     * This is inventory state. If game state equals inventory state, show inventory
     * </p>
     */
    public final int inventoryState = 4;

    /**
     * <p>
     * This is options state. If game state equals options state, open options menu
     * </p>
     */
    public final int optionsState = 5;

    /**
     * <p>
     * This is deadState state. If game state equals deadState state, show respawn
     * buttons
     * </p>
     */
    public final int deadState = 6;

    /**
     * <p>
     * This is tradeState state. If game state equals tradeState state, show trade
     * </p>
     */
    public final int tradeState = 7;

    /**
     * <p>
     * This is titleState state. If game state equals titleState state, show
     * character status and start end exit button
     * </p>
     */
    public final int titleState = 8;

    /**
     * <p>
     * This is enchantState state. If game state equals enchantState state opening
     * blacksmith page
     * </p>
     */
    public final int enchantState = 9;

    /**
     * <p>
     * This check the play button status
     * </p>
     */
    public boolean playBtn = false;

    /**
     * <p>
     * This check the game load status
     * </p>
     */
    public boolean gameLoad = true;

    /**
     * <p>
     * This check the load screen status
     * </p>
     */
    public boolean loadScreen = false;

    /**
     * <p>
     * This check the load screen status.If is opening set the 1 otherwise 0
     * </p>
     */
    public int loadScreenControl = 0;

    /**
     * <p>
     * This check the end game .if the game is over it gets the true value
     * </p>
     */
    public boolean endGame = false;

    /**
     * <p>
     * This check the exit status .If it gets the true value, the game is closed.
     * </p>
     */
    public boolean exit = false;

    /**
     * <p>
     * The values ​​of this field when counting down as the game closes
     * </p>
     */
    public int endGameCounter = 0;

    /**
     * <p>
     * This field set end game time
     * </p>
     */
    public int endGameTime = 180;

    /**
     * <p>
     * This field check the loading music status
     * </p>
     */
    public boolean loadMusic = false;

    /**
     * <p>
     * This field check the loading character music status
     * </p>
     */
    public boolean loadCharMusic = false;

    /**
     * <p>
     * This field count button
     * </p>
     */
    public int btnCounter = 0;

    /**
     * <p>
     * This field hold button click timeout
     * </p>
     */
    public int btnTimeOut = 120;

    /**
     * <p>
     * This is game panel constructor.Set the setPreferredSize,setDoubleBuffered,
     * and add addKeyListener,addMouseListener
     * </p>
     */
    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // Set the size of this class (JPanel)
        // this.setBackground(Color.black);
        this.setDoubleBuffered(true);// If set true, all the drawing from this component will be done in an offscreen
                                     // pointing buffer. IMPROVE GAME RENDERING PERFORMANS

        this.addKeyListener(keyH);
        this.addMouseListener(mouseH);
        this.setFocusable(true);
    }

    /**
     * <p>
     * Set up game. Set npc , draw screen and save load
     * </p>
     * 
     * @since 1.0
     */
    public void setupGame() {

        aSetter.setNpc();

        tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D) tempScreen.getGraphics();

        setFullScreen();

        gameState = titleState;

        saveLoad.load();

    }

    /**
     * <p>
     * If the character dies, it will respawn
     * </p>
     * 
     * @param rebornInCenter Allows the player to respawn where they died
     * @since 1.0
     */
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

    /**
     * <p>
     * This method set the full screen mode
     * </p>
     * 
     * @since 1.0
     */
    public void setFullScreen() {
        // GET LOCAL SCREEN DEVICE
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(Main.window);

        // GET FULL SCREEN WIDTH AND HEIGHT

        screenWidth2 = Main.window.getWidth();
        screenHeight2 = Main.window.getHeight();

    }

    /**
     * <p>
     * This method start game thread
     * </p>
     * 
     * @since 1.0
     */
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * <p>
     * It allows 60 frames per second to be drawn on the screen by making FPS
     * adjustments
     * </p>
     * 
     * @since 1.0
     */
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

    /**
     * <p>
     * This method updates everything according to their new status according to the
     * state of the game
     * </p>
     * 
     * @since 1.0
     */
    public void update() {

        mouseH.hoverInventoryOptionsBtn(g2);

        if (playBtn) {
            btnCounter++;
            if (btnCounter == btnTimeOut) {
                loadScreen = true;
                gameLoad = false;
                gameState = loadingState;
            }
        }

        if (gameState == titleState) {
            mouseH.hoverTitleScreenBtn(g2);
            mouseH.hoverNameBtn(g2);
        }

        if (loadScreen && loadScreenControl == 0) {
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

    /**
     * <p>
     * This method changes the opacity of the drawing.
     * </p>
     * 
     * @param g2         is the drawing component
     * @param alphaValue set the opacity
     * @since 1.0
     */
    public void changeAlpha(Graphics2D g2, float alphaValue) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }

    /**
     * <p>
     * This method draws drawings on a temporary screen to make the game full
     * screen.
     * </p>
     * 
     * @since 1.0
     */
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
    

    /**
     * <p>
     * This method draws the drawings on the temporary screen to the main screen.
     * screen.
     * </p>
     * 
     * @since 1.0
     */
    public void drawToScreen() {
        Graphics g = getGraphics();
        g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
        g.dispose();
    }
    

    /**
     * <p>
     * This method plays music
     * screen.
     * </p>
     * param index choose sound track 
     * @since 1.0
     */
    public void playMusic(int index) {

        soundtrack.setFile(index);
        soundtrack.play();
        soundtrack.loop();
    }
    

    /**
     * <p>
     * This method stops music
     * screen.
     * </p>
     * 
     * @since 1.0
     */
    public void stopMusic() {

        soundtrack.stop();
    }
    
    /**
     * <p>
     * This method plays sound effects 
     * screen.
     * </p>
     * @param index set sound effects 
     * @since 1.0
     */
    public void playSE(int index) { // Sound Effects

        se.setFile(index);
        se.play();
    }

}
