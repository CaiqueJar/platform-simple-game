package entities;

import java.awt.Graphics;

import graphics.Animation;
import graphics.EntitiesSprites;

public class Smoke extends Entity{

	public Animation sprSmoke;

	public Smoke(int x, int y) {
		super(x, y, 18, 18);
		// TODO Auto-generated constructor stub
		sprSmoke = new Animation(this);

	}
	
	public void tick() {

		/*if(sprSmoke.index+1 == sprSmoke.maxIndex) {
			Game.miscellaneous.remove(this);
		}*/
	}

	public void render(Graphics g) {
		//sprSmoke.animationTick();
		//sprSmoke.render(g, false);
	}
	
}
