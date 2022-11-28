package main;

import java.awt.Color;

public class Damages {
    
    public int damageSize;
    public int damagePosX, damagePosY;
    public int damageFontSize = 60;
    public int damageCounter = 0;
    
    public Color color;
    
    public Damages(int damageSize, int damagePosX, int damagePosY, int damageFontSize, Color color) {
        this.damageSize = damageSize;
        this.damagePosX = damagePosX;
        this.damagePosY = damagePosY;
        this.damageFontSize = damageFontSize;
        this.color = color;
    }
}
