package characters;

import handlers.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Random;

public class NPC_Boy extends Entity {

    private String text;

    public NPC_Boy(GamePanel gamePanel) {
        super(gamePanel);
        direction = "backward";
        speed = 1;


        getNPCImage();
    }

    public void getNPCImage() {

            //system.getproperty("user.dir")
            forward1 = setup("NPCs/ForwardLeft");
            forward2 = setup("NPCs/ForwardRight");
            backwards1 = setup("NPCs/BackwardLeft");
            backwards2 = setup("NPCs/BackwardRight");
            left1 = setup("NPCs/LeftLeft");
            left2 = setup("NPCs/LeftStill");
            right1 = setup("NPCs/RightRight");
            right2 = setup("NPCs/RightStill");
        forwardStill = setup("NPCs/ForwardStill");
    }

    public String getText() {
        return text;
    }

    public void setAction() {

        actionCounter++;

        if (actionCounter == 120) {


            Random random = new Random();
            int randomNumber = random.nextInt(100) + 1; // pick random number
            if (randomNumber <= 25) {
                direction = "forward";
            }
            if (randomNumber > 25 && randomNumber <= 50) {
                direction = "backward";
            }
            if (randomNumber > 50 && randomNumber <= 75) {
                direction = "left";
            }
            if (randomNumber > 75 && randomNumber <= 100) {
                direction = "right";
            }

            actionCounter = 0;
        }
    }
}
