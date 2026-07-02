package screens;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class TitleScreen extends Screen {

    BufferedImage titleScreenImage;

    // Menu item positions — adjust these x/y values to match where the text sits on your image
    Rectangle newGameBounds  = new Rectangle(147,  462, 138, 32);
    Rectangle loadGameBounds = new Rectangle(405, 462, 148, 32);
    Rectangle optionsBounds  = new Rectangle(694, 462, 98, 32);

    public TitleScreen (GamePanel gp) {
        super(gp);

        try {
            titleScreenImage = ImageIO.read(getClass().getResourceAsStream("/screens/title_screen.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        // Draw background
        g2.drawImage(titleScreenImage, 0, 0, gp.screenW, gp.screenH, null);
        drawAsteroid(g2);

//        g2.setColor(Color.red);
//        g2.draw(newGameBounds);
//        g2.draw(loadGameBounds);
//        g2.draw(optionsBounds);

    }

    @Override
    protected Rectangle getItemBounds (int item) {
        return switch (item) {
            case 0 -> newGameBounds;
            case 1 -> loadGameBounds;
            case 2 -> optionsBounds;
            default -> null;
        };
    }

    @Override
    protected int getItemCount() {
        return 3;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point p = scaleMousePoint(e.getPoint());
        if (newGameBounds.contains(p))          gp.gameState = gp.PLAY_STATE;
        else if (loadGameBounds.contains(p))    System.out.println("PLAY");
        else if (optionsBounds.contains(p)) {
            gp.previousState = gp.gameState;
            gp.gameState = gp.OPTIONS_STATE;
        }
    }
}
