package handlers;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gamePanel;
    public Tile[] tile;
    public int[][] mapTileNumber;
    String imagePath;
    //NewBarkTown
    //320x288
    //320/16 x 288/16 = 20x18px
    //IF player xpositions and ypositinon == correct number load map2

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tile = new Tile[10]; // will expand when developing more areas
        mapTileNumber = new int [gamePanel.maxWorldCol][gamePanel.maxWorldRow];
        getTileImage();
        imagePath = "Maps/NewBarkTownMap.txt";
        loadMap(imagePath);
    }
    public void getTileImage() {

//        try {
//            tile[0] = new Tile();
//            tile[0].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/tree.png"));
//            tile[0].collision = true;
            setup(0, "tree", true);
            setup(1, "Roof", true);
            setup(2, "Grass", false);
            setup(3, "window BR", true);
            setup(4, "window BL", true);
            setup(5, "Building", true);
            setup(6, "Sign", true);
            setup(7, "Door", false);
            setup(8, "Sand", false);
            setup(9, "water", true);

//        }
//        catch(IOException e){
//            e.printStackTrace();
//        }

    }
    public void setup(int index, String imagePath, boolean collision){
        Scaling scaling = new Scaling();
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/"+imagePath+".png"));
            tile[index].image = scaling.scaleImage(tile[index].image, gamePanel.tileSize, gamePanel.tileSize);
            tile[index].collision = collision;
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void loadMap(String imagePath) {

        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(imagePath);
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
//should be screenX and Screen Y instead of 0, 0
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
