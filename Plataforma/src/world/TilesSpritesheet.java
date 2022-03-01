package world;

import java.awt.image.BufferedImage;

import graphics.Spritesheet;

public class TilesSpritesheet {

	protected static Spritesheet spritesheet = new Spritesheet("/tileset.png");

	public static BufferedImage[] grassSprites = { spritesheet.getSprite(0, 0, 16, 16),
			spritesheet.getSprite(16, 0, 16, 16), spritesheet.getSprite(32, 0, 16, 16) };

	public static BufferedImage[] dirtSprites = { spritesheet.getSprite(0, 16, 16, 16),
			spritesheet.getSprite(16, 16, 16, 16), spritesheet.getSprite(32, 16, 16, 16),
			spritesheet.getSprite(0, 32, 16, 16), spritesheet.getSprite(16, 32, 16, 16), 
			spritesheet.getSprite(32, 32, 16, 16),
			spritesheet.getSprite(48, 32, 16, 16), spritesheet.getSprite(64, 32, 16, 16), 
			spritesheet.getSprite(80, 32, 16, 16)};
	
	
	public static BufferedImage dirtColumnSprite = spritesheet.getSprite(48, 64, 16, 16);

	public static BufferedImage[] floatingGrassPlatforms = { spritesheet.getSprite(0, 48, 16, 16),
			spritesheet.getSprite(16, 48, 16, 16), spritesheet.getSprite(32, 48, 16, 16) };
	
	public TilesSpritesheet(String typeOfLevel) {
		
	}

}
