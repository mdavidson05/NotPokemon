package characters;

import handlers.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    //Motion
    public int worldX,worldY;
    public int speed;

    //Animation
    GamePanel gamePanel;
    public BufferedImage forward1, forward2, backwards1, backwards2, left1, left2, right1, right2; //Buffered Image describes an image that contains data we can access
    public String direction;
    public int spriteCounter = 0;
    public int spriteNumber = 1;
    public Rectangle playerHitbox = new Rectangle(0,0,48,48);
    public boolean collisionOn = false;

    public Entity(GamePanel gamePanel){
        this.gamePanel=gamePanel;
    }
}
