package pl.regzand.tanksgame.commands;

import pl.regzand.contestserver.Client;
import pl.regzand.contestserver.CommandException;
import pl.regzand.tanksgame.Tank;
import pl.regzand.tanksgame.User;

public class GetTanksCommand extends GameCommand{

    @Override
    public void handleGameCommand(Client client, User user, String[] args) throws CommandException {
        checkArguments(args, 0, 0);

        String out = "OK "+user.getTanks().size();
        for(Tank t : user.getTanks())
            out += " "+t.getId();

        client.send(out+"\n");
    }

}
