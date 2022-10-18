package main;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) throws Exception {
		//Character character = new Character("Ebu");
		//int charID = character.getCharID();
		//Store store = new Store(charID);
		//MainMenu menu = new MainMenu();
		//BackgroundSound bgSound = new BackgroundSound("sounds/soundtracks/bgsoundtrack.wav");
		//BackgroundSound gameSound = new BackgroundSound("sounds/soundtracks/soundtrack.wav");
		
		JFrame f = new JFrame("Metin2");
		//f.setUndecorated(true); // üst çubuğu kapatmak için
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GamePanel gamePanel = new GamePanel();
		f.add(gamePanel);
		f.pack(); // window will be sized to fit the preferred size and layouts of its subcomponents (GamePanel) 
		
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		
		gamePanel.setupGame();
		
		gamePanel.startGameThread();
		
		ImageIcon favicon = new ImageIcon("resources/background/metin2.png");
		f.setIconImage(favicon.getImage());
	}
}
