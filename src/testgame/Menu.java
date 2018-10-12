package testgame;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.*;
import org.lwjgl.system.windows.DISPLAY_DEVICE;

import java.awt.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.glfw.GLFWMouseButtonCallback;



class Menu {


    public int Draw(Texture texture) {


        // Window.setCallbacks();
       // if (!glfwInit()) {
         //   System.err.println("GLFW Failed to initialize");
           // System.exit(1);
        //}


        //Window window = new Window();

        //window.createWindow("KsiInt");


        //GL.createCapabilities();



       // Texture texture = new Texture("./Res/Test.png");



       // GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

        texture.bind();

        GL11.glBegin( GL11.GL_QUADS);
        GL11.glTexCoord2f(0, 0);
        GL11.glVertex2f(0, 0);

        GL11.glTexCoord2f(1, 0);
        GL11.glVertex2f(TestGame.WIDTH / World.blockSize, 0);

        GL11.glTexCoord2f(1, 1);
        GL11.glVertex2f(TestGame.WIDTH /  World.blockSize, TestGame.HEIGHT /  World.blockSize);

        GL11.glTexCoord2f(0, 1);
        GL11.glVertex2f(0, TestGame.HEIGHT /  World.blockSize);
        GL11.glEnd();

        //System.out.println("pisdec");
        /*try
        {
            Thread.sleep(10000);
        }catch (InterruptedException o)
        {
            throw new IllegalStateException(o);
        }*/
        return 1;
    }
}