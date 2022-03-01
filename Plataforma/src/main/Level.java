package main;

import java.awt.Color;
import java.awt.Graphics;

public class Level {

	
	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE);
		g.setFont(Game.newFont);
		g.setColor(Color.white);
		g.drawString("COMEÇOU O JOGO", Game.WIDTH * Game.SCALE / 2 - 150, 200);
	}
}
