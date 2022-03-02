package entities;

import java.awt.Graphics;

import graphics.Animation;
import graphics.EntitiesSprites;
import main.Game;
import world.World;

public class Enemy1 extends Enemy {

	public boolean dead, walking = true, remove, canTick;
	private int dir = -1;

	public int deadCoolDown = 0;

	public double spd = 1;

	public double grvt = 0.4;
	public double spdFalling = 2;
	public double maxSpdGrvt = 6;
	public boolean falling;
	
	private Animation sprites;

	public Enemy1(int x, int y) {
		super(x, y, 48, 48);
		// TODO Auto-generated constructor stub

		setMwidth(getWidth() - 16);
		setMheight(getHeight() - 16);
		
		sprites = new Animation(this);
		sprites.spritesNovas.put("walking", EntitiesSprites.sprEnemy1Wallking);
		sprites.spriteNovas.put("falling", EntitiesSprites.sprEnemy1Falling);
		sprites.spriteNovas.put("dead", EntitiesSprites.sprEnemy1Death);

	}

	public void tick() {
		canTick = true;
		if (canTick) {
			if (walking) {
				try {
					if (!World.isFree((int) (getX() - spd), getY())) {
						dir = 1;
					} else if (!World.isFree((int) (getX() + spd), getY())) {
						dir = -1;
					}
					
					x+=spd*dir;
				} catch (ArrayIndexOutOfBoundsException error) {
					Game.enemies.remove(this);
					return;
				}
			}

			else if (dead) {
				super.dead = true;
				walking = false;
				remover(0.5);
			}
		}
		
		updateMaskCordenate(8, 16);
		falling();
	}

	public void falling() {
		try {
			if (World.isFree(getX(), (int) (y + spdFalling))) {
				falling = true;

				y += spdFalling;
				spdFalling += grvt;

				if (spdFalling >= maxSpdGrvt) {
					spdFalling = maxSpdGrvt;
				}
			} else {
				if (World.isFree(getX(), (int) (y + 1))) {
					y += 1;
				}
				falling = false;
				spdFalling = 2;
			}
		} catch (ArrayIndexOutOfBoundsException error) {
			Game.enemies.remove(this);
			return;
		}
	}

	public void render(Graphics g) {
		sprites.animationTick();

		if (walking) {
			if (dir == -1)
				if(falling) 
					sprites.renderTeste(g, "falling", 10, false);

				else
					sprites.renderTeste(g, "walking", 8, false);

			else if (dir == 1) {
				if(falling) 
					sprites.renderTeste(g, "falling", 10, true);

				else
					sprites.renderTeste(g, "walking", 8, true);

			
			}
		} else if (dead)
			//g.drawImage(EntitiesSprites.sprEnemy1Death, getX() - Camera.x, getY() - Camera.y, getWidth(), getHeight(), null);
			sprites.renderTeste(g, "dead", 10, false);
	}
}