package pl.regzand.tanksgame.commands;

import pl.regzand.contestserver.Client;
import pl.regzand.contestserver.CommandException;
import pl.regzand.tanksgame.Game;
import pl.regzand.tanksgame.Tank;
import pl.regzand.tanksgame.Tank.TankType;
import pl.regzand.tanksgame.User;

public class SpawnCommand extends GameCommand{

    @Override
    public void handleGameCommand(Client client, User user, String[] args) throws CommandException {
        checkArguments(args, 1, 1);

        TankType type = TankType.getById(Integer.parseInt(args[0]));

        if(type==null)
            throw new CommandException(210);

        pay(user, type.price);
        Game.spawnTank(user, type, user.getBase());

        client.send("OK\n");
    }

}
