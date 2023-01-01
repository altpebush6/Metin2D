package tile;
import main.GamePanel;
import main.UtilityTool;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

import javax.imageio.ImageIO;


public class TileManager {
	
	GamePanel gp;
	BufferedImage loadingScreen, loadingScreen2;
	public Tile tile[];
	public int mapTileNum[][];
	boolean drawPath = false;
	public int i = 0;
	public boolean collisionControl = false;
	public int[] collisionTiles = {374,375,376,377,424,425,426,427,472,473,474,475,476,477,478,479,523,524,525,526,527,528,573,574,575,576,577,578,622,623,624,625,626,627,628,629,672,673,674,675,676,677,678,679,722,723,724,725,726,727,728,729,772,772,773,774,775,776,777,778,779,841,859,860,890,891,892,908,909,910,911,940,941,942,958,959,960,961,989,990,991,992,1008,1009,1010,1011,1039,1040,1041,1042,1058,1059,1060,1061};
	Random rand = new Random();
	
	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		
		tile = new Tile[2500];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		
		
		loadMap("/maps/newMap.txt");
		
		int randomImage = rand.nextInt(6) + 1;
		
		loadingScreen = setup("/loadingScreen/loadingScreen" + Integer.toString(randomImage), gp.screenWidth, gp.screenHeight);
		loadingScreen2 = setup("/loadingScreen/loadingBar", 148, 15);
        
	}
	
	public void loadScreen(Graphics2D g2) {
	    g2.drawImage(loadingScreen, null, 0 ,0); 
	}
	
	public void getTileImage(Graphics2D g2) {
	    String prefix = "";
	        
	    if(i % 17 == 0 && i / 7 < 305) {
	        g2.drawImage(loadingScreen2, 583, 780, i / 7, 15, null); 
	    }
	       
	    
        if(i < 9) {
            prefix = "00";
        }else if(i < 99) {
            prefix = "0";
        }else {
            prefix = "";
        }
        
        collisionControl = false;
        for(int j = 0; j < collisionTiles.length; j++) {
            if(collisionTiles[j] == i) {
                collisionControl = true;
            }
        }
        
        if(collisionControl) {
            setup(i, "tile_" + prefix + Integer.toString(i + 1), true);
        }else {
            setup(i, "tile_" + prefix + Integer.toString(i + 1), false);
        }
        
        System.out.println("tile_" + prefix + Integer.toString(i + 1));

    
        i++;
	        
        if(i == 2500) {
            gp.gameLoad = true;
            gp.gameState = gp.playState;
            gp.FPS = 60;
        }
	    
	}
	
	public void setup(int index, String imageName, boolean collision) {
		UtilityTool uTool = new UtilityTool();
		
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tile/" + imageName + ".png"));
			tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap(String mapPath) {
		try {
			InputStream is = getClass().getResourceAsStream(mapPath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
				
				String line = br.readLine();
								
				while(col < gp.maxWorldCol) {
					
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					col++;
				}
				if(col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
			
		} catch (Exception e) {
			System.out.println("Error:1");
		}
	}
	
	public void draw(Graphics2D g2) {
		int worldCol = 0;
		int worldRow = 0;
		
		
		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			
			int tileNum = mapTileNum[worldCol][worldRow] - 1;
			
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
            if(screenX < gp.maxScreenCol * gp.tileSize && screenY > gp.maxScreenRow * gp.tileSize) {   // IF PLAYER ON LEFT SIDE 
                if(worldY > gp.player.worldY - gp.player.screenY - gp.tileSize  && worldY < gp.player.worldY + gp.player.screenY + gp.tileSize) {
                    g2.drawImage(tile[tileNum].image, screenX, screenY, null);
                }
            }if(screenY < gp.maxScreenRow * gp.tileSize && screenX > gp.maxScreenCol * gp.tileSize) {   // IF PLAYER ON TOP SIDE 
                if(worldX > gp.player.worldX - gp.player.screenX - gp.tileSize && worldX < gp.player.worldX + gp.player.screenX + gp.tileSize) {
                    g2.drawImage(tile[tileNum].image, screenX, screenY, null);
                }
            }if(screenX < gp.maxScreenCol * gp.tileSize && screenY < gp.maxScreenRow * gp.tileSize) {   // IF PLAYER ON TOP LEFT SIDE 
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }else {
		         if(worldX > gp.player.worldX - gp.player.screenX - gp.tileSize && worldX < gp.player.worldX + gp.player.screenX + gp.tileSize &&
		            worldY > gp.player.worldY - gp.player.screenY - gp.tileSize  && worldY < gp.player.worldY + gp.player.screenY + gp.tileSize) {
	                     g2.drawImage(tile[tileNum].image, screenX, screenY, null);
		         }
			}

			worldCol ++;
					
			if(worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow ++;
			}
		}
		
		/*// WITH FOR LOOP
		for(row = 0; row < gp.maxWorldRow; row++) {
			for(col = 0; col < gp.maxWorldCol; col++) {
				
				int tileNum = mapTileNum[col][row];
				g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
				
				x += gp.tileSize;
			}
			x = 0;
			y += gp.tileSize;
		}
		*/
		
		if(drawPath) {
		    g2.setColor(new Color(0, 100, 255, 70));
		    
		    for(int i = 0; i < gp.pathFinder.pathList.size(); i++) {
		        
		        int worldX = gp.pathFinder.pathList.get(i).col * gp.tileSize;
	            int worldY = gp.pathFinder.pathList.get(i).row * gp.tileSize;
	            int screenX = worldX - gp.player.worldX + gp.player.screenX;
	            int screenY = worldY - gp.player.worldY + gp.player.screenY;
	            
	            g2.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);
		    }
		}
	}
	
    public BufferedImage setup(String imagePath, int width, int height) {

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image, width, height);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }
}
