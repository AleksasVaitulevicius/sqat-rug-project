package nl.tudelft.jpacman.ui;import coverageApi.Collect;import java.awt.GridLayout;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

import nl.tudelft.jpacman.level.Player; public class ScorePanel extends JPanel {

	/**
	 * Default serialisation ID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The map of players and the labels their scores are on.
	 */
	private final Map<Player, JLabel> scoreLabels;
	
	/**
	 * The default way in which the score is shown.
	 */
	public static final ScoreFormatter DEFAULT_SCORE_FORMATTER =
			// this lambda breaks cobertura 2.7 ...
			// player) -> String.format("Score: %3d", player.getScore());
			new ScoreFormatter() {
				public String format(Player p) {Collect.Hit("ScorePanel.java","format(Player p)");return String.format("Score: %3d", p.getScore()); Collect.Hit("ScorePanel.java","format(Player p)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/ScorePanel.java|(991,49,<40,5>,<40,54>)");}
			};

	/**
	 * The way to format the score information.
	 */
	private ScoreFormatter scoreFormatter = DEFAULT_SCORE_FORMATTER;

	/**
	 * Creates a new score panel with a column for each player.
	 * 
	 * @param players
	 *            The players to display the scores of.
	 */
	public ScorePanel(List<Player> players) {super();Collect.Hit("ScorePanel.java","ScorePanel(List<Player> players)");assert players != null; Collect.Hit("ScorePanel.java","ScorePanel(List<Player> players)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/ScorePanel.java|(1397,23,<57,2>,<57,25>)"); setLayout(new GridLayout(2, players.size())); Collect.Hit("ScorePanel.java","ScorePanel(List<Player> players)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/ScorePanel.java|(1426,45,<59,2>,<59,47>)"); for (int i = 1; i <= players.size(); i++) {
			add(new JLabel("Player " + i, JLabel.CENTER));
		} Collect.Hit("ScorePanel.java","ScorePanel(List<Player> players)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/ScorePanel.java|(1477,99,<61,2>,<63,3>)"); scoreLabels = new LinkedHashMap<>(); Collect.Hit("ScorePanel.java","ScorePanel(List<Player> players)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/ScorePanel.java|(1580,36,<64,2>,<64,38>)"); for (Player p : players) {
			JLabel scoreLabel = new JLabel("0", JLabel.CENTER);
			scoreLabels.put(p, scoreLabel);
			add(scoreLabel);
		} Collect.Hit("ScorePanel.java","ScorePanel(List<Player> players)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/ScorePanel.java|(1620,144,<65,2>,<69,3>)");}

	/**
	 * Refreshes the scores of the players.
	 */
	protected void refresh() {Collect.Hit("ScorePanel.java","refresh()");for (Map.Entry<Player, JLabel> entry : scoreLabels.entrySet()) {
			Player p = entry.getKey();
			String score = "";
			if (!p.isAlive()) {
				score = "You died. ";
			}
			score += scoreFormatter.format(p);
			entry.getValue().setText(score);
		} Collect.Hit("ScorePanel.java","refresh()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/ScorePanel.java|(1857,256,<76,2>,<84,3>)");}
	
	/**
	 * Provide means to format the score for a given player.
	 */
	public interface ScoreFormatter {
		
		/**
		 * Format the score of a given player.
		 * @param p The player and its score
		 * @return Formatted score.
		 */
		String format(Player p);
	}
	
	/**
	 * Let the score panel use a dedicated score formatter.
	 * @param sf Score formatter to be used.
	 */
	public void setScoreFormatter(ScoreFormatter sf) {Collect.Hit("ScorePanel.java","setScoreFormatter(ScoreFormatter sf)");assert sf != null; Collect.Hit("ScorePanel.java","setScoreFormatter(ScoreFormatter sf)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/ScorePanel.java|(2563,18,<105,2>,<105,20>)"); scoreFormatter = sf; Collect.Hit("ScorePanel.java","setScoreFormatter(ScoreFormatter sf)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/ui/ScorePanel.java|(2585,20,<106,2>,<106,22>)");}
}
