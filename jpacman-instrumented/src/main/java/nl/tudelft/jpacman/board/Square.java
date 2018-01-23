package nl.tudelft.jpacman.board;import coverageApi.Collect;import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;

import nl.tudelft.jpacman.sprite.*; public abstract class Square {

	/**
	 * The units occupying this square, in order of appearance.
	 */
	private final List<Unit> occupants;

	/**
	 * The collection of squares adjacent to this square.
	 */
	private final Map<Direction, Square> neighbours;

	/**
	 * Creates a new, empty square.
	 */
	protected Square() {Collect.Hit("Square.java","Square()");this.occupants = new ArrayList<>(); Collect.Hit("Square.java","Square()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/Square.java|(718,35,<34,2>,<34,37>)"); this.neighbours = new EnumMap<>(Direction.class); Collect.Hit("Square.java","Square()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/Square.java|(757,49,<35,2>,<35,51>)"); assert invariant(); Collect.Hit("Square.java","Square()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/Square.java|(810,19,<36,2>,<36,21>)");}

	/**
	 * Returns the square adjacent to this square.
	 * 
	 * @param direction
	 *            The direction of the adjacent square.
	 * @return The adjacent square in the given direction.
	 */
	public Square getSquareAt(Direction direction) {Collect.Hit("Square.java","getSquareAt(Direction direction)");return neighbours.get(direction); Collect.Hit("Square.java","getSquareAt(Direction direction)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/Square.java|(1090,33,<47,2>,<47,35>)");}

	/**
	 * Links this square to a neighbour in the given direction. Note that this
	 * is a one-way connection.
	 * 
	 * @param neighbour
	 *            The neighbour to link.
	 * @param direction
	 *            The direction the new neighbour is in, as seen from this cell.
	 */
	public void link(Square neighbour, Direction direction) {Collect.Hit("Square.java","link(Square neighbour, Direction direction)");neighbours.put(direction, neighbour); Collect.Hit("Square.java","link(Square neighbour, Direction direction)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/Square.java|(1480,37,<60,2>,<60,39>)"); assert invariant(); Collect.Hit("Square.java","link(Square neighbour, Direction direction)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/Square.java|(1521,19,<61,2>,<61,21>)");}

	/**
	 * Returns an immutable list of units occupying this square, in the order in
	 * which they occupied this square (i.e. oldest first.)
	 * 
	 * @return An immutable list of units occupying this square, in the order in
	 *         which they occupied this square (i.e. oldest first.)
	 */
	public List<Unit> getOccupants() {Collect.Hit("Square.java","getOccupants()");return ImmutableList.copyOf(occupants); Collect.Hit("Square.java","getOccupants()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/Square.java|(1887,39,<72,2>,<72,41>)");}

	/**
	 * Adds a new occupant to this square.
	 * 
	 * @param occupant
	 *            The unit to occupy this square.
	 */
	void put(Unit occupant) {Collect.Hit("Square.java","put(Unit occupant)");assert occupant != null; Collect.Hit("Square.java","put(Unit occupant)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/Square.java|(2092,24,<82,2>,<82,26>)"); assert !occupants.contains(occupant); Collect.Hit("Square.java","put(Unit occupant)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/Square.java|(2120,37,<83,2>,<83,39>)"); occupants.add(occupant); Collect.Hit("Square.java","put(Unit occupant)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/Square.java|(2165,24,<85,2>,<85,26>)");}

	/**
	 * Removes the unit from this square if it was present.
	 * 
	 * @param occupant
	 *            The unit to be removed from this square.
	 */
	void remove(Unit occupant) {Collect.Hit("Square.java","remove(Unit occupant)");assert occupant != null; Collect.Hit("Square.java","remove(Unit occupant)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/Square.java|(2384,24,<95,2>,<95,26>)"); occupants.remove(occupant); Collect.Hit("Square.java","remove(Unit occupant)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/Square.java|(2412,27,<96,2>,<96,29>)");}

	/**
	 * Verifies that all occupants on this square have indeed listed this square
	 * as the square they are currently occupying.
	 * 
	 * @return <code>true</code> iff all occupants of this square have this
	 *         square listed as the square they are currently occupying.
	 */
	protected final boolean invariant() {Collect.Hit("Square.java","invariant()");for (Unit occupant : occupants) {
			if (occupant.getSquare() != this) {
				return false;
			}
		} Collect.Hit("Square.java","invariant()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/Square.java|(2780,103,<107,2>,<111,3>)"); return true; Collect.Hit("Square.java","invariant()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/Square.java|(2887,12,<112,2>,<112,14>)");}

	/**
	 * Determines whether the unit is allowed to occupy this square.
	 * 
	 * @param unit
	 *            The unit to grant or deny access.
	 * @return <code>true</code> iff the unit is allowed to occupy this square.
	 */
	public abstract boolean isAccessibleTo(Unit unit);

	/**
	 * Returns the sprite of this square.
	 * 
	 * @return The sprite of this square.
	 */
	public abstract Sprite getSprite();

}
