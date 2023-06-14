package lighting;

import primitives.Color;
import primitives.Double3;
import primitives.Point;
import primitives.Vector;

/**
 * PointLight class is the class representing a point light
 * extends Light implements LightSource
 * in point light there is attenuation with distance
 *
 * @author Pazit and Leah
 */
public class PointLight extends Light implements LightSource {

    /**
     * the position of the light
     */
    private final Point position;

    /**
     * the attenuation factors
     */
    private Double3 kC = Double3.ONE;

    /**
     * the attenuation factors
     */
    private Double3 kL = Double3.ZERO;

    /**
     * the attenuation factors
     */
    private Double3 kQ = Double3.ZERO;

    /**
     * constructor
     *
     * @param intensity the intensity of the light
     * @param position  the position of the light
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }

    /**
     * constructor
     *
     * @param intensity the intensity of the light
     * @param position  the position of the light
     * @param kC        attenuation factors
     * @param kL        attenuation factors
     * @param kQ        attenuation factors
     */
    public PointLight(Color intensity, Point position, double kC, double kL, double kQ) {
        super(intensity);
        this.position = position;
        setKc(kC);
        setKl(kL);
        setKq(kQ);
    }

    public PointLight setKc(Double3 kC) {
        this.kC = kC;
        return this;
    }

    /**
     * setter for the attenuation factors
     */
    public PointLight setKc(double kC) {
        this.kC = new Double3(kC);
        return this;
    }

    public PointLight setKl(Double3 kL) {
        this.kL = kL;
        return this;
    }

    public PointLight setKl(double kL) {
        this.kL = new Double3(kL);
        return this;
    }

    public PointLight setKq(Double3 kQ) {
        this.kQ = kQ;
        return this;
    }

    public PointLight setKq(double kQ) {
        this.kQ = new Double3(kQ);
        return this;
    }

    @Override
    public Color getIntensity(Point p) {
        // Distance between the light source position and the given point
        double d = position.distance(p);

        if (d <= 0) {
            // If the distance is close to zero or negative, return the overall intensity of the light source
            return getIntensity();
        }

        // Calculate the attenuation factor based on distance and coefficients
        Double3 factor = kC.add(kL.scale(d).add(kQ.scale(d * d)));

        // Reduce the overall intensity of the light source based on the attenuation factor
        Color intensity = getIntensity().reduce(factor);

        // Return the resulting intensity at the given point
        return intensity;
    }


    @Override
    public Vector getL(Point p) {
        // Calculate the vector from the light source position to the given point and normalize it
        Vector l = p.subtract(position).normalize();

        // Return the resulting direction vector
        return l;
    }


    @Override
    public double getDistance(Point point) {
        // Calculate the distance between the light source position and the given point
        double dis = this.position.distance(point);

        // Return the resulting distance
        return dis;
    }


}
