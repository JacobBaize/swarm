package core;


import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.input.Keyboard;


public class Player 
{
	
	
	public double x, y;
	public boolean isDead;
	private double xspeed, yspeed;
	
	
	public Player() 
	{
		x = 320;
		y = 240;
	}
	
	
	public void draw() 
	{
		logic();
		glPushMatrix();
		glTranslated(x, y, 0);
		glBegin(GL_QUADS);
		glColor3d(0, 0, 0);
		glVertex2d(-8, 0);
		glVertex2d(+8, 0);
		glVertex2d(8, 16);
		glVertex2d(-8, 16);
		glEnd();
		glPopMatrix();
	}
	
	
	private void logic() 
	{
		if(Game.particleCheckIn % 32 != 0) 
		{
			xspeed = 0;
			yspeed = 0;
			x = 320;
			y = 240;
		}
		if(isDead) 
		{
			x = 320;
			y = 240;
			isDead = false;
			Enemy.respawnEnemyList();
		}
		updateVelocity();
		if(Game.particleCheckIn % 32 == 0) 
		{
			acceptUserInput();
		}
		enforceBorders();
	}
	
	
	private void updateVelocity()
	{
		xspeed *= 0.9;
		yspeed *= 0.9;
		x += xspeed;
		y += yspeed;
	}
	
	
	private void acceptUserInput()
	{
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT))
		{
			xspeed -= 1.5;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
		{
			xspeed += 1.5;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_UP))
		{
			yspeed += 1.5;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN))
		{
			yspeed -= 1.5;	
		}
	}
	
	
	private void enforceBorders()
	{
		if(Game.player.x <= 9)
		{
			Game.player.x = 9;
		}
		if(Game.player.x >= 631)
		{
			Game.player.x = 631;
		}
		if(Game.player.y <= 1)
		{
			Game.player.y = 1;
		}
		if(Game.player.y >= 464)
		{
			Game.player.y = 464;
		}
	}
	
	
}
