package main;

import java.awt.Rectangle;

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
			if(gp.obj[i] != null && !gp.obj[i].deadObj) {
				
				// Get Entity's solid area position
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				
				// Get Object's solid area position
				gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
				gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

				
				switch(entity.direction) {
                    case "up":      entity.solidArea.y -= entity.speed; break;
                    case "down":    entity.solidArea.y += entity.speed; break;
                    case "left":    entity.solidArea.x -= entity.speed; break;
                    case "right":   entity.solidArea.x += entity.speed; break;
                }
				
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
		System.out.println("crashed index: " + index);
		return index;
	}
	
	// NPC or ENEMY Collision
	public int checkEntity(Entity entity, Entity[] target) {
        int index = -1;
        
        for(int i=0; i < target.length; i++ ) {
            if(target[i] != null) {
                
                // Get Entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                
                // Get Target's solid area position
                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;
                
                switch(entity.direction) {
                    case "up":          entity.solidArea.y -= entity.speed;                                     break;
                    case "upleft":      entity.solidArea.y -= entity.speed; entity.solidArea.x -= entity.speed; break;
                    case "upright":     entity.solidArea.y -= entity.speed; entity.solidArea.x += entity.speed; break;
                    case "down":        entity.solidArea.y += entity.speed;                                     break;
                    case "left":        entity.solidArea.x -= entity.speed;                                     break;
                    case "downleft":    entity.solidArea.y += entity.speed; entity.solidArea.x -= entity.speed; break;
                    case "downright":   entity.solidArea.y += entity.speed; entity.solidArea.x += entity.speed; break;
                    case "right":       entity.solidArea.x += entity.speed;                                     break;
                }
                
                if(entity.solidArea.intersects(target[i].solidArea)) {
                    if(target[i] != entity) { // to avoid detect itself
                        entity.collisionOn = true;
                        index = i;      
                    }
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }
        }
        
        return index;
	}
	
	public boolean checkPlayer(Entity entity) {
	    
	    boolean contactPlayer = false;
	    
        // Get Entity's solid area position
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;
        
        // Get Player's solid area position
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        
        switch(entity.direction) {
            case "up":          entity.solidArea.y -= entity.speed;                                     break;
            case "upleft":      entity.solidArea.y -= entity.speed; entity.solidArea.x -= entity.speed; break;
            case "upright":     entity.solidArea.y -= entity.speed; entity.solidArea.x += entity.speed; break;
            case "down":        entity.solidArea.y += entity.speed;                                     break;
            case "left":        entity.solidArea.x -= entity.speed;                                     break;
            case "downleft":    entity.solidArea.y += entity.speed; entity.solidArea.x -= entity.speed; break;
            case "downright":   entity.solidArea.y += entity.speed; entity.solidArea.x += entity.speed; break;
            case "right":       entity.solidArea.x += entity.speed;                                     break;
        }
        
        if(entity.solidArea.intersects(gp.player.solidArea)) {
            entity.collisionOn = true;
            contactPlayer = true;
        }

        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        
        return contactPlayer;
	}
	
	
	public int checkFightArea(Entity entity, Entity enemy[]) {
	    int index = -1;
        
        for(int i=0; i < enemy.length; i++ ) {
            if(enemy[i] != null) {
                
                int enemyX = enemy[i].worldX;
                int enemyY = enemy[i].worldY;
                
                int playerX = entity.worldX + entity.solidArea.x / 2;
                int playerY = entity.worldY + entity.solidArea.y / 2;
                
                int diffX = Math.abs(playerX - enemyX);
                int diffY = Math.abs(playerY - enemyY);
                
                double hipotenus = Math.sqrt(diffX * diffX + diffY * diffY);
                
                double distance = Math.sqrt(gp.tileSize * gp.tileSize + gp.tileSize * gp.tileSize);
                
                if(hipotenus <= distance) {
                    index = i;
                }
                /*
                // Get Entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                
                // Get Enemy's Fight Area (3*3)
                int enemyAreaX = enemy[i].worldX - gp.tileSize;         // Subtract tileSize from enemy's location X
                int enemyAreaY = enemy[i].worldY - gp.tileSize;         // Subtract tileSize from enemy's location Y
                int enemyAreaWidth = gp.tileSize * 3;                   // Width equals to 3 times of tileSize
                int enemyAreaHeight = gp.tileSize * 3;                  // Heights equals to 3 times of tileSize
                
                Rectangle enemyArea = new Rectangle(enemyAreaX, enemyAreaY, enemyAreaWidth, enemyAreaHeight);

                if(entity.solidArea.intersects(enemyArea)) {
                    index = i;
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                */
            }
        }
        
        return index;
	}
	
	// To check is enemy around player for standing
	public boolean checkFightAreaForEnemy(Entity entity, Entity enemy) {
        
        int enemyX = enemy.worldX;
        int enemyY = enemy.worldY;
        
        int playerX = entity.worldX + entity.solidArea.x / 2;
        int playerY = entity.worldY + entity.solidArea.y / 2;
        
        int diffX = Math.abs(playerX - enemyX);
        int diffY = Math.abs(playerY - enemyY);
        
        double hipotenus = Math.sqrt(diffX * diffX + diffY * diffY);
        
        double distance = Math.sqrt(gp.tileSize * gp.tileSize + gp.tileSize * gp.tileSize);
        
        if(hipotenus <= 50) {
            return true;
        }
        return false;
        
    }
	
	// New Entity Collisions
	public void checkTileForNewEntity(int entityWorldX, int entityWorldY) {
        int tileNum = gp.tileM.mapTileNum[entityWorldX / gp.tileSize][entityWorldY / gp.tileSize];
                
        if(gp.tileM.tile[tileNum].collision) {
            gp.aSetter.collisionOn = true;
        }
    }
    
    public void checkEntityForNewEntity(int entityWorldX, int entityWorldY,  Entity[] target) {
        // Get Entity's solid area position
        Rectangle newEntitySolidArea = new Rectangle(entityWorldX, entityWorldY, gp.tileSize, gp.tileSize);
        
        for(int i=0; i < target.length; i++ ) {
            if(target[i] != null) {
                // Get Target's solid area position
                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;
                
                if(newEntitySolidArea.intersects(target[i].solidArea)) {
                    gp.aSetter.collisionOn = true;
                }
                
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }
        }
    }
    
    public void checkPlayerForNewEntity(int entityWorldX, int entityWorldY) {     
        // Get Entity's solid area position
        Rectangle newEntitySolidArea = new Rectangle(entityWorldX, entityWorldY, gp.tileSize, gp.tileSize);

        // Get Player's solid area position
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        
        if(newEntitySolidArea.intersects(gp.player.solidArea)) {
            gp.aSetter.collisionOn = true;
        }
        
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
    }
}
