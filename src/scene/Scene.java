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
 * @author Pazit and Leah - 26.06.23
 */
public class Scene {

    //background color of the scene
    public final Color background;

    //ambient light of the scene, default in builder is black color
    private  AmbientLight ambientLight;

    //geometries of the scene
    public final Geometries geometries;

    //lights of the scene
    public final List<LightSource> lights;

    //name of the scene
    private final String name;

    /**
     * constructor for Scene class
     *
     * @param builder - the builder of the scene
     * @return Scene
     */
    public Scene(SceneBuilder builder) {
        //initialize the name of the scene
        this.name = builder.name;

        //initialize the background color of the scene
        this.background = builder.background;

        //initialize the geometries of the scene
        this.geometries = builder.geometries;

        //initialize the ambientLight of the scene
        this.ambientLight = builder.ambientLight;

        //initialize the lights of the scene
        this.lights = builder.lights;
    }

    /**
     * getter for the ambientLight field
     *
     * @return the ambientLight of the scene
     */
    public AmbientLight getAmbientLight() {
        return ambientLight;
    }

    /**
     * setter for the ambientLight field
     *
     * @param ambientLight - the ambientLight of the scene
     * @return the scene itself
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * inner class,
     * --------builder pattern--------
     * SceneBuilder class is the basic class representing a builder of scene matries of the scene
     *
     * @return SceneBuilder
     */
    public static class SceneBuilder {

        //name of the scene
        private final String name;

        //background color of the scene
        private Color background = Color.BLACK;

        //ambient light of the scene, default is black color
        private AmbientLight ambientLight = AmbientLight.NONE;

        //geometries of the scene
        private Geometries geometries = new Geometries();

        //lights of the scene
        private List<LightSource> lights = new LinkedList<>();

        /**
         * constructor for SceneBuilder class
         *
         * @param name - the name of the scene
         */
        public SceneBuilder(String name) {
            this.name = name;
        }

        /**
         * setter for the background field
         *
         * @param background - the background color of the scene
         * @return the scene itself
         */
        public SceneBuilder setBackground(Color background) {
            this.background = background;
            return this;
        }

        /**
         * setter for the ambientLight field
         *
         * @param ambientLight - the ambientLight of the scene
         * @return the scene itself
         */
        public SceneBuilder setAmbientLight(AmbientLight ambientLight) {
            this.ambientLight = ambientLight;
            return this;
        }

        /**
         * setter for the geometries field
         *
         * @param geometries - the geometries of the scene
         * @return the scene itself
         */
        public SceneBuilder setGeometries(Geometries geometries) {
            this.geometries = geometries;
            return this;
        }

        /**
         * setter for the lights field
         *
         * @param lights - the lights of the scene
         * @return the scene itself
         */
        public SceneBuilder setLights(List<LightSource> lights) {
            Collections.copy(this.lights, lights);
            // this.lights = lights;
            return this;
        }

        //the build method
        public Scene build() {
            return new Scene(this);
        }
    }
}
