package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.MissingResourceException;

import static primitives.Util.isZero;

    /**
      This class represents a Camera, defined by a position and 3 direction vectors.
      @author pazit and lea
     **/
public class Camera {
    //camera's location
    private Point p0;

    //vRight - represent Z axis – scene depth (in the canonic orientation, Z is opposite to Vto)
    private Vector vRight;

    //vUp - represent Y axis – scene bottom to top
    private Vector vUp;

    //vTo - represent X axis – scene left to right
    private Vector vTo;

    //height of the view plane
    private double height;

    //width of the view plane
    private double width;

    //object's distance from camera's center
    private double distance;

    private ImageWriter imageWriter;

    private RayTracerBase rayTracerBase;

    public Point getP0() {return p0;}

    public Vector getvRight() {return vRight;}

    public Vector getvUp() {return vUp;}

    public Vector getvTo() {return vTo;}

    public double getHeight() {return height;}

    public double getWidth() {return width;}

    public double getDistance() {return distance;}

    /**
     * Constructor for Camera class.
     *
     * @param p0  the camera's location
     * @param vTo the direction vector pointing right from the camera's perspective
     * @param vUp the direction vector pointing up from the camera's perspective
     */

        public Camera(Point p0, Vector vTo, Vector vUp) {
        if(!isZero(vTo.dotProduct(vUp))){
            throw new IllegalArgumentException("vto  and vup are not orthogonal");
        }
        this.p0 = p0;

        //saving only normalize vectors
        this.vUp = vUp.normalize();
        this.vTo = vTo.normalize();

        //calculate vector vRight by the 2 other normalized vectors
        vRight = this.vTo.crossProduct(this.vUp);
    }

    /**
     * Sets the distance between the camera and the viewPlane.
     * ------------function from the tests, not from the file instruction------------
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
    public Camera setVPSize(double width, double height){
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     A method of creating a ray through the center of a pixel.
     * @param nX amount of *columns* in view plane
     * @param nY amount of *rows* in view plane
     * @param j represent a specific pixel's column
     * @param i represent a specific pixel's row
     * @return ray from camera to viewPlane coordinate (i, j)
     */
    public Ray constructRay(int nX, int nY, int j, int i){

        //view plane center Point
        Point Pc = p0.add(vTo.scale(distance));

        //pixels ratios
        double Rx = width / nX;
        double Ry = height / nY;

        //Pij point[i,j] in view-plane coordinates
        Point Pij = Pc;

        //delta values for moving on the view=plane
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

        // vector from camera's eye in the direction of point(i,j) in the viewplane
        Vector Vij = Pij.subtract(p0);

        return new Ray(p0, Vij);
    }

        /**
         * checking if all the parameters are initialized
         */
    void renderImage() {
        if(this.height == 0 || this.width == 0 || this.vTo == null
                || this.vUp == null || this.vRight == null || this.p0 == null || this.distance == 0){
            throw new MissingResourceException("missing one parameters value or more", null, null);
        }
        throw new UnsupportedOperationException("Not implemented yet");
    }

       void printGrid(int interval, Color color){

       }

}
