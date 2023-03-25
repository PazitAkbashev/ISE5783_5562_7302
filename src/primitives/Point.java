package primitives;
import primitives.Double3;

public class Point {
    private Double3 point3d;

    public Point(Double3 point3d) {
        this.point3d = point3d;
    }


     public Point (double d1, double d2, double d3){
         this.point3d = super(d1, d2, d3);
     }

}
