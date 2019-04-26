package component;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import objects.Bubble;
import objects.Fish;
import objects.Tank;
import utils.Colour;
import utils.Rand;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jack Hosking
 * studentID 16932920
 */

public class BubbleManager {
    private final static Colour WHITE = new Colour(1.0f, 1.0f, 1.0f);

    private List<Bubble> bubbleList = new ArrayList<>();
    private static final int BUBBLE_AMOUNT = 20;
    private final long TIME_DELAY = 600L;
    private long prevTime = System.currentTimeMillis() - TIME_DELAY;


    private void populate() {
        long currentTime = System.currentTimeMillis();
        if((currentTime-prevTime) >= TIME_DELAY) {
            prevTime = currentTime;
            if(BUBBLE_AMOUNT > bubbleList.size()) {
                float transparency = Rand.getFloatBetween(0.5f, 1.0f);
                float radius = Rand.getFloatBetween(0.03f, 0.05f);
                float x = Fish.x - (Rand.getFloatBetween(0.0f, 1.0f) * Fish.radius);
                float y = Fish.y + (Rand.getFloatBetween(0.2f, 0.4f) * Fish.radius);
                float z = Fish.z - (Rand.getFloatBetween(0.0f,-1.0f) * Fish.radius);
                System.out.println(y);
                float age = Rand.getFloatBetween(0.005f, 0.01f);

                bubbleList.add(new Bubble(transparency, radius, WHITE, x, y, z, age));
            }
        }
    }

    private void reset() {
        for (Bubble bub : bubbleList) {
            float resetX = Fish.x - ((Rand.getFloatBetween(0.0f, 1.0f) * Fish.radius) * (float) Math.sin(Math.toRadians(Fish.rotation)));
            float resetY = Fish.y + (Rand.getFloatBetween(0.2f, 0.4f) * Fish.radius);
            float resetZ = Fish.z - ((Rand.getFloatBetween(0.0f, -1.0f) * Fish.radius) * (float) Math.cos(Math.toRadians(Fish.rotation)));
            float radius = Rand.getFloatBetween(0.02f, 0.04f);
            float transparency = Rand.getFloatBetween(0.3f, 1.0f);

            if (bub.y >= (Tank.height /2) - 0.1f) {
                bub.y = resetY;
            }
            if (bub.transparency < 0.0f || bub.radius < 0.0f) {
                bub.x = resetX;
                bub.y = resetY;
                bub.z = resetZ;
                bub.transparency = transparency;
                bub.radius = radius;
            }
        }
    }

    public void stateManager(GL2 gl, GLU glu, GLUquadric quadric, boolean filled) {
        populate();
        for(Bubble bub : bubbleList) {
            bub.draw(gl, glu, quadric, filled);
            bub.animate();
            reset();
        }
    }
}
