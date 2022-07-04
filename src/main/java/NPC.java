import java.util.ArrayList;

public class NPC extends Character{

    private String text;

    public NPC(String name, double money, ArrayList<Item> items, ArrayList<Pokemon> party, String text) {
        super(name, money, items, party);
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
