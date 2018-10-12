package testgame;

import org.lwjgl.glfw.GLFW;

import java.awt.font.FontRenderContext;

public class Input implements IObservable {

    private long window;

    private boolean keys[];

    public Input(long window)
    {
        this.window=window;
        this.keys=new boolean[GLFW.GLFW_KEY_LAST];
        for (int i=0;i<GLFW.GLFW_KEY_LAST;i++)
        {
            keys[i]=false;
        }
    }

    public boolean isKeyDown(int key)
    {
        return GLFW.glfwGetKey(window,key) == 1;
    }

    public boolean isMouseButtonDown(int button)
    {
        return GLFW.glfwGetMouseButton(window,button) == 1;
    }

    public boolean idKeyPressed(int key)
    {
        return (isKeyDown(key) && !keys[key]);
    }

    public boolean idKeyReleased(int key)
    {
        return (!isKeyDown(key) && keys[key]);
    }

    public void update()
    {
        for (int i=0;i<GLFW.GLFW_KEY_LAST;i++)
            keys[i]=isKeyDown(i);
    }

}

