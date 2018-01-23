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
	BoardPanel(Game game) {super();Collect.Hit("BoardPanel.java","BoardPanel(Game game)");assert game != null; Collect.Hit("BoardPanel.java","BoardPanel(Game game)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/BoardPanel.java|(1076,20,<51,2>,<51,22>)"); this.game = game; Collect.Hit("BoardPanel.java","BoardPanel(Game game)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/BoardPanel.java|(1100,17,<52,2>,<52,19>)"); Board board = game.getLevel().getBoard(); Collect.Hit("BoardPanel.java","BoardPanel(Game game)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/BoardPanel.java|(1123,41,<54,2>,<54,43>)"); int w = board.getWidth() * SQUARE_SIZE; Collect.Hit("BoardPanel.java","BoardPanel(Game game)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/BoardPanel.java|(1172,39,<56,2>,<56,41>)"); int h = board.getHeight() * SQUARE_SIZE; Collect.Hit("BoardPanel.java","BoardPanel(Game game)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/BoardPanel.java|(1215,40,<57,2>,<57,42>)"); Dimension size = new Dimension(w, h); Collect.Hit("BoardPanel.java","BoardPanel(Game game)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/BoardPanel.java|(1261,37,<59,2>,<59,39>)"); setMinimumSize(size); Collect.Hit("BoardPanel.java","BoardPanel(Game game)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/BoardPanel.java|(1302,21,<60,2>,<60,23>)"); setPreferredSize(size); Collect.Hit("BoardPanel.java","BoardPanel(Game game)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/BoardPanel.java|(1327,23,<61,2>,<61,25>)");}

	@Override
	public void paint(Graphics g) {Collect.Hit("BoardPanel.java","paint(Graphics g)");assert g != null; Collect.Hit("BoardPanel.java","paint(Graphics g)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/BoardPanel.java|(1406,17,<66,2>,<66,19>)"); render(game.getLevel().getBoard(), g, getSize()); Collect.Hit("BoardPanel.java","paint(Graphics g)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/BoardPanel.java|(1427,49,<67,2>,<67,51>)");}

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
	private void render(Board board, Graphics g, Dimension window) {Collect.Hit("BoardPanel.java","render(Board board, Graphics g, Dimension window)");int cellW = window.width / board.getWidth(); Collect.Hit("BoardPanel.java","render(Board board, Graphics g, Dimension window)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/BoardPanel.java|(1849,44,<81,2>,<81,46>)"); int cellH = window.height / board.getHeight(); Collect.Hit("BoardPanel.java","render(Board board, Graphics g, Dimension window)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/BoardPanel.java|(1897,46,<82,2>,<82,48>)"); g.setColor(BACKGROUND_COLOR); Collect.Hit("BoardPanel.java","render(Board board, Graphics g, Dimension window)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/BoardPanel.java|(1949,29,<84,2>,<84,31>)"); g.fillRect(0, 0, window.width, window.height); Collect.Hit("BoardPanel.java","render(Board board, Graphics g, Dimension window)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/BoardPanel.java|(1982,46,<85,2>,<85,48>)"); for (int y = 0; y < board.getHeight(); y++) {
			for (int x = 0; x < board.getWidth(); x++) {
				int cellX = x * cellW;
				int cellY = y * cellH;
				Square square = board.squareAt(x, y);
				render(square, g, cellX, cellY, cellW, cellH);
			}
		} Collect.Hit("BoardPanel.java","render(Board board, Graphics g, Dimension window)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/BoardPanel.java|(2034,256,<87,2>,<94,3>)");}

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
	private void render(Square square, Graphics g, int x, int y, int w, int h) {Collect.Hit("BoardPanel.java","render(Square square, Graphics g, int x, int y, int w, int h)");square.getSprite().draw(g, x, y, w, h); Collect.Hit("BoardPanel.java","render(Square square, Graphics g, int x, int y, int w, int h)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/BoardPanel.java|(2872,39,<115,2>,<115,41>)"); for (Unit unit : square.getOccupants()) {
			unit.getSprite().draw(g, x, y, w, h);
		} Collect.Hit("BoardPanel.java","render(Square square, Graphics g, int x, int y, int w, int h)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/BoardPanel.java|(2915,88,<116,2>,<118,3>)");}
}