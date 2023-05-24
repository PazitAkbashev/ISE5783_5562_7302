package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * PointLight class is the class representing a point light
 * extends Light implements LightSource
 * in point light there is attenuation with distance
 *
 * @author Pazit and Leah
 */
public class PointLight  extends Light implements  LightSource{

    /** the position of the light*/
    private Point position;

    /** the attenuation factors*/
   private double kC = 1;

   /** the attenuation factors*/
    private double kL = 0;

    /** the attenuation factors*/
    private double kQ = 0;

    /**
     * setter for the attenuation factors
     */
    public PointLight setKC(double kC) {
        this.kC = kC;
        return this;
    }

    public PointLight setKL(double kL) {
        this.kL = kL;
        return this;
    }

    public PointLight setKQ(double kQ) {
        this.kQ = kQ;
        return this;
    }

    /**
     * constructor
     *
     * @param intensity the intensity of the light
     * @param position the position of the light
     */
    public PointLight(Color intensity,Point position) {
        super(intensity);
        this.position = position;
    }

    @Override
    public Color getIntensity(Point p) {
        double dd = position.distanceSquared(p);
        return getIntensity().reduce(kC + kL * Math.sqrt(dd) + kQ * dd);
    }

    @Override
    public Vector getL(Point p) {
        return p.subtract(position);
    }

}
