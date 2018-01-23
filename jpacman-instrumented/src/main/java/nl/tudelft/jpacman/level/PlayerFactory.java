package nl.tudelft.jpacman.level;import coverageApi.Collect;import nl.tudelft.jpacman.sprite.PacManSprites; public class PlayerFactory {

	/**
	 * The sprite store containing the Pac-Man sprites.
	 */
	private final PacManSprites sprites;

	/**
	 * Creates a new player factory.
	 * 
	 * @param spriteStore
	 *            The sprite store containing the Pac-Man sprites.
	 */
	public PlayerFactory(PacManSprites spriteStore) {Collect.Hit("PlayerFactory.java","PlayerFactory(PacManSprites spriteStore)");this.sprites = spriteStore; Collect.Hit("PlayerFactory.java","PlayerFactory(PacManSprites spriteStore)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/PlayerFactory.java|(499,27,<24,2>,<24,29>)");}

	/**
	 * Creates a new player with the classic Pac-Man sprites.
	 * 
	 * @return A new player.
	 */
	public Player createPacMan() {Collect.Hit("PlayerFactory.java","createPacMan()");return new Player(getSprites().getPacmanSprites(),
				getSprites().getPacManDeathAnimation()); Collect.Hit("PlayerFactory.java","createPacMan()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/PlayerFactory.java|(674,96,<33,2>,<34,44>)");}

	/**
	 * The sprites created by the factory.
	 * 
	 * @return The sprites for the player created.
	 */
	protected PacManSprites getSprites() {Collect.Hit("PlayerFactory.java","getSprites()");return sprites; Collect.Hit("PlayerFactory.java","getSprites()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/PlayerFactory.java|(929,15,<43,2>,<43,17>)");}
}
