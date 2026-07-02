package main;

import entities.NPC_Mentor;
import entities.Player;
import screens.OptionScreen;
import screens.PauseScreen;
import screens.TitleScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable {

    // --- SCREEN SETTINGS ---
    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 20;
    final int maxScreenRow = 12;

    public final int screenW = tileSize * maxScreenCol;    // 960 pixels
    public final int screenH = tileSize * maxScreenRow;    // 576 pixels

    public int screenW2 = screenW;
    public int screenH2 = screenH;

    public int gameState;
    public int previousState;

    BufferedImage tempScreen;
    Graphics2D g2;

    public boolean fullScreen;

    Thread gameThread;
    KeyHandler keyH = new KeyHandler(this);
    Player player;
    NPC_Mentor mentor;

    int FPS = 60;

    TitleScreen titleScreen;
    OptionScreen optionScreen;
    PauseScreen pauseScreen;

    // --- FLAGS ---
    public final int TITLE_STATE = 0;
    public final int PAUSE_STATE = 1;
    public final int OPTIONS_STATE = 2;
    public final int PLAY_STATE = 3;

    public GamePanel () {

        this.setPreferredSize(new Dimension(screenW, screenH));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        titleScreen = new TitleScreen(this);
        optionScreen = new OptionScreen(this);
        pauseScreen = new PauseScreen(this);

    }

    public void setupGame () {

        player = new Player(this, keyH);
        mentor = new NPC_Mentor(this);

        tempScreen = new BufferedImage(screenW, screenH, BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D) tempScreen.getGraphics();
        fullScreen = false;
        gameState = TITLE_STATE;

    }

    public void setFullScreen () {
        Main.setDecoration(this, false);

        // Get local screen device info (monitor / laptop screen)
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(Main.window);

        // Get full screen dimensions
        screenW2 = Main.window.getWidth();
        screenH2 = Main.window.getHeight();
        fullScreen = !fullScreen;
    }

    public void exitFullScreen () {
        Main.setDecoration(this, true);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(null);

        screenW2 = screenW;
        screenH2 = screenH;
        fullScreen = !fullScreen;
    }

    public void startGameThread () {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1_000_000_000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;


        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                drawToTempScreen();
                drawToScreen();
                delta--;
            }

        }

    }

    public void update () {
        if (gameState == PLAY_STATE) {
            player.update();
//            mentor.update();
        }

    }

    public void drawToTempScreen () {

        g2.setColor(Color.black);
        g2.fillRect(0, 0, screenW, screenH);

        if (gameState == TITLE_STATE) {
            titleScreen.draw(g2);
        } else if (gameState == OPTIONS_STATE) {
            optionScreen.draw(g2);
        } else if (gameState == PAUSE_STATE) {
            pauseScreen.draw(g2);
        } else if (gameState == PLAY_STATE) {
            mentor.draw(g2);
            player.draw(g2);
        }

    }

    public void drawToScreen () {
        Graphics g = getGraphics();
        g.drawImage(tempScreen, 0, 0, screenW2, screenH2, null);
        g.dispose();
    }

}
