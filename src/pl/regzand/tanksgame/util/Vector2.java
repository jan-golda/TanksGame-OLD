package pl.regzand.tanksgame.util;

public class Vector2 {

    public int x;
    public int y;

    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2 add(Vector2 v){
        return add(v.x, v.y);
    }

    public Vector2 add(int a){
        return add(a,a);
    }

    public Vector2 add(int x, int y){
        this.x += x;
        this.y += y;
        return this;
    }

    public Vector2 subtract(Vector2 v){
        return subtract(v.x, v.y);
    }

    public Vector2 subtract(int a){
        return subtract(a, a);
    }

    public Vector2 subtract(int x, int y){
        this.x -= x;
        this.y -= y;
        return this;
    }

    public Vector2 multiply(int a){
        this.x *= a;
        this.y *= a;
        return this;
    }

    public Vector2 copy(){
        return new Vector2(x,y);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Vector2))
            return false;
        return equals((Vector2)obj);
    }

    public boolean equals(Vector2 v){
        return equals(v.x, v.y);
    }

    public boolean equals(int x, int y){
        return this.x==x && this.y==y;
    }
}
