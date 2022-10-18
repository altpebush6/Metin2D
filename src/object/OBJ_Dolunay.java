package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Dolunay extends SuperObject{
	
	
	public OBJ_Dolunay() {
		
		name = "Dolunay";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/dolunay.png"));
		} catch (IOException e) {
			// TODO: handle exception
		}
		
	
	}
}
