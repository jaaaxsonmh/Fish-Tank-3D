
import java.awt.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;


import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;

import javax.sound.midi.Track;

public class Scene implements GLEventListener, KeyListener {


    private static GLCanvas canvas;
    private GLUT glut;
    private float rquad = 45.0f;

    //track-ball camera
    private TrackballCamera camera = new TrackballCamera(canvas);


    @Override
    public void display(GLAutoDrawable drawable) {

        GL2 gl = drawable.getGL().getGL2();
        glut = new GLUT();

        // select and clear the model-view matrix
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();

        camera.draw(gl);


        gl.glRotatef(rquad, 1.0f, 1.0f, 1.0f);

        //giving different colors to different sides
        gl.glBegin(GL2.GL_QUADS); // Start Drawing The Cube
        gl.glColor3f(1f, 0f, 0f); //red color
        gl.glVertex3f(1.0f, 1.0f, -1.0f); // Top Right Of The Quad (Top)
        gl.glVertex3f(-1.0f, 1.0f, -1.0f); // Top Left Of The Quad (Top)
        gl.glVertex3f(-1.0f, 1.0f, 1.0f); // Bottom Left Of The Quad (Top)
        gl.glVertex3f(1.0f, 1.0f, 1.0f); // Bottom Right Of The Quad (Top)

        gl.glColor3f(0f, 1f, 0f); //green color
        gl.glVertex3f(1.0f, -1.0f, 1.0f); // Top Right Of The Quad
        gl.glVertex3f(-1.0f, -1.0f, 1.0f); // Top Left Of The Quad
        gl.glVertex3f(-1.0f, -1.0f, -1.0f); // Bottom Left Of The Quad
        gl.glVertex3f(1.0f, -1.0f, -1.0f); // Bottom Right Of The Quad

        gl.glColor3f(0f, 0f, 1f); //blue color
        gl.glVertex3f(1.0f, 1.0f, 1.0f); // Top Right Of The Quad (Front)
        gl.glVertex3f(-1.0f, 1.0f, 1.0f); // Top Left Of The Quad (Front)
        gl.glVertex3f(-1.0f, -1.0f, 1.0f); // Bottom Left Of The Quad
        gl.glVertex3f(1.0f, -1.0f, 1.0f); // Bottom Right Of The Quad

        gl.glColor3f(1f, 1f, 0f); //yellow (red + green)
        gl.glVertex3f(1.0f, -1.0f, -1.0f); // Bottom Left Of The Quad
        gl.glVertex3f(-1.0f, -1.0f, -1.0f); // Bottom Right Of The Quad
        gl.glVertex3f(-1.0f, 1.0f, -1.0f); // Top Right Of The Quad (Back)
        gl.glVertex3f(1.0f, 1.0f, -1.0f); // Top Left Of The Quad (Back)

        gl.glColor3f(1f, 0f, 1f); //purple (red + green)
        gl.glVertex3f(-1.0f, 1.0f, 1.0f); // Top Right Of The Quad (Left)
        gl.glVertex3f(-1.0f, 1.0f, -1.0f); // Top Left Of The Quad (Left)
        gl.glVertex3f(-1.0f, -1.0f, -1.0f); // Bottom Left Of The Quad
        gl.glVertex3f(-1.0f, -1.0f, 1.0f); // Bottom Right Of The Quad

        gl.glColor3f(0f, 1f, 1f); //sky blue (blue +green)
        gl.glVertex3f(1.0f, 1.0f, -1.0f); // Top Right Of The Quad (Right)
        gl.glVertex3f(1.0f, 1.0f, 1.0f); // Top Left Of The Quad
        gl.glVertex3f(1.0f, -1.0f, 1.0f); // Bottom Left Of The Quad
        gl.glVertex3f(1.0f, -1.0f, -1.0f); // Bottom Right Of The Quad
        gl.glEnd(); // Done Drawing The Qua
        //<your drawing and other code>
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

        //<other init code here>
        gl.glShadeModel(GL2.GL_SMOOTH);
        camera = new TrackballCamera(canvas);
        camera.setLookAt(0, 0, 0);
        camera.setDistance(10);
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

