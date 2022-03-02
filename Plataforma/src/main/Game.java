package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import entities.Enemy;
import entities.Entity;
import entities.Player;
import graphics.Background;
import graphics.EntitiesSprites;
import graphics.Ui;
import world.Camera;
import world.World;

public class Game extends Canvas implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 480, HEIGHT = 272, SCALE = 2;
	private static Image icon;
	
	public static InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("Minecraft.ttf");
	public static Font newFont;
	
	public static Player player;
	public static List<Enemy> enemies;
	public static List<Entity> miscellaneous;

	public static Background backgrounds;
	public static World world;

	public static int actualLevel;

	public static String gameState = "present";
	private MenuScreen menuScreen;
	private PresentScreen presentScreen;
	private MapScreen mapScreen;
	public static GameoverScreen gameoverScreen;
	
	public Game() {
		this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		this.addKeyListener(this);

		new EntitiesSprites();
		try {
			newFont = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(30f);
			icon = ImageIO.read(getClass().getResource("/icon.png"));

		} catch (FontFormatException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Criação das telas
		menuScreen = new MenuScreen();
		presentScreen = new PresentScreen();
		mapScreen = new MapScreen();
		gameoverScreen = new GameoverScreen();

	}

	public static void main(String[] args) {
		Game game = new Game();

		JFrame frame = new JFrame("Plataforma");
		frame.add(game);
		
		frame.setIconImage(icon);
		
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		game.run();
		

	}

	public void tick() {
		
		if(gameState.equals("game")) {
			//Sound.menuSong.stop();
			
			world.nextLevel();
			player.tick();
			
			if(player.gameOver == false){
				for(int i = 0; i < enemies.size(); i++) {
					Enemy enemy = enemies.get(i);
					enemy.tick();
				}
				
				
				for(int i = 0; i < miscellaneous.size(); i++) {
					Entity thing = miscellaneous.get(i);
					if(thing.getX() <= Camera.x+WIDTH*SCALE) {
						thing.tick();
					}
				}
			}
		}
		else if(gameState.equals("menu")) {
			menuScreen.tick();
			
		}
		else if(gameState.equals("mapSelecter")) {
			mapScreen.tick();
		}
		else if(gameState.equals("gameOver")) {
			gameoverScreen.tick();

		}
		
	}

	public void render() {
		Toolkit.getDefaultToolkit().sync();
		
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		//Graphics2D g2d = (Graphics2D) g;
		
		if(gameState.equals("game") || gameState.equals("gameOver")) {
			g.setColor(new Color(0xFF5FCDE4));
			g.fillRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);
	
			backgrounds.render(g);
			world.render(g);
			player.render(g);
			
			for(int i = 0; i < enemies.size(); i++) {
				Enemy enemy = enemies.get(i);
				enemy.render(g);
			}
			
			
			for(Entity thing : miscellaneous) {
				thing.render(g);
			}
			
			
			Ui.render(g);
			gameoverScreen.render(g);

			
		}
		else if(gameState.equals("level")) {
			
		}
		else if(gameState.equals("mapSelecter")) {
			mapScreen.render(g);
		}
		else if(gameState.equals("menu")) {
			menuScreen.render(g);
		}
		else if(gameState.equals("present")) {
			presentScreen.render(g);
		}
		bs.show();
	}
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			tick();
			render();

			try {
				Thread.sleep(1000 / 60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			if(gameState.equals("mapSelecter")) {
				mapScreen.right = true;
				return;
			}
			player.right = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			if(gameState.equals("mapSelecter")) {
				mapScreen.left = true;
				return;
			}
			player.left = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			
			if(player.isJumping == false)
				player.jump = true;
		}
		
		
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			if(gameState.equals("menu")) {
				menuScreen.up = true;
			}
			
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			if(gameState.equals("menu")) {
				menuScreen.down = true;
			}
			
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(gameState.equals("menu")) {
				menuScreen.enter = true;
			}
			else if(gameState.equals("mapSelecter")) {
				mapScreen.enter = true;
			}
			else if(gameState.equals("gameOver")) {
				gameoverScreen.enter = true;
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_Z) {
			player.fire = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			if(gameState.equals("game")) 
				player.right = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			if(gameState.equals("game")) 
				player.left = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			// player.jump = true;
		}
		
		
		if(e.getKeyCode() == KeyEvent.VK_UP) {

		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN) {

		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}