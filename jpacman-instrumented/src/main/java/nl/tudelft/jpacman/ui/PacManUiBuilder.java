package nl.tudelft.jpacman.ui;import coverageApi.Collect;import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import nl.tudelft.jpacman.game.Game;
import nl.tudelft.jpacman.ui.ScorePanel.ScoreFormatter; public class PacManUiBuilder {

	/**
	 * Caption for the default stop button.
	 */
	private static final String STOP_CAPTION = "Stop";

	/**
	 * Caption for the default start button.
	 */
	private static final String START_CAPTION = "Start";

	/**
	 * Map of buttons and their actions.
	 */
	private final Map<String, Action> buttons;

	/**
	 * Map of key events and their actions.
	 */
	private final Map<Integer, Action> keyMappings;

	/**
	 * <code>true</code> iff this UI has the default buttons.
	 */
	private boolean defaultButtons;
	
	/**
	 * Way to format the score.
	 */
	private ScoreFormatter scoreFormatter = null;

	/**
	 * Creates a new Pac-Man UI builder without any mapped keys or buttons.
	 */
	public PacManUiBuilder() {Collect.Hit("PacManUiBuilder.java","PacManUiBuilder()");this.defaultButtons = false; Collect.Hit("PacManUiBuilder.java","PacManUiBuilder()", "1068"); this.buttons = new LinkedHashMap<>(); Collect.Hit("PacManUiBuilder.java","PacManUiBuilder()", "1100"); this.keyMappings = new HashMap<>(); Collect.Hit("PacManUiBuilder.java","PacManUiBuilder()", "1141");}

	/**
	 * Creates a new Pac-Man UI with the set keys and buttons.
	 * 
	 * @param game
	 *            The game to build the UI for.
	 * @return A new Pac-Man UI with the set keys and buttons.
	 */
	public PacManUI build(final Game game) {Collect.Hit("PacManUiBuilder.java","build(final Game game)");assert game != null; Collect.Hit("PacManUiBuilder.java","build(final Game game)", "1432"); if (defaultButtons) {
			addStartButton(game);
			addStopButton(game);
		} Collect.Hit("PacManUiBuilder.java","build(final Game game)", "1458"); Collect.Hit("PacManUiBuilder.java","build(final Game game)", "1539");return new PacManUI(game, buttons, keyMappings, scoreFormatter) ; }

	/**
	 * Adds a button with the caption {@value #STOP_CAPTION} that stops the
	 * game.
	 * 
	 * @param game
	 *            The game to stop.
	 */
	private void addStopButton(final Game game) {Collect.Hit("PacManUiBuilder.java","addStopButton(final Game game)");assert game != null; Collect.Hit("PacManUiBuilder.java","addStopButton(final Game game)", "1815"); buttons.put(STOP_CAPTION, new Action() {
			@Override
			public void doAction() {Collect.Hit("PacManUiBuilder.java","doAction()");game.stop(); Collect.Hit("PacManUiBuilder.java","doAction()", "1930");}
		}); Collect.Hit("PacManUiBuilder.java","addStopButton(final Game game)", "1841");}

	/**
	 * Adds a button with the caption {@value #START_CAPTION} that starts the
	 * game.
	 * 
	 * @param game
	 *            The game to start.
	 */
	private void addStartButton(final Game game) {Collect.Hit("PacManUiBuilder.java","addStartButton(final Game game)");assert game != null; Collect.Hit("PacManUiBuilder.java","addStartButton(final Game game)", "2171"); buttons.put(START_CAPTION, new Action() {
			@Override
			public void doAction() {Collect.Hit("PacManUiBuilder.java","doAction()");game.start(); Collect.Hit("PacManUiBuilder.java","doAction()", "2287");}
		}); Collect.Hit("PacManUiBuilder.java","addStartButton(final Game game)", "2197");}

	/**
	 * Adds a key listener to the UI.
	 * 
	 * @param keyCode
	 *            The key code of the key as used by {@link java.awt.event.KeyEvent}.
	 * @param action
	 *            The action to perform when the key is pressed.
	 * @return The builder.
	 */
	public PacManUiBuilder addKey(Integer keyCode, Action action) {Collect.Hit("PacManUiBuilder.java","addKey(Integer keyCode, Action action)");assert keyCode != null; Collect.Hit("PacManUiBuilder.java","addKey(Integer keyCode, Action action)", "2655"); assert action != null; Collect.Hit("PacManUiBuilder.java","addKey(Integer keyCode, Action action)", "2682"); keyMappings.put(keyCode, action); Collect.Hit("PacManUiBuilder.java","addKey(Integer keyCode, Action action)", "2710"); Collect.Hit("PacManUiBuilder.java","addKey(Integer keyCode, Action action)", "2747");return this ; }

	/**
	 * Adds a button to the UI.
	 * 
	 * @param caption
	 *            The caption of the button.
	 * @param action
	 *            The action to execute when the button is clicked.
	 * @return The builder.
	 */
	public PacManUiBuilder addButton(String caption, Action action) {Collect.Hit("PacManUiBuilder.java","addButton(String caption, Action action)");assert caption != null; Collect.Hit("PacManUiBuilder.java","addButton(String caption, Action action)", "3059"); assert !caption.isEmpty(); Collect.Hit("PacManUiBuilder.java","addButton(String caption, Action action)", "3086"); assert action != null; Collect.Hit("PacManUiBuilder.java","addButton(String caption, Action action)", "3116"); buttons.put(caption, action); Collect.Hit("PacManUiBuilder.java","addButton(String caption, Action action)", "3144"); Collect.Hit("PacManUiBuilder.java","addButton(String caption, Action action)", "3177");return this ; }

	/**
	 * Adds a start and stop button to the UI. The actual actions for these
	 * buttons will be added upon building the UI.
	 * 
	 * @return The builder.
	 */
	public PacManUiBuilder withDefaultButtons() {Collect.Hit("PacManUiBuilder.java","withDefaultButtons()");defaultButtons = true; Collect.Hit("PacManUiBuilder.java","withDefaultButtons()", "3414"); buttons.put(START_CAPTION, null); Collect.Hit("PacManUiBuilder.java","withDefaultButtons()", "3440"); buttons.put(STOP_CAPTION, null); Collect.Hit("PacManUiBuilder.java","withDefaultButtons()", "3477"); Collect.Hit("PacManUiBuilder.java","withDefaultButtons()", "3513");return this ; }
	
	/**
	 * Provide formatter for the score.
	 * 
	 * @param sf
	 *         The score formatter to be used.
	 * 
	 * @return The builder.
	 */
	public PacManUiBuilder withScoreFormatter(ScoreFormatter sf) {Collect.Hit("PacManUiBuilder.java","withScoreFormatter(ScoreFormatter sf)");scoreFormatter = sf; Collect.Hit("PacManUiBuilder.java","withScoreFormatter(ScoreFormatter sf)", "3749"); Collect.Hit("PacManUiBuilder.java","withScoreFormatter(ScoreFormatter sf)", "3773");return this ; }
}
