package pl.regzand.tanksgame.util;

public class Settings {

    public final int repairPrice;
    public final int wallLife;
    public final int startMoney;
    public final int mapWidth;
    public final int mapHeight;
    public final int turns;
    public final int viewDistance;
    public final int turnTime;

    public Settings() {
        this.repairPrice    = 10;
        this.wallLife       = 100;
        this.startMoney     = 1500;
        this.mapWidth       = Utils.randomInt(20, 50);
        this.mapHeight      = Utils.randomInt(20, 50);
        this.turns          = Utils.randomInt(1000, 10000);
        this.viewDistance   = Utils.randomInt(1, 5);
        this.turnTime       = 1000;
    }
}
