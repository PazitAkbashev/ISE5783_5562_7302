package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

/**
 This class represents a Camera, defined by a position and 3 direction vectors.

 - The Camera class has methods for ********************** ******************
 ****************************************************************************
 * @author pazit and lea
 */
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

    public Point getP0() {return p0;}

    public Vector getvRight() {return vRight;}

    public Vector getvUp() {return vUp;}

    public Vector getvTo() {return vTo;}

    public double getHeight() {return height;}

    public double getWidth() {return width;}

    public double getDistance() {return distance;}

    /**
     * constructor
     * @param p0
     * @param vUp
     * @param vTo
     */
    public Camera(Point p0, Vector vUp, Vector vTo) {
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
     *
     * @param width
     * @param height
     * @return
     */
    public Camera setVPSize(double width, double height){
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * *****    implement after writing its tests     ***********
     A method of creating a beam through the center of a pixel.
     * @param nX amount of *columns* in view plane
     * @param nY amount of *rows* in view plane
     * @param j represent a specific pixel's column
     * @param i represent a specific pixel's row
     * @return
     */
    public Ray constructRay(int nX, int nY, int j, int i){
        // Ratio (pixel width & height)
        double rY = (double) this.height / nY;
        double rX = (double) this.height / nX;

        // Image center
        Point Pc = this.p0.add(this.vTo.scale(this.distance));

        // Pixel[i,j] center
        Point Pij = Pc;

        double yI = -(i -((nY - 1)/2d)) * rY;
        double xJ = (j -((nX - 1)/2d)) * rX;;

        // move to middle of pixel i,j
        if (!isZero(xJ))
            Pij = Pij.add (this.vRight.scale(xJ));
        if (!isZero(yI))
            Pij = Pij.add(this.vUp.scale(yI));

        // return ray from camera to viewPlane coordinate (i, j)
        return new Ray(this.p0, Pij.subtract(this.p0));
    }
}
