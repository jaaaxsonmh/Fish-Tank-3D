package Objects;

import Component.FishComponent;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import utils.AXIS;
import utils.Colour;
import utils.Drawable;

public class Fish implements Drawable {

    private final Colour fish = new Colour(0.98f, 0.89803f, 0.38823f, 0.2f);


    private FishComponent root;

    public Fish(float size) {
        double radius = size * 0.5;
        double height = size * 0.25;

        root = new FishBody(radius, height, AXIS.X);

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
}
