/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//package testgame;
package testgame;

import de.matthiasmann.twl.utils.PNGDecoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;

/**
 *
 * @author Александр
 */
public class Texture{

    private int id;

    public int getId(){
        return id;
    }
    
    
    public Texture(String fileName){

        FileInputStream in = null;

        try {
            //load png file
            in = new FileInputStream(fileName);
        } catch (FileNotFoundException ex) {
            System.err.println("FileNotFoundException");
            System.exit(-1);
        }


        PNGDecoder decoder = null; 
        try {
            decoder = new PNGDecoder(in);
        } catch (IOException ex) {
            System.err.println("Nam Pizda 2");
            System.exit(-1);
        }

        //create a byte buffer big enough to store RGBA values
        ByteBuffer buffer = ByteBuffer.allocateDirect(4 * decoder.getWidth() * decoder.getHeight());

        try {
            //decode
            decoder.decode(buffer, decoder.getWidth() * 4, PNGDecoder.Format.RGBA);
        } catch (IOException ex) {
            System.err.println("Nam Pizda 3");
            System.exit(-1);
        }

        //flip the buffer so its ready to read
        buffer.flip();

        //create a texture
        this.id = glGenTextures();

        //bind the texture
        glBindTexture(GL_TEXTURE_2D, id);

        //tell opengl how to unpack bytes
        //glPixelStorei(GL_UNPACK_ALIGNMENT, 1);

        //set the texture parameters, can be GL_LINEAR or GL_NEAREST
        glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

        //upload texture
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, decoder.getWidth(), decoder.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);

        // Generate Mip Map
        glGenerateMipmap(GL_TEXTURE_2D);

        try {
            in.close();
        } catch (IOException ex) {
            System.err.println("Pohuy");
            System.exit(-1);
        }
    }
    
    public void bind(){
        glBindTexture(GL_TEXTURE_2D, id);
    }
}
