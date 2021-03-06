package pokemon;

public enum PokemonConstructor {

    CHARMANDER(1, 2, 15),
    CHARMELION(2, 3, 40),
    CHARIZARD(3, 200, 101),
    SQUIRTLE(4, 5, 15),
    WHARTORTLE(5, 6, 40),
    BLASTOISE(6, 201, 101),
    BULBASAUR(7, 8, 15),
    IVYSAUR(8, 9, 40),
    VENOSAUR(9, 202, 101),;


    private final int pokeID;
    //enum class
    private final int evolvesIntoID;
    private final int evolvesAtLevel;

    PokemonConstructor(int pokeID, int evolvesIntoID, int evolvesAtLevel) {
        this.pokeID = pokeID;
        this.evolvesIntoID = evolvesIntoID;

        this.evolvesAtLevel = evolvesAtLevel;
    }

    public int getPokeID() {
        return pokeID;
    }

    public int getEvolvesIntoID() {
        return evolvesIntoID;
    }

    public int getEvolvesAtLevel() {
        return evolvesAtLevel;
    }

//    public Handlers.PokemonTypes[] getPokemonTypes() {
//        return pokemonTypes;
//    }

//    PokemonConstructor[] pokemon = PokemonConstructor.values();

}