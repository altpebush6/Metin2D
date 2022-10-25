package main;

import entity.Entity;

public class CollisionChecker {
	
	GamePanel gp;
	
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkTile(Entity entity) {
		
		int entityLeftWorldX = entity.worldX + entity.solidArea.x; // Defines how far character collision is from left side of map.
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width; // Defines how far character collision is from right side of map.
		int entityTopWorldY = entity.worldY + entity.solidArea.y; // Defines how far character collision is from top side of map.
		int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height; // Defines how far character collision is from bottom side of map.
	
		int entityLeftCol = entityLeftWorldX / gp.tileSize;
		int entityRightCol = entityRightWorldX / gp.tileSize;
		int entityTopRow = entityTopWorldY / gp.tileSize;
		int entityBottomRow = entityBottomWorldY / gp.tileSize;
		
		int tileNum1, tileNum2;
		
		switch(entity.direction) {
			case "up":
				entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
				tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
				tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
				
				// If player wants to go through a path and if there is a obstacle on path. these makes player to go around of this obstacle
				/*
				if(gp.tileM.tile[tileNum1].collision) {
					entity.worldX += entity.speed;
					entity.collisionOn = true;
				}else if(gp.tileM.tile[tileNum2].collision){
					entity.worldX -= entity.speed;
					entity.collisionOn = true;
				}
				*/
				
				if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
					entity.collisionOn = true;
				}

				break;
				
			case "upleft":
				entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
				tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
				
				if(gp.tileM.tile[tileNum1].collision) {
					entity.collisionOn = true;
				}
				break;
				
			case "upright":
				entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
				tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
				
				if(gp.tileM.tile[tileNum1].collision) {
					entity.collisionOn = true;
				}
				break;
				
			case "down":
				entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
				tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
				tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
				
				// If player wants to go through a path and if there is a obstacle on path. these makes player to go around of this obstacle
				/*
				if(gp.tileM.tile[tileNum1].collision) {
					entity.worldX += entity.speed;
					entity.collisionOn = true;
				}else if(gp.tileM.tile[tileNum2].collision) {
					entity.worldX -= entity.speed;
					entity.collisionOn = true;
				}
				*/
				
				if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
					entity.collisionOn = true;
				}
				
				break;
				
			case "downleft":
				entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
				tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
				
				if(gp.tileM.tile[tileNum1].collision) {
					entity.collisionOn = true;
				}
				break;
				
			case "downright":
				entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
				tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
				if(gp.tileM.tile[tileNum1].collision) {
					entity.collisionOn = true;
				}
				break;
				
			case "left":
				entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;

				tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
				tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
				
				// If player wants to go through a path and if there is a obstacle on path. these makes player to go around of this obstacle
				/*
				if(gp.tileM.tile[tileNum1].collision) {
					entity.worldY += entity.speed;
					entity.collisionOn = true;
				}else if(gp.tileM.tile[tileNum2].collision) {
					entity.worldY -= entity.speed;
					entity.collisionOn = true;
				}
				*/
				
				if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
					entity.collisionOn = true;
				}
				
				break;
				
			case "right":
				entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;

				tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
				tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
				
				// If player wants to go through a path and if there is a obstacle on path. these makes player to go around of this obstacle
				/*
				if(gp.tileM.tile[tileNum1].collision) {
					entity.worldY += entity.speed;
					entity.collisionOn = true;
				}else if(gp.tileM.tile[tileNum2].collision) {
					entity.worldY -= entity.speed;
					entity.collisionOn = true;
				}
				*/
				
				if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
					entity.collisionOn = true;
				}
				
				break;
		}
	}
	
	public int checkObject(Entity entity, boolean player) {
		
		int index = -1;
		
		for(int i=0; i < gp.obj.length; i++ ) {
			if(gp.obj[i] != null) {
				
				// Get Entity's solid area position
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				
				// Get Object's solid area position
				gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
				gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

				entity.solidArea.y -= entity.speed;
				if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
					if(gp.obj[i].collision) {
						entity.collisionOn = true;
					}
					if(player) {
						index = i;
					}
				}
				
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
				gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
				
			
			}
		}
		
		return index;
		
	}
}