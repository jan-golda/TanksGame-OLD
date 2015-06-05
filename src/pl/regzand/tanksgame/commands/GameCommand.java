package pl.regzand.tanksgame.commands;

import pl.regzand.contestserver.Client;
import pl.regzand.contestserver.Command;
import pl.regzand.contestserver.CommandException;
import pl.regzand.tanksgame.Server;
import pl.regzand.tanksgame.Tank;
import pl.regzand.tanksgame.User;

public abstract class GameCommand implements Command {

    @Override
    public void handle(Client client, String[] args) throws CommandException {
        User user = Server.instance.getUser(client);

        if(user==null)
            throw new CommandException(110);

        handleGameCommand(client, user, args);
    }

    public abstract void handleGameCommand(Client client, User user, String[] args) throws CommandException;

    public void checkArguments(String[] args, int min, int max) throws CommandException{
        if(args.length < min || args.length > max)
            throw new CommandException(103);
    }

    public Tank getTank(User user, String id) throws CommandException{
        Tank tank = user.getTank(Integer.parseInt(id));
        if(tank==null || !tank.getOwner().equals(user))
            throw new CommandException(200);
        return tank;
    }

    public void pay(User user, int amount) throws CommandException{
        if(user.getMoney()<amount)
            throw new CommandException(300);
        user.addMoney(-1*amount);
    }
}
