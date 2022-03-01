package main;

import java.awt.Graphics;
import java.awt.Color;

public class Present {

	private int timer, difference;
	private boolean voltar;
	private int showWhat = 0;
	
	public void render(Graphics g) {
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE);
		

		if(!voltar) {
			if(difference < 40) {
				timer++;
				if(timer % 6 == 0) {
					difference++;
				}
			}
			else {
				voltar = true;
			}
		}
		else {
			if(difference > 0) {
				timer--;
				if(timer % 6 == 0) {
					difference--;
				}
			}
			else {
				timer++;
				voltar = false;
				if(showWhat == 2) {
					Game.gameState = "menu";
				}
				showWhat++;
			}
		}
		

		switch(showWhat) {
		case 0:
			g.setColor(new Color(difference*6+15, difference*6+15, difference*6+15));
			g.setFont(Game.newFont);
			g.drawString("FEITO POR DIAS", Game.WIDTH * Game.SCALE / 2 - 150, 200);
			break;
			
		case 1:
			g.setColor(new Color(difference*6+15, difference*6+15, difference*6+15));
			g.setFont(Game.newFont);
			g.drawString("ARTES DO GOOGLE", Game.WIDTH * Game.SCALE / 2 - 150, 200);
			break;
			
		case 2:
			g.setColor(new Color(difference*6+15, difference*6+15, difference*6+15));
			g.setFont(Game.newFont);
			g.drawString("SONS TAMBEM", Game.WIDTH * Game.SCALE / 2 - 150, 200);
			break;
			
		}
	}
}
