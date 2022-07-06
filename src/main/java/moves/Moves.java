package moves;

public class Moves {

    private int accuracy;
    private int basePower;
    private String category;
    private int pp;
    private int priority;
    private String type;
    private String name;

    public Moves(String name, int accuracy, int basePower, String category, int pp, int priority, String type) {
        this.name = name;
        this.accuracy = accuracy;
        this.basePower = basePower;
        this.category = category;
        this.pp = pp;
        this.priority = priority;
        this.type = type;
    }


}



