package world;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Tiles extends Rectangle{

	private static final long serialVersionUID = 1L;
	
	private int x, y, width, height;
	private BufferedImage sprite;
	
	
	public Tiles(int x, int y, int width, int height, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, x - Camera.x, y - Camera.y, width, height, null);
	}
}
