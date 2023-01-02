package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {

	Clip clip;
	URL soundURL[] = new URL[30];
	FloatControl fc;
    int volumeScale = 3;
    float volume;
	
	public Sound() {
		
		soundURL[0] = getClass().getResource("/sounds/enterTheEast.wav");
		soundURL[1] = getClass().getResource("/sounds/bgsoundtrack.wav");
        soundURL[2] = getClass().getResource("/sounds/coin.wav");
        soundURL[3] = getClass().getResource("/sounds/sword.wav");
        soundURL[4] = getClass().getResource("/sounds/walk1.wav");
        soundURL[5] = getClass().getResource("/sounds/walk2.wav");
        soundURL[6] = getClass().getResource("/sounds/dogDie.wav");
        soundURL[7] = getClass().getResource("/sounds/dogSound1.wav");
        soundURL[8] = getClass().getResource("/sounds/dogSound2.wav");
        soundURL[9] = getClass().getResource("/sounds/punchSound.wav");
        soundURL[10] = getClass().getResource("/sounds/fight1.wav");
        soundURL[11] = getClass().getResource("/sounds/fight2.wav");
        soundURL[12] = getClass().getResource("/sounds/fight3.wav");
        soundURL[13] = getClass().getResource("/sounds/fight4.wav");
        soundURL[14] = getClass().getResource("/sounds/playerGetDamage1.wav");
        soundURL[15] = getClass().getResource("/sounds/playerGetDamage2.wav");
        soundURL[16] = getClass().getResource("/sounds/playerGetDamage3.wav");
        soundURL[17] = getClass().getResource("/sounds/playerGetDamage4.wav");
        soundURL[18] = getClass().getResource("/sounds/playerGetDamage5.wav");
        soundURL[19] = getClass().getResource("/sounds/swordSpin.wav");
        soundURL[20] = getClass().getResource("/sounds/dungeon.wav");
        soundURL[21] = getClass().getResource("/sounds/deadSound.wav");
        soundURL[22] = getClass().getResource("/sounds/auraOfTheSword.wav");
        soundURL[23] = getClass().getResource("/sounds/threeWayCut.wav");
        soundURL[24] = getClass().getResource("/sounds/redPotion.wav");
        soundURL[25] = getClass().getResource("/sounds/titleScreen.wav");
        soundURL[26] = getClass().getResource("/sounds/button.wav");        
        soundURL[27] = getClass().getResource("/sounds/blacksmith.wav");        
	}
	
	public void setFile(int index) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[index]);  
			clip = AudioSystem.getClip();
			clip.open(ais);
	        fc = (FloatControl) clip.getControl((FloatControl.Type.MASTER_GAIN));
	        checkVolume();
			
		} catch (Exception e) {
		}
	}
	
	public void play() {
		
		clip.start();
		
	}
	
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop() {
		clip.stop();
	}
	
	public void checkVolume() {
        switch (volumeScale) {
            case 0:
                volume = -80f;
                break;
            case 1:
                volume = -20f;
                break;
            case 2:
                volume = -12f;
                break;
            case 3:
                volume = -5f;
                break;
            case 4:
                volume = 1f;
                break;
            case 5:
                volume = 6f;
                break;
        }
        fc.setValue(volume);
    }
}
