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

    public int checkItem(Entity entity, boolean player) {
        int index = 999;

        for(int i = 0; i < gamePanel.item.length; i++) {
            if (gamePanel.item[i] != null) {
                //get entity hitbox position
                entity.playerHitbox.x = entity.worldX + entity.playerHitbox.x;
                entity.playerHitbox.y = entity.worldY + entity.playerHitbox.y;
                //get item hitbox
                gamePanel.item[i].solidArea.x = gamePanel.item[i].worldX + gamePanel.item[i].solidArea.x;
                gamePanel.item[i].solidArea.y = gamePanel.item[i].worldY + gamePanel.item[i].solidArea.y;

                switch (entity.direction) {
                    case "forward":
                        entity.playerHitbox.y -= entity.speed;
                        if (entity.playerHitbox.intersects(gamePanel.item[i].solidArea)) {
                            // intersects checks if two objects are colliding
                            if(gamePanel.item[i].collision == true){
                                entity.collisionOn = true;
                                if(player == true){
                                    index = i;
                                }
                            }
                        }
                        break;
                    case "backward":
                        entity.playerHitbox.y += entity.speed;
                        if (entity.playerHitbox.intersects(gamePanel.item[i].solidArea)) {
                            // intersects checks if two objects are colliding
//                            System.out.println("backward collision");
                            if(gamePanel.item[i].collision == true){
                                entity.collisionOn = true;
                                if(player == true){
                                    index = i;
                                }
                            }
                        }
                        break;
                    case "left":
                        entity.playerHitbox.x -= entity.speed;
                        if (entity.playerHitbox.intersects(gamePanel.item[i].solidArea)) {
                            if(gamePanel.item[i].collision == true){
                                entity.collisionOn = true;
                                if(player == true){
                                    index = i;
                                }
                        }}
                        break;
                    case "right":
                        entity.playerHitbox.x += entity.speed;
                        if (entity.playerHitbox.intersects(gamePanel.item[i].solidArea)) {
                            // intersects checks if two objects are colliding
                            if(gamePanel.item[i].collision == true){
                                entity.collisionOn = true;
                                if(player == true){
                                    index = i;
                                }
                            break;
                        }}

                }
                entity.playerHitbox.x = entity.solidAreaDefaultX;
                entity.playerHitbox.y = entity.solidAreaDefaultY;
                gamePanel.item[i].solidArea.x = gamePanel.item[i].solidAreaDefaultX;
                gamePanel.item[i].solidArea.y = gamePanel.item[i].solidAreaDefaultY;

            }
        }

        return index;
    }

    //NPC Collision
    public int checkEntity(Entity entity, Entity[] target){
        int index = 999;

        for(int i = 0; i < target.length; i++) {
            if (target[i] != null) {
                //get entity hitbox position
                entity.playerHitbox.x = entity.worldX + entity.playerHitbox.x;
                entity.playerHitbox.y = entity.worldY + entity.playerHitbox.y;
                //get item hitbox
                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

                switch (entity.direction) {
                    case "forward":
                        entity.playerHitbox.y -= entity.speed;
                        if (entity.playerHitbox.intersects(target[i].solidArea)) {
                            // intersects checks if two objects are colliding
                                entity.collisionOn = true;
                                index = i;
                            }

                        break;

                    case "backward":
                        entity.playerHitbox.y += entity.speed;
                        if (entity.playerHitbox.intersects(target[i].solidArea)) {
                            // intersects checks if two objects are colliding
//                            System.out.println("backward collision");
                                entity.collisionOn = true;
                                index = i;
                        }
                        break;
                    case "left":
                        entity.playerHitbox.x -= entity.speed;
                        if (entity.playerHitbox.intersects(target[i].solidArea)) {
                                entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "right":
                        entity.playerHitbox.x += entity.speed;
                        if (entity.playerHitbox.intersects(target[i].solidArea)) {
                            // intersects checks if wtwo objects are colliding
                                entity.collisionOn = true;
                            index = i;
                            break;
                            }

                }
                entity.playerHitbox.x = entity.solidAreaDefaultX;
                entity.playerHitbox.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;

            }
        }

        return index;
    }

    public void checkPlayer(Entity entity){
        int index = 999;
                //get entity hitbox position
                entity.playerHitbox.x = entity.worldX + entity.playerHitbox.x;
                entity.playerHitbox.y = entity.worldY + entity.playerHitbox.y;
                //get item hitbox
        gamePanel.player.solidArea.x = gamePanel.player.worldX + gamePanel.player.solidArea.x;
        gamePanel.player.solidArea.y = gamePanel.player.worldY + gamePanel.player.solidArea.y;

                switch (entity.direction) {
                    case "forward":
                        entity.playerHitbox.y -= entity.speed;
                        if (entity.playerHitbox.intersects(gamePanel.player.solidArea)) {
                            // intersects checks if two objects are colliding
                            entity.collisionOn = true;
                        }

                        break;

                    case "backward":
                        entity.playerHitbox.y += entity.speed;
                        if (entity.playerHitbox.intersects(gamePanel.player.solidArea)) {
                            // intersects checks if two objects are colliding
//                            System.out.println("backward collision");
                            entity.collisionOn = true;
                        }
                        break;
                    case "left":
                        entity.playerHitbox.x -= entity.speed;
                        if (entity.playerHitbox.intersects(gamePanel.player.solidArea)) {
                            entity.collisionOn = true;
                        }
                        break;
                    case "right":
                        entity.playerHitbox.x += entity.speed;
                        if (entity.playerHitbox.intersects(gamePanel.player.solidArea)) {
                            // intersects checks if wtwo objects are colliding
                            entity.collisionOn = true;
                            break;
                        }

                }
                entity.playerHitbox.x = entity.solidAreaDefaultX;
                entity.playerHitbox.y = entity.solidAreaDefaultY;
        gamePanel.player.solidArea.x = gamePanel.player.solidAreaDefaultX;
        gamePanel.player.solidArea.y = gamePanel.player.solidAreaDefaultY;

            }
        }


