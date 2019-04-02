package shapes;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;
import utils.Colour;

public class Cube {


    public void draw(GLUT glut, GL2 gl) {
        glut.glutSolidCube(1.0f);
    }
}
