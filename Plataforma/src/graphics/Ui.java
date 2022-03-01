package graphics;

import java.awt.Graphics;

import main.Game;

public class Ui {

	public static void render(Graphics g) {
		for(int i = 0; i < Game.player.maxLife; i++) {
			g.drawImage(EntitiesSprites.sprUiHearts[2], 10+(i*40), 10, 48, 48, null);
		}
		
		for(int i = 0; i < Game.player.life; i++) {
			g.drawImage(EntitiesSprites.sprUiHearts[0], 10+(i*40), 10, 48, 48, null);
		}
	}
}
