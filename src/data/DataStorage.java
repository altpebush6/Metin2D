package data;

import java.io.Serializable;
import java.util.ArrayList;

public class DataStorage implements Serializable {
    
    // PLAYER STATS
    int level;
    int playerCoin;
    String playerWeapon;
    int playerXP;
    int attackPower;
    
    //PLAYER INVENTORY
    ArrayList<String> itemNames = new ArrayList<>();
    ArrayList<Integer> itemAmounts = new ArrayList<>();
    
   

}
