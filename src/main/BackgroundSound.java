package main;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.*;

public class BackgroundSound {
	 BackgroundSound(String soundPath) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		// TODO Auto-generated constructor stub
		 File file = new File(soundPath);
		 AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
		 Clip clip = AudioSystem.getClip();
		 clip.open(audioStream);
		 
		 Scanner input = new Scanner(System.in);
		 
		 clip.start();
		 
	}
}
