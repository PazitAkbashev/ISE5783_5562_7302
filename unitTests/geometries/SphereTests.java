package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class SphereTests {

    @Test
    void testGetNormal() {
        Sphere sphere = new Sphere(new Point(0,0,0),1d);
        assertEquals(new Vector(1,0,0),sphere.getNormal(new Point(2,0,0)),"too bad");

    }

}