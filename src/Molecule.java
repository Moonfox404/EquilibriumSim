public class Molecule extends Particle{
    private final String name;

    // constructors
    // no default

    public Molecule(String name, double x, double y, double mass, double speed, double angle){
        super(x, y, mass, speed, angle);
        this.name = name;
    }

    // copy
    public Molecule(Molecule other){
        super(other.getX(), other.getY(), other.getMass(), other.getVelocity().getValue(), other.getVelocity().getAngle());
        name = other.getName();
    }

    // convert
    public Molecule(String name, Particle p){
        super(p);
        this.name = name;
    }

    // get/set
    public String getName(){
        return name;
    }

}
