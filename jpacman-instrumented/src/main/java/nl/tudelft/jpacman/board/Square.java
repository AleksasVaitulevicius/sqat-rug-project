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
	protected Square() {Collect.Hit("Square.java","Square()");this.occupants = new ArrayList<>(); Collect.Hit("Square.java","Square()", "718"); this.neighbours = new EnumMap<>(Direction.class); Collect.Hit("Square.java","Square()", "757"); assert invariant(); Collect.Hit("Square.java","Square()", "810");}

	/**
	 * Returns the square adjacent to this square.
	 * 
	 * @param direction
	 *            The direction of the adjacent square.
	 * @return The adjacent square in the given direction.
	 */
	public Square getSquareAt(Direction direction) {Collect.Hit("Square.java","getSquareAt(Direction direction)"); Collect.Hit("Square.java","getSquareAt(Direction direction)", "1090");return neighbours.get(direction) ; }

	/**
	 * Links this square to a neighbour in the given direction. Note that this
	 * is a one-way connection.
	 * 
	 * @param neighbour
	 *            The neighbour to link.
	 * @param direction
	 *            The direction the new neighbour is in, as seen from this cell.
	 */
	public void link(Square neighbour, Direction direction) {Collect.Hit("Square.java","link(Square neighbour, Direction direction)");neighbours.put(direction, neighbour); Collect.Hit("Square.java","link(Square neighbour, Direction direction)", "1480"); assert invariant(); Collect.Hit("Square.java","link(Square neighbour, Direction direction)", "1521");}

	/**
	 * Returns an immutable list of units occupying this square, in the order in
	 * which they occupied this square (i.e. oldest first.)
	 * 
	 * @return An immutable list of units occupying this square, in the order in
	 *         which they occupied this square (i.e. oldest first.)
	 */
	public List<Unit> getOccupants() {Collect.Hit("Square.java","getOccupants()"); Collect.Hit("Square.java","getOccupants()", "1887");return ImmutableList.copyOf(occupants) ; }

	/**
	 * Adds a new occupant to this square.
	 * 
	 * @param occupant
	 *            The unit to occupy this square.
	 */
	void put(Unit occupant) {Collect.Hit("Square.java","put(Unit occupant)");assert occupant != null; Collect.Hit("Square.java","put(Unit occupant)", "2092"); assert !occupants.contains(occupant); Collect.Hit("Square.java","put(Unit occupant)", "2120"); occupants.add(occupant); Collect.Hit("Square.java","put(Unit occupant)", "2165");}

	/**
	 * Removes the unit from this square if it was present.
	 * 
	 * @param occupant
	 *            The unit to be removed from this square.
	 */
	void remove(Unit occupant) {Collect.Hit("Square.java","remove(Unit occupant)");assert occupant != null; Collect.Hit("Square.java","remove(Unit occupant)", "2384"); occupants.remove(occupant); Collect.Hit("Square.java","remove(Unit occupant)", "2412");}

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
		} Collect.Hit("Square.java","invariant()", "2780"); Collect.Hit("Square.java","invariant()", "2887");return true ; }

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
