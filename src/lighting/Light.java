package lighting;

import primitives.Color;

/**
 * Light class is the abstract class for all the lights in the scene
 *
 * @author Pazit and Leah
 */
abstract class Light {

    /** the intensity of light*/
    private final Color intensity;

    /**
     * constructor for Light class
     *
     * @param intensity the intensity of the light
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * getIntensity function is the function that return the intensity of the light
     *
     * @return the intensity of the light
     */
    protected Color getIntensity(){ return this.intensity;}
}
