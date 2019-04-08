package shapes;

import com.jogamp.opengl.util.gl2.GLUT;

/**
 * @author Jack Hosking
 * @studentID 16932920
 */

public class Cube {

    public void draw(GLUT glut) {
        glut.glutSolidCube(1.0f);
    }
}
