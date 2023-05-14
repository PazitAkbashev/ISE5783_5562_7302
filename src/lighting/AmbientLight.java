package lighting;

import primitives.Color;
import primitives.Double3;

/**
 *
 * @author pazit and Leah
 */
public class AmbientLight {
    public Color intensity;
    public static AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO);

    Color getIntensity(){ return this.intensity;}
    /**
     * constructor
     * @param iA
     * @param kA
     */
    public AmbientLight(Color iA, Double kA) {
        this.intensity = iA.scale(kA);
    }

    public AmbientLight(double kA){
        this.intensity = this.intensity.scale(kA);
    }

    public AmbientLight(Color iA, Double3 kA){
        this.intensity = iA.scale(kA);
    }

    public AmbientLight(Color iA , double kA){
        this.intensity = iA.scale(kA);
    }


}
