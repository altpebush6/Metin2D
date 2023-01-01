package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Dolunay;
import object.OBJ_EcelGetiren;
import object.OBJ_TasKanat;

public class SaveLoad {
    
    GamePanel gp;
    
    public SaveLoad(GamePanel gp) {
        this.gp = gp;
        
    }
    public Entity getObject(String itemName) {
        
        Entity obj = null;
        
        switch(itemName) {
            
            case "EcelGetiren": obj = new OBJ_EcelGetiren(gp); break;
            case "TasKanat": obj = new OBJ_TasKanat(gp); break;
            case "Dolunay": obj = new OBJ_Dolunay(gp); break;
        }
        return obj;
    }
    
    public void save() {
        
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));
            
            DataStorage ds = new DataStorage();
            
            ds.level = gp.player.level;
            ds.attackPower = gp.player.attackPower;
            ds.playerCoin = gp.player.playerCoin;
            ds.playerWeapon = gp.player.playerWeapon;
            ds.playerXP = gp.player.playerXP;
            
           // PLAYER INVENTORY
            for(int i = 0; i < gp.player.inventory.size();i++) {
                ds.itemNames.add(gp.player.inventory.get(i).name);
               // ds.itemAmounts.add(gp.player.inventory.get(i).amount);
            }
            
            
            //Write the DataStorage Object
            oos.writeObject(ds);
            System.out.println("SAVED");
        }
        catch(Exception e) {
            System.out.println("Save Exception!");
        }
        
    }
    public void load() {
        
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));
            
            //Read the DataStorage object
            DataStorage ds = (DataStorage)ois.readObject();
            //PLAYER STATS
            gp.player.level = ds.level;
            gp.player.attackPower = ds.attackPower;
            gp.player.playerCoin = ds.playerCoin;
            gp.player.playerWeapon = ds.playerWeapon;
            gp.player.playerXP = ds.playerXP;
            
            // PLAYER INVENTORY
            gp.player.inventory.clear();
            for(int i = 0; i < ds.itemNames.size(); i++) {
                gp.player.inventory.add(getObject(ds.itemNames.get(i)));
            }
        }
        catch(Exception e){
            System.out.println("Load exception!");
            
        }
    }

}
