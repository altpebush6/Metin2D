package main;

import java.awt.Color;

/**
 * <p>
 * This Class arranges the given and taken damages by player
 * </p>
 */
public class Damages {
    
    /**
     * <p>
     * Indicates damage size given
     * </p>
     */
    public int damageSize;
    
    /**
     * <p>
     * Where damage has given as position X
     * </p>
     */
    public int damagePosX;
    
    /**
     * <p>
     * Where damage has given as position Y
     * </p>
     */
    public int damagePosY;
    
    /**
     * <p>
     * This variable sets the font size for damage display
     * </p>
    */
    public int damageFontSize = 60;
    
    /**
     * <p>
     * Keeps counter to set damage display position in time
     * </p>
     */
    public int damageCounter = 0;
    
    
    /**
     * <p>
     * Keeps color of damage display
     * </p>
     */
    public Color color;
    
    
    /**
     * <p>
     * This is constructor assigns given variables to all variables 
     * </p>
     * 
     * @param damageSize gets damage size
     * @param damagePosX gets damage position X
     * @param damagePosY gets damage position Y
     * @param damageFontSize gets given font size
     * @param color gets given color
     * @since 1.0
     */
    public Damages(int damageSize, int damagePosX, int damagePosY, int damageFontSize, Color color) {
        this.damageSize = damageSize;
        this.damagePosX = damagePosX;
        this.damagePosY = damagePosY;
        this.damageFontSize = damageFontSize;
        this.color = color;
    }
}
