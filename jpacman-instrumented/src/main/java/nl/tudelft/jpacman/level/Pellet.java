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
	public Pellet(int points, Sprite sprite) {Collect.Hit("Pellet.java","Pellet(int points, Sprite sprite)");this.image = sprite; Collect.Hit("Pellet.java","Pellet(int points, Sprite sprite)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Pellet.java|(598,20,<29,2>,<29,22>)"); this.value = points; Collect.Hit("Pellet.java","Pellet(int points, Sprite sprite)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Pellet.java|(622,20,<30,2>,<30,22>)");}
	
	/**
	 * Returns the point value of this pellet.
	 * @return The point value of this pellet.
	 */
	public int getValue() {Collect.Hit("Pellet.java","getValue()");return value; Collect.Hit("Pellet.java","getValue()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Pellet.java|(781,13,<38,2>,<38,15>)");}
	
	@Override
	public Sprite getSprite() {Collect.Hit("Pellet.java","getSprite()");return image; Collect.Hit("Pellet.java","getSprite()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Pellet.java|(847,13,<43,2>,<43,15>)");}
}
