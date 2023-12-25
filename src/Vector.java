public class Vector {
    private double value;
    private double angle;
    // in radians

    // constructors

    // default
    public Vector(){
        value = 0.0;
        angle = 0.0;
    }

    // value
    public Vector(double value, double angle){
        this.value = value;
        this.angle = angle;
    }

    // copy
    public Vector(Vector other){
        this.value = other.getValue();
        this.angle = other.getAngle();
    }

    // get/set methods
    public double getValue(){
        return value;
    }

    public double getAngle(){
        return angle;
    }

    public void setValue(double val){
        value = val;
    }

    public void setAngle(double val){
        angle = val;
    }
}
