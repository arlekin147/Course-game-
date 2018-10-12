/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgame;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import org.lwjgl.glfw.GLFW;
import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.lwjgl.glfw.GLFWScrollCallback;
/**
 *
 * @author РђР»РµРєСЃР°РЅРґСЂ
 */
public class TestGame {
    World world;
    MainMenu mainMenu;
    //Menu menu = new Menu();
            /**
     * @param args the command line arguments
     */
   public static final int WIDTH = 1366;
   public static final int HEIGHT = 768;
    private long window;

        
   
	public void run() {
		System.out.println("Hello LWJGL " + Version.getVersion() + "!");

		init();
		loop();

		// Free the window callbacks and destroy the window
		glfwFreeCallbacks(window);
		glfwDestroyWindow(window);

		// Terminate GLFW and free the error callback
		glfwTerminate();
		glfwSetErrorCallback(null).free();
	}

	private void init() {
		GLFWErrorCallback.createPrint(System.err).set();

		// Initialize GLFW. Most GLFW functions will not work before doing this.
		if ( !glfwInit() )
			throw new IllegalStateException("Unable to initialize GLFW");

		// Configure GLFW
		glfwDefaultWindowHints(); // optional, the current window hints are already the default
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE); // the window will be resizable

		// Create the window
		window = glfwCreateWindow(WIDTH, HEIGHT, "Hello World!", NULL, NULL);
		if ( window == NULL )
			throw new RuntimeException("Failed to create the GLFW window");

