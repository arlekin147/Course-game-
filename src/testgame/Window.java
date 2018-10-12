package testgame;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

public class Window implements IObseravable {
    private long window;
    private int width,height;

    private Input input;

    public static void setCallbacks()
    {
        GLFW.glfwSetErrorCallback(new GLFWErrorCallback() {
            @Override
            public void invoke(int error, long description) {
                throw new IllegalStateException(GLFWErrorCallback.getDescription(description));
            }
        });
    }

    public Window()
    {
        setSize(700,500);
    }

    public void createWindow(String title)
    {
        window= GLFW.glfwCreateWindow(width,height,title,0,0);

        if(window == 0)
            throw new IllegalStateException("Failed to create world");

        GLFWVidMode vid = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        GLFW.glfwSetWindowPos(window,(vid.width()-width)/2,(vid.height()-height)/2);

        GLFW.glfwShowWindow(window);

        GLFW.glfwMakeContextCurrent(window);

        input = new Input(window);
    }

    public boolean shouldClose()
    {
       return GLFW.glfwWindowShouldClose(window);
    }



    public  void swapBuffers()
    {
        GLFW.glfwSwapBuffers(window);
    }

    public void setSize(int width,int height)
    {
        this.width=width;
        this.height=height;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Input getInput() {
        return input;
    }

    public long getWindow() {
        return window;
    }

    @Override
    public void update() {
        input.update();
        GLFW.glfwPollEvents();
    }
}
