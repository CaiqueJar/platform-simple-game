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
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import entities.Enemy;
import entities.Entity;
import entities.Player;
import graphics.EntitiesSprites;
import graphics.Ui;
import world.Camera;
import world.World;

public class Game extends Canvas implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 480, HEIGHT = 272, SCALE = 2;
	private static Image icon;
	
	public InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("Minecraft.ttf");
	public static Font newFont;
	
	
	
	public static Player player;
	
	
	public static List<Entity> miscellaneous;
	
	public static World world;

	public static int actualLevel;

	public static List<Enemy> enemies;
	
	public static BufferedImage background1, background2;
	
	public static String gameState = "menu";
	private MenuScreen menuScreen;
	private Present present;
	private GameoverScreen gameoverScreen;
	
	
	
	public Game() {
		this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		this.addKeyListener(this);

		new EntitiesSprites();
		
		try {
			newFont = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(30f);
		} catch (FontFormatException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		menuScreen = new MenuScreen();
		present = new Present();
		

		player = new Player(0, 0);
		
		
		enemies = new ArrayList<Enemy>();
		
		miscellaneous = new ArrayList<Entity>();
		
		world = new World("/level1.png");
		//world = new World("/levelPlaceHolder.png");
		//world = new World("/level4.png");
		
		try {
			icon = ImageIO.read(getClass().getResource("/icon.png"));
			background1 = ImageIO.read(getClass().getResource("/mountais.png"));
			background2 = ImageIO.read(getClass().getResource("/clouds.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
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
		
		if(gameState.equals("game")) {
			g.setColor(new Color(0xFF5FCDE4));
			g.fillRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);
	
			
			for(int xx = 0; xx <= background2.getWidth() * 8; xx+=background2.getWidth()*SCALE) {
				g.drawImage(background2, (xx-Camera.x)+player.getX()/8, HEIGHT*SCALE-80-Camera.y, background2.getWidth()*SCALE, background2.getHeight()*SCALE, null);
			
			}
			
			for(int xx = 0; xx <= background1.getWidth() * 8; xx+=background1.getWidth()*SCALE) {
				g.drawImage(background1, xx-Camera.x, HEIGHT*SCALE-Camera.y, background1.getWidth()*SCALE, background1.getHeight()*SCALE, null);
			
			}
		
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
		else if(gameState.equals("menu")) {
			menuScreen.render(g);
		}
		else if(gameState.equals("present")) {
			present.render(g);
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
			player.right = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
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
		}
		
		if(e.getKeyCode() == KeyEvent.VK_Z) {
			player.fire = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			player.right = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
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