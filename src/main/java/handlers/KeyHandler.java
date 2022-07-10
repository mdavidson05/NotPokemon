package handlers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gamePanel;


    public boolean upPressed, leftPressed, downPressed, rightPressed, pausePressed,enterPressed;
    public boolean checkDrawTime = false;

    public KeyHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int input = e.getKeyCode();

        //Title State
        if(gamePanel.gameState == gamePanel.titleState){
            if (input == KeyEvent.VK_W)
            {
                gamePanel.ui.commandNumber--;
                if(gamePanel.ui.commandNumber < 0){
                    gamePanel.ui.commandNumber = 2;
                }
            }

            if (input == KeyEvent.VK_S)
            {
                gamePanel.ui.commandNumber++;
                if (gamePanel.ui.commandNumber > 2){
                    gamePanel.ui.commandNumber = 0;
                }

            }
            if (input == KeyEvent.VK_SPACE)
            {
                if(gamePanel.ui.commandNumber == 0){
                    gamePanel.gameState = gamePanel.playState;
                }
                if(gamePanel.ui.commandNumber == 2){
                    System.exit(0);
                }
            }
        }


        //PlayState
        if (gamePanel.gameState == gamePanel.playState){
        if (input == KeyEvent.VK_W)
        {
            upPressed = true;
        }
        if (input == KeyEvent.VK_A)
        {
            leftPressed = true;
        }
        if (input == KeyEvent.VK_S)
        {
            downPressed = true;
        }
        if (input == KeyEvent.VK_D)
        {
            rightPressed = true;
        }

        if (input == KeyEvent.VK_SPACE)
        {
            enterPressed = true;
        }
        if (input == KeyEvent.VK_T){
            if(checkDrawTime == false){
                checkDrawTime = true;
            }
            else if(checkDrawTime == true){
                checkDrawTime = false;
            }
        }
        if (input == KeyEvent.VK_P)
        {
            if(gamePanel.gameState == gamePanel.playState) {
                gamePanel.gameState = gamePanel.pauseState;
            }
            else if(gamePanel.gameState == gamePanel.pauseState){
                gamePanel.gameState = gamePanel.playState;
            }
            pausePressed = true;
        }
    }
        //Pause State
        else if(gamePanel.gameState == gamePanel.pauseState){
            if (input == KeyEvent.VK_P)
            {
                if(gamePanel.gameState == gamePanel.playState) {
                    gamePanel.gameState = gamePanel.pauseState;
                }
            }
        }
    //Dialogue State
        else if(gamePanel.gameState == gamePanel.dialogueState){
            if(input == KeyEvent.VK_SPACE){
                gamePanel.gameState = gamePanel.playState;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int input = e.getKeyCode();

        if (input == KeyEvent.VK_W)
        {
            upPressed = false;
        }
        if (input == KeyEvent.VK_A)
        {
            leftPressed = false;
        }
        if (input == KeyEvent.VK_S)
        {
            downPressed = false;
        }
        if (input == KeyEvent.VK_D)
        {
            rightPressed = false;
        }

    }
}
