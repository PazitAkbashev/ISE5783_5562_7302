package geometries;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import primitives.*;

class TubeTests {
    /**
     * Test method for {@link geometries.Tube#getNormal(primitives.Point)}.
     */
    @Test
    public void testGetNormalDan() {
        Tube tube = new Tube(1.0, new Ray(new Point(0, 0, 1), new Vector(0, 1, 0)));

        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        assertEquals(new Vector(0, 0, 1), tube.getNormal(new Point(0, 0.5, 2)), "Bad normal to tube");

        // =============== Boundary Values Tests ==================
        // TC11: The point is against axe start point
        assertEquals(new Vector(1, 0, 0), tube.getNormal(new Point(1, 0, 1)),
                "Bad normal to tube - against the axe start point");

    }
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: There is a simple single test here
        Ray ray = new Ray(new Point(0,0,1), new Vector(0,1,0));
        Tube tb = new Tube(1, ray);
        assertEquals(tb.getNormal(new Point(2,0,0)) ,new Vector(1,0,0),
                "Normal abnormality");

        // =============== Boundary Values Tests ==================

        // TC11: test When connecting the point to the horn head of the cylinder axis produces a right angle with the axis
        Tube tube = new Tube(1,new Ray(new Point(0,0,0),new Vector(1,0,0)));
        assertEquals(tube.getNormal(new Point(0,1,0)),new Vector(0,1,0),
                "The point in front of the head of the foundation");
    }
}