package pl.regzand.tanksgame;

import pl.regzand.contestserver.Client;
import pl.regzand.contestserver.Command;
import pl.regzand.contestserver.CommandException;
import pl.regzand.contestserver.ContestServer;
import pl.regzand.tanksgame.commands.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Server extends ContestServer implements Command{

    public static Server instance;
    public static void main(String[] args){
        if(args.length==0){
            System.err.println("Usage: java TanksGame <port>");
            System.exit(10);
            return;
        }

        instance = new Server(Integer.parseInt(args[0]));
    }

    private Map<Client,User> users = new HashMap<Client,User>();

    public Server(int port){
        super(port);

        registerCommands();
        registerErrors();

        Game.newRound();

        super.start();

        new Timer().start();
    }

    private void registerCommands(){
        cmdHandler.addCommand("LOGIN",      this);
        cmdHandler.addCommand("WAIT",       new WaitCommand());
        cmdHandler.addCommand("INFO",       new InfoCommand());
        cmdHandler.addCommand("GET_TANKS",  new GetTanksCommand());
        cmdHandler.addCommand("GET_TANK",   new GetTankCommand());
        cmdHandler.addCommand("SCAN",       new ScanCommand());
        cmdHandler.addCommand("SPAWN",      new SpawnCommand());
        cmdHandler.addCommand("MOVE",       new MoveCommand());
        cmdHandler.addCommand("SHOOT",      new ShootCommand());
        cmdHandler.addCommand("BUY",        new BuyCommand());
        cmdHandler.addCommand("REPAIR",     new RepairCommand());
    }

    private void registerErrors(){
        cmdHandler.addError(110, "authorization required");
        cmdHandler.addError(200, "you do not have such tank");
        cmdHandler.addError(201, "you can't drive through that field");
        cmdHandler.addError(202, "no moves left");
        cmdHandler.addError(203, "you do not have such ammunition");
        cmdHandler.addError(210, "there is no such tank type");
        cmdHandler.addError(211, "there is no such ammunition type");
        cmdHandler.addError(300, "not enough money");
        cmdHandler.addError(301, "you have to be in shop");
        cmdHandler.addError(302, "not enough space");
    }

    public User getUser(Client client){
        return users.get(client);
    }

    @Override
    public void handle(Client client, String[] args) throws CommandException {
        if(args.length!=1)
            throw new CommandException(103);

        //TODO: password check

        User user = Game.getUser(args[0]);
        if(user==null)
            user = Game.createUser(args[0]);

        users.put(client, user);

        client.send("OK\n");
    }

    @Override
    protected void onClientDisconnect(Client client) {
        users.remove(client);
    }
}
