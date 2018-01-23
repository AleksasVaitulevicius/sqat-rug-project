package nl.tudelft.jpacman;import coverageApi.Collect;import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.game.Game;
import nl.tudelft.jpacman.game.GameFactory;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.LevelFactory;
import nl.tudelft.jpacman.level.MapParser;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.level.PlayerFactory;
import nl.tudelft.jpacman.npc.ghost.GhostFactory;
import nl.tudelft.jpacman.sprite.PacManSprites;
import nl.tudelft.jpacman.ui.Action;
import nl.tudelft.jpacman.ui.PacManUI;
import nl.tudelft.jpacman.ui.PacManUiBuilder; @SuppressWarnings("PMD.TooManyMethods")
public class Launcher {

	private static final PacManSprites SPRITE_STORE = new PacManSprites();
	
	public static final String DEFAULT_MAP = "/board.txt";
	private String levelMap = DEFAULT_MAP;

	private PacManUI pacManUI;
	private Game game;

	/**
	 * @return The game object this launcher will start when {@link #launch()}
	 *         is called.
	 */
	public Game getGame() {Collect.Hit("Launcher.java","getGame()");return game; Collect.Hit("Launcher.java","getGame()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/Launcher.java|(1289,12,<44,2>,<44,14>)");}
	
	/**
	 * The map file used to populate the level.
	 * @return The name of the map file.
	 */
	protected String getLevelMap() {Collect.Hit("Launcher.java","getLevelMap()");return levelMap; Collect.Hit("Launcher.java","getLevelMap()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/Launcher.java|(1444,16,<52,2>,<52,18>)");}
	
	/**
	 * Set the name of the file containing this level's map.
	 * @param fileName Map to be used.
	 * @return Level corresponding to the given map.
	 */
	public Launcher withMapFile(String fileName) {Collect.Hit("Launcher.java","withMapFile(String fileName)");levelMap = fileName; Collect.Hit("Launcher.java","withMapFile(String fileName)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/Launcher.java|(1679,20,<61,2>,<61,22>)"); return this; Collect.Hit("Launcher.java","withMapFile(String fileName)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/Launcher.java|(1703,12,<62,2>,<62,14>)");}

	/**
	 * Creates a new game using the level from {@link #makeLevel()}.
	 * 
	 * @return a new Game.
	 */
	public Game makeGame() {Collect.Hit("Launcher.java","makeGame()");GameFactory gf = getGameFactory(); Collect.Hit("Launcher.java","makeGame()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/Launcher.java|(1862,34,<71,2>,<71,36>)"); Level level = makeLevel(); Collect.Hit("Launcher.java","makeGame()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/Launcher.java|(1900,26,<72,2>,<72,28>)"); return gf.createSinglePlayerGame(level); Collect.Hit("Launcher.java","makeGame()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/Launcher.java|(1930,40,<73,2>,<73,42>)");}

	/**
	 * Creates a new level. By default this method will use the map parser to
	 * parse the default board stored in the <code>board.txt</code> resource.
	 * 
	 * @return A new level.
	 */
	public Level makeLevel() {Collect.Hit("Launcher.java","makeLevel()");MapParser parser = getMapParser(); Collect.Hit("Launcher.java","makeLevel()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/Launcher.java|(2205,34,<83,2>,<83,36>)"); try (InputStream boardStream = Launcher.class
				.getResourceAsStream(getLevelMap())) {
			return parser.parseMap(boardStream);
		} catch (IOException e) {
			throw new PacmanConfigurationException("Unable to create level.", e);
		} Collect.Hit("Launcher.java","makeLevel()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/Launcher.java|(2243,238,<84,2>,<89,3>)");}
	

	/**
	 * @return A new map parser object using the factories from
	 *         {@link #getLevelFactory()} and {@link #getBoardFactory()}.
	 */
	protected MapParser getMapParser() {Collect.Hit("Launcher.java","getMapParser()");return new MapParser(getLevelFactory(), getBoardFactory()); Collect.Hit("Launcher.java","getMapParser()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/Launcher.java|(2679,59,<98,2>,<98,61>)");}

	/**
	 * @return A new board factory using the sprite store from
	 *         {@link #getSpriteStore()}.
	 */
	protected BoardFactory getBoardFactory() {Collect.Hit("Launcher.java","getBoardFactory()");return new BoardFactory(getSpriteStore()); Collect.Hit("Launcher.java","getBoardFactory()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/Launcher.java|(2906,42,<106,2>,<106,44>)");}

	/**
	 * @return The default {@link PacManSprites}.
	 */
	protected PacManSprites getSpriteStore() {Collect.Hit("Launcher.java","getSpriteStore()");return SPRITE_STORE; Collect.Hit("Launcher.java","getSpriteStore()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/Launcher.java|(3063,20,<113,2>,<113,22>)");}

	/**
	 * @return A new factory using the sprites from {@link #getSpriteStore()}
	 *         and the ghosts from {@link #getGhostFactory()}.
	 */
	protected LevelFactory getLevelFactory() {Collect.Hit("Launcher.java","getLevelFactory()");return new LevelFactory(getSpriteStore(), getGhostFactory()); Collect.Hit("Launcher.java","getLevelFactory()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/Launcher.java|(3287,61,<121,2>,<121,63>)");}

	/**
	 * @return A new factory using the sprites from {@link #getSpriteStore()}.
	 */
	protected GhostFactory getGhostFactory() {Collect.Hit("Launcher.java","getGhostFactory()");return new GhostFactory(getSpriteStore()); Collect.Hit("Launcher.java","getGhostFactory()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/Launcher.java|(3492,42,<128,2>,<128,44>)");}

