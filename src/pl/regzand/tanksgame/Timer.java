package pl.regzand.tanksgame;

public class Timer extends Thread{

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(Game.getSettings().turnTime);
                Game.newTurn();
            }catch(Exception e){

            }
        }
    }
}
