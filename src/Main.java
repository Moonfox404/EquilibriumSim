import java.util.Timer;
import java.util.TimerTask;
import java.util.HashMap;

public class Main {

    static void updateStatus(Particle[] system){
        HashMap<String, Particle> points = new HashMap<>();
        for (Particle particle : system) {
            particle.update(0.5);

            String coords = ((Double) particle.getX()).toString().concat(((Double) particle.getY()).toString());
            if(points.containsKey(coords)){
                elasticCollision(particle, points.get(coords));
                points.remove(coords);
            } else {
                points.put(coords, particle);
            }
        }
        points.clear();

        testSim(system);
    }

    static void testSim(Particle[] system){
        System.out.println();
        for(int i = 0; i < system.length; i++){
            System.out.println(i + ":\nx: " + system[i].getX() + "\ty: " + system[i].getY());
        }
    }

    static void elasticCollision(Particle p1, Particle p2){
        Vector v1wrt2 = Vector.subtract(p1.getVelocity(), p2.getVelocity());
        System.out.println("\nColliding... vel1 rel speed: " + v1wrt2.getValue()+ ", rel angle: " + v1wrt2.getAngle());

        double v2New = (2 * v1wrt2.getValue() * p1.getMass()) / (p1.getMass() + p2.getMass());
        double v1New = ((2 * v1wrt2.getValue() * p2.getMass()) / (p1.getMass() + p2.getMass())) - v1wrt2.getValue();

        Vector vel1New = (v1New >= 0) ? new Vector(v1New, v1wrt2.getAngle())
                : new Vector(Math.abs(v1New), (v1wrt2.getAngle() + Math.PI));
        vel1New.add(p2.getVelocity());

        Vector vel2New = (v2New >= 0) ? new Vector(v2New, v1wrt2.getAngle())
                : new Vector(Math.abs(v2New), (v1wrt2.getAngle() + Math.PI));
        vel2New.add(p2.getVelocity());

        p1.setVelocity(vel1New.getValue(), vel1New.getAngle());
        p2.setVelocity(vel2New.getValue(), vel2New.getAngle());
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
        // all values random except mass = 12
        for(int i = 0; i < system.length; i++){
            system[i] = new Particle(
                    Math.random() * 6,
                    Math.random() * 6,
                    12,
                    Math.random(),
                    Math.random() * (2 * Math.PI + 1));

        }
        updateStatus(system);
    }

    public static void main(String[] args) {
        final int size = 2;
        Particle[] system = new Particle[size];

        system[0] = new Particle(2, 0, 1, 1, Math.PI);
        system[1] = new Particle(-2, 0, 1, 1, 0);

        // initSim(system);
        runSim(system);
    }
}