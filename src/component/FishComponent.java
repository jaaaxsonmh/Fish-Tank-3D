package component;


import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import utils.AXIS;
import utils.Drawable;

import java.util.LinkedList;

/**
 * @author Jack Hosking
 * @studentID 16932920
 */

public abstract class FishComponent implements Drawable {

    private LinkedList<FishComponent> children;
    public double radius;
    public double height;
    public double animatorSpeed;
    private double rotationAngle, transX, transY, transZ;
    private AXIS axis;
    public double[] eqn = {0, 0.0, -1.0, 0};


    public FishComponent(double radius, double height, AXIS axis) {
        children = new LinkedList<>();
        this.radius = radius;
        this.height = height;
        this.axis = axis;
    }

    @Override
    public void draw(GL2 gl, GLU glu, GLUquadric quadric, boolean filled) {
        gl.glPushMatrix();

        transformNode(gl);
        drawNode(gl, glu, quadric, filled);

        // draw each child
        for (FishComponent child : children) {
            child.draw(gl, glu, quadric, filled);

        }

        gl.glPopMatrix();
    }

    public void addChild(FishComponent child) {
        children.add(child);
    }

    private void transformNode(GL2 gl) {
        // do the translation relative to the parent and then rotate and move nodes
        gl.glTranslated(transX, transY, transZ);

        switch (axis) {
            case X:
                gl.glRotated(rotationAngle, 1, 0, 0);
                break;
            case Y:
                gl.glRotated(rotationAngle, 0, 1, 0);
                break;
            case Z:
                gl.glRotated(rotationAngle, 0, 0, 1);
                break;
            default:
                break;
        }
    }

    public void setTranslations(double x, double y, double z) {
        transX = x;
        transY = y;
        transZ = z;
    }

    void setRotation(double theta) {
        rotationAngle = theta;
    }

    public double getSpeed() {
        return animatorSpeed;
    }

    public void setSpeed(double animatorSpeed) {
        this.animatorSpeed =  animatorSpeed;
    }

    public void setEqn(double[] eqn) {
        this.eqn = eqn;
    }

    public abstract void drawNode(GL2 gl, GLU glu, GLUquadric quadric, boolean filled);

}
