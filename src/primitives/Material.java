package primitives;

/**
 * Material class is the basic class representing a material of a geometry
 *
 * @author Pazit and Leah
 */
public class Material {
    public Material setkT(Double3 kT) {
        this.kT = kT;
        return this;
    }

    public Material setkR(Double3 kR) {
        this.kR = kR;
        return this;
    }


    public Material setkT(Double kT) {
        this.kT = new Double3(kT);
        return this;
    }

    public Material setkR(Double kR) {
        this.kR = new Double3(kR);
        return this;
    }


    public Double3 kT=Double3.ZERO;//TODO HEAROT
    public Double3 kR=Double3.ZERO;//TODO HEAROT

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
    public int nShininess = 1;

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
