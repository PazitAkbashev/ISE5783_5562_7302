package geometries;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * the class test for plan
 * test : get normal
 *
 * @author Pazit and lea
 */
class CylinderTest {

    /**
     * Test method for {@link geometries.Cylinder#getNormal(primitives.Point)}.
     */
    /**
     * *********!!!!!!!!!!!the tests here are for example only!!!!!*******************
     */
    @Test
    @Disabled //TODO
    void testGetNormal() {
        Ray ray = new Ray(new Point(0, 1, 0), new Vector(0, 1, 0));
        Cylinder cylinder = new Cylinder(2, ray, 4);

        // ============ Equivalence Partitions Tests ==============

        // TC01: There is a simple single test here
        assertEquals(new Vector(1, 0, 0), cylinder.getNormal(new Point(2, 2, 0)),
                "Normal abnormality");

        // TC02: Normal test on base
        assertEquals(cylinder.getNormal(new Point(1, 1, 0)), new Vector(0, -1, 0),
                "Normal abnormality");

        // TC03: Normal test on top base
        assertEquals(cylinder.getNormal(new Point(1, 5, 0)), new Vector(0, 1, 0),
                "Normal abnormality");

        // =============== Boundary Values Tests ==================

        // TC01: A dot in the center of the lower base
        assertEquals(cylinder.getNormal(new Point(0, 1, 0)), new Vector(0, -1, 0),
                "Normal abnormality");

        // TC02: A dot in the center of the upper base
        assertEquals(cylinder.getNormal(new Point(0, 5, 0)), new Vector(0, 1, 0),
                "Normal abnormality");
    }

//
//        Vector dir = new Vector(0, 0, 1);
//        Point p0 = new Point(0, 0, -1);
//        Ray axisRay = new Ray(p0, dir);
//        Cylinder cylinder = new Cylinder(1 ,axisRay, 2);
//
//        Point sidePoint = new Point(0, 1, 0);
//        Vector exceptVectorSide = new Vector(0, 2, 0).normalize();
//
//        Point topBaseCenterPoint = new Point(0, 0, 1);
//        Vector exceptVectorCenterTopBase = new Vector(0, 0, 2).normalize();
//
//        Point bottomBaseCenterPoint = new Point(0, 0, -1);
//        Vector exceptVectorCenterBottomBase = new Vector(0, 0, -2).normalize();
//
//        Point topBasePoint = new Point(-0.5, 0, 1);
//        Vector exceptVectorTopBase = new Vector(0, 0, 1);
//
//        Point bottomBasePoint = new Point(0.5, 0, -1);
//        Vector exceptVectorBottomBase = new Vector(0, 0, -1);
//
//        /* ============ Equivalence Partitions Tests ============== */
//
//        // TC01: point on the sidePoint.
//        assertEquals(exceptVectorSide, cylinder.getNormal(sidePoint),
//                "ERROR: getNormal() point on the side doesn't work correctly.");
//
//        // TC02: point the top base.
//        assertEquals(exceptVectorTopBase, cylinder.getNormal(topBasePoint),
//                "ERROR: getNormal() point on the top base doesn't work correctly.");
//
//        // TC03: point the bottom base.
//        assertEquals(exceptVectorBottomBase, cylinder.getNormal(bottomBasePoint),
//                "ERROR: getNormal() point on the top base doesn't work correctly.");
//
//        /* =============== Boundary Values Tests ================== */
//
//        /* TC04: point in the center the top base. */
//        assertEquals(exceptVectorCenterTopBase, cylinder.getNormal(topBaseCenterPoint),
//                "ERROR: getNormal() point in the center on the top base "
//                        + "doesn't work correctly.");
//
//        /* TC05: point in the center the bottom base. */
//        assertEquals(exceptVectorCenterBottomBase, cylinder.getNormal(bottomBaseCenterPoint),
//                "ERROR: getNormal() point in the center on the top base "
//                        + "doesn't work correctly.");
//    }
}
