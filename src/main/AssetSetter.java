package main;

import java.util.Random;

import enemy.ENEMY_Wolf;
import object.*;

public class AssetSetter {
	
	GamePanel gp;
	Random random = new Random();
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		
		gp.obj[0] = new OBJ_Coin(gp, random.nextInt(100));
		gp.obj[0].worldX = 32 * gp.tileSize;
		gp.obj[0].worldY = 22 * gp.tileSize;
		
		gp.obj[1] = new OBJ_Coin(gp, random.nextInt(100));
		gp.obj[1].worldX = 28 * gp.tileSize;
		gp.obj[1].worldY = 18 * gp.tileSize;
		
		gp.obj[2] = new OBJ_Dolunay(gp);
		gp.obj[2].worldX = 27 * gp.tileSize;
		gp.obj[2].worldY = 27 * gp.tileSize;
	}
	
	public void setEnemy() {
	    
	    gp.enemy[0] = new ENEMY_Wolf(gp);
	    gp.enemy[0].worldX = gp.tileSize * 30;
	    gp.enemy[0].worldY = gp.tileSize * 24;
	    
	}
}
