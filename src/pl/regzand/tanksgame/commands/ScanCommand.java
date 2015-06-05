package pl.regzand.tanksgame.commands;

import pl.regzand.contestserver.Client;
import pl.regzand.contestserver.CommandException;
import pl.regzand.tanksgame.Field;
import pl.regzand.tanksgame.Game;
import pl.regzand.tanksgame.Tank;
import pl.regzand.tanksgame.User;
import pl.regzand.tanksgame.util.Vector2;

import java.util.ArrayList;
import java.util.List;

public class ScanCommand extends GameCommand{

    @Override
    public void handleGameCommand(Client client, User user, String[] args) throws CommandException {
        checkArguments(args, 1, 1);

        Tank tank = getTank(user, args[0]);

        //TODO: add FOV
        String out = "";
        int count = 0;
        Vector2 min = tank.getPosition().copy().subtract(Game.getViewDistance());
        Vector2 max = tank.getPosition().copy().add(Game.getViewDistance());
        for(int x = min.x; x<=max.x; x++){
            for(int y = min.y; y<=max.y; y++){
                Field field = Game.getMap().getField(x,y);

                if(field==null)
                    continue;

                out += String.format("%d %d %s\n", x, y, (user.getBase().equals(x,y) ? "BASE" : field.code));
                count++;
            }
        }

        client.send("OK "+count+"\n"+out);
    }

}
