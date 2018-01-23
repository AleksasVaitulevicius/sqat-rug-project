package nl.tudelft.jpacman.npc.ghost;import coverageApi.Collect;import nl.tudelft.jpacman.sprite.PacManSprites; public class GhostFactory {

	/**
	 * The sprite store containing the ghost sprites.
	 */
	private final PacManSprites sprites;

	/**
	 * Creates a new ghost factory.
	 * 
	 * @param spriteStore The sprite provider.
	 */
	public GhostFactory(PacManSprites spriteStore) {Collect.Hit("GhostFactory.java","GhostFactory(PacManSprites spriteStore)");this.sprites = spriteStore; Collect.Hit("GhostFactory.java","GhostFactory(PacManSprites spriteStore)", "453");}

	/**
	 * Creates a new Blinky / Shadow, the red Ghost.
	 * 
	 * @see Blinky
	 * @return A new Blinky.
	 */
	public Ghost createBlinky() {Collect.Hit("GhostFactory.java","createBlinky()"); Collect.Hit("GhostFactory.java","createBlinky()", "635");return new Blinky(sprites.getGhostSprite(GhostColor.RED)) ; }

	/**
	 * Creates a new Pinky / Speedy, the pink Ghost.
	 * 
	 * @see Pinky
	 * @return A new Pinky.
	 */
	public Ghost createPinky() {Collect.Hit("GhostFactory.java","createPinky()"); Collect.Hit("GhostFactory.java","createPinky()", "845");return new Pinky(sprites.getGhostSprite(GhostColor.PINK)) ; }

	/**
	 * Creates a new Inky / Bashful, the cyan Ghost.
	 * 
	 * @see Inky
	 * @return A new Inky.
	 */
	public Ghost createInky() {Collect.Hit("GhostFactory.java","createInky()"); Collect.Hit("GhostFactory.java","createInky()", "1052");return new Inky(sprites.getGhostSprite(GhostColor.CYAN)) ; }

	/**
	 * Creates a new Clyde / Pokey, the orange Ghost.
	 * 
	 * @see Clyde
	 * @return A new Clyde.
	 */
	public Ghost createClyde() {Collect.Hit("GhostFactory.java","createClyde()"); Collect.Hit("GhostFactory.java","createClyde()", "1262");return new Clyde(sprites.getGhostSprite(GhostColor.ORANGE)) ; }
}
