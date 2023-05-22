package renderer;

import static java.awt.Color.YELLOW;

import com.google.gson.Gson;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import primitives.*;
import scene.Scene;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Test rendering a basic image
 *
 * @author Dan
 */
public class RenderTests {

    /**
     * Produce a scene with basic 3D model and render it into a png image with a
     * grid
     */
    @Test
    public void basicRenderTwoColorTest() {
        Scene scene = new Scene.SceneBuilder("Test scene")
                //background is green
                .setBackground(new Color(75, 127, 90))
                //AmbientLight is pink
                .setAmbientLight(new AmbientLight(
                        new Color(255, 191, 191),
                        new Double3(1, 1, 1))) //
                .build();


        scene.getGeometries().add(new Sphere(new Point(0, 0, -100), 50d),
                new Triangle(new Point(-100, 0, -100), new Point(0, 100, -100),
                        new Point(-100, 100, -100)), // up left

                new Triangle(new Point(-100, 0, -100), new Point(0, -100, -100),
                        new Point(-100, -100, -100)), // down left

               new Triangle(new Point(100, 0, -100), new Point(0, -100, -100),
                       new Point(100, -100, -100))); // down right

        // right
        Camera camera = new Camera(Point.ZERO, new Vector(0, 0, -1),
                new Vector(0, 1, 0)) //
                .setVPDistance(100) //
                .setVPSize(500, 500) //
                .setImageWriter(new ImageWriter("base render test 2", 1000, 1000))
                .setRayTracer(new RayTracerBasic(scene));

        camera.renderImage();
        camera.printGrid(100, new Color(YELLOW));
        camera.writeToImage();
    }


    //--------only if doing the bonus --------//
    /** Test for Json based scene - for bonus */
    @Test
    @Disabled
    public void basicRenderJson() {
        Gson gson = new Gson();
        try {
            FileReader reader = new FileReader("basicRenderTwoColors.json");
            Scene jsonScene =gson.fromJson(reader, Scene.class);

            Camera camera = new Camera(Point.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0))  //
                    .setVPDistance(100)                                                                //
                    .setVPSize(500, 500).setImageWriter(new ImageWriter
                            ("Json render test", 1000, 1000))
                    .setRayTracer(new RayTracerBasic(jsonScene));
            camera.renderImage();
            camera.printGrid(100, new Color(YELLOW));
            camera.writeToImage();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }




        /**
         * Eliezer's test
         ****** FOR STAGE 6*******
         * Produce a scene with basic 3D model and render it into a png image with a grid
         */
//    @Test
//    void testRedtriangle() {
//        Scene scene = new Scene.SceneBuilder("Test scene")//
//                .setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.2)))
//                .build(); //
//
//        scene.getGeometries().add( //
//                new Triangle(
//                        new Point(-100, 0, -100),
//                        new Point(0, -100, -100),
//                        new Point(-100, -100, -100))
//                        .setEmission(new Color(RED)));
//
//        Camera camera = new Camera(Point.ZERO, new Vector(0, 0, -1),
//                new Vector(0, 1, 0)) //
//                .setVPDistance(100) //
//                .setVPSize(500, 500) //
//                .setImageWriter(new ImageWriter("red triangle render test",
//                        1000, 1000))
//                .setRayTracer(new RayTracerBasic(scene));
//
//        camera.renderImage();
//        camera.printGrid(100, new Color(WHITE));
//        camera.writeToImage();
//    }


    // For stage 6 - please disregard in stage 5
  //  /** Produce a scene with basic 3D model - including individual lights of the
   //  * bodies and render it into a png image with a grid */
    // @Test
    // public void basicRenderMultiColorTest() {
    // Scene scene = new Scene("Test scene")//
    // .setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.2))); //
    //
    // scene.geometries.add( //
    // new Sphere(new Point(0, 0, -100), 50),
    // // up left
    // new Triangle(new Point(-100, 0, -100), new Point(0, 100, -100), new
    // Point(-100, 100, -100))
    // .setEmission(new Color(GREEN)),
    // // down left
    // new Triangle(new Point(-100, 0, -100), new Point(0, -100, -100), new
    // Point(-100, -100, -100))
    // .setEmission(new Color(RED)),
    // // down right
    // new Triangle(new Point(100, 0, -100), new Point(0, -100, -100), new
    // Point(100, -100, -100))
    // .setEmission(new Color(BLUE)));
    //
    // Camera camera = new Camera(Point.ZERO, new Vector(0, 0, -1), new Vector(0, 1,
    // 0)) //
    // .setVPDistance(100) //
    // .setVPSize(500, 500) //
    // .setImageWriter(new ImageWriter("color render test", 1000, 1000))
    // .setRayTracer(new RayTracerBasic(scene));
    //
    // camera.renderImage();
    // camera.printGrid(100, new Color(WHITE));
    // camera.writeToImage();
    // }




//        Scene  scene  = new Scene("Json Test scene");
//        // enter Json file name and parse from Json file into scene object
//        // using the code you added in appropriate packages
//        // ...
//        // NB: unit tests is not the correct place to put Json parsing code
//
//        Camera camera = new Camera(Point.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0))     //
//                .setVPDistance(100)                                                                //
//                .setVPSize(500, 500).setImageWriter(new ImageWriter("xml render test", 1000, 1000))
//                .setRayTracer(new RayTracerBasic(scene));
//        camera.renderImage();
//        camera.printGrid(100, new Color(YELLOW));
//        camera.writeToImage();
    }
}
