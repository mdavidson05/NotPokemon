package pokemon;

import com.sun.org.glassfish.external.statistics.Stats;
import moves.Moves;
import moves.StartingMoves;
import pokemon.PokemonTypes;

import java.util.ArrayList;

public class PokemonCreator extends pokemon.Pokemon{

    private int ID;
    private double height;
    private ArrayList<moves.Moves> moveList;
    private ArrayList baseStats;

    public PokemonCreator(String name, int hp, int level, PokemonTypes pokemonTypes, int xp, boolean awake, int ID, double height, ArrayList<Moves> moveList, ArrayList baseStats) {
        super(name, hp, level, pokemonTypes, xp, awake);
        this.ID = ID;
        this.height = height;
        this.moveList = moveList;
        this.baseStats = baseStats;
    }

    public int getID() {
        return ID;
    }

    public double getHeight() {
        return height;
    }

    public ArrayList<Moves> getMoveList() {
        return moveList;
    }

    public ArrayList<Stats> getBaseStats() {
        return baseStats;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setMoveList(ArrayList<Moves> moveList) {
        this.moveList = moveList;
    }

    public void setBaseStats(ArrayList<Stats> baseStats) {
        this.baseStats = baseStats;
    }

    public void populateStartingMoves(){
        if (this.pokemonTypes == PokemonTypes.FIRE){
            this.moveList = StartingMoves.lookup.get(PokemonTypes.FIRE);
        }
        else if (this.pokemonTypes == PokemonTypes.WATER){
            this.moveList = StartingMoves.lookup.get(PokemonTypes.WATER);

        }
        else if (this.pokemonTypes == PokemonTypes.GRASS) {
            this.moveList = StartingMoves.lookup.get(PokemonTypes.GRASS);

        }


}}
