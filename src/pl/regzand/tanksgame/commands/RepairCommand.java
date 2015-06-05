package pl.regzand.tanksgame.commands;

import pl.regzand.contestserver.Client;
import pl.regzand.contestserver.CommandException;
import pl.regzand.tanksgame.Game;
import pl.regzand.tanksgame.Tank;
import pl.regzand.tanksgame.User;
import pl.regzand.tanksgame.util.Settings;

public class RepairCommand extends GameCommand{

    @Override
    public void handleGameCommand(Client client, User user, String[] args) throws CommandException {
        checkArguments(args, 1, 1);

        Tank tank = getTank(user, args[0]);

        if(!Game.getMap().getField(tank.getPosition()).code.equalsIgnoreCase("SHOP"))
            throw new CommandException(301);

        pay(user, (tank.getType().life-tank.getLife())* Game.getSettings().repairPrice);
        tank.setLife(tank.getType().life);

        client.send("OK\n");
    }

}
