
import java.awt.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import Objects.Water;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;


import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;
import Objects.Tank;
import utils.Colour;

/**
 * @author Jack Hosking
 * @studentID 16932920
 */

public class Scene implements GLEventListener, KeyListener {

    private final Colour fish = new Colour(1.0f, 0.0f, 0.0f, 1.0f);

    private float fogDensity = 0.007f;

    private static final float[] fogColour = new float[]{1.0f, 1.0f, 1.0f};

    private Tank tank;
    private Water water;

    private static GLCanvas canvas;
    private GLUT glut;

    private TrackballCamera camera = new TrackballCamera(canvas);

    public Scene() {
        float length = 5f;
        float height = 1f;
        float width = 4f;

        tank = new Tank(length, height, width);
        water = new Water(length, height, width);
    }

    @Override
    public void display(GLAutoDrawable drawable) {

        GL2 gl = drawable.getGL().getGL2();
        glut = new GLUT();
        float positionRelativeToCam = (float) camera.getDistance() * (float) camera.getFieldOfView();

        // select and clear the model-view matrix
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();

        camera.draw(gl);

        gl.glDisable(GL2.GL_LIGHTING);
        gl.glDisable(GL2.GL_LIGHT0);
        gl.glDisable(GL2.GL_LIGHT1);

        gl.glEnable(GL2.GL_BLEND);

        gl.glDisable(GL2.GL_DEPTH_TEST);

        water.draw(gl, glut);
        tank.draw(gl, glut);

        setUpFog(gl, positionRelativeToCam);

        // those lines for 3d
        drawScale(gl);

        gl.glEnable(GL2.GL_DEPTH_TEST);

        gl.glDisable(GL2.GL_BLEND);

        gl.glEnable(GL2.GL_LIGHTING);
        gl.glEnable(GL2.GL_LIGHT0);
        gl.glEnable(GL2.GL_LIGHT1);

        gl.glPushMatrix();
        Colour.setColourRGBA(fish, gl);
        gl.glTranslated(0, 3.25f, 0);
        glut.glutSolidSphere(0.3, 100, 40);
        gl.glPopMatrix();

        gl.glFlush();
    }


    @Override
    public void dispose(GLAutoDrawable drawable) {
        // TODO Auto-generated method stub

    }

    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        gl.setSwapInterval(1);

        gl.glShadeModel(GL2.GL_SMOOTH);
        camera = new TrackballCamera(canvas);
        camera.setLookAt(0, 0, 0);
        camera.setDistance(15);
        camera.setFieldOfView(40);

        //use the lights
        this.lights(gl);


        gl.glEnable(GL2.GL_DEPTH_TEST);

    }

    private void lights(GL2 gl) {
        // lighting stuff
        float ambient[] = {0, 0, 0, 1};
        float diffuse[] = {1f, 1f, 1f, 1};
        float specular[] = {1, 1, 1, 1};

        float[] ambientLight = {0.1f, 0.1f, 0.1f, 0f};  // weak RED ambient
        gl.glLightfv(GL2.GL_LIGHT3, GL2.GL_AMBIENT, ambientLight, 0);

        float position0[] = {5, 5, 5, 0};
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, position0, 0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, ambient, 0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, diffuse, 0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_SPECULAR, specular, 0);

        float position1[] = {-10, -10, -10, 0};
        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_POSITION, position1, 0);
        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_AMBIENT, ambient, 0);
        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_DIFFUSE, diffuse, 0);
        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_SPECULAR, specular, 0);

        gl.glEnable(GL2.GL_LIGHTING);
        gl.glEnable(GL2.GL_LIGHT0);
        gl.glEnable(GL2.GL_LIGHT1);

        //lets use use standard color functions
        gl.glEnable(GL2.GL_COLOR_MATERIAL);
        //normalise the surface normals for lighting calculations
        gl.glEnable(GL2.GL_NORMALIZE);
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

    }

    private void drawScale(GL2 gl) {
        gl.glLineWidth(2.0f);
        gl.glBegin(GL2.GL_LINES);
        gl.glColor3d(1, 0, 0);
        gl.glVertex3d(0, 0, 0);
        gl.glVertex3d(8, 0, 0);
        gl.glColor3d(0, 1, 0);
        gl.glVertex3d(0, 0, 0);
        gl.glVertex3d(0, 8, 0);
        gl.glColor3d(0, 0, 1);
        gl.glVertex3d(0, 0, 0);
        gl.glVertex3d(0, 0, 8);
        gl.glEnd();
    }

    private void setUpFog(GL2 gl, float positionRelativeToCam) {

        if (positionRelativeToCam < 800) {
            fogDensity = 0.0f;
        } else if (positionRelativeToCam >= 800 && positionRelativeToCam < 1000) {
            fogDensity = 0.1f;
        } else if (positionRelativeToCam >= 1000 && positionRelativeToCam <= 1200) {
            fogDensity = 1.0f;
        }

        gl.glEnable(GL2.GL_FOG);
        gl.glFogfv(GL2.GL_FOG_COLOR, fogColour, 0);
        gl.glFogf(GL2.GL_FOG_MODE, GL2.GL_EXP2);
        gl.glFogf(GL2.GL_FOG_DENSITY, fogDensity);
    }


    public static void main(String[] args) {
        Frame frame = new Frame("Jack's 3D Fish(y) Tank");
        frame.setResizable(false);

        //key mapping console prints
        System.out.println("Key mapping:");
        System.out.println("--------------------------");
        System.out.println("SPACE: pause/restart");
        System.out.println("1: SLOW ANIMATION SPEED");
        System.out.println("2: NORMAL ANIMATION SPEED");
        System.out.println("3: FAST ANIMATION SPEED");

        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        canvas = new GLCanvas(capabilities);

        Scene fish3D = new Scene();


        // add event listeners
        canvas.addGLEventListener(fish3D);
        frame.add(canvas);
        frame.setSize(500, 500);

        final FPSAnimator animator = new FPSAnimator(canvas, 60);
        animator.start();

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(() -> {
                    animator.stop();
                    System.exit(0);
                }).start();
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

