package nl.tudelft.jpacman.sprite;import coverageApi.Collect;import java.awt.Graphics; public class EmptySprite implements Sprite {

	@Override
	public void draw(Graphics g, int x, int y, int width, int height) {Collect.Hit("EmptySprite.java","draw(Graphics g, int x, int y, int width, int height)");Collect.Hit("EmptySprite.java","draw(Graphics g, int x, int y, int width, int height)", "4828");}

	@Override
	public Sprite split(int x, int y, int width, int height) {Collect.Hit("EmptySprite.java","split(int x, int y, int width, int height)"); Collect.Hit("EmptySprite.java","split(int x, int y, int width, int height)", "441");return new EmptySprite() ; }

	@Override
	public int getWidth() {Collect.Hit("EmptySprite.java","getWidth()"); Collect.Hit("EmptySprite.java","getWidth()", "514");return 0 ; }

	@Override
	public int getHeight() {Collect.Hit("EmptySprite.java","getHeight()"); Collect.Hit("EmptySprite.java","getHeight()", "572");return 0 ; }

}
