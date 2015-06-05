package pl.regzand.tanksgame.commands;

import pl.regzand.contestserver.Client;
import pl.regzand.contestserver.CommandException;
import pl.regzand.tanksgame.Ammunition;
import pl.regzand.tanksgame.Game;
import pl.regzand.tanksgame.Tank;
import pl.regzand.tanksgame.User;

public class BuyCommand extends GameCommand{

    @Override
    public void handleGameCommand(Client client, User user, String[] args) throws CommandException {
        checkArguments(args, 3, 3);

        Tank tank = getTank(user, args[0]);

        if(!Game.getMap().getField(tank.getPosition()).code.equalsIgnoreCase("SHOP"))
            throw new CommandException(301);

        Ammunition.AmmunitionType type = Ammunition.AmmunitionType.getById(Integer.parseInt(args[1]));
        if(type==null)
            throw new CommandException(211);

        Ammunition ammo = new Ammunition(type, Integer.parseInt(args[2]));

        if(tank.getAmmunitionWeight() + ammo.getWeight() > tank.getType().capacity)
            throw new CommandException(302);

        pay(user, ammo.getAmount()*ammo.getType().price);
        tank.addAmmunition(ammo);

        client.send("OK\n");
    }

}
