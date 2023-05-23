package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 *
 * @author Pazit and Leah
 */

public class PointLight  extends Light implements  LightSource{
    Point position;
   private double kC = 1;
    private double kL = 0;
    private double kQ = 0;

    public PointLight setkC(double kC) {
        this.kC = kC;
        return this;
    }

    public PointLight setkL(double kL) {
        this.kL = kL;
        return this;
    }

    public PointLight setkQ(double kQ) {
        this.kQ = kQ;
        return this;
    }

    /**
     * constructor
     *
     * @param intensity
     */
    public PointLight(Point position, Color intensity) {
        super(intensity);
        this.position = position;
    }

    @Override
    public Color getIntensity(Point p) {
        return null;
    } //TODO

    @Override
    public Vector getL(Point p) {
        return null;
    } //TODO
}
