package characters;

import handlers.GamePanel;

public class CollisionCheck {

    GamePanel gamePanel;

    public CollisionCheck(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public void checkTile(Entity entity){

        int entityLeftWorldX = entity.worldX + entity.playerHitbox.x;
        int entityRightWorldX = entity.worldX + entity.playerHitbox.x + entity.playerHitbox.width;
        int entityTopWorldY = entity.worldY + entity.playerHitbox.y;
        int entityBottomWorldY = entity.worldY +entity.playerHitbox.y + entity.playerHitbox.height;

        int entityLeftCol = entityLeftWorldX/gamePanel.tileSize;
        int entityRightCol = entityRightWorldX/ gamePanel.tileSize;
        int entityTopRow = entityTopWorldY/ gamePanel.tileSize;
        int entityBottomRow = entityBottomWorldY/ gamePanel.tileSize;

        //only need to check two tiles in each direction(I think...) ask Stuart
        int tileNumber1, tileNumber2;

        //need to predict player will be after he moved

        switch (entity.direction) {
            case "forward":
                entityTopRow = (entityTopWorldY - entity.speed)/ gamePanel.tileSize;
                tileNumber1 = gamePanel.tileManager.mapTileNumber[entityLeftCol][entityTopRow];
                tileNumber2 = gamePanel.tileManager.mapTileNumber[entityRightCol][entityTopRow];
                if(gamePanel.tileManager.tile[tileNumber1].collision == true || gamePanel.tileManager.tile[tileNumber2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "backward":
                entityBottomRow = (entityBottomWorldY + entity.speed)/ gamePanel.tileSize;
                tileNumber1 = gamePanel.tileManager.mapTileNumber[entityLeftCol][entityBottomRow];
                tileNumber2 = gamePanel.tileManager.mapTileNumber[entityRightCol][entityBottomRow];
                if(gamePanel.tileManager.tile[tileNumber1].collision == true || gamePanel.tileManager.tile[tileNumber2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/ gamePanel.tileSize;
                tileNumber1 = gamePanel.tileManager.mapTileNumber[entityLeftCol][entityTopRow];
                tileNumber2 = gamePanel.tileManager.mapTileNumber[entityLeftCol][entityBottomRow];
                if(gamePanel.tileManager.tile[tileNumber1].collision == true || gamePanel.tileManager.tile[tileNumber2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/ gamePanel.tileSize;
                tileNumber1 = gamePanel.tileManager.mapTileNumber[entityRightCol][entityTopRow];
                tileNumber2 = gamePanel.tileManager.mapTileNumber[entityRightCol][entityBottomRow];
                if(gamePanel.tileManager.tile[tileNumber1].collision == true || gamePanel.tileManager.tile[tileNumber2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
        }



    }
}
