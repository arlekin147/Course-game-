package testgame;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.*;
import org.lwjgl.system.windows.DISPLAY_DEVICE;

import java.awt.*;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.glfw.GLFWMouseButtonCallback;



class MenuButton {

    public int DrawQuadTex(Texture texture,double x,double y,double width,double height)
    {
        texture.bind();
        GL11.glBegin( GL11.GL_QUADS);
        GL11.glTexCoord2f(0, 0);
        GL11.glVertex2d(x, y);

        GL11.glTexCoord2f(1, 0);
        GL11.glVertex2d(x+width, y);

        GL11.glTexCoord2f(1, 1);
        GL11.glVertex2d(x+width, y+height);

        GL11.glTexCoord2f(0, 1);
        GL11.glVertex2d(x, y+height);

        GL11.glEnd();
        return 0;
    }




}