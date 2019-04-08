package Objects;

import Component.FishComponent;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import utils.AXIS;
import utils.Colour;
import utils.Drawable;

/**
 * @author Jack Hosking
 * @studentID 16932920
 */

public class Fish implements Drawable {

    private final Colour fish = new Colour(1.0f, 0.65490f, 0.14901f, 1.0f);


    private FishComponent root;

    public Fish(float size) {
        double radius = size * 0.5;
        double height = size * 0.25;

        root = new FishBody(radius, height, AXIS.X);
        
        //Fish eye -> 
        
        

    }

    @Override
    public void draw(GL2 gl, GLU glu, GLUquadric quadric, boolean filled) {
        root.draw(gl, glu, quadric, filled);
    }

    private class FishBody extends FishComponent {
        public FishBody(double radius, double height, AXIS axis) {
            super(radius, height, axis);
        }

        @Override
        public void drawNode(GL2 gl, GLU glu, GLUquadric quadric, boolean filled) {
            gl.glPushMatrix();
            Colour.setColourRGBA(fish, gl);
            gl.glScaled(radius, height, height);
            glu.gluSphere(quadric, 1, 250, 200);
            gl.glPopMatrix();
        }

    }
    
    private class FishFin extends FishComponent {
        public FishFin(double radius, double height, AXIS axis) {
            super(radius, height, axis);
        }

        @Override
        public void drawNode(GL2 gl, GLU glu, GLUquadric quadric, boolean filled) {
            gl.glPushMatrix();
            Colour.setColourRGBA(fish, gl);
            gl.glScaled(radius, height, height);
            //draw a fin shape
            gl.glPopMatrix();
        }

    }
    
    private class FishTail extends FishComponent {
        public FishTail(double radius, double height, AXIS axis) {
            super(radius, height, axis);
        }

        @Override
        public void drawNode(GL2 gl, GLU glu, GLUquadric quadric, boolean filled) {
            gl.glPushMatrix();
            Colour.setColourRGBA(fish, gl);
            gl.glScaled(radius, height, height);
            //draw a tail shape
            gl.glPopMatrix();
        }
    }
    
    private class FishEye extends FishComponent {
        public FishEye(double radius, double height, AXIS axis) {
            super(radius, height, axis);
        }

        @Override
        public void drawNode(GL2 gl, GLU glu, GLUquadric quadric, boolean filled) {
            gl.glPushMatrix();
            Colour.setColourRGBA(fish, gl);
            gl.glScaled(radius, height, height);
            //draw a tail shape
            gl.glPopMatrix();
        }
    }
}
