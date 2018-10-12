package testgame;

import org.lwjgl.glfw.GLFW;


public class Animation {
	private Texture[] frames;
	private int texturePointer;
	
	private double elapsedTime;
	private double currentTime;
	private double lastTime;
	private double fps;
	
	public Animation(int amount, int fps, String filename) {
		this.texturePointer = 0;
		this.elapsedTime = 0;
		this.currentTime = 0;

		this.fps = 1.0 / fps;
		
		this.frames = new Texture[amount];
		for (int i = 0; i < amount; i++) {
			this.frames[i] = new Texture(filename + "_" + i + ".png");
		}
	}
	
	public Animation(Texture texture) {
		this.texturePointer = 0;
		this.elapsedTime = 0;
		this.currentTime = 0;

		this.fps = 1.0 / fps;
		
		this.frames = new Texture[1];  //CLEANME
		for (int i = 0; i < 1; i++) {
			this.frames[i] = texture;
		}
	}
	
	public void bind() {
		bind(0);
	}
	
	public void bind(int sampler) {
		this.currentTime = GLFW.glfwGetTime();
		this.elapsedTime += currentTime - lastTime;
		
		if (elapsedTime >= fps) {
			elapsedTime = 0;
			texturePointer++;
		}
		
		if (texturePointer >= frames.length) texturePointer = 0;
		
		this.lastTime = currentTime;
		
		frames[texturePointer].bind();
	}
}