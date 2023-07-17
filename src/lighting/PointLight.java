package lighting;

import primitives.Color;
import primitives.Double3;
import primitives.Point;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.sqrt;
import static primitives.Util.isZero;

/**
 * PointLight class is the class representing a point light
 * extends Light implements LightSource
 * in point light there is attenuation with distance
 *
 * @author Pazit and Leah
 */
public class PointLight extends Light implements LightSource {



    //the position of the point light
    private final Point position;

    //the kc attenuation factors
    private Double3 kC = Double3.ONE;

    //the kl attenuation factors
    private Double3 kL = Double3.ZERO;

    //the kq attenuation factors
    private Double3 kQ = Double3.ZERO;

    /**
     * constructor for PointLight class
     *
     * @param intensity - the intensity of the light
     * @param position - the position of the light
     */
    public PointLight(Color intensity, Point position) {
        //initialize the intensity by calling to the fathers constructor
        super(intensity);

        //initialize the position of the light
        this.position = position;
    }

    /**
     * constructor for PointLight class with attenuation factors
     *
     * @param intensity the intensity of the light
     * @param position  the position of the light
     * @param kC - attenuation factors
     * @param kL - attenuation factors
     * @param kQ - attenuation factors
     */
    public PointLight(Color intensity, Point position, double kC, double kL, double kQ) {
        //initialize the intensity by calling to the fathers constructor
        super(intensity);

        //initialize the position
        this.position = position;

        //initialize the attenuation factors
        setKc(kC);
        setKl(kL);
        setKq(kQ);
    }

    /**
     * setter for the attenuation factors
     */
    public PointLight setKc(double kC) {
        this.kC = new Double3(kC);
        return this;
    }

    /**
     * setter for the kL attenuation factor
     *
     * @param kL - the attenuation factor
     * @return the object itself
     */
    public PointLight setKl(double kL) {
        this.kL = new Double3(kL);
        return this;
    }

    /**
     * setter for the kQ attenuation factor
     *
     * @param kQ - the attenuation factor
     * @return the object itself
     */
    public PointLight setKq(double kQ) {
        this.kQ = new Double3(kQ);
        return this;
    }

    /**
     * setter for the kQ attenuation factor
     *
     * @param kQ - the attenuation factor
     * @return the object itself
     */
    public PointLight setKq(Double3 kQ) {
        this.kQ = kQ;
        return this;
    }

    /**
     * setter for the kL attenuation factor
     *
     * @param kL - the attenuation factor
     * @return the object itself
     */
    public PointLight setKl(Double3 kL) {
        this.kL = kL;
        return this;
    }

    /**
     * setter for the kC attenuation factor
     *
     * @param kC - the attenuation factor
     * @return the object itself
     */
    public PointLight setKc(Double3 kC) {
        this.kC = kC;
        return this;
    }

    @Override
    public Color getIntensity(Point p) {
        // Distance between the light source position and the given point
        double d = position.distance(p);

        if (d <= 0) {
            // If the distance is close to zero or negative, return the overall intensity of the light source
            return getIntensity();
        }

        // Calculate the attenuation factor based on distance and coefficients
        Double3 factor = kC.add(kL.scale(d).add(kQ.scale(d * d)));

        // Reduce the overall intensity of the light source based on the attenuation factor
        Color intensity = getIntensity().reduce(factor);

        // Return the resulting intensity at the given point
        return intensity;
    }


    @Override
    public Vector getL(Point p) {
        // Calculate the vector from the light source position to the given point and normalize it
        Vector l = p.subtract(position).normalize();

        // Return the resulting direction vector
        return l;
    }


    @Override
    public double getDistance(Point point) {
        // Calculate the distance between the light source position and the given point
        double dis = this.position.distance(point);

        // Return the resulting distance
        return dis;
    }

    //for random number of rays to create for soft shadows
    private static final Random RND = new Random();
    @Override
    public List<Vector> getLCircle(Point p, double r, int amount) {
        if (p.equals(position))
            return null;

        List<Vector> result = new LinkedList<>();

        Vector l = getL(p); //vector to the center of the point light
        result.add(l);

        if (amount < 2) {
            return result;
        }

        Vector vAcross;
        //if l is parallel to z axis, then the normal is across z on x-axis
        if (isZero(l.getX()) && isZero(l.getY())) {
            //switch z and x places
            vAcross = new Vector(0, 0, -1 * l.getZ()).normalize();
            //otherwise get the normal using x and y
        } else {//switched x and y places
            vAcross = new Vector(l.getX(),-1 * l.getY(),  0).normalize();
        }

        //the vector to the other direction
        Vector vForward = vAcross.crossProduct(l).normalize();

        double cosAngle, sinAngle, moveX, moveY, d;

        for (int i = 0; i < amount; i++) {
            Point movedPoint = this.position;

            //random cosine of angle between (-1,1)
            cosAngle = 2 * RND.nextDouble() - 1;

            //sin(angle)=1-cos^2(angle)
            sinAngle = sqrt(1 - cosAngle * cosAngle);

            //d is between (-r,r)
            d = r * (2 * RND.nextDouble() - 1);
            //if we got 0 then try again, because it will just be the same as the center
            if (isZero(d)) {
                i--;
                continue;
            }

            //says how much to move across and down
            moveX = d * cosAngle;
            moveY = d * sinAngle;

            //moving the point according to the value
            if (!isZero(moveX)) {
                movedPoint = movedPoint.add(vAcross.scale(moveX));
            }
            if (!isZero(moveY)) {
                movedPoint = movedPoint.add(vForward.scale(moveY));
            }

            //adding the vector from the new point to the light position
            result.add(p.subtract(movedPoint).normalize());
        }
        return result;
    }
}
