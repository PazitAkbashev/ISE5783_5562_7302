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

    /**
     * the direction of the light
     */
    private final Vector direction;

    /**
     * the narrow beam of the light
     */
    private double narrowBeam = 3;

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

    /**
     * getL function is the function that return the direction of the light
     * according to design pattern builder
     *
     * @param num the point to calculate the vector to
     * @return the object itself
     */
    public SpotLight setNarrowBeam(double num) {
        this.narrowBeam = num;
        return this;
    }

    @Override
    public Color getIntensity(Point p) {
        Vector l = getL(p);
        return super.getIntensity(p).scale(Math.max(0, direction.dotProduct(l)));
    }
}
