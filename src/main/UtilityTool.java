package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * <p>
 * This Class set the images
 * </p>
 */
public class UtilityTool {
    
    /**
     * <p>
     * this method scale images 
     * </p>
     * 
     * @param original refers the original image
     * @param width is refers image width
     * @param height is referes image height
     * @since 1.0
     * @return BufferedImage
     */
	public BufferedImage scaleImage(BufferedImage original, int width, int height) {
		
		BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
		Graphics2D g2 = scaledImage.createGraphics();
		g2.drawImage(original, 0, 0, width, height, null);
		g2.dispose();
		
		return scaledImage;
	}
	
	/**
     * <p>
     * this method gets images and scale images by calling scaleImage method  
     * </p>
     * 
     * @param imagePath refers the original image path 
     * @param width refers original image's width
     * @param height refers original image's height
     * @since 1.0
     * @return BufferedImage
     */
    public BufferedImage setup(String imagePath, int width, int height) {

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image, width, height);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }
}
