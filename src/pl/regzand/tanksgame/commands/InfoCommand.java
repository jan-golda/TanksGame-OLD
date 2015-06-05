package pl.regzand.tanksgame.commands;

import pl.regzand.contestserver.Client;
import pl.regzand.contestserver.CommandException;
import pl.regzand.tanksgame.Game;
import pl.regzand.tanksgame.User;

public class InfoCommand extends GameCommand{

    @Override
    public void handleGameCommand(Client client, User user, String[] args) throws CommandException {
        checkArguments(args, 0 ,0);

        client.send(String.format(
                "OK %d %d %d %d\n",
                Game.getMap().getWidth(),
                Game.getMap().getHeight(),
                Game.getViewDistance(),
                user.getMoney()
        ));
    }

}
