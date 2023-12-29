public class ChemParticle extends Particle{
    private String name;

    // constructors
    // no default

    public ChemParticle(String name, double x, double y, double mass, double speed, double angle){
        super(x, y, mass, speed, angle);
        this.name = name;
    }

    // copy
    public ChemParticle(ChemParticle other){
        super(other.getX(), other.getY(), other.getMass(), other.getVelocity().getValue(), other.getVelocity().getAngle());
        name = other.getName();
    }

    // convert
    public ChemParticle(String name, Particle p){
        super(p);
        this.name = name;
    }

    // get/set
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

}
