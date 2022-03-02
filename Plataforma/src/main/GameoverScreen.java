package main;

import java.awt.Color;
import java.awt.Graphics;

public class GameoverScreen {

	private int alpha = 0;
	private String[] gameOverLetters = "Game-Over".split("");
	private String phraseGameOver = "";
	private boolean showMessage;
	private int messageTimer, index;
	
	public void render(Graphics g) {
		if(Game.player.gameOver) {
			g.setColor(new Color(0, 0, 0, alpha));
			
			alpha += 2;
			if(alpha >= 255) { 
				alpha = 255;
				showMessage = true;
			}
			g.fillRect(0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE);

			if(showMessage) {
				messageTimer++;
				if(messageTimer % 8 == 0 && index < gameOverLetters.length) {
					phraseGameOver += gameOverLetters[index];
					index++;
				}
				g.setColor(Color.white);
				g.setFont(Game.newFont);
				g.drawString(phraseGameOver, Game.WIDTH * Game.SCALE / 2 - 150, 200);
			}
		}
	}
}
