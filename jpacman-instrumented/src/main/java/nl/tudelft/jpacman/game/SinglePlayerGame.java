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
	protected SinglePlayerGame(Player p, Level l) {Collect.Hit("SinglePlayerGame.java","SinglePlayerGame(Player p, Level l)");assert p != null; Collect.Hit("SinglePlayerGame.java","SinglePlayerGame(Player p, Level l)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/game/SinglePlayerGame.java|(704,17,<36,2>,<36,19>)"); assert l != null; Collect.Hit("SinglePlayerGame.java","SinglePlayerGame(Player p, Level l)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/game/SinglePlayerGame.java|(725,17,<37,2>,<37,19>)"); this.player = p; Collect.Hit("SinglePlayerGame.java","SinglePlayerGame(Player p, Level l)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/game/SinglePlayerGame.java|(748,16,<39,2>,<39,18>)"); this.level = l; Collect.Hit("SinglePlayerGame.java","SinglePlayerGame(Player p, Level l)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/game/SinglePlayerGame.java|(768,15,<40,2>,<40,17>)"); level.registerPlayer(p); Collect.Hit("SinglePlayerGame.java","SinglePlayerGame(Player p, Level l)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/game/SinglePlayerGame.java|(787,24,<41,2>,<41,26>)");}

	@Override
	public List<Player> getPlayers() {Collect.Hit("SinglePlayerGame.java","getPlayers()");return ImmutableList.of(player); Collect.Hit("SinglePlayerGame.java","getPlayers()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/game/SinglePlayerGame.java|(870,32,<46,2>,<46,34>)");}

	@Override
	public Level getLevel() {Collect.Hit("SinglePlayerGame.java","getLevel()");return level; Collect.Hit("SinglePlayerGame.java","getLevel()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/game/SinglePlayerGame.java|(952,13,<51,2>,<51,15>)");}
}
