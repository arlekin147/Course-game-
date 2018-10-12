
package testgame;

public class Button {
    private String name;
    private Texture texture;
    private double x,y,width,height;

    public Button(String name,Texture texture,double x , double y , double width,double height)
    {
        this.height=height;
        this.x=x;
        this.y=y;
        this.width=width;
        this.texture=texture;
        this.name=name;
    }

  /*  public Button(String name,Texture texture,double x , double y )
    {
        this.height=texture.getHeight();
        this.x=x;
        this.y=y;
        this.width=texture.getWidth();
        this.texture=texture;
        this.name=name;
    }*/

    public double getWidth() {
        return width;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getHeight() {
        return height;
    }

    public String getName() {
        return name;
    }

    public Texture getTexture() {
        return texture;
    }
}
