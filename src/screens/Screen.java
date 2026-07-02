package screens;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Screen implements MouseMotionListener, MouseListener {

    protected GamePanel gp;
    protected BufferedImage asteroid;
    protected int hoveredItem = -1;

    public Screen (GamePanel gp) {
        this.gp = gp;

        try {
            asteroid = ImageIO.read(getClass().getResourceAsStream("/screens/asteroid.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        gp.addMouseMotionListener(this);
        gp.addMouseListener(this);

    }

    public abstract void draw (Graphics2D g2);
    protected abstract Rectangle getItemBounds (int item);
    protected abstract int getItemCount ();

    protected void drawAsteroid (Graphics2D g2) {
        if (hoveredItem != -1 && asteroid != null) {
            Rectangle hovered = getItemBounds(hoveredItem);
            if (hovered != null) {
                int asteroidX = hovered.x + (hovered.width / 2) - (asteroid.getWidth() / 2);
                int asteroidY = hovered.y + hovered.height + 5;
                g2.drawImage(asteroid, asteroidX, asteroidY, null);
            }
        }
    }

    protected Point scaleMousePoint (Point p) {
        double scaleX = (double) gp.screenW / gp.screenW2;
        double scaleY = (double) gp.screenH / gp.screenH2;
        return new Point(
                (int) (p.x * scaleX),
                (int) (p.y * scaleY)
        );
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Point p = scaleMousePoint(e.getPoint());
        hoveredItem = -1;
        for (int i = 0; i < getItemCount(); i++) {
            Rectangle bounds = getItemBounds(i);
            if (bounds != null && bounds.contains(p)) {
                hoveredItem = i;
                break;
            }
        }
    }

    @Override public void mouseDragged(MouseEvent e) {}
    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}

}
