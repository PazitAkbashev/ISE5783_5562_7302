package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import java.util.List;

/**
 * LightSource interface is the interface for all the lights in the scene
 * that have a source
 *
 * @author pazit and Leah
 */
public interface LightSource {

    /**
     * getIntensity function is the function that return the intensity of the light
     *
     * @param p the point to calculate the intensity at.
     * @return the intensity of the light
     */
    Color getIntensity(Point p);

    /**
     * getL function is the function that return the vector from the light source to the point
     *
     * @param p the point to calculate the vector to
     * @return the vector from the light source to the point
     */
    Vector getL(Point p);

    double getDistance(Point point);

    /**
     * Creates a list of vectors from the given point to random points around the light within radius r
     *
     * @param p the given point
     * @param r the radius
     * @param amount the amount of vectors to create
     * @return list of vectors
     */
    List<Vector> getLCircle(Point p, double r, int amount);  //for soft shadows

}
