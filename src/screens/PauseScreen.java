package screens;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PauseScreen extends Screen {

    BufferedImage pauseScreen;

    Rectangle resume = new Rectangle(143, 190, 192, 44);
    Rectangle options = new Rectangle(143, 287, 192, 46);
    Rectangle save = new Rectangle(143, 382, 302, 46);

    public PauseScreen (GamePanel gp) {
        super(gp);

        try {
            pauseScreen = ImageIO.read(getClass().getResourceAsStream("/screens/pause_screen.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw (Graphics2D g2) {
        g2.drawImage(pauseScreen, 0, 0, gp.screenW, gp.screenH, null);
        drawAsteroid(g2);

//        g2.setColor(Color.green);
//        g2.draw(resume);
//        g2.draw(options);
//        g2.draw(save);
    }

    @Override
    protected Rectangle getItemBounds(int item) {
        return switch (item) {
            case 0 -> resume;
            case 1 -> options;
            case 2 -> save;
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

        if (resume.contains(p)) gp.gameState = gp.previousState;
        else if (options.contains(p)) {
            gp.previousState = gp.gameState;
            gp.gameState = gp.OPTIONS_STATE;
        } else if (save.contains(p)) gp.gameState = gp.TITLE_STATE;
    }
}
