import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "backwards";
    }

    public void getPlayerImage() {
        try {
            forward1 = ImageIO.read(getClass().getResourceAsStream("Character_Moves/Forward_Left.png"));
            forward2 = ImageIO.read(getClass().getResourceAsStream("Character_Moves/Forward_Right.png"));
            backwards1 = ImageIO.read(getClass().getResourceAsStream("Character_Moves/Backward_Left.png"));
            backwards2 = ImageIO.read(getClass().getResourceAsStream("Character_Moves/Backward_Right.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("Character_Moves/Left_Left.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("Character_Moves/Left_Still.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("Character_Moves/Right_Right.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("Character_Moves/Right_Still.png"));

        }catch(IOException e){

        }
    }

    public void update() { //gets called 60x per second as in game loop

        if (keyHandler.upPressed == true || keyHandler.downPressed == true || keyHandler.leftPressed == true || keyHandler.rightPressed == true) {

            if (keyHandler.upPressed == true) {
                direction = "forward";
                y -= speed;
            } else if (keyHandler.leftPressed == true) {
                direction = "left";
                x -= speed;
            } else if (keyHandler.downPressed == true) {
                direction = "backward";
                y += speed;
            } else if (keyHandler.rightPressed == true) {
                direction = "right";
                x += speed;
            }
            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNumber == 1) {
                    spriteNumber = 2;
                } else if (spriteNumber == 2) {
                    spriteNumber = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D graphics2){

//        graphics2.setColor(Color.white);
//        graphics2.fillRect(x,y,gamePanel.tileSize,gamePanel.tileSize);
        BufferedImage image = null;
        switch(direction) {
            case "forward":
                if(spriteNumber == 1){
                image = forward1;
                }
                if(spriteNumber ==2){
                    image = forward2;
                }
                break;
            case "backward": {
                if(spriteNumber == 1){
                    image = backwards1;
                }
                if(spriteNumber ==2){
                    image = backwards2;
                }
                break;
                }
            case "left": {
                if(spriteNumber == 1){
                    image = left1;
                }
                if(spriteNumber ==2){
                    image = left2;
                }
                break;
            }
            case "right": {
                if(spriteNumber == 1){
                image = right1;
                }
                if(spriteNumber ==2){
                    image = right2;
                }
                break;
                }
            }
            graphics2.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);

        }


    }

