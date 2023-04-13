//package primitives;
//
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static primitives.Util.isZero;
//
//class VectorTests {

//  /** Test method for {@link primitives.Vector#add(primitives.Vector)}.
//            */
//    @Test
//    public void testAdd() {
//        // ============ Equivalence Partitions Tests ==============
//        // TC01: Simple test
//        assertEquals( new Vector(1, 1, 1),
//                new Vector(2, 3, 4).add(new Vector(-1, -2, -3)),
//                "Wrong vector add");
//
//        // =============== Boundary Values Tests ==================
//        // TC11: test adding v + (-v)
//        assertThrows( IllegalArgumentException.class,
//                () -> new Vector(1, 2, 3).add(new Vector(-1, -2, -3)),
//                "Add v plus -v must throw exception");
//    }
//
//  /** Test method for {@link primitives.Vector#subtract(Point)} (primitives.Vector)}.
//            */
//    @Test
//    public void testSubtract() {
//        // ============ Equivalence Partitions Tests ==============
//        // TC01: Simple test
//        assertEquals( new Vector(1, 1, 1), new Vector(2, 3, 4).subtract(new Vector(1, 2, 3)),
//                "Wrong vector subtract");
//
//        // =============== Boundary Values Tests ==================
//        // TC11: test subtracting same vector
//        assertThrows( IllegalArgumentException.class,
//                () -> new Vector(1, 2, 3).subtract(new Vector(1, 2, 3)),
//                "Subtract v from v must throw exception");
//    }
//
//  /** Test method for {@link primitives.Point#subtract(Point)} (primitives.Point)}.
//            */
//    @Test
//    public void testPointSubtract() {
//        // ============ Equivalence Partitions Tests ==============
//        // TC01: Simple test
//        assertEquals(new Vector(1, 1, 1), new Point(2, 3, 4).subtract(new Point(1, 2, 3)),
//                "Wrong point subtract");
//
//        // =============== Boundary Values Tests ==================
//        // TC11: test subtracting same point
//        assertThrows(IllegalArgumentException.class,
//                () -> new Point(1, 2, 3).subtract(new Point(1, 2, 3)),
//                "Subtract P from P must throw exception");
//    }
//
//  /*
//          * Test method for {@link primitives.Vector#scale(double)}.
//            */
//    @Test
//    public void testScale() {
//        // ============ Equivalence Partitions Tests ==============
//        // TC01: Simple test
//        assertEquals("Wrong vector scale", new Vector(2, 4, 6), new Vector(1, 2, 3).scale(2));
//
//        // =============== Boundary Values Tests ==================
//        // TC11: test adding v + (-v)
//        assertThrows("Scale by 0 must throw exception", IllegalArgumentException.class,
//                () -> new Vector(1, 2, 3).scale(0d));
//    }
//
//  /** Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
//            */
//    @Test
//    public void testDotProduct() {
//        Vector v1 = new Vector(1, 2, 3);
//
//        // ============ Equivalence Partitions Tests ==============
//        // TC01: Simple dotProduct test
//        Vector v2 = new Vector(-2, -4, -6);
//        assertEquals("dotProduct() wrong value", -28d, v1.dotProduct(v2), 0.00001);
//
//        // =============== Boundary Values Tests ==================
//        // TC11: dotProduct for orthogonal vectors
//        Vector v3 = new Vector(0, 3, -2);
//        assertEquals("dotProduct() for orthogonal vectors is not zero", 0d, v1.dotProduct(v3), 0.00001);
//    }
//
//  /** Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
//            */
//    /**
//     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
//     */
//    @Test
//    public void testCrossProduct() {
//        Vector v1 = new Vector(1, 2, 3);
//        // ============ Equivalence Partitions Tests ==============
//        Vector v2 = new Vector(0, 3, -2);
//        Vector vr = v1.crossProduct(v2);
//        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
//        // for simplicity)
//        assertEquals(v1.length() * v2.length(), vr.length(), 0.00001, "crossProduct() wrong result length");
//        // TC02: Test cross-product result orthogonality to its operands
//        assertTrue(isZero(vr.dotProduct(v1)), "crossProduct() result is not orthogonal to 1st operand");
//        assertTrue(isZero(vr.dotProduct(v2)), "crossProduct() result is not orthogonal to 2nd operand");
//        // =============== Boundary Values Tests ==================
//        // TC11: test zero vector from cross-productof co-lined vectors
//        Vector v3 = new Vector(-2, -4, -6);
//        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v3),
//                "crossProduct() for parallel vectors does not throw an exception");
//    }
//    /** Test method for {@link primitives.Vector#lengthSquared()}.
//            */
//    @Test
//    public void testLengthSquared() {
//        // ============ Equivalence Partitions Tests ==============
//        // TC01: Simple test
//        assertEquals("lengthSquared() wrong value", 14d, new Vector(1, 2, 3).lengthSquared(), 0.00001);
//    }
//
//  /** Test method for {@link primitives.Vector#length()}.
//            */
//    @Test
//    public void testLength() {
//        // TC01: Simple test
//        assertEquals("length() wrong value", 5d, new Vector(0, 3, 4).length(), 0.00001);
//    }
//
//
//  /** Test method for {@link primitives.Vector#normalize()}.
//            */
//    @Test
//    public void testNormalize() {
//        Vector v = new Vector(0, 3, 4);
//        Vector n = v.normalize();
//        // ============ Equivalence Partitions Tests ==============
//        // TC01: Simple test
//        assertTrue("normalize() does not change the vector itself", v == n);
//        assertEquals("wrong normalized vector length", 1d, v.lengthSquared(), 0.00001);
//        assertEquals("wrong normalized vector", new Vector(0, 0.6, 0.8), v);
//    }
//
}
