package main;

import java.util.Random;

import object.*;

public class AssetSetter {
	
	GamePanel gp;
	Random random = new Random();
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		
		gp.obj[0] = new OBJ_Coin(random.nextInt(100));
		gp.obj[0].worldX = 32 * gp.tileSize;
		gp.obj[0].worldY = 22 * gp.tileSize;
		
		gp.obj[1] = new OBJ_Coin(random.nextInt(100));
		gp.obj[1].worldX = 28 * gp.tileSize;
		gp.obj[1].worldY = 18 * gp.tileSize;
		
		gp.obj[2] = new OBJ_Dolunay();
		gp.obj[2].worldX = 27 * gp.tileSize;
		gp.obj[2].worldY = 27 * gp.tileSize;
	}
}
