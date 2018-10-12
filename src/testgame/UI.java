package testgame;

import org.lwjgl.BufferUtils;

import java.awt.event.MouseEvent;
import java.nio.DoubleBuffer;
import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.glfwGetCursorPos;


public class UI
{
    ArrayList<Button> buttonList;
    public UI()
    {
        buttonList = new ArrayList<Button>();
    }
    public void addButton(String name,Texture texture,double x, double y,double width,double height)
    {
        buttonList.add(new Button(name,texture,x,y,width,height));
    }
    public boolean IsButtonClicked(String buttonName,long window)
    {
        Button b = getButton(buttonName);
        DoubleBuffer b1 = BufferUtils.createDoubleBuffer(1);
        DoubleBuffer b2 = BufferUtils.createDoubleBuffer(1);
        glfwGetCursorPos(window, b1, b2);
        //double mouseY=Lenght-b2.get(0)-1;
        if(b1.get(0)/World.blockSize > b.getX() && b1.get(0)/World.blockSize< b.getX()+b.getWidth() && b2.get(0)/World.blockSize>b.getY() &&
        		b2.get(0)/World.blockSize<b.getY()+b.getHeight())
        {
            return true;
        }
        return false;
    }

    public Button getButton(String buttonName)
    {
        for (Button b: buttonList) {
            if (b.getName().equals(buttonName))
            {
                return b;
            }
        }
        return null;
    }
    public void draw()
    {
        for (Button b:buttonList)
        {
            new MenuButton().DrawQuadTex(b.getTexture(),b.getX(),b.getY(),b.getWidth(),b.getHeight());
        }
    }
}
