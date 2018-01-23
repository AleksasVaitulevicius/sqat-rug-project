package nl.tudelft.jpacman.npc.ghost;import coverageApi.Collect;import java.util.List;
import java.util.Map;

import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.board.Unit;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.sprite.Sprite; public class Inky extends Ghost {

	private static final int SQUARES_AHEAD = 2;

	/**
	 * The variation in intervals, this makes the ghosts look more dynamic and
	 * less predictable.
	 */
	private static final int INTERVAL_VARIATION = 50;

	/**
	 * The base movement interval.
	 */
	private static final int MOVE_INTERVAL = 250;

	/**
	 * Creates a new "Inky", a.k.a. Bashful.
	 * 
	 * @param spriteMap
	 *            The sprites for this ghost.
	 */
	public Inky(Map<Direction, Sprite> spriteMap) {super(spriteMap, MOVE_INTERVAL, INTERVAL_VARIATION);Collect.Hit("Inky.java","Inky(Map<Direction, Sprite> spriteMap)");Collect.Hit("Inky.java","Inky(Map<Direction, Sprite> spriteMap)", "5012");}

	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * Bashful has the most complicated AI of all. When the ghosts are not
	 * patrolling their home corners, Bashful considers two things: Shadow's
	 * location, and the location two grid spaces ahead of Pac-Man. Bashful
	 * draws a line from Shadow to the spot that is two squares in front of
	 * Pac-Man and extends that line twice as far. Therefore, if Bashful is
	 * alongside Shadow when they are behind Pac-Man, Bashful will usually
	 * follow Shadow the whole time. But if Bashful is in front of Pac-Man when
	 * Shadow is far behind him, Bashful tends to want to move away from Pac-Man
	 * (in reality, to a point very far ahead of Pac-Man). Bashful is affected
	 * by a similar targeting bug that affects Speedy. When Pac-Man is moving or
	 * facing up, the spot Bashful uses to draw the line is two squares above
	 * and left of Pac-Man.
	 * </p>
	 * 
	 * <p>
	 * <b>Implementation:</b> by lack of a coordinate system there is a
	 * workaround: first determine the square of Blinky (A) and the square 2
	 * squares away from Pac-Man (B). Then determine the shortest path from A to
	 * B regardless of terrain and walk that same path from B. This is the
	 * destination.
	 * </p>
	 */
	// CHECKSTYLE:OFF To keep this more readable.
	@Override
	public Direction nextMove() {Collect.Hit("Inky.java","nextMove()");Unit blinky = Navigation.findNearest(Blinky.class, getSquare()); Collect.Hit("Inky.java","nextMove()", "3669"); if (blinky == null) {
			return randomMove();
		} Collect.Hit("Inky.java","nextMove()", "3737"); Unit player = Navigation.findNearest(Player.class, getSquare()); Collect.Hit("Inky.java","nextMove()", "3794"); if (player == null) {
			return randomMove();
		} Collect.Hit("Inky.java","nextMove()", "3862"); Direction targetDirection = player.getDirection(); Collect.Hit("Inky.java","nextMove()", "3919"); Square playerDestination = player.getSquare(); Collect.Hit("Inky.java","nextMove()", "3973"); for (int i = 0; i < SQUARES_AHEAD; i++) {
			playerDestination = playerDestination.getSquareAt(targetDirection);
		} Collect.Hit("Inky.java","nextMove()", "4023"); Square destination = playerDestination; Collect.Hit("Inky.java","nextMove()", "4147"); List<Direction> firstHalf = Navigation.shortestPath(blinky.getSquare(),
				playerDestination, null); Collect.Hit("Inky.java","nextMove()", "4190"); if (firstHalf == null) {
			return randomMove();
		} Collect.Hit("Inky.java","nextMove()", "4296"); for (Direction d : firstHalf) {
			destination = playerDestination.getSquareAt(d);
		} Collect.Hit("Inky.java","nextMove()", "4356"); List<Direction> path = Navigation.shortestPath(getSquare(),
				destination, this); Collect.Hit("Inky.java","nextMove()", "4450"); if (path != null && !path.isEmpty()) {
			return path.get(0);
		} Collect.Hit("Inky.java","nextMove()", "4538"); Collect.Hit("Inky.java","nextMove()", "4609");return randomMove() ; }
	// CHECKSTYLE:ON

}
