package main;

public class MovePlayer {
    
    static int newScreenX, newScreenY;
    static int newWorldX, newWorldY;
    static boolean overX, overY;
    static boolean underX, underY; 
    static int lockScreenCounterX, lockScreenCounterY;


    public static void move(GamePanel gp) {
        
        // These variables to control can player move
        newScreenX = gp.player.screenX;
        newScreenY = gp.player.screenY;
        newWorldX = gp.player.worldX;
        newWorldY = gp.player.worldY;
        
        // If screen reaches Top or Left Edges
        underX = gp.player.worldX < (gp.maxScreenCol * gp.tileSize / 2 - gp.tileSize/2);
        underY = gp.player.worldY < (gp.maxScreenRow * gp.tileSize / 2 - gp.tileSize/2);

        // If screen reaches Bottom or Right Edges
        overX = gp.maxWorldCol * gp.tileSize - gp.player.worldX - gp.tileSize < (gp.maxScreenCol * gp.tileSize / 2 - gp.tileSize/2);
        overY = gp.maxWorldRow * gp.tileSize - gp.player.worldY - gp.tileSize < (gp.maxScreenRow * gp.tileSize / 2 - gp.tileSize/2);
        
        if((overX && overY) || (overX && underY) || (underX && overY) || (underX && underY)) { // CORNERS

            // when screen reaches to edge for the first time, newScreenX and newScreenY doesn't increase or decrease by speed so we add
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
            
            // while moving player by speed, move screen by speed with the same direction
            switch (gp.player.direction) {
                case "upleft":      newScreenX -= gp.player.speed * 0.707; newScreenY -= gp.player.speed * 0.707; newWorldY  -= gp.player.speed * 0.707;  newWorldX -= gp.player.speed * 0.707;  break;
                case "upright":     newScreenX += gp.player.speed * 0.707; newScreenY -= gp.player.speed * 0.707; newWorldY  -= gp.player.speed * 0.707;  newWorldX += gp.player.speed * 0.707;  break;
                case "downleft":    newScreenX -= gp.player.speed * 0.707; newScreenY += gp.player.speed * 0.707; newWorldY  += gp.player.speed * 0.707;  newWorldX -= gp.player.speed * 0.707;  break;
                case "downright":   newScreenX += gp.player.speed * 0.707; newScreenY += gp.player.speed * 0.707; newWorldY  += gp.player.speed * 0.707;  newWorldX += gp.player.speed * 0.707;  break;
                case "up":          newScreenY -= gp.player.speed; newWorldY  -= gp.player.speed;       break;
                case "down":        newScreenY += gp.player.speed; newWorldY  += gp.player.speed;       break;
                case "left":        newScreenX -= gp.player.speed; newWorldX  -= gp.player.speed;       break;
                case "right":       newScreenX += gp.player.speed; newWorldX  += gp.player.speed;       break;
            }
        }else if(overX || underX) { // RIGHT | LEFT

            if(lockScreenCounterX == 0) {
                if(overX)   newScreenX += gp.player.speed;
                else        newScreenX -= gp.player.speed;  
                lockScreenCounterX++;
            }
            lockScreenCounterY = 0;
            switch (gp.player.direction) {
                case "upleft":         newScreenX -= gp.player.speed * 0.707; newWorldY -= gp.player.speed * 0.707;  newWorldX -= gp.player.speed * 0.707;  break;
                case "upright":        newScreenX += gp.player.speed * 0.707; newWorldY -= gp.player.speed * 0.707;  newWorldX += gp.player.speed * 0.707;  break;
                case "downleft":       newScreenX -= gp.player.speed * 0.707; newWorldY += gp.player.speed * 0.707;  newWorldX -= gp.player.speed * 0.707;  break;
                case "downright":      newScreenX += gp.player.speed * 0.707; newWorldY += gp.player.speed * 0.707;  newWorldX += gp.player.speed * 0.707;  break;
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
            lockScreenCounterX = 0;
            switch (gp.player.direction) {
                case "upleft":      newScreenY -= gp.player.speed * 0.707; newWorldY  -= gp.player.speed * 0.707;  newWorldX -= gp.player.speed * 0.707;  break;
                case "upright":     newScreenY -= gp.player.speed * 0.707; newWorldY  -= gp.player.speed * 0.707;  newWorldX += gp.player.speed * 0.707;  break;
                case "downleft":    newScreenY += gp.player.speed * 0.707; newWorldY  += gp.player.speed * 0.707;  newWorldX -= gp.player.speed * 0.707;  break;
                case "downright":   newScreenY += gp.player.speed * 0.707; newWorldY  += gp.player.speed * 0.707;  newWorldX += gp.player.speed * 0.707;  break;
                case "up":          newScreenY -= gp.player.speed; newWorldY  -= gp.player.speed;       break;
                case "down":        newScreenY += gp.player.speed; newWorldY  += gp.player.speed;       break;
                case "left":        newWorldX  -= gp.player.speed;  break;
                case "right":       newWorldX  += gp.player.speed;  break;
            } 
        }else {

            lockScreenCounterX = 0; 
            lockScreenCounterY = 0;
            switch (gp.player.direction) {
                case "upleft":      newWorldY -= gp.player.speed * 0.707;    newWorldX -= gp.player.speed * 0.707;    break;
                case "upright":     newWorldY -= gp.player.speed * 0.707;    newWorldX += gp.player.speed * 0.707;    break;
                case "downleft":    newWorldY += gp.player.speed * 0.707;    newWorldX -= gp.player.speed * 0.707;    break;
                case "downright":   newWorldY += gp.player.speed * 0.707;    newWorldX += gp.player.speed * 0.707;    break;
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
        
        if(overX) gp.isPlayerAtRightEdge = true;
        else      gp.isPlayerAtRightEdge = false;
        
        if(overY) gp.isPlayerAtBottomEdge = true;
        else      gp.isPlayerAtBottomEdge = false;
        
        if(underX) gp.isPlayerAtLeftEdge = true;
        else      gp.isPlayerAtLeftEdge = false;
        
        if(underY) gp.isPlayerAtTopEdge = true;
        else      gp.isPlayerAtTopEdge = false;

    }
    
}
