package Battle;

import characters.Entity;
import characters.NPC_Boy;
import characters.Player;
import handlers.GamePanel;
import moves.Moves;
import pokemon.Pokemon;
import pokemon.PokemonCreator;


import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
//available pokemon moves
//super(name, hp, level, pokemonTypes, xp, awake);
//        this.ID = ID;
//        this.height = height;
//        this.moveList = moveList;
//        this.baseStats = baseStats;


public class Battle {
    public int playerTurn = 1;
    private Entity player;
    private Entity npc;
    private Graphics2D graphics2;
    private GamePanel gamePanel;
    public int commandNumber = 0;
    public Battle(Entity player, Entity npc, GamePanel gamePanel) {
        this.player = player;
        this.npc = npc;
        this.gamePanel = gamePanel;

    }

    public void attack(int index) {
        PokemonCreator playerPokemon = player.battleSlot.get(0);
        PokemonCreator npcPokemon = npc.battleSlot.get(0);


        if (playerTurn == 1) {
            Moves attack = playerPokemon.getMoveList().get(index);
            int move = attack.getBasePower();
            npcPokemon.setHp(npcPokemon.hp - move);
            npcPokemon.checkHasFainted();
            player.checkHasWon(npc.party);
            changePlayerTurn(playerTurn);
        }
        if (playerTurn == 2) {
            ArrayList<Moves> attack = npcPokemon.getMoveList();
            int moveIndex = randomAttack(npcPokemon, 0, 3);
            Moves move = attack.get(moveIndex);
            int moveDamage = move.getBasePower();
            playerPokemon.setHp(playerPokemon.hp - moveDamage);
            playerPokemon.checkHasFainted();
            npc.checkHasWon(player.party);
            changePlayerTurn(playerTurn);
        }
    }

    public int randomAttack(PokemonCreator pokemon, int min, int max){
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;

    }

    public void setPlayerTurn(int player){
        playerTurn = player;

    }

//
    public void changePlayerTurn(int playerTurn){
        if(playerTurn ==1){
            setPlayerTurn(2);
        }
        else if(playerTurn ==2){
            setPlayerTurn(1);
        }
    }
//    public void applyItem(){
//        selected = selectPokemon()
//                item = selectItem()
//                item.effect*pokemon.hp
//
//    }
//
    public void switchPokemon(Entity player, Entity npc){
        if (playerTurn == 1) {
            player.battleSlot.remove(0);
//            PokemonCreator selectedPokemon = player.selectPokemon();
//            player.battleSlot.add(selectedPokemon);
            changePlayerTurn(playerTurn);
//            update();

        }
        if (playerTurn == 2) {
            npc.battleSlot.remove(0);
//            PokemonCreator selectedPokemon = npc.selectPokemon();
//            player.battleSlot.add(selectedPokemon);
            changePlayerTurn(playerTurn);
//            update();
        }
    }
//
//    public void run(){
//        graphics.dispose();
//    }

