package renderer;

import lighting.AmbientLight;
//import lighting.PointLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import geometries.*;
import scene.Scene;

import static java.awt.Color.*;

//the original class
public class miniProject {

    /**
     * The final picture of miniProject2
     */
    @Test
    public void miniProject1() {
        Scene scene1 = new Scene.SceneBuilder("Test scene").build();
        Camera camera3 = new Camera(new Point(-40, 50, 160),
                new Vector(0, 0, -1), new Vector(0, -1, 0)) //
                .setVPSize(200, 200) //
                .setVPDistance(110);

        Geometry box1 = new Polygon(new Point(-28, 30, 65), new Point(-14, 30, 65),
                new Point(-14, 37, 55), new Point(-28, 37, 55))
                .setEmission(new Color(pink).scale(0.3))
                .setMaterial(new Material().setKd(0.5).setKs(0.9).setShininess(100));

        Geometry backBox = new Polygon(new Point(-28, 21.1, 65), new Point(-14, 21.1, 65),
                new Point(-14, 30, 65), new Point(-28, 30, 65))
                .setEmission(new Color(yellow).scale(0.5))
                .setMaterial(new Material().setKd(0.5).setKs(0.9).setShininess(100));

        Geometry frontBox = new Polygon(new Point(-14, 21.1, 77), new Point(-28, 21.1, 77),
                new Point(-28, 30, 77), new Point(-14, 30, 77))
                .setEmission(new Color(java.awt.Color.GREEN).scale(0.5))
                .setMaterial(new Material().setKd(0.5).setKs(0.9).setShininess(100));

        Geometry rightBox = new Polygon(new Point(-14, 21.1, 65), new Point(-14, 21.1, 77),
                new Point(-14, 30, 77), new Point(-14, 30, 65))
                .setEmission(new Color(pink).scale(0.5))
                .setMaterial(new Material().setKd(0.5).setKs(0.9).setShininess(100));

        Geometry leftBox = new Polygon(new Point(-28, 21.1, 65), new Point(-28, 21.1, 77),
                new Point(-28, 30, 77), new Point(-28, 30, 65))
                .setEmission(new Color(java.awt.Color.GREEN).scale(0.3))
                .setMaterial(new Material().setKd(0.5).setKs(0.9).setShininess(100));

        Geometry box = new Polygon(new Point(-14, 21.1, 65), new Point(-14, 21.1, 77),
                new Point(-28, 21.1, 77), new Point(-28, 21.1, 65))
                .setEmission(new Color(java.awt.Color.GREEN).scale(0.3))
                .setMaterial(new Material().setKd(0.5).setKs(0.9).setShininess(100));


        Geometry upMirror = new Polygon(new Point(-80, 65, -19.8), new Point(17, 65, -19.8),
                new Point(22, 70, -19.8), new Point(-85, 70, -19.8))
                .setEmission(new Color(PINK))
                .setMaterial(new Material().setKd(0.1).setKs(0.7).setShininess(100));

        Geometry downMirror = new Polygon(new Point(-80, 15, -19.8), new Point(17, 15, -19.8),
                new Point(22, 10, -19.8), new Point(-85, 10, -19.8))
                .setEmission(new Color(PINK))
                .setMaterial(new Material().setKd(0.1).setKs(0.7).setShininess(100));

        Geometry leftMirror = new Polygon(new Point(-80, 15, -19.8), new Point(-80, 65, -19.8),
                new Point(-85, 70, -19.8), new Point(-85, 10, -19.8))
                .setEmission(new Color(PINK))
                .setMaterial(new Material().setKd(0.1).setKs(0.7).setShininess(100));

        Geometry rightMirror = new Polygon(new Point(17, 15, -19.8), new Point(22, 10, -19.8),
                new Point(22, 70, -19.8), new Point(17, 65, -19.8))
                .setEmission(new Color(PINK))
                .setMaterial(new Material().setKd(0.1).setKs(0.7).setShininess(100));

        Geometry mirror = new Polygon(new Point(-80, 65, -19.8), new Point(-80, 15, -19.8),
                new Point(17, 15, -19.8), new Point(17, 65, -19.8))
                .setMaterial(new Material().setKd(0.1).setKs(0.7).setShininess(100).setKr(new Double3(1)));

//        Geometry triangleUpMirror1 = new Triangle((new Point(-82, 69.5, -19.7)), new Point(-72, 69.5, -19.7),
//                new Point(-77, 65.5, -19.7))
//                .setEmission(new Color(WHITE))
//                .setMaterial(new Material().setKd(0.1).setKs(0.7).setShininess(100));
//
//        Geometry triangleUpMirror2 = new Triangle((new Point(-69, 69.5, -19.7)), new Point(-59, 69.5, -19.7),
//                new Point(-64, 65.5, -19.7))
//                .setEmission(new Color(WHITE))
//                .setMaterial(new Material().setKd(0.1).setKs(0.7).setShininess(100));
//
//        Geometry triangleUpMirror3 = new Triangle((new Point(-56, 69.5, -19.7)), new Point(-46, 69.5, -19.7),
//                new Point(-51, 65.5, -19.7))
//                .setEmission(new Color(WHITE))
//                .setMaterial(new Material().setKd(0.1).setKs(0.7).setShininess(100));
//
//        Geometry triangleUpMirror4 = new Triangle((new Point(-43, 69.5, -19.7)), new Point(-33, 69.5, -19.7),
//                new Point(-38, 65.5, -19.7))
//                .setEmission(new Color(WHITE))
//                .setMaterial(new Material().setKd(0.1).setKs(0.7).setShininess(100));
//
//        Geometry triangleUpMirror5 = new Triangle((new Point(-30, 69.5, -19.7)), new Point(-20, 69.5, -19.7),
//                new Point(-25, 65.5, -19.7))
//                .setEmission(new Color(WHITE))
//                .setMaterial(new Material().setKd(0.1).setKs(0.7).setShininess(100));
//
//        Geometry triangleUpMirror6 = new Triangle((new Point(-17, 69.5, -19.7)), new Point(-7, 69.5, -19.7),
//                new Point(-12, 65.5, -19.7))
//                .setEmission(new Color(WHITE))
//                .setMaterial(new Material().setKd(0.1).setKs(0.7).setShininess(100));
//
//        Geometry triangleUpMirror7 = new Triangle((new Point(-4, 69.5, -19.7)), new Point(6, 69.5, -19.7),
//                new Point(1, 65.5, -19.7))
//                .setEmission(new Color(WHITE))
//                .setMaterial(new Material().setKd(0.1).setKs(0.7).setShininess(100));
//
//        Geometry triangleUpMirror8 = new Triangle((new Point(9, 69.5, -19.7)), new Point(19, 69.5, -19.7),
//                new Point(14, 65.5, -19.7))
//                .setEmission(new Color(WHITE))
//                .setMaterial(new Material().setKd(0.1).setKs(0.7).setShininess(100));
//
//        Geometry triangleDownMirror1 = new Triangle((new Point(-82, 10.5, -19.7)), new Point(-72, 10.5, -19.7),
//                new Point(-77, 14.5, -19.7))
//                .setEmission(new Color(WHITE))
//                .setMaterial(new Material().setKd(0.1).setKs(0.7).setShininess(100));
//
//        Geometry triangleDownMirror2 = new Triangle((new Point(-69, 10.5, -19.7)), new Point(-59, 10.5, -19.7),
//                new Point(-64, 14.5, -19.7))
//                .setEmission(new Color(WHITE))
//                .setMaterial(new Material().setKd(0.1).setKs(0.7).setShininess(100));
//
//        Geometry triangleDownMirror3 = new Triangle((new Point(-56, 10.5, -19.7)), new Point(-46, 10.5, -19.7),
//                new Point(-51, 14.5, -19.7))
//                .setEmission(new Color(WHITE))
//                .setMaterial(new Material().setKd(0.1).setKs(0.7).setShininess(100));
//
//        Geometry triangleDownMirror4 = new Triangle((new Point(-43, 10.5, -19.7)), new Point(-33, 10.5, -19.7),
//                new Point(-38, 14.5, -19.7))
//                .setEmission(new Color(WHITE))
//                .setMaterial(new Material().setKd(0.1).setKs(0.7).setShininess(100));
//
//        Geometry triangleDownMirror5 = new Triangle((new Point(-30, 10.5, -19.7)), new Point(-20, 10.5, -19.7),
//                new Point(-25, 14.5, -19.7))
//                .setEmission(new Color(WHITE))
//                .setMaterial(new Material().setKd(0.1).setKs(0.7).setShininess(100));
//
//        Geometry triangleDownMirror6 = new Triangle((new Point(-17, 10.5, -19.7)), new Point(-7, 10.5, -19.7),
//                new Point(-12, 14.5, -19.7))
//                .setEmission(new Color(WHITE))
//                .setMaterial(new Material().setKd(0.1).setKs(0.7).setShininess(100));
//
//        Geometry triangleDownMirror7 = new Triangle((new Point(-4, 10.5, -19.7)), new Point(6, 10.5, -19.7),
//                new Point(1, 14.5, -19.7))
//                .setEmission(new Color(WHITE))
//                .setMaterial(new Material().setKd(0.1).setKs(0.7).setShininess(100));
//
//        Geometry triangleDownMirror8 = new Triangle((new Point(9, 10.5, -19.7)), new Point(19, 10.5, -19.7),
//                new Point(14, 14.5, -19.7))
//                .setEmission(new Color(WHITE))
//                .setMaterial(new Material().setKd(0.1).setKs(0.7).setShininess(100));
//
//        Geometry downMirror1 = new Triangle((new Point(-56, 30, 71)), new Point(-62, 21, 66),
//                new Point(-50, 21, 66))
//                .setMaterial(new Material().setKd(0.3).setKs(0.7).setShininess(100).setKt(new Double3(0.8)));
//
//        Geometry leftMirror1 = new Triangle((new Point(-56, 30, 71)), new Point(-62, 21, 66),
//                new Point(-50, 21, 66))
//                .setMaterial(new Material().setKd(0.3).setKs(0.7).setShininess(100).setKt(new Double3(0.8)));
//
//        Geometry rightMirror1 = new Triangle((new Point(-56, 30, 71)), new Point(-62, 21, 66),
//                new Point(-50, 21, 66))
//                .setMaterial(new Material().setKd(0.3).setKs(0.7).setShininess(100).setKt(new Double3(0.8)));

        Geometry upTable = new Polygon(new Point(-7, 21, 58), new Point(-7, 21, 84),
                new Point(-35, 21, 84), new Point(-35, 21, 58))
                .setEmission(new Color(java.awt.Color.RED).scale(0.5))
                .setMaterial(new Material().setKd(0.5).setKs(0.9).setShininess(100));

        Geometry downTable = new Polygon(new Point(-7, 0, 58), new Point(-7, 0, 84),
                new Point(-35, 0, 84), new Point(-35, 0, 58))
                .setEmission(new Color(java.awt.Color.RED).scale(0.5))
                .setMaterial(new Material().setKd(0.5).setKs(0.9).setShininess(100));

        Geometry middleTable = new Polygon(new Point(-35, 21, 84), new Point(-35, 21, 58),
                new Point(-7, 0, 58), new Point(-7, 0, 84))
                .setEmission(new Color(java.awt.Color.RED).scale(0.5))
                .setMaterial(new Material().setKd(0.5).setKs(0.9).setShininess(100));

        Geometry floor = new Plane(new Point(0, 0, 0), new Vector(0, 1, 0))
                .setEmission(new Color(java.awt.Color.GRAY))
                .setMaterial(new Material().setKd(0.4).setKs(0.05).setShininess(100));
        Geometry roof = new Plane(new Point(0, 80, 0), new Vector(0, 1, 0))
                .setEmission(new Color(java.awt.Color.WHITE).scale(0.3))
                .setMaterial(new Material().setKd(0.7));
        Geometry wallRight = new Plane(new Point(35, 0, 0), new Vector(1, 0, 0))
                .setEmission(new Color(java.awt.Color.LIGHT_GRAY))
                .setMaterial(new Material().setKd(0.5));
        Geometry wallLeft = new Plane(new Point(-100, 0, 0), new Vector(1, 0, 0))
                .setEmission(new Color(java.awt.Color.LIGHT_GRAY))
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100));
        Geometry wallBehind = new Plane(new Point(0, 0, 200), new Vector(0, 0, 1))
                .setEmission(new Color(java.awt.Color.LIGHT_GRAY))
                .setMaterial(new Material().setKd(0.5));
        Geometry wallFront = new Plane(new Point(0, 0, -20), new Vector(0, 0, 1))
                .setEmission(new Color(java.awt.Color.LIGHT_GRAY))
                .setMaterial(new Material().setKd(0.5));

        Geometry sphere1 = new Sphere( new Point(-37, 78, 75), 4)
                .setEmission(new Color(java.awt.Color.RED).scale(0.5))
                .setMaterial(new Material().setKd(0.5).setKs(0.9).setShininess(100));
        Geometry sphere2 = new Sphere(new Point(-17, 78, 75), 4)
                .setEmission(new Color(java.awt.Color.ORANGE).scale(0.5))
                .setMaterial(new Material().setKd(0.5).setKs(0.9).setShininess(100));
        Geometry sphere3 = new Sphere(new Point(3, 78, 75), 4)
                .setEmission(new Color(java.awt.Color.GREEN).scale(0.3))
                .setMaterial(new Material().setKd(0.5).setKs(0.9).setShininess(100));

