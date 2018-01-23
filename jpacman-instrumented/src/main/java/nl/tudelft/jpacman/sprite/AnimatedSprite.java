package nl.tudelft.jpacman.sprite;import coverageApi.Collect;import java.awt.Graphics; public class AnimatedSprite implements Sprite {

	/**
	 * Static empty sprite to serve as the end of a non-looping sprite.
	 */
	private static final Sprite END_OF_LOOP = new EmptySprite();

	/**
	 * The animation itself, in frames.
	 */
	private final Sprite[] animationFrames;

	/**
	 * The delay between frames.
	 */
	private final int animationDelay;

	/**
	 * Whether is animation should be looping or not.
	 */
	private final boolean looping;

	/**
	 * The index of the current frame.
	 */
	private int current;

	/**
	 * Whether this sprite is currently animating or not.
	 */
	private boolean animating;

	/**
	 * The {@link System#currentTimeMillis()} stamp of the last update.
	 */
	private long lastUpdate;

	/**
	 * Creates a new animating sprite that will change frames every interval. By
	 * default the sprite is not animating.
	 * 
	 * @param frames
	 *            The frames of this animation.
	 * @param delay
	 *            The delay between frames.
	 * @param loop
	 *            Whether or not this sprite should be looping.
	 */
	public AnimatedSprite(Sprite[] frames, int delay, boolean loop) {this(frames, delay, loop, false);Collect.Hit("AnimatedSprite.java","AnimatedSprite(Sprite[] frames, int delay, boolean loop)");Collect.Hit("AnimatedSprite.java","AnimatedSprite(Sprite[] frames, int delay, boolean loop)", "|project://sqat-analysis/src/sqat/series2/A1b_DynCov.rsc|(4826,34)");}

	/**
	 * Creates a new animating sprite that will change frames every interval.
	 * 
	 * @param frames
	 *            The frames of this animation.
	 * @param delay
	 *            The delay between frames.
	 * @param loop
	 *            Whether or not this sprite should be looping.
	 * @param isAnimating
	 *            Whether or not this sprite is animating from the start.
	 */
	public AnimatedSprite(Sprite[] frames, int delay, boolean loop,	boolean isAnimating) {Collect.Hit("AnimatedSprite.java","AnimatedSprite(Sprite[] frames, int delay, boolean loop,boolean isAnimating)");assert frames.length > 0; Collect.Hit("AnimatedSprite.java","AnimatedSprite(Sprite[] frames, int delay, boolean loop,boolean isAnimating)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/AnimatedSprite.java|(1893,25,<76,2>,<76,27>)"); this.animationFrames = frames.clone(); Collect.Hit("AnimatedSprite.java","AnimatedSprite(Sprite[] frames, int delay, boolean loop,boolean isAnimating)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/AnimatedSprite.java|(1924,38,<78,2>,<78,40>)"); this.animationDelay = delay; Collect.Hit("AnimatedSprite.java","AnimatedSprite(Sprite[] frames, int delay, boolean loop,boolean isAnimating)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/AnimatedSprite.java|(1966,28,<79,2>,<79,30>)"); this.looping = loop; Collect.Hit("AnimatedSprite.java","AnimatedSprite(Sprite[] frames, int delay, boolean loop,boolean isAnimating)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/AnimatedSprite.java|(1998,20,<80,2>,<80,22>)"); this.animating = isAnimating; Collect.Hit("AnimatedSprite.java","AnimatedSprite(Sprite[] frames, int delay, boolean loop,boolean isAnimating)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/AnimatedSprite.java|(2022,29,<81,2>,<81,31>)"); this.current = 0; Collect.Hit("AnimatedSprite.java","AnimatedSprite(Sprite[] frames, int delay, boolean loop,boolean isAnimating)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/AnimatedSprite.java|(2057,17,<83,2>,<83,19>)"); this.lastUpdate = System.currentTimeMillis(); Collect.Hit("AnimatedSprite.java","AnimatedSprite(Sprite[] frames, int delay, boolean loop,boolean isAnimating)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/AnimatedSprite.java|(2078,45,<84,2>,<84,47>)");}

	/**
	 * @return The frame of the current index.
	 */
	private Sprite currentSprite() {Collect.Hit("AnimatedSprite.java","currentSprite()");Sprite result = END_OF_LOOP; Collect.Hit("AnimatedSprite.java","currentSprite()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/AnimatedSprite.java|(2225,28,<91,2>,<91,30>)"); if (current < animationFrames.length) {
			result = animationFrames[current];
		} Collect.Hit("AnimatedSprite.java","currentSprite()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/AnimatedSprite.java|(2257,83,<92,2>,<94,3>)"); assert result != null; Collect.Hit("AnimatedSprite.java","currentSprite()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/AnimatedSprite.java|(2344,22,<95,2>,<95,24>)"); return result; Collect.Hit("AnimatedSprite.java","currentSprite()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/AnimatedSprite.java|(2370,14,<96,2>,<96,16>)");}

	/**
	 * Starts or stops the animation of this sprite.
	 * 
	 * @param isAnimating
	 *            <code>true</code> to animate this sprite or <code>false</code>
	 *            to stop animating this sprite.
	 */
	public void setAnimating(boolean isAnimating) {Collect.Hit("AnimatedSprite.java","setAnimating(boolean isAnimating)");this.animating = isAnimating; Collect.Hit("AnimatedSprite.java","setAnimating(boolean isAnimating)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/AnimatedSprite.java|(2663,29,<107,2>,<107,31>)");}
	
	/**
	 * (Re)starts the current animation.
	 */
	public void restart() {Collect.Hit("AnimatedSprite.java","restart()");this.current = 0; Collect.Hit("AnimatedSprite.java","restart()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/AnimatedSprite.java|(2780,17,<114,2>,<114,19>)"); this.lastUpdate = System.currentTimeMillis(); Collect.Hit("AnimatedSprite.java","restart()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/AnimatedSprite.java|(2801,45,<115,2>,<115,47>)"); setAnimating(true); Collect.Hit("AnimatedSprite.java","restart()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/AnimatedSprite.java|(2850,19,<116,2>,<116,21>)");}

	@Override
	public void draw(Graphics g, int x, int y, int width, int height) {Collect.Hit("AnimatedSprite.java","draw(Graphics g, int x, int y, int width, int height)");update(); Collect.Hit("AnimatedSprite.java","draw(Graphics g, int x, int y, int width, int height)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/AnimatedSprite.java|(2961,9,<121,2>,<121,11>)"); currentSprite().draw(g, x, y, width, height); Collect.Hit("AnimatedSprite.java","draw(Graphics g, int x, int y, int width, int height)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/AnimatedSprite.java|(2974,45,<122,2>,<122,47>)");}

	@Override
	public Sprite split(int x, int y, int width, int height) {Collect.Hit("AnimatedSprite.java","split(int x, int y, int width, int height)");update(); Collect.Hit("AnimatedSprite.java","split(int x, int y, int width, int height)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/AnimatedSprite.java|(3102,9,<127,2>,<127,11>)"); return currentSprite().split(x, y, width, height); Collect.Hit("AnimatedSprite.java","split(int x, int y, int width, int height)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/AnimatedSprite.java|(3115,50,<128,2>,<128,52>)");}

	/**
	 * Updates the current frame index depending on the current system time.
	 */
	private void update() {Collect.Hit("AnimatedSprite.java","update()");long now = System.currentTimeMillis(); Collect.Hit("AnimatedSprite.java","update()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/AnimatedSprite.java|(3288,38,<135,2>,<135,40>)"); if (animating) {
			while (lastUpdate < now) {
				lastUpdate += animationDelay;
				current++;
				if (looping) {
					current %= animationFrames.length;
				} else if (current == animationFrames.length) {
					animating = false;
				}
			}
		} else {
			lastUpdate = now;
		} Collect.Hit("AnimatedSprite.java","update()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/AnimatedSprite.java|(3330,289,<136,2>,<148,3>)");}

	@Override
	public int getWidth() {Collect.Hit("AnimatedSprite.java","getWidth()");assert currentSprite() != null; Collect.Hit("AnimatedSprite.java","getWidth()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/AnimatedSprite.java|(3667,31,<153,2>,<153,33>)"); return currentSprite().getWidth(); Collect.Hit("AnimatedSprite.java","getWidth()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/AnimatedSprite.java|(3702,34,<154,2>,<154,36>)");}

	@Override
	public int getHeight() {Collect.Hit("AnimatedSprite.java","getHeight()");assert currentSprite() != null; Collect.Hit("AnimatedSprite.java","getHeight()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/AnimatedSprite.java|(3785,31,<159,2>,<159,33>)"); return currentSprite().getHeight(); Collect.Hit("AnimatedSprite.java","getHeight()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/AnimatedSprite.java|(3820,35,<160,2>,<160,37>)");}

}
