import characters.Player;
import handlers.GamePanel;

import javax.swing.*;
import java.awt.*;

public class Runner {

    static GamePanel gamePanel;



    public static void main(String[] args) {
        JFrame window = new JFrame();
//        panel = new JPanel(new GridLayout(2, 2));
//        button1 = new JButton("Button1");
//        button2 = new JButton("Button2");
//        button3 = new JButton("Button3");
//        button4 = new JButton("Button4");

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("NotPokemon");

        gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

//            if (player.inFight == true){
//            //draw new JPanel
//            JPanel jPanel = new JPanel();
////                jPanel.add(new PopupMenu());
//            jPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
//            jPanel.setBackground(Color.white);
//            jPanel.setVisible(true);
//            Button button = new Button("Button");
//            jPanel.add(button);
//            window.add(jPanel, BorderLayout.SOUTH);
//    }
        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}


