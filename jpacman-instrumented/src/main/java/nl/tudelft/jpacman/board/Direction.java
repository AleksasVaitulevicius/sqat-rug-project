package nl.tudelft.jpacman.board;import coverageApi.Collect; public enum Direction {

	/**
	 * North, or up.
	 */
	NORTH(0, -1),

	/**
	 * South, or down.
	 */
	SOUTH(0, 1),

	/**
	 * West, or left.
	 */
	WEST(-1, 0),

	/**
	 * East, or right.
	 */
	EAST(1, 0);

	/**
	 * The delta x (width difference) to an element in the direction in a grid
	 * with 0,0 (x,y) as its top-left element.
	 */
	private final int dx;

	/**
	 * The delta y (height difference) to an element in the direction in a grid
	 * with 0,0 (x,y) as its top-left element.
	 */
	private final int dy;

	/**
	 * Creates a new Direction with the given parameters.
	 * 
	 * @param deltaX
	 *            The delta x (width difference) to an element in the direction
	 *            in a matrix with 0,0 (x,y) as its top-left element.
	 * @param deltaY
	 *            The delta y (height difference) to an element in the direction
	 *            in a matrix with 0,0 (x,y) as its top-left element.
	 */
	Direction(int deltaX, int deltaY) {Collect.Hit("Direction.java","Direction(int deltaX, int deltaY)");this.dx = deltaX; Collect.Hit("Direction.java","Direction(int deltaX, int deltaY)", "1145"); this.dy = deltaY; Collect.Hit("Direction.java","Direction(int deltaX, int deltaY)", "1166");}

	/**
	 * @return The delta x (width difference) for a single step in this
	 *         direction, in a matrix with 0,0 (x,y) as its top-left element.
	 */
	public int getDeltaX() {Collect.Hit("Direction.java","getDeltaX()"); Collect.Hit("Direction.java","getDeltaX()", "1378");return dx ; }

	/**
	 * @return The delta y (height difference) for a single step in this
	 *         direction, in a matrix with 0,0 (x,y) as its top-left element.
	 */
	public int getDeltaY() {Collect.Hit("Direction.java","getDeltaY()"); Collect.Hit("Direction.java","getDeltaY()", "1584");return dy ; }
}