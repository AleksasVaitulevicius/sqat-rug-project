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
	public PacManUI(final Game game, final Map<String, Action> buttons, final Map<Integer, Action> keyMappings, ScoreFormatter sf) {super("JPac-Man");Collect.Hit("PacManUI.java","PacManUI(final Game game, final Map<String, Action> buttons, final Map<Integer, Action> keyMappings, ScoreFormatter sf)");assert game != null; Collect.Hit("PacManUI.java","PacManUI(final Game game, final Map<String, Action> buttons, final Map<Integer, Action> keyMappings, ScoreFormatter sf)", "1913"); assert buttons != null; Collect.Hit("PacManUI.java","PacManUI(final Game game, final Map<String, Action> buttons, final Map<Integer, Action> keyMappings, ScoreFormatter sf)", "1937"); assert keyMappings != null; Collect.Hit("PacManUI.java","PacManUI(final Game game, final Map<String, Action> buttons, final Map<Integer, Action> keyMappings, ScoreFormatter sf)", "1964"); setDefaultCloseOperation(EXIT_ON_CLOSE); Collect.Hit("PacManUI.java","PacManUI(final Game game, final Map<String, Action> buttons, final Map<Integer, Action> keyMappings, ScoreFormatter sf)", "1999"); PacKeyListener keys = new PacKeyListener(keyMappings); Collect.Hit("PacManUI.java","PacManUI(final Game game, final Map<String, Action> buttons, final Map<Integer, Action> keyMappings, ScoreFormatter sf)", "2045"); addKeyListener(keys); Collect.Hit("PacManUI.java","PacManUI(final Game game, final Map<String, Action> buttons, final Map<Integer, Action> keyMappings, ScoreFormatter sf)", "2103"); JPanel buttonPanel = new ButtonPanel(buttons, this); Collect.Hit("PacManUI.java","PacManUI(final Game game, final Map<String, Action> buttons, final Map<Integer, Action> keyMappings, ScoreFormatter sf)", "2130"); scorePanel = new ScorePanel(game.getPlayers()); Collect.Hit("PacManUI.java","PacManUI(final Game game, final Map<String, Action> buttons, final Map<Integer, Action> keyMappings, ScoreFormatter sf)", "2188"); if (sf != null) {
			scorePanel.setScoreFormatter(sf);
		} Collect.Hit("PacManUI.java","PacManUI(final Game game, final Map<String, Action> buttons, final Map<Integer, Action> keyMappings, ScoreFormatter sf)", "2239"); boardPanel = new BoardPanel(game); Collect.Hit("PacManUI.java","PacManUI(final Game game, final Map<String, Action> buttons, final Map<Integer, Action> keyMappings, ScoreFormatter sf)", "2307"); Container contentPanel = getContentPane(); Collect.Hit("PacManUI.java","PacManUI(final Game game, final Map<String, Action> buttons, final Map<Integer, Action> keyMappings, ScoreFormatter sf)", "2349"); contentPanel.setLayout(new BorderLayout()); Collect.Hit("PacManUI.java","PacManUI(final Game game, final Map<String, Action> buttons, final Map<Integer, Action> keyMappings, ScoreFormatter sf)", "2395"); contentPanel.add(buttonPanel, BorderLayout.SOUTH); Collect.Hit("PacManUI.java","PacManUI(final Game game, final Map<String, Action> buttons, final Map<Integer, Action> keyMappings, ScoreFormatter sf)", "2442"); contentPanel.add(scorePanel, BorderLayout.NORTH); Collect.Hit("PacManUI.java","PacManUI(final Game game, final Map<String, Action> buttons, final Map<Integer, Action> keyMappings, ScoreFormatter sf)", "2496"); contentPanel.add(boardPanel, BorderLayout.CENTER); Collect.Hit("PacManUI.java","PacManUI(final Game game, final Map<String, Action> buttons, final Map<Integer, Action> keyMappings, ScoreFormatter sf)", "2549"); pack(); Collect.Hit("PacManUI.java","PacManUI(final Game game, final Map<String, Action> buttons, final Map<Integer, Action> keyMappings, ScoreFormatter sf)", "2605");}

	/**
	 * Starts the "engine", the thread that redraws the interface at set
	 * intervals.
	 */
	public void start() {Collect.Hit("PacManUI.java","start()");setVisible(true); Collect.Hit("PacManUI.java","start()", "2745"); ScheduledExecutorService service = Executors
				.newSingleThreadScheduledExecutor(); Collect.Hit("PacManUI.java","start()", "2768"); service.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {Collect.Hit("PacManUI.java","run()");nextFrame(); Collect.Hit("PacManUI.java","run()", "2950");}
		}, 0, FRAME_INTERVAL, TimeUnit.MILLISECONDS); Collect.Hit("PacManUI.java","start()", "2860");}

	/**
	 * Draws the next frame, i.e. refreshes the scores and game.
	 */
	private void nextFrame() {Collect.Hit("PacManUI.java","nextFrame()");boardPanel.repaint(); Collect.Hit("PacManUI.java","nextFrame()", "3133"); scorePanel.refresh(); Collect.Hit("PacManUI.java","nextFrame()", "3158");}
}
