package handlers;

import characters.Player;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 20;
    final int maxScreenRow = 18;
    public final int screenWidth = tileSize*maxScreenCol; //SHOULD BE 768 PIXELS
    public final int screenHeight = tileSize*maxScreenRow; //SHOULD BE 576 PIXELS

    //WORLD SETTINGS
    public final int maxWorldCol = 20;
    public final int maxWorldRow = 18;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    //FPS
    int FPS = 60;

    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();
    public Player player = new Player(this, keyHandler);
    TileManager tileManager = new TileManager(this);

//    //Set default player location testing
//    int playerPositionX = 100;
//    int playerPositionY = 100;
//    int playerSpeed = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // this improves better gaming performance
        this.addKeyListener(keyHandler);
        this.setFocusable(true); // Handlers.GamePanel can be "focused" to recieve key input? Still unclear on what it does
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run() {

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint();
                delta --;
            }
//            System.out.println(playerPositionX);
//            System.out.println(playerPositionY);
        }
//            if(timer >= 1000000000){ //check is running at 60FPS
//            System.out.println("FPS: " + drawCount);
//            drawCount = 0;
//            timer = 0;
//        }
    }

    private void update() {
        player.update();
    }


    public void paintComponent(Graphics graphics) { //paintComponenet is in-built Java component
        super.paintComponent(graphics); //super as paint component extends from paintComponent class in Java

        Graphics2D graphics2 = (Graphics2D)graphics; //change graphics to 2D as this has in-built functions that i NEED
        tileManager.draw(graphics2);
        player.draw(graphics2);
        graphics2.dispose(); //works without this line but saves memory. Good practice

    }
}
