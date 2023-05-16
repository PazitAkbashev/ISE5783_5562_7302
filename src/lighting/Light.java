package lighting;

import primitives.Color;

public abstract class Light {
    private final Color intensity;

    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    Color getIntensity(){ return this.intensity;}
}
