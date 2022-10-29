package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{

	// SCREEN SETTINGS
	final int originalTileSize = 16;
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale; // 48x48 tile
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	
	public final int screenWidth = tileSize * maxScreenCol; // 1920 pixels
	public final int screenHeight = tileSize * maxScreenRow; // 1080 pixels
	
	// WORLD SETTINGS
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;

	// FPS
	final int FPS = 60;

	// SYSTEM	
	KeyHandler keyH = new KeyHandler(this);
	MouseHandler mouseH = new MouseHandler(this);
	Sound soundtrack = new Sound();
	Sound se = new Sound(); // sound effects
	TileManager tileM = new TileManager(this);
	public CollisionChecker collisionChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	Thread gameThread;
	
	// ENTITY AND OBJECT
	public Player player = new Player(this,keyH,mouseH);
	public Entity obj[] = new Entity[10];
	public Entity enemy[] = new Entity[10];
	ArrayList<Entity> entityList = new ArrayList<>();
	

	
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth,screenHeight)); // Set the size of this class (JPanel)
		//this.setBackground(Color.black); 
		this.setDoubleBuffered(true);// If set true, all the drawing from this component will be done in an offscreen pointing buffer. IMPROVE GAME RENDERING PERFORMANS
	
		this.addKeyListener(keyH);
		this.addMouseListener(mouseH);
		this.setFocusable(true);
	}

	public void setupGame() {
		
		aSetter.setObject();
		aSetter.setEnemy();
		
		playMusic(0);
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void run() {
		
		//  when press a key button computer catches it too many times. to avoid this, we use 2 different methods.
		
		// FIRST METHOD to avoid
		double drawInterval = 1000000000/FPS; // 0.01666 seconds = 16,666,666 nanoseconds
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		long timer = 0;
		int drawCount = 0;
			
		while(gameThread != null) {
		
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime); // To show FPS
			lastTime = currentTime;
			
			if(delta > 1) {
				update();
				repaint();
				delta --;
				drawCount++; // To show FPS
			}
			if(timer >= 1000000000) {
				//System.out.println("FPS:"+drawCount);
				drawCount = 0;
				timer = 0;
			}
		}
			
		/* // SECOND METHOD to avoid 
		double drawInterval = 1000000000/FPS; // 0.01666 seconds 
		double nextDrawTime = System.nanoTime() + drawInterval;
		
		while(gameThread != null) {
			//System.out.println("The Game Loop is running!");
			
			long currentTime = System.nanoTime(); // Returns current value of the running Java Virtual Machine's high-resolution time source in nanoseconds
			System.out.println("current time: "+currentTime);
			
			
			// 1 UPDATE: update information such as character position
			update();
			// 2 DRAW: draw the screen with the updated information
			repaint();
			
			try { // it won't do anything until sleep time is over.
				
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000; // nano to mili seconds
				
				if(remainingTime < 0) {
					remainingTime = 0;
				}
				
				Thread.sleep((long) remainingTime);
				
				nextDrawTime += drawInterval;
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			*/
		
	}
	
	public void update() {
		
	    // Player
		player.update();
		
		// Enemy
		for(int i = 0; i < enemy.length; i++) {
		    if(enemy[i] != null) {
		        enemy[i].update();
		    }
		}
		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		// DEBUG
		long startTime = 0;
		if(keyH.openDebug) {
			startTime = System.nanoTime();
		}
		
		// TILE
		tileM.draw(g2);
		
		// OBJECTS 
        for(int i=0; i < obj.length; i++) {
            if(obj[i] != null) {
                obj[i].draw(g2);
            }
        }
		
		// ADD ENTITIES TO THE LIST
		entityList.add(player);
		
		/*
		for(int i=0; i < obj.length; i++) {
		    if(obj[i] != null) {
		        entityList.add(obj[i]);
		    }
		}
		*/
		
		for(int i=0; i < enemy.length; i++) { 
            if(enemy[i] != null) {
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
		for(int i=0; i < entityList.size(); i++) {
		    entityList.get(i).draw(g2);
		}
		// EMPTY ENTITY LIST 
		entityList.clear();
		

		
		// UI
		ui.draw(g2);
		
		// DEBUG (How many seconds it takes to draw the things above)
		if(keyH.openDebug) {
			long endTime = System.nanoTime();
			long diff = endTime - startTime;
			g2.drawString("Drawing Time: " + (diff*1.0/1000000000), 10, 300);
		}
		
		g2.dispose();
		
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
