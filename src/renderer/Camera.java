package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.MissingResourceException;

import static primitives.Util.isZero;

/**
 * This class represents a Camera,
 * defined by a position and 3 direction vectors.
 *
 * @author pazit and lea - 26.06.23
 **/
public class Camera {
    //camera's location
    private final Point p0;

    //vRight - represent Z axis – scene depth (in the canonic orientation, Z is opposite to Vto)
    private final Vector vRight;

    //vUp - represent Y axis – scene bottom to top
    private final Vector vUp;

    //vTo - represent X axis – scene left to right
    private final Vector vTo;

    //height of the view plane
    private double height;

    //width of the view plane
    private double width;

    //object's distance from camera's center
    private double distance;

    //image writer, contain parameters: image name, Nx, Ny, width, height
    private ImageWriter imageWriter;

    //ray tracer, contain parameters: scene, image writer
    private RayTracerBase rayTracer;

    /**
     * Constructor for Camera class.
     *
     * @param p0  the camera's location
     * @param vTo the direction vector pointing right from the camera's perspective
     * @param vUp the direction vector pointing up from the camera's perspective
     */
    public Camera(Point p0, Vector vTo, Vector vUp) {
        if (!isZero(vTo.dotProduct(vUp))) {
            throw new IllegalArgumentException("vto  and vup are not orthogonal");
        }
        //initialize the camera's location
        this.p0 = p0;

        //initialize the camera's direction vectors -
        //saving only normalize vectors
        this.vUp = vUp.normalize();
        this.vTo = vTo.normalize();

        //calculate vector vRight by the 2 other normalized vectors
        vRight = this.vTo.crossProduct(this.vUp);
    }

    /**
     * getter for the camera's location
     *
     * @return the camera's location
     */
    public Point getP0() {
        return p0;
    }

    /**
     * getter for the camera's direction vector pointing
     * right from the camera's perspective
     *
     * @return the camera's direction vector pointing
     */
    public Vector getvRight() {
        return vRight;
    }

    /**
     * getter for the camera's direction vector pointing
     * up from the camera's perspective
     *
     * @return the camera's direction vector pointing
     */
    public Vector getvUp() {
        return vUp;
    }

    /**
     * getter for the camera's direction vector pointing
     * right from the camera's perspective
     *
     * @return the camera's direction vector pointing
     */
    public Vector getvTo() {
        return vTo;
    }

    /**
     * getter for the camera's height
     *
     * @return the camera's height
     */
    public double getHeight() {
        return height;
    }

    /**
     * getter for the camera's width
     *
     * @return the camera's width
     */
    public double getWidth() {
        return width;
    }

    /**
     * getter for the camera's distance
     *
     * @return the camera's distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Sets the distance between the camera and the viewPlane.
     * ------------function from the tests, not from the file instruction------------
     *
     * @param distance the distance from the camera to the view plane
     * @return the Camera object itself
     */
    public Camera setVPDistance(double distance) {
        this.distance = distance;
        return this;
    }

    /**
     * Sets the size of the view plane.
     *
     * @param width  the width of the view plane
     * @param height the height of the view plane
     * @return the Camera object itself
     */
    public Camera setVPSize(double width, double height) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * A method of creating a ray through the center of a pixel.
     *
     * @param nX amount of *columns* in view plane
     * @param nY amount of *rows* in view plane
     * @param j  represent a specific pixel's column
     * @param i  represent a specific pixel's row
     * @return ray from camera to viewPlane coordinate (i, j)
     */
    public Ray constructRay(int nX, int nY, int j, int i) {

        //view plane center Point
        Point Pc = p0.add(vTo.scale(distance));

        //pixels ratios (pixels width and height)
        double Rx = width / nX;
        double Ry = height / nY;

        //Pij point[i,j] in view-plane coordinates
        Point Pij = Pc;

        //delta values for moving on the view plane
        double Xj = (j - (nX - 1) / 2d) * Rx;
        double Yi = -(i - (nY - 1) / 2d) * Ry;

        //if not on zero coordinates add the delta distance
        // to the center of point (i,j)
        // to reach it
        if (!isZero(Xj)) {
            Pij = Pij.add(vRight.scale(Xj));
        }
        if (!isZero(Yi)) {
            Pij = Pij.add(vUp.scale(Yi));
        }

        // vector from camera's eye in the direction of point(i,j) in the view plane
        Vector Vij = Pij.subtract(p0);

        return new Ray(p0, Vij);
    }

    /**
     * Renders the image by casting rays and setting the colors of the pixels.
     *
     * @return The Camera object after rendering the image.
     * @throws MissingResourceException If any required
     * parameter is missing or has an invalid value.
     */
    public Camera renderImage() {
        // Check if any required parameter is missing or has an invalid value
        if (this.height == 0
                || this.width == 0
                || this.vTo == null
                || this.vUp == null
                || this.vRight == null
                || this.p0 == null
                || this.distance == 0
                || this.imageWriter == null
                || this.rayTracer == null) {
            throw new MissingResourceException(
                    "Missing one or more parameter values"
                    , ImageWriter.class.getName()
                    , null);
        }

        // Number of pixels in the x direction
        int nX = imageWriter.getNx();
        // Number of pixels in the y direction
        int nY = imageWriter.getNy();

        // Iterate over each pixel in the image
        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                // Cast a ray for the current pixel and set the color of the pixel
                imageWriter.writePixel(j, i, castRay(j, i));
            }
        }

        // Return the Camera object after rendering the image
        return this;
    }


    /**
     * The method prints a grid on existing image
     *
     * @param interval is the width of a square
     * @param color    is the color of the grid
     */
    void printGrid(int interval, Color color) {
        if (this.imageWriter == null) {
            throw new MissingResourceException("imageWriter is null"
                    , ImageWriter.class.getName()
                    , null);
        }

        // Number of pixels in the x direction
        int nX = imageWriter.getNx();
        // Number of pixels in the y direction
        int nY = imageWriter.getNy();

        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                //if the pixel is on the grid, paint it with the color
                if (j % interval == 0 || i % interval == 0)
                    imageWriter.writePixel(j, i, color);
            }
        }
    }

    /**
     * Function writeToImage produces unoptimized png file of the image according to
     * pixel color matrix in the directory of the project
     */
    public void writeToImage() {
        if (this.imageWriter == null) {
            throw new MissingResourceException("imageWriter is null"
                    , ImageWriter.class.getName()
                    , null);
        }
        imageWriter.writeToImage();
    }

    /**
     * -----wasn't required in the file instruction-----
     * getter for the imageWriter
     *
     * @param imageWriter the imageWriter to set
     * @return the camera itself with the new imageWriter
     */
    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    /**
     * -----wasn't required in the file instruction-----
     * getter for the rayTracer object
     *
     * @param rayTracer the rayTracer to set for the camera
     * @return the camera itself with the new rayTracer
     */
    public Camera setRayTracer(RayTracerBasic rayTracer) {
        this.rayTracer = rayTracer;
        return this;
    }

    /**
     * Casts a ray through a pixel and traces it to determine the color.
     *
     * @param j The x-coordinate of the pixel.
     * @param i The y-coordinate of the pixel.
     * @return The color obtained by tracing the ray through the pixel.
     */
    private Color castRay(int j, int i) {
        // Construct the ray for the given pixel coordinates
        Ray ray = constructRay(imageWriter.getNx(), imageWriter.getNy(), j, i);

        // Trace the ray to determine the color
        var tmp = rayTracer.traceRay(ray);

        // Return the color obtained from the ray tracing, or Color.BLACK if no color is obtained
        return tmp == null ? Color.BLACK : tmp;
    }
}
