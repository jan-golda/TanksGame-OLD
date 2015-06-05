package pl.regzand.tanksgame;

import pl.regzand.tanksgame.util.Vector2;

import java.util.ArrayList;
import java.util.List;

public class User {

    private final String name;
    private int score;
    private int money;
    private List<Tank> tanks;
    private Vector2 base;

    User(String name){
        this.name = name;
        reset();
    }

    public void reset(){
        money = Game.getSettings().startMoney;
        tanks = new ArrayList<Tank>();
        base = Game.getMap().spawnBase();
        score = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void addMoney(int money) {
        this.money += money;
    }

    public List<Tank> getTanks() {
        return tanks;
    }

    public void clearTanks() {
        this.tanks.clear();
    }

    public void addTank(Tank t){
        this.tanks.add(t);
    }

    public void removeTank(Tank t){
        this.tanks.remove(t);
    }

    public Tank getTank(int id){
        for(Tank t : tanks)
            if(t.getId()==id)
                return t;
        return null;
    }

    public Vector2 getBase() {
        return base;
    }

    public void setBase(Vector2 base) {
        this.base = base;
    }
}
