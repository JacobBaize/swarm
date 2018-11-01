package core;


import static org.lwjgl.opengl.GL11.*;
import java.util.Random;


public class Particles 
{
	
	
	private double x, y, xspeed, yspeed;
	private int RColor, GColor, BColor;
	private boolean tracking, hasRun;
	
	
	public Particles(double x, double y, int RColor, int GColor, int BColor) 
	{
		this.RColor = RColor;
		this.GColor = GColor;
		this.BColor = BColor;
		this.x = x;
		this.y = y;
		xspeed = vectorCreator();
		yspeed = vectorCreator();
	}

	
	private double vectorCreator()
	{
		boolean particleDirection = new Random().nextBoolean();
		double rand = new Random().nextDouble() * 6;
		if(particleDirection)
		{
			return rand;
		}
		else
		{
			return rand * -1;
		}
	}
	
	
	public void draw() 
	{
		logic();
		glPushMatrix();
		glTranslated(x, y, 0);
		glColor3d(RColor, GColor, BColor);
		glBegin(GL_QUADS);
		glVertex2d(-2, 0);
		glVertex2d(2, 0);
		glVertex2d(2, 4);
		glVertex2d(-2, 4);
		glEnd();
		glPopMatrix();
	}
	
	
	private void logic() 
	{
		updateVelocity();
		particleReturnCheck();
	}
	
	
	private void updateVelocity()
	{
		x += xspeed;
		y += yspeed;
	}
	
	
	private void particleReturnCheck()
	{
		if(Game.player.isDead && !hasRun) 
		{
			tracking = true;
			RColor = 0;
			GColor = 0;
			BColor = 0;
			xspeed = 0;
			yspeed = 0;
		}
		if(tracking) 
		{
			Game.particleList.get(0).x = 320;
			Game.particleList.get(0).y = 245;
			if(new Random().nextInt(2) == 1) 
			{
				xspeed *= 0.6;
				if(320 > x)
				{
					if(Math.abs(320 - x) <= 30)
					{
						xspeed += 1;
					}
					else
					{
						xspeed += 2;
					}
				}
				if(320 < x)
				{
					if(Math.abs(320 - x) <= 30)
					{
						xspeed -= 1;
					}
					else
					{
						xspeed -= 2;
					}
				}
			}
			else 
			{
				yspeed *= 0.6;
				if(245 > y)
				{
					if(Math.abs(245 - y) <= 30)
					{
						yspeed += 1;
					}
					else
					{
						yspeed += 2;
					}
				}
				if(245 < y)
				{
					if(Math.abs(245 - y) <= 30)
					{
						yspeed -= 1;
					}
					else
					{
						yspeed -= 2;
					}
				}
			}
		}
		if(Math.abs(x - 320) <= 2 && Math.abs(y - 245) <= 2 && tracking) 
		{
			tracking = false;
			RColor = 1;
			GColor = 1;
			BColor = 1;
			xspeed = 0;
			yspeed = 0;
			x = 320;
			y = 245;
			hasRun = true;
			Game.particleCheckIn++;
		}
	}
		 
	
	public static void drawParticleList()
	{
		for(Particles p: Game.particleList)
		{
			p.draw();
		}
	}
	
	
	public static void createParticles()
	{
		for(int i = 0; i < Game.particleAmount; i++)
		{
			Game.particleList.add(new Particles(Game.enemyList.get(0).x, Game.enemyList.get(0).y, 1, 0, 0));
		}
	}
				
	
}
