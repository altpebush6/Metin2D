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
        
        solidArea.x = 38;
        solidArea.y = 12;
        solidArea.width = 20;
        solidArea.height = 74;
        
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

        dialogues[0] = "Merhaba, "+ gp.player.name +"\nGeleceğini Fuat Bey'den duyduğumdan beri\ngözüm yollarda. Senin gibi yağız ve gürbüz\nbir delikanlıyı aramızda görmekten\nmemnuniyet duyarım. Köyümüzün senin gibi\nbir nefere ihtiyacı vardı. İlk olarak\nteşkilatımızı tanımanı istiyorum. Şu\nparayı al ve Silah satıcısı Ruhsar Bey\nile alışveriş yaparak tanış.\nSonra bana geri dön.";
        dialogues[1] = "Tebrikler, " + gp.player.name +"\nTeşkilatımızın diğer üyesi olan Demirci Rüstem ile tanış.\nGücü ile tüm kılıçları döver aynı zamanda iyi bir savaşçıdır.\nKöyümüze sayamayacağım kadar yardımı dokunmuştur.\nEski yönetimden Fethi Bey'in ürettirdiği bir alete sahip.\nBu alet ÖRS GB-2 Fetih Bey köyden kaçtığı sırada ele geçirdik.\nDemirci Rüstem bu aleti kullanarak nice neferlerin kılıçlarını\ngüçlendirdi. Demirci'ye kılıcını yükseltmek için gitmeni ve tanışmanı\nistiyorum. Her nefer silahını yükseltecek kadar talihli değildir.\nGörev bitince hemen bana uğra.";
        dialogues[2] = "Tebrikler, " + gp.player.name +"\nŞimdiki görevinde kılıç çok lazım olacak.\nSana biraz köyümüzden bahsedeyim. Büyük\nbuhran sonrası küçük yerleşim yerleri\nkurulmaya başlandıktan sonra kurulan ilk\nyer bizim köyümüz. Köy kurulduktan sonra\nFethi Bey köyümüzün yöneticisi oldu. Köy\nmeydanında meclis kurdu. Fethi Bey bilime\nçok önem verirdi. Genç Bilginler\nadlı bilim ve mühendislik grubunu kurdu\nher şey güzel ilerlerken Genç Bilginler";
        dialogues[3] = "Tebrikler evlat, duyduğuma göre kurtları cesurca haklamışsın. Şimdiden namın yayılmaya başladı. Cesaretini kutluyorum. Bu Kurt Temizliği ile hem Fethi Bey'e güzel bir ders verdik hem de köyümüzün güvenliğini arttırdık. Ancak seni bu köye getiren gerçek görevini verme zamanım geldi. Bu görevi senden önce başarı ile yerine getiren hiçkimse olmadı. Sana moral vermek isterdim ama gerçekler bundan ibaret. Bu görevin başarılı olması demek köy bekası demek. Köyümüzün başına bela olan Fethi'nin ve adamlarının bu görev ile hiçbir etkisi kalmayacak. Daha önce sana Fethi Bey'in yaptırdığı bilinç kopyalama cihazından bahsetmiştim. Bu cihazla kendi saltanatını sürdürüyor; canına can, malına mal katıyor. Canlıları kendi etkisi altına alıyor. Bilinci etkilenmiş kurtlar köye korku yaydı insanlarımız evlerinden çıkamaz oldu. Kurt temizliğini başarıyla yerine getirdin üzgünüm ancak kurt temizliği Fethi Bey'e çok az zarar verebilir. Bilinç Kopyalama cihazının en büyük VERİCİ ANTENİ köyün hemen aşağısında bulunuyor. Bu verici anteni yok etmen lazım. Nice neferler bu yolda canını verdi. Çok dikkatlı ol evlat.";
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