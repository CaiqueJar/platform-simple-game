package entities;

import java.awt.Color;
import java.awt.Graphics;

import graphics.Animation;
import graphics.EntitiesSprites;
import main.Game;
import main.Sound;
import world.Camera;
import world.World;

public class Boss extends Enemy {

	private Animation sprites;
	
	public int lifes = 3;
	
	public double spd = 2, attackImpulse = 15, loosingForce = 0.5;
	public boolean impulse = false, attack, takingDamage, resetAnim, increaseSpd, dead;
	
	public boolean coolDown;
	public int c_Counter, maxC_Counter = 60*2;
	
	public int dir = 1;
	
	public int state = 0;
	public int disX;

	public double deadJump = 15, deadLoose = 0.5;
	
	
	public Boss(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	
		sprites = new Animation(this);
		sprites.spritesNovas.put("walking", EntitiesSprites.bossWalking);
		sprites.spritesNovas.put("attacking", EntitiesSprites.bossAttacking);
		sprites.spriteNovas.put("takingDamage", EntitiesSprites.bossTakingDamage);

		setMwidth(190);
		setMheight(90);
		
		
	}
	
	public void tick() {
		updateMaskCordenate(0, 100);
		
		if(dead == false) {
			disX = Math.abs((Game.player.getX() + Game.player.getMwidth() - 4) - (getX() + getMwidth() / 2));
			
			
			if(attack == false && takingDamage == false) {
				if(disX <= 150 && !coolDown) state = 1;
				else state = 0;
			}
	
			if(state == 0 && !impulse) {
				// Andando
	
				if(resetAnim) resetAnim = false;
				
				if(dir == 1) {
					if(!World.isFree((int) (getMaskx() + getMwidth() - 48), getY())) {
						dir = -1;
					}
					else {
						x+=spd;
		
					}
					
				}
				else {
					if(!World.isFree((int) (getX() - spd), getY())) {
						dir = 1;
					}
					else {
						x-=spd;
	
					}
				}
		
			}
			else if (state == 1 && !impulse) {
				// Ataque
				attack = true;
				
				if(resetAnim == false) {
					sprites.reset();
					resetAnim = true;
				}
				
				if(sprites.getIndex() >= 3) {
					impulse = true;
					
				}
			}
			
			for(int i = 0; i < Player.fireBalls.size(); i++) {
				Entity fireball = Player.fireBalls.get(i);
				
				if(Entity.isColidding(this, fireball)) {
					if(attack) {
						if(sprites.getIndex() == 2) {
							takingDamage = true;
							state = 2;
							attack = false;
							impulse = false;
							attackImpulse = 15;
						}
					}
					Player.fireBalls.remove(fireball);

				}
			}
			
			if(takingDamage) {
				state = 2;
				coolDown = true;
			}
	
			if(impulse) {
				if(World.isFree((int)(getMaskx()+getMwidth() + (attackImpulse*dir)), getY())) {
					x = x + (attackImpulse*dir);
					attackImpulse -= loosingForce;
					
					if(attackImpulse <= 0) {
						impulse = false;
						attack = false;
						attackImpulse = 15;
						sprites.reset();
						coolDown = true;
					}
				}
				else {
					attackImpulse = 15;
					impulse = false;
					attack = false;
	
					coolDown = true;
				}
			}
			
			if(coolDown) {
				c_Counter++;
				if(c_Counter == maxC_Counter) {
					c_Counter = 0;
					coolDown = false;
					
					if(takingDamage) {
						takingDamage = false;
						state = 0;
						coolDown = true;
						increaseSpd = true;
						lifes--;
	
					}
				}
			}
			
			
			if(increaseSpd) {
				increaseSpd = false;
				spd+=2;
			}
		}
		else {
			Sound.diarrhea.playOnce();

			y-=deadJump;
			deadJump -= deadLoose;
		}
		
		if(lifes <= 0) {
			dead = true;
			
		}
		
	}
	
	public void render(Graphics g) {
		if(dead == false) {
			sprites.animationTick();
			if(state == 0) {
				if(dir == -1)
					sprites.renderTeste(g, "walking", 5, false);
				
				else
					sprites.renderTeste(g, "walking", 5, true);
			}
			else if(state == 1) {
				
				if(dir == -1)
					sprites.renderTeste(g, "attacking", 10, false);
				
				else
					sprites.renderTeste(g, "attacking", 10, true);
			}
			else if(state == 2) {
				
				if(dir == -1)
					sprites.renderTeste(g, "takingDamage", 5, false);
				
				else
					sprites.renderTeste(g, "takingDamage", 5, true);
			}
		}
		else {
			
			if(dir == -1)
				sprites.renderTeste(g, "takingDamage", 5, false);
			
			else
				sprites.renderTeste(g, "takingDamage", 5, true);
		}
		
		g.setColor(new Color(225, 0, 0, 150));
		g.fillRect(getMaskx() - Camera.x, getMasky() - Camera.y, getMwidth(), getMheight());
		//g.fillRect((Game.player.getX() + Game.player.getMwidth() - 4), Game.player.getY() - Camera.y, 4, 4);

	}
}
