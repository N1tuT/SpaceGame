package entities;

import main.GamePanel;
import main.KeyHandler;
import utils.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {

        super(gp);
        this.keyH = keyH;

        screenX = gp.screenW / 2 - (gp.tileSize / 2);
        screenY = gp.screenH / 2 - (gp.tileSize / 2);

        hitbox = new Rectangle();
        hitbox.x = 8;
        hitbox.y = 16;
        hitboxX = hitbox.x;
        hitboxY = hitbox.y;
        hitbox.width = 32;
        hitbox.height = 32;

        SpriteSheet sheet = new SpriteSheet("/entities/player_sprite.png");
        BufferedImage[] sprite = sheet.getFrames(16, 16, 12);

        down  = sprite[0];  left  = sprite[1];  right  = sprite[2];  up  = sprite[3];
        down1 = sprite[4];  left1 = sprite[5];  right1 = sprite[6];  up1 = sprite[7];
        down2 = sprite[8];  left2 = sprite[9];  right2 = sprite[10]; up2 = sprite[11];

        x = 100;
        y = 100;
        speed = 5;
        direction = "down";
    }

    public void update() {
        boolean moving = keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed;
        if (!moving) return;

        if (keyH.upPressed)    { y -= speed; direction = "up"; }
        if (keyH.downPressed)  { y += speed; direction = "down"; }
        if (keyH.leftPressed)  { x -= speed; direction = "left"; }
        if (keyH.rightPressed) { x += speed; direction = "right"; }

        if (++spriteCounter > 10) {
            spriteNum = (spriteNum == 1) ? 2 : 1;
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {
        boolean moving = keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed;

        BufferedImage image = switch (direction) {
            case "up"    -> moving ? (spriteNum == 1 ? up1    : up2)    : up;
            case "down"  -> moving ? (spriteNum == 1 ? down1  : down2)  : down;
            case "left"  -> moving ? (spriteNum == 1 ? left1  : left2)  : left;
            case "right" -> moving ? (spriteNum == 1 ? right1 : right2) : right;
            default -> down;
        };

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);

    }
}