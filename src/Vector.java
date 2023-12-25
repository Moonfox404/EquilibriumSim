public class Vector {
    private double value;
    private double theta;

    // constructors

    // default
    public Vector(){
        value = 0.0;
        theta = 0.0;
    }

    // value
    public Vector(double value, double angle){
        this.value = value;
        theta = angle;
    }

    // copy
    public Vector(Vector other){
        this.value = other.getValue();
        this.theta = other.getTheta();
    }

    // get/set methods
    public double getValue(){
        return value;
    }

    public double getTheta(){
        return theta;
    }

    public void setValue(double val){
        value = val;
    }

    public void setTheta(double val){
        theta = val;
    }
}
