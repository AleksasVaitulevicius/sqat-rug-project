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
	protected Game() {Collect.Hit("Game.java","Game()");inProgress = false; Collect.Hit("Game.java","Game()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/game/Game.java|(660,19,<31,2>,<31,21>)");}

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
		} Collect.Hit("Game.java","start()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/game/Game.java|(758,253,<38,2>,<48,3>)");}

	/**
	 * Pauses the game.
	 */
	public void stop() {Collect.Hit("Game.java","stop()");synchronized (progressLock) {
			if (!isInProgress()) {
				return;
			}
			inProgress = false;
			getLevel().stop();
		} Collect.Hit("Game.java","stop()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/game/Game.java|(1078,127,<55,2>,<61,3>)");}

	/**
	 * @return <code>true</code> iff the game is started and in progress.
	 */
	public boolean isInProgress() {Collect.Hit("Game.java","isInProgress()");return inProgress; Collect.Hit("Game.java","isInProgress()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/game/Game.java|(1333,18,<68,2>,<68,20>)");}

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
		} Collect.Hit("Game.java","move(Player player, Direction direction)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/game/Game.java|(1843,94,<90,2>,<93,3>)");}
	
	@Override
	public void levelWon() {Collect.Hit("Game.java","levelWon()");stop(); Collect.Hit("Game.java","levelWon()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/game/Game.java|(1987,7,<98,2>,<98,9>)");}
	
	@Override
	public void levelLost() {Collect.Hit("Game.java","levelLost()");stop(); Collect.Hit("Game.java","levelLost()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/game/Game.java|(2045,7,<103,2>,<103,9>)");}
}
