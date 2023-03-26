package primitives;

public class Ray extends Vector{
    private Point p3;
    private Vector vec;

    public Ray(Double3 point3d, Point p3, Vector vec) {
        super(point3d);
        this.p3 = p3;
        this.vec = vec.normalize();
    }

    public Point getP3() {
        return p3;
    }

    public Vector getVec() {
        return vec;
    }

}
