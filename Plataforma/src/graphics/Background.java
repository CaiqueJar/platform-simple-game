package graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Game;
import world.Camera;

public class Background {
	
	private BufferedImage background1;
	private BufferedImage background2;

	public Background() {
		try {
			background1 = ImageIO.read(getClass().getResource("/mountais.png"));
			background2 = ImageIO.read(getClass().getResource("/clouds.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		for(int xx = 0; xx <= background2.getWidth() * 8; xx+=background2.getWidth()*Game.SCALE) {
			g.drawImage(background2, (xx-Camera.x)+Camera.x/8, Game.HEIGHT*Game.SCALE-80-Camera.y, background2.getWidth()*Game.SCALE, background2.getHeight()*Game.SCALE, null);
		}
		
		for(int xx = 0; xx <= background1.getWidth() * 8; xx+=background1.getWidth()*Game.SCALE) {
			g.drawImage(background1, xx-Camera.x, Game.HEIGHT*Game.SCALE-Camera.y, background1.getWidth()*Game.SCALE, background1.getHeight()*Game.SCALE, null);
		
		}
	}
}
