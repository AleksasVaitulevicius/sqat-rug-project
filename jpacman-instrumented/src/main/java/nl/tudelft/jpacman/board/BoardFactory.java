package nl.tudelft.jpacman.board;import coverageApi.Collect;import nl.tudelft.jpacman.sprite.PacManSprites;
import nl.tudelft.jpacman.sprite.Sprite; public class BoardFactory {

	/**
	 * The sprite store providing the sprites for the background.
	 */
	private final PacManSprites sprites;

	/**
	 * Creates a new BoardFactory that will create a board with the provided
	 * background sprites.
	 * 
	 * @param spriteStore
	 *            The sprite store providing the sprites for the background.
	 */
	public BoardFactory(PacManSprites spriteStore) {Collect.Hit("BoardFactory.java","BoardFactory(PacManSprites spriteStore)");this.sprites = spriteStore; Collect.Hit("BoardFactory.java","BoardFactory(PacManSprites spriteStore)", "689");}

	/**
	 * Creates a new board from a grid of cells and connects it.
	 * 
	 * @param grid
	 *            The square grid of cells, in which grid[x][y] corresponds to
	 *            the square at position x,y.
	 * @return A new board, wrapping a grid of connected cells.
	 */
	public Board createBoard(Square[][] grid) {Collect.Hit("BoardFactory.java","createBoard(Square[][] grid)");assert grid != null; Collect.Hit("BoardFactory.java","createBoard(Square[][] grid)", "1053"); Board board = new Board(grid); Collect.Hit("BoardFactory.java","createBoard(Square[][] grid)", "1079"); int width = board.getWidth(); Collect.Hit("BoardFactory.java","createBoard(Square[][] grid)", "1115"); int height = board.getHeight(); Collect.Hit("BoardFactory.java","createBoard(Square[][] grid)", "1148"); for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				Square square = grid[x][y];
				for (Direction dir : Direction.values()) {
					int dirX = (width + x + dir.getDeltaX()) % width;
					int dirY = (height + y + dir.getDeltaY()) % height;
					Square neighbour = grid[dirX][dirY];
					square.link(neighbour, dir);
				}
			}
		} Collect.Hit("BoardFactory.java","createBoard(Square[][] grid)", "1183"); Collect.Hit("BoardFactory.java","createBoard(Square[][] grid)", "1552");return board ; }

	/**
	 * Creates a new square that can be occupied by any unit.
	 * 
	 * @return A new square that can be occupied by any unit.
	 */
	public Square createGround() {Collect.Hit("BoardFactory.java","createGround()"); Collect.Hit("BoardFactory.java","createGround()", "1746");return new Ground(sprites.getGroundSprite()) ; }

	/**
	 * Creates a new square that cannot be occupied by any unit.
	 * 
	 * @return A new square that cannot be occupied by any unit.
	 */
	public Square createWall() {Collect.Hit("BoardFactory.java","createWall()"); Collect.Hit("BoardFactory.java","createWall()", "1976");return new Wall(sprites.getWallSprite()) ; }

	/**
	 * A wall is a square that is inaccessible to anyone.
	 * 
	 * @author Jeroen Roosen 
	 */
	private static final class Wall extends Square {

		/**
		 * The background for this square.
		 */
		private final Sprite background;

		/**
		 * Creates a new wall square.
		 * 
		 * @param sprite
		 *            The background for the square.
		 */
		Wall(Sprite sprite) {Collect.Hit("BoardFactory.java","Wall(Sprite sprite)");this.background = sprite; Collect.Hit("BoardFactory.java","Wall(Sprite sprite)", "2420");}

		@Override
		public boolean isAccessibleTo(Unit unit) {Collect.Hit("BoardFactory.java","isAccessibleTo(Unit unit)"); Collect.Hit("BoardFactory.java","isAccessibleTo(Unit unit)", "2516");return false ; }

		@Override
		public Sprite getSprite() {Collect.Hit("BoardFactory.java","getSprite()"); Collect.Hit("BoardFactory.java","getSprite()", "2585");return background ; }
	}

	/**
	 * A wall is a square that is accessible to anyone.
	 * 
	 * @author Jeroen Roosen 
	 */
	private static final class Ground extends Square {

		/**
		 * The background for this square.
		 */
		private final Sprite background;

		/**
		 * Creates a new ground square.
		 * 
		 * @param sprite
		 *            The background for the square.
		 */
		Ground(Sprite sprite) {Collect.Hit("BoardFactory.java","Ground(Sprite sprite)");this.background = sprite; Collect.Hit("BoardFactory.java","Ground(Sprite sprite)", "3015");}

		@Override
		public boolean isAccessibleTo(Unit unit) {Collect.Hit("BoardFactory.java","isAccessibleTo(Unit unit)"); Collect.Hit("BoardFactory.java","isAccessibleTo(Unit unit)", "3111");return true ; }

		@Override
		public Sprite getSprite() {Collect.Hit("BoardFactory.java","getSprite()"); Collect.Hit("BoardFactory.java","getSprite()", "3179");return background ; }
	}
}
