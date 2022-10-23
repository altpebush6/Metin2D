package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
	
	public int worldX,worldY,screenX,screenY,speed;
	
	public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2, walk1,walk2,walk3,walk4;
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	
	public int stepCounter = 0;
	public int stepType = 0;
	
	public Rectangle solidArea;
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
}
