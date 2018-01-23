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
	Direction(int deltaX, int deltaY) {Collect.Hit("Direction.java","Direction(int deltaX, int deltaY)");this.dx = deltaX; Collect.Hit("Direction.java","Direction(int deltaX, int deltaY)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/Direction.java|(1145,17,<53,2>,<53,19>)"); this.dy = deltaY; Collect.Hit("Direction.java","Direction(int deltaX, int deltaY)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/Direction.java|(1166,17,<54,2>,<54,19>)");}

	/**
	 * @return The delta x (width difference) for a single step in this
	 *         direction, in a matrix with 0,0 (x,y) as its top-left element.
	 */
	public int getDeltaX() {Collect.Hit("Direction.java","getDeltaX()");return dx; Collect.Hit("Direction.java","getDeltaX()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/Direction.java|(1378,10,<62,2>,<62,12>)");}

	/**
	 * @return The delta y (height difference) for a single step in this
	 *         direction, in a matrix with 0,0 (x,y) as its top-left element.
	 */
	public int getDeltaY() {Collect.Hit("Direction.java","getDeltaY()");return dy; Collect.Hit("Direction.java","getDeltaY()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/Direction.java|(1584,10,<70,2>,<70,12>)");}
}