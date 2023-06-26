package primitives;

/**
 * Material class is the basic class representing a material of a geometry
 *
 * @author Pazit and Leah - 26.06.23
 */
public class Material {

    //Transparency factor (=SHKIFUT) - between 0 and 1
    public Double3 kT = Double3.ZERO;

    //Reflection factor - between 0 and 1
    public Double3 kR = Double3.ZERO;

    //Diffusive factor
    public Double3 kD = Double3.ZERO;

    //Specular factor
    public Double3 kS = Double3.ZERO;

    //how shiny is the material
    public int nShininess = 0;

    /**
     * setter of kt - transparency factor
     *
     * @param kT3 - the transparency factor
     * @return the object itself
     */
    public Material setKt(Double3 kT3) {
        this.kT = kT3;
        return this;
    }

    /**
     * setter of kt - transparency factor
     *
     * @param kT - the transparency factor
     * @return the object itself
     */
    public Material setKt(double kT) {
        this.kT = new Double3(kT);
        return this;
    }

    /**
     * setter of kr - reflection factor
     *
     * @param kR3 - the reflection factor
     * @return the object itself
     */
    public Material setKr(Double3 kR3) {
        this.kR = kR3;
        return this;
    }

    /**
     * setter of kr - reflection factor
     *
     * @param kR - the reflection factor
     * @return the object itself
     */
    public Material setKr(Double kR) {
        this.kR = new Double3(kR);
        return this;
    }

    /**
     * setters of Material class by builder pattern
     *
     * @param kd3 - the diffusive factor
     * return the object itself
     */
    public Material setKd(Double3 kd3) {
        this.kD = kd3;
        return this;
    }

    /**
     * setter of kd - diffusive factor
     *
     * @param kd - the diffusive factor
     * @return the object itself
     */
    public Material setKd(double kd) {
        this.kD = new Double3(kd);
        return this;
    }

    /**
     * setter of ks - specular factor
     *
     * @param ks3 - the specular factor
     * @return the object itself
     */
    public Material setKs(Double3 ks3) {
        this.kS = ks3;
        return this;
    }

    /**
     * setter of ks - specular factor
     *
     * @param ks - the specular factor
     * @return the object itself
     */
    public Material setKs(double ks) {
        this.kS = new Double3(ks);
        return this;
    }

    /**
     * setter of nShininess - how shiny the material is
     *
     * @param shininess - how shiny the material is
     * @return the object itself
     */
    public Material setShininess(int shininess) {
        this.nShininess = shininess;
        return this;
    }
}
