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
 * @author Pazit and lea
 */
class CylinderTest {
    /**
     *Test method for {
     * @link geometries.Cylinder#getNormal(primitives.Point)}.
     */
    @Test
    @Disabled
    void testGetNormal() {
        Ray ray = new Ray(new Point(0,1,0), new Vector(0,1,0));
        Cylinder  cylinder = new Cylinder(2, ray ,4);
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        assertEquals(new Vector(1,0,0),cylinder.getNormal(new Point(2,2,0)),
                "Normal abnormality");
        // TC02: Normal test on base
        assertEquals(cylinder.getNormal(new Point(1,1,0)) ,new Vector(0,-1,0),
                "Normal abnormality");
        // TC03: Normal test on top base
        assertEquals(cylinder.getNormal(new Point(1,5,0)) ,new Vector(0,1,0),
                "Normal abnormality");
        // =============== Boundary Values Tests ==================
        // TC01: A dot on the center of the lower base
        assertEquals(cylinder.getNormal(new Point(0,1,0)),new Vector(0,-1,0),
                "Normal abnormality");
        // TC02: A dot in the center of the upper base
        assertEquals(cylinder.getNormal(new Point(0,5,0)),new Vector(0,1,0),
                "Normal abnormality");
    }
}