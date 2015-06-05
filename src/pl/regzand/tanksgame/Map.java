package pl.regzand.tanksgame;

import pl.regzand.tanksgame.util.Utils;
import pl.regzand.tanksgame.util.Vector2;

public class Map {

    private final int width;
    private final int height;
    private Field[][] map;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        this.map = new Field[width][height];

        generate();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Field getField(Vector2 v){
        return getField(v.x, v.y);
    }

    public Field getField(int x, int y){
        if(x<0 || x>=width || y<0 || y>=height)
            return null;
        return map[x][y];
    }

    public void setField(Vector2 v, Field field){
        setField(v.x, v.y, field);
    }

    public void setField(int x, int y, Field field){
        map[x][y] = field;
    }

    private void generate(){
        // filling with empty
        for(int x = 0; x<width; x++)
            for(int y = 0; y<height; y++)
                map[x][y] = new Field.Empty();

        // adding borders
        for(int x = 0; x<width; x++){
            map[x][0] = new Field.Solid();
            map[x][height-1] = new Field.Solid();
        }
        for(int y = 0; y<height; y++){
            map[0][y] = new Field.Solid();
            map[width-1][y] = new Field.Solid();
        }

        //TODO: create proper generator
        map[Utils.randomInt(1,width-2)][Utils.randomInt(1, height-2)] = new Field.Shop();
    }

    public Vector2 spawnBase(){
        while(true){
            Vector2 v = new Vector2(Utils.randomInt(1,width-1), Utils.randomInt(1,height-1));
            if(getField(v).code.equalsIgnoreCase("EMPTY"))
                return v;
        }
    }
}
