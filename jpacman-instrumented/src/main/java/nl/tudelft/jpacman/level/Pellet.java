package nl.tudelft.jpacman.level;import coverageApi.Collect;import nl.tudelft.jpacman.board.Unit;
import nl.tudelft.jpacman.sprite.Sprite; public class Pellet extends Unit {

	/**
	 * The sprite of this unit.
	 */
	private final Sprite image;
	
	/**
	 * The point value of this pellet.
	 */
	private final int value;
	
	/**
	 * Creates a new pellet.
	 * @param points The point value of this pellet.
	 * @param sprite The sprite of this pellet.
	 */
	public Pellet(int points, Sprite sprite) {Collect.Hit("Pellet.java","Pellet(int points, Sprite sprite)");this.image = sprite; Collect.Hit("Pellet.java","Pellet(int points, Sprite sprite)", "598"); this.value = points; Collect.Hit("Pellet.java","Pellet(int points, Sprite sprite)", "622");}
	
	/**
	 * Returns the point value of this pellet.
	 * @return The point value of this pellet.
	 */
	public int getValue() {Collect.Hit("Pellet.java","getValue()"); Collect.Hit("Pellet.java","getValue()", "781");return value ; }
	
	@Override
	public Sprite getSprite() {Collect.Hit("Pellet.java","getSprite()"); Collect.Hit("Pellet.java","getSprite()", "847");return image ; }
}
