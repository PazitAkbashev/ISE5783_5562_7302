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
    public Vector getNormal(Point p) {
        // Starting point of the axis ray
        Point p0 = axisRay.getP0();

        // Direction of the axis ray
        Vector v = axisRay.getDir();

        // Vector from the starting point of the axis ray to the given point
        Vector p0_p = p.subtract(p0);

        // Projection of the vector p0_p onto the axis ray
        double t = alignZero(p0_p.dotProduct(v));

        // If the projection is close to zero, the point is very close to the axis ray
        if (isZero(t)) {
            // Return the normalized p0_p vector as the normal vector
            return p0_p.normalize();
        }

        // Calculate the point on the axis ray closest to the given point
        Point o = p0.add(v.scale(t));

        // Vector from the closest point on the axis ray to the given point
        Vector o_p = p.subtract(o);

        // Normalize the o_p vector to obtain the normal vector
        Vector n = o_p.normalize();

        // Return the normal vector
        return n;
    }

    //!!!!!!!!!check comments, and change names to be meaning full!!!!!!!!!!!

    @Override
    //https://hugi.scene.org/online/hugi24/coding%20graphics%20chris%20dragan%20raytracing%20shapes.htm
    //( -this link added by Eliezer)
    //----BONUS----
    //otherwise return null
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        // Direction vector of the cylinder's axis
        Vector vAxis = axisRay.getDir();
        // Direction vector of the ray
        Vector v = ray.getDir();
        // Starting point of the ray
        Point p0 = ray.getP0();

        // At^2+Bt+C=0 - initializing coefficients for quadratic equation
        double a = 0;
        double b = 0;
        double c = 0;

        // Dot product between v and vAxis
        double vVa = alignZero(v.dotProduct(vAxis));
        Vector vVaVa;
        Vector vMinusVVaVa;
        // the ray is orthogonal to the axis
        if (vVa == 0)
            vMinusVVaVa = v;
        else {
            vVaVa = vAxis.scale(vVa);
            try {
                // Subtracting vVaVa from v
                vMinusVVaVa = v.subtract(vVaVa);
            } catch (IllegalArgumentException e1) {
                // the rays is parallel to axis
                return null;  // Rays are parallel to the axis, so no intersection
            }
        }
        // A = (v-(v*va)*va)^2
        a = vMinusVVaVa.lengthSquared();  // Squared length of vMinusVVaVa

        Vector deltaP = null;
        try {
            // Difference vector between ray's starting point and axis's starting point
            deltaP = p0.subtract(axisRay.getP0());
        }
        // the ray begins at axis P0
        catch (IllegalArgumentException e1) {
            // the ray is orthogonal to Axis
            if (vVa == 0 && alignZero(radius - maxDistance) <= 0) {
                // Ray is orthogonal to the axis and within maxDistance from the cylinder, so return the intersection point
                return List.of(new GeoPoint(this, ray.getPoint(radius)));
            }
            double t = alignZero(Math.sqrt(radius * radius / vMinusVVaVa.lengthSquared()));
            // If t is within maxDistance, return the intersection point; otherwise, no intersection
            return alignZero(t - maxDistance) >= 0 ? null : List.of(new GeoPoint(this, ray.getPoint(t)));
        }

        // Dot product between deltaP and vAxis
        double dPVAxis = alignZero(deltaP.dotProduct(vAxis));
        Vector dPVaVa;
        Vector dPMinusdPVaVa;
        if (dPVAxis == 0)
            dPMinusdPVaVa = deltaP;
        else {
            dPVaVa = vAxis.scale(dPVAxis);
            try {
                // Subtracting dPVaVa from deltaP
                dPMinusdPVaVa = deltaP.subtract(dPVaVa);
            } catch (IllegalArgumentException e1) {
                double t = alignZero(Math.sqrt(radius * radius / a));
                // If t is within maxDistance, return the intersection point; otherwise, no intersection
                return alignZero(t - maxDistance) >= 0 ? null : List.of(new GeoPoint(this, ray.getPoint(t)));
            }
        }

        // B = 2(v - (v*va)*va) * (dp - (dp*va)*va))
        // Dot product between vMinusVVaVa and dPMinusdPVaVa
        b = 2 * alignZero(vMinusVVaVa.dotProduct(dPMinusdPVaVa));
        // Squared length of dPMinusdPVaVa minus radius squared
        c = dPMinusdPVaVa.lengthSquared() - radius * radius;

        // A*t^2 + B*t + C = 0 - lets resolve it
        // Discriminant of the quadratic equation
        double discr = alignZero(b * b - 4 * a * c);
        // the ray is outside or tangent to the tube
        if (discr <= 0) return null;

        double doubleA = 2 * a;
        // Value of -b / (2a)
        double tm = alignZero(-b / doubleA);
        // Square root of the discriminant divided by (2a)
        double th = Math.sqrt(discr) / doubleA;
        // the ray is tangent to the tube
        if (isZero(th)) return null;

        // First solution of the quadratic equation
        double t1 = alignZero(tm + th);
        // t1 is behind the head
        if (t1 <= 0)
            // since th must be positive (sqrt), t2 must be non-positive as t1
            return null;

        // Second solution of the quadratic equation
        double t2 = alignZero(tm - th);

        // if both t1 and t2 are positive
        if (t2 > 0 && alignZero(t2 - maxDistance) < 0)
            // Both intersection points are within maxDistance, so return them
            return List.of(new GeoPoint(this, ray.getPoint(t1)), new GeoPoint(this, ray.getPoint(t2)));

        else
            // t2 is behind the head
            if (alignZero(t1 - maxDistance) < 0)
                // Only the first intersection point is within maxDistance, so return it
                return List.of(new GeoPoint(this, ray.getPoint(t1)));

        // No intersection points within maxDistance
        return null;
    }
}
