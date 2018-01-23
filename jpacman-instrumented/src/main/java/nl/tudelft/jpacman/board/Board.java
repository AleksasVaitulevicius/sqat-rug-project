package nl.tudelft.jpacman.board;import coverageApi.Collect; public class Board {

	/**
	 * The grid of squares with board[x][y] being the square at column x, row y.
	 */
	private final Square[][] board;

	/**
	 * Creates a new board.
	 * 
	 * @param grid
	 *            The grid of squares with grid[x][y] being the square at column
	 *            x, row y.
	 */
	Board(Square[][] grid) {Collect.Hit("Board.java","Board(Square[][] grid)");assert grid != null; Collect.Hit("Board.java","Board(Square[][] grid)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/Board.java|(477,20,<23,2>,<23,22>)"); this.board = grid; Collect.Hit("Board.java","Board(Square[][] grid)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/Board.java|(501,18,<24,2>,<24,20>)"); assert invariant() : "Initial grid cannot contain null squares"; Collect.Hit("Board.java","Board(Square[][] grid)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/Board.java|(523,64,<25,2>,<25,66>)");}
	
	/**
	 * Whatever happens, the squares on the board can't be null.
	 * @return false if any square on the board is null.
	 */
	protected final boolean invariant() {Collect.Hit("Board.java","invariant()");for (Square[] row : board) {
			for (Square square : row) {
				if (square == null) {
					return false;
				}
			}
		} Collect.Hit("Board.java","invariant()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/Board.java|(768,125,<33,2>,<39,3>)"); return true; Collect.Hit("Board.java","invariant()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/Board.java|(897,12,<40,2>,<40,14>)");}

	/**
	 * Returns the number of columns.
	 * 
	 * @return The width of this board.
	 */
	public int getWidth() {Collect.Hit("Board.java","getWidth()");return board.length; Collect.Hit("Board.java","getWidth()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/Board.java|(1037,20,<49,2>,<49,22>)");}

	/**
	 * Returns the number of rows.
	 * 
	 * @return The height of this board.
	 */
	public int getHeight() {Collect.Hit("Board.java","getHeight()");return board[0].length; Collect.Hit("Board.java","getHeight()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/Board.java|(1184,23,<58,2>,<58,25>)");}

	/**
	 * Returns the square at the given <code>x,y</code> position.
	 * 
	 * @param x
	 *            The <code>x</code> position (column) of the requested square.
	 * @param y
	 *            The <code>y</code> position (row) of the requested square.
	 * @return The square at the given <code>x,y</code> position (never null).
	 */
	public Square squareAt(int x, int y) {Collect.Hit("Board.java","squareAt(int x, int y)");assert withinBorders(x, y); Collect.Hit("Board.java","squareAt(int x, int y)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/Board.java|(1598,27,<71,2>,<71,29>)"); Square result = board[x][y]; Collect.Hit("Board.java","squareAt(int x, int y)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/Board.java|(1629,28,<72,2>,<72,30>)"); assert result != null : "Follows from invariant."; Collect.Hit("Board.java","squareAt(int x, int y)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/Board.java|(1661,50,<73,2>,<73,52>)"); return result; Collect.Hit("Board.java","squareAt(int x, int y)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/Board.java|(1715,14,<74,2>,<74,16>)");}

	/**
	 * Determines whether the given <code>x,y</code> position is on this board.
	 * 
	 * @param x
	 *            The <code>x</code> position (row) to test.
	 * @param y
	 *            The <code>y</code> position (column) to test.
	 * @return <code>true</code> iff the position is on this board.
	 */
	public boolean withinBorders(int x, int y) {Collect.Hit("Board.java","withinBorders(int x, int y)");return x >= 0 && x < getWidth() && y >= 0 && y < getHeight(); Collect.Hit("Board.java","withinBorders(int x, int y)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/Board.java|(2097,61,<87,2>,<87,63>)");}
}
