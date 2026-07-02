package screens;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class OptionScreen extends Screen {

    BufferedImage optionScreen;

    // Menu item positions — adjust these x/y values to match where the text sits on your image
    Rectangle zoomIn  = new Rectangle(249,  158, 22, 22);
    Rectangle zoomOut = new Rectangle(356, 158, 22, 22);
    Rectangle fullScreen  = new Rectangle(212, 254, 25, 25);
    Rectangle exit  = new Rectangle(287, 345, 55, 30);
    Rectangle back  = new Rectangle(44, 32, 60, 30);

    public OptionScreen (GamePanel gp) {
        super(gp);

        try {
            optionScreen = ImageIO.read(getClass().getResourceAsStream("/screens/options_screen.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw (Graphics2D g2) {
        g2.drawImage(optionScreen, 0, 0, gp.screenW, gp.screenH, null);
        drawAsteroid(g2);

//        g2.setColor(Color.red);
//        g2.draw(zoomIn);
//        g2.draw(zoomOut);
//        g2.draw(fullScreen);
//        g2.draw(exit);
//        g2.draw(back);

    }

    @Override
    protected Rectangle getItemBounds(int item) {
        return switch (item) {
            case 0 -> zoomIn;
            case 1 -> zoomOut;
            case 2 -> fullScreen;
            case 3 -> exit;
            case 4 -> back;
            default -> null;
        };
    }

    @Override
    protected int getItemCount() {
        return 5;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point p = scaleMousePoint(e.getPoint());

        if (zoomIn.contains(p))         System.out.println("In");
        else if (zoomOut.contains(p))   System.out.println("Out");

        else if (fullScreen.contains(p))
            if (!gp.fullScreen) gp.setFullScreen();
            else gp.exitFullScreen();

        else if (exit.contains(p))      System.exit(0);
        else if (back.contains(p))      gp.gameState = gp.previousState;
    }

}