//        scene1.geometries.add(downTable, upTable, middleTable, box, leftBox, rightBox, backBox, frontBox,
//                floor, wallBehind, roof, box1, upMirror, leftMirror, rightMirror, downMirror, triangleUpMirror1,
//                triangleUpMirror2, triangleUpMirror3, triangleUpMirror4, triangleUpMirror5, triangleUpMirror6,
//                triangleUpMirror7, triangleUpMirror8, triangleDownMirror1, triangleDownMirror2, triangleDownMirror3,
//                triangleDownMirror4, triangleDownMirror5, triangleDownMirror6, triangleDownMirror7,
//                triangleDownMirror8
//                , wallRight, wallLeft, wallFront, mirror, sphere1, sphere2, sphere3);

        scene1.geometries.add(downTable, upTable, middleTable, box, leftBox, rightBox, backBox, frontBox,
                floor, wallBehind, roof, box1, upMirror, leftMirror, rightMirror, downMirror
                , wallRight, wallLeft, wallFront, mirror, sphere1, sphere2, sphere3);

        scene1.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.0015)));
        scene1.lights.add(new SpotLight
                (new Color(java.awt.Color.CYAN).scale(0.8),
                new Point(-17, 55, 75),
                new Vector(0, 1, 0)));
//        scene1.lights.add(new PointLight(new Color(YELLOW)
//                .add(new Color(YELLOW)).scale(0.2), new Point(0, 50, 40),3));
        scene1.lights.add(new SpotLight(new Color(java.awt.Color.orange), new Point(-56, 50, 71),new Vector(0,-1,0)));
        scene1.lights.add(new SpotLight(new Color(java.awt.Color.CYAN).scale(0.8), new Point(-5, 55, 75),new Vector(1.5,-1,0)));

        ImageWriter imageWriter = new ImageWriter("finalPicture", 1000, 1000);
        camera3.setImageWriter(imageWriter)
                .setRayTracer(new RayTracerBasic(scene1)).renderImage().writeToImage();
                        //.setMIN_SHADOW_SAMPLES(100)
                       // .setMULTISAMPLING())
               // .setMultithreading(3)
                //.setAntiAliasing(17)
//                .renderImage()
//                .writeToImage();
    }
}