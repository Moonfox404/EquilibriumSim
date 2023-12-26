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

    public Vector add(Vector other){
        double x_component = (value * Math.cos(angle)) + (other.getValue() * Math.cos(other.getAngle()));
        double y_component = (value * Math.sin(angle)) + (other.getValue() * Math.sin(other.getAngle()));

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

    public Vector subtract(Vector other){
        Vector flip = new Vector(other.getValue(), (other.getAngle() + Math.PI));
        return this.add(flip);
    }
}
