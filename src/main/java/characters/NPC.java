package characters;

import handlers.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class NPC extends Entity {

    private String text;

    public NPC(GamePanel gamePanel, String text) {
        super(gamePanel);
        this.text = text;

        direction = "backwards";
        speed = 4;
    }

    public void getNPCImage() {
        try {
            //system.getproperty("user.dir")
            forward1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("NPCs/ForwardLeft.png"));
            forward2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("NPCs/Forward_Right.png"));
            backwards1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("NPCs/Backward_Left.png"));
            backwards2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("NPCs/Backward_Right.png"));
            left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("NPCs/Left_Left.png"));
            left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("NPCs/Left_Still.png"));
            right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("NPCs/Right_Right.png"));
            right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("NPCs/Right_Still.png"));

        }catch(IOException e){

        }
    }

    public String getText() {
        return text;
    }
}
