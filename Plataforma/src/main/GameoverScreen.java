package main;

import java.awt.Color;
import java.awt.Graphics;

public class GameoverScreen {

	private int alpha = 0;
	private String[] gameOverLetters = "Game-Over".split("");
	private String phraseGameOver = "";
	private boolean showMessage, showPressEnter, onoff;
	public boolean enter;
	private int messageTimer, pressEnterTimer, index;
	
	public GameoverScreen() {
		alpha = 0;
		phraseGameOver = "";
		showMessage = false;
		showPressEnter = false; 
		onoff = false;
		messageTimer = 0;
		pressEnterTimer = 0;
		index = 0;
	}
	
	public void tick() {
		if(enter) {
			enter = false;
			Game.gameState = "mapSelecter";
			
		}
	}
	
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
				else if (!(index < gameOverLetters.length)) {
					showPressEnter = true;
				}
				g.setColor(Color.white);
				g.setFont(Game.newFont.deriveFont(40f));
				g.drawString(phraseGameOver, (Game.WIDTH * Game.SCALE - g.getFontMetrics(Game.newFont.deriveFont(40f)).stringWidth("Game-over"))/ 2, 200);
			}
			
			if(showPressEnter) {
				pressEnterTimer++;
				
				if(pressEnterTimer % 50 == 0) {
					onoff = !(onoff);
					pressEnterTimer = 0;
				}
			}
			if(onoff) {
				g.setColor(Color.white);
				g.setFont(Game.newFont.deriveFont(26f));
				g.drawString("press enter", (Game.WIDTH * Game.SCALE - g.getFontMetrics(Game.newFont.deriveFont(26f)).stringWidth("press enter")) / 2, 400);
			}
		}
	}
}
