import java.util.Timer;
import java.util.TimerTask;

public class Main {

    static void updateStatus(Particle[] system){
        for (Particle particle : system) {
            particle.update(0.5);
        }
    }

    static void runSim(Particle[] system){
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
                                      @Override
                                      public void run() {
                                          updateStatus(system);
                                      }
                                  },
                0, 500);
    }

    static void initSim(Particle[] system){
        for(int i = 0; i < system.length; i++){
            system[i] = new Particle(
                    Math.random() * 6,
                    Math.random() * 6,
                    Math.random(),
                    Math.random() * (2 * Math.PI + 1));

        }
    }

    public static void main(String[] args) {
        final int size = 2;
        Particle[] system = new Particle[size];

        initSim(system);
        runSim(system);
    }
}