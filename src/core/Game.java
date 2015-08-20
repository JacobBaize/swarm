package core;


import org.lwjgl.opengl.Display;
import java.util.ArrayList;


public class Game 
{
	
	
	public static int userCurrentScore = 0, userHighScore = 0, particleCheckIn = 0, particleAmount = 32;
	public static Player player = new Player();
	public static ArrayList<Enemy> enemyList = new ArrayList<Enemy>(0);
	public static ArrayList<Particles> particleList = new ArrayList<Particles>(0);
	
	
	public static void main(String []args) 
	{
		Window.setupDisplay();
		Enemy.createEnemy();
		while(!Display.isCloseRequested())
		{
			Window.setCamera();
			Level.draw();
			Particles.drawParticleList();
			player.draw();
			Enemy.drawEnemyList();
			Enemy.hordeKillCheck();
			Display.setTitle("HIGHEST STREAK: " + userHighScore + "          " + "CURRENT STREAK: " + userCurrentScore);
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
	}
	
	
}