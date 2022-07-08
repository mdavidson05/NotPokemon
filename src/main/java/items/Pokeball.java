package items;

import handlers.GamePanel;
import handlers.Scaling;

import javax.imageio.ImageIO;
import java.io.IOException;


public class Pokeball extends SuperItem{
    GamePanel gamePanel;
    {
        name = "pokeball";
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Items/Pokeball.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
        collision = true;

    }
}


