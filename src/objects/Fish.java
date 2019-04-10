package objects;

import component.FishComponent;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import utils.AXIS;
import utils.Colour;
import utils.Drawable;

import objects.Tank;
import objects.Water;


/**
 * @author Jack Hosking
 * @studentID 16932920
 */

public class Fish implements Drawable {

    private final Colour fish = new Colour(1.0f, 0.65490f, 0.14901f, 1.0f);
    private final Colour sclera = new Colour(1.0f, 1.0f, 1.0f, 1.0f);
    private final Colour pupil = new Colour(0.0f, 0.0f, 0.0f, 1.0f);
    private int finMovementCount = 0;
    private double finMovement = 0;
    private float rotation = 0;
    private float x,y,z;
    private float vx, vy, vz;


    private double[] eqn0 = {0, 0.0, 1.0, 0};
    private double[] eqn1 = {0, 0.0, -1.0, 0};


    private FishComponent root;

    public Fish(float size) {
        double radius = size * 0.5;
        double height = size * 0.25;
        finMovement = 0.002f;
        x = 0;
        y = 0;
        z = 0;

        root = new FishBody(radius, height, AXIS.X);

        //Left eye ::  Child of the body
        FishEye leftEyeSclera = new FishEye(radius, height, AXIS.X);
        leftEyeSclera.setTranslations(radius * 0.8, height * 0.5, size * 0.1);


        // Eye Pupil :: Child of the Sclera
        FishEyePupil leftEyePupil = new FishEyePupil(radius * 0.8, height * 0.8, AXIS.X);
        leftEyePupil.setTranslations(height * 0.19, 0, 0);
        leftEyeSclera.addChild(leftEyePupil);

        // Right eye ::  Child of the body
        FishEye rightEyeSclera = new FishEye(radius, height, AXIS.X);
        rightEyeSclera.setTranslations(radius * 0.8, height * 0.5, -size * 0.1);

        //Eye Pupil ::  Child of the Sclera
        FishEyePupil rightEyePupil = new FishEyePupil(radius * 0.8, height * 0.8, AXIS.X);
        rightEyePupil.setTranslations(height * 0.19, 0, 0);
        rightEyeSclera.addChild(rightEyePupil);

        root.addChild(leftEyeSclera);
        root.addChild(rightEyeSclera);


        finMovementCount++;
        if (finMovementCount > 5) {
            finMovement *= -1;
            finMovementCount = 0;
        }

        FishFin leftFin = new FishFin(radius, height, AXIS.X);
        leftFin.setEqn(eqn0);
        leftFin.setTranslations(0, 0, height * 0.8);

        FishFin rightFin = new FishFin(radius, height, AXIS.X);
        rightFin.setTranslations(0, 0, -height * 0.8);

        root.addChild(leftFin);
        root.addChild(rightFin);

    }

    @Override
    public void draw(GL2 gl, GLU glu, GLUquadric quadric, boolean filled) {
        gl.glPushMatrix();

        gl.glTranslated(x, y, z);
        gl.glRotated(0 + rotation, 0, 1, 0);

//      float yPos =  height /2;
//		float xPos = length / 2;
//		float frontRearPos = width / 2;

        root.draw(gl, glu, quadric, filled);
        gl.glPopMatrix();
    }

    public void animate(float speed) {

        y += vy + speed;
        x += vy + speed;
        z += vz + speed;

        System.out.println("X: " + x + "\n"
                +"Y: " + y + "\n"
                +"Z: " + z + "\n"
                +"VX: " + vx + "\n"
                +"VY: " + vy + "\n"
                +"VZ: " + vz + "\n"
        );

        if(y > (Tank.height / 2)) {
            vy -= speed ;
            System.out.println("Move Y -");
        } else if (y < (-Tank.height / 2)) {
            vy += speed;
            System.out.println("Move Y +");
        }

        if(x > (Tank.length / 2)) {
            vx -= speed;
            System.out.println("Move X -");
            rotation = 180;
        } else if (x < -(Tank.length / 2)) {
            vx += speed;
            System.out.println("Move X +");
            rotation = -180;
        }

        if(z > (Tank.width / 2)) {
            vz -= speed;
            System.out.println("Move Z -");
            rotation = 90;
        } else if (z < -(Tank.width / 2)) {
            vz += speed;
            System.out.println("Move Z +");
            rotation = -90;
        }

    }

    public class FishBody extends FishComponent {
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

    public class FishFin extends FishComponent {
        public FishFin(double radius, double height, AXIS axis) {
            super(radius, height, axis);
        }

        @Override
        public void drawNode(GL2 gl, GLU glu, GLUquadric quadric, boolean filled) {
            gl.glPushMatrix();

            gl.glClipPlane(GL2.GL_CLIP_PLANE0, eqn, 0);
            gl.glEnable(GL2.GL_CLIP_PLANE0);
            Colour.setColourRGBA(fish, gl);

            gl.glScaled(radius * 0.5, height * 0.1, height * 0.5);
            glu.gluSphere(quadric, 1, 25, 20);
            gl.glRotated(10, 0, 10, 0);

            gl.glDisable(GL2.GL_CLIP_PLANE0);
            gl.glPopMatrix();
        }
    }

    public class FishTail extends FishComponent {
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

    public class FishEye extends FishComponent {
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

    public class FishEyePupil extends FishComponent {
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
