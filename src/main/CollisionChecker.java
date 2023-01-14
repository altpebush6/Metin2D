package main;

import java.awt.Rectangle;
import entity.Entity;

/**
 * <p>
 * This Class controls the collision status of the player and objects.
 * </p>
 */
public class CollisionChecker {
	
	GamePanel gp;
	
	/**
     * <p>
     * this is constructor
     * </p>
     * 
     * @param gp is the game panel
     * @since 1.0
     */
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}
	
	/**
     * <p>
     * this is tile collision checker. Collision is true if there is a solid object in the direction of the entity
     * </p>
     * 
     * @param entity is may be player or enemy
     * @since 1.0
     */
	public void checkTile(Entity entity) {
	    /**
	     * <p>
	     * Defines how far character collision is from left side of map.
	     * </p>
	     */
        int entityLeftWorldX = entity.worldX + entity.solidArea.x; 
        /**
         * <p>
         *  Defines how far character collision is from right side of map.
         * </p>
         */
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        /**
         * <p>
         * Defines how far character collision is from top side of map.
         * </p>
         */
        int entityTopWorldY = entity.worldY + entity.solidArea.y; 
        /**
         * <p>
         * Defines how far character collision is from bottom side of map.
         * </p>
         */
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height; 
    
        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;
        
	    try{
	        int tileNum1, tileNum2;
	        
	        switch(entity.direction) {
	            case "up":
	                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
	                
                    if(entityRightCol >= gp.maxWorldCol) entityRightCol = gp.maxScreenCol - 1;
                    if(entityLeftCol < 0) entityLeftCol = 0;
                    if(entityLeftCol >= gp.maxWorldCol) entityLeftCol = gp.maxScreenCol - 1;
                    if(entityBottomRow >= gp.maxWorldRow) entityBottomRow = gp.maxScreenCol - 1;
                    if(entityTopRow < 0) entityTopRow = 0;
                    if(entityTopRow >= gp.maxWorldCol) entityTopRow = gp.maxScreenCol - 1;
	                
	                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
	                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
	                
	                if(tileNum1 >= 2500)  tileNum1 = 2499;
	                if(tileNum2 >= 2500)  tileNum2 = 2499;
	                
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

                    if(entityRightCol >= gp.maxWorldCol) entityRightCol = gp.maxScreenCol - 1;
                    if(entityLeftCol < 0) entityLeftCol = 0;
                    if(entityLeftCol >= gp.maxWorldCol) entityLeftCol = gp.maxScreenCol - 1;
                    if(entityBottomRow >= gp.maxWorldRow) entityBottomRow = gp.maxScreenCol - 1;
                    if(entityTopRow < 0) entityTopRow = 0;
                    if(entityTopRow >= gp.maxWorldCol) entityTopRow = gp.maxScreenCol - 1;
                    
	                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
	                
	                if(tileNum1 >= 2500)  tileNum1 = 2499;
	                
	                if(gp.tileM.tile[tileNum1].collision) {
	                    entity.collisionOn = true;
	                }
	                break;
	                
	            case "upright":
	                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
	                
                    if(entityRightCol >= gp.maxWorldCol) entityRightCol = gp.maxScreenCol - 1;
                    if(entityLeftCol < 0) entityLeftCol = 0;
                    if(entityLeftCol >= gp.maxWorldCol) entityLeftCol = gp.maxScreenCol - 1;
                    if(entityBottomRow >= gp.maxWorldRow) entityBottomRow = gp.maxScreenCol - 1;
                    if(entityTopRow < 0) entityTopRow = 0;
                    if(entityTopRow >= gp.maxWorldCol) entityTopRow = gp.maxScreenCol - 1;
                    
	                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
	                
	                if(tileNum1 >= 2500)  tileNum1 = 2499;
	                
	                if(gp.tileM.tile[tileNum1].collision) {
	                    entity.collisionOn = true;
	                }
	                break;
	                
	            case "down":
	                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
	                
                    if(entityRightCol >= gp.maxWorldCol) entityRightCol = gp.maxScreenCol - 1;
                    if(entityLeftCol < 0) entityLeftCol = 0;
                    if(entityLeftCol >= gp.maxWorldCol) entityLeftCol = gp.maxScreenCol - 1;
                    if(entityBottomRow >= gp.maxWorldRow) entityBottomRow = gp.maxScreenCol - 1;
                    if(entityTopRow < 0) entityTopRow = 0;
                    if(entityTopRow >= gp.maxWorldCol) entityTopRow = gp.maxScreenCol - 1;
	                
	                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
	                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
	                
	                if(tileNum1 >= 2500)  tileNum1 = 2499;
	                if(tileNum2 >= 2500)  tileNum2 = 2499;
	                
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
	                
                    if(entityRightCol >= gp.maxWorldCol) entityRightCol = gp.maxScreenCol - 1;
                    if(entityLeftCol < 0) entityLeftCol = 0;
                    if(entityLeftCol >= gp.maxWorldCol) entityLeftCol = gp.maxScreenCol - 1;
                    if(entityBottomRow >= gp.maxWorldRow) entityBottomRow = gp.maxScreenCol - 1;
                    if(entityTopRow < 0) entityTopRow = 0;
                    if(entityTopRow >= gp.maxWorldCol) entityTopRow = gp.maxScreenCol - 1;
	                
	                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
	                
                    if(tileNum1 >= 2500)  tileNum1 = 2499;
	                
	                if(gp.tileM.tile[tileNum1].collision) {
	                    entity.collisionOn = true;
	                }
	                break;
	                
	            case "downright":
	                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
	                
                    if(entityRightCol >= gp.maxWorldCol) entityRightCol = gp.maxScreenCol - 1;
                    if(entityLeftCol < 0) entityLeftCol = 0;
                    if(entityLeftCol >= gp.maxWorldCol) entityLeftCol = gp.maxScreenCol - 1;
                    if(entityBottomRow >= gp.maxWorldRow) entityBottomRow = gp.maxScreenCol - 1;
                    if(entityTopRow < 0) entityTopRow = 0;
                    if(entityTopRow >= gp.maxWorldCol) entityTopRow = gp.maxScreenCol - 1;
	                
	                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
	                
                    if(tileNum1 >= 2500)  tileNum1 = 2499;

	                if(gp.tileM.tile[tileNum1].collision) {
	                    entity.collisionOn = true;
	                }
	                break;
	                
	            case "left":
	                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
	                
                    if(entityRightCol >= gp.maxWorldCol) entityRightCol = gp.maxScreenCol - 1;
                    if(entityLeftCol < 0) entityLeftCol = 0;
                    if(entityLeftCol >= gp.maxWorldCol) entityLeftCol = gp.maxScreenCol - 1;
                    if(entityBottomRow >= gp.maxWorldRow) entityBottomRow = gp.maxScreenCol - 1;
                    if(entityTopRow < 0) entityTopRow = 0;
                    if(entityTopRow >= gp.maxWorldCol) entityTopRow = gp.maxScreenCol - 1;

	                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
	                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
	                
                    if(tileNum1 >= 2500)  tileNum1 = 2499;
                    if(tileNum2 >= 2500)  tileNum2 = 2499;
                    
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
	                
                    if(entityRightCol >= gp.maxWorldCol) entityRightCol = gp.maxScreenCol - 1;
                    if(entityLeftCol < 0) entityLeftCol = 0;
                    if(entityLeftCol >= gp.maxWorldCol) entityLeftCol = gp.maxScreenCol - 1;
                    if(entityBottomRow >= gp.maxWorldRow) entityBottomRow = gp.maxScreenCol - 1;
                    if(entityTopRow < 0) entityTopRow = 0;
                    if(entityTopRow >= gp.maxWorldCol) entityTopRow = gp.maxScreenCol - 1;
                    
	                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
	                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
	                
                    if(tileNum1 >= 2500)  tileNum1 = 2499;
                    if(tileNum2 >= 2500)  tileNum2 = 2499;
	                
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
	    }catch (ArrayIndexOutOfBoundsException e) {
	        System.out.println(e+"entity.direction:" + entity.direction + "entity.name:" + entity.name + " entityLeftCol: "+ entityLeftCol + " entityRightCol: " + entityRightCol + " entityTopRow:" + entityTopRow + " entityTopRow:" + entityBottomRow);
        }
		
		
	}
	
	
	 /**
     * <p>
     * this is  object collision checker. Collision is true if there is a solid object in the direction of the entity
     * </p>
     * 
     * @param entity is may be player or enemy
     * @param player check player or not
     * @return player is true return set the index i
     * @since 1.0
     */
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
		
		return index;
	}
	
	/**
     * <p>
     * this is  entity collision  checker. Collision is true if there is a solid object in the direction of the entity
     * </p>
     * 
     * @param entity is may be npc or enemy
     * @param target is array of the entity target
     * @return player is true return set the index i
     * @since 1.0
     */
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
	
	
	/**
     * <p>
     * this is  player collision checker. Collision is true if there is a solid object in the direction of the entity
     * </p>
     * 
     * @param entity is may be npc tile or enemy
     * @return collision contact player value
     * @since 1.0
     */
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
	
	/**
     * <p>
     * this is fight area checker. if distance bigger than hipotenus return enemy index
     * </p>
     * 
     * @param entity is may be player
     * @param enemy is the array of the enemies
     * @return enemy index
     * @since 1.0
     */
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
	
	/**
     * <p>
     * this is fight area checker for enemy. To check is enemy around player for standing
     * </p>
     * 
     * @param entity is may be player
     * @param enemy is  enemy
     * @return if hipotenus les than 50  return true otherwise return false
     * @since 1.0
     */
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
	
	/**
     * <p>
     * this is tile collision checker for new entity. 
     * </p>
     * 
     * @param entityWorldX is enemy's x cordinates
     * @param entityWorldY is enemy's y cordinates
     * @since 1.0
     */
	
	// New Entity Collisions
	public void checkTileForNewEntity(int entityWorldX, int entityWorldY) {
        int tileNum = gp.tileM.mapTileNum[entityWorldX / gp.tileSize][entityWorldY / gp.tileSize];
                
        if(gp.tileM.tile[tileNum].collision) {
            gp.aSetter.collisionOn = true;
        }
    }
    
	/**
     * <p>
     * this is entity collision checker for new entity.
     * </p>
     * 
     * @param entityWorldX is  x cordinates
     * @param entityWorldY is  y cordinates
     * @param target is enemy's target
     * @since 1.0
     */
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
    
    /**
     * <p>
     * this is player collision checker for new entity.
     * </p>
     * 
     * @param entityWorldX is enemy's x cordinates
     * @param entityWorldY is enemy's y cordinates
     * @since 1.0
     */
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
