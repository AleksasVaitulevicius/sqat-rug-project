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
	public AnimatedSprite(Sprite[] frames, int delay, boolean loop) {this(frames, delay, loop, false);Collect.Hit("AnimatedSprite.java","AnimatedSprite(Sprite[] frames, int delay, boolean loop)");Collect.Hit("AnimatedSprite.java","AnimatedSprite(Sprite[] frames, int delay, boolean loop)", "4828");}

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
	public AnimatedSprite(Sprite[] frames, int delay, boolean loop,	boolean isAnimating) {Collect.Hit("AnimatedSprite.java","AnimatedSprite(Sprite[] frames, int delay, boolean loop,boolean isAnimating)");assert frames.length > 0; Collect.Hit("AnimatedSprite.java","AnimatedSprite(Sprite[] frames, int delay, boolean loop,boolean isAnimating)", "1893"); this.animationFrames = frames.clone(); Collect.Hit("AnimatedSprite.java","AnimatedSprite(Sprite[] frames, int delay, boolean loop,boolean isAnimating)", "1924"); this.animationDelay = delay; Collect.Hit("AnimatedSprite.java","AnimatedSprite(Sprite[] frames, int delay, boolean loop,boolean isAnimating)", "1966"); this.looping = loop; Collect.Hit("AnimatedSprite.java","AnimatedSprite(Sprite[] frames, int delay, boolean loop,boolean isAnimating)", "1998"); this.animating = isAnimating; Collect.Hit("AnimatedSprite.java","AnimatedSprite(Sprite[] frames, int delay, boolean loop,boolean isAnimating)", "2022"); this.current = 0; Collect.Hit("AnimatedSprite.java","AnimatedSprite(Sprite[] frames, int delay, boolean loop,boolean isAnimating)", "2057"); this.lastUpdate = System.currentTimeMillis(); Collect.Hit("AnimatedSprite.java","AnimatedSprite(Sprite[] frames, int delay, boolean loop,boolean isAnimating)", "2078");}

	/**
	 * @return The frame of the current index.
	 */
	private Sprite currentSprite() {Collect.Hit("AnimatedSprite.java","currentSprite()");Sprite result = END_OF_LOOP; Collect.Hit("AnimatedSprite.java","currentSprite()", "2225"); if (current < animationFrames.length) {
			result = animationFrames[current];
		} Collect.Hit("AnimatedSprite.java","currentSprite()", "2257"); assert result != null; Collect.Hit("AnimatedSprite.java","currentSprite()", "2344"); Collect.Hit("AnimatedSprite.java","currentSprite()", "2370");return result ; }

	/**
	 * Starts or stops the animation of this sprite.
	 * 
	 * @param isAnimating
	 *            <code>true</code> to animate this sprite or <code>false</code>
	 *            to stop animating this sprite.
	 */
	public void setAnimating(boolean isAnimating) {Collect.Hit("AnimatedSprite.java","setAnimating(boolean isAnimating)");this.animating = isAnimating; Collect.Hit("AnimatedSprite.java","setAnimating(boolean isAnimating)", "2663");}
	
	/**
	 * (Re)starts the current animation.
	 */
	public void restart() {Collect.Hit("AnimatedSprite.java","restart()");this.current = 0; Collect.Hit("AnimatedSprite.java","restart()", "2780"); this.lastUpdate = System.currentTimeMillis(); Collect.Hit("AnimatedSprite.java","restart()", "2801"); setAnimating(true); Collect.Hit("AnimatedSprite.java","restart()", "2850");}

	@Override
	public void draw(Graphics g, int x, int y, int width, int height) {Collect.Hit("AnimatedSprite.java","draw(Graphics g, int x, int y, int width, int height)");update(); Collect.Hit("AnimatedSprite.java","draw(Graphics g, int x, int y, int width, int height)", "2961"); currentSprite().draw(g, x, y, width, height); Collect.Hit("AnimatedSprite.java","draw(Graphics g, int x, int y, int width, int height)", "2974");}

	@Override
	public Sprite split(int x, int y, int width, int height) {Collect.Hit("AnimatedSprite.java","split(int x, int y, int width, int height)");update(); Collect.Hit("AnimatedSprite.java","split(int x, int y, int width, int height)", "3102"); Collect.Hit("AnimatedSprite.java","split(int x, int y, int width, int height)", "3115");return currentSprite().split(x, y, width, height) ; }

	/**
	 * Updates the current frame index depending on the current system time.
	 */
	private void update() {Collect.Hit("AnimatedSprite.java","update()");long now = System.currentTimeMillis(); Collect.Hit("AnimatedSprite.java","update()", "3288"); if (animating) {
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
		} Collect.Hit("AnimatedSprite.java","update()", "3330");}

	@Override
	public int getWidth() {Collect.Hit("AnimatedSprite.java","getWidth()");assert currentSprite() != null; Collect.Hit("AnimatedSprite.java","getWidth()", "3667"); Collect.Hit("AnimatedSprite.java","getWidth()", "3702");return currentSprite().getWidth() ; }

	@Override
	public int getHeight() {Collect.Hit("AnimatedSprite.java","getHeight()");assert currentSprite() != null; Collect.Hit("AnimatedSprite.java","getHeight()", "3785"); Collect.Hit("AnimatedSprite.java","getHeight()", "3820");return currentSprite().getHeight() ; }

}
