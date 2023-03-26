package primitives;

public class Vector extends Point {
    public Vector(Double3 point3d) {
        super(point3d);
    }

    public Vector(double d1, double d2, double d3) {
        super(d1, d2, d3);
    }

    public Vector normalize(){
        return new Vector(xyz.reduce(length()));
    }
}
