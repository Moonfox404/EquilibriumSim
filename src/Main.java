import java.util.Timer;
import java.util.TimerTask;

public class Main {

    static void updateStatus(){

    }

    static void runSim(){
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
                                      @Override
                                      public void run() {
                                          updateStatus();
                                      }
                                  },
                0, 500);
    }

    public static void main(String[] args) {
        
    }
}