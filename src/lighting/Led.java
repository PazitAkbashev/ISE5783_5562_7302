package lighting;

import geometries.Cylinder;
import primitives.Ray;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import primitives.Point;
import primitives.Color;

public class Led extends Cylinder {

    /**
     * constructor for finite Cylinder
     *
     * @param radius  - radius of the circumference of the cylinder
     * @param axisray - ray of the circumference of the cylinder
     * @param length  - the length of the cylinder
     */
    public Led(double radius, Ray axisray, double length) {
        //the super is the constructor of the Cylinder
        super(radius, axisray, length);
    }

    public List<PointLight> lightSource(int num, double ledsLength) {
       //List<PointLight> list = new pointsList<>();
        List<PointLight> list = new ArrayList<>();
        for(int i = 0; i < (int)ledsLength; i++) {
            if(i % 10 == 0) {
               list.add(0, new PointLight(new Color(0, 0, 0),
                       new Point(0.0, 0.0, 0.0),
                       1,
                       0.0001,
                       0.0001));

            }
        }
        return null;
    }
}
