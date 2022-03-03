package entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import graphics.Animation;
import graphics.EntitiesSprites;
import main.Game;
import main.GameoverScreen;
import main.Sound;
import world.Camera;
import world.World;

public class Player extends Entity {

	private Animation sprites;
	private int maxFrame;

	public boolean right, left;
	public int lastDirection = 1;
	public String whatRUDoing = "stop";

	public double spd = 3.5;

	public int maxLife = 3, life = maxLife;


	public boolean win, showWinAnimation;
	public int winCooldown;

	public boolean newLevel;
	
	public boolean smoke;
	
	public boolean fire;
	
	public static List<FireBall> fireBalls;
	
	public boolean jump, isJumping;
	private double vspd = 0, gravity = 0.8;
	
	public boolean imortal;
	public int imortalTime;
	
	public boolean takingDamage;
	public int takingDamageTime;
	
	public boolean gameOver;
	public double impulse = 15;
	public int nada;
	
	public Player(int x, int y) {
		super(x, y, 48, 48);
		
		sprites = new Animation(this);
		sprites.spritesNovas.put("stop", EntitiesSprites.sprStop);
		sprites.spritesNovas.put("walking", EntitiesSprites.sprWallking);
		sprites.spriteNovas.put("jumping", EntitiesSprites.sprJumping);
		sprites.spriteNovas.put("falling", EntitiesSprites.sprFalling);
		sprites.spriteNovas.put("takingDamage", EntitiesSprites.sprTakingDamage);
		
		setMwidth(getWidth() - 20);
		setMheight(getHeight() - 10);
		
		fireBalls = new ArrayList<FireBall>();

	}

	public void tick() {

		if(gameOver) {
			gameOver();
		}
		else if(win) {
			win();
			fallingAndJump();
		}
		else {
			cameraUpdate();
			moving();
			fallingAndJump();
			updateMaskCordenate(10, 0);
			takingDamage();
			shoot();

		} 
	}

	public void cameraUpdate() {
		Camera.x = Camera.clamp(getX() - Game.WIDTH, 48, World.WIDTH * 24 - Game.WIDTH);
		Camera.y = Camera.clamp(getY() - Game.HEIGHT, 0, (World.HEIGHT) * 32 - Game.HEIGHT);
		
		switch(Game.actualLevel) {
		case 1:
			Camera.x = Camera.clamp(getX() - Game.WIDTH, 0, World.WIDTH * 48 - Game.WIDTH);
			Camera.y = Camera.clamp(getY() - Game.HEIGHT, 0, (World.HEIGHT) * 32 - Game.HEIGHT);
			break;
			
		case 2:
			
			break;
		}
	}


	public void moving() {
		if(takingDamage == false) {
			whatRUDoing = "stop";
			
			if(right && World.isFree((int) (x + spd), getY())) {
				x += spd;
				whatRUDoing = "walking";
				lastDirection = 1;
			}
			else if(left && World.isFree((int) (x - spd), getY())) {
				x -= spd;
				whatRUDoing = "walking";
				lastDirection = -1;
			}
		}
	}

	public void fallingAndJump() {
		
		vspd+=gravity;
		if(takingDamage == false) {
			if(!World.isFree((int)x, (int)(y+1)) && jump) {
				vspd = -15; // Altura
				jump = false;
				isJumping = true;
				//Sound.jump.play();
			}
			
			if(World.isFree((int)x, (int)(y-1)))
				isJumping = true;
		}
		if(!World.isFree((int)x, (int)(y+vspd))) {
			int signVsp = 0;

			
			if(vspd >= 0) {
				signVsp = 1;
			}
			else {
				signVsp = -1;
			}
			
			while(World.isFree((int)x, (int)(y+signVsp))) {
				y = y+signVsp;
			}
			vspd = 0;
			isJumping = false;
		}
		
		if(isJumping) {
			if(vspd > 0)
				whatRUDoing = "falling";
			else
				whatRUDoing = "jumping";
			
			
			Entity collision = new Entity(getX() + 10, getY() + 45, 28, 8);

			for (int i = 0; i < Game.enemies.size(); i++) {
				if(String.valueOf(Game.enemies.get(i).getClass()).contains("Enemy1")) {
					Enemy1 enemy1 = (Enemy1) Game.enemies.get(i);
					if (Entity.isColidding(collision, enemy1) && !enemy1.dead) {
						//Sound.hit3.play();
						enemy1.dead = true;
						enemy1.walking = false;
						vspd = -10;

					}
				}
			}
		}
			
		y = y + vspd;
	}

	public void takingDamage() {
		if(imortal == false) {
			for(int i = 0; i < Game.enemies.size(); i++) {
				Enemy enemy = Game.enemies.get(i);
				if(isColidding(this, enemy) && !enemy.dead) {
					life--;
					imortal = true;
					takingDamage = true;
					whatRUDoing = "takingDamage";
					break;
				}
			}
		} 
		else {
			imortalTime++;
			if(imortalTime >= 60*1.5) {
				imortalTime = 0;
				imortal = false;
			}
			
			if(takingDamage == true) {
				takingDamageTime++;
				x -= 0.5*lastDirection;
				if(takingDamageTime >= 60*0.5) {
					takingDamageTime = 0;
					whatRUDoing = "stop";
					takingDamage = false;
				}
			}			
		}
		if(life <= 0 && gameOver == false) {
			gameOver = true;
			Game.gameState = "gameOver";
			Game.gameoverScreen = new GameoverScreen();

		}
	}

	public void gameOver() {
		y-=impulse;
		impulse-=0.5;

	}
	
	public void win() {
		if (win) {
			Sound.song1.stop();

			if (!whatRUDoing.equals("win"))
				whatRUDoing = "stop";

			winCooldown++;
			if (winCooldown >= 60 * 4) {
				showWinAnimation = true;
				whatRUDoing = "win";
				winCooldown++;
				World.newLevel = true;
				
			}
		}
	}
	
	

	public void shoot() {
		if(fire) {
			fire = false;
			if(fireBalls.size() < 3) {
				FireBall fireball = new FireBall(getX(), getY(), 48, 48);

				fireBalls.add(fireball);
			}
		}
		for(int i = 0; i < fireBalls.size(); i++) {
			fireBalls.get(i).tick();
		}
	}
	
	public void render(Graphics g) {
		renderWalk(g);
		renderWin(g);

		for(FireBall fireball : fireBalls) {
			fireball.render(g);
		}
		
		//g.setColor(new Color(225, 0, 0, 150));
		//g.fillRect(getMaskx() - Camera.x, getMasky() - Camera.y, getMwidth(), getMheight());
		
	}

	public void renderWalk(Graphics g) {
		
		switch(whatRUDoing) {
		case "walking":
			maxFrame = 5;
			break;
		case "jumping":
			
			break;
		case "falling":
			
			break;
		case "takingDamage":
			maxFrame = 1;
			break;
		case "stop":
			maxFrame = 10;
			break;
		}

		sprites.animationTick();
		switch (lastDirection) {
		case 1:
			sprites.renderTeste(g, whatRUDoing, maxFrame, false);
			break;

		case -1:
			sprites.renderTeste(g, whatRUDoing, maxFrame, true);
			
		}
	}

	public void renderWin(Graphics g) {
		if (win && showWinAnimation) {
			g.drawImage(EntitiesSprites.sprWin, getX() - Camera.x, getY() - Camera.y, 48, 48, null);
		}
	}
}