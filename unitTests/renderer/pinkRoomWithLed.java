package renderer;

import org.junit.jupiter.api.Test;
import lighting.*;
import primitives.*;
import geometries.*;
import scene.Scene;

import static java.awt.Color.*;

public class pinkRoomWithLed {

    /**
     * The final picture of miniProject2
     */
    @Test
    public void miniProject1() {
        Scene scene1 = new Scene.SceneBuilder("Test scene").build();

        Camera camera3 = new Camera(new Point(-30, 30, 160),
                new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(200, 200) //
                .setVPDistance(140);

        Geometry floor = new Plane(new Point(0, 0, 0), new Vector(0, 1, 0))
                .setEmission(new Color(255, 153, 185))
                .setMaterial(new Material().setKd(0.5));

        Geometry roof = new Plane(new Point(0, 80, 0), new Vector(0, 1, 0)) //++
                .setEmission(new Color(255, 183, 185))
                .setMaterial(new Material().setKd(0.5));


        Geometry wallRight = new Plane(new Point(35, 0, 0), new Vector(1, 0, 0))
                .setEmission(new Color(255, 128, 168))
                .setMaterial(new Material().setKd(0.5));


        Geometry wallLeft = new Plane(new Point(-100, 0, 0), new Vector(1, 0, 0)) //++
                .setEmission(new Color(255, 128, 168))
                .setMaterial(new Material().setKd(0.5));

        Geometry wallFront = new Plane(new Point(0, 0, -20), new Vector(0, 0, 1)) //++
                .setEmission(new Color(255, 128, 168))
                .setMaterial(new Material().setKd(0.5));

        //double radius = 2;
        Ray axisray = new Ray(new Point(32, 79, 0), new Vector(0, 0, 1));
        //double length = 1;

        Geometry led = new Cylinder( 1, axisray, 0.1)
                .setEmission(new Color(RED))
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKt(0.9));

        //points lights
//        for(int i = -15; i < 100; i+=5)
//        {
//            scene1.lights.add(new PointLight(new Color(YELLOW),
//                    new Point(34, 73, i),
//                    1,
//                    0.01111,
//                    0.0111));
//        }

        //spot light
//        {
//            scene1.lights.add(new SpotLight(new Color(YELLOW),
//                    new Point(34, 73, -10),
//                    new Vector(-100, 0, -15)));
//        }

        {
            scene1.lights.add(new SpotLight(new Color(YELLOW).scale(1.5),
                    new Point(34, 20, 40),
                    new Vector(-34, -70, 30)));
        }
        scene1.geometries.add(roof, wallRight, wallLeft, wallFront, floor, led);


        ImageWriter imageWriter = new ImageWriter("pinkRoom", 1000, 1000);
        camera3.setImageWriter(imageWriter)
                .setRayTracer(new RayTracerBasic(scene1)).renderImage().writeToImage();
    }
}
