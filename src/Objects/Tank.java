package Objects;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;

import shapes.Cube;
import utils.Colour;

/**
 * @author Jack Hosking
 * @studentID 16932920
 */

public class Tank extends Cube {
	
	private final Colour tankSide = new Colour(1.0f, 1.0f, 1.0f, 0.3f);

	float length, height, width;
	private static final float TANK_PANE = 0.1f;
	
	public Tank(float length, float height, float width) {
		this.length = length;
		this.height = height;
		this.width = width;
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
        gl.glScalef(length, TANK_PANE, width);
        super.draw(glut);
        gl.glPopMatrix();
	}



//    float bottomLength = (size / 1.25f) + WIDTH;
//
//        gl.glPushMatrix();
//        Colour.setColourRGBA(tankSide2, gl);
//        gl.glTranslated(0, 0, size * 0.4f);
//        gl.glScalef(size * 0.5f, (size * 0.6f), WIDTH);
//        super.draw(glut, gl);
//        gl.glPopMatrix();
//
//    // SIDE REAR
//        gl.glPushMatrix();
//        Colour.setColourRGBA(tankSide2, gl);
//        gl.glTranslated(0, 0, -size * 0.4f);
//        gl.glScalef(size * 0.5f, (size * 0.6f), WIDTH);
//        super.draw(glut, gl);
//        gl.glPopMatrix();
//
//    // RIGHT HAND SIDE
//        gl.glPushMatrix();
//        Colour.setColourRGBA(tankSide2, gl);
//        gl.glTranslated(1.2f, 0, 0);
//        gl.glScalef(WIDTH, size * 0.6f, size * 0.8f);
//        super.draw(glut, gl);
//        gl.glPopMatrix();
//
//    // LEFT HAND SIDE
//        gl.glPushMatrix();
//        Colour.setColourRGBA(tankSide2, gl);
//        gl.glTranslated(-2.45f, 0, 0);
//        gl.glScalef(WIDTH, size * 0.6f, size * 0.8f);
//        super.draw(glut, gl);
//        gl.glPopMatrix();
//
//    // BOTTOM
//        gl.glPushMatrix();
//        Colour.setColourRGBA(tankSide2, gl);
//        gl.glTranslated(0, -size * 0.3, 0);
//        gl.glScalef(size / 2, WIDTH, bottomLength);
//        super.draw(glut, gl);
//        gl.glPopMatrix();
    
}
