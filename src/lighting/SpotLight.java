package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class SpotLight extends PointLight{
   private final Vector direction;
   private double narrowBeam=3;

   /**
    * constructor
    *
    * @param position
    * @param intensity
    */
   public SpotLight( Color intensity,Point position,Vector dir) {
      super(intensity,position);
      this.direction = dir;
   }

   @Override
   public Color getIntensity(Point p) {
      Vector l = getL(p);
      return super.getIntensity(p).scale(Math.max(0, direction.dotProduct(l)));
   }

   public SpotLight setNarrowBeam(double num) {
      this.narrowBeam=num;
      return this;
   }
}
