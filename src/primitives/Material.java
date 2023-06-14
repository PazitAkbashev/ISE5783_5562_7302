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

    /**Reflection factor - between 0 and 1*/
    public Double3 kR = Double3.ZERO;

    /**Diffusive factor*/
    public Double3 kD = Double3.ZERO;

    /** Specular factor*/
    public Double3 kS = Double3.ZERO;

    /** how shiny is the material*/
   // public int nShininess = 1;
    public int nShininess = 0;

    public Material setkT(Double3 kT3) {
        this.kT = kT3;
        return this;
    }

    public Material setkT(double kT) {
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
