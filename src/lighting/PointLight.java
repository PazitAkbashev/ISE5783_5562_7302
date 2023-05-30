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

    public PointLight setKl(Double3 kL) {
        this.kL = kL;
        return this;
    }

    public PointLight setKq(Double3 kQ) {
        this.kQ = kQ;
        return this;
    }

    /**
     * setter for the attenuation factors
     */

    public PointLight setKc(double kC) {
        this.kC = new Double3(kC);
        return this;
    }

    public PointLight setKl(double kL) {
        this.kL = new Double3(kL);
        return this;
    }

    public PointLight setKq(double kQ) {
        this.kQ = new Double3(kQ);
        return this;
    }

    @Override
    public Color getIntensity(Point p) {
        double d = position.distance(p);
        Double3 factor = kC.add(kL.scale(d).add(kQ.scale(d * d)));
        return getIntensity().reduce(factor);
    }

    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }

    @Override
    public double getDistance(Point point) {
        return  this.position.distance(point);
    }

}
