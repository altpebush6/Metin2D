package npc;

import entity.Entity;
import main.GamePanel;

/**
 * <p>
 * This Class determines the story teller NPC's property. Contains scenario
 * dialogues.
 * </p>
 */
public class Npc_Abulbul extends Entity {

    GamePanel gp;

    /**
     * <p>
     * This is Constructor. The constructor assign variables. NPC's name, level etc:
     * </p>
     * 
     * @param gp is the game panel
     * @since 1.0
     */
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

    /**
     * <p>
     * This method sets the images of NPC. 4 images ensure the animation.
     * </p>
     */
    public void getNpcImage() {
        down1 = setup("/npc/abulbul1", 96, 96);
        down2 = setup("/npc/abulbul2", 96, 96);
        down3 = setup("/npc/abulbul3", 96, 96);
        down4 = setup("/npc/abulbul4", 96, 96);
    }

    /**
     * <p>
     * This method keeps it from moving.
     * </p>
     */
    public void setAction() {
        standing = true;
    }

    /**
     * <p>
     * This method sets the NPC's dialogues. It is game's scenario also task
     * system's dialogues.
     * </p>
     */
    public void setDialogue() {

        dialogues[0] = "Merhaba, " + gp.player.name
                + "\nGeleceğini Fuat Bey'den duyduğumdan beri\ngözüm yollarda. Senin gibi yağız ve gürbüz\nbir delikanlıyı aramızda görmekten\nmemnuniyet duyarım. Köyümüzün senin gibi\nbir nefere ihtiyacı vardı. İlk olarak\nteşkilatımızı tanımanı istiyorum. Şu\nparayı al ve Silah satıcısı Ferruh Hanım\nile alışveriş yaparak tanış.\nSonra bana geri dön.";
        dialogues[1] = "Tebrikler, " + gp.player.name
                + "\nTeşkilatımızın diğer üyesi olan Demirci Rüstem ile tanış.\nGücü ile tüm kılıçları döver aynı zamanda iyi bir savaşçıdır.\nKöyümüze sayamayacağım kadar yardımı dokunmuştur.\nEski yönetimden Fatih Bey'in ürettirdiği bir alete sahip.\nBu alet ÖRS GB-2 Fatih Bey köyden kaçtığı sırada ele geçirdik.\nDemirci Rüstem bu aleti kullanarak nice neferlerin kılıçlarını\ngüçlendirdi. Demirci'ye kılıcını yükseltmek için gitmeni ve tanışmanı\nistiyorum. Her nefer silahını yükseltecek kadar talihli değildir.\nGörev bitince hemen bana uğra.";
        dialogues[2] = "Tebrikler, " + gp.player.name
                + "\nŞimdiki görevinde kılıç çok lazım olacak.\nSana biraz köyümüzden bahsedeyim. Büyük\nbuhran sonrası küçük yerleşim yerleri\nkurulmaya başlandıktan sonra kurulan ilk\nyer bizim köyümüz. Köy kurulduktan sonra\nFatih Bey köyümüzün yöneticisi oldu. Köy\nmeydanında meclis kurdu. Fatih Bey bilime\nçok önem verirdi. Genç Bilginler\nadlı bilim ve mühendislik grubunu kurdu.";
        dialogues[3] = "Her şey güzel ilerlerken Genç Bilginler bilinç kopyalama cihazı\nürettiler.Bu cihazın üretim amacı köyün bekası için kullanılmak\nolsa da Fatih Bey'in gözü döndü ve kendi saltanatı için\ncihazı kullanmaya başladı. Köyde gördüğün kurtlar\nve diğer canavarlar Fatih Bey'in güdümündedir.\nFatih Bey her hayvana kendi bilincini kopyalayıp yaşam süresini\nuzattı. Şimdiki görevinde Fatih Bey'de tahribat oluşturmak için\n3 Adet Kurt öldüreceksin.\nYolun açık olsun!";
        dialogues[4] = "Tebrikler evlat, duyduğuma göre kurtları cesurca haklamışsın.\nŞimdiden namın yayılmaya başladı. Cesaretini kutluyorum.\nBu Kurt Temizliği ile hem Fatih Bey'e güzel bir ders verdik\nhem de köyümüzün güvenliğini arttırdık.\nÖdülünü sana takdim ediyorum.\nBiraz köy meydanında gez ve bana geri dön.";
        dialogues[5] = "Tüm görevlerde adeta tarih yazdın.\nKöyde adın bilinir hale geldi. Ancak artık\nişler değişti. Seni bu köye getiren gerçek\ngörevini verme zamanım geldi. Bu görevi\nsenden önce başarı ile yerine getiren hiç kimse\nolmadı. Sana moral vermek isterdim ama gerçekler\nbundan ibaret. Bu görevin başarılı olması demek\nköy bekası demek. Köyümüzün başına bela olan\nFethi'nin ve adamlarının bu görev ile hiçbir etkisi kalmayacak.";
        dialogues[6] = "Daha önce sana Fatih Bey'in yaptırdığı bilinç kopyalama\ncihazından bahsetmiştim. Bu cihazla kendi saltanatını sürdürüyor;\ncanına can, malına mal katıyor. Canlıları\nkendi etkisi altına alıyor. Bilinci etkilenmiş kurtlar\nköye korku yaydı insanlarımız evlerinden çıkamaz oldu.\nKurt temizliğini başarıyla yerine getirdin üzgünüm ancak\nkurt temizliği Fatih Bey'e çok az zarar verebilir.\nBilinç Kopyalama cihazının en büyük VERİCİ ANTENİ köyün\nhemen aşağısında bulunuyor.\nBu verici anteni yok etmen lazım. anteni parçala!";
        dialogues[7] = "Az Zamanda çok işler başardın nefer!\nCesaretini, gücünü zekanı takdir ediyorum.\nKöyümüzü ve canlıları Fatih Bey'den kurtardın.\nİstihbarata göre Fatih Bey ve Ekibi bu işlerden elini eteğini çekmiş\nArtık köyün sefasını sür!\nKöy bütçesinden büyük bir para senin için ayrıldı.\nKendine çok dikkat et nefer!";
        dialogues[8] = "Az Zamanda çok işler başardın nefer!\nCesaretini, gücünü zekanı takdir ediyorum.\nKöyümüzü ve canlıları Fatih Bey'den kurtardın.\nİstihbarata göre Fatih Bey ve Ekibi bu işlerden elini eteğini çekmiş\nArtık köyün sefasını sür!\nKöy bütçesinden büyük bir para senin için ayrıldı.\nKendine çok dikkat et nefer!";

    }

    /**
     * <p>
     * This method send the NPC's dialogue arrays' first element.
     * </p>
     */
    public void speak() {
        gp.ui.currentDialogue = dialogues[0];
    }

}