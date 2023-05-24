package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 *
 */
public interface LightSource {

    Color getIntensity(Point p); //at a point IL

    Vector getL(Point p);
}
