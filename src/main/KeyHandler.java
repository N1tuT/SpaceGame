package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class KeyHandler implements KeyListener, MouseListener {

    GamePanel gp;
    public boolean
    upPressed, downPressed, leftPressed, rightPressed;

    public KeyHandler (GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP)    upPressed = true;
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN)  downPressed = true;
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT)  leftPressed = true;
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) rightPressed = true;

        if (code == KeyEvent.VK_C) System.exit(0);

        if (code == KeyEvent.VK_ESCAPE) {
            if (gp.gameState == gp.PLAY_STATE || gp.gameState == gp.PAUSE_STATE || gp.previousState == gp.PAUSE_STATE){
                if (gp.gameState == gp.PAUSE_STATE) gp.gameState = gp.PLAY_STATE;
                else {
                    gp.previousState = gp.gameState;
                    gp.gameState = gp.PAUSE_STATE;
                }
            }
        }
     }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP)    upPressed = false;
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN)  downPressed = false;
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT)  leftPressed = false;
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) rightPressed = false;

    }

    @Override public void keyTyped(KeyEvent e) {}
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}
