package nl.tudelft.jpacman.level;import coverageApi.Collect;import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import nl.tudelft.jpacman.PacmanConfigurationException;
import nl.tudelft.jpacman.board.Board;
import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.npc.NPC; public class MapParser {

	/**
	 * The factory that creates the levels.
	 */
	private final LevelFactory levelCreator;

	/**
	 * The factory that creates the squares and board.
	 */
	private final BoardFactory boardCreator;

	/**
	 * Creates a new map parser.
	 * 
	 * @param levelFactory
	 *            The factory providing the NPC objects and the level.
	 * @param boardFactory
	 *            The factory providing the Square objects and the board.
	 */
	public MapParser(LevelFactory levelFactory, BoardFactory boardFactory) {Collect.Hit("MapParser.java","MapParser(LevelFactory levelFactory, BoardFactory boardFactory)");this.levelCreator = levelFactory; Collect.Hit("MapParser.java","MapParser(LevelFactory levelFactory, BoardFactory boardFactory)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/MapParser.java|(1094,33,<42,2>,<42,35>)"); this.boardCreator = boardFactory; Collect.Hit("MapParser.java","MapParser(LevelFactory levelFactory, BoardFactory boardFactory)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/MapParser.java|(1131,33,<43,2>,<43,35>)");}

	/**
	 * Parses the text representation of the board into an actual level.
	 * 
	 * <ul>
	 * <li>Supported characters:
	 * <li>' ' (space) an empty square.
	 * <li>'#' (bracket) a wall.
	 * <li>'.' (period) a square with a pellet.
	 * <li>'P' (capital P) a starting square for players.
	 * <li>'G' (capital G) a square with a ghost.
	 * </ul>
	 * 
	 * @param map
	 *            The text representation of the board, with map[x][y]
	 *            representing the square at position x,y.
	 * @return The level as represented by this text.
	 */
	public Level parseMap(char[][] map) {Collect.Hit("MapParser.java","parseMap(char[][] map)");int width = map.length; Collect.Hit("MapParser.java","parseMap(char[][] map)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/MapParser.java|(1774,23,<64,2>,<64,25>)"); int height = map[0].length; Collect.Hit("MapParser.java","parseMap(char[][] map)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/MapParser.java|(1801,27,<65,2>,<65,29>)"); Square[][] grid = new Square[width][height]; Collect.Hit("MapParser.java","parseMap(char[][] map)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/MapParser.java|(1834,44,<67,2>,<67,46>)"); List<NPC> ghosts = new ArrayList<>(); Collect.Hit("MapParser.java","parseMap(char[][] map)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/MapParser.java|(1884,37,<69,2>,<69,39>)"); List<Square> startPositions = new ArrayList<>(); Collect.Hit("MapParser.java","parseMap(char[][] map)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/MapParser.java|(1925,48,<70,2>,<70,50>)"); makeGrid(map, width, height, grid, ghosts, startPositions); Collect.Hit("MapParser.java","parseMap(char[][] map)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/MapParser.java|(1979,59,<72,2>,<72,61>)"); Board board = boardCreator.createBoard(grid); Collect.Hit("MapParser.java","parseMap(char[][] map)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/MapParser.java|(2046,45,<74,2>,<74,47>)"); return levelCreator.createLevel(board, ghosts, startPositions); Collect.Hit("MapParser.java","parseMap(char[][] map)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/MapParser.java|(2095,63,<75,2>,<75,65>)");}

	private void makeGrid(char[][] map, int width, int height, Square[][] grid, List<NPC> ghosts, List<Square> startPositions) {Collect.Hit("MapParser.java","makeGrid(char[][] map, int width, int height, Square[][] grid, List<NPC> ghosts, List<Square> startPositions)");for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				char c = map[x][y];
				addSquare(grid, ghosts, startPositions, x, y, c);
			}
		} Collect.Hit("MapParser.java","makeGrid(char[][] map, int width, int height, Square[][] grid, List<NPC> ghosts, List<Square> startPositions)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/MapParser.java|(2295,163,<79,2>,<84,3>)");}

	private void addSquare(Square[][] grid, List<NPC> ghosts, List<Square> startPositions, int x, int y, char c) {Collect.Hit("MapParser.java","addSquare(Square[][] grid, List<NPC> ghosts, List<Square> startPositions, int x, int y, char c)");switch (c) {
		case ' ':
			grid[x][y] = boardCreator.createGround();
			break;
		case '#':
			grid[x][y] = boardCreator.createWall();
			break;
		case '.':
			Square pelletSquare = boardCreator.createGround();
			grid[x][y] = pelletSquare;
			levelCreator.createPellet().occupy(pelletSquare);
			break;
		case 'G':
			Square ghostSquare = makeGhostSquare(ghosts);
			grid[x][y] = ghostSquare;
			break;
		case 'P':
			Square playerSquare = boardCreator.createGround();
			grid[x][y] = playerSquare;
			startPositions.add(playerSquare);
			break;
		default:
			throw new PacmanConfigurationException("Invalid character at "
					+ x + "," + y + ": " + c);
		} Collect.Hit("MapParser.java","addSquare(Square[][] grid, List<NPC> ghosts, List<Square> startPositions, int x, int y, char c)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/MapParser.java|(2581,683,<88,2>,<112,3>)");}

	private Square makeGhostSquare(List<NPC> ghosts) {Collect.Hit("MapParser.java","makeGhostSquare(List<NPC> ghosts)");Square ghostSquare = boardCreator.createGround(); Collect.Hit("MapParser.java","makeGhostSquare(List<NPC> ghosts)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/MapParser.java|(3327,49,<116,2>,<116,51>)"); NPC ghost = levelCreator.createGhost(); Collect.Hit("MapParser.java","makeGhostSquare(List<NPC> ghosts)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/MapParser.java|(3380,39,<117,2>,<117,41>)"); ghosts.add(ghost); Collect.Hit("MapParser.java","makeGhostSquare(List<NPC> ghosts)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/MapParser.java|(3423,18,<118,2>,<118,20>)"); ghost.occupy(ghostSquare); Collect.Hit("MapParser.java","makeGhostSquare(List<NPC> ghosts)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/MapParser.java|(3445,26,<119,2>,<119,28>)"); return ghostSquare; Collect.Hit("MapParser.java","makeGhostSquare(List<NPC> ghosts)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/MapParser.java|(3475,19,<120,2>,<120,21>)");}

	/**
	 * Parses the list of strings into a 2-dimensional character array and
	 * passes it on to {@link #parseMap(char[][])}.
	 * 
	 * @param text
	 *            The plain text, with every entry in the list being a equally
	 *            sized row of squares on the board and the first element being
	 *            the top row.
	 * @return The level as represented by the text.
	 * @throws PacmanConfigurationException If text lines are not properly formatted.
	 */
	public Level parseMap(List<String> text) {Collect.Hit("MapParser.java","parseMap(List<String> text)");checkMapFormat(text); Collect.Hit("MapParser.java","parseMap(List<String> text)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/MapParser.java|(4030,21,<136,2>,<136,23>)"); int height = text.size(); Collect.Hit("MapParser.java","parseMap(List<String> text)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/MapParser.java|(4057,25,<138,2>,<138,27>)"); int width = text.get(0).length(); Collect.Hit("MapParser.java","parseMap(List<String> text)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/MapParser.java|(4086,33,<139,2>,<139,35>)"); char[][] map = new char[width][height]; Collect.Hit("MapParser.java","parseMap(List<String> text)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/MapParser.java|(4125,39,<141,2>,<141,41>)"); for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				map[x][y] = text.get(y).charAt(x);
			}
		} Collect.Hit("MapParser.java","parseMap(List<String> text)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/MapParser.java|(4168,123,<142,2>,<146,3>)"); return parseMap(map); Collect.Hit("MapParser.java","parseMap(List<String> text)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/MapParser.java|(4295,21,<147,2>,<147,23>)");}
	
	/**
	 * Check the correctness of the map lines in the text.
	 * @param text Map to be checked
	 * @throws PacmanConfigurationException if map is not OK.
	 */
	private void checkMapFormat(List<String> text) {Collect.Hit("MapParser.java","checkMapFormat(List<String> text)");if (text == null) {
			throw new PacmanConfigurationException(
					"Input text cannot be null.");
		} Collect.Hit("MapParser.java","checkMapFormat(List<String> text)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/MapParser.java|(4543,105,<156,2>,<159,3>)"); if (text.isEmpty()) {
			throw new PacmanConfigurationException(
					"Input text must consist of at least 1 row.");
		} Collect.Hit("MapParser.java","checkMapFormat(List<String> text)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/MapParser.java|(4654,123,<161,2>,<164,3>)"); int width = text.get(0).length(); Collect.Hit("MapParser.java","checkMapFormat(List<String> text)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/MapParser.java|(4783,33,<166,2>,<166,35>)"); if (width == 0) {
			throw new PacmanConfigurationException(
				"Input text lines cannot be empty.");
		} Collect.Hit("MapParser.java","checkMapFormat(List<String> text)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/MapParser.java|(4822,109,<168,2>,<171,3>)"); for (String line : text) {
			if (line.length() != width) {
				throw new PacmanConfigurationException(
					"Input text lines are not of equal width.");
			}
		} Collect.Hit("MapParser.java","checkMapFormat(List<String> text)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/MapParser.java|(4937,167,<173,2>,<178,3>)");}

	/**
	 * Parses the provided input stream as a character stream and passes it
	 * result to {@link #parseMap(List)}.
	 * 
	 * @param source
	 *            The input stream that will be read.
	 * @return The parsed level as represented by the text on the input stream.
	 * @throws IOException
	 *             when the source could not be read.
	 */
	public Level parseMap(InputStream source) throws IOException {Collect.Hit("MapParser.java","parseMap(InputStream source)");try (BufferedReader reader = new BufferedReader(new InputStreamReader(
				source, "UTF-8"))) {
			List<String> lines = new ArrayList<>();
			while (reader.ready()) {
				lines.add(reader.readLine());
			}
			return parseMap(lines);
		} Collect.Hit("MapParser.java","parseMap(InputStream source)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/MapParser.java|(5539,243,<192,2>,<199,3>)");}
}
