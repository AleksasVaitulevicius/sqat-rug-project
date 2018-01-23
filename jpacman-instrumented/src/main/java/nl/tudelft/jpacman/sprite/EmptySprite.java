package nl.tudelft.jpacman.sprite;import coverageApi.Collect;import java.awt.Graphics; public class EmptySprite implements Sprite {

	@Override
	public void draw(Graphics g, int x, int y, int width, int height) {Collect.Hit("EmptySprite.java","draw(Graphics g, int x, int y, int width, int height)");Collect.Hit("EmptySprite.java","draw(Graphics g, int x, int y, int width, int height)", "|project://sqat-analysis/src/sqat/series2/A1b_DynCov.rsc|(4826,34)");}

	@Override
	public Sprite split(int x, int y, int width, int height) {Collect.Hit("EmptySprite.java","split(int x, int y, int width, int height)");return new EmptySprite(); Collect.Hit("EmptySprite.java","split(int x, int y, int width, int height)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/EmptySprite.java|(441,25,<20,2>,<20,27>)");}

	@Override
	public int getWidth() {Collect.Hit("EmptySprite.java","getWidth()");return 0; Collect.Hit("EmptySprite.java","getWidth()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/EmptySprite.java|(514,9,<25,2>,<25,11>)");}

	@Override
	public int getHeight() {Collect.Hit("EmptySprite.java","getHeight()");return 0; Collect.Hit("EmptySprite.java","getHeight()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/EmptySprite.java|(572,9,<30,2>,<30,11>)");}

}
