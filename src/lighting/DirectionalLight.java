package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * A class to represent directional light.
 * extends Light implements LightSource
 * in direction light no attenuation with distance
 */
public class DirectionalLight extends Light implements LightSource {

    private final Vector direction;

    /**
     * constructor
     * @param intensity
     * @param direction
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction;
    }

    @Override
    public Color getIntensity(Point p) {
        return this.getIntensity();
    }

    @Override
    public Vector getL(Point p) {
        return this.direction;
    }

//    @Override
//    public Color getIntensity(primitives.Point3D p) {
//        return super.getIntensity();
//    }
}

