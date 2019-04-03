package shapes;

import com.jogamp.opengl.util.gl2.GLUT;

public class Cube {

    public void draw(GLUT glut) {
        glut.glutSolidCube(1.0f);
    }
}
