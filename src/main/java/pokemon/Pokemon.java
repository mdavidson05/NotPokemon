package pokemon;

public abstract class Pokemon {

    private String name;
    private int hp;
    private int level;
    PokemonTypes pokemonTypes;
    private int xp;
    private boolean awake;

    public Pokemon(String name, int hp, int level, PokemonTypes pokemonTypes, int xp, boolean awake) {
        this.name = name;
        this.hp = hp;
        this.level = level;
        this.pokemonTypes = pokemonTypes;
        this.xp = xp;
        this.awake = awake;
    }


    public boolean hasFainted(){
        if (hp <= 0) {
            this.awake = false;
        }
        return this.awake;
    }
}