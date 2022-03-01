package graphics;

import java.awt.image.BufferedImage;

public class EntitiesSprites {

	private Spritesheet entitiesSpritesheet;
	private Spritesheet bossSpritesheet;

	public static BufferedImage[] sprStop;
	public static BufferedImage[] sprWallking;
	public static BufferedImage sprJumping;
	public static BufferedImage sprFalling;
	public static BufferedImage sprTakingDamage;
	public static BufferedImage sprWin;
	
	public static BufferedImage[] sprFireBall;
	
	public static BufferedImage[] sprEnemy1Wallking;
	public static BufferedImage sprEnemy1Death;
	public static BufferedImage sprEnemy1Falling;

	public static BufferedImage[] sprEnemy2Wallking;
	
	public static BufferedImage[] sprUiHearts;
	
	public static BufferedImage[] sprRedFlags;
	public static BufferedImage[] sprBlueFlags;
	
	public static BufferedImage[] sprSmoke;
	
	
	
	public static BufferedImage[] bossWalking;
	public static BufferedImage[] bossAttacking;
	public static BufferedImage bossTakingDamage;
	
	public EntitiesSprites() {
		entitiesSpritesheet = new Spritesheet("/player.png");
		
		sprWallking = new BufferedImage[6];
		sprStop = new BufferedImage[4];
		
		sprEnemy1Wallking = new BufferedImage[6];
		sprEnemy2Wallking = new BufferedImage[6];

		sprUiHearts = new BufferedImage[3];
		
		sprRedFlags = new BufferedImage[4];
		sprBlueFlags = new BufferedImage[4];

		sprFireBall = new BufferedImage[2];
		
		sprSmoke = new BufferedImage[8];
		
		for (int i = 0; i < 6; i++) {
			sprWallking[i] = entitiesSpritesheet.getSprite(0 + (i * 16), 16, 16, 16);
			sprEnemy1Wallking[i] = entitiesSpritesheet.getSprite(64 + (i * 16), 0, 16, 16);
			sprEnemy2Wallking[i] = entitiesSpritesheet.getSprite(64 + (i * 16), 48, 16, 16);

		}
		
		for (int i = 0; i < sprStop.length; i++)
			sprStop[i] = entitiesSpritesheet.getSprite(0 + (i * 16), 0, 16, 16);
		
		for (int i = 0; i < sprUiHearts.length; i++)
			sprUiHearts[i] = entitiesSpritesheet.getSprite(112 + (i * 16), 144, 16, 16);
		
		for (int i = 0; i < sprRedFlags.length; i++) {
			sprRedFlags[i] = entitiesSpritesheet.getSprite(0 + (i * 16), 144, 16, 16);
			sprBlueFlags[i] = entitiesSpritesheet.getSprite(0 + (i * 16), 128, 16, 16);

		}
		
		for(int i = 0; i < sprFireBall.length; i++) {
			sprFireBall[i] = entitiesSpritesheet.getSprite(0+(i*16), 64, 16, 16);
		}
		
		for (int i = 0; i < sprSmoke.length; i++) {
			sprSmoke[i] = entitiesSpritesheet.getSprite(0 + (i * 16), 112, 16, 16);
		
		}
		
		sprJumping = entitiesSpritesheet.getSprite(0, 32, 16, 16);
		sprFalling = entitiesSpritesheet.getSprite(16, 32, 16, 16);
		sprTakingDamage = entitiesSpritesheet.getSprite(48, 32, 16, 16);
		sprWin = entitiesSpritesheet.getSprite(64, 32, 16, 16);
		sprEnemy1Death = entitiesSpritesheet.getSprite(96, 16, 16, 16);
		sprEnemy1Falling = entitiesSpritesheet.getSprite(112, 16, 16, 16);
		
		
		
		bossSpritesheet = new Spritesheet("/Enemies&Boss.png");
				
		bossWalking = new BufferedImage[7];
		bossAttacking = new BufferedImage[6];
		
		bossTakingDamage = bossSpritesheet.getSprite(192, 32, 32, 32);
		
		for(int i = 0; i < bossWalking.length; i++) {
			bossWalking[i] = bossSpritesheet.getSprite(0 + (i * 32), 0, 32, 32);
		}
		for(int i = 0; i < bossAttacking.length; i++) {
			bossAttacking[i] = bossSpritesheet.getSprite(0 + (i * 32), 32, 32, 32);
		}
		
		
	}
}
