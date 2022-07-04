//import java.util.ArrayList;
//
//public abstract class Trainer extends Character{
//
//    ArrayList<Pokemon> pokedex; //pokedex might need to be another class
//    protected ArrayList<Pokemon> party = new ArrayList<>();
//    private Pokemon stagedPokemon = null;
////    protected  BattleSlot ownedSlot; will probably need a slot for the current pokemon on field
////    protected BattleSlot enemySlot;
//
//    public Trainer(String name, double money, ArrayList<Item> items, ArrayList<Pokemon> party, ArrayList<Pokemon> pokedex, ArrayList<Pokemon> party1, Pokemon stagedPokemon, BattleSlot ownedSlot, BattleSlot enemySlot) {
//        super(name, money, items, party);
//        this.pokedex = pokedex;
//        this.party = party1;
//        this.stagedPokemon = stagedPokemon;
////        this.ownedSlot = ownedSlot;
////        this.enemySlot = enemySlot;
//    }
//
//    //add pokemons to trainer party
//    public Trainer(String name, Pokemon[] pokemons) {
//            name = trainerName;
//            for (Pokemon pokemon:pokemons) {
//                party.add(pokemon);
//            }
//        }
//        public  Pokemon getStagedPokemon(){
//            return  stagedPokemon;
//        }
//
//        public boolean canFight() {
//            for (Pokemon pokemon:party) {
//                if(!pokemon.hasFainted())
//                    return true;
//            }
//            return false;
//        }
//
//        public boolean canSwap(){
//            for (Pokemon pokemon:party) {
//                if(!pokemon.hasFainted() && pokemon != getStagedPokemon())
//                    return  true;
//                else{
//                    System.out.println(pokemon.name + " is fainted and can't battle now");
//                }
//            }
//            return  false;
//        }
//        public void swapPokemon(){
//            System.out.println(getStagedPokemon().name + " was recalled");
//            Pokemon pokemonToSwapWith = sendOutFirstAvailablePokemon();
//            swapPokemon(pokemonToSwapWith);
//        }
//
//        public void swapPokemon(Pokemon pokemonToSwapWith ){
//            if(pokemonToSwapWith == null)
//                System.out.println("swap failed");
//            else{
//                stagedPokemon = pokemonToSwapWith;//no need to add the previous staged pokemon to party again
////                ownedSlot.setPokemon(stagedPokemon);
//            }
//        }
//
//        public Pokemon sendOutFirstAvailablePokemon(){//get first not dead pokemon that's not already sent out or return null,
//            for (Pokemon pokemon :party) {
//                if(!pokemon.hasFainted() && stagedPokemon != pokemon) {
//                    return pokemon;
//                }
//            }
//            return  null;
//        }
//
//        public Pokemon stageFirstAvailablePokemon(){// used for getting pokemon to send out first in battle//also stages the mon
//            Pokemon p = sendOutFirstAvailablePokemon();
//            stagedPokemon = p;
//            System.out.println(name+"sending poke "+ p.name);
//            return stagedPokemon;
//        }
//
////        public void prepareForBattle(BattleSlot ownedSlot,BattleSlot enemySlot){
////            this.ownedSlot = ownedSlot;
////            this.enemySlot = enemySlot;
////
////            ownedSlot.setPokemon(stageFirstAvailablePokemon());
////        }
//
//        public void endBattle(){
//            stagedPokemon = null;
////            ownedSlot = null;
////            enemySlot = null;
//        }
//
//
////        public abstract ArrayList<Attack> getCommands();
////        public abstract Boolean moveHasFinished();
////        public abstract void prepTurn();
//    }