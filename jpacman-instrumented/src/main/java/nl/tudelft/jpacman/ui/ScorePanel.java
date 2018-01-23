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
				public String format(Player p) {Collect.Hit("ScorePanel.java","format(Player p)"); Collect.Hit("ScorePanel.java","format(Player p)", "991");return String.format("Score: %3d", p.getScore()) ; }
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
	public ScorePanel(List<Player> players) {super();Collect.Hit("ScorePanel.java","ScorePanel(List<Player> players)");assert players != null; Collect.Hit("ScorePanel.java","ScorePanel(List<Player> players)", "1397"); setLayout(new GridLayout(2, players.size())); Collect.Hit("ScorePanel.java","ScorePanel(List<Player> players)", "1426"); for (int i = 1; i <= players.size(); i++) {
			add(new JLabel("Player " + i, JLabel.CENTER));
		} Collect.Hit("ScorePanel.java","ScorePanel(List<Player> players)", "1477"); scoreLabels = new LinkedHashMap<>(); Collect.Hit("ScorePanel.java","ScorePanel(List<Player> players)", "1580"); for (Player p : players) {
			JLabel scoreLabel = new JLabel("0", JLabel.CENTER);
			scoreLabels.put(p, scoreLabel);
			add(scoreLabel);
		} Collect.Hit("ScorePanel.java","ScorePanel(List<Player> players)", "1620");}

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
		} Collect.Hit("ScorePanel.java","refresh()", "1857");}
	
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
	public void setScoreFormatter(ScoreFormatter sf) {Collect.Hit("ScorePanel.java","setScoreFormatter(ScoreFormatter sf)");assert sf != null; Collect.Hit("ScorePanel.java","setScoreFormatter(ScoreFormatter sf)", "2563"); scoreFormatter = sf; Collect.Hit("ScorePanel.java","setScoreFormatter(ScoreFormatter sf)", "2585");}
}
