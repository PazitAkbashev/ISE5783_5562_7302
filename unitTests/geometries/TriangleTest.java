package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * the class test for triangle
 * test the: get normal
 * find intersection
 *
 * @author Pazit and lea
 */
class TriangleTest {
    /**
     * Test method for {@link geometries.Triangle#getNormal(primitives.Point)}.
     */
    Triangle tr = new Triangle(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));
    Plane pl = new Plane(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));

    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: .There is a simple single test here
        Triangle triangle = new Triangle(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals(new Vector(sqrt3, sqrt3, sqrt3), triangle.getNormal(new Point(0, 0, 1)), "Bad normal to plan");
    }

    /**
     * Test method for {@link geometries.Triangle#findIntersections(primitives.Ray)}.
     */
    @Test
    void testTriangleIntersectionTC02() {
        Ray ray = new Ray(new Point(0, 0, -1), new Vector(1, 1, 0));
        assertEquals(List.of(new Point(1, 1, -1)), pl.findIntersections(ray), "Wrong intersection with plane");
        assertNull(tr.findIntersections(ray), "Bad intersection");
    }

    @Test
    void testFindIntersections() {
        Triangle triangle = new Triangle(new Point(0, 0, 3), new Point(-2, 0, 0), new Point(0, -3, 0));
        // ============ Equivalence Partitions Tests ==============
        //TC01: Ray's line out of triangle
        assertNull(triangle.findIntersections(new Ray(new Point(-5, 1, 0), new Vector(1, 2, 0)))
                , "Ray's line out of triangle");

        //TC02: Ray's line inside triangle
        Triangle triangle2 = new Triangle(new Point(0, 0, 3), new Point(-2, 0, 0), new Point(0, 0, 0));
        assertEquals(List.of(new Point(-1, 0, 1)), triangle2.findIntersections(new Ray(new Point(-1, -3, 0)
                        , new Vector(0, 3, 1)))
                , "Ray's line inside triangle");

        //TC03: Ray's line the two continuations of sides
        assertNull(triangle2.findIntersections(new Ray(new Point(-1, -3, 0)
                        , new Vector(2, -1, 1)))
                , "Ray's line the two continuations of sides");

        // =============== Boundary Values Tests ==================
        //TC11:Ray's line on the vertices
        assertNull(triangle.findIntersections(new Ray(new Point(-5, 1, 0)
                        , new Vector(3, -1, 0)))
                , "Ray's line on the vertices");

        //TC12:Ray's line on the side of the triangle
        assertNull(triangle.findIntersections(new Ray(new Point(-5, 1, 0)
                        , new Vector(1, 2, 0)))
                , "Ray's line on the continuation of a side of the triangle");
    }
}




