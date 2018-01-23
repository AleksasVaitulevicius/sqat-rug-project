package nl.tudelft.jpacman.game;import coverageApi.Collect;import java.util.List;

import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.Level.LevelObserver;
import nl.tudelft.jpacman.level.Player; public abstract class Game implements LevelObserver {

	/**
	 * <code>true</code> if the game is in progress.
	 */
	private boolean inProgress;

	/**
	 * Object that locks the start and stop methods.
	 */
	private final Object progressLock = new Object();

	/**
	 * Creates a new game.
	 */
	protected Game() {Collect.Hit("Game.java","Game()");inProgress = false; Collect.Hit("Game.java","Game()", "660");}

	/**
	 * Starts or resumes the game.
	 */
	public void start() {Collect.Hit("Game.java","start()");synchronized (progressLock) {
			if (isInProgress()) {
				return;
			}
			if (getLevel().isAnyPlayerAlive()
					&& getLevel().remainingPellets() > 0) {
				inProgress = true;
				getLevel().addObserver(this);
				getLevel().start();
			}
		} Collect.Hit("Game.java","start()", "758");}

	/**
	 * Pauses the game.
	 */
	public void stop() {Collect.Hit("Game.java","stop()");synchronized (progressLock) {
			if (!isInProgress()) {
				return;
			}
			inProgress = false;
			getLevel().stop();
		} Collect.Hit("Game.java","stop()", "1078");}

	/**
	 * @return <code>true</code> iff the game is started and in progress.
	 */
	public boolean isInProgress() {Collect.Hit("Game.java","isInProgress()"); Collect.Hit("Game.java","isInProgress()", "1333");return inProgress ; }

	/**
	 * @return An immutable list of the participants of this game.
	 */
	public abstract List<Player> getPlayers();

	/**
	 * @return The level currently being played.
	 */
	public abstract Level getLevel();

	/**
	 * Moves the specified player one square in the given direction.
	 * 
	 * @param player
	 *            The player to move.
	 * @param direction
	 *            The direction to move in.
	 */
	public void move(Player player, Direction direction) {Collect.Hit("Game.java","move(Player player, Direction direction)");if (isInProgress()) {
			// execute player move.
			getLevel().move(player, direction);
		} Collect.Hit("Game.java","move(Player player, Direction direction)", "1843");}
	
	@Override
	public void levelWon() {Collect.Hit("Game.java","levelWon()");stop(); Collect.Hit("Game.java","levelWon()", "1987");}
	
	@Override
	public void levelLost() {Collect.Hit("Game.java","levelLost()");stop(); Collect.Hit("Game.java","levelLost()", "2045");}
}