    public void drawFightScreen(Graphics2D graphics2){
        graphics2.setColor(Color.black);
        graphics2.fillRect(0,0, gamePanel.screenWidth, gamePanel.screenHeight);
        graphics2.setFont(graphics2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "Fight";
        int x = getXforCenterText(text, gamePanel, graphics2);
        int y = gamePanel.tileSize*3;

        //shadow
        graphics2.setColor(Color.gray);
        graphics2.drawString(text, x+5, y+5);

        graphics2.setColor(Color.white);
        graphics2.drawString(text,x,y);

        //Fight Screen
        x = gamePanel.screenWidth/2 - (gamePanel.tileSize*2)/2;
        y = gamePanel.screenHeight/4;
        graphics2.drawImage(gamePanel.player.backwards1,x,y, gamePanel.tileSize*2, gamePanel.tileSize *2, null);
//        graphics2.drawString((player.hp.toString()));

        //Menu
        text = "Fight";
        graphics2.setFont(graphics2.getFont().deriveFont(Font.BOLD, 48F));
        x = getXforCenterText(text, gamePanel, graphics2);
        y += gamePanel.tileSize*4;
        graphics2.drawString(text, x, y);
//        graphics2.drawString(">", x - gamePanel.tileSize, y);

        if(commandNumber == 0){
            graphics2.drawString(">", x - gamePanel.tileSize, y);
        }

        text = "USE ITEM";
        graphics2.setFont(graphics2.getFont().deriveFont(Font.BOLD, 48F));
        x = getXforCenterText(text, gamePanel, graphics2);
        y += gamePanel.tileSize;
        graphics2.drawString(text, x, y);
        if(commandNumber == 1){
            graphics2.drawString(">", x - gamePanel.tileSize, y);
        }

        text = "SWAP";
        graphics2.setFont(graphics2.getFont().deriveFont(Font.BOLD, 48F));
        x = getXforCenterText(text, gamePanel, graphics2);
        y += gamePanel.tileSize;
        graphics2.drawString(text, x, y);
        if(commandNumber == 2){
            graphics2.drawString(">", x - gamePanel.tileSize, y);
        }

        text = "RUN";
        graphics2.setFont(graphics2.getFont().deriveFont(Font.BOLD, 48F));
        x = getXforCenterText(text, gamePanel, graphics2);
        y += gamePanel.tileSize;
        graphics2.drawString(text, x, y);
        if(commandNumber == 3){
            graphics2.drawString(">", x - gamePanel.tileSize, y);
        }
    }

    public int getXforCenterText(String text, GamePanel gamePanel, Graphics2D graphics2){
        int length = (int)graphics2.getFontMetrics().getStringBounds(text, graphics2).getWidth();
        int x = gamePanel.screenWidth/2 - length/2;
        return x;
    }

    public void drawAttackScreen(Graphics2D graphics2) {
        graphics2.setColor(Color.black);
        graphics2.fillRect(0,0, gamePanel.screenWidth, gamePanel.screenHeight);
        graphics2.setFont(graphics2.getFont().deriveFont(Font.BOLD, 96F));
        String title = "Fight";
        int x = getXforCenterText(title, gamePanel, graphics2);
        int y = gamePanel.tileSize*3;

        //shadow
        graphics2.setColor(Color.gray);
        graphics2.drawString(title, x+5, y+5);

        graphics2.setColor(Color.white);
        graphics2.drawString(title,x,y);

        //Fight Screen
        x = gamePanel.screenWidth/2 - (gamePanel.tileSize*2)/2;
        y = gamePanel.screenHeight/4;
        graphics2.drawImage(gamePanel.player.backwards1,x,y, gamePanel.tileSize*2, gamePanel.tileSize *2, null);
//        graphics2.drawString((player.hp.toString()));

        //Menu
        ArrayList<Moves> pokemonMoves = player.battleSlot.get(0).getMoveList();
        Moves moves = pokemonMoves.get(0);
        String move = moves.getName();
        graphics2.setFont(graphics2.getFont().deriveFont(Font.BOLD, 48F));
        x = getXforCenterText(move, gamePanel, graphics2);
        y += gamePanel.tileSize*4;
        graphics2.drawString(move, x, y);
//        graphics2.drawString(">", x - gamePanel.tileSize, y);

        if(commandNumber == 0){
            graphics2.drawString(">", x - gamePanel.tileSize, y);
        }
        Moves moves1 = pokemonMoves.get(1);
        String move1 = moves1.getName();
        graphics2.setFont(graphics2.getFont().deriveFont(Font.BOLD, 48F));
        x = getXforCenterText(move1, gamePanel, graphics2);
        y += gamePanel.tileSize;
        graphics2.drawString(move1, x, y);
        if(commandNumber == 1){
            graphics2.drawString(">", x - gamePanel.tileSize, y);
        }

        Moves moves2 = pokemonMoves.get(2);
        String move2 = moves2.getName();
        graphics2.setFont(graphics2.getFont().deriveFont(Font.BOLD, 48F));
        x = getXforCenterText(move2, gamePanel, graphics2);
        y += gamePanel.tileSize;
        graphics2.drawString(move2, x, y);
        if(commandNumber == 2){
            graphics2.drawString(">", x - gamePanel.tileSize, y);
        }

        Moves moves3 = pokemonMoves.get(3);
        String move3 = moves3.getName();
        graphics2.setFont(graphics2.getFont().deriveFont(Font.BOLD, 48F));
        x = getXforCenterText(move3, gamePanel, graphics2);
        y += gamePanel.tileSize;
        graphics2.drawString(move3, x, y);
        if(commandNumber == 3){
            graphics2.drawString(">", x - gamePanel.tileSize, y);
        }

    }
}