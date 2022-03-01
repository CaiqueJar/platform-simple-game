package entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import world.Camera;

public class Entity {

	public double x, y;
	public int width, height;
	
	protected double maskx;
	protected double masky;
	private int mwidth;
	private int mheight;

	public Entity(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		this.maskx = x;
		this.masky = y;
		this.mwidth = width;
		this.mheight = height;
	}

	public int getX() {
		return (int)(x);
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return (int)(y);
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public int getMaskx() {
		return (int)(maskx);
	}

	public void setMaskx(int maskx) {
		this.maskx = maskx;
	}

	public int getMasky() {
		return (int)(masky);
	}

	public void setMasky(int masky) {
		this.masky = masky;
	}

	public int getMwidth() {
		return mwidth;
	}

	public void setMwidth(int mwidth) {
		this.mwidth = mwidth;
	}

	public int getMheight() {
		return mheight;
	}

	public void setMheight(int mheight) {
		this.mheight = mheight;
	}

	public void updateMaskCordenate(int xDifference, int yDifference) {
		setMaskx((int)(x + xDifference));
		setMasky((int)(y + yDifference));

	}
	
	public static boolean isColidding(Entity e1, Entity e2) {
		Rectangle e1Mask = new Rectangle(e1.getMaskx(), e1.getMasky(), e1.mwidth, e1.mheight);
		Rectangle e2Mask = new Rectangle(e2.getMaskx(), e2.getMasky(), e2.mwidth, e2.mheight);
		
		return e1Mask.intersects(e2Mask);
	}
	
	public void tick() {
		// TODO Auto-generated method stub

	}
	
	public void render(Graphics g) {
		//g.drawImage(null, getX() - Camera.x, getY() - Camera.y, width, height, null);
	}
}
