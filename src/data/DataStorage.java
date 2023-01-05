package data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * <p>
 * This Class stores characters stats and items
 * </p>
 */
public class DataStorage implements Serializable {
    
    
    // PLAYER STATS
    /**
     * <p>
     * This field stores characters level
     * </p>
     */
    public int level;
    /**
     * <p>
     * This field stores characters coin
     * </p>
     */
    public int playerCoin;
    /**
     * <p>
     * This field stores characters experience point
     * </p>
     */
    public int playerXP; 
    /**
     * <p>
     * This field stores characters attack power
     * </p>
     */
    public int attackPower;
    /**
     * <p>
     * This field stores how many red potion character have
     * </p>
     */
    public int redPotionNumber;
    /**
     * <p>
     * This field stores how many blue potions character have
     * </p>
     */
    public int bluePotionNumber;
    /**
     * <p>
     * This field stores characters task level
     * </p>
     */
    public int taskLevel;
    /**
     * <p>
     * This field stores characters weapon name
     * </p>
     */
    public String playerWeapon;
    /**
     * <p>
     * This field stores characters name
     * </p>
     */
    public String playerName;
    
    //PLAYER INVENTORY
    /**
     * <p>
     * This field stores item names in the inventory
     * </p>
     */
    ArrayList<String> itemNames = new ArrayList<>();
    /**
     * <p>
     * This field stores items level
     * </p>
     */
    ArrayList<Integer> enhancedLevel = new ArrayList<>();
    /**
     * <p>
     * This field stores characters current weapon's name
     * </p>
     */
    String currentWeaponName;
    
    /**
     * <p>
     * This stores to whether mission is taken or not
     * </p>
     */
    int[] missionPrize = new int[5];
  
}
