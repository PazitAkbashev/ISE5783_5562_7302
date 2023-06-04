package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * A class to represent directional light.
 * extends Light implements LightSource
 * in direction light no attenuation with distance
 *
 * @author pazit  and Leah
 */
public class DirectionalLight extends Light implements LightSource {

    /**
     * the direction of the light
     */
    private final Vector direction;

    /**
     * constructor
     *
     * @param intensity
     * @param direction
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction.normalize();
    }

    @Override
    public Color getIntensity(Point p) {
        return this.getIntensity();
    }

    @Override
    public Vector getL(Point p) {
        return this.direction;
    }

    @Override
    public double getDistance(Point point) {
        return Double.POSITIVE_INFINITY;
    }
}

