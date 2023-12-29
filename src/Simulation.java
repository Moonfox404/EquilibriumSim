import java.util.Timer;
import java.util.TimerTask;
import java.util.HashMap;
import java.util.Random;

public class Simulation {
    private static final HashMap<String, Double> collisions;
    private static final HashMap<String, String[]> reactions;
    private static final HashMap<String, Double> weights;
    private final ChemParticle[] system;

    static {
        collisions = new HashMap<>();
        collisions.put("HNO2:H2O", 1.12481E-4);
        collisions.put("HNO2:OH-", 0.9999966);
        collisions.put("NO2-:H2O", 5.555556E-12);
        collisions.put("NO2-:H3O+", 0.985);
        collisions.put("NO2-:HCl", 1.0);
        collisions.put("HCl:H2O", 1.0);
        collisions.put("HCL:OH-", 1.0);
        collisions.put("OH-:H3O+", 1.0);

        reactions = new HashMap<>();
        String[] temp = new String[]{"NO2-", "H3O+"};
        reactions.put("HNO2:H2O", temp);
        temp = new String[]{"NO2-", "H2O"};
        reactions.put("HNO2:OH-", temp);
        temp = new String[]{"OH-", "HNO2"};
        reactions.put("NO2-:H2O", temp);
        temp = new String[]{"HNO2", "H2O"};
        reactions.put("NO2-:H3O+", temp);
        temp = new String[]{"HNO2", "Cl-"};
        reactions.put("NO2-:HCl", temp);
        temp = new String[]{"H3O+", "Cl-"};
        reactions.put("HCl:H2O", temp);
        temp = new String[]{"H2O", "Cl-"};
        reactions.put("HCL:OH-", temp);
        temp = new String[]{"H2O", "H2O"};
        reactions.put("OH-:H3O+", temp);

        weights = new HashMap<>();
        weights.put("HNO2", 47.02);
        weights.put("NO2-", 46.01);
        weights.put("HCL", 36.46);
        weights.put("OH-", 17.01);
        weights.put("H3O+", 19.03);
        weights.put("H2O", 18.02);
    }

    // constructors

    // no default
    public Simulation(ChemParticle[] system){
        this.system = system;
    }

    public Simulation(int size){
        system = new ChemParticle[size];
    }
    // no copy

    // other methods

    public void updateStatus(){
        HashMap<Integer, ChemParticle> points = new HashMap<>();
        for(ChemParticle particle : system){
            // update the coordinates to account for its displacement over time
            particle.update(0.5);

            // implement collision interaction between particles
            Integer coords = Integer.parseInt(((Double) particle.getX()).toString().concat(((Double) particle.getY()).toString()));
            if(points.containsKey(coords)){
                handleCollision(points.get(coords), particle);
                points.remove(coords);
            } else {
                points.put(coords, particle);
            }
        }
        points.clear();

        //testSim(system);
    }

    private static void testSim(ChemParticle[] system){
        System.out.println();
        for(int i = 0; i < system.length; i++){
            System.out.println(i + ":\nx: " + system[i].getX() + "\ty: " + system[i].getY());
        }
    }

    private static void handleCollision(ChemParticle p1, ChemParticle p2){
        String key;
        if(collisions.containsKey(p1.getName() + ":" + p2.getName())){
            key = p1.getName() + ":" + p2.getName();
        } else if (collisions.containsKey(p2.getName() + ":" + p1.getName())){
            key = p2.getName() + ":" + p1.getName();
        } else {
            elasticCollision(p1, p2);
            return;
        }
        if(collisions.get(key) == 1.0){
            reaction(key, p1, p2);
        } else {
            if (Math.random() <= collisions.get(key)){
                reaction(key, p1, p2);
            } else {
                elasticCollision(p1, p2);
            }
        }
    }

    private static void reaction(String key, ChemParticle p1, ChemParticle p2){
        String p1new = reactions.get(key)[0];
        p1.setName(p1new);
        p1.setMass(weights.get(p1new));

        String p2new = reactions.get(key)[1];
        p2.setName(p2new);
        p2.setMass(weights.get(p2new));

        elasticCollision(p1, p2);
    }

    private static void elasticCollision(ChemParticle p1, ChemParticle p2){
        Vector v1wrt2 = Vector.subtract(p1.getVelocity(), p2.getVelocity());
        System.out.println("\nColliding...\np1 initial speed: " + p1.getVelocity().getValue()
                + ", initial angle: " + p1.getVelocity().getAngle()
                + "\np2 initial speed: " + p2.getVelocity().getValue()
                + ", initial angle: " + p2.getVelocity().getAngle());

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

        System.out.println("p1 final speed: " + p1.getVelocity().getValue()
                + ", final angle: " + p1.getVelocity().getAngle()
                + "\np2 final speed: " + p2.getVelocity().getValue()
                + ", final angle: " + p2.getVelocity().getAngle());
    }

    public void runSim(int cycleSpeed){
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
                                      @Override
                                      public void run() {
                                          updateStatus();
                                      }
                                  },
                0, cycleSpeed);
    }

    public void initSim(int numHNO2, int numNaNO2, int numHCl, int numNaOH){
        // all values random

        Random r = new Random();

        int i = 0;

        for(; i < numHNO2; i++){
            system[i] = new ChemParticle(
                    "HNO2",
                    r.nextDouble(-5, 5),
                    r.nextDouble(-5, 5),
                    47.02,
                    r.nextDouble(-1,1),
                    r.nextDouble(0, 2 * Math.PI)
            );
        }

        for(; i < (numNaNO2 + numHNO2); i++){
            system[i] = new ChemParticle(
                    "NO2-",
                    r.nextDouble(-5, 5),
                    r.nextDouble(-5, 5),
                    46.01,
                    r.nextDouble(-1,1),
                    r.nextDouble(0, 2 * Math.PI)
            );
            system[i] = new ChemParticle(
                    "Na+",
                    r.nextDouble(-5, 5),
                    r.nextDouble(-5, 5),
                    22.99,
                    r.nextDouble(-1,1),
                    r.nextDouble(0, 2 * Math.PI)
            );
        }

        for(; i < (numHCl + numNaNO2 + numHNO2); i++){
            system[i] = new ChemParticle(
                    "HCl",
                    r.nextDouble(-5, 5),
                    r.nextDouble(-5, 5),
                    36.46,
                    r.nextDouble(-1,1),
                    r.nextDouble(0, 2 * Math.PI)
            );
        }

        for(; i < (numNaOH + numNaNO2 + numHCl + numHNO2); i++){
            system[i] = new ChemParticle(
                    "OH-",
                    r.nextDouble(-5, 5),
                    r.nextDouble(-5, 5),
                    17.01,
                    r.nextDouble(-1,1),
                    r.nextDouble(0, 2 * Math.PI)
            );
            system[i] = new ChemParticle(
                    "Na+",
                    r.nextDouble(-5, 5),
                    r.nextDouble(-5, 5),
                    22.99,
                    r.nextDouble(-1,1),
                    r.nextDouble(0, 2 * Math.PI)
            );
        }

        this.updateStatus();
    }

    public ChemParticle[] getSystem(){
        return system;
    }
}
