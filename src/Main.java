public class Main {
    public static void main(String[] args) {
        final int size = 2;
        Particle[] system = new Particle[size];

        system[0] = new Particle(2, 0, 1, 1, Math.PI);
        system[1] = new Particle(-2, 0, 1, 1, 0);

        Simulation test = new Simulation(system);

        // initSim(system);
        test.runSim(500);
    }
}