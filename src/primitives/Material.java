package primitives;

/**
 * Material class is the basic class representing a material of a geometry
 *
 * @author Pazit and Leah
 */
public class Material {

    /**
     * Transparency factor (=SHKIFUT) - between 0 and 1
     */
    public Double3 kT = Double3.ZERO;

    /**
     * Reflection factor - between 0 and 1
     */
    public Double3 kR = Double3.ZERO;

    /**
     * Diffusive factor
     */
    public Double3 kD = Double3.ZERO;

    /**
     * Specular factor
     */
    public Double3 kS = Double3.ZERO;

    /**
     * how shiny is the material
     */
    // public int nShininess = 1;
    public int nShininess = 0;

    public Material setKt(Double3 kT3) {
        this.kT = kT3;
        return this;
    }

    public Material setKt(double kT) {
        this.kT = new Double3(kT);
        return this;
    }

    public Material setKr(Double3 kR3) {
        this.kR = kR3;
        return this;
    }

    public Material setKr(Double kR) {
        this.kR = new Double3(kR);
        return this;
    }

    /**
     * setters of Material class by builder pattern
     * returning the object itself
     */
    public Material setKd(Double3 kd3) {
        this.kD = kd3;
        return this;
    }

    public Material setKd(double kd) {
        this.kD = new Double3(kd);
        return this;
    }

    public Material setKs(Double3 ks3) {
        this.kS = ks3;
        return this;
    }

    public Material setKs(double ks) {
        this.kS = new Double3(ks);
        return this;
    }

    public Material setShininess(int shininess) {
        this.nShininess = shininess;
        return this;
    }
}

//
//public class Material {
//    /** transparency coefficient */
//    public Double3 kT = Double3.ZERO;
//    /** reflection coefficient */
//    public Double3 kR = Double3.ZERO;
//    /** Diffusive factor */
//    public Double3 kD = Double3.ZERO;
//    /** Specular factor */
//    public Double3 kS = Double3.ZERO;
//    /** how shiny is the material */
//    public int nShininess = 1;
//
//    /**
//     * Sets the transparency coefficient property.
//     *
//     * @param kT to set
//     * @return Material object
//     */
//    public Material setKt(Double3 kT) {
//        this.kT = kT;
//        return this;
//    }
//
//    /**
//     * Sets the reflection coefficient property.
//     *
//     * @param kR to set
//     * @return Materiel object
//     */
//    public Material setKr(Double3 kR) {
//        this.kR = kR;
//        return this;
//    }
//
//    /**
//     * Sets the transparency coefficient property.
//     *
//     * @param kT to set
//     * @return Material object
//     */
//    public Material setKt(double kT) {
//        this.kT = new Double3(kT);
//        return this;
//    }
//
//    /**
//     * Sets the reflection coefficient property.
//     *
//     * @param kR to set
//     * @return Materiel object
//     */
//    public Material setKr(double kR) {
//        this.kR = new Double3(kR);
//        return this;
//    }
//
//    /**
//     * Sets the kD property to a Double3.
//     *
//     * @param kD the Double3 kD to set
//     * @return this
//     */
//    public Material setKd(Double3 kD) {
//        if (!kS.add(kD).lowerThan(Double3.ONE))
//            throw new IllegalArgumentException("ERROR: kD + kS should be  <= 1");
//        this.kD = kD;
//        return this;
//    }
//
//    /**
//     * Sets the kD property to a Double3.
//     *
//     * @param kD the Double3 kD to set
//     * @return this
//     */
//    public Material setKd(double kD) {
//        if (!kS.add(kD).lowerThan(Double3.ONE))
//            throw new IllegalArgumentException("ERROR: kD + kS should be  <= 1");
//        this.kD = new Double3(kD);
//        return this;
//    }
//
//    /**
//     * Sets the kS property to a Double3
//     *
//     * @param kS the Double3 kS to set
//     * @return this
//     */
//    public Material setKs(Double3 kS) {
//        if (!kD.add(kS).lowerThanEqual(Double3.ONE))
//            throw new IllegalArgumentException("ERROR: kD + kS should be  <= 1");
//        this.kS = kS;
//        return this;
//    }
//
//    /**
//     * Sets the kS property to a Double3
//     *
//     * @param kS the Double3 kS to set
//     * @return this
//     */
//    public Material setKs(double kS) {
//        if (!kD.add(kS).lowerThanEqual(Double3.ONE))
//            throw new IllegalArgumentException("ERROR: kD + kS should be  <= 1");
//        this.kS = new Double3(kS);
//        return this;
//    }
//
//    /**
//     * Sets the nShininess property
//     *
//     * @param nShininess the nShininess to set
//     * @return this
//     */
//    public Material setShininess(int nShininess) {
//        this.nShininess = nShininess;
//        return this;
//    }
//
//}