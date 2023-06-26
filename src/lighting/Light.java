package lighting;

import primitives.Color;

/**
 * Light class is the abstract class for all the lights in the scene
 *
 * @author Pazit and Leah - 26.06.23
 */
abstract class Light {

    //the intensity of light
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
     * getter for the intensity of the light
     *
     * @return the intensity of the light
     */
    public Color getIntensity() {
        return this.intensity;
    }
}
