package data;

import java.io.Serializable;
import java.util.ArrayList;

public class DataStorage implements Serializable {
    
    // PLAYER STATS
    int level, playerCoin, playerXP, attackPower, redPotionNumber, bluePotionNumber;
    String playerWeapon;
    
    //PLAYER INVENTORY
    ArrayList<String> itemNames = new ArrayList<>();
    ArrayList<Integer> itemAmounts = new ArrayList<>();
    
   

}
