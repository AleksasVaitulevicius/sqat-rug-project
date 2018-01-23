package nl.tudelft.jpacman.game;import coverageApi.Collect;import java.util.List;

import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.Player;

import com.google.common.collect.ImmutableList; public class SinglePlayerGame extends Game{

	/**
	 * The player of this game.
	 */
	private final Player player;

	/**
	 * The level of this game.
	 */
	private final Level level;

	/**
	 * Create a new single player game for the provided level and player.
	 * 
	 * @param p
	 *            The player.
	 * @param l
	 *            The level.
	 */
	protected SinglePlayerGame(Player p, Level l) {Collect.Hit("SinglePlayerGame.java","SinglePlayerGame(Player p, Level l)");assert p != null; Collect.Hit("SinglePlayerGame.java","SinglePlayerGame(Player p, Level l)", "704"); assert l != null; Collect.Hit("SinglePlayerGame.java","SinglePlayerGame(Player p, Level l)", "725"); this.player = p; Collect.Hit("SinglePlayerGame.java","SinglePlayerGame(Player p, Level l)", "748"); this.level = l; Collect.Hit("SinglePlayerGame.java","SinglePlayerGame(Player p, Level l)", "768"); level.registerPlayer(p); Collect.Hit("SinglePlayerGame.java","SinglePlayerGame(Player p, Level l)", "787");}

	@Override
	public List<Player> getPlayers() {Collect.Hit("SinglePlayerGame.java","getPlayers()"); Collect.Hit("SinglePlayerGame.java","getPlayers()", "870");return ImmutableList.of(player) ; }

	@Override
	public Level getLevel() {Collect.Hit("SinglePlayerGame.java","getLevel()"); Collect.Hit("SinglePlayerGame.java","getLevel()", "952");return level ; }
}
