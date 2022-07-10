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

        if (gamePanel.gameState == gamePanel.titleState) {
            titleState(input);
        }
        else if (gamePanel.gameState == gamePanel.playState) {
            playState(input);
        }
        else if (gamePanel.gameState == gamePanel.pauseState) {
            pauseState(input);
        }
        else if (gamePanel.gameState == gamePanel.dialogueState) {
            dialogueState(input);
        }
        else if (gamePanel.gameState == gamePanel.characterState) {
            characterState(input);
        }
    }

    public void titleState(int input){
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

    public void playState(int input){
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
//                pausePressed = true;
            }

            if (input == KeyEvent.VK_C)
            {
                gamePanel.gameState = gamePanel.characterState;
            }
        }


    public void pauseState(int input){

        gamePanel.gameState = gamePanel.pauseState;
                }




    public void dialogueState(int input) {
        if (input == KeyEvent.VK_SPACE) {
            gamePanel.gameState = gamePanel.playState;
        }
    }


    public void characterState(int input){
        if(input == KeyEvent.VK_C){
                gamePanel.gameState = gamePanel.playState;
            }
        if(input == KeyEvent.VK_W) {
            if (gamePanel.ui.slotRow != 0) {
                gamePanel.ui.slotRow--;
            }
        }
        if(input == KeyEvent.VK_A) {
            if (gamePanel.ui.slotCoL != 0) {
                gamePanel.ui.slotCoL--;
            }
        }

            if (input == KeyEvent.VK_S) {

                if (gamePanel.ui.slotRow != 3) {
                    gamePanel.ui.slotRow++;
                }
            }

            if (input == KeyEvent.VK_D) {
                if (gamePanel.ui.slotCoL != 3) {
                    gamePanel.ui.slotCoL++;
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
