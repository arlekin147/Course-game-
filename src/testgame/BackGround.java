package testgame;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2i;
import static org.lwjgl.opengl.GL11.glVertex2d;
import static org.lwjgl.opengl.GL11.glVertex2i;

import java.util.*;

public class BackGround {
	
	ArrayList<Texture> backs;
	
	public BackGround() {
		this.backs = new ArrayList<Texture>();
		this.backs.add(new Texture("res/back.png"));
		this.backs.add(new Texture("res/caveBack.png"));		
	}
	
	public void draw(int num) {
		this.backs.get(num).bind();
    	glBegin(GL_QUADS); 
        glTexCoord2i(0,0);
        glVertex2i(0,0); //Р В РІР‚С”Р В Р’ВµР В Р вЂ Р В Р’В°Р РЋР РЏ Р В Р вЂ Р В Р’ВµР РЋР вЂљР РЋРІР‚В¦Р В Р вЂ¦Р РЋР РЏР РЋР РЏ Р РЋРІР‚С™Р В РЎвЂўР РЋРІР‚РЋР В РЎвЂќР В Р’В° Р В РЎвЂќР В Р вЂ Р В Р’В°Р В РўвЂ�Р РЋР вЂљР В Р’В°Р РЋРІР‚С™Р В Р’В° 
        glTexCoord2i(1,0);
        glVertex2d(Camera.width,0); //Р В РЎСџР РЋР вЂљР В Р’В°Р В Р вЂ Р В Р’В°Р РЋР РЏ Р В Р вЂ Р В Р’ВµР РЋР вЂљР РЋРІР‚В¦Р В Р вЂ¦Р РЋР РЏР РЋР РЏ Р РЋРІР‚С™Р В РЎвЂўР РЋРІР‚РЋР В РЎвЂќР В Р’В° Р В РЎвЂќР В Р вЂ Р В Р’В°Р В РўвЂ�Р РЋР вЂљР В Р’В°Р РЋРІР‚С™Р В Р’В° 
        glTexCoord2i(1,1);
        glVertex2d(Camera.width,Camera.height); //Р В РЎСџР РЋР вЂљР В Р’В°Р В Р вЂ Р В Р’В°Р РЋР РЏ Р В Р вЂ¦Р В РЎвЂ�Р В Р’В¶Р В Р вЂ¦Р РЋР РЏР РЋР РЏ Р РЋРІР‚С™Р В РЎвЂўР РЋРІР‚РЋР В РЎвЂќР В Р’В° Р В РЎвЂќР В Р вЂ Р В Р’В°Р В РўвЂ�Р РЋР вЂљР В Р’В°Р РЋРІР‚С™Р В Р’В° 
        glTexCoord2i(0,1);
        glVertex2d(0,Camera.height); //Р В РІР‚С”Р В Р’ВµР В Р вЂ Р В Р’В°Р РЋР РЏ Р В Р вЂ¦Р В РЎвЂ�Р В Р’В¶Р В Р вЂ¦Р РЋР РЏР РЋР РЏ Р РЋРІР‚С™Р В РЎвЂўР РЋРІР‚РЋР В РЎвЂќР В Р’В° Р В РЎвЂќР В Р вЂ Р В Р’В°Р В РўвЂ�Р РЋР вЂљР В Р’В°Р РЋРІР‚С™Р В Р’В° 
        glEnd(); 
	}
}
