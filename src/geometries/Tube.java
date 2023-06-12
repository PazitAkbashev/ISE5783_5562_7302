package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Class describe tube, the tube is infinity.
 *
 * @author Pazit and Lea
 */
public class Tube extends RadialGeometry {
    final Ray axisray;

    /**
     * constructor of tube
     *
     * @param radius  the radius of the tube
     * @param axisray ray, the ray contains the direction and the center point of the ray
     */
    public Tube(double radius, Ray axisray) {
        super(radius);
        this.axisray = axisray;
    }

    @Override
    /**
     * Calculates the normal vector at a given point on the object.
     *
     * @param p The point at which to calculate the normal vector.
     * @return The normal vector at the given point.
     */
    public Vector getNormal(Point p) {
        Point p0 = axisray.getP0();           // Starting point of the axis ray
        Vector v = axisray.getDir();          // Direction of the axis ray

        Vector p0_p = p.subtract(p0);         // Vector from the starting point of the axis ray to the given point
        double t = alignZero(p0_p.dotProduct(v));  // Projection of the vector p0_p onto the axis ray

        if (isZero(t)) {
            // If the projection is close to zero, the point is very close to the axis ray
            return p0_p.normalize();         // Return the normalized p0_p vector as the normal vector
        }

        Point o = p0.add(v.scale(t));         // Calculate the point on the axis ray closest to the given point
        Vector o_p = p.subtract(o);           // Vector from the closest point on the axis ray to the given point
        Vector n = o_p.normalize();           // Normalize the o_p vector to obtain the normal vector

        return n;                             // Return the normal vector
    }


    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray,double maxDistance) {
        return null;
    }
}
