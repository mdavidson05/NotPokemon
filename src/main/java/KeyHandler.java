import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, leftPressed, downPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int input = e.getKeyCode();
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
