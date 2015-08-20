package core;


import static org.lwjgl.opengl.GL11.*;


public class Level 
{
	
	
	public Level() 
	{
		
	}
	
	
	public static void draw() 
	{
		glBegin(GL_QUADS);
		glColor3d(1, 1, 1);
		glVertex2d(0, 0);
		glVertex2d(640, 0);
		glVertex2d(640, 480);
		glVertex2d(0, 480);
		glEnd();
	}
	
	
}
