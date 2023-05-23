package primitives;

public class Material {
    private Double3 kD;
    private Double3 kS;
    private int nShininess;

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
        this.kS = new Double3(ks)
        return this;
    }

    public Material setShininess(int shininess) {
        this.nShininess = shininess;
        return this;
    }
}
