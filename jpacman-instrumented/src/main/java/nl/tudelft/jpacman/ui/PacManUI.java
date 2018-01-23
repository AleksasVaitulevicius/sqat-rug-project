package nl.tudelft.jpacman.ui;import coverageApi.Collect;import java.awt.BorderLayout;
import java.awt.Container;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import nl.tudelft.jpacman.game.Game;
import nl.tudelft.jpacman.ui.ScorePanel.ScoreFormatter; public class PacManUI extends JFrame {

	/**
	 * Default serialisation UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The desired frame rate interval for the graphics in milliseconds, 40
	 * being 25 fps.
	 */
	private static final int FRAME_INTERVAL = 40;

	/**
	 * The panel displaying the player scores.
	 */
	private final ScorePanel scorePanel;

	/**
	 * The panel displaying the game.
	 */
	private final BoardPanel boardPanel;

	/**
	 * Creates a new UI for a JPac-Man game.
	 * 
	 * @param game
	 *            The game to play.
	 * @param buttons
	 *            The map of caption-to-action entries that will appear as
	 *            buttons on the interface.
	 * @param keyMappings
	 *            The map of keyCode-to-action entries that will be added as key
	 *            listeners to the interface.
	 * @param sf
	 *            The formatter used to display the current score. 
	 */
	public PacManUI(final Game game, final Map<String, Action> buttons, final Map<Integer, Action> keyMappings, ScoreFormatter sf) {super("JPac-Man");Collect.Hit("PacManUI.java","PacManUI(final Game game, final Map<String, Action> buttons, final Map<Integer, Action> keyMappings, ScoreFormatter sf)");assert game != null; Collect.Hit("PacManUI.java","PacManUI(final Game game, final Map<String, Action> buttons, final Map<Integer, Action> keyMappings, ScoreFormatter sf)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/PacManUI.java|(1913,20,<69,2>,<69,22>)"); assert buttons != null; Collect.Hit("PacManUI.java","PacManUI(final Game game, final Map<String, Action> buttons, final Map<Integer, Action> keyMappings, ScoreFormatter sf)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/PacManUI.java|(1937,23,<70,2>,<70,25>)"); assert keyMappings != null; Collect.Hit("PacManUI.java","PacManUI(final Game game, final Map<String, Action> buttons, final Map<Integer, Action> keyMappings, ScoreFormatter sf)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/PacManUI.java|(1964,27,<71,2>,<71,29>)"); setDefaultCloseOperation(EXIT_ON_CLOSE); Collect.Hit("PacManUI.java","PacManUI(final Game game, final Map<String, Action> buttons, final Map<Integer, Action> keyMappings, ScoreFormatter sf)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/PacManUI.java|(1999,40,<73,2>,<73,42>)"); PacKeyListener keys = new PacKeyListener(keyMappings); Collect.Hit("PacManUI.java","PacManUI(final Game game, final Map<String, Action> buttons, final Map<Integer, Action> keyMappings, ScoreFormatter sf)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/PacManUI.java|(2045,54,<75,2>,<75,56>)"); addKeyListener(keys); Collect.Hit("PacManUI.java","PacManUI(final Game game, final Map<String, Action> buttons, final Map<Integer, Action> keyMappings, ScoreFormatter sf)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/PacManUI.java|(2103,21,<76,2>,<76,23>)"); JPanel buttonPanel = new ButtonPanel(buttons, this); Collect.Hit("PacManUI.java","PacManUI(final Game game, final Map<String, Action> buttons, final Map<Integer, Action> keyMappings, ScoreFormatter sf)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/PacManUI.java|(2130,52,<78,2>,<78,54>)"); scorePanel = new ScorePanel(game.getPlayers()); Collect.Hit("PacManUI.java","PacManUI(final Game game, final Map<String, Action> buttons, final Map<Integer, Action> keyMappings, ScoreFormatter sf)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/PacManUI.java|(2188,47,<80,2>,<80,49>)"); if (sf != null) {
			scorePanel.setScoreFormatter(sf);
		} Collect.Hit("PacManUI.java","PacManUI(final Game game, final Map<String, Action> buttons, final Map<Integer, Action> keyMappings, ScoreFormatter sf)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/PacManUI.java|(2239,60,<81,2>,<83,3>)"); boardPanel = new BoardPanel(game); Collect.Hit("PacManUI.java","PacManUI(final Game game, final Map<String, Action> buttons, final Map<Integer, Action> keyMappings, ScoreFormatter sf)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/PacManUI.java|(2307,34,<85,2>,<85,36>)"); Container contentPanel = getContentPane(); Collect.Hit("PacManUI.java","PacManUI(final Game game, final Map<String, Action> buttons, final Map<Integer, Action> keyMappings, ScoreFormatter sf)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/PacManUI.java|(2349,42,<87,2>,<87,44>)"); contentPanel.setLayout(new BorderLayout()); Collect.Hit("PacManUI.java","PacManUI(final Game game, final Map<String, Action> buttons, final Map<Integer, Action> keyMappings, ScoreFormatter sf)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/PacManUI.java|(2395,43,<88,2>,<88,45>)"); contentPanel.add(buttonPanel, BorderLayout.SOUTH); Collect.Hit("PacManUI.java","PacManUI(final Game game, final Map<String, Action> buttons, final Map<Integer, Action> keyMappings, ScoreFormatter sf)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/PacManUI.java|(2442,50,<89,2>,<89,52>)"); contentPanel.add(scorePanel, BorderLayout.NORTH); Collect.Hit("PacManUI.java","PacManUI(final Game game, final Map<String, Action> buttons, final Map<Integer, Action> keyMappings, ScoreFormatter sf)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/PacManUI.java|(2496,49,<90,2>,<90,51>)"); contentPanel.add(boardPanel, BorderLayout.CENTER); Collect.Hit("PacManUI.java","PacManUI(final Game game, final Map<String, Action> buttons, final Map<Integer, Action> keyMappings, ScoreFormatter sf)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/PacManUI.java|(2549,50,<91,2>,<91,52>)"); pack(); Collect.Hit("PacManUI.java","PacManUI(final Game game, final Map<String, Action> buttons, final Map<Integer, Action> keyMappings, ScoreFormatter sf)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/PacManUI.java|(2605,7,<93,2>,<93,9>)");}

	/**
	 * Starts the "engine", the thread that redraws the interface at set
	 * intervals.
	 */
	public void start() {Collect.Hit("PacManUI.java","start()");setVisible(true); Collect.Hit("PacManUI.java","start()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/PacManUI.java|(2745,17,<101,2>,<101,19>)"); ScheduledExecutorService service = Executors
				.newSingleThreadScheduledExecutor(); Collect.Hit("PacManUI.java","start()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/PacManUI.java|(2768,86,<103,2>,<104,40>)"); service.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {Collect.Hit("PacManUI.java","run()");nextFrame(); Collect.Hit("PacManUI.java","run()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/PacManUI.java|(2950,12,<110,4>,<110,16>)");}
		}, 0, FRAME_INTERVAL, TimeUnit.MILLISECONDS); Collect.Hit("PacManUI.java","start()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/PacManUI.java|(2860,157,<106,2>,<112,47>)");}

	/**
	 * Draws the next frame, i.e. refreshes the scores and game.
	 */
	private void nextFrame() {Collect.Hit("PacManUI.java","nextFrame()");boardPanel.repaint(); Collect.Hit("PacManUI.java","nextFrame()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/PacManUI.java|(3133,21,<120,2>,<120,23>)"); scorePanel.refresh(); Collect.Hit("PacManUI.java","nextFrame()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/PacManUI.java|(3158,21,<121,2>,<121,23>)");}
}
