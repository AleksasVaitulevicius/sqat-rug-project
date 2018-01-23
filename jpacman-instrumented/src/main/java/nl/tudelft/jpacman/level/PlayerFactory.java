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
	public PlayerFactory(PacManSprites spriteStore) {Collect.Hit("PlayerFactory.java","PlayerFactory(PacManSprites spriteStore)");this.sprites = spriteStore; Collect.Hit("PlayerFactory.java","PlayerFactory(PacManSprites spriteStore)", "499");}

	/**
	 * Creates a new player with the classic Pac-Man sprites.
	 * 
	 * @return A new player.
	 */
	public Player createPacMan() {Collect.Hit("PlayerFactory.java","createPacMan()"); Collect.Hit("PlayerFactory.java","createPacMan()", "674");return new Player(getSprites().getPacmanSprites(),
				getSprites().getPacManDeathAnimation()) ; }

	/**
	 * The sprites created by the factory.
	 * 
	 * @return The sprites for the player created.
	 */
	protected PacManSprites getSprites() {Collect.Hit("PlayerFactory.java","getSprites()"); Collect.Hit("PlayerFactory.java","getSprites()", "929");return sprites ; }
}
