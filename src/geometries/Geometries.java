package geometries;

import primitives.Ray;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author pazit and Lea
 */
public class Geometries extends Intersectable {
    private List<Intersectable> intersectables = null;

    /**
     * Default constructor for Geometries
     */
    public Geometries() {
        // use LinkedList because the use of this list is only for iterate
        // from the start of the list to the end, and adding new items to her
        intersectables = new LinkedList();
    }

    /**
     * Constructor for Geometries
     * @param intersectables one or more interfaces to add to the geometries list
     */
    public Geometries(Intersectable... intersectables) {
        this();
        add(intersectables);
    }

    /**
     * Add interfaces to the list of the geometries
     * @param intersectables one or more interfaces to add to the geometries list
     */
    public void add(Intersectable... intersectables) {
        Collections.addAll(this.intersectables, intersectables);
    }

    @Override
    /**
     * Finds the intersection points between a given ray and a collection of intersectable objects.
     * @param ray         The ray to find intersections with.
     * @param maxDistance The maximum distance for valid intersections.
     * @return A list of GeoPoint objects representing the intersection points, or null if no intersections are found.
     */
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        List<GeoPoint> result = null;
        // Iterate through each intersect item
        for (var item : intersectables)

        {
            List<GeoPoint> itemPoints = item.findGeoIntersections(ray, maxDistance);// Find intersection points between the item and the ray
            // If there are intersection points
            if (itemPoints != null) {
                // Initialize the result list if it's null
                if (result == null)
                {
                    result = new LinkedList<>();
                }
                result.addAll(itemPoints);// Add the item's intersection points to the result list
            }
        }
        return result;
    }
}
