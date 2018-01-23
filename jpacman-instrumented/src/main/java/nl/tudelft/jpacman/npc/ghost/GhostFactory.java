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
	public GhostFactory(PacManSprites spriteStore) {Collect.Hit("GhostFactory.java","GhostFactory(PacManSprites spriteStore)");this.sprites = spriteStore; Collect.Hit("GhostFactory.java","GhostFactory(PacManSprites spriteStore)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/npc/ghost/GhostFactory.java|(453,27,<23,2>,<23,29>)");}

	/**
	 * Creates a new Blinky / Shadow, the red Ghost.
	 * 
	 * @see Blinky
	 * @return A new Blinky.
	 */
	public Ghost createBlinky() {Collect.Hit("GhostFactory.java","createBlinky()");return new Blinky(sprites.getGhostSprite(GhostColor.RED)); Collect.Hit("GhostFactory.java","createBlinky()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/npc/ghost/GhostFactory.java|(635,58,<33,2>,<33,60>)");}

	/**
	 * Creates a new Pinky / Speedy, the pink Ghost.
	 * 
	 * @see Pinky
	 * @return A new Pinky.
	 */
	public Ghost createPinky() {Collect.Hit("GhostFactory.java","createPinky()");return new Pinky(sprites.getGhostSprite(GhostColor.PINK)); Collect.Hit("GhostFactory.java","createPinky()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/npc/ghost/GhostFactory.java|(845,58,<43,2>,<43,60>)");}

	/**
	 * Creates a new Inky / Bashful, the cyan Ghost.
	 * 
	 * @see Inky
	 * @return A new Inky.
	 */
	public Ghost createInky() {Collect.Hit("GhostFactory.java","createInky()");return new Inky(sprites.getGhostSprite(GhostColor.CYAN)); Collect.Hit("GhostFactory.java","createInky()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/npc/ghost/GhostFactory.java|(1052,57,<53,2>,<53,59>)");}

	/**
	 * Creates a new Clyde / Pokey, the orange Ghost.
	 * 
	 * @see Clyde
	 * @return A new Clyde.
	 */
	public Ghost createClyde() {Collect.Hit("GhostFactory.java","createClyde()");return new Clyde(sprites.getGhostSprite(GhostColor.ORANGE)); Collect.Hit("GhostFactory.java","createClyde()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/npc/ghost/GhostFactory.java|(1262,60,<63,2>,<63,62>)");}
}
