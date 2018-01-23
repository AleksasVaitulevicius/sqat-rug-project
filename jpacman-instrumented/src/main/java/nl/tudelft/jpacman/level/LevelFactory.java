package nl.tudelft.jpacman.level;import coverageApi.Collect;import java.util.List;
import java.util.Map;

import nl.tudelft.jpacman.board.Board;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.npc.NPC;
import nl.tudelft.jpacman.npc.ghost.Ghost;
import nl.tudelft.jpacman.npc.ghost.GhostColor;
import nl.tudelft.jpacman.npc.ghost.GhostFactory;
import nl.tudelft.jpacman.sprite.PacManSprites;
import nl.tudelft.jpacman.sprite.Sprite; public class LevelFactory {

	private static final int GHOSTS = 4;
	private static final int BLINKY = 0;
	private static final int INKY = 1;
	private static final int PINKY = 2;
	private static final int CLYDE = 3;

	/**
	 * The default value of a pellet.
	 */
	private static final int PELLET_VALUE = 10;

	/**
	 * The sprite store that provides sprites for units.
	 */
	private final PacManSprites sprites;

	/**
	 * Used to cycle through the various ghost types.
	 */
	private int ghostIndex;

	/**
	 * The factory providing ghosts.
	 */
	private final GhostFactory ghostFact;

	/**
	 * Creates a new level factory.
	 * 
	 * @param spriteStore
	 *            The sprite store providing the sprites for units.
	 * @param ghostFactory
	 *            The factory providing ghosts.
	 */
	public LevelFactory(PacManSprites spriteStore, GhostFactory ghostFactory) {Collect.Hit("LevelFactory.java","LevelFactory(PacManSprites spriteStore, GhostFactory ghostFactory)");this.sprites = spriteStore; Collect.Hit("LevelFactory.java","LevelFactory(PacManSprites spriteStore, GhostFactory ghostFactory)", "1471"); this.ghostIndex = -1; Collect.Hit("LevelFactory.java","LevelFactory(PacManSprites spriteStore, GhostFactory ghostFactory)", "1502"); this.ghostFact = ghostFactory; Collect.Hit("LevelFactory.java","LevelFactory(PacManSprites spriteStore, GhostFactory ghostFactory)", "1527");}

	/**
	 * Creates a new level from the provided data.
	 * 
	 * @param board
	 *            The board with all ghosts and pellets occupying their squares.
	 * @param ghosts
	 *            A list of all ghosts on the board.
	 * @param startPositions
	 *            A list of squares from which players may start the game.
	 * @return A new level for the board.
	 */
	public Level createLevel(Board board, List<NPC> ghosts, List<Square> startPositions) {Collect.Hit("LevelFactory.java","createLevel(Board board, List<NPC> ghosts, List<Square> startPositions)");CollisionMap collisionMap = new PlayerCollisions(); Collect.Hit("LevelFactory.java","createLevel(Board board, List<NPC> ghosts, List<Square> startPositions)", "2084"); Collect.Hit("LevelFactory.java","createLevel(Board board, List<NPC> ghosts, List<Square> startPositions)", "2143");return new Level(board, ghosts, startPositions, collisionMap) ; }

	/**
	 * Creates a new ghost.
	 * 
	 * @return The new ghost.
	 */
	NPC createGhost() {Collect.Hit("LevelFactory.java","createGhost()");ghostIndex++; Collect.Hit("LevelFactory.java","createGhost()", "2309"); ghostIndex %= GHOSTS; Collect.Hit("LevelFactory.java","createGhost()", "2326"); switch (ghostIndex) {
		case BLINKY:
			return ghostFact.createBlinky();
		case INKY:
			return ghostFact.createInky();
		case PINKY:
			return ghostFact.createPinky();
		case CLYDE:
			return ghostFact.createClyde();
		default:
			return new RandomGhost(sprites.getGhostSprite(GhostColor.RED));
		} Collect.Hit("LevelFactory.java","createGhost()", "2351");}

	/**
	 * Creates a new pellet.
	 * 
	 * @return The new pellet.
	 */
	public Pellet createPellet() {Collect.Hit("LevelFactory.java","createPellet()"); Collect.Hit("LevelFactory.java","createPellet()", "2778");return new Pellet(PELLET_VALUE, sprites.getPelletSprite()) ; }

	/**
	 * Implementation of an NPC that wanders around randomly.
	 * 
	 * @author Jeroen Roosen 
	 */
	private static final class RandomGhost extends Ghost {

		/**
		 * The suggested delay between moves.
		 */
		private static final long DELAY = 175L;

		/**
		 * Creates a new random ghost.
		 * 
		 * @param ghostSprite
		 *            The sprite for the ghost.
		 */
		RandomGhost(Map<Direction, Sprite> ghostSprite) {super(ghostSprite, (int) DELAY, 0);Collect.Hit("LevelFactory.java","RandomGhost(Map<Direction, Sprite> ghostSprite)");Collect.Hit("LevelFactory.java","RandomGhost(Map<Direction, Sprite> ghostSprite)", "4828");}

		@Override
		public Direction nextMove() {Collect.Hit("LevelFactory.java","nextMove()"); Collect.Hit("LevelFactory.java","nextMove()", "3382");return randomMove() ; }
	}
}
