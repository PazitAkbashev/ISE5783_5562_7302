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
    private final String name;
    private final Color background;
    //private AmbientLight ambientLight = AmbientLight.NONE;

    private AmbientLight ambientLight;
    private final Geometries geometries;

    public Scene(SceneBuilder builder) {
        this.name = builder.name;
        this.background = builder.background;
        this.geometries = builder.geometries;
        this.ambientLight = builder.ambientLight;
    }

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

    public static class SceneBuilder {

        private final String name;
        private Color background = Color.BLACK;
        private AmbientLight ambientLight = new AmbientLight();
        private Geometries geometries = new Geometries();

        public SceneBuilder(String name) {
            this.name = name;
        }

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

        public Scene build() {
            //            validateObject(scene);
            return new Scene(this);
        }

    }
