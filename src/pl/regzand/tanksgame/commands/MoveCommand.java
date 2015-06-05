package pl.regzand.tanksgame.commands;

import pl.regzand.contestserver.Client;
import pl.regzand.contestserver.CommandException;
import pl.regzand.tanksgame.Game;
import pl.regzand.tanksgame.Tank;
import pl.regzand.tanksgame.User;
import pl.regzand.tanksgame.util.Vector2;

public class MoveCommand extends GameCommand{

    @Override
    public void handleGameCommand(Client client, User user, String[] args) throws CommandException {
        checkArguments(args, 2, 2);

        Tank tank = getTank(user, args[0]);

        if(tank.getMoves()<=0)
            throw new CommandException(202);

        switch (args[1].toUpperCase()){
            case "L":
                tank.rotateLeft();
                tank.useMove();
                break;
            case "R":
                tank.rotateRight();
                tank.useMove();
                break;
            case "F":
            case "B":
                Vector2 newPos = tank.getPosition().add(tank.getDirection().getVector().multiply((args[1].equalsIgnoreCase("F") ? 1 : -1)));
                if(!Game.getMap().getField(newPos).drivable)
                    throw new CommandException(201);
                tank.setPosition(newPos);
                tank.useMove();
                break;
            default:
                throw new CommandException(103);
        }

        client.send("OK\n");
    }

}
