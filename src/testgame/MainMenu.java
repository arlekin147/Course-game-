package testgame;

import org.lwjgl.glfw.GLFW;

import java.awt.event.MouseEvent;

public class MainMenu  {
    private Texture texture;
    private UI menuUI;
    private Input input;


    public MainMenu(double Lenght,double Width)
    {
        texture = new Texture("./Res/back.png");
        menuUI = new UI();
        menuUI.addButton("PLAY",new Texture("./res/Play.png"),Lenght/2.5/World.blockSize,Width/4/World.blockSize,Lenght/5/World.blockSize,Width/5/World.blockSize);
        menuUI.addButton("EXIT",new Texture("./res/Exit.png"),Lenght/2.5/World.blockSize,Width/2/World.blockSize,Lenght/5/World.blockSize,Width/5/World.blockSize);
    }

    public boolean updateButtonPlay(long window)
    {
            if(menuUI.IsButtonClicked("PLAY",window))
            {
                return true;
            }
        return false;
    }
    public boolean updateButtonExit(long window)
    {
            if(menuUI.IsButtonClicked("EXIT",window))
            {
                return true;
            }
            return false;
    }

    public void update(double width,double height,long window) {
        new MenuButton().DrawQuadTex(texture,0,0,width/World.blockSize,height/World.blockSize);
        menuUI.draw();
        //updateButton(width,window);
    }
}
