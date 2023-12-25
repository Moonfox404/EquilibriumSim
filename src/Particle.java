public class Particle {
    private double x;
    private double y;
    private Vector velocity;

    // constructors

    // default
    public Particle(){
        x = 0;
        y = 0;
        velocity = new Vector();
    }

    // value
    public Particle(double x, double y, double speed, double angle){
        this.x = x;
        this.y = y;
        velocity = new Vector(speed, angle);
    }

    //copy
    public Particle(Particle other){
        this.x = other.getX();
        this.y = other.getY();
        velocity = new Vector(other.getVelocity());
    }

    // get/set methods

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public Vector getVelocity(){
        return velocity;
    }

    public void setVelocity(double speed, double angle){
        velocity = new Vector(speed, angle);
    }

    public void update(double time){

    }
}
