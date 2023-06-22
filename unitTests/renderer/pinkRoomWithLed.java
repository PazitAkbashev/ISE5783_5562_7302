package renderer;
//
//import org.junit.jupiter.api.Test;
//import lighting.*;
//import primitives.*;
//import geometries.*;
//import scene.Scene;
//
//import static java.awt.Color.*;

import geometries.*;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import scene.Scene;

import static java.awt.Color.RED;
import static java.awt.Color.YELLOW;

public class pinkRoomWithLed {

    /**
     * The final picture of miniProject2
     */
    @Test
    public void miniProject1() {

        Scene scene1 = new Scene.SceneBuilder("Test scene")
                .build();

        Camera camera3 = new Camera(new Point(-40, 50, 160),
                new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(200, 200) //
                .setVPDistance(120);

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

//        Geometry Sphere = new Sphere(new Point(-30, 20, 50), 25)
//                .setEmission(new Color(RED))
//                .setMaterial(new Material().setKd(0.5).setKt(0.2).setShininess(100));

        Geometry Sphere1 = new Sphere(new Point(-30, 20, 50), 25)
                .setEmission(new Color(RED))
                .setMaterial(new Material().setKd(0.5).setKt(0.2).setShininess(100));

        //rectengle0
        Geometry door0 =
                new Polygon(
                        new Point(-75, 0, 120),
                        new Point(-60, 0, 90),
                        new Point(-60, 80, 90),
                        new Point(-75, 80, 120))
                        .setMaterial(new Material()).setEmission(new Color(255,26,140));

        //rectangle5
        Geometry door1 = new Polygon(
                        new Point(10, 80, 110),
                        new Point(-60, 80, 90),
                        new Point(-60, 65, 90),
                        new Point(10, 65, 110))
                        .setMaterial(new Material()).setEmission(new Color(255,26,140));
        //rectangle1
        Geometry door2 = new Polygon(
                        new Point(-40, 0, 96),
                        new Point(-60, 0, 90),
                        new Point(-60, 65, 90),
                        new Point(-40, 65, 96)
                ).setMaterial(new Material()).setEmission(new Color(255,26,140));

        //rectangle2
        Geometry door3 = new Polygon(
                        new Point(-40, 65, 96),
                        new Point(-35, 65, 80),
                        new Point(-35, 0, 80),
                        new Point(-40, 0, 96))
                        .setMaterial(new Material()).setEmission(new Color(255,26,140));

        //rectangle 3
        Geometry door4 = new Polygon(
                        new Point(-10, 0, 105),
                        new Point(30, 0, 120),
                        new Point(30, 65, 120),
                        new Point(-10, 65, 105))
                        .setMaterial(new Material()).setEmission(new Color(255,26,140));

        //rectangle6
        Geometry door5 = new Polygon(
                        new Point(20, 68.9, 80),//right
                        new Point(20, 68.9, 96),//right
                        new Point(-40, 65, 96),//left
                        new Point(-40, 65, 80)//left
                ).setMaterial(new Material()).setEmission(new Color(255,26,140));


        double radius = 1;
        Ray axisray = new Ray(new Point(0, 79, -20), new Vector(1, 0, 0));
        double length = 0.1;

        Geometry led = new Cylinder( radius, axisray, length)
                .setEmission(new Color(RED))
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKt(0.9));


        //spot lights:
        {// right
            scene1.lights.add(new SpotLight(new Color(YELLOW).scale(.8),
                    new Point(20, 79, -15),
                    new Vector(-100, -40, -100)));
        }

        {//middle
            scene1.lights.add(new SpotLight(new Color(YELLOW).scale(.8),
                    new Point(-32, 79, -15),
                    new Vector(0, 0, -100)));
        }

        {//left
            scene1.lights.add(new SpotLight(new Color(YELLOW).scale(.8),
                    new Point(-85, 79, -15),
                    new Vector(100, -40, -100)));
        }

        {//left - middle
            scene1.lights.add(new SpotLight(new Color(YELLOW).scale(1.5),
                    new Point(-57, 79, -15),
                    new Vector(40, 60, -100)));
        }

        {//right - middle
            scene1.lights.add(new SpotLight(new Color(YELLOW).scale(1.5),
                    new Point(-5, 79, -15),
                    new Vector(40, 60, -100)));
        }

       scene1.geometries.add(roof, wallRight, wallLeft, wallFront, floor, led, Sphere1, door0, door1, door2, door3, door4, door5);

        ImageWriter imageWriter = new ImageWriter("pinkRoom", 1000, 1000);
        camera3.setImageWriter(imageWriter)
                .setRayTracer(new RayTracerBasic(scene1)).renderImage().writeToImage();
    }
}
