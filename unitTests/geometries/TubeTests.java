package geometries;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import primitives.*;

/**
 * the class test for tube
 * test the: get normal
 * @author Pazit and lea
 */
class TubeTests {
    /**
     * Test method for {@link geometries.Tube#getNormal(primitives.Point)}.
     */
    @Test
    public void testGetNormal() {
        Tube tube = new Tube(1.0, new Ray(new Point(0, 0, 1), new Vector(0, 1, 0)));

        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        assertEquals(new Vector(0, 0, 1), tube.getNormal(new Point(0, 0.5, 2)), "Bad normal to tube");

        // =============== Boundary Values Tests ==================
        // TC11: The point is against axe start point
        assertEquals(new Vector(1, 0, 0), tube.getNormal(new Point(1, 0, 1)),
                "Bad normal to tube - against the axe start point");

    }
}