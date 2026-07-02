package main;

import javax.swing.*;

public class Main {

    public static JFrame window = new JFrame();

    public static void main (String[] args) {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Space Game");
        window.setUndecorated(false);

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();

    }

    public static void setDecoration (GamePanel gp, boolean decorated) {
        Thread pauseThread = gp.gameThread;
        gp.gameThread = null;

        try {
            pauseThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        window.dispose();
        window.setUndecorated(!decorated);
        window.setVisible(true);

        gp.startGameThread();
    }

}
