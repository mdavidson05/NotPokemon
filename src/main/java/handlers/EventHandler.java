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
            eventHitbox[col][row] .x = 23;
            eventHitbox[col][row] .y = 23;
            eventHitbox[col][row] .width = 2; //highlight how this was small in presentation
            eventHitbox[col][row] .height = 2;
            eventHitbox[col][row].eventHitboxDefaultX  = eventHitbox[col][row] .x;
            eventHitbox[col][row].eventHitboxDefaultY  = eventHitbox[col][row] .y;

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
        if (canTouchEvent == true) {
            if (hit(2, 9, "any") == true) {
                System.out.println("hit");
                teleport(gamePanel.profOakLabState);
//
            }

        }
    }

    public boolean hit(int col, int row, String direction) {
        boolean hit = false;
        gamePanel.player.playerHitbox.x = gamePanel.player.worldX + gamePanel.player.playerHitbox.x;
        gamePanel.player.playerHitbox.y = gamePanel.player.worldY + gamePanel.player.playerHitbox.y;
        eventHitbox[col][row].x = col * gamePanel.tileSize + eventHitbox[col][row].x;
        eventHitbox[col][row].y = row * gamePanel.tileSize + eventHitbox[col][row].y;

        if (gamePanel.player.solidArea.intersects(eventHitbox[col][row])) {
            if (gamePanel.player.direction.contentEquals(direction) || direction.contentEquals("any")) ;
            hit = true;
            System.out.println("hit");

            previousEventX = gamePanel.player.worldX;
            previousEventY = gamePanel.player.worldY;
        }
        gamePanel.player.playerHitbox.x = gamePanel.player.solidAreaDefaultX;
        gamePanel.player.playerHitbox.y = gamePanel.player.solidAreaDefaultY;

        eventHitbox[col][row].x = eventHitbox[col][row].eventHitboxDefaultX;
        eventHitbox[col][row].y = eventHitbox[col][row].eventHitboxDefaultY;

        return hit;

    }

    public void teleport(int gameState) {
        if (gameState == gamePanel.playState) {
            tileManager.setImagePath("Maps/NewBarkTownMap.txt");
        } else if (gameState == gamePanel.profOakLabState) {
            tileManager.setImagePath("Maps/TestMap.txt");
        }
        tileManager.loadMap(tileManager.imagePath);

        canTouchEvent = false;
    }

}
