package objects;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;

import shapes.Cube;
import utils.Colour;

/**
 * @author Jack Hosking
 * Student ID 16932920
 */

public class Tank extends Cube {
	
	private final Colour tankSide = new Colour(1.0f, 1.0f, 1.0f, 0.3f);

	static float length;
    static float height;
    static float width;
	private static final float TANK_PANE = 0.01f;
	
	public Tank(float length, float height, float width) {
		Tank.length = length;
		Tank.height = height;
		Tank.width = width;
	}

	public void draw(GL2 gl, GLUT glut) {
		float yPos =  height /2;
		float xPos = length / 2;

		float frontRearPos = width / 2;
		float zSize = width + TANK_PANE;
		float ySize = height + TANK_PANE;

		// SIDE FRONT
        gl.glPushMatrix();
        Colour.setColourRGBA(tankSide, gl);
        gl.glTranslated(0, 0, frontRearPos);
        gl.glScalef(length, ySize, TANK_PANE);
        super.draw(glut);
        gl.glPopMatrix();

        // SIDE REAR
        gl.glPushMatrix();
        Colour.setColourRGBA(tankSide, gl);
        gl.glTranslated(0, 0, -frontRearPos);
        gl.glScalef(length, ySize, TANK_PANE);
        super.draw(glut);
        gl.glPopMatrix();

        // RIGHT HAND SIDE
        gl.glPushMatrix();
        Colour.setColourRGBA(tankSide, gl);
        gl.glTranslated(xPos, 0, 0);
        gl.glScalef(TANK_PANE, ySize,  zSize);
        super.draw(glut);
        gl.glPopMatrix();

        // LEFT HAND SIDE
        gl.glPushMatrix();
        Colour.setColourRGBA(tankSide, gl);
        gl.glTranslated(-xPos, 0, 0);
        gl.glScalef(TANK_PANE, ySize, zSize);
        super.draw(glut);
        gl.glPopMatrix();

        // BOTTOM
        gl.glPushMatrix();
        Colour.setColourRGBA(tankSide, gl);
        gl.glTranslated(0, -yPos, 0);
        gl.glScalef(length, TANK_PANE * 2, width);
        super.draw(glut);
        gl.glPopMatrix();
	}

    
}
