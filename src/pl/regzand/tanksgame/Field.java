package pl.regzand.tanksgame;

import pl.regzand.tanksgame.util.Settings;

public abstract class Field {
    
    public final String code;
    public final boolean drivable;

    public Field(String code, boolean drivable) {
        this.code = code;
        this.drivable = drivable;
    }

    @Override
    public String toString() {
        return code;
    }
    
    public static class Empty extends Field{
        public Empty() {
            super("EMPTY", true);
        }
    }
    
    public static class Water extends Field{
        public Water() {
            super("WATER", false);
        }
    }
    
    public static class Wall extends Field{

        private int life = Game.getSettings().wallLife;

        public Wall() {
            super("WALL", false);
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
    }
    
    public static class Solid extends Field{
        public Solid() {
            super("SOLID", false);
        }
    }
    
    public static class Crate extends Field{
        public Crate() {
            super("CRATE", true);
        }
    }
    
    public static class Shop extends Field{
        public Shop() {
            super("SHOP", true);
        }
    }
}
