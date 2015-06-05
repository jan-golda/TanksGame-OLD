package pl.regzand.tanksgame.util;

public enum Direction {
    NORTH   (0, new Vector2(0,-1)),
    EAST    (1, new Vector2(1,0)),
    SOUTH   (2, new Vector2(0,1)),
    WEST    (3, new Vector2(-1,0));

    public final int code;
    public final Vector2 vector;

    Direction(int code, Vector2 vector) {
        this.code = code;
        this.vector = vector;
    }

    public Vector2 getVector() {
        return vector.copy();
    }

    public static Direction getByCode(int code){
        code = Math.abs(code)%4;
        for(Direction d : values())
            if(d.code == code)
                return d;
        return null;
    }
}
