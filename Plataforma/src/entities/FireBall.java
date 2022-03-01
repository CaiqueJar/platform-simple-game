package entities;

import java.awt.Graphics;

import graphics.Animation;
import graphics.EntitiesSprites;
import main.Game;

public class FireBall extends Entity {

	public Animation sprite;
	
	public char dir;
	public int spd = 4;
	public int timer;
	
	public FireBall(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		dir = Game.player.lastDirection;
		
		this.sprite = new Animation(this);
		sprite.spritesNovas.put("fire", EntitiesSprites.sprFireBall);
		
		setMaskx(getX() + 6);
		setMasky(getY() + 16);
		setMwidth(getWidth()-18);
		setMheight(getHeight()-30);
	}
	
	public void tick() {
		setMaskx(getX() + 6);
		setMasky(getY() + 16);
		
		timer++;
		
		if(dir == 'r') {
			x+=spd;
		}
		else {
			x-=spd;
		}
		
		if(timer >= 60*3) {
			Player.fireBalls.remove(this);
			return;
		}
		
	}
	
	public void render(Graphics g) {
		sprite.animationTick();
		
		if(dir == 'r')
			sprite.renderTeste(g, "fire", 5, false);
	
		else
			sprite.renderTeste(g, "fire", 5, true);
		
		
		//g.setColor(new Color(225, 0, 0, 150));
		//g.fillRect(getX() + 6 - Camera.x, getY() + 16 - Camera.y, getWidth()-18, getHeight()-30);
	}

}
