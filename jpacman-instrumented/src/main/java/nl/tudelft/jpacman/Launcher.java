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
	public Game getGame() {Collect.Hit("Launcher.java","getGame()"); Collect.Hit("Launcher.java","getGame()", "1289");return game ; }
	
	/**
	 * The map file used to populate the level.
	 * @return The name of the map file.
	 */
	protected String getLevelMap() {Collect.Hit("Launcher.java","getLevelMap()"); Collect.Hit("Launcher.java","getLevelMap()", "1444");return levelMap ; }
	
	/**
	 * Set the name of the file containing this level's map.
	 * @param fileName Map to be used.
	 * @return Level corresponding to the given map.
	 */
	public Launcher withMapFile(String fileName) {Collect.Hit("Launcher.java","withMapFile(String fileName)");levelMap = fileName; Collect.Hit("Launcher.java","withMapFile(String fileName)", "1679"); Collect.Hit("Launcher.java","withMapFile(String fileName)", "1703");return this ; }

	/**
	 * Creates a new game using the level from {@link #makeLevel()}.
	 * 
	 * @return a new Game.
	 */
	public Game makeGame() {Collect.Hit("Launcher.java","makeGame()");GameFactory gf = getGameFactory(); Collect.Hit("Launcher.java","makeGame()", "1862"); Level level = makeLevel(); Collect.Hit("Launcher.java","makeGame()", "1900"); Collect.Hit("Launcher.java","makeGame()", "1930");return gf.createSinglePlayerGame(level) ; }

	/**
	 * Creates a new level. By default this method will use the map parser to
	 * parse the default board stored in the <code>board.txt</code> resource.
	 * 
	 * @return A new level.
	 */
	public Level makeLevel() {Collect.Hit("Launcher.java","makeLevel()");MapParser parser = getMapParser(); Collect.Hit("Launcher.java","makeLevel()", "2205"); try (InputStream boardStream = Launcher.class
				.getResourceAsStream(getLevelMap())) {
			return parser.parseMap(boardStream);
		} catch (IOException e) {
			throw new PacmanConfigurationException("Unable to create level.", e);
		} Collect.Hit("Launcher.java","makeLevel()", "2243");}
	

	/**
	 * @return A new map parser object using the factories from
	 *         {@link #getLevelFactory()} and {@link #getBoardFactory()}.
	 */
	protected MapParser getMapParser() {Collect.Hit("Launcher.java","getMapParser()"); Collect.Hit("Launcher.java","getMapParser()", "2679");return new MapParser(getLevelFactory(), getBoardFactory()) ; }

	/**
	 * @return A new board factory using the sprite store from
	 *         {@link #getSpriteStore()}.
	 */
	protected BoardFactory getBoardFactory() {Collect.Hit("Launcher.java","getBoardFactory()"); Collect.Hit("Launcher.java","getBoardFactory()", "2906");return new BoardFactory(getSpriteStore()) ; }

	/**
	 * @return The default {@link PacManSprites}.
	 */
	protected PacManSprites getSpriteStore() {Collect.Hit("Launcher.java","getSpriteStore()"); Collect.Hit("Launcher.java","getSpriteStore()", "3063");return SPRITE_STORE ; }

	/**
	 * @return A new factory using the sprites from {@link #getSpriteStore()}
	 *         and the ghosts from {@link #getGhostFactory()}.
	 */
	protected LevelFactory getLevelFactory() {Collect.Hit("Launcher.java","getLevelFactory()"); Collect.Hit("Launcher.java","getLevelFactory()", "3287");return new LevelFactory(getSpriteStore(), getGhostFactory()) ; }

	/**
	 * @return A new factory using the sprites from {@link #getSpriteStore()}.
	 */
	protected GhostFactory getGhostFactory() {Collect.Hit("Launcher.java","getGhostFactory()"); Collect.Hit("Launcher.java","getGhostFactory()", "3492");return new GhostFactory(getSpriteStore()) ; }

	/**
	 * @return A new factory using the players from {@link #getPlayerFactory()}.
	 */
	protected GameFactory getGameFactory() {Collect.Hit("Launcher.java","getGameFactory()"); Collect.Hit("Launcher.java","getGameFactory()", "3678");return new GameFactory(getPlayerFactory()) ; }

	/**
	 * @return A new factory using the sprites from {@link #getSpriteStore()}.
	 */
	protected PlayerFactory getPlayerFactory() {Collect.Hit("Launcher.java","getPlayerFactory()"); Collect.Hit("Launcher.java","getPlayerFactory()", "3867");return new PlayerFactory(getSpriteStore()) ; }

	/**
	 * Adds key events UP, DOWN, LEFT and RIGHT to a game.
	 * 
	 * @param builder
	 *            The {@link PacManUiBuilder} that will provide the UI.
	 * @param game
	 *            The game that will process the events.
	 */
	protected void addSinglePlayerKeys(final PacManUiBuilder builder, final Game game) {Collect.Hit("Launcher.java","addSinglePlayerKeys(final PacManUiBuilder builder, final Game game)");final Player p1 = getSinglePlayer(game); Collect.Hit("Launcher.java","addSinglePlayerKeys(final PacManUiBuilder builder, final Game game)", "4244"); builder.addKey(KeyEvent.VK_UP, new Action() {

			@Override
			public void doAction() {Collect.Hit("Launcher.java","doAction()");game.move(p1, Direction.NORTH); Collect.Hit("Launcher.java","doAction()", "4386");}
		}).addKey(KeyEvent.VK_DOWN, new Action() {

			@Override
			public void doAction() {Collect.Hit("Launcher.java","doAction()");game.move(p1, Direction.SOUTH); Collect.Hit("Launcher.java","doAction()", "4520");}
		}).addKey(KeyEvent.VK_LEFT, new Action() {

			@Override
			public void doAction() {Collect.Hit("Launcher.java","doAction()");game.move(p1, Direction.WEST); Collect.Hit("Launcher.java","doAction()", "4654");}
		}).addKey(KeyEvent.VK_RIGHT, new Action() {

			@Override
			public void doAction() {Collect.Hit("Launcher.java","doAction()");game.move(p1, Direction.EAST); Collect.Hit("Launcher.java","doAction()", "4788");}
		}); Collect.Hit("Launcher.java","addSinglePlayerKeys(final PacManUiBuilder builder, final Game game)", "4290");}

	private Player getSinglePlayer(final Game game) {Collect.Hit("Launcher.java","getSinglePlayer(final Game game)");List<Player> players = game.getPlayers(); Collect.Hit("Launcher.java","getSinglePlayer(final Game game)", "4895"); if (players.isEmpty()) {
			throw new IllegalArgumentException("Game has 0 players.");
		} Collect.Hit("Launcher.java","getSinglePlayer(final Game game)", "4940"); Collect.Hit("Launcher.java","getSinglePlayer(final Game game)", "5036");return players.get(0) ; }

	/**
	 * Creates and starts a JPac-Man game.
	 */
	public void launch() {Collect.Hit("Launcher.java","launch()");game = makeGame(); Collect.Hit("Launcher.java","launch()", "5146"); PacManUiBuilder builder = new PacManUiBuilder().withDefaultButtons(); Collect.Hit("Launcher.java","launch()", "5168"); addSinglePlayerKeys(builder, game); Collect.Hit("Launcher.java","launch()", "5241"); pacManUI = builder.build(game); Collect.Hit("Launcher.java","launch()", "5280"); pacManUI.start(); Collect.Hit("Launcher.java","launch()", "5315");}

	/**
	 * Disposes of the UI. For more information see {@link javax.swing.JFrame#dispose()}.
	 */
	public void dispose() {Collect.Hit("Launcher.java","dispose()");pacManUI.dispose(); Collect.Hit("Launcher.java","dispose()", "5468");}

	/**
	 * Main execution method for the Launcher.
	 * 
	 * @param args
	 *            The command line arguments - which are ignored.
	 * @throws IOException
	 *             When a resource could not be read.
	 */
	public static void main(String[] args) throws IOException {Collect.Hit("Launcher.java","main(String[] args)");new Launcher().launch(); Collect.Hit("Launcher.java","main(String[] args)", "5780");}
}
