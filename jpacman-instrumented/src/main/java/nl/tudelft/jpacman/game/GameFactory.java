package nl.tudelft.jpacman.game;import coverageApi.Collect;import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.PlayerFactory; public class GameFactory {

	/**
	 * The factory providing the player objects.
	 */
	private final PlayerFactory playerFact;

	/**
	 * Creates a new game factory.
	 * 
	 * @param playerFactory
	 *            The factory providing the player objects.
	 */
	public GameFactory(PlayerFactory playerFactory) {Collect.Hit("GameFactory.java","GameFactory(PlayerFactory playerFactory)");this.playerFact = playerFactory; Collect.Hit("GameFactory.java","GameFactory(PlayerFactory playerFactory)", "530");}

	/**
	 * Creates a game for a single level with one player.
	 * 
	 * @param level
	 *            The level to create a game for.
	 * @return A new single player game.
	 */
	public Game createSinglePlayerGame(Level level) {Collect.Hit("GameFactory.java","createSinglePlayerGame(Level level)"); Collect.Hit("GameFactory.java","createSinglePlayerGame(Level level)", "803");return new SinglePlayerGame(playerFact.createPacMan(), level) ; }

	/**
	 * Returns the player factory associated with this game factory.
	 * @return the player factory associated with this game factory.
	 */
	protected PlayerFactory getPlayerFactory() {Collect.Hit("GameFactory.java","getPlayerFactory()"); Collect.Hit("GameFactory.java","getPlayerFactory()", "1068");return playerFact ; }
}
