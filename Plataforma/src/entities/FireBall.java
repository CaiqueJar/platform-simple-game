package entities;

import java.awt.Graphics;

import graphics.Animation;
import graphics.EntitiesSprites;
import main.Game;

public class FireBall extends Entity {

	public Animation sprite;
	
	public int dir;
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
		
		if(dir == 1) {
			x+=spd;
		}
		else if(dir == -1){
			x-=spd;
		}
		
		if(timer >= 60*3) {
			Player.fireBalls.remove(this);
			return;
		}
		
	}
	
	public void render(Graphics g) {
		sprite.animationTick();
		
		if(dir == 1)
			sprite.renderTeste(g, "fire", 5, false);
	
		else
			sprite.renderTeste(g, "fire", 5, true);
		
		
		//g.setColor(new Color(225, 0, 0, 150));
		//g.fillRect(getX() + 6 - Camera.x, getY() + 16 - Camera.y, getWidth()-18, getHeight()-30);
	}

}