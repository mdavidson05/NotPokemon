public enum PokemonTypes {

    FIRE(1),
    WATER(2),
    GRASS(3),;

    private final int typeID;

    PokemonTypes(int typeID) {
        this.typeID = typeID;
    }

    public int getTypeID() {
        return typeID;
    }


    PokemonTypes[] pokemonTypes = PokemonTypes.values();

}