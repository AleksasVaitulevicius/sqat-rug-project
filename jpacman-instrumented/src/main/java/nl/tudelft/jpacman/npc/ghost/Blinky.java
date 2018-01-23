package nl.tudelft.jpacman.npc.ghost;import coverageApi.Collect;import java.util.List;
import java.util.Map;

import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.sprite.Sprite; public class Blinky extends Ghost {

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
	 * Creates a new "Blinky", a.k.a. "Shadow".
	 * 
	 * @param spriteMap
	 *            The sprites for this ghost.
	 */
	// TODO Blinky should speed up when there are a few pellets left, but he
	// has no way to find out how many there are.
	public Blinky(Map<Direction, Sprite> spriteMap) {super(spriteMap, MOVE_INTERVAL, INTERVAL_VARIATION);Collect.Hit("Blinky.java","Blinky(Map<Direction, Sprite> spriteMap)");Collect.Hit("Blinky.java","Blinky(Map<Direction, Sprite> spriteMap)", "4828");}

	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * When the ghosts are not patrolling in their home corners (Blinky:
	 * top-right, Pinky: top-left, Inky: bottom-right, Clyde: bottom-left),
	 * Blinky will attempt to shorten the distance between Pac-Man and himself.
	 * If he has to choose between shortening the horizontal or vertical
	 * distance, he will choose to shorten whichever is greatest. For example,
	 * if Pac-Man is four grid spaces to the left, and seven grid spaces above
	 * Blinky, he'll try to move up towards Pac-Man before he moves to the left.
	 * </p>
	 */
	@Override
	public Direction nextMove() {Collect.Hit("Blinky.java","nextMove()");Square target = Navigation.findNearest(Player.class, getSquare())
				.getSquare(); Collect.Hit("Blinky.java","nextMove()", "2975"); if (target == null) {
			return randomMove();
		} Collect.Hit("Blinky.java","nextMove()", "3065"); List<Direction> path = Navigation.shortestPath(getSquare(), target,
				this); Collect.Hit("Blinky.java","nextMove()", "3124"); if (path != null && !path.isEmpty()) {
			return path.get(0);
		} Collect.Hit("Blinky.java","nextMove()", "3207"); Collect.Hit("Blinky.java","nextMove()", "3278");return randomMove() ; }
}
