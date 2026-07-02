package entities;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    GamePanel gp;

    public int x, y, speed;

    public String direction;
    public BufferedImage
            up, up1, up2,
            down, down1, down2,
            left, left1, left2,
            right, right1, right2;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle hitbox;
    public int hitboxX, hitboxY;
    public boolean collisionOn = false;

    public Entity (GamePanel gp) {
        this.gp = gp;
    }

}