		// Setup a key callback. It will be called every time a key is pressed, repeated or released.
		glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
			if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
				glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
		});

		// Get the thread stack and push a new frame
		try ( MemoryStack stack = stackPush() ) {
			IntBuffer pWidth = stack.mallocInt(1); // int*
			IntBuffer pHeight = stack.mallocInt(1); // int*

			// Get the window size passed to glfwCreateWindow
			glfwGetWindowSize(window, pWidth, pHeight);

			// Get the resolution of the primary monitor
			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

			// Center the window
			glfwSetWindowPos(
				window,
				(vidmode.width() - pWidth.get(0)) / 2,
				(vidmode.height() - pHeight.get(0)) / 2
			);
		} // the stack frame is popped automatically

		// Make the OpenGL context current
		glfwMakeContextCurrent(window);
		// Enable v-sync
		glfwSwapInterval(1);

		// Make the window visible
		glfwShowWindow(window);
                

		// This line is critical for LWJGL's interoperation with GLFW's
		// OpenGL context, or any context that is managed externally.
		// LWJGL detects the context that is current in the current thread,
		// creates the GLCapabilities instance and makes the OpenGL
		// bindings available for use.
		///Yeah, rly criticaly important!
		GL.createCapabilities();
		
		
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA,GL_ONE_MINUS_SRC_ALPHA);
        
       world = new World(this.window);
       //world.mapCOut();
       world.generateCaves();
       world.generateHills();
		// Setup an error callback. The default implementation
		// will print the error message in System.err.
	}
    
	private void loop() {
                
		// Set the clear color
		glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
                
                glMatrixMode(GL_PROJECTION); //РўР°Рє РЅР°РїРёСЃР°РЅРѕ РІ СѓСЂРѕРєРµ 
                glLoadIdentity(); 
                glOrtho(0,WIDTH/World.blockSize,HEIGHT/World.blockSize,0,1,-1); 
                glMatrixMode(GL_MODELVIEW);  //...
                
		// Run the rendering loop until the user has attempted to close
		// the window or has pressed the ESCAPE key.
        Texture texture = new Texture("person.png");
        mainMenu = new MainMenu(WIDTH,HEIGHT);
        int time = 0;
        
        int state = 0;
        
		while ( !glfwWindowShouldClose(window) ) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
			switch(state) {
			case 0:		
				mainMenu.update(WIDTH,HEIGHT,this.window);

				if (glfwGetMouseButton(this.window, 0) == 1) {
					if(mainMenu.updateButtonPlay(window)) {
						state = 1;
						//System.out.println("ogo");
					}
					if(mainMenu.updateButtonExit(window))
					{
						glfwSetWindowShouldClose(this.window,true);
					}
				}
				
				
				
				break;
			case 1:
				
				this.world.draw();
				this.world.update();  
				if((glfwGetKey(window,GLFW_KEY_F6)) == 1) {
					this.world.save();
				}
				if((glfwGetKey(window,GLFW_KEY_F5)) == 1) {
					System.out.println("load");
					this.world.load();
				}
				//testChar.move(this.world.blockSize , 0);
				break;
				
			case 2 :
				Texture promo = new Texture("res/promo.png");
				
				promo.bind();
		    	glBegin(GL_QUADS); 
		        glTexCoord2i(0,0);
		        glVertex2i(0,0); //Р В РІР‚С”Р В Р’ВµР В Р вЂ Р В Р’В°Р РЋР РЏ Р В Р вЂ Р В Р’ВµР РЋР вЂљР РЋРІР‚В¦Р В Р вЂ¦Р РЋР РЏР РЋР РЏ Р РЋРІР‚С™Р В РЎвЂўР РЋРІР‚РЋР В РЎвЂќР В Р’В° Р В РЎвЂќР В Р вЂ Р В Р’В°Р В РўвЂ�Р РЋР вЂљР В Р’В°Р РЋРІР‚С™Р В Р’В° 
		        glTexCoord2i(1,0);
		        glVertex2d(Camera.width,0); //Р В РЎСџР РЋР вЂљР В Р’В°Р В Р вЂ Р В Р’В°Р РЋР РЏ Р В Р вЂ Р В Р’ВµР РЋР вЂљР РЋРІР‚В¦Р В Р вЂ¦Р РЋР РЏР РЋР РЏ Р РЋРІР‚С™Р В РЎвЂўР РЋРІР‚РЋР В РЎвЂќР В Р’В° Р В РЎвЂќР В Р вЂ Р В Р’В°Р В РўвЂ�Р РЋР вЂљР В Р’В°Р РЋРІР‚С™Р В Р’В° 
		        glTexCoord2i(1,1);
		        glVertex2d(Camera.width,Camera.height - 2); //Р В РЎСџР РЋР вЂљР В Р’В°Р В Р вЂ Р В Р’В°Р РЋР РЏ Р В Р вЂ¦Р В РЎвЂ�Р В Р’В¶Р В Р вЂ¦Р РЋР РЏР РЋР РЏ Р РЋРІР‚С™Р В РЎвЂўР РЋРІР‚РЋР В РЎвЂќР В Р’В° Р В РЎвЂќР В Р вЂ Р В Р’В°Р В РўвЂ�Р РЋР вЂљР В Р’В°Р РЋРІР‚С™Р В Р’В° 
		        glTexCoord2i(0,1);
		        glVertex2d(0,Camera.height - 2); //Р В РІР‚С”Р В Р’ВµР В Р вЂ Р В Р’В°Р РЋР РЏ Р В Р вЂ¦Р В РЎвЂ�Р В Р’В¶Р В Р вЂ¦Р РЋР РЏР РЋР РЏ Р РЋРІР‚С™Р В РЎвЂўР РЋРІР‚РЋР В РЎвЂќР В Р’В° Р В РЎвЂќР В Р вЂ Р В Р’В°Р В РўвЂ�Р РЋР вЂљР В Р’В°Р РЋРІР‚С™Р В Р’В° 
		        glEnd(); 
		        
		        
				++time;
				if(time > 150) {
					state = 0;
				}
				break;

			}
		
		/*try {
			//Thread.sleep(100L);
		} catch (Exception var3) {
			System.out.println("ogo");
		}*/
			glfwSwapBuffers(window); // swap the color buffers

			// Poll for window events. The key callback above will only be
			// invoked during this call.
			glfwPollEvents();
		}
	}
	
	
	public static void main(String[] args) {
		

		new TestGame().run();
	}
    
}
