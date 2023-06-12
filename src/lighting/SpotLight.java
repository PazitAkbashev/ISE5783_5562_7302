package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * SpotLight class is the class representing a spot light
 * extends PointLight
 * in spot light there is attenuation with distance and the light is in a cone
 *
 * @author pazit and leah
 */
public class SpotLight extends PointLight {

    /**the direction of the light */
    private final Vector direction;

    /**
     * constructor
     *
     * @param position  the position of the light
     * @param intensity the intensity of the light
     */
    public SpotLight(Color intensity, Point position, Vector dir) {
        super(intensity, position);
        this.direction = dir.normalize();
    }

    @Override
    /**
     * Calculates the intensity of light at a given point for a spotlight.
     *
     * @param p The point at which to calculate the intensity.
     * @return The intensity of light at the given point.
     */
    public Color getIntensity(Point p) {
        Vector l = getL(p);  // Calculate the direction vector from the light source position to the given point

        double angle = direction.dotProduct(l);  // Calculate the angle between the spotlight direction and the direction to the point
        double factor = angle > 0 ? angle : 0;   // If the angle is positive, use it as the factor; otherwise, set it to 0

        return super.getIntensity(p).scale(factor);  // Scale the overall intensity of the light source by the factor
    }


}
