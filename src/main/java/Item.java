public class Item {

    private String name;
    private int spriteNumber;
    private int number;
    private boolean isPokeball;

    public Item(String name, int spriteNumber, int number, boolean isPokeball) {
        this.name = name;
        this.spriteNumber = spriteNumber;
        this.number = number;
        this.isPokeball = isPokeball;
    }

    public Item pokeball(){
        Item pokeball = new Item("pokeball", 1, 1, true);
        return pokeball;
    }

}
