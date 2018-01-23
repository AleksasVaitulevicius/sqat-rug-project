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
	public BoardFactory(PacManSprites spriteStore) {Collect.Hit("BoardFactory.java","BoardFactory(PacManSprites spriteStore)");this.sprites = spriteStore; Collect.Hit("BoardFactory.java","BoardFactory(PacManSprites spriteStore)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/BoardFactory.java|(689,27,<27,2>,<27,29>)");}

	/**
	 * Creates a new board from a grid of cells and connects it.
	 * 
	 * @param grid
	 *            The square grid of cells, in which grid[x][y] corresponds to
	 *            the square at position x,y.
	 * @return A new board, wrapping a grid of connected cells.
	 */
	public Board createBoard(Square[][] grid) {Collect.Hit("BoardFactory.java","createBoard(Square[][] grid)");assert grid != null; Collect.Hit("BoardFactory.java","createBoard(Square[][] grid)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/BoardFactory.java|(1053,20,<39,2>,<39,22>)"); Board board = new Board(grid); Collect.Hit("BoardFactory.java","createBoard(Square[][] grid)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/BoardFactory.java|(1079,30,<41,2>,<41,32>)"); int width = board.getWidth(); Collect.Hit("BoardFactory.java","createBoard(Square[][] grid)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/BoardFactory.java|(1115,29,<43,2>,<43,31>)"); int height = board.getHeight(); Collect.Hit("BoardFactory.java","createBoard(Square[][] grid)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/BoardFactory.java|(1148,31,<44,2>,<44,33>)"); for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				Square square = grid[x][y];
				for (Direction dir : Direction.values()) {
					int dirX = (width + x + dir.getDeltaX()) % width;
					int dirY = (height + y + dir.getDeltaY()) % height;
					Square neighbour = grid[dirX][dirY];
					square.link(neighbour, dir);
				}
			}
		} Collect.Hit("BoardFactory.java","createBoard(Square[][] grid)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/BoardFactory.java|(1183,363,<45,2>,<55,3>)"); return board; Collect.Hit("BoardFactory.java","createBoard(Square[][] grid)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/BoardFactory.java|(1552,13,<57,2>,<57,15>)");}

	/**
	 * Creates a new square that can be occupied by any unit.
	 * 
	 * @return A new square that can be occupied by any unit.
	 */
	public Square createGround() {Collect.Hit("BoardFactory.java","createGround()");return new Ground(sprites.getGroundSprite()); Collect.Hit("BoardFactory.java","createGround()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/BoardFactory.java|(1746,45,<66,2>,<66,47>)");}

	/**
	 * Creates a new square that cannot be occupied by any unit.
	 * 
	 * @return A new square that cannot be occupied by any unit.
	 */
	public Square createWall() {Collect.Hit("BoardFactory.java","createWall()");return new Wall(sprites.getWallSprite()); Collect.Hit("BoardFactory.java","createWall()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/BoardFactory.java|(1976,41,<75,2>,<75,43>)");}

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
		Wall(Sprite sprite) {Collect.Hit("BoardFactory.java","Wall(Sprite sprite)");this.background = sprite; Collect.Hit("BoardFactory.java","Wall(Sprite sprite)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/BoardFactory.java|(2420,25,<97,3>,<97,28>)");}

		@Override
		public boolean isAccessibleTo(Unit unit) {Collect.Hit("BoardFactory.java","isAccessibleTo(Unit unit)");return false; Collect.Hit("BoardFactory.java","isAccessibleTo(Unit unit)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/BoardFactory.java|(2516,13,<102,3>,<102,16>)");}

		@Override
		public Sprite getSprite() {Collect.Hit("BoardFactory.java","getSprite()");return background; Collect.Hit("BoardFactory.java","getSprite()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/BoardFactory.java|(2585,18,<107,3>,<107,21>)");}
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
		Ground(Sprite sprite) {Collect.Hit("BoardFactory.java","Ground(Sprite sprite)");this.background = sprite; Collect.Hit("BoardFactory.java","Ground(Sprite sprite)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/BoardFactory.java|(3015,25,<130,3>,<130,28>)");}

		@Override
		public boolean isAccessibleTo(Unit unit) {Collect.Hit("BoardFactory.java","isAccessibleTo(Unit unit)");return true; Collect.Hit("BoardFactory.java","isAccessibleTo(Unit unit)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/BoardFactory.java|(3111,12,<135,3>,<135,15>)");}

		@Override
		public Sprite getSprite() {Collect.Hit("BoardFactory.java","getSprite()");return background; Collect.Hit("BoardFactory.java","getSprite()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/board/BoardFactory.java|(3179,18,<140,3>,<140,21>)");}
	}
}
