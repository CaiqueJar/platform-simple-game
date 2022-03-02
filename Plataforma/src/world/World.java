package world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import entities.Boss;
import entities.Enemy;
import entities.Enemy1;
import entities.Enemy2;
import entities.Entity;
import entities.Flag;
import entities.Player;
import graphics.Background;
import main.Game;

public class World {

	public static Tiles[][] tiles;

	public static int WIDTH, HEIGHT;
	public static final int TILE_SIZE = 48;

	public static boolean newLevel;

	public World(String path) {
		try {
			Game.player = new Player(0, 0);
			Game.enemies = new ArrayList<Enemy>();
			Game.miscellaneous = new ArrayList<Entity>();
			
			Game.backgrounds = new Background();
			
			BufferedImage map = ImageIO.read(getClass().getResource(path));

			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();

			int[] pixels = new int[WIDTH * HEIGHT];

			tiles = new Tiles[WIDTH][HEIGHT];

			map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());

			for (int xx = 0; xx < tiles.length; xx++) {
				for (int yy = 0; yy < tiles[xx].length; yy++) {
					int pixelAtual = pixels[xx + (yy * WIDTH)];

					if (pixelAtual == 0xFF000000)
						continue;

					else if (pixelAtual == 0xFF0000FF) {
						Game.player.setX(xx * 48);
						Game.player.setY(yy * 48);
					
					}
					
					else if (pixelAtual == 0xFFF83800) {
						Game.enemies.add(new Enemy1(xx * 48, yy * 48));
					}
					else if (pixelAtual == 0xFFE45C10) {
						Game.enemies.add(new Enemy2(xx * 48, yy * 48));

					}
					else if (pixelAtual == 0xFFFFFF00) {
						//Game.enemy3.add(new Enemy3(xx * 48, yy * 48, null));
					
					}
					else if (pixelAtual == 0xFFE40058) {
						Game.miscellaneous.add(new Flag(xx * 48, yy * 48, 48, 48));
					
					}
					else if (pixelAtual == 0xFF00FF00) {
						//Game.boss = new Boss(xx * 48 - 48, yy * 48 - 48, 96*2, 96*2);
						Game.enemies.add(new Boss(xx * 48 - 48, yy * 48 - 48, 96*2, 96*2));
					}

					try {
						if (pixelAtual == 0xFFFFFFFF
								&& pixels[xx + ((yy - 1) * WIDTH)] != 0xFFFFFFFF
								&& pixels[xx - 1 + (yy * WIDTH)] != 0xFFFFFFFF
								&& pixels[xx + 1 + (yy * WIDTH)] != 0xFF000000
								&& pixels[xx + ((yy + 1) * WIDTH)] != 0xFF000000) {

							tiles[xx][yy] = new Grass(xx * 48, yy * 48, TilesSpritesheet.grassSprites[0]);
						
						}
						else if (pixelAtual == 0xFFFFFFFF
								&& pixels[xx + ((yy - 1) * WIDTH)] != 0xFFFFFFFF
								&& pixels[xx - 1 + (yy * WIDTH)] != 0xFF000000
								&& pixels[xx + 1 + (yy * WIDTH)] != 0xFF000000
								&& pixels[xx + ((yy + 1) * WIDTH)] != 0xFF000000) {

							tiles[xx][yy] = new Grass(xx * 48, yy * 48, TilesSpritesheet.grassSprites[1]);
						
						}
						else if (pixelAtual == 0xFFFFFFFF
								&& pixels[xx + ((yy - 1) * WIDTH)] != 0xFFFFFFFF
								&& pixels[xx - 1 + (yy * WIDTH)] != 0xFF000000
								&& pixels[xx + 1 + (yy * WIDTH)] != 0xFFFFFFFF
								&& pixels[xx + ((yy + 1) * WIDTH)] != 0xFF000000) {

							tiles[xx][yy] = new Grass(xx * 48, yy * 48, TilesSpritesheet.grassSprites[2]);
						
						}
						else if (pixelAtual == 0xFFFFFFFF
								&& pixels[xx + ((yy - 1) * WIDTH)] != 0xFFFFFFFF
								&& pixels[xx - 1 + (yy * WIDTH)] != 0xFF000000
								&& pixels[xx + 1 + (yy * WIDTH)] != 0xFFFFFFFF
								&& pixels[xx + ((yy + 1) * WIDTH)] != 0xFFFFFFFF) {

							tiles[xx][yy] = new Grass(xx * 48, yy * 48, TilesSpritesheet.floatingGrassPlatforms[2]);
						
						}
						else if (pixelAtual == 0xFFFFFFFF
								&& pixels[xx + ((yy - 1) * WIDTH)] != 0xFFFFFFFF
								&& pixels[xx - 1 + (yy * WIDTH)] != 0xFFFFFFFF
								&& pixels[xx + 1 + (yy * WIDTH)] != 0xFF000000
								&& pixels[xx + ((yy + 1) * WIDTH)] != 0xFFFFFFFF) {

							tiles[xx][yy] = new Grass(xx * 48, yy * 48, TilesSpritesheet.floatingGrassPlatforms[0]);
						
						}
						else if (pixelAtual == 0xFFFFFFFF
								&& pixels[xx + ((yy - 1) * WIDTH)] != 0xFFFFFFFF
								&& pixels[xx - 1 + (yy * WIDTH)] != 0xFF000000
								&& pixels[xx + 1 + (yy * WIDTH)] != 0xFF000000
								&& pixels[xx + ((yy + 1) * WIDTH)] != 0xFFFFFFFF) {

							tiles[xx][yy] = new Grass(xx * 48, yy * 48, TilesSpritesheet.floatingGrassPlatforms[1]);
						
						}
						else if (pixelAtual == 0xFFFFFFFF
								&& pixels[xx + ((yy - 1) * WIDTH)] != 0xFF000000
								&& pixels[xx - 1 + (yy * WIDTH)] != 0xFFFFFFFF
								&& pixels[xx + 1 + (yy * WIDTH)] != 0xFFFFFFFF
								&& pixels[xx + ((yy + 1) * WIDTH)] != 0xFF000000) {

							tiles[xx][yy] = new Grass(xx * 48, yy * 48, TilesSpritesheet.dirtColumnSprite);
						
						}
						else if (pixelAtual == 0xFFFFFFFF
								&& pixels[xx + ((yy - 1) * WIDTH)] != 0xFF000000
								&& pixels[xx - 1 + (yy * WIDTH)] != 0xFFFFFFFF
								&& pixels[xx + 1 + (yy * WIDTH)] != 0xFF000000
								&& pixels[xx + ((yy + 1) * WIDTH)] != 0xFFFFFFFF) {

							tiles[xx][yy] = new Grass(xx * 48, yy * 48, TilesSpritesheet.dirtSprites[3]);
						
						}
						else if (pixelAtual == 0xFFFFFFFF
								&& pixels[xx + ((yy - 1) * WIDTH)] != 0xFF000000
								&& pixels[xx - 1 + (yy * WIDTH)] != 0xFF000000
								&& pixels[xx + 1 + (yy * WIDTH)] != 0xFF000000
								&& pixels[xx + ((yy + 1) * WIDTH)] != 0xFFFFFFFF) {

							tiles[xx][yy] = new Grass(xx * 48, yy * 48, TilesSpritesheet.dirtSprites[4]);
						
						}
						else if (pixelAtual == 0xFFFFFFFF
								&& pixels[xx + ((yy - 1) * WIDTH)] != 0xFF000000
								&& pixels[xx - 1 + (yy * WIDTH)] != 0xFF000000
								&& pixels[xx + 1 + (yy * WIDTH)] != 0xFFFFFFFF
								&& pixels[xx + ((yy + 1) * WIDTH)] != 0xFFFFFFFF) {

							tiles[xx][yy] = new Grass(xx * 48, yy * 48, TilesSpritesheet.dirtSprites[5]);
						}
						else if (pixelAtual == 0xFFFFFFFF
								&& pixels[xx + ((yy - 1) * WIDTH)] != 0xFF000000
								&& pixels[xx - 1 + (yy * WIDTH)] != 0xFF000000
								&& pixels[xx + 1 + (yy * WIDTH)] != 0xFF000000
								&& pixels[xx + ((yy + 1) * WIDTH)] != 0xFF000000) {

							tiles[xx][yy] = new Grass(xx * 48, yy * 48, TilesSpritesheet.dirtSprites[1]);
						}
						else if (pixelAtual == 0xFFFFFFFF
								&& pixels[xx + ((yy - 1) * WIDTH)] != 0xFF000000
								&& pixels[xx - 1 + (yy * WIDTH)] != 0xFFFFFFFF
								&& pixels[xx + 1 + (yy * WIDTH)] != 0xFF000000
								&& pixels[xx + ((yy + 1) * WIDTH)] != 0xFF000000) {

							tiles[xx][yy] = new Grass(xx * 48, yy * 48, TilesSpritesheet.dirtSprites[0]);
						
							
						}
						else if (pixelAtual == 0xFFFFFFFF
								&& pixels[xx + ((yy - 1) * WIDTH)] != 0xFF000000
								&& pixels[xx - 1 + (yy * WIDTH)] != 0xFFFFFFFF
								&& pixels[xx + 1 + (yy * WIDTH)] != 0xFFFFFFFF
								&& pixels[xx + ((yy + 1) * WIDTH)] != 0xFFFFFFFF) {

							tiles[xx][yy] = new Grass(xx * 48, yy * 48, TilesSpritesheet.dirtSprites[0]);
						}
						else if (pixelAtual == 0xFFFFFFFF
								&& pixels[xx + ((yy - 1) * WIDTH)] != 0xFFFFFFFF
								&& pixels[xx - 1 + (yy * WIDTH)] != 0xFFFFFFFF
								&& pixels[xx + 1 + (yy * WIDTH)] != 0xFFFFFFFF
								&& pixels[xx + ((yy + 1) * WIDTH)] != 0xFF000000) {

							tiles[xx][yy] = new Grass(xx * 48, yy * 48, TilesSpritesheet.dirtSprites[0]);
						}
					} catch(ArrayIndexOutOfBoundsException error) {
						tiles[xx][yy] = new Grass(xx * 48, yy * 48, TilesSpritesheet.grassSprites[1]);
						continue;
					}
					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean isFree(int xnext, int ynext) {

		int x1 = (xnext / TILE_SIZE);
		int y1 = (ynext / TILE_SIZE);

		int x2 = ((xnext + TILE_SIZE - 1) / TILE_SIZE);
		int y2 = (ynext / TILE_SIZE);

		int x3 = (xnext / TILE_SIZE);
		int y3 = ((ynext + TILE_SIZE - 1) / TILE_SIZE);

		int x4 = ((xnext + TILE_SIZE - 1) / TILE_SIZE);
		int y4 = ((ynext + TILE_SIZE - 1) / TILE_SIZE);

		return !(tiles[x1][y1] instanceof Grass || tiles[x2][y2] instanceof Grass || tiles[x3][y3] instanceof Grass
				|| tiles[x4][y4] instanceof Grass);
	}

	public void nextLevel() {
		if (newLevel == true) {
			newLevel = false;
			Game.actualLevel++;

			Game.enemies.clear();
			Game.miscellaneous.clear();

			Game.enemies = new ArrayList<Enemy>();
			Game.miscellaneous = new ArrayList<Entity>();

			Game.player = new Player(10, 10);

			Game.world = new World("/level" + Game.actualLevel + ".png");
		}
	}

	public void render(Graphics g) {
		for (int xx = 0; xx < tiles.length; xx++) {
			for (int yy = 0; yy < tiles[xx].length; yy++) {
				Tiles tile = tiles[xx][yy];
				if (tile == null)
					continue;

				tile.render(g);
			}
		}
	}
}