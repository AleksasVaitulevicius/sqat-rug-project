package nl.tudelft.jpacman.ui;import coverageApi.Collect;import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import nl.tudelft.jpacman.board.Board;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.board.Unit;
import nl.tudelft.jpacman.game.Game; class BoardPanel extends JPanel {

	/**
	 * Default serialisation ID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The background colour of the board.
	 */
	private static final Color BACKGROUND_COLOR = Color.BLACK;

	/**
	 * The size (in pixels) of a square on the board. The initial size of this
	 * panel will scale to fit a board with square of this size.
	 */
	private static final int SQUARE_SIZE = 16;

	/**
	 * The game to display.
	 */
	private final Game game;

	/**
	 * Creates a new board panel that will display the provided game.
	 * 
	 * @param game
	 *            The game to display.
	 */
	BoardPanel(Game game) {super();Collect.Hit("BoardPanel.java","BoardPanel(Game game)");assert game != null; Collect.Hit("BoardPanel.java","BoardPanel(Game game)", "1076"); this.game = game; Collect.Hit("BoardPanel.java","BoardPanel(Game game)", "1100"); Board board = game.getLevel().getBoard(); Collect.Hit("BoardPanel.java","BoardPanel(Game game)", "1123"); int w = board.getWidth() * SQUARE_SIZE; Collect.Hit("BoardPanel.java","BoardPanel(Game game)", "1172"); int h = board.getHeight() * SQUARE_SIZE; Collect.Hit("BoardPanel.java","BoardPanel(Game game)", "1215"); Dimension size = new Dimension(w, h); Collect.Hit("BoardPanel.java","BoardPanel(Game game)", "1261"); setMinimumSize(size); Collect.Hit("BoardPanel.java","BoardPanel(Game game)", "1302"); setPreferredSize(size); Collect.Hit("BoardPanel.java","BoardPanel(Game game)", "1327");}

	@Override
	public void paint(Graphics g) {Collect.Hit("BoardPanel.java","paint(Graphics g)");assert g != null; Collect.Hit("BoardPanel.java","paint(Graphics g)", "1406"); render(game.getLevel().getBoard(), g, getSize()); Collect.Hit("BoardPanel.java","paint(Graphics g)", "1427");}

	/**
	 * Renders the board on the given graphics context to the given dimensions.
	 * 
	 * @param board
	 *            The board to render.
	 * @param g
	 *            The graphics context to draw on.
	 * @param window
	 *            The dimensions to scale the rendered board to.
	 */
	private void render(Board board, Graphics g, Dimension window) {Collect.Hit("BoardPanel.java","render(Board board, Graphics g, Dimension window)");int cellW = window.width / board.getWidth(); Collect.Hit("BoardPanel.java","render(Board board, Graphics g, Dimension window)", "1849"); int cellH = window.height / board.getHeight(); Collect.Hit("BoardPanel.java","render(Board board, Graphics g, Dimension window)", "1897"); g.setColor(BACKGROUND_COLOR); Collect.Hit("BoardPanel.java","render(Board board, Graphics g, Dimension window)", "1949"); g.fillRect(0, 0, window.width, window.height); Collect.Hit("BoardPanel.java","render(Board board, Graphics g, Dimension window)", "1982"); for (int y = 0; y < board.getHeight(); y++) {
			for (int x = 0; x < board.getWidth(); x++) {
				int cellX = x * cellW;
				int cellY = y * cellH;
				Square square = board.squareAt(x, y);
				render(square, g, cellX, cellY, cellW, cellH);
			}
		} Collect.Hit("BoardPanel.java","render(Board board, Graphics g, Dimension window)", "2034");}

	/**
	 * Renders a single square on the given graphics context on the specified
	 * rectangle.
	 * 
	 * @param square
	 *            The square to render.
	 * @param g
	 *            The graphics context to draw on.
	 * @param x
	 *            The x position to start drawing.
	 * @param y
	 *            The y position to start drawing.
	 * @param w
	 *            The width of this square (in pixels.)
	 * @param h
	 *            The height of this square (in pixels.)
	 */
	private void render(Square square, Graphics g, int x, int y, int w, int h) {Collect.Hit("BoardPanel.java","render(Square square, Graphics g, int x, int y, int w, int h)");square.getSprite().draw(g, x, y, w, h); Collect.Hit("BoardPanel.java","render(Square square, Graphics g, int x, int y, int w, int h)", "2872"); for (Unit unit : square.getOccupants()) {
			unit.getSprite().draw(g, x, y, w, h);
		} Collect.Hit("BoardPanel.java","render(Square square, Graphics g, int x, int y, int w, int h)", "2915");}
}