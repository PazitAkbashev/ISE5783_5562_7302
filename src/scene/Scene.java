package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import primitives.Color;

/**
 * no getters here' setters only!!!
 */
public class Scene {

    public String name;
   public Color background = Color.BLACK;
   public AmbientLight ambientLight = AmbientLight.NONE;
   public Geometries geometries = new Geometries();

    public Scene(String name) {
        this.name = name;
    }

    public void setBackground(Color background) {
        this.background = background;
    }

    public void setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
    }

    public void setGeometries(Geometries geometries) {
        this.geometries = geometries;
    }
}
