package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * AmbientLight class is the basic class representing an ambient light
 * It is responsible for the intensity of the ambient light
 *
 * @author pazit and Leah
 */
public class AmbientLight extends Light {

    //default ambient light
    public static AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO);

    /**
     * constructor for D3
     *
     * @param iA - the intensity of the ambient light
     * @param kA - the attenuation factor of the ambient light
     */
    public AmbientLight(Color iA, Double3 kA) {
        super(iA.scale(kA));
    }

    /**
     * constructor for D1
     *
     * @param iA - the intensity of the ambient light
     * @param kA - the attenuation factor of the ambient light
     */
    public AmbientLight(Color iA, double kA) {
        super(iA.scale(kA));
    }


}
