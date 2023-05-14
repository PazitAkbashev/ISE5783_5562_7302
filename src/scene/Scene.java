package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import primitives.Color;

/**
 * Scene class is the basic class representing a scene
 * It is responsible for the geometries in the scene
 *
 * @author Pazit and Leah
 */
public class Scene {

    public String name;
   public Color background = Color.BLACK;
   public AmbientLight ambientLight = AmbientLight.NONE;
   public Geometries geometries = new Geometries();

    /**
     * constructor for Scene class
     * @param name the name of the scene
     */
    public Scene(String name) {
        this.name = name;
    }

    /**
     * @param background the background to set
     * @return this
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * @param ambientLight the ambientLight to set
     * @return this
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * @param geometries the geometries to set
     * @return this
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }
}
