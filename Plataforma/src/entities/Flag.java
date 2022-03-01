package entities;

import java.awt.Graphics;

import graphics.Animation;
import graphics.EntitiesSprites;
import main.Game;

public class Flag extends Entity{

	public boolean win; 
	
	private Animation sprites;
	
	public Flag(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		
		sprites = new Animation(this);
		sprites.spritesNovas.put("vermelho", EntitiesSprites.sprRedFlags);
		sprites.spritesNovas.put("azul", EntitiesSprites.sprBlueFlags);

	}

	public void tick() {
		if(Entity.isColidding(this, Game.player)) {
			Game.player.win = true;
			win = true;
		}
	}
	
	public void render(Graphics g) {
		sprites.animationTick();
		if(!win) {
			sprites.renderTeste(g, "vermelho", 5, false);
		}
		else if(win) {
			sprites.renderTeste(g, "azul", 5, false);
		}
	}
}
