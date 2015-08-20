package core;


import static org.lwjgl.opengl.GL11.*;
import java.util.Random;


public class Enemy 
{
	
	
	public double x, y;
	private double xspeed, yspeed;
	
	
	public Enemy() 
	{
		x = safeSpawnX();
		y = safeSpawnY();
	}
	
	
	public static double safeSpawnX()
	{
		 boolean safeSpawnX = false;
		 double possibleX = 0;
			while(!safeSpawnX) 
			{
				possibleX = new Random().nextDouble() * 640;
				if(Math.abs(possibleX - Game.player.x) >= 120)
				{
					safeSpawnX = true;
				}
			}
			return possibleX;
	}
	
	
	public static double safeSpawnY()
	{
		boolean safeSpawnY = false;
		double possibleY = 0;
		while(!safeSpawnY) 
		{
			possibleY = new Random().nextDouble() * 480;
			if(Math.abs(possibleY - Game.player.y) >= 120)
			{
				safeSpawnY = true;
			}
		}
		return possibleY;	
	}
	
	
	public void draw() 
	{
		logic();
		if(Game.particleCheckIn % 32 == 0 || Game.particleCheckIn == 0)
		{
			glPushMatrix();
			glTranslated(x, y, 0);
			glBegin(GL_QUADS);
			glColor3d(1, 0, 0);
			glVertex2d(-8, 0);
			glVertex2d(+8, 0);
			glVertex2d(8, 16);
			glVertex2d(-8, 16);
			glEnd();
			glPopMatrix();
		}
	}
	
	
	private void logic() 
	{
		if(Game.particleCheckIn % 32 != 0) 
		{
			xspeed = 0;
			yspeed = 0;
		}
		updateVelocity();
		if(Game.particleCheckIn % 32 == 0) 
		{
			pathfind();
		}
		playerKillCheck();
	}
	
	
	private void updateVelocity()
	{
		xspeed *= 0.9;
		yspeed *= 0.9;
		x += xspeed;
		y += yspeed;
	}
	
	
	private void pathfind()
	{
		if(Game.player.x > x)
		{
			if(Math.abs(Game.player.x - x) < 3)
			{
				xspeed += 0.1;
			}
			else
			{
				xspeed += 0.4;
			}
		}
		if(Game.player.x < x) 
		{
			if(Math.abs(Game.player.x - x) < 3) 
			{
				xspeed -= 0.1;
			}
			else
			{
				xspeed -= 0.4;
			}
		}
		if(Game.player.y > y)
		{
			if(Math.abs(Game.player.y - y) < 3)
			{
				yspeed += 0.1;
			}
			else
			{
				yspeed += 0.4;
			}
		}
		if(Game.player.y < y)
		{
			if(Math.abs(Game.player.y - y) < 3)
			{
				yspeed -= 0.1;
			}
			else
			{
				yspeed -= 0.4;
			}
		}
	}
	
	
	private void playerKillCheck()
	{
		if(Math.abs(Game.player.x - x) <= 10 && Math.abs(Game.player.y - y) <= 10) 
		{
			Game.userCurrentScore = 0;
			Game.player.isDead = true;
		}
	}
	
	
	public static void createEnemy()
	{
		for(int i = 0; i < 6; i++)
		{
			Game.enemyList.add(new Enemy());
		}
	}
	
		
	public static void drawEnemyList()
	{
		for(Enemy e: Game.enemyList)
		{
			e.draw();
		}		
	}
	
	
	public static void hordeKillCheck()
	{
		if(Math.abs(Game.enemyList.get(0).x - Game.enemyList.get(5).x) <= 2 && Math.abs(Game.enemyList.get(0).y - Game.enemyList.get(5).y) <= 2
				&& Math.abs(Game.enemyList.get(1).x - Game.enemyList.get(4).x) <= 2 && Math.abs(Game.enemyList.get(1).y - Game.enemyList.get(4).y) <= 2
				&& Math.abs(Game.enemyList.get(2).x - Game.enemyList.get(3).x) <= 2 && Math.abs(Game.enemyList.get(2).y - Game.enemyList.get(3).y) <= 2) 
		{
			Game.particleList.clear();
			Particles.createParticles();
			Enemy.respawnEnemyList();
			Game.userCurrentScore++;
			if(Game.userCurrentScore > Game.userHighScore) 
			{
				Game.userHighScore = Game.userCurrentScore;
			}
		}
	}
		
	
	public static void respawnEnemyList()
	{
		for(Enemy e: Game.enemyList) 
		{
			e.x = Enemy.safeSpawnX();
			e.y = Enemy.safeSpawnY();
		}
	}
			
	
}
