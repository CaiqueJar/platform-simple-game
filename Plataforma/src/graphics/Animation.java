package graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import entities.Entity;
import world.Camera;

public class Animation {

	private Entity entity;
	
	private int frame, maxFrame;
	private int index, maxIndex;
	
	private String lastName;
	
	
	public Map<String, BufferedImage[]> spritesNovas;
	public Map<String, BufferedImage> spriteNovas;
	
	public Animation(Entity entity) {
		this.entity = entity;
		
		spritesNovas = new HashMap<>();
		spriteNovas = new HashMap<>();
	}
	
	public void animationTick() {
		frame++;
		if (frame == maxFrame) {
			frame = 0;
			index++;
			if (index == maxIndex) {
				index = 0;
			}
		}
	}
	
	public void reset() {
		frame = 0;
		index = 0;
	}
	
	public int getIndex() {
		return index;
	}

	public void renderTeste(Graphics g, String spritesName, int maxFrame, boolean flip) {
		
		if(spritesNovas.containsKey(spritesName)) {
			if(lastName != spritesName) {
				frame = 0;
				index = 0;
				this.maxFrame = maxFrame;
				this.maxIndex = spritesNovas.get(spritesName).length;
			}
			
			lastName = spritesName;
			
			
			if(flip == false)
				g.drawImage(spritesNovas.get(spritesName)[index], entity.getX() - Camera.x, entity.getY() - Camera.y, entity.width, entity.height, null);
			
			else
				g.drawImage(spritesNovas.get(spritesName)[index], entity.getX() + entity.width - Camera.x, entity.getY() - Camera.y, -entity.width, entity.height, null);
		}
		else if(spriteNovas.containsKey(spritesName)) {
			if(flip == false)
				g.drawImage(spriteNovas.get(spritesName), entity.getX() - Camera.x, entity.getY() - Camera.y, entity.width, entity.height, null);
			
			else
				g.drawImage(spriteNovas.get(spritesName), entity.getX() + entity.width - Camera.x, entity.getY() - Camera.y, -entity.width, entity.height, null);
		
		}
	}
}
