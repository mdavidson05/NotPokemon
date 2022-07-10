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
    public String currentDialogue;
    public int commandNumber = 0;

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

//        Title
        if(gamePanel.gameState == gamePanel.titleState){

            drawTitleScreen();
        }

        if(gamePanel.gameState == gamePanel.playState){

        }
        if(gamePanel.gameState == gamePanel.pauseState){
        drawPauseScreen();
        }
        if(gamePanel.gameState == gamePanel.dialogueState){
            drawDialogueScreen();
        }
}

public void drawProfOakLab(){

}

public void drawTitleScreen(){
        graphics2.setColor(Color.black);
        graphics2.fillRect(0,0, gamePanel.screenWidth, gamePanel.screenHeight);
        graphics2.setFont(graphics2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "NotPokemon";
        int x = getXforCenteredText(text);
        int y = gamePanel.tileSize*3;

        //shadow
    graphics2.setColor(Color.gray);
    graphics2.drawString(text, x+5, y+5);

        graphics2.setColor(Color.white);
        graphics2.drawString(text,x,y);

        //Title image
    x = gamePanel.screenWidth/2 - (gamePanel.tileSize*2)/2;
    y = gamePanel.screenHeight/4;
    graphics2.drawImage(gamePanel.player.backwards1,x,y, gamePanel.tileSize*2, gamePanel.tileSize *2, null);
    //Menu
    text = "NEW GAME";
    graphics2.setFont(graphics2.getFont().deriveFont(Font.BOLD, 48F));
    x = getXforCenteredText(text);
    y += gamePanel.tileSize*4;
    graphics2.drawString(text, x, y);
    if(commandNumber == 0){
        graphics2.drawString(">", x - gamePanel.tileSize, y);
    }

    text = "LOAD GAME";
    graphics2.setFont(graphics2.getFont().deriveFont(Font.BOLD, 48F));
    x = getXforCenteredText(text);
    y += gamePanel.tileSize;
    graphics2.drawString(text, x, y);
    if(commandNumber == 1){
        graphics2.drawString(">", x - gamePanel.tileSize, y);
    }

    text = "QUIT";
    graphics2.setFont(graphics2.getFont().deriveFont(Font.BOLD, 48F));
    x = getXforCenteredText(text);
    y += gamePanel.tileSize;
    graphics2.drawString(text, x, y);
    if(commandNumber == 2){
        graphics2.drawString(">", x - gamePanel.tileSize, y);
    }
}

public void drawDialogueScreen() {
    //Create window
    int x = gamePanel.tileSize * 4;
    int y = gamePanel.tileSize * 4;
    int width = gamePanel.screenWidth - (gamePanel.tileSize * 8);
    int height = gamePanel.screenHeight - (gamePanel.tileSize * 16);
    drawSubWindow(x, y, width, height);

    graphics2.setFont(graphics2.getFont().deriveFont(Font.PLAIN, 30));
    x += gamePanel.tileSize;
    y += gamePanel.tileSize;
    for (String line : currentDialogue.split("\n")) {
        graphics2.drawString(line, x, y);
        y += 40;

    }
}

public void drawSubWindow(int x, int y, int width, int height){
        Color color = new Color(0,0,0,220); //4th param is the opacity. 255 the max
        graphics2.setColor(color);

        graphics2.fillRoundRect(x,y,width,height, 35,35);

        color = new Color(255,255,255);
        graphics2.setColor(color);
        graphics2.setStroke(new BasicStroke(5));
        graphics2.drawRoundRect(x+5,y+5, width -10, height-10, 25,25);
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
