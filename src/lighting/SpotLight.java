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
//    public Color getIntensity(Point p) {
//        Vector l = getL(p);
//        Color itensity = super.getIntensity(p).scale(Math.max(0, direction.dotProduct(l)));
//        return itensity;
//    }
    public Color getIntensity(Point p) {
        Vector l = getL(p); // direction to the point
        double angle = direction.dotProduct(l); // the angle between the spot direction and the point direction
        double factor =  angle > 0 ? angle : 0;

        return super.getIntensity(p).scale(factor);
    }

}
