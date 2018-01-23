package nl.tudelft.jpacman.npc.ghost;import coverageApi.Collect;import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.npc.NPC;
import nl.tudelft.jpacman.sprite.Sprite; public abstract class Ghost extends NPC {
	
	/**
	 * The sprite map, one sprite for each direction.
	 */
	private final Map<Direction, Sprite> sprites;

	/**
	 * The base move interval of the ghost.
	 */
	private final int moveInterval;

	/**
	 * The random variation added to the {@link #moveInterval}.
	 */
	private final int intervalVariation;

	/**
	 * Creates a new ghost.
	 *
	 * @param spriteMap
	 *            The sprites for every direction.
	 * @param moveInterval
	 * 			  The base interval of movement.
	 * @param intervalVariation
	 * 			  The variation of the interval.
	 */
	protected Ghost(Map<Direction, Sprite> spriteMap, int moveInterval, int intervalVariation) {Collect.Hit("Ghost.java","Ghost(Map<Direction, Sprite> spriteMap, int moveInterval, int intervalVariation)");this.sprites = spriteMap; Collect.Hit("Ghost.java","Ghost(Map<Direction, Sprite> spriteMap, int moveInterval, int intervalVariation)", "1116"); this.intervalVariation = intervalVariation; Collect.Hit("Ghost.java","Ghost(Map<Direction, Sprite> spriteMap, int moveInterval, int intervalVariation)", "1145"); this.moveInterval = moveInterval; Collect.Hit("Ghost.java","Ghost(Map<Direction, Sprite> spriteMap, int moveInterval, int intervalVariation)", "1192");}

	@Override
	public Sprite getSprite() {Collect.Hit("Ghost.java","getSprite()"); Collect.Hit("Ghost.java","getSprite()", "1277");return sprites.get(getDirection()) ; }

	@Override
	public long getInterval() {Collect.Hit("Ghost.java","getInterval()"); Collect.Hit("Ghost.java","getInterval()", "1364");return this.moveInterval + new Random().nextInt(this.intervalVariation) ; }

	/**
	 * Determines a possible move in a random direction.
	 * 
	 * @return A direction in which the ghost can move, or <code>null</code> if
	 *         the ghost is shut in by inaccessible squares.
	 */
	protected Direction randomMove() {Collect.Hit("Ghost.java","randomMove()");Square square = getSquare(); Collect.Hit("Ghost.java","randomMove()", "1693"); List<Direction> directions = new ArrayList<>(); Collect.Hit("Ghost.java","randomMove()", "1725"); for (Direction d : Direction.values()) {
			if (square.getSquareAt(d).isAccessibleTo(this)) {
				directions.add(d);
			}
		} Collect.Hit("Ghost.java","randomMove()", "1776"); if (directions.isEmpty()) {
			return null;
		} Collect.Hit("Ghost.java","randomMove()", "1909"); int i = new Random().nextInt(directions.size()); Collect.Hit("Ghost.java","randomMove()", "1962"); Collect.Hit("Ghost.java","randomMove()", "2014");return directions.get(i) ; }
}
