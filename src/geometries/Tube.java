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
    final Ray axisRay;

    /**
     * constructor of tube
     *
     * @param radius  the radius of the tube
     * @param axisRay ray, the ray contains the direction and the center point of the ray
     */
    public Tube(double radius, Ray axisRay) {
        super(radius);
        this.axisRay = axisRay;
    }

    @Override
    /**
     * Calculates the normal vector at a given point on the object.
     *
     * @param p The point at which to calculate the normal vector.
     * @return The normal vector at the given point.
     */
    public Vector getNormal(Point p) {
        Point p0 = axisRay.getP0();           // Starting point of the axis ray
        Vector v = axisRay.getDir();          // Direction of the axis ray

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
    //https://hugi.scene.org/online/hugi24/coding%20graphics%20chris%20dragan%20raytracing%20shapes.htm
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray,double maxDistance) {

        Vector vAxis = axisRay.getDir();
        Vector v = ray.getDir();
        Point p0 = ray.getP0();

        // At^2+Bt+C=0
        double a = 0;
        double b = 0;
        double c = 0;

        double vVa = alignZero(v.dotProduct(vAxis));
        Vector vVaVa;
        Vector vMinusVVaVa;
        // the ray is orthogonal to the axis
        if (vVa == 0)
            vMinusVVaVa = v;
        else {
            vVaVa = vAxis.scale(vVa);
            try {
                vMinusVVaVa = v.subtract(vVaVa);
            } catch (IllegalArgumentException e1) {
                // the rays is parallel to axis
                return null;
            }
        }
        // A = (v-(v*va)*va)^2
        a = vMinusVVaVa.lengthSquared();

        Vector deltaP = null;
        try {
            deltaP = p0.subtract(axisRay.getP0());
        } catch (IllegalArgumentException e1) { // the ray begins at axis P0
            if (vVa == 0 && alignZero(radius - maxDistance) <= 0) { // the ray is orthogonal to Axis
                return List.of(new GeoPoint(this, ray.getPoint(radius)));
            }
            double t = alignZero(Math.sqrt(radius * radius / vMinusVVaVa.lengthSquared()));
            return alignZero(t - maxDistance) >= 0 ? null : List.of(new GeoPoint(this, ray.getPoint(t)));
        }

        double dPVAxis = alignZero(deltaP.dotProduct(vAxis));
        Vector dPVaVa;
        Vector dPMinusdPVaVa;
        if (dPVAxis == 0)
            dPMinusdPVaVa = deltaP;
        else {
            dPVaVa = vAxis.scale(dPVAxis);
            try {
                dPMinusdPVaVa = deltaP.subtract(dPVaVa);
            } catch (IllegalArgumentException e1) {
                double t = alignZero(Math.sqrt(radius * radius / a));
                return alignZero(t - maxDistance) >= 0 ? null : List.of(new GeoPoint(this, ray.getPoint(t)));
            }
        }

        // B = 2(v - (v*va)*va) * (dp - (dp*va)*va))
        b = 2 * alignZero(vMinusVVaVa.dotProduct(dPMinusdPVaVa));
        c = dPMinusdPVaVa.lengthSquared() - radius * radius;

        // A*t^2 + B*t + C = 0 - lets resolve it
        double discr = alignZero(b * b - 4 * a * c);
        if (discr <= 0) return null; // the ray is outside or tangent to the tube

        double doubleA = 2 * a;
        double tm = alignZero(-b / doubleA);
        double th = Math.sqrt(discr) / doubleA;
        if (isZero(th)) return null; // the ray is tangent to the tube

        double t1 = alignZero(tm + th);
        if (t1 <= 0) // t1 is behind the head
            return null; // since th must be positive (sqrt), t2 must be non-positive as t1

        double t2 = alignZero(tm - th);

        // if both t1 and t2 are positive
        if (t2 > 0 && alignZero(t2 - maxDistance) < 0)
            return List.of(new GeoPoint(this, ray.getPoint(t1)), new GeoPoint(this, ray.getPoint(t2)));
        else if (alignZero(t1 - maxDistance) < 0)// t2 is behind the head
            return List.of(new GeoPoint(this, ray.getPoint(t1)));
        return null;
    }
}
