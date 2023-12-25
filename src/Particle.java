public class Particle {
    private double x;
    private double y;
    private double mass;
    private Vector velocity;

    // constructors

    // default
    public Particle(){
        x = 0;
        y = 0;
        mass = 0;
        velocity = new Vector();
    }

    // value
    public Particle(double x, double y, double mass, double speed, double angle){
        this.x = x;
        this.y = y;
        this.mass = mass;
        velocity = new Vector(speed, angle);
    }

    //copy
    public Particle(Particle other){
        this.x = other.getX();
        this.y = other.getY();
        this.mass = other.getMass();
        velocity = new Vector(other.getVelocity());
    }

    // get/set methods

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public double getMass(){
        return mass;
    }

    public Vector getVelocity(){
        return velocity;
    }

    public void setVelocity(double speed, double angle){
        velocity = new Vector(speed, angle);
    }

    public void update(double time){
        double xSpeed = velocity.getValue() * Math.cos(velocity.getAngle());
        double ySpeed = velocity.getValue() * Math.sin(velocity.getAngle());

        x += (xSpeed * time);
        y += (ySpeed * time);
    }

    public double kinEnergy(){
        return (mass * Math.pow(velocity.getValue(), 2)) / 2;
    }
}
