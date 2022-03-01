package entities;

import java.awt.Graphics;

import main.Game;

public class Enemy extends Entity {

	public int timer;

	public Enemy(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}
	
	public void tick() {
		
	}
	
	public void remover(double maxTime) {
		timer++;
		if(timer >= 60*maxTime) {
			Game.enemies.remove(this);
		}
	}
	
	public void render(Graphics g) {

	}
	 

}
