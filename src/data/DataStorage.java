package data;

import java.io.Serializable;
import java.util.ArrayList;

import entity.Entity;

/**
 * <p>
 * This Class stores characters stats and items
 * </p>
 */
public class DataStorage implements Serializable {
    
    /**
     * <p>
     * These fields are created to store the stats of the character 
     * </p>
     */
    // PLAYER STATS
    int level, playerCoin, playerXP, attackPower, redPotionNumber, bluePotionNumber, taskLevel;
    String playerWeapon, playerName;
    /**
     * <p>
     * These fields are created to store the items of the character 
     * </p>
     */
    //PLAYER INVENTORY
    ArrayList<String> itemNames = new ArrayList<>();
    ArrayList<Integer> enhancedLevel = new ArrayList<>();
    String currentWeaponName;
    
    int[] missionPrize = new int[5];
  
}
