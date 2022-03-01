package entities;

import java.awt.Graphics;
import java.awt.Color;

import main.Game;
import world.Camera;

public class Enemy3 extends Entity {

	public boolean canTick;
	public boolean remove;
	
	public double spd = 0.5;
	public boolean ataque;
	public int coolDown, side;
	public double boteForca = 20, perca = 0.5;

	public Enemy3(int x, int y) {
		super(x, y, 48, 48);
		// TODO Auto-generated constructor stub
		
	}
	
	public void tick() {
		if(Game.player.jump) ataque = true;
		
		//bote();
		int d = getX() - Game.player.getX();
		
		if(d < 400) x+=2;
		else x-=2;
		
		System.out.println(getY() - Game.player.getY());
		if(getY() - Game.player.getY() > -260) y--;
		else y+=2;
	}
	
	public void bote() {
		if(ataque) {
			// ataque = false;
			
			switch(side) {
			case 0:
				x-=8;
				y+=boteForca;
				boteForca -= perca;
				break;
				
			case 1:
				
				break;
				
			}
			
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect(getX() - Camera.x, getY() - Camera.y, 48, 48);
	}

}
