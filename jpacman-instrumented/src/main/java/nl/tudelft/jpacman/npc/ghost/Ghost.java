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
	protected Ghost(Map<Direction, Sprite> spriteMap, int moveInterval, int intervalVariation) {Collect.Hit("Ghost.java","Ghost(Map<Direction, Sprite> spriteMap, int moveInterval, int intervalVariation)");this.sprites = spriteMap; Collect.Hit("Ghost.java","Ghost(Map<Direction, Sprite> spriteMap, int moveInterval, int intervalVariation)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/npc/ghost/Ghost.java|(1116,25,<46,2>,<46,27>)"); this.intervalVariation = intervalVariation; Collect.Hit("Ghost.java","Ghost(Map<Direction, Sprite> spriteMap, int moveInterval, int intervalVariation)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/npc/ghost/Ghost.java|(1145,43,<47,2>,<47,45>)"); this.moveInterval = moveInterval; Collect.Hit("Ghost.java","Ghost(Map<Direction, Sprite> spriteMap, int moveInterval, int intervalVariation)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/npc/ghost/Ghost.java|(1192,33,<48,2>,<48,35>)");}

	@Override
	public Sprite getSprite() {Collect.Hit("Ghost.java","getSprite()");return sprites.get(getDirection()); Collect.Hit("Ghost.java","getSprite()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/npc/ghost/Ghost.java|(1277,35,<53,2>,<53,37>)");}

	@Override
	public long getInterval() {Collect.Hit("Ghost.java","getInterval()");return this.moveInterval + new Random().nextInt(this.intervalVariation); Collect.Hit("Ghost.java","getInterval()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/npc/ghost/Ghost.java|(1364,72,<58,2>,<58,74>)");}

	/**
	 * Determines a possible move in a random direction.
	 * 
	 * @return A direction in which the ghost can move, or <code>null</code> if
	 *         the ghost is shut in by inaccessible squares.
	 */
	protected Direction randomMove() {Collect.Hit("Ghost.java","randomMove()");Square square = getSquare(); Collect.Hit("Ghost.java","randomMove()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/npc/ghost/Ghost.java|(1693,28,<68,2>,<68,30>)"); List<Direction> directions = new ArrayList<>(); Collect.Hit("Ghost.java","randomMove()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/npc/ghost/Ghost.java|(1725,47,<69,2>,<69,49>)"); for (Direction d : Direction.values()) {
			if (square.getSquareAt(d).isAccessibleTo(this)) {
				directions.add(d);
			}
		} Collect.Hit("Ghost.java","randomMove()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/npc/ghost/Ghost.java|(1776,129,<70,2>,<74,3>)"); if (directions.isEmpty()) {
			return null;
		} Collect.Hit("Ghost.java","randomMove()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/npc/ghost/Ghost.java|(1909,49,<75,2>,<77,3>)"); int i = new Random().nextInt(directions.size()); Collect.Hit("Ghost.java","randomMove()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/npc/ghost/Ghost.java|(1962,48,<78,2>,<78,50>)"); return directions.get(i); Collect.Hit("Ghost.java","randomMove()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/npc/ghost/Ghost.java|(2014,25,<79,2>,<79,27>)");}
}
