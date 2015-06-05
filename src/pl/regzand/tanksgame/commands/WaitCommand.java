package pl.regzand.tanksgame.commands;

import pl.regzand.contestserver.Client;
import pl.regzand.contestserver.CommandException;
import pl.regzand.tanksgame.Game;
import pl.regzand.tanksgame.User;

import java.util.ArrayList;
import java.util.List;

public class WaitCommand extends GameCommand{

    @Override
    public void handleGameCommand(Client client, User user, String[] args) throws CommandException{
        checkArguments(args, 0, 0);

        queue.add(client);

        client.send("OK\n");
    }

    public static List<Client> queue = new ArrayList<Client>();

    public static void newTurn(){
        for(Client c : WaitCommand.queue)
            c.send("OK TURN\n");
        WaitCommand.queue.clear();
    }

    public static void newRound(){
        for(Client c : WaitCommand.queue)
            c.send("OK ROUND\n");
        WaitCommand.queue.clear();
    }

}
