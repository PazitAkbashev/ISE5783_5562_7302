package geometries;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    @Test
    void testGetNormal() {
        Vector dir = new Vector(0, 0, 1);
        Point p0 = new Point(0, 0, -1);
        Ray axisRay = new Ray(p0, dir);
        Cylinder cylinder = new Cylinder(1 ,axisRay, 2);

        Point sidePoint = new Point(0, 1, 0);
        Vector exceptVectorSide = new Vector(0, 2, 0).normalize();

        Point topBasePoint = new Point(-0.5, 0, 1);
        Vector exceptVectorTopBase = new Vector(0, 0, 1);

        Point topBaseCenterPoint = new Point(0, 0, 1);
        Vector exceptVectorCenterTopBase = new Vector(0, 0, 2).normalize();

        Point bottomBasePoint = new Point(0.5, 0, -1);
        Vector exceptVectorBottomBase = new Vector(0, 0, -1);

        Point bottomBaseCenterPoint = new Point(0, 0, -1);
        Vector exceptVectorCenterBottomBase = new Vector(0, 0, -2).normalize();

        /* ============ Equivalence Partitions Tests ============== */

        // TC01: point on the sidePoint.
        assertEquals(exceptVectorSide, cylinder.getNormal(sidePoint),
                "ERROR: getNormal() point on the side.");

        // TC02: point the top base.
        assertEquals(exceptVectorTopBase, cylinder.getNormal(topBasePoint),
                "ERROR: getNormal() point on the top base.");

        // TC03: point the bottom base.
        assertEquals(exceptVectorBottomBase, cylinder.getNormal(bottomBasePoint),
                "ERROR: getNormal() point on the top base.");

        /* =============== Boundary Values Tests ================== */

        /* TC04: point in the center the top base. */
        assertEquals(exceptVectorCenterTopBase, cylinder.getNormal(topBaseCenterPoint),
                "ERROR: getNormal() point in the center on the top base.");

        /* TC05: point in the center the bottom base. */
        assertEquals(exceptVectorCenterBottomBase, cylinder.getNormal(bottomBaseCenterPoint),
                "ERROR: getNormal() point in the center on the top base.");
    }
    //!!!!need to add : BVA: 2 TC on the base edge (force decision which normal to use)!!!!! //TODO
}
