package utils;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;

public interface Drawable {
    void draw(GL2 gl2, GLU glu, GLUquadric quadric, boolean filled);

}