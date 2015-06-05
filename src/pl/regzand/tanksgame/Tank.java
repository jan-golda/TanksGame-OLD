package pl.regzand.tanksgame;

import pl.regzand.tanksgame.util.Direction;
import pl.regzand.tanksgame.util.Vector2;

import java.util.ArrayList;
import java.util.List;

public class Tank {

    private static int lastId = 0;

    private final int id;
    private final User owner;
    private final TankType type;
    private Vector2 position;
    private Direction direction;
    private List<Ammunition> ammunition;
    private int moves;
    private int life;

    public Tank(User owner, TankType type, Vector2 position){
        this.id = ++lastId;
        this.owner = owner;
        this.type = type;
        this.position = position;
        this.direction = Direction.NORTH;
        this.ammunition = new ArrayList<Ammunition>();
        this.moves = type.moves;
        this.life = type.life;
    }

    public int getId() {
        return id;
    }

    public User getOwner() {
        return owner;
    }

    public TankType getType() {
        return type;
    }

    public Vector2 getPosition() {
        return position.copy();
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction rotateRight(){
        this.direction = Direction.getByCode(this.direction.code+1);
        return this.direction;
    }

    public Direction rotateLeft(){
        this.direction = Direction.getByCode(this.direction.code-1);
        return this.direction;
    }

    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    public boolean useMove(){
        if(moves<=0)
            return false;
        --moves;
        return true;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int damage(int damage){
        return (this.life -= damage);
    }

    public List<Ammunition> getAmmunition() {
        return ammunition;
    }

    public int getAmmunitionWeight(){
        int out = 0;
        for(Ammunition a : ammunition)
            out += a.getWeight();
        return out;
    }

    public void addAmmunition(Ammunition ammo){
        for(Ammunition a : ammunition){
            if(a.getType()==ammo.getType()){
                a.setAmount(a.getAmount()+ammo.getAmount());
                return;
            }
        }
        ammunition.add(ammo);
    }

    public enum TankType{
        //      id  moves   life    capacity    price
        HEAVY   (1, 1,      500,    1000,       2000),
        NORMAL  (2, 3,      100,    300,        1000),
        LIGHT   (3, 6,      50,     50,         1500);

        public final int id;
        public final int moves;
        public final int life;
        public final int capacity;
        public final int price;

        TankType(int id, int moves, int life, int capacity, int price) {
            this.id = id;
            this.moves = moves;
            this.life = life;
            this.capacity = capacity;
            this.price = price;
        }

        public static TankType getById(int id){
            for(TankType tt : values())
                if(tt.id==id)
                    return tt;
            return null;
        }
    }
}
