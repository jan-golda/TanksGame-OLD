package pl.regzand.tanksgame.commands;

import pl.regzand.contestserver.Client;
import pl.regzand.contestserver.CommandException;
import pl.regzand.tanksgame.Ammunition;
import pl.regzand.tanksgame.Tank;
import pl.regzand.tanksgame.User;

public class GetTankCommand extends GameCommand{

    @Override
    public void handleGameCommand(Client client, User user, String[] args) throws CommandException {
        checkArguments(args, 1, 1);

        Tank tank = getTank(user, args[0]);

        //TODO: add direction
        String out = String.format(
                "OK %d %d %d %d %d\n",
                tank.getPosition().x,
                tank.getPosition().y,
                tank.getType().id,
                tank.getLife(),
                tank.getAmmunition().size()
        );
        for(Ammunition a : tank.getAmmunition())
            out += a.getType().id+" "+a.getAmount()+"\n";

        client.send(out);
    }

}
