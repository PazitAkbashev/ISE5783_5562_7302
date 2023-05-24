package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 *
 * @author Pazit and Leah
 */

public class PointLight  extends Light implements  LightSource{
    private Point position;
   private double kC = 1;
    private double kL = 0;
    private double kQ = 0;

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
     * @param intensity
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
