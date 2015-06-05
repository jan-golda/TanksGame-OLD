package pl.regzand.tanksgame;

public class Ammunition {

    private final AmmunitionType type;
    private int amount;

    public Ammunition(AmmunitionType type, int amount) {
        this.type = type;
        this.amount = amount;
    }

    public AmmunitionType getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void addAmount(int amount) {
        this.amount += amount;
    }

    public int getWeight(){
        return type.weight*amount;
    }

    public enum AmmunitionType{
        //          id   dmgTank dmgWall range   weight  price
        EXPLOSIVE   (1,   5,     100,    10,     2,      10),
        NORMAL      (2,   25,    25,     15,     1,      5),
        ANTIPANC    (3,   100,   10,     30,     2,      15),
        LONGRANGE   (4,   30,    30,     100,    3,      20);

        public final int id;
        public final int damageToTanks;
        public final int damageToWalls;
        public final int range;
        public final int weight;
        public final int price;

        AmmunitionType(int id, int damageToTanks, int damageToWalls, int range, int weight, int price) {
            this.id = id;
            this.damageToTanks = damageToTanks;
            this.damageToWalls = damageToWalls;
            this.range = range;
            this.weight = weight;
            this.price = price;
        }

        public static AmmunitionType getById(int id){
            for(AmmunitionType at : values())
                if(at.id==id)
                    return at;
            return null;
        }
    }
}
