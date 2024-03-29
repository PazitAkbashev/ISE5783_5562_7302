package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * the class test for tube
 * test the: get normal, first test was required, the second was for explanation from Eliezer
 *
 * @author Pazit and lea
 */
class TubeTests {
    /**
     * Test method for {@link geometries.Tube#getNormal(primitives.Point)}.
     */
    @Test
    public void testGetNormal1() {
        Tube tube = new Tube(1.0, new Ray(new Point(0, 0, 1), new Vector(0, 1, 0)));

        // ============ Equivalence Partitions Tests ==============
        // TC01:
        assertEquals(new Vector(0, 0, 1), tube.getNormal(new Point(0, 0.5, 2)), "Bad normal to tube");

        // =============== Boundary Values Tests ==================
        // TC11: The point is against axe start point
        assertEquals(new Vector(1, 0, 0), tube.getNormal(new Point(1, 0, 1)),
                "Bad normal to tube - against the axe start point");

    }

    /**
     * Test method for {@link geometries.Tube#getNormal(primitives.Point)}.
     * wasn't required test
     */
    @Test
    void testGetNormal2() {
        //thanks to Eliezer
        // ============ Equivalence Partitions Tests ==============

        Tube tube = new Tube(2.5, new Ray(new Point(0, 0, -2.5), new Vector(0, 1, 0)));

        // TC01:
        assertEquals(new Vector(0, 0, 1), tube.getNormal(new Point(0, 0, 0.5)), "bad luck");
    }
}