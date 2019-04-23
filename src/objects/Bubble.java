package objects;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import utils.Colour;
import utils.Drawable;

public class Bubble implements Drawable {

    private Colour colour;
    private float age;
    public float x, y, z;
    public float transparency;
    public float radius;

    public Bubble(float transparency, float radius, Colour colour, float x, float y, float z, float age){
        this.transparency = transparency;
        this.radius = radius;
        this.x = x;
        this.y = y;
        this.z = z;
        this.age = age;
        this.colour = colour;
    }


    @Override
    public void draw(GL2 gl, GLU glu, GLUquadric quadric, boolean filled) {
        gl.glPushMatrix();
        gl.glTranslated(x, y, z);
        Colour.setDynamicColourRGBA(colour, transparency, gl);
        glu.gluSphere(quadric, radius, 10, 20);
        gl.glPopMatrix();
    }

    public void animate() {
        this.y += age;

        if(transparency > 0) {
            this.transparency -= age/2;
        } else {
            transparency = 0;
        }

        if(radius > 0) {
            this.radius -= age / 30;
        } else {
            radius = 0;
        }
    }
}
