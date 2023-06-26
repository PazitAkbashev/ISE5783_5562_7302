package geometries;

import primitives.Ray;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * A class that represents a list of intersectable items.
 *
 * @author pazit and Lea - 26.06.23
 */
public class Geometries extends Intersectable {

    // List of intersectable items
    private final List<Intersectable> intersectables;

    /**
     * Default constructor for Geometries
     */
    public Geometries() {
        // use LinkedList because the use of this list is only for iterate  from the start
        // of the list to the end, and adding new items to it
        intersectables = new LinkedList<>();
    }

    /**
     * Constructor for Geometries
     *
     * @param intersectables one or more interfaces to add to the geometries list
     */
    public Geometries(Intersectable... intersectables) {
        this();
        add(intersectables);
    }

    /**
     * Creates a list of given intersectables.
     *
     * @param intersectables one or more intersections to add to the geometries list
     */
    public void add(Intersectable... intersectables) {
        Collections.addAll(this.intersectables, intersectables);
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        List<GeoPoint> result = null;

        // Iterate through each intersect item
        for (Intersectable geo : intersectables) {
            // Find intersection points between the item and the ray
            List<GeoPoint> tmp = geo.findGeoIntersections(ray, maxDistance);
            // If there are intersection points
            if (tmp != null) {
                // Initialize the result list if it's null
                if (result == null)
                    result = new LinkedList<>();
                // Add the item's intersection points to the result list
                result.addAll(tmp);
            }
        }
        return result;
    }
}
