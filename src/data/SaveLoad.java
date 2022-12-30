package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import main.GamePanel;

public class SaveLoad {
    
    GamePanel gp;
    
    public SaveLoad(GamePanel gp) {
        this.gp = gp;
        
    }
    
    public void save() {
        
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));
            
            DataStorage ds = new DataStorage();
            
            ds.level = gp.player.level;
            ds.maxLife = gp.player.maxLife;
            ds.life = gp.player.life;
            ds.attackPower = gp.player.attackPower;
            ds.playerCoin = gp.player.playerCoin;
            ds.playerWeapon = gp.player.playerWeapon;
            ds.playerXP = gp.player.playerXP;
            
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
            
            gp.player.level = ds.level;
            gp.player.maxLife = ds.maxLife;
            gp.player.life = ds.life;
            gp.player.attackPower = ds.attackPower;
            gp.player.playerCoin = ds.playerCoin;
            gp.player.playerWeapon = ds.playerWeapon;
            gp.player.playerXP = ds.playerXP;                       
        }
        catch(Exception e){
            System.out.println("Load exception!");
            
        }
    }

}
