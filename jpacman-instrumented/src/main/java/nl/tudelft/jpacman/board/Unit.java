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
	protected Unit() {Collect.Hit("Unit.java","Unit()");this.direction = Direction.EAST; Collect.Hit("Unit.java","Unit()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/Unit.java|(457,32,<26,2>,<26,34>)");}
	
	/**
	 * Sets this unit to face the new direction.
	 * @param newDirection The new direction this unit is facing.
	 */
	public void setDirection(Direction newDirection) {Collect.Hit("Unit.java","setDirection(Direction newDirection)");this.direction = newDirection; Collect.Hit("Unit.java","setDirection(Direction newDirection)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/Unit.java|(676,30,<34,2>,<34,32>)");}
	
	/**
	 * Returns the current direction this unit is facing.
	 * @return The current direction this unit is facing.
	 */
	public Direction getDirection() {Collect.Hit("Unit.java","getDirection()");return this.direction; Collect.Hit("Unit.java","getDirection()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/Unit.java|(877,22,<42,2>,<42,24>)");}
	
	/**
	 * Returns the square this unit is currently occupying.
	 * 
	 * @return The square this unit is currently occupying, or <code>null</code>
	 *         if this unit is not on a square.
	 */
	public Square getSquare() {Collect.Hit("Unit.java","getSquare()");assert invariant(); Collect.Hit("Unit.java","getSquare()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/Unit.java|(1141,19,<52,2>,<52,21>)"); return square; Collect.Hit("Unit.java","getSquare()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/Unit.java|(1164,14,<53,2>,<53,16>)");}

	/**
	 * Occupies the target square iff this unit is allowed to as decided by
	 * {@link Square#isAccessibleTo(Unit)}.
	 * 
	 * @param target
	 *            The square to occupy.
	 */
	public void occupy(Square target) {Collect.Hit("Unit.java","occupy(Square target)");assert target != null; Collect.Hit("Unit.java","occupy(Square target)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/Unit.java|(1417,22,<64,2>,<64,24>)"); if (square != null) {
			square.remove(this);
		} Collect.Hit("Unit.java","occupy(Square target)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/Unit.java|(1447,51,<66,2>,<68,3>)"); square = target; Collect.Hit("Unit.java","occupy(Square target)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/Unit.java|(1502,16,<69,2>,<69,18>)"); target.put(this); Collect.Hit("Unit.java","occupy(Square target)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/Unit.java|(1522,17,<70,2>,<70,19>)"); assert invariant(); Collect.Hit("Unit.java","occupy(Square target)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/Unit.java|(1543,19,<71,2>,<71,21>)");}
	
	/**
	 * Leaves the currently occupying square, thus removing this unit from the board.
	 */
	public void leaveSquare() {Collect.Hit("Unit.java","leaveSquare()");if (square != null) {
			square.remove(this);
			square = null;
		} Collect.Hit("Unit.java","leaveSquare()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/Unit.java|(1699,70,<78,2>,<81,3>)"); assert invariant(); Collect.Hit("Unit.java","leaveSquare()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/Unit.java|(1773,19,<82,2>,<82,21>)");}

	/**
	 * Tests whether the square this unit is occupying has this unit listed as
	 * one of its occupiers.
	 * 
	 * @return <code>true</code> if the square this unit is occupying has this
	 *         unit listed as one of its occupiers, or if this unit is currently
	 *         not occupying any square.
	 */
	protected boolean invariant() {Collect.Hit("Unit.java","invariant()");return square == null || square.getOccupants().contains(this); Collect.Hit("Unit.java","invariant()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/Unit.java|(2153,62,<94,2>,<94,64>)");}

	/**
	 * Returns the sprite of this unit.
	 * 
	 * @return The sprite of this unit.
	 */
	public abstract Sprite getSprite();

}
