package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import java.util.List;

/**
 * A class to represent directional light.
 * extends Light implements LightSource
 * in direction light no attenuation with distance
 *
 * @author Pazit and Leah - 26.06.23
 */
public class DirectionalLight extends Light implements LightSource {

    //the direction of the light
    private final Vector direction;

    /**
     * constructor for directional light
     *
     * @param intensity
     * @param direction
     */
    public DirectionalLight(Color intensity, Vector direction) {
        //initialize the intensity by sending it to fathers class
        super(intensity);

        //initialize the direction vector of the light
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

    @Override
    public List<Vector> getLCircle(Point p, double r, int amount) {
        //list of rays for soft shadows
        List<Vector> list = List.of(getL(p));
        return list;
    }
}

