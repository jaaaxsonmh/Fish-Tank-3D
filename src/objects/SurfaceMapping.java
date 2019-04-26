package objects;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import utils.Drawable;

import javax.xml.soap.Text;
import java.io.IOException;

public class SurfaceMapping implements Drawable {


    private float yPos;
    private float width;
    private float length;
    private Texture surfaceTexture;
    private boolean texture;

    public SurfaceMapping(String textureFile){
        this.yPos = Water.WATER_HEIGHT/2;
        texture = (textureFile.trim().isEmpty());
        if(texture) {
            try {
                setSurfaceTexture(textureFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        length = Tank.length;
        width = Tank.width;
    }

    public void setSurfaceTexture(String file) throws IOException {
        surfaceTexture = TextureIO.newTexture(this.getClass().getResourceAsStream(file), true, "jpg");
    }

    @Override
    public void draw(GL2 gl, GLU glu, GLUquadric quadric, boolean filled) {

        if(texture) {
            surfaceTexture.enable(gl);
            surfaceTexture.bind(gl);

            surfaceTexture.setTexParameteri(gl, GL.GL_TEXTURE_WRAP_S, GL.GL_REPEAT);
            surfaceTexture.setTexParameteri(gl, GL.GL_TEXTURE_WRAP_T, GL.GL_REPEAT);
        }


        System.out.println((int)length +" " + (int) width);
        for(float i = -length/2; i < length/2; i++) {
            for(float j = -width/2; j < width/2; j++) {
                gl.glBegin(filled ? GL2.GL_QUADS : GL.GL_LINE_LOOP);

                // makes a 1x1 square grid.
                gl.glNormal3f(0.0f, 1.0f, 0.0f);
                gl.glTexCoord2d(2, 1);
                gl.glVertex3d(i, yPos, j );

                gl.glNormal3f(0.0f, 1.0f, 0.0f);
                gl.glTexCoord2d(2, 1);
                gl.glVertex3d(i + 1, yPos, j);

                gl.glNormal3d(0,1,0);
                gl.glTexCoord2d(1 , 2 );
                gl.glVertex3d(i +  1, yPos,j + 1);

                gl.glNormal3d(0,1,0);
                gl.glTexCoord2d(1 , 1 );
                gl.glVertex3d(i , yPos,j + 1);

                gl.glEnd();
            }
        }
        if(texture) {
            surfaceTexture.disable(gl);

        }
    }

    public void animate() {
    }


}
