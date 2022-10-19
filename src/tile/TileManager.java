package tile;
import main.GamePanel;
import main.UtilityTool;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;


public class TileManager {
	
	GamePanel gp;
	public Tile tile[];
	public int mapTileNum[][];
	
	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		
		tile = new Tile[20];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		
		getTileImage();
		loadMap("/maps/map.txt");
		
	}
	
	public void getTileImage() {
			
			setup(0, "grass", false);
			setup(1, "square1", false);
			setup(2, "sand1", false);
			setup(3, "sand2", false);
			setup(4, "square2", false);
			setup(5, "tree", true);
			setup(6, "squaresand1", false);
			setup(7, "sand1sand2grass", false);
			setup(8, "sand1sand2", false);
			setup(9, "squaresand2", false);
			setup(10, "squaresand3", false);
			setup(11, "sand2grass", false);
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
			
			if(worldX > gp.player.worldX - gp.player.screenX - gp.tileSize && worldX < gp.player.worldX + gp.player.screenX + gp.tileSize &&
			    worldY > gp.player.worldY - gp.player.screenY - gp.tileSize  && worldY < gp.player.worldY + gp.player.screenY + gp.tileSize) {
				g2.drawImage(tile[tileNum].image, screenX, screenY, null);
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
	}
}
