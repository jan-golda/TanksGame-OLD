package pl.regzand.tanksgame;

import pl.regzand.contestserver.Client;
import pl.regzand.tanksgame.commands.WaitCommand;
import pl.regzand.tanksgame.util.Settings;
import pl.regzand.tanksgame.util.Vector2;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private static List<User> users = new ArrayList<User>();
    private static int turn;
    private static int round;
    private static Map map;
    private static int viewDistance;
    private static List<Tank> tanks;
    private static Settings settings;

    public static void newRound(){
        //TODO: save scores

        settings = new Settings();

        map = new Map(settings.mapWidth, settings.mapHeight);
        tanks = new ArrayList<Tank>();
        viewDistance = settings.viewDistance;

        for(User u : users) u.reset();

        System.out.println("-------------------------------------------");

        turn = 0;
        round++;
    }

    public static void newTurn(){
        if(turn+1>getSettings().turns)
            newRound();

        turn++;

        for(Tank t : tanks) t.setMoves(t.getType().moves);

        System.out.println("Round: "+round+"\t Turn: "+turn);

        if(turn==1) WaitCommand.newRound(); else WaitCommand.newTurn();
    }

    public static User getUser(String name){
        for(User u : users)
            if(u.getName().equalsIgnoreCase(name))
                return u;
        return null;
    }

    public static User createUser(String name){
        User u = new User(name);
        users.add(u);
        return u;
    }

    public static int getTurn() {
        return turn;
    }

    public static int getRound() {
        return round;
    }

    public static List<User> getUsers() {
        return users;
    }

    public static Map getMap() {
        return map;
    }

    public static int getViewDistance() {
        return viewDistance;
    }

    public static void spawnTank(User user, Tank.TankType type, Vector2 pos){
        Tank t = new Tank(user, type, pos);
        tanks.add(t);
        user.addTank(t);
    }

    public static void removeTank(Tank t){
        tanks.remove(t);
        t.getOwner().removeTank(t);
    }

    public static Tank getTank(Vector2 v){
        for(Tank t : tanks)
            if(t.getPosition().equals(v))
                return t;
        return null;
    }

    public static Settings getSettings() {
        return settings;
    }
}
