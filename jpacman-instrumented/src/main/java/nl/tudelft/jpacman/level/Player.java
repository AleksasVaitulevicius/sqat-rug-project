package nl.tudelft.jpacman.level;import coverageApi.Collect;import java.util.Map;

import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.board.Unit;
import nl.tudelft.jpacman.sprite.AnimatedSprite;
import nl.tudelft.jpacman.sprite.Sprite; public class Player extends Unit {

	/**
	 * The amount of points accumulated by this player.
	 */
	private int score;

	/**
	 * The animations for every direction.
	 */
	private final Map<Direction, Sprite> sprites;

	/**
	 * The animation that is to be played when Pac-Man dies.
	 */
	private final AnimatedSprite deathSprite;

	/**
	 * <code>true</code> iff this player is alive.
	 */
	private boolean alive;

	/**
	 * Creates a new player with a score of 0 points.
	 * 
	 * @param spriteMap
	 *            A map containing a sprite for this player for every direction.
	 * @param deathAnimation
	 *            The sprite to be shown when this player dies.
	 */
	protected Player(Map<Direction, Sprite> spriteMap, AnimatedSprite deathAnimation) {Collect.Hit("Player.java","Player(Map<Direction, Sprite> spriteMap, AnimatedSprite deathAnimation)");this.score = 0; Collect.Hit("Player.java","Player(Map<Direction, Sprite> spriteMap, AnimatedSprite deathAnimation)", "1104"); this.alive = true; Collect.Hit("Player.java","Player(Map<Direction, Sprite> spriteMap, AnimatedSprite deathAnimation)", "1123"); this.sprites = spriteMap; Collect.Hit("Player.java","Player(Map<Direction, Sprite> spriteMap, AnimatedSprite deathAnimation)", "1145"); this.deathSprite = deathAnimation; Collect.Hit("Player.java","Player(Map<Direction, Sprite> spriteMap, AnimatedSprite deathAnimation)", "1174"); deathSprite.setAnimating(false); Collect.Hit("Player.java","Player(Map<Direction, Sprite> spriteMap, AnimatedSprite deathAnimation)", "1212");}

	/**
	 * Returns whether this player is alive or not.
	 * 
	 * @return <code>true</code> iff the player is alive.
	 */
	public boolean isAlive() {Collect.Hit("Player.java","isAlive()"); Collect.Hit("Player.java","isAlive()", "1407");return alive ; }

	/**
	 * Sets whether this player is alive or not.
	 * 
	 * @param isAlive
	 *            <code>true</code> iff this player is alive.
	 */
	public void setAlive(boolean isAlive) {Collect.Hit("Player.java","setAlive(boolean isAlive)");if (isAlive) {
			deathSprite.setAnimating(false);
		} Collect.Hit("Player.java","setAlive(boolean isAlive)", "1617"); if (!isAlive) {
			deathSprite.restart();
		} Collect.Hit("Player.java","setAlive(boolean isAlive)", "1677"); this.alive = isAlive; Collect.Hit("Player.java","setAlive(boolean isAlive)", "1728");}

	/**
	 * Returns the amount of points accumulated by this player.
	 * 
	 * @return The amount of points accumulated by this player.
	 */
	public int getScore() {Collect.Hit("Player.java","getScore()"); Collect.Hit("Player.java","getScore()", "1927");return score ; }

	@Override
	public Sprite getSprite() {Collect.Hit("Player.java","getSprite()");if (isAlive()) {
			return sprites.get(getDirection());
		} Collect.Hit("Player.java","getSprite()", "1992"); Collect.Hit("Player.java","getSprite()", "2057");return deathSprite ; }

	/**
	 * Adds points to the score of this player.
	 * 
	 * @param points
	 *            The amount of points to add to the points this player already
	 *            has.
	 */
	public void addPoints(int points) {Collect.Hit("Player.java","addPoints(int points)");score += points; Collect.Hit("Player.java","addPoints(int points)", "2306");}
}
