package Objects;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;
import utils.Colour;

/**
 * @author Jack Hosking
 * @studentID 16932920
 */

public class Water extends Tank {

    private final Colour tankSide2 = new Colour(0.0f, 0.89803f, 0.98823f, 0.2f);
    public Water(float length, float height, float width) {
        super(length, height, width);
    }

    public void draw(GL2 gl, GLUT glut) {
        gl.glPushMatrix();
        Colour.setColourRGBA(tankSide2, gl);
        gl.glTranslated(0, -0.05, 0);
        gl.glScalef(length - 0.1f, height - 0.1f, width - 0.1f);
        super.draw(glut);
        gl.glPopMatrix();
    }
}
