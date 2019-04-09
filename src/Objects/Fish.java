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
    private final Colour sclera = new Colour(1.0f, 1.0f, 1.0f, 1.0f);
    private final Colour pupil = new Colour(0.0f, 0.0f, 0.0f, 1.0f);




    private FishComponent root;

    public Fish(float size) {
        double radius = size * 0.5;
        double height = size * 0.25;

        root = new FishBody(radius, height, AXIS.X);
        
        //Left eye ::  Child of the body
        FishEye leftEyeSclera = new FishEye(radius, height, AXIS.X);
        leftEyeSclera.setTranslations(radius * 0.8, height * 0.5, size * 0.1);


        // Eye Pupil :: Child of the Sclera
        FishEyePupil leftEyePupil = new FishEyePupil(radius * 0.8, height * 0.8 , AXIS.X);
        leftEyePupil.setTranslations(height * 0.19, 0, 0);

        // Add as child
        leftEyeSclera.addChild(leftEyePupil);

        // Right eye ::  Child of the body
        FishEye rightEyeSclera = new FishEye(radius, height, AXIS.X);
        rightEyeSclera.setTranslations(radius * 0.8, height * 0.5, -size * 0.1);

        //Eye Pupil ::  Child of the Sclera
        FishEyePupil rightEyePupil = new FishEyePupil(radius * 0.8, height * 0.8 , AXIS.X);
        rightEyePupil.setTranslations(height * 0.19, 0, 0);

        // Add as child
        rightEyeSclera.addChild(rightEyePupil);

        root.addChild(leftEyeSclera);
        root.addChild(rightEyeSclera);






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
            glu.gluSphere(quadric, 1, 25, 20);
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
            Colour.setColourRGBA(sclera, gl);
            gl.glScaled(0.05, 0.05, 0.05);
            glu.gluSphere(quadric, 1, 25, 20);
            gl.glPopMatrix();
        }
    }

    private class FishEyePupil extends FishComponent {
        public FishEyePupil(double radius, double height, AXIS axis) {
            super(radius, height, axis);
        }

        @Override
        public void drawNode(GL2 gl, GLU glu, GLUquadric quadric, boolean filled) {
            gl.glPushMatrix();
            Colour.setColourRGBA(pupil, gl);
            gl.glScaled(0.01, 0.02, 0.02);
            glu.gluSphere(quadric, 1, 25, 20);
            gl.glPopMatrix();
        }
    }
}
