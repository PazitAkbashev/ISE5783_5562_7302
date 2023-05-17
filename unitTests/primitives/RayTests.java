package primitives;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for primitives.Ray class
 *
 * @author Pazit and Leah
 */
class RayTests {

    /**
     * Test method for {@link primitives.Ray#findClosestPoint(java.util.List)}.
     */
    @Test
    void testFindClosestPoint() {
        //============ Equivalence Partitions Tests ==============

        // TC01: The closest point is in the middle of the list

        Ray ray = new Ray(new Point(0, 0, 1), new Vector(0, 0, 1));
        List<Point> points = List.of(new Point(0, 0, 2), new Point(0, 0, 3), new Point(0, 0, 4));
        assertEquals(new Point(0, 0, 2), ray.findClosestPoint(points), "Wrong point");

        // =============== Boundary Values Tests ==================

        // TC11: The list is empty
        assertNull(ray.findClosestPoint(null), "Wrong point");

        // TC12: The closest point is the first point in the list
        points = List.of(new Point(0, 0, 1), new Point(0, 0, 2), new Point(0, 0, 3));
        assertEquals(new Point(0, 0, 1), ray.findClosestPoint(points), "Wrong point");

        // TC13: The closest point is the last point in the list
        points = List.of(new Point(0, 0, 3), new Point(0, 0, 2), new Point(0, 0, 1));
        assertEquals(new Point(0, 0, 1), ray.findClosestPoint(points), "Wrong point");

    }
}