package main;

import java.awt.Color;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * <p>
 * This class is main class of the game it uses Java Swing to create window, and in this class
 * game is getting ready and being started 
 * </p>
 * 
 */
public class Main {
    /**
     * <p>
     * This parameter is the window of game
     * </p> 
     */
	public static JFrame window;

	public static void main(String[] args) throws Exception {

		window = new JFrame("Metin2");

		// f.setUndecorated(true); // To close top bar
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setUndecorated(true);	

		GamePanel gamePanel = new GamePanel();
		
		
		window.add(gamePanel);
		window.pack(); // window will be sized to fit the preferred size and layouts of its
						// subcomponents (GamePanel)
		//createText(gamePanel);
		//window.pack();			
		
		window.setLocationRelativeTo(null);

		gamePanel.setupGame();

		gamePanel.startGameThread();
		
	

		ImageIcon favicon = new ImageIcon("resources/background/metin2.png");
		window.setIconImage(favicon.getImage());
		window.setVisible(true);
	}

	/**
     * <p>
     * This method sets the name of the window
     * </p> 
     */
	public static void createText(GamePanel gamePanel) {
		JTextField t = new JTextField();
		window.add(t);
		JLabel t2= new JLabel("Metin2");
		t2.setBounds(10, 0, 100, 30);;
		//window.add(t2);
	}
}
