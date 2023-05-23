package lighting;

import primitives.Point;
import primitives.Vector;

public interface LightSource {

    public Vector getL(Point p);
    public double getDistance(Point point);
}
