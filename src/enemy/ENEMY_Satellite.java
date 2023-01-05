package enemy;

import entity.Entity;
import main.GamePanel;

/**
 * <p>
 * This Class contains wolf Satellite as an enemy
 * </p>
 */
public class ENEMY_Satellite extends Entity {

    /**
     * <p>
     * for usage of GamePanel 
     * </p>
     */
    public GamePanel gp;
    
    /**
     * <p>
     * This constructor instantiates the wolf specifications
     * </p>
     * 
     * @param gp means GamePanel
     * @param id gets the Satellite id
     * @since 1.0
     */
    public ENEMY_Satellite(GamePanel gp, int id) {

        super(gp);

        this.gp = gp;
        name = "Satellite";
        speed = 0;
        defaultSpeed = speed;
        maxLife = 300;
        life = maxLife;
        type = enemyType;
        standing = true;
        hpBarOn = true;
        direction = "down";
        subType = 0;
        // hpBarCounter = 0;

        solidArea.x = 50;
        solidArea.y = 20;
        solidArea.width = 74;
        solidArea.height = 244;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    /**
     * <p>
     * Gets the satellite images as alive and cracked
     * </p>
     * 
     * @since 1.0
    */
    public void getImage() {
        down1 = setup("/satellite/satellite", gp.tileSize * 4, gp.tileSize * 6);
        down2 = setup("/satellite/satellite", gp.tileSize * 4, gp.tileSize * 6);
        down3 = setup("/satellite/satellite", gp.tileSize * 4, gp.tileSize * 6);
        
        downCracked1 = setup("/satellite/deathsatellite", gp.tileSize * 4, gp.tileSize * 6);
        downCracked2 = setup("/satellite/deathsatellite", gp.tileSize * 4, gp.tileSize * 6);
        downCracked3 = setup("/satellite/deathsatellite", gp.tileSize * 4, gp.tileSize * 6);
    }

    /**
     * <p>
     * Calls the super's update method
     * </p>
     * 
     * @since 1.0
    */
    public void update() {
        super.update();
    }

    /**
     * <p>
     * If damage taken by player sets inFight true
     * </p>
     * 
     * @since 1.0
    */
    public void damageReaction() {
        inFight = true;
    }

}
