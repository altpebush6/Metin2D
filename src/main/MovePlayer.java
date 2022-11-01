package main;

public class MovePlayer {
    
    static int newScreenX, newScreenY;
    static int newWorldX, newWorldY;
    static boolean overX, overY;
    static boolean underX, underY; 
    static int lockScreenCounterX, lockScreenCounterY;


    public static void move(GamePanel gp) {
        newScreenX = gp.player.screenX;
        newScreenY = gp.player.screenY;
        newWorldX = gp.player.worldX;
        newWorldY = gp.player.worldY;
        
        underX = gp.player.worldX < (gp.maxScreenCol * gp.tileSize / 2 - gp.tileSize/2);
        underY = gp.player.worldY < (gp.maxScreenRow * gp.tileSize / 2 - gp.tileSize/2);
        
        overX = gp.maxWorldCol * gp.tileSize - gp.player.worldX - gp.tileSize < (gp.maxScreenCol * gp.tileSize / 2 - gp.tileSize/2);
        overY = gp.maxWorldRow * gp.tileSize - gp.player.worldY - gp.tileSize < (gp.maxScreenRow * gp.tileSize / 2 - gp.tileSize/2);
        
        if((overX && overY) || (overX && underY) || (underX && overY) || (underX && underY)) { // CORNERS
            if(lockScreenCounterX == 0) {
                if(overX)   newScreenX += gp.player.speed;
                else        newScreenX -= gp.player.speed;  
                lockScreenCounterX++;
            }
            if(lockScreenCounterY == 0) {
                if(overY)   newScreenY += gp.player.speed;
                else        newScreenY -= gp.player.speed;  
                lockScreenCounterY++;
            }
            switch (gp.player.direction) {
                case "upleft":      newScreenX -= gp.player.speed; newScreenY -= gp.player.speed; newWorldY  -= gp.player.speed;  newWorldX -= gp.player.speed;  break;
                case "upright":     newScreenX += gp.player.speed; newScreenY -= gp.player.speed; newWorldY  -= gp.player.speed;  newWorldX += gp.player.speed;  break;
                case "downleft":    newScreenX -= gp.player.speed; newScreenY += gp.player.speed; newWorldY  += gp.player.speed;  newWorldX -= gp.player.speed;  break;
                case "downright":   newScreenX += gp.player.speed; newScreenY += gp.player.speed; newWorldY  += gp.player.speed;  newWorldX += gp.player.speed;  break;
                case "up":          newScreenY -= gp.player.speed; newWorldY  -= gp.player.speed;       break;
                case "down":        newScreenY += gp.player.speed; newWorldY  += gp.player.speed;       break;
                case "left":        newScreenX -= gp.player.speed; newWorldX  -= gp.player.speed;       break;
                case "right":       newScreenX += gp.player.speed; newWorldX  += gp.player.speed;       break;
            }
        }else if(overX || underX) { // RIGHT | LEFT
            if(lockScreenCounterX == 0) { // when screen reaches to edge for the first time, newScreenX doesn't increase by speed so we add
                if(overX)   newScreenX += gp.player.speed;
                else        newScreenX -= gp.player.speed;  
                lockScreenCounterX++;
            }
            switch (gp.player.direction) {
                case "upleft":         newScreenX -= gp.player.speed; newWorldY -= gp.player.speed;  newWorldX -= gp.player.speed;  break;
                case "upright":        newScreenX += gp.player.speed; newWorldY -= gp.player.speed;  newWorldX += gp.player.speed;  break;
                case "downleft":       newScreenX -= gp.player.speed; newWorldY += gp.player.speed;  newWorldX -= gp.player.speed;  break;
                case "downright":      newScreenX += gp.player.speed; newWorldY += gp.player.speed;  newWorldX += gp.player.speed;  break;
                case "up":             newWorldY  -= gp.player.speed;                      break;
                case "down":           newWorldY  += gp.player.speed;                      break;
                case "left":           newScreenX -= gp.player.speed; newWorldX -= gp.player.speed;  break;
                case "right":          newScreenX += gp.player.speed; newWorldX += gp.player.speed;  break;
            }
        }else if(overY || underY) { // BOTTOM | TOP
            if(lockScreenCounterY == 0) {
                if(overY)   newScreenY += gp.player.speed;
                else        newScreenY -= gp.player.speed;  
                lockScreenCounterY++;
            }
            switch (gp.player.direction) {
                case "upleft":      newScreenY -= gp.player.speed; newWorldY  -= gp.player.speed;  newWorldX -= gp.player.speed;  break;
                case "upright":     newScreenY -= gp.player.speed; newWorldY  -= gp.player.speed;  newWorldX += gp.player.speed;  break;
                case "downleft":    newScreenY += gp.player.speed; newWorldY  += gp.player.speed;  newWorldX -= gp.player.speed;  break;
                case "downright":   newScreenY += gp.player.speed; newWorldY  += gp.player.speed;  newWorldX += gp.player.speed;  break;
                case "up":          newScreenY -= gp.player.speed; newWorldY  -= gp.player.speed;       break;
                case "down":        newScreenY += gp.player.speed; newWorldY  += gp.player.speed;       break;
                case "left":        newWorldX  -= gp.player.speed;  break;
                case "right":       newWorldX  += gp.player.speed;  break;
            } 
        }else {
            if(lockScreenCounterX != 0) {
                lockScreenCounterX = 0; 
            }
            if(lockScreenCounterY != 0) {
                lockScreenCounterY = 0;
            }
            switch (gp.player.direction) {
                case "upleft":      newWorldY -= gp.player.speed;    newWorldX -= gp.player.speed;    break;
                case "upright":     newWorldY -= gp.player.speed;    newWorldX += gp.player.speed;    break;
                case "downleft":    newWorldY += gp.player.speed;    newWorldX -= gp.player.speed;    break;
                case "downright":   newWorldY += gp.player.speed;    newWorldX += gp.player.speed;    break;
                case "up":          newWorldY -= gp.player.speed;    break;
                case "down":        newWorldY += gp.player.speed;    break;
                case "left":        newWorldX -= gp.player.speed;    break;
                case "right":       newWorldX += gp.player.speed;    break;
            } 
        }
       
        if(newWorldX > 0 && newWorldX < (gp.maxWorldCol-1) * gp.tileSize &&
           newWorldY > 0 && newWorldY < (gp.maxWorldRow-2) * gp.tileSize) {
            gp.player.screenX = newScreenX;
            gp.player.screenY = newScreenY;
            gp.player.worldX = newWorldX;
            gp.player.worldY = newWorldY;
           
        }        
    }
    
}
