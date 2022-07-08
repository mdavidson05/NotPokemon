package handlers;

import java.awt.*;
import java.text.DecimalFormat;

public class UI {

    GamePanel gamePanel;
    Graphics2D graphics2;
    Font arial_40, arial_80B;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;

    double playTime;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gamePanel){
        this.gamePanel = gamePanel;

        arial_40 = new Font("Arial",Font.PLAIN,40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D graphics2){

        this.graphics2 = graphics2;
        graphics2.setFont(arial_40);
        graphics2.setColor(Color.white);

        if(gamePanel.gameState == gamePanel.playState){

        }
        if(gamePanel.gameState == gamePanel.pauseState){
        drawPauseScreen();
        }
}

public void drawPauseScreen(){
        graphics2.setFont(graphics2.getFont().deriveFont(Font.PLAIN, 80F));
    String text = "PAUSED";
    int x = getXforCenteredText(text);


    int y = gamePanel.screenHeight/2;

    graphics2.drawString(text, x, y);
}

public int getXforCenteredText(String text){
    int length = (int)graphics2.getFontMetrics().getStringBounds(text, graphics2).getWidth();
    int x = gamePanel.screenWidth/2 - length/2;
    return x;
    }
}
