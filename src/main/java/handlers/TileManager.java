package handlers;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gamePanel;
    Tile[] tile;
    int mapTileNumber[][];

    //NewBarkTown
    //320x288
    //320/16 x 288/16 = 20x18px
    //IF player xpositions and ypositinon == correct number load map2

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tile = new Tile[10]; // will expand when developing more areas
        mapTileNumber = new int [gamePanel.maxWorldCol][gamePanel.maxWorldRow];
        getTileImage();
        loadMap();
    }
    public void getTileImage() {

        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/tree.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/Roof.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/Grass.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/window BR.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/window BL.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/Building.png"));

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/Sign.png"));

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/Door.png"));

            tile[8] = new Tile();
            tile[8].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/Sand.png"));

            tile[9] = new Tile();
            tile[9].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/Water.png"));


        }
        catch(IOException e){
            e.printStackTrace();
        }

    }

    public void loadMap() {

        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("Maps/NewBarkTownMap.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row= 0;

            while(col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow) {
                String line = br.readLine();

                while(col < gamePanel.maxWorldCol) {
                    char numbers[] = line.toCharArray();

                    int num = Integer.parseInt(String.valueOf(numbers[col]));

                    mapTileNumber[col][row] = num;
                    col++;
                }
                if(col == gamePanel.maxScreenCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
        }
    }

    public void draw(Graphics2D graphics2){
        int worldCol = 0;
        int worldRow = 0;


        while(worldCol < gamePanel.maxScreenCol && worldRow < gamePanel.maxScreenRow){

            int tileNumber = mapTileNumber[worldCol][worldRow];

            int worldX = worldCol * gamePanel.tileSize;
            int worldY = worldRow * gamePanel.tileSize;
            int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenY;
            int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;


            //Dynamic rendering loop. Remember to fix the boundaries
            if(worldX + gamePanel.tileSize >gamePanel.player.worldX - gamePanel.player.screenX &&
            worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
            worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
            worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY){

                graphics2.drawImage(tile[tileNumber].image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);

            }

            worldCol++;


            if (worldCol == gamePanel.maxScreenCol) {
                worldCol = 0;
                worldRow++;

            }
        }
//        graphics2.drawImage(tile[0].image, 0, 0, gamePanel.screenWidth,gamePanel.screenHeight, null);
    }
}
