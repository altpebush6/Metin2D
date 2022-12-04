package main;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JWindow;

public class Main {
    
    public static JFrame window;
    
	public static void main(String[] args) throws Exception {

		window = new JFrame("Metin2");
		
		//f.setUndecorated(true); // To close top bar
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		window.setUndecorated(false);
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		window.pack(); // window will be sized to fit the preferred size and layouts of its subcomponents (GamePanel) 
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gamePanel.setupGame();
		
		gamePanel.startGameThread();
		
		ImageIcon favicon = new ImageIcon("resources/background/metin2.png");
		window.setIconImage(favicon.getImage());
	}
}
