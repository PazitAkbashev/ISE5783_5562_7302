package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Tube extends RadialGeometry{
    final Ray axisray;
    protected Tube(double radius, Ray axisray) {
        super(radius);
        this.axisray = axisray;
    }

    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}
