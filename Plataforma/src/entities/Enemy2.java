package entities;

import java.awt.Color;
import java.awt.Graphics;

import graphics.Animation;
import graphics.EntitiesSprites;
import world.World;

public class Enemy2 extends Enemy {

	public Animation sprWalking;

	public boolean right = true, left, dead, walking = true, remove, canTick;

	public int deadCoolDown = 0;

	public double spd = 1;

	public double maxJumpSpd = 12;
	public double jumpSpd = maxJumpSpd;
	public double weight = 0.5;
	public boolean jump, jumping = true;

	public Enemy2(int x, int y) {
		super(x, y, 48, 48);
		// TODO Auto-generated constructor stub

		sprWalking = new Animation(this);
		sprWalking.spritesNovas.put("walking", EntitiesSprites.sprEnemy2Wallking);
		
		setMaskx(x);
		setMasky(y);
		setMwidth(48);
		setMheight(48);
	}

	public void tick() {
		canTick = true;
		if (canTick) {
			
			if (walking) {

				if (!World.isFree((int) (getX() - spd), getY())) {
					left = false;
					right = true;
				} else if (!World.isFree((int) (getX() + spd), getY())) {
					left = true;
					right = false;
				}
				

				if (left) {
					x -= spd;
				} else if (right) {
					x += spd;
				}
			}
			
			else if (dead) {
				walking = false;
				deadCoolDown++;
				if (deadCoolDown == 60 * 0.5) {
					dead = false;
					remove = true;
				}
			}
		}
		
		updateMaskCordenate();
		fallingAndJump();
	}
	
	public void updateMaskCordenate() {
		maskx = x;
		masky = y;

	}
	
	public void fallingAndJump() {
		
		if (jumping && World.isFree(getX(), (int) (y - jumpSpd))) {
			y -= jumpSpd;
			jumpSpd -= weight;
			
		} else if(jumping && !World.isFree(getX(), (int) (y - jumpSpd)) && jumpSpd >= 0) {
			jumpSpd *= -1;
			
		} else {
			jumpSpd = maxJumpSpd;

		}
		
	}

	public void render(Graphics g) {
		sprWalking.animationTick();

		sprWalking.renderTeste(g, "walking", 10, true);
		/*
		if(left)
			sprWalking.render(g, false);
		
		else if(right)
			sprWalking.render(g, true);
		*/
		
		g.setColor(new Color(225, 0, 0, 150));
		//g.fillRect(getMaskx()-Camera.x, getMasky()-Camera.y, getMwidth(), getMheight());
	}
}
