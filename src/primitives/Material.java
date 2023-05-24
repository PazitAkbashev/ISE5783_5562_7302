package primitives;

/**
 * Material class is the basic class representing a material of a geometry
 *
 * @author Pazit and Leah
 */
public class Material {
    public Double3 kD = new Double3(0, 0, 0);
    public Double3 kS = new Double3(0, 0, 0);
    public int nShininess = 0;

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
