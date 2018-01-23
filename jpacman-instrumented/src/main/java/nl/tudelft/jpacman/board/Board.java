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
	Board(Square[][] grid) {Collect.Hit("Board.java","Board(Square[][] grid)");assert grid != null; Collect.Hit("Board.java","Board(Square[][] grid)", "477"); this.board = grid; Collect.Hit("Board.java","Board(Square[][] grid)", "501"); assert invariant() : "Initial grid cannot contain null squares"; Collect.Hit("Board.java","Board(Square[][] grid)", "523");}
	
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
		} Collect.Hit("Board.java","invariant()", "768"); Collect.Hit("Board.java","invariant()", "897");return true ; }

	/**
	 * Returns the number of columns.
	 * 
	 * @return The width of this board.
	 */
	public int getWidth() {Collect.Hit("Board.java","getWidth()"); Collect.Hit("Board.java","getWidth()", "1037");return board.length ; }

	/**
	 * Returns the number of rows.
	 * 
	 * @return The height of this board.
	 */
	public int getHeight() {Collect.Hit("Board.java","getHeight()"); Collect.Hit("Board.java","getHeight()", "1184");return board[0].length ; }

	/**
	 * Returns the square at the given <code>x,y</code> position.
	 * 
	 * @param x
	 *            The <code>x</code> position (column) of the requested square.
	 * @param y
	 *            The <code>y</code> position (row) of the requested square.
	 * @return The square at the given <code>x,y</code> position (never null).
	 */
	public Square squareAt(int x, int y) {Collect.Hit("Board.java","squareAt(int x, int y)");assert withinBorders(x, y); Collect.Hit("Board.java","squareAt(int x, int y)", "1598"); Square result = board[x][y]; Collect.Hit("Board.java","squareAt(int x, int y)", "1629"); assert result != null : "Follows from invariant."; Collect.Hit("Board.java","squareAt(int x, int y)", "1661"); Collect.Hit("Board.java","squareAt(int x, int y)", "1715");return result ; }

	/**
	 * Determines whether the given <code>x,y</code> position is on this board.
	 * 
	 * @param x
	 *            The <code>x</code> position (row) to test.
	 * @param y
	 *            The <code>y</code> position (column) to test.
	 * @return <code>true</code> iff the position is on this board.
	 */
	public boolean withinBorders(int x, int y) {Collect.Hit("Board.java","withinBorders(int x, int y)"); Collect.Hit("Board.java","withinBorders(int x, int y)", "2097");return x >= 0 && x < getWidth() && y >= 0 && y < getHeight() ; }
}
