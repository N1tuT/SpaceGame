package entities;

import main.GamePanel;
import utils.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class NPC_Mentor extends Entity{

    public NPC_Mentor (GamePanel gp) {
        super(gp);

        SpriteSheet sheet = new SpriteSheet("/entities/ncp1_sprite.png");
        BufferedImage[] sprite = sheet.getFrames(16, 16, 12);

        down  = sprite[0];  left  = sprite[1];  right  = sprite[2];  up  = sprite[3];
        down1 = sprite[4];  left1 = sprite[5];  right1 = sprite[6];  up1 = sprite[7];
        down2 = sprite[8];  left2 = sprite[9];  right2 = sprite[10]; up2 = sprite[11];

        direction = "down";
        speed = 2;
        x = 200;
        y = 200;

    }

    public void draw (Graphics2D g2) {
        g2.drawImage(down, x, y, gp.tileSize, gp.tileSize, null);
    }

}
