package tile;
import main.GamePanel;
import main.UtilityTool;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;


public class TileManager {
	
	GamePanel gp;
	public Tile tile[];
	public int mapTileNum[][];
	boolean drawPath = false;
	
	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		
		tile = new Tile[20];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		
		getTileImage();
		loadMap("/maps/maptry.txt");
		
	}
	
	public void getTileImage() {
        setup(3, "grass", false);
        setup(6, "grassPath1", false);
		setup(0, "grassPath2", false);
		setup(1, "grassPath3", false);
		setup(5, "grassPath4", false);
		setup(2, "path1", false);
		setup(4, "path2", false);
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
			
			int tileNum = mapTileNum[worldCol][worldRow];
			
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
}
