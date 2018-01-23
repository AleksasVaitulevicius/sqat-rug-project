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
	protected Player(Map<Direction, Sprite> spriteMap, AnimatedSprite deathAnimation) {Collect.Hit("Player.java","Player(Map<Direction, Sprite> spriteMap, AnimatedSprite deathAnimation)");this.score = 0; Collect.Hit("Player.java","Player(Map<Direction, Sprite> spriteMap, AnimatedSprite deathAnimation)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Player.java|(1104,15,<46,2>,<46,17>)"); this.alive = true; Collect.Hit("Player.java","Player(Map<Direction, Sprite> spriteMap, AnimatedSprite deathAnimation)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Player.java|(1123,18,<47,2>,<47,20>)"); this.sprites = spriteMap; Collect.Hit("Player.java","Player(Map<Direction, Sprite> spriteMap, AnimatedSprite deathAnimation)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Player.java|(1145,25,<48,2>,<48,27>)"); this.deathSprite = deathAnimation; Collect.Hit("Player.java","Player(Map<Direction, Sprite> spriteMap, AnimatedSprite deathAnimation)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Player.java|(1174,34,<49,2>,<49,36>)"); deathSprite.setAnimating(false); Collect.Hit("Player.java","Player(Map<Direction, Sprite> spriteMap, AnimatedSprite deathAnimation)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Player.java|(1212,32,<50,2>,<50,34>)");}

	/**
	 * Returns whether this player is alive or not.
	 * 
	 * @return <code>true</code> iff the player is alive.
	 */
	public boolean isAlive() {Collect.Hit("Player.java","isAlive()");return alive; Collect.Hit("Player.java","isAlive()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Player.java|(1407,13,<59,2>,<59,15>)");}

	/**
	 * Sets whether this player is alive or not.
	 * 
	 * @param isAlive
	 *            <code>true</code> iff this player is alive.
	 */
	public void setAlive(boolean isAlive) {Collect.Hit("Player.java","setAlive(boolean isAlive)");if (isAlive) {
			deathSprite.setAnimating(false);
		} Collect.Hit("Player.java","setAlive(boolean isAlive)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Player.java|(1617,56,<69,2>,<71,3>)"); if (!isAlive) {
			deathSprite.restart();
		} Collect.Hit("Player.java","setAlive(boolean isAlive)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Player.java|(1677,47,<72,2>,<74,3>)"); this.alive = isAlive; Collect.Hit("Player.java","setAlive(boolean isAlive)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Player.java|(1728,21,<75,2>,<75,23>)");}

	/**
	 * Returns the amount of points accumulated by this player.
	 * 
	 * @return The amount of points accumulated by this player.
	 */
	public int getScore() {Collect.Hit("Player.java","getScore()");return score; Collect.Hit("Player.java","getScore()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Player.java|(1927,13,<84,2>,<84,15>)");}

	@Override
	public Sprite getSprite() {Collect.Hit("Player.java","getSprite()");if (isAlive()) {
			return sprites.get(getDirection());
		} Collect.Hit("Player.java","getSprite()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Player.java|(1992,61,<89,2>,<91,3>)"); return deathSprite; Collect.Hit("Player.java","getSprite()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Player.java|(2057,19,<92,2>,<92,21>)");}

	/**
	 * Adds points to the score of this player.
	 * 
	 * @param points
	 *            The amount of points to add to the points this player already
	 *            has.
	 */
	public void addPoints(int points) {Collect.Hit("Player.java","addPoints(int points)");score += points; Collect.Hit("Player.java","addPoints(int points)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Player.java|(2306,16,<103,2>,<103,18>)");}
}
