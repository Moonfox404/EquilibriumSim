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

    // other methods

    public static Vector add(Vector v1, Vector v2){
        double x_component = (v1.getValue() * Math.cos(v1.getAngle())) + (v2.getValue() * Math.cos(v2.getAngle()));
        double y_component = (v1.getValue() * Math.sin(v1.getAngle())) + (v2.getValue() * Math.sin(v2.getAngle()));

        double res_magnitude = Math.sqrt(Math.pow(x_component, 2) + Math.pow(y_component, 2));
        double res_angle = Math.atan(Math.abs(x_component) / Math.abs(y_component));

        if(x_component < 0){
            if(y_component >= 0){
                res_angle = Math.PI - res_angle;
            } else {
                res_angle = Math.PI + res_angle;
            }
        } else if(y_component < 0){
            res_angle = (2 * Math.PI) - res_angle;
        }

        Vector sum = new Vector(res_magnitude, res_angle);
        return sum;
    }

    public void add(Vector other){
        Vector sum = add(this, other);
        value = sum.getValue();
        angle = sum.getAngle();
    }

    public static Vector subtract(Vector v1, Vector v2){
        Vector flip = new Vector(v2.getValue(), (v2.getAngle() + Math.PI));
        return add(v1, flip);
    }

    public void subtract(Vector other){
        Vector flip = new Vector(other.getValue(), (other.getAngle() + Math.PI));
        this.add(flip);
    }
}
