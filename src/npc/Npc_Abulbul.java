package npc;

import java.awt.Rectangle;
import java.util.Random;



import entity.Entity;
import entity.Player;
import main.GamePanel;
import main.KeyHandler;
import main.MouseHandler;

public class Npc_Abulbul extends Entity {

    GamePanel gp;
    

    public Npc_Abulbul(GamePanel gp) {
        super(gp);
        this.gp = gp;

        direction = "down";
        speed = 1;
        defaultSpeed = speed;
        type = npcType;
        level = 100;
        name = "Abulbul";
        
        solidArea.x = 12;
        solidArea.y = 38;
        solidArea.width = 50;
        solidArea.height = 48;
        
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getNpcImage();
        setDialogue();
    }

    public void getNpcImage() {
        down1 = setup("/npc/abulbul1", 96, 96);
        down2 = setup("/npc/abulbul2", 96, 96);
        down3 = setup("/npc/abulbul3", 96, 96);
        down4 = setup("/npc/abulbul4", 96, 96);
    }

    public void setAction() {

        standing = true;
    }

    public void setDialogue() {

        dialogues[0] = "Merhaba, "+ gp.player.name+"\nGeleceğini Fuat Bey'den duyduğumdan beri\ngözüm yollarda. Senin gibi yağız ve gürbüz\nbir delikanlıyı aramızda görmekten\nmemnuniyet duyarım. Köyümüzün senin gibi\nbir nefere ihtiyacı vardı. İlk olarak\nteşkilatımızı tanımanı istiyorum. Şu\nparayı al ve Silah satıcısı Ruhsar Bey\nile alışveriş yaparak tanış.\nSonra bana geri dön.";
        dialogues[1] = "Tebrikler, " + gp.player.name+"\nRuhsar Bey en iyi kılıçları üretir.\nŞimdiki görevinde kılıç çok lazım olacak.\nSana biraz köyümüzden bahsedeyim. Büyük\nbuhran sonrası küçük yerleşim yerleri\nkurulmaya başlandıktan sonra kurulan ilk\nyer bizim köyümüz. Köy kurulduktan sonra\nFethi Bey köyümüzün yöneticisi oldu. Köy\nmeydanında meclis kurdu. Fethi Bey bilime\nçok önem verirdi. Genç Bilginler\nadlı bilim ve mühendislik grubunu kurdu\nher şey güzel ilerlerken Genç Bilginler";
        dialogues[2] = "wizard";
        dialogues[3] = "sword";
        dialogues[4] = "Hello";
        dialogues[5] = "welcome";
        dialogues[6] = "wizard";
        dialogues[7] = "sword";
        dialogues[8] = "Hello";
        dialogues[9] = "welcome";
        dialogues[10] = "wizard";
        dialogues[11] = "sword";
    }

    public void speak() {
        gp.ui.currentDialogue = dialogues[0];
    }

}