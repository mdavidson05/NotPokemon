package handlers;

import Battle.Battle;
import characters.CollisionCheck;
import characters.Entity;
import characters.NPC_Boy;
import characters.Player;
import items.SuperItem;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 20;
    final int maxScreenRow = 19;
    public final int screenWidth = tileSize*maxScreenCol; //SHOULD BE 768 PIXELS
    public final int screenHeight = tileSize*maxScreenRow; //SHOULD BE 576 PIXELS

    //WORLD SETTINGS
    public final int maxWorldCol = 20;
    public final int maxWorldRow = 19;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    //FPS
    int FPS = 60;

    //Timein Game
    Thread gameThread;
    //User Input
    public KeyHandler keyHandler = new KeyHandler(this);
    //Player
    public Player player = new Player(this, keyHandler);
    //Tile Manager
    public TileManager tileManager = new TileManager(this);
    //Collision checker
    public CollisionCheck collisionCheck = new CollisionCheck(this);
    //Items
    public SuperItem item[] = new SuperItem[10]; //CAN DISPLAY UP 10 X MANY OBJECTS AT THE SAME TIME.
    public ItemSetter itemSetter = new ItemSetter(this);
    //UI
    public UI ui = new UI(this);
    //NPCs
    public Entity npc[] = new Entity[10];
    //EventHandller
    public EventHandler eventHandler = new EventHandler(this);
//Fight menu
    public NPC_Boy npcBoy = new NPC_Boy(this);
    public Battle battle = new Battle(player, npcBoy,this);


    //GameStates
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState =3;
    public final int titleState = 0;
    public final int profOakLabState =4;
    public final int characterState = 5;
    public final int fightState = 6;
    public final int attackState = 7;

    //maps
    public final int maxMap = 10;
    public int currentMap = 0;


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

    public void setupGame(){
        itemSetter.setItem();
        itemSetter.setNPC();

        gameState = titleState;
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
            //Sleep method (doesn't work)
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
        if(gameState == playState) {
            player.update();
            //NPC
            for(int i = 0; i < npc.length; i++){
                if(npc[i] != null){
                    npc[i].update();
                }
            }
        }
        if(gameState == pauseState){

        }
        //Fight Screen
        if(gameState == fightState){
            player.update();
        }
        if(gameState == attackState){
            player.update();
        }
    }


    public void paintComponent(Graphics graphics) { //paintComponenet is in-built Java component
        super.paintComponent(graphics); //super as paint component extends from paintComponent class in Java

        Graphics2D graphics2 = (Graphics2D)graphics; //change graphics to 2D as this has in-built functions that i NEED

        long drawStart = 0;
        if(keyHandler.checkDrawTime == true){
            drawStart = System.nanoTime();
        }
//        Title Screen
        if(gameState == titleState) {
            ui.draw(graphics2);
        }
        else{
            tileManager.draw(graphics2);

            for(int i = 0; i < item.length; i++){
                if(item[i] != null){
                    item[i].draw(graphics2,this);

                }
            }
            //Draw new NPC
            for(int i = 0; i < npc.length; i++){
                if(npc[i] != null){
                    npc[i].draw(graphics2);
                }
            }
            player.draw(graphics2);

            ui.draw(graphics2);

//            battle.drawFightScreen(graphics2);
        }

        //Everything else


        //Find the time taken to draw a tile
        if (keyHandler.checkDrawTime == true) {
            long drawEnd = System.nanoTime();
            long timePassed = drawEnd - drawStart;
            graphics2.setColor(Color.white);
            graphics2.drawString("Draw Time: " + timePassed, 10, 400);
            System.out.println("Draw Time: " + timePassed);
        }
        graphics2.dispose(); //works without this line but saves memory. Good practice

    }
}
