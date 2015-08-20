package core;


import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;


public class Window 
{
	
	
	public Window()
	{
		
	}
	
	
	public static void setupDisplay()
	{
		try					
		{
			Display.setDisplayMode(new DisplayMode(640, 480));
			Display.create();
		}
		catch (LWJGLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void setCamera()
	{
		glClear(GL_COLOR_BUFFER_BIT);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 640, 0 ,480, -1, 1);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
	}
	

}
