package Objects;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;

import shapes.Cube;
import utils.Colour;



public class Tank extends Cube{
	
	private final Colour tankSide1 = new Colour(1.0f, 1.0f, 1.0f, 0.2f);
    private final Colour tankSide2 = new Colour(0.0f, 0.89803f, 0.98823f, 0.2f);
   
    
	private float length, width, height;
	private float x, y, z;
	
	public Tank(float x, float y, float z, float length, float width, float height) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.length = length;
		this.width = width;
		this.height = height;
	}
	
	public void draw(GL2 gl, GLUT glut) {
		
		// length = 5
		// height = 3
		//width = 0.1
	
		// SIDE FRONT
        gl.glPushMatrix();
        Colour.setColourRGBA(tankSide1, gl);
        gl.glTranslated(0, 0, 2);
        gl.glScalef(length, height, width);
        super.draw(glut, gl);
        gl.glPopMatrix();

        // SIDE REAR
        gl.glPushMatrix();
        Colour.setColourRGBA(tankSide1, gl);
        gl.glTranslated(0, 0, -2);
        gl.glScalef(length, height, width);
        super.draw(glut, gl);
        gl.glPopMatrix();

        // RIGHT HAND SIDE
        gl.glPushMatrix();
        Colour.setColourRGBA(tankSide1, gl);
        gl.glTranslated(2.45, 0, 0);
        gl.glScalef(width, height, 4.0f);
        super.draw(glut, gl);
        gl.glPopMatrix();

        // LEFT HAND SIDE
        gl.glPushMatrix();
        Colour.setColourRGBA(tankSide1, gl);
        gl.glTranslated(-2.45, 0, 0);
        gl.glScalef(width, height, 4.0f);
        super.draw(glut, gl);
        gl.glPopMatrix();

        // BOTTOM
        gl.glPushMatrix();
        Colour.setColourRGBA(tankSide1, gl);
        gl.glTranslated(0, -1.50, 0);
        gl.glScalef(length, width, 4.1f);
        super.draw(glut, gl);
        gl.glPopMatrix();
	}
    
}
