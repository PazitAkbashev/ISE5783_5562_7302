package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import primitives.Color;

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
    private final Color background;

    /**ambient light of the scene, default in builder is black color*/
    private AmbientLight ambientLight;

    /**geometries of the scene*/
    private final Geometries geometries;

    /**
     * constructor
     * @param builder - the builder of the scene
     * @param name - the name of the scene
     * @param background - the background color of the scene
     * @param geometries - the geometries of the scene
     * @param ambientLight - the ambient light of the scene
     *
     * @return Scene
     */
    public Scene(SceneBuilder builder) {
        this.name = builder.name;
        this.background = builder.background;
        this.geometries = builder.geometries;
        this.ambientLight = builder.ambientLight;
    }

    /**getters for the fields*/
    public String getName() {
        return name;
    }

    public Color getBackground() {
        return background;
    }

    public AmbientLight getAmbientLight() {
        return ambientLight;
    }

    public Geometries getGeometries() {
        return geometries;
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

        /**build method*/
        public Scene build() {
            return new Scene(this);
        }
    }
}