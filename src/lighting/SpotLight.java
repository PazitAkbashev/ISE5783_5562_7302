package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * SpotLight class is the class representing a spot light
 * extends PointLight
 * in spotlight there is attenuation with distance and the light is in a cone
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
    public Color getIntensity(Point p) {
        // Calculate the direction vector from the light source position to the given point
        Vector l = getL(p);

        // Calculate the angle between the spotlight direction and the direction to the point
        double angle = direction.dotProduct(l);
        // If the angle is positive, use it as the factor; otherwise, set it to 0
        double factor = angle > 0 ? angle : 0;

        // Scale the overall intensity of the light source by the factor
        return super.getIntensity(p).scale(factor);
    }


}
