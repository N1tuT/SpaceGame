package utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteSheet {

    private BufferedImage sheet;

    public SpriteSheet (String path) {
        try {
            sheet = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*
        * Split sheet into frames
        * @param frameW     width of each individual sprite in pixels
        * @param frameH     height of each individual sprite in pixels
        * @param count      how many frames to extract (left to right, top to bottom)
        * @return           array of BufferedImages, one per frame
     */

    public BufferedImage[] getFrames (int frameW, int frameH, int count) {
        BufferedImage[] frames = new BufferedImage[count];
        int cols = sheet.getWidth() / frameW;

        for (int i = 0; i < count; i++) {
            int x = (i % cols) * frameW;
            int y = (i / cols) * frameH;
            frames[i] = sheet.getSubimage(x, y, frameW, frameH);
        }

        return frames;
    }

}
