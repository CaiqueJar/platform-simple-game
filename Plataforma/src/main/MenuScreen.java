package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MenuScreen {

	public BufferedImage[] menuLogo;
	public BufferedImage[] newGame;
	public BufferedImage[] loadGame;
	public BufferedImage[] optionsLogo;
	public BufferedImage[] exit;

	public boolean animation = true;
	public int frames, maxFrames = 12;
	public int index, maxIndex = 5;
	
	public boolean animationOptions = true;
	public int framesOptions, maxFramesOptions = 4;
	public int indexOptions, maxIndexOptions = 5;
	
	public boolean up, down, enter;
	
	public String[] options = {"New Game", "Load Game", "Options", "Exit"};
	public String actualOption = options[0];
	public int selectedOption = 0;
	
	public MenuScreen() {
		menuLogo = new BufferedImage[6];
		newGame = new BufferedImage[6];
		loadGame = new BufferedImage[6];
		optionsLogo = new BufferedImage[6];
		exit = new BufferedImage[6];

		for(int i = 0; i < 6; i++) {
			try {
				menuLogo[i] = ImageIO.read(getClass().getResource("/MenuSprites/menuLogo/"+(i+1)+".png"));
				newGame[i] = ImageIO.read(getClass().getResource("/MenuSprites/newGame/"+(i+1)+".png"));
				loadGame[i] = ImageIO.read(getClass().getResource("/MenuSprites/loadGame/"+(i+1)+".png"));
				optionsLogo[i] = ImageIO.read(getClass().getResource("/MenuSprites/options/"+(i+1)+".png"));
				exit[i] = ImageIO.read(getClass().getResource("/MenuSprites/exit/"+(i+1)+".png"));

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void tick() {
		//Sound.menuSong.loop();
		if(up) {
			Sound.menuClick.play();
			up = false;
			selectedOption--;
			if(selectedOption < 0) selectedOption = options.length-1;
			
			actualOption = options[selectedOption];
		}
		else if(down) {
			Sound.menuClick.play();
			down = false;
			selectedOption++;
			if(selectedOption > options.length-1) selectedOption = 0;

			actualOption = options[selectedOption];
		}
		else if(enter) {
			enter = false;
			switch(actualOption) {
				case "New Game":
					//Game.gameState = "level";
					
					Game.gameState = "mapSelecter";

					Game.actualLevel = 1;
					break;
				
				case "Exit":
					System.exit(1);
					break;
			}
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE);
		
		if(animation) {
			frames++;
			if(frames == maxFrames) {
				frames = 0;
				index++;
				if(index == maxIndex) {
					animation = false;
					index = menuLogo.length-2;
				}
			}
		}
		
		framesOptions++;
		if(framesOptions == maxFramesOptions) {
			framesOptions = 0;
			indexOptions++;
			if(indexOptions == maxIndexOptions) {
				indexOptions = 0;
			}
		}

		g.drawImage(menuLogo[index], (Game.WIDTH/2)+(menuLogo[index].getWidth()/2)-15, -100, menuLogo[index].getWidth()*3, menuLogo[index].getHeight()*3, null);
		
		if(animation == false) {
			if(actualOption.equals("New Game")) {
				g.drawImage(newGame[indexOptions], Game.WIDTH-67-23, 196, null);
				g.drawImage(loadGame[5], Game.WIDTH-70-25, 350 - 50, null);
				g.drawImage(optionsLogo[5], Game.WIDTH-50-29, 450 - 100, null);
				g.drawImage(exit[5], Game.WIDTH-10-34, 500 - 100, null);

			}
			else if(actualOption.equals("Load Game")) {
				g.drawImage(newGame[5], Game.WIDTH-60-23, 250, null);
				g.drawImage(loadGame[indexOptions], Game.WIDTH-100-25, 296 - 50, null);
				g.drawImage(optionsLogo[5], Game.WIDTH-50-29, 450 - 100, null);
				g.drawImage(exit[5], Game.WIDTH-10-34, 500 - 100, null);

			}
			else if(actualOption.equals("Options")) {
				g.drawImage(newGame[5], Game.WIDTH-60-23, 250, null);
				g.drawImage(loadGame[5], Game.WIDTH-70-25, 350 - 50, null);
				g.drawImage(optionsLogo[indexOptions], Game.WIDTH-60-29, 396 - 100, null);
				g.drawImage(exit[5], Game.WIDTH-10-34, 500 - 100, null);

			}
			else if(actualOption.equals("Exit")) {
				g.drawImage(newGame[5], Game.WIDTH-60-23, 250, null);
				g.drawImage(loadGame[5], Game.WIDTH-70-25, 350 - 50, null);
				g.drawImage(optionsLogo[5], Game.WIDTH-50-29, 450 - 100, null);
				g.drawImage(exit[indexOptions], Game.WIDTH-90-34, 445 - 100, null);

			}
		}
		
	}
}
