package pl.regzand.tanksgame.commands;

import pl.regzand.contestserver.Client;
import pl.regzand.contestserver.CommandException;
import pl.regzand.tanksgame.*;
import pl.regzand.tanksgame.util.Vector2;

import java.util.Vector;

public class ShootCommand extends GameCommand{

    @Override
    public void handleGameCommand(Client client, User user, String[] args) throws CommandException {
        checkArguments(args, 2, 2);

        Tank tank = getTank(user, args[0]);

        Ammunition.AmmunitionType type = Ammunition.AmmunitionType.getById(Integer.parseInt(args[1]));
        if(type==null)
            throw new CommandException(211);

        Ammunition ammo = null;
        for(Ammunition a : tank.getAmmunition())
            if(a.getType()==type)
                ammo = a;
        if(ammo == null || ammo.getAmount()<=0)
            throw new CommandException(203);

        ammo.setAmount(ammo.getAmount()-1);

        Vector2 bullet = tank.getPosition();
        Vector2 direction = tank.getDirection().getVector();
        int range = ammo.getType().range;
        int distance = 0;
        while(distance <= range){
            distance++;
            bullet.add(direction);

            Field hitField = Game.getMap().getField(bullet);
            if(hitField==null || hitField.code.equalsIgnoreCase("SOLID"))
                break;
            if(hitField.code.equalsIgnoreCase("WALL")){
                if( ((Field.Wall)hitField).damage(ammo.getType().damageToWalls * (range-distance+1)/range) <= 0)
                    Game.getMap().setField(bullet, new Field.Empty());
                break;
            }

            Tank hitTank = Game.getTank(bullet);
            if(hitTank!=null){
                if( hitTank.damage(ammo.getType().damageToTanks * (range-distance+1)/range) <= 0) {
                    Game.removeTank(hitTank);
                    user.setScore(user.getScore()+1);
                }
                break;
            }
        }

        client.send("OK\n");
    }

}
