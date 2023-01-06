package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import entity.Entity;
import main.GamePanel;
import object.OBJ_BluePotion;
import object.OBJ_Dolunay;
import object.OBJ_KDP;
import object.OBJ_RedPotion;
import object.OBJ_Sword;
import object.OBJ_SuPerisi;
/**
 * <p>
 * This Class makes save and load process 
 * </p>
 */
public class SaveLoad implements Serializable{
    /**
     * <p>
     * This field is instance of GamePanel class 
     * </p>
     */
    GamePanel gp;
    
    /**
     * <p>
     * this is constructor
     * </p>
     * 
     * @param gp is the game panel
     * @since 1.0
     */
    public SaveLoad(GamePanel gp) {
        this.gp = gp;
        
    }
    /**
     * <p>
     * This method returns the object corresponding to the name
     * </p>
     * @param itemName item's name in the game
     * @return Entity object
     */
    public Entity getObject(String itemName) {
        
        Entity obj = null;
        
        switch(itemName) {
            
            case "Sword": obj = new OBJ_Sword(gp); break;
            case "Kırmızı Demir Pala": obj = new OBJ_KDP(gp); break;
            case "Su Perisi": obj = new OBJ_SuPerisi(gp); break;
            case "Geniş Kılıç": obj = new OBJ_BluePotion(gp); break;
            case "Dolunay": obj = new OBJ_Dolunay(gp); break;
            case "Red Potion": obj = new OBJ_RedPotion(gp); break;
            case "Blue Potion": obj = new OBJ_BluePotion(gp); break;
        }
        return obj;
    }
    /**
     * <p>
     * This method does save process by creating .dat file by using ObjectOutputStream 
     * </p>
     * 
     */
    public void save() {
        
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));
            
            DataStorage ds = new DataStorage();
            
            ds.level = gp.player.level;
            ds.attackPower = gp.player.getAttackPower();
            ds.playerCoin = gp.player.getPlayerCoin();
            ds.playerWeapon = gp.player.playerWeapon;
            ds.playerXP = gp.player.getPlayerXP();
            ds.redPotionNumber = gp.player.redPotionNumber;
            ds.bluePotionNumber = gp.player.bluePotionNumber;
            ds.playerName = gp.player.name;
            ds.currentWeaponName = gp.player.currentWeapon.name;
            ds.taskLevel = gp.player.taskLevel;
            
            for(int i = 0; i < 5; i++) {
                ds.missionPrize[i] = gp.keyH.missionPrize[i];
            }
            

            // PLAYER INVENTORY
            for(int i = 0; i < gp.player.inventory.size();i++) {
                ds.itemNames.add(gp.player.inventory.get(i).name);
                ds.enhancedLevel.add(gp.player.inventory.get(i).enchantLevel);
            }
            
            
            //Write the DataStorage Object
            oos.writeObject(ds);
            System.out.println("SAVED");
        }
        catch(Exception e) {
            System.out.println(e+": Save Exception!");
        }
        
    }
    /**
     * <p>
     * This method does load process if there is a .dat file by using ObjectInputStream
     * </p>
     * 
     */
    public void load() {
        
        try {
            File saveFile = new File("save.dat");
            if(saveFile.exists()) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saveFile));
                
                //Read the DataStorage object
                DataStorage ds = (DataStorage)ois.readObject();
                //PLAYER STATS
                gp.player.level = ds.level;
                gp.player.setAttackPower(ds.attackPower);
                gp.player.setPlayerCoin(ds.playerCoin);
                gp.player.playerWeapon = ds.playerWeapon;
                gp.player.setPlayerXP(ds.playerXP);
                gp.player.redPotionNumber = ds.redPotionNumber;
                gp.player.bluePotionNumber = ds.bluePotionNumber;
                gp.player.name = ds.playerName;
                gp.player.taskLevel = ds.taskLevel;
                
                for(int i = 0; i < 5; i++) {
                    gp.keyH.missionPrize[i] = ds.missionPrize[i];
                }
                
                // PLAYER INVENTORY
                gp.player.inventory.clear();
                
                for(int i = 0; i < ds.itemNames.size(); i++) {
                    gp.player.inventory.add(getObject(ds.itemNames.get(i)));
                    gp.player.inventory.get(i).enchantLevel = ds.enhancedLevel.get(i);
                    if(gp.player.inventory.get(i).name.equals(ds.currentWeaponName)) {
                        gp.player.currentWeapon = gp.player.inventory.get(i);
                    }
                }
            }
        }
        catch(Exception e){
            System.out.println(e+": Load exception!");
            
        }
    }

}