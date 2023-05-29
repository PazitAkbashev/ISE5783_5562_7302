package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.Color;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Scene class is the basic class representing a scene
 * It is responsible for the geometries in the scene
 * holding the name of the scene, the background color of the scene,
 * the ambient light of the scene and the geometries.
 *
 * @author Pazit and Leah
 */
public class Scene {

    /**name of the scene*/
    private final String name;

    /**background color of the scene*/
    public final Color background;

    /**ambient light of the scene, default in builder is black color*/
    public final AmbientLight ambientLight;

    /**geometries of the scene*/
    public final Geometries geometries;

    /**lights of the scene*/
    public final List<LightSource> lights;

    /**
     * constructor
     * @param builder - the builder of the scene
      *
     * @return Scene
     */
    public Scene(SceneBuilder builder) {
        this.name = builder.name;
        this.background = builder.background;
        this.geometries = builder.geometries;
        this.ambientLight = builder.ambientLight;
        this.lights = builder.lights;
    }

    /**
     * inner class,
     * --------builder pattern--------
     * SceneBuilder class is the basic class representing a builder of scene matries of the scene
     *
     * @return SceneBuilder
     */
    public static class SceneBuilder {

        /**name of the scene*/
        private final String name;

        /**background color of the scene*/
        private Color background = Color.BLACK;

        /**ambient light of the scene, default is black color*/
        private AmbientLight ambientLight = AmbientLight.NONE;

        /**geometries of the scene*/
        private Geometries geometries = new Geometries();

        /**lights of the scene*/
        private List<LightSource> lights = new LinkedList<>();
        /**
         * constructor
         * @param name - the name of the scene
         */
        public SceneBuilder(String name) {
            this.name = name;
        }

        /**setters for the fields*/
        public SceneBuilder setBackground(Color background) {
            this.background = background;
            return this;
        }

        public SceneBuilder setAmbientLight(AmbientLight ambientLight) {
            this.ambientLight = ambientLight;
            return this;
        }

        public SceneBuilder setGeometries(Geometries geometries) {
            this.geometries = geometries;
            return this;
        }

        public SceneBuilder setLights(List<LightSource> lights) {
            Collections.copy(this.lights, lights);
           // this.lights = lights;
            return this;
        }

        /**build method*/
        public Scene build() {
            return new Scene(this);
        }
    }
}
