package characters;

import handlers.GamePanel;
import handlers.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        super(gamePanel);
        this.keyHandler = keyHandler;

        screenX = gamePanel.screenWidth/2 - (gamePanel.tileSize/2);
        screenY = gamePanel.screenHeight/2 -(gamePanel.tileSize/2);
        playerHitbox = new Rectangle(8,16,32, 32); //need to adjust when resizing

        setDefaultValues();
        getPlayerImage();




    }

    public void setDefaultValues() {
        //set speed and starting position of player on map
        worldX = gamePanel.tileSize+(screenX)/2;
        worldY = gamePanel.tileSize+(screenY)/2;
        speed = 4;
        direction = "backwards";
    }

    public void getPlayerImage() {
        try {
            //system.getproperty("user.dir")
            forward1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Character_Moves/Forward_Left.png"));
            forward2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Character_Moves/Forward_Right.png"));
            backwards1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Character_Moves/Backward_Left.png"));
            backwards2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Character_Moves/Backward_Right.png"));
            left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Character_Moves/Left_Left.png"));
            left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Character_Moves/Left_Still.png"));
            right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Character_Moves/Right_Right.png"));
            right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Character_Moves/Right_Still.png"));

        }catch(IOException e){

        }
    }

    public void update() { //gets called 60x per second as in game loop

        if (keyHandler.upPressed == true || keyHandler.downPressed == true || keyHandler.leftPressed == true || keyHandler.rightPressed == true) {

            if (keyHandler.upPressed == true) {
                direction = "forward";

            } else if (keyHandler.leftPressed == true) {
                direction = "left";

            } else if (keyHandler.downPressed == true) {
                direction = "backward";

            } else if (keyHandler.rightPressed == true) {
                direction = "right";

            }


            //Test tile collision
            collisionOn = false;
            gamePanel.collisionCheck.checkTile(this);

            //If Collision is flase, player can move
            if(collisionOn == false) {
                switch(direction){
                    case "forward":
                        worldY -= speed;
                        break;
                    case "backward":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }
            System.out.println(worldY);

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
            graphics2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
//screenX screenY instead of -20, -20
        }


    }

