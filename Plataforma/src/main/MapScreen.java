package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.io.IOException;
import java.io.InputStream;

import world.World;

public class MapScreen {
	private InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("Minecraft.ttf");
	private Font font;
	
	private int option = 1;
	public boolean right, left, enter;

	public MapScreen() {
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(38f);
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void tick() {
		if(right) {
			right = false;
			option++;
			Sound.menuClick.play();
		}
		else if(left) {
			left = false;
			option --;
			Sound.menuClick.play();
		}
		
		if(option <= 0) {
			option = 4;
		}
		else if(option > 4) {
			option = 1;
		}
		
		if(enter) {
			enter = false;
			Game.actualLevel = option;
			Game.world = new World("/level"+option+".png");
			Game.gameState = "game";
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE);
		
		g.setFont(font);
		
		for(int i = 0; i < 4; i++) {
			g.setColor(Color.white);
			g.fillRoundRect(10+(i*74), 10, 64, 64, 4, 4);
			g.setColor(Color.black);
			g.drawString(String.valueOf(i+1), 35+(i*74), 55);
		}
		
		g.setColor(new Color(0, 255, 0, 150));
		g.fillRoundRect(10+((option-1)*74), 10, 64, 64, 4, 4);
	}
}
