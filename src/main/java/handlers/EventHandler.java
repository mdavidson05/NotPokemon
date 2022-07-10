package handlers;

import java.awt.*;

public class EventHandler {
    GamePanel gamePanel;
//    Rectangle eventHitBox;
//    int eventHitboxDefaultX, eventHitboxDefaultY;
    EventHitbox eventHitbox[][];
    TileManager tileManager;

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;



    public EventHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        eventHitbox = new EventHitbox[gamePanel.maxWorldCol][gamePanel.maxWorldRow];

        int col = 0;
        int row = 0;
        while (col< gamePanel.maxWorldCol && row < gamePanel.maxWorldRow){
            eventHitbox[col][row] = new EventHitbox();
            eventHitbox[col][row].x = 4;
            eventHitbox[col][row].y = 7;
            eventHitbox[col][row].width = 16; //highlight how this was small in presentation
            eventHitbox[col][row].height = 16;
            eventHitbox[col][row].eventHitboxDefaultX  = eventHitbox[col][row].x;
            eventHitbox[col][row].eventHitboxDefaultY  = eventHitbox[col][row].y;

            col++;
            if(col == gamePanel.maxWorldCol){
                col = 0;
                row++;
            }
        }


    }

    public void checkEvent() {
        //check player is 1 tile away
        int xDistance = Math.abs(gamePanel.player.worldX - previousEventX); //needed to make abs as was using  anegative number
        int yDistance = Math.abs(gamePanel.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if (distance > gamePanel.tileSize) {
            canTouchEvent = true;
        }
//

        if (canTouchEvent == true) {
            if (hit(0,7, 4, "any") == true) {
                System.out.println("hit");
                teleport(1,12,13);
//
            }
            else if (hit(1,12, 13, "any") == true) {
                System.out.println("hit");
                teleport(0,4,7);
//
            }

        }
    }

    public boolean hit(int map, int col, int row, String direction) {
        boolean hit = false;
        gamePanel.player.playerHitbox.x = gamePanel.player.worldX + gamePanel.player.playerHitbox.x;
        gamePanel.player.playerHitbox.y = gamePanel.player.worldY + gamePanel.player.playerHitbox.y;
        eventHitbox[col][row].x = col * gamePanel.tileSize + eventHitbox[col][row].x;
        eventHitbox[col][row].y = row * gamePanel.tileSize + eventHitbox[col][row].y;
//        System.out.println(eventHitbox[col][row]);
//        System.out.println(gamePanel.player.playerHitbox);

        if (gamePanel.player.playerHitbox.intersects(eventHitbox[col][row])) {
            if (gamePanel.player.direction.contentEquals(direction) || direction.contentEquals("any")) ;
            hit = true;
//            System.out.println("hit");

            previousEventX = gamePanel.player.worldX;
            previousEventY = gamePanel.player.worldY;
        }
        gamePanel.player.playerHitbox.x = gamePanel.player.solidAreaDefaultX;
        gamePanel.player.playerHitbox.y = gamePanel.player.solidAreaDefaultY;

        eventHitbox[col][row].x = eventHitbox[col][row].eventHitboxDefaultX;
        eventHitbox[col][row].y = eventHitbox[col][row].eventHitboxDefaultY;

        return hit;

    }

    public void teleport(int map, int col, int row) {
        gamePanel.currentMap = map;
        gamePanel.player.worldX = gamePanel.tileSize*col;
        gamePanel.player.worldY = gamePanel.tileSize*row;
        previousEventX = gamePanel.player.worldX;
        previousEventY = gamePanel.player.worldY;
        canTouchEvent = false;
    }

}
