package main;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) throws Exception {

		JFrame f = new JFrame("Metin2");
		//f.setUndecorated(true); // To close top bar
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
