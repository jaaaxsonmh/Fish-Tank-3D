package utils;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;

public class Guide {

    public void draw(GL2 gl) {
        float LINE_SIZE = 20;

        gl.glBegin(GL.GL_LINES);
        // Draw y line
        gl.glColor3f(0,1,0); // green
        gl.glVertex3f(0, LINE_SIZE /2,0);
        gl.glVertex3f(0,0,0);
        gl.glEnd();

        gl.glBegin(GL.GL_LINES);
        // Draw x line
        gl.glColor3f(1,0,0); // red
        gl.glVertex3f(LINE_SIZE /2,0,0);
        gl.glVertex3f(0,0,0);
        gl.glEnd();

        gl.glBegin(GL.GL_LINES);
        // Draw z line
        gl.glColor3f(0,0,1); // blue
        gl.glVertex3f(0,0,0);
        gl.glVertex3f(0,0, LINE_SIZE /2);
        gl.glEnd();
    }

}
