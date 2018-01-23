package nl.tudelft.jpacman.board;import coverageApi.Collect;import nl.tudelft.jpacman.sprite.Sprite; public abstract class Unit {

	/**
	 * The square this unit is currently occupying.
	 */
	private Square square;
	
	/**
	 * The direction this unit is facing.
	 */
	private Direction direction;

	/**
	 * Creates a unit that is facing east.
	 */
	protected Unit() {Collect.Hit("Unit.java","Unit()");this.direction = Direction.EAST; Collect.Hit("Unit.java","Unit()", "457");}
	
	/**
	 * Sets this unit to face the new direction.
	 * @param newDirection The new direction this unit is facing.
	 */
	public void setDirection(Direction newDirection) {Collect.Hit("Unit.java","setDirection(Direction newDirection)");this.direction = newDirection; Collect.Hit("Unit.java","setDirection(Direction newDirection)", "676");}
	
	/**
	 * Returns the current direction this unit is facing.
	 * @return The current direction this unit is facing.
	 */
	public Direction getDirection() {Collect.Hit("Unit.java","getDirection()"); Collect.Hit("Unit.java","getDirection()", "877");return this.direction ; }
	
	/**
	 * Returns the square this unit is currently occupying.
	 * 
	 * @return The square this unit is currently occupying, or <code>null</code>
	 *         if this unit is not on a square.
	 */
	public Square getSquare() {Collect.Hit("Unit.java","getSquare()");assert invariant(); Collect.Hit("Unit.java","getSquare()", "1141"); Collect.Hit("Unit.java","getSquare()", "1164");return square ; }

	/**
	 * Occupies the target square iff this unit is allowed to as decided by
	 * {@link Square#isAccessibleTo(Unit)}.
	 * 
	 * @param target
	 *            The square to occupy.
	 */
	public void occupy(Square target) {Collect.Hit("Unit.java","occupy(Square target)");assert target != null; Collect.Hit("Unit.java","occupy(Square target)", "1417"); if (square != null) {
			square.remove(this);
		} Collect.Hit("Unit.java","occupy(Square target)", "1447"); square = target; Collect.Hit("Unit.java","occupy(Square target)", "1502"); target.put(this); Collect.Hit("Unit.java","occupy(Square target)", "1522"); assert invariant(); Collect.Hit("Unit.java","occupy(Square target)", "1543");}
	
	/**
	 * Leaves the currently occupying square, thus removing this unit from the board.
	 */
	public void leaveSquare() {Collect.Hit("Unit.java","leaveSquare()");if (square != null) {
			square.remove(this);
			square = null;
		} Collect.Hit("Unit.java","leaveSquare()", "1699"); assert invariant(); Collect.Hit("Unit.java","leaveSquare()", "1773");}

	/**
	 * Tests whether the square this unit is occupying has this unit listed as
	 * one of its occupiers.
	 * 
	 * @return <code>true</code> if the square this unit is occupying has this
	 *         unit listed as one of its occupiers, or if this unit is currently
	 *         not occupying any square.
	 */
	protected boolean invariant() {Collect.Hit("Unit.java","invariant()"); Collect.Hit("Unit.java","invariant()", "2153");return square == null || square.getOccupants().contains(this) ; }

	/**
	 * Returns the sprite of this unit.
	 * 
	 * @return The sprite of this unit.
	 */
	public abstract Sprite getSprite();

}