	/**
	 * @return A new factory using the players from {@link #getPlayerFactory()}.
	 */
	protected GameFactory getGameFactory() {Collect.Hit("Launcher.java","getGameFactory()");return new GameFactory(getPlayerFactory()); Collect.Hit("Launcher.java","getGameFactory()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/Launcher.java|(3678,43,<135,2>,<135,45>)");}

	/**
	 * @return A new factory using the sprites from {@link #getSpriteStore()}.
	 */
	protected PlayerFactory getPlayerFactory() {Collect.Hit("Launcher.java","getPlayerFactory()");return new PlayerFactory(getSpriteStore()); Collect.Hit("Launcher.java","getPlayerFactory()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/Launcher.java|(3867,43,<142,2>,<142,45>)");}

	/**
	 * Adds key events UP, DOWN, LEFT and RIGHT to a game.
	 * 
	 * @param builder
	 *            The {@link PacManUiBuilder} that will provide the UI.
	 * @param game
	 *            The game that will process the events.
	 */
	protected void addSinglePlayerKeys(final PacManUiBuilder builder, final Game game) {Collect.Hit("Launcher.java","addSinglePlayerKeys(final PacManUiBuilder builder, final Game game)");final Player p1 = getSinglePlayer(game); Collect.Hit("Launcher.java","addSinglePlayerKeys(final PacManUiBuilder builder, final Game game)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/Launcher.java|(4244,40,<154,2>,<154,42>)"); builder.addKey(KeyEvent.VK_UP, new Action() {

			@Override
			public void doAction() {Collect.Hit("Launcher.java","doAction()");game.move(p1, Direction.NORTH); Collect.Hit("Launcher.java","doAction()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/Launcher.java|(4386,31,<160,4>,<160,35>)");}
		}).addKey(KeyEvent.VK_DOWN, new Action() {

			@Override
			public void doAction() {Collect.Hit("Launcher.java","doAction()");game.move(p1, Direction.SOUTH); Collect.Hit("Launcher.java","doAction()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/Launcher.java|(4520,31,<166,4>,<166,35>)");}
		}).addKey(KeyEvent.VK_LEFT, new Action() {

			@Override
			public void doAction() {Collect.Hit("Launcher.java","doAction()");game.move(p1, Direction.WEST); Collect.Hit("Launcher.java","doAction()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/Launcher.java|(4654,30,<172,4>,<172,34>)");}
		}).addKey(KeyEvent.VK_RIGHT, new Action() {

			@Override
			public void doAction() {Collect.Hit("Launcher.java","doAction()");game.move(p1, Direction.EAST); Collect.Hit("Launcher.java","doAction()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/Launcher.java|(4788,30,<178,4>,<178,34>)");}
		}); Collect.Hit("Launcher.java","addSinglePlayerKeys(final PacManUiBuilder builder, final Game game)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/Launcher.java|(4290,541,<156,2>,<180,5>)");}

	private Player getSinglePlayer(final Game game) {Collect.Hit("Launcher.java","getSinglePlayer(final Game game)");List<Player> players = game.getPlayers(); Collect.Hit("Launcher.java","getSinglePlayer(final Game game)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/Launcher.java|(4895,41,<185,2>,<185,43>)"); if (players.isEmpty()) {
			throw new IllegalArgumentException("Game has 0 players.");
		} Collect.Hit("Launcher.java","getSinglePlayer(final Game game)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/Launcher.java|(4940,92,<186,2>,<188,3>)"); return players.get(0); Collect.Hit("Launcher.java","getSinglePlayer(final Game game)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/Launcher.java|(5036,22,<189,2>,<189,24>)");}

	/**
	 * Creates and starts a JPac-Man game.
	 */
	public void launch() {Collect.Hit("Launcher.java","launch()");game = makeGame(); Collect.Hit("Launcher.java","launch()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/Launcher.java|(5146,18,<196,2>,<196,20>)"); PacManUiBuilder builder = new PacManUiBuilder().withDefaultButtons(); Collect.Hit("Launcher.java","launch()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/Launcher.java|(5168,69,<197,2>,<197,71>)"); addSinglePlayerKeys(builder, game); Collect.Hit("Launcher.java","launch()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/Launcher.java|(5241,35,<198,2>,<198,37>)"); pacManUI = builder.build(game); Collect.Hit("Launcher.java","launch()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/Launcher.java|(5280,31,<199,2>,<199,33>)"); pacManUI.start(); Collect.Hit("Launcher.java","launch()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/Launcher.java|(5315,17,<200,2>,<200,19>)");}

	/**
	 * Disposes of the UI. For more information see {@link javax.swing.JFrame#dispose()}.
	 */
	public void dispose() {Collect.Hit("Launcher.java","dispose()");pacManUI.dispose(); Collect.Hit("Launcher.java","dispose()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/Launcher.java|(5468,19,<207,2>,<207,21>)");}

	/**
	 * Main execution method for the Launcher.
	 * 
	 * @param args
	 *            The command line arguments - which are ignored.
	 * @throws IOException
	 *             When a resource could not be read.
	 */
	public static void main(String[] args) throws IOException {Collect.Hit("Launcher.java","main(String[] args)");new Launcher().launch(); Collect.Hit("Launcher.java","main(String[] args)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/Launcher.java|(5780,24,<219,2>,<219,26>)");}
}
