package Component;


import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import utils.AXIS;
import utils.Drawable;

import java.util.LinkedList;

public class Fish implements Drawable {

    private LinkedList<Fish> children;
    private double radius;
    private double height;
    private double rotationAngle, transX, transY, transZ;
    private AXIS axis;

    Fish(double radius, double height, AXIS axis) {
        this.radius = radius;
        this.height = height;
        this.axis = axis;
    }

    @Override
    public void draw(GL2 gl, GLU glu, GLUquadric quadric, boolean filled) {
        gl.glPushMatrix();

        // TODO TRANSFORM

        // draw each child
        for (Fish child : children) {
            child.draw(gl, glu, quadric, filled);
        }

        gl.glPopMatrix();
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

    private void setTranslations(double x, double y, double z) {
        transX = x;
        transY = y;
        transZ = z;
    }

    void setRotation(double theta) {
        rotationAngle = theta;
    }
}
