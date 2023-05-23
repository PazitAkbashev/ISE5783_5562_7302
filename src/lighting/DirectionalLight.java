package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * ************לשים לב מה שצריך להיות פיינאל!!!!!!!!!!! **
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
        return null;
    }

    @Override
    public Vector getL(Point p) {
        return null;
    }

//    @Override
//    public Color getIntensity(primitives.Point3D p) {
//        return super.getIntensity();
//    }
}

