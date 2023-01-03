package data;

import java.io.Serializable;
import java.util.ArrayList;

import entity.Entity;

public class DataStorage implements Serializable {
    
    // PLAYER STATS
    int level, playerCoin, playerXP, attackPower, redPotionNumber, bluePotionNumber, taskLevel;
    String playerWeapon, playerName;
    
    //PLAYER INVENTORY
    ArrayList<String> itemNames = new ArrayList<>();
    ArrayList<Integer> enhancedLevel = new ArrayList<>();
    String currentWeaponName;
    
    int[] missionPrize = new int[5];
  
}
