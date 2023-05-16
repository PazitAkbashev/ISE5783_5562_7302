package lighting;

import primitives.Color;
import primitives.Double3;

/**
 *
 * @author pazit and Leah
 */
public class AmbientLight extends  Light {
    public static AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO);

    /**
     * constructor
     * @param iA
     * @param kA
     */
    public AmbientLight(Color iA, Double3 kA) {
        super( iA.scale(kA));
    }


    public AmbientLight(Color iA, double kA){
        super( iA.scale(kA));
    }


}
