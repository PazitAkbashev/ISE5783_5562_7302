package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class SpotLight extends PointLight{
   private final Vector direction;

   /**
    * constructor
    *
    * @param position
    * @param intensity
    */
   public SpotLight(Point position, Color intensity, Vector dir) {
      super(position, intensity);
      this.direction = dir;
   }
}
