public class Moves {

    private int accuracy;
    private int basePower;
    private String category;
    private int pp;
    private int priority;
    private String type;
    Moves scratch;

    public Moves(int accuracy, int basePower, String category, int pp, int priority, String type) {
        this.accuracy = accuracy;
        this.basePower = basePower;
        this.category = category;
        this.pp = pp;
        this.priority = priority;
        this.type = type;
    }

    public Moves scratch(){
        scratch = new Moves(100, 40, "Physical", 35, 0, "Normal");
        return scratch;

    }
}



