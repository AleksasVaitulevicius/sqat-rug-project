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
	public PacManUiBuilder() {Collect.Hit("PacManUiBuilder.java","PacManUiBuilder()");this.defaultButtons = false; Collect.Hit("PacManUiBuilder.java","PacManUiBuilder()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/PacManUiBuilder.java|(1068,28,<51,2>,<51,30>)"); this.buttons = new LinkedHashMap<>(); Collect.Hit("PacManUiBuilder.java","PacManUiBuilder()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/PacManUiBuilder.java|(1100,37,<52,2>,<52,39>)"); this.keyMappings = new HashMap<>(); Collect.Hit("PacManUiBuilder.java","PacManUiBuilder()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/PacManUiBuilder.java|(1141,35,<53,2>,<53,37>)");}

	/**
	 * Creates a new Pac-Man UI with the set keys and buttons.
	 * 
	 * @param game
	 *            The game to build the UI for.
	 * @return A new Pac-Man UI with the set keys and buttons.
	 */
	public PacManUI build(final Game game) {Collect.Hit("PacManUiBuilder.java","build(final Game game)");assert game != null; Collect.Hit("PacManUiBuilder.java","build(final Game game)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/PacManUiBuilder.java|(1432,20,<64,2>,<64,22>)"); if (defaultButtons) {
			addStartButton(game);
			addStopButton(game);
		} Collect.Hit("PacManUiBuilder.java","build(final Game game)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/PacManUiBuilder.java|(1458,77,<66,2>,<69,3>)"); return new PacManUI(game, buttons, keyMappings, scoreFormatter); Collect.Hit("PacManUiBuilder.java","build(final Game game)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/PacManUiBuilder.java|(1539,64,<70,2>,<70,66>)");}

	/**
	 * Adds a button with the caption {@value #STOP_CAPTION} that stops the
	 * game.
	 * 
	 * @param game
	 *            The game to stop.
	 */
	private void addStopButton(final Game game) {Collect.Hit("PacManUiBuilder.java","addStopButton(final Game game)");assert game != null; Collect.Hit("PacManUiBuilder.java","addStopButton(final Game game)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/PacManUiBuilder.java|(1815,20,<81,2>,<81,22>)"); buttons.put(STOP_CAPTION, new Action() {
			@Override
			public void doAction() {Collect.Hit("PacManUiBuilder.java","doAction()");game.stop(); Collect.Hit("PacManUiBuilder.java","doAction()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/PacManUiBuilder.java|(1930,12,<86,4>,<86,16>)");}
		}); Collect.Hit("PacManUiBuilder.java","addStopButton(final Game game)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/PacManUiBuilder.java|(1841,114,<83,2>,<88,5>)");}

	/**
	 * Adds a button with the caption {@value #START_CAPTION} that starts the
	 * game.
	 * 
	 * @param game
	 *            The game to start.
	 */
	private void addStartButton(final Game game) {Collect.Hit("PacManUiBuilder.java","addStartButton(final Game game)");assert game != null; Collect.Hit("PacManUiBuilder.java","addStartButton(final Game game)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/PacManUiBuilder.java|(2171,20,<99,2>,<99,22>)"); buttons.put(START_CAPTION, new Action() {
			@Override
			public void doAction() {Collect.Hit("PacManUiBuilder.java","doAction()");game.start(); Collect.Hit("PacManUiBuilder.java","doAction()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/PacManUiBuilder.java|(2287,13,<104,4>,<104,17>)");}
		}); Collect.Hit("PacManUiBuilder.java","addStartButton(final Game game)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/PacManUiBuilder.java|(2197,116,<101,2>,<106,5>)");}

	/**
	 * Adds a key listener to the UI.
	 * 
	 * @param keyCode
	 *            The key code of the key as used by {@link java.awt.event.KeyEvent}.
	 * @param action
	 *            The action to perform when the key is pressed.
	 * @return The builder.
	 */
	public PacManUiBuilder addKey(Integer keyCode, Action action) {Collect.Hit("PacManUiBuilder.java","addKey(Integer keyCode, Action action)");assert keyCode != null; Collect.Hit("PacManUiBuilder.java","addKey(Integer keyCode, Action action)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/PacManUiBuilder.java|(2655,23,<119,2>,<119,25>)"); assert action != null; Collect.Hit("PacManUiBuilder.java","addKey(Integer keyCode, Action action)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/PacManUiBuilder.java|(2682,22,<120,2>,<120,24>)"); keyMappings.put(keyCode, action); Collect.Hit("PacManUiBuilder.java","addKey(Integer keyCode, Action action)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/PacManUiBuilder.java|(2710,33,<122,2>,<122,35>)"); return this; Collect.Hit("PacManUiBuilder.java","addKey(Integer keyCode, Action action)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/PacManUiBuilder.java|(2747,12,<123,2>,<123,14>)");}

	/**
	 * Adds a button to the UI.
	 * 
	 * @param caption
	 *            The caption of the button.
	 * @param action
	 *            The action to execute when the button is clicked.
	 * @return The builder.
	 */
	public PacManUiBuilder addButton(String caption, Action action) {Collect.Hit("PacManUiBuilder.java","addButton(String caption, Action action)");assert caption != null; Collect.Hit("PacManUiBuilder.java","addButton(String caption, Action action)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/PacManUiBuilder.java|(3059,23,<136,2>,<136,25>)"); assert !caption.isEmpty(); Collect.Hit("PacManUiBuilder.java","addButton(String caption, Action action)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/PacManUiBuilder.java|(3086,26,<137,2>,<137,28>)"); assert action != null; Collect.Hit("PacManUiBuilder.java","addButton(String caption, Action action)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/PacManUiBuilder.java|(3116,22,<138,2>,<138,24>)"); buttons.put(caption, action); Collect.Hit("PacManUiBuilder.java","addButton(String caption, Action action)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/PacManUiBuilder.java|(3144,29,<140,2>,<140,31>)"); return this; Collect.Hit("PacManUiBuilder.java","addButton(String caption, Action action)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/PacManUiBuilder.java|(3177,12,<141,2>,<141,14>)");}

	/**
	 * Adds a start and stop button to the UI. The actual actions for these
	 * buttons will be added upon building the UI.
	 * 
	 * @return The builder.
	 */
	public PacManUiBuilder withDefaultButtons() {Collect.Hit("PacManUiBuilder.java","withDefaultButtons()");defaultButtons = true; Collect.Hit("PacManUiBuilder.java","withDefaultButtons()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/PacManUiBuilder.java|(3414,22,<151,2>,<151,24>)"); buttons.put(START_CAPTION, null); Collect.Hit("PacManUiBuilder.java","withDefaultButtons()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/PacManUiBuilder.java|(3440,33,<152,2>,<152,35>)"); buttons.put(STOP_CAPTION, null); Collect.Hit("PacManUiBuilder.java","withDefaultButtons()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/PacManUiBuilder.java|(3477,32,<153,2>,<153,34>)"); return this; Collect.Hit("PacManUiBuilder.java","withDefaultButtons()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/PacManUiBuilder.java|(3513,12,<154,2>,<154,14>)");}
	
	/**
	 * Provide formatter for the score.
	 * 
	 * @param sf
	 *         The score formatter to be used.
	 * 
	 * @return The builder.
	 */
	public PacManUiBuilder withScoreFormatter(ScoreFormatter sf) {Collect.Hit("PacManUiBuilder.java","withScoreFormatter(ScoreFormatter sf)");scoreFormatter = sf; Collect.Hit("PacManUiBuilder.java","withScoreFormatter(ScoreFormatter sf)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/PacManUiBuilder.java|(3749,20,<166,2>,<166,22>)"); return this; Collect.Hit("PacManUiBuilder.java","withScoreFormatter(ScoreFormatter sf)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/PacManUiBuilder.java|(3773,12,<167,2>,<167,14>)");}
}
