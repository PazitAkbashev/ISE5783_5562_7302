package lighting;

import primitives.Color;

/**
 * Light class is the abstract class for all the lights in the scene
 * It is responsible for the intensity of the light
 *
 * @author Pazit and Leah
 */
public abstract class Light {

    /** the intensity of the light*/
    private final Color intensity;

    /**
     * constructor for Light class
     * @param intensity the intensity of the light
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * getIntensity function is the function that return the intensity of the light
     * @return the intensity of the light
     */
    Color getIntensity(){ return this.intensity;}
}
