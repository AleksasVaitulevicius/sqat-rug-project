package nl.tudelft.jpacman.level;import coverageApi.Collect;import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import nl.tudelft.jpacman.board.Board;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.board.Unit;
import nl.tudelft.jpacman.npc.NPC; @SuppressWarnings("PMD.TooManyMethods")
public class Level {

	/**
	 * The board of this level.
	 */
	private final Board board;

	/**
	 * The lock that ensures moves are executed sequential.
	 */
	private final Object moveLock = new Object();

	/**
	 * The lock that ensures starting and stopping can't interfere with each
	 * other.
	 */
	private final Object startStopLock = new Object();

	/**
	 * The NPCs of this level and, if they are running, their schedules.
	 */
	private final Map<NPC, ScheduledExecutorService> npcs;

	/**
	 * <code>true</code> iff this level is currently in progress, i.e. players
	 * and NPCs can move.
	 */
	private boolean inProgress;

	/**
	 * The squares from which players can start this game.
	 */
	private final List<Square> startSquares;

	/**
	 * The start current selected starting square.
	 */
	private int startSquareIndex;

	/**
	 * The players on this level.
	 */
	private final List<Player> players;

	/**
	 * The table of possible collisions between units.
	 */
	private final CollisionMap collisions;

	/**
	 * The objects observing this level.
	 */
	private final Set<LevelObserver> observers;

	/**
	 * Creates a new level for the board.
	 * 
	 * @param b
	 *            The board for the level.
	 * @param ghosts
	 *            The ghosts on the board.
	 * @param startPositions
	 *            The squares on which players start on this board.
	 * @param collisionMap
	 *            The collection of collisions that should be handled.
	 */
	public Level(Board b, List<NPC> ghosts, List<Square> startPositions, CollisionMap collisionMap) {Collect.Hit("Level.java","Level(Board b, List<NPC> ghosts, List<Square> startPositions, CollisionMap collisionMap)");assert b != null; Collect.Hit("Level.java","Level(Board b, List<NPC> ghosts, List<Square> startPositions, CollisionMap collisionMap)", "2354"); assert ghosts != null; Collect.Hit("Level.java","Level(Board b, List<NPC> ghosts, List<Square> startPositions, CollisionMap collisionMap)", "2375"); assert startPositions != null; Collect.Hit("Level.java","Level(Board b, List<NPC> ghosts, List<Square> startPositions, CollisionMap collisionMap)", "2401"); this.board = b; Collect.Hit("Level.java","Level(Board b, List<NPC> ghosts, List<Square> startPositions, CollisionMap collisionMap)", "2437"); this.inProgress = false; Collect.Hit("Level.java","Level(Board b, List<NPC> ghosts, List<Square> startPositions, CollisionMap collisionMap)", "2456"); this.npcs = new HashMap<>(); Collect.Hit("Level.java","Level(Board b, List<NPC> ghosts, List<Square> startPositions, CollisionMap collisionMap)", "2484"); for (NPC g : ghosts) {
			npcs.put(g, null);
		} Collect.Hit("Level.java","Level(Board b, List<NPC> ghosts, List<Square> startPositions, CollisionMap collisionMap)", "2516"); this.startSquares = startPositions; Collect.Hit("Level.java","Level(Board b, List<NPC> ghosts, List<Square> startPositions, CollisionMap collisionMap)", "2570"); this.startSquareIndex = 0; Collect.Hit("Level.java","Level(Board b, List<NPC> ghosts, List<Square> startPositions, CollisionMap collisionMap)", "2609"); this.players = new ArrayList<>(); Collect.Hit("Level.java","Level(Board b, List<NPC> ghosts, List<Square> startPositions, CollisionMap collisionMap)", "2639"); this.collisions = collisionMap; Collect.Hit("Level.java","Level(Board b, List<NPC> ghosts, List<Square> startPositions, CollisionMap collisionMap)", "2676"); this.observers = new HashSet<>(); Collect.Hit("Level.java","Level(Board b, List<NPC> ghosts, List<Square> startPositions, CollisionMap collisionMap)", "2711");}

	/**
	 * Adds an observer that will be notified when the level is won or lost.
	 * 
	 * @param observer
	 *            The observer that will be notified.
	 */
	public void addObserver(LevelObserver observer) {Collect.Hit("Level.java","addObserver(LevelObserver observer)");observers.add(observer); Collect.Hit("Level.java","addObserver(LevelObserver observer)", "2972");}

	/**
	 * Removes an observer if it was listed.
	 * 
	 * @param observer
	 *            The observer to be removed.
	 */
	public void removeObserver(LevelObserver observer) {Collect.Hit("Level.java","removeObserver(LevelObserver observer)");observers.remove(observer); Collect.Hit("Level.java","removeObserver(LevelObserver observer)", "3187");}

	/**
	 * Registers a player on this level, assigning him to a starting position. A
	 * player can only be registered once, registering a player again will have
	 * no effect.
	 * 
	 * @param p
	 *            The player to register.
	 */
	public void registerPlayer(Player p) {Collect.Hit("Level.java","registerPlayer(Player p)");assert p != null; Collect.Hit("Level.java","registerPlayer(Player p)", "3510"); assert !startSquares.isEmpty(); Collect.Hit("Level.java","registerPlayer(Player p)", "3531"); if (players.contains(p)) {
			return;
		} Collect.Hit("Level.java","registerPlayer(Player p)", "3568"); players.add(p); Collect.Hit("Level.java","registerPlayer(Player p)", "3615"); Square square = startSquares.get(startSquareIndex); Collect.Hit("Level.java","registerPlayer(Player p)", "3634"); p.occupy(square); Collect.Hit("Level.java","registerPlayer(Player p)", "3689"); startSquareIndex++; Collect.Hit("Level.java","registerPlayer(Player p)", "3710"); startSquareIndex %= startSquares.size(); Collect.Hit("Level.java","registerPlayer(Player p)", "3733");}

	/**
	 * Returns the board of this level.
	 * 
	 * @return The board of this level.
	 */
	public Board getBoard() {Collect.Hit("Level.java","getBoard()"); Collect.Hit("Level.java","getBoard()", "3905");return board ; }

	/**
	 * Moves the unit into the given direction if possible and handles all
	 * collisions.
	 * 
	 * @param unit
	 *            The unit to move.
	 * @param direction
	 *            The direction to move the unit in.
	 */
	public void move(Unit unit, Direction direction) {Collect.Hit("Level.java","move(Unit unit, Direction direction)");assert unit != null; Collect.Hit("Level.java","move(Unit unit, Direction direction)", "4213"); assert direction != null; Collect.Hit("Level.java","move(Unit unit, Direction direction)", "4237"); if (!isInProgress()) {
			return;
		} Collect.Hit("Level.java","move(Unit unit, Direction direction)", "4268"); synchronized (moveLock) {
			unit.setDirection(direction);
			Square location = unit.getSquare();
			Square destination = location.getSquareAt(direction);

			if (destination.isAccessibleTo(unit)) {
				List<Unit> occupants = destination.getOccupants();
				unit.occupy(destination);
				for (Unit occupant : occupants) {
					collisions.collide(unit, occupant);
				}
			}
			updateObservers();
		} Collect.Hit("Level.java","move(Unit unit, Direction direction)", "4313");}

	/**
	 * Starts or resumes this level, allowing movement and (re)starting the
	 * NPCs.
	 */
	public void start() {Collect.Hit("Level.java","start()");synchronized (startStopLock) {
			if (isInProgress()) {
				return;
			}
			startNPCs();
			inProgress = true;
			updateObservers();
		} Collect.Hit("Level.java","start()", "4856");}

	/**
	 * Stops or pauses this level, no longer allowing any movement on the board
	 * and stopping all NPCs.
	 */
	public void stop() {Collect.Hit("Level.java","stop()");synchronized (startStopLock) {
			if (!isInProgress()) {
				return;
			}
			stopNPCs();
			inProgress = false;
		} Collect.Hit("Level.java","stop()", "5150");}

	/**
	 * Starts all NPC movement scheduling.
	 */
	private void startNPCs() {Collect.Hit("Level.java","startNPCs()");for (final NPC npc : npcs.keySet()) {
			ScheduledExecutorService service = Executors
					.newSingleThreadScheduledExecutor();
			service.schedule(new NpcMoveTask(service, npc),
					npc.getInterval() / 2, TimeUnit.MILLISECONDS);
			npcs.put(npc, service);
		} Collect.Hit("Level.java","startNPCs()", "5363");}

	/**
	 * Stops all NPC movement scheduling and interrupts any movements being
	 * executed.
	 */
	private void stopNPCs() {Collect.Hit("Level.java","stopNPCs()");for (Entry<NPC, ScheduledExecutorService> e : npcs.entrySet()) {
			e.getValue().shutdownNow();
		} Collect.Hit("Level.java","stopNPCs()", "5769");}

	/**
	 * Returns whether this level is in progress, i.e. whether moves can be made
	 * on the board.
	 * 
	 * @return <code>true</code> iff this level is in progress.
	 */
	public boolean isInProgress() {Collect.Hit("Level.java","isInProgress()"); Collect.Hit("Level.java","isInProgress()", "6092");return inProgress ; }

	/**
	 * Updates the observers about the state of this level.
	 */
	private void updateObservers() {Collect.Hit("Level.java","updateObservers()");if (!isAnyPlayerAlive()) {
			for (LevelObserver o : observers) {
				o.levelLost();
			}
		} Collect.Hit("Level.java","updateObservers()", "6225"); if (remainingPellets() == 0) {
			for (LevelObserver o : observers) {
				o.levelWon();
			}
		} Collect.Hit("Level.java","updateObservers()", "6326");}

	/**
	 * Returns <code>true</code> iff at least one of the players in this level
	 * is alive.
	 * 
	 * @return <code>true</code> if at least one of the registered players is
	 *         alive.
	 */
	public boolean isAnyPlayerAlive() {Collect.Hit("Level.java","isAnyPlayerAlive()");for (Player p : players) {
			if (p.isAlive()) {
				return true;
			}
		} Collect.Hit("Level.java","isAnyPlayerAlive()", "6680"); Collect.Hit("Level.java","isAnyPlayerAlive()", "6762");return false ; }

	/**
	 * Counts the pellets remaining on the board.
	 * 
	 * @return The amount of pellets remaining on the board.
	 */
	public int remainingPellets() {Collect.Hit("Level.java","remainingPellets()");Board b = getBoard(); Collect.Hit("Level.java","remainingPellets()", "6944"); int pellets = 0; Collect.Hit("Level.java","remainingPellets()", "6969"); for (int x = 0; x < b.getWidth(); x++) {
			for (int y = 0; y < b.getHeight(); y++) {
				for (Unit u : b.squareAt(x, y).getOccupants()) {
					if (u instanceof Pellet) {
						pellets++;
					}
				}
			}
		} Collect.Hit("Level.java","remainingPellets()", "6989"); assert pellets >= 0; Collect.Hit("Level.java","remainingPellets()", "7210"); Collect.Hit("Level.java","remainingPellets()", "7234");return pellets ; }

	/**
	 * A task that moves an NPC and reschedules itself after it finished.
	 * 
	 * @author Jeroen Roosen 
	 */
	private final class NpcMoveTask implements Runnable {

		/**
		 * The service executing the task.
		 */
		private final ScheduledExecutorService service;

		/**
		 * The NPC to move.
		 */
		private final NPC npc;

		/**
		 * Creates a new task.
		 * 
		 * @param s
		 *            The service that executes the task.
		 * @param n
		 *            The NPC to move.
		 */
		NpcMoveTask(ScheduledExecutorService s, NPC n) {Collect.Hit("Level.java","NpcMoveTask(ScheduledExecutorService s, NPC n)");this.service = s; Collect.Hit("Level.java","NpcMoveTask(ScheduledExecutorService s, NPC n)", "7822"); this.npc = n; Collect.Hit("Level.java","NpcMoveTask(ScheduledExecutorService s, NPC n)", "7844");}

		@Override
		public void run() {Collect.Hit("Level.java","run()");Direction nextMove = npc.nextMove(); Collect.Hit("Level.java","run()", "7905"); if (nextMove != null) {
				move(npc, nextMove);
			} Collect.Hit("Level.java","run()", "7946"); long interval = npc.getInterval(); Collect.Hit("Level.java","run()", "8006"); service.schedule(this, interval, TimeUnit.MILLISECONDS); Collect.Hit("Level.java","run()", "8045");}
	}

	/**
	 * An observer that will be notified when the level is won or lost.
	 * 
	 * @author Jeroen Roosen 
	 */
	public interface LevelObserver {

		/**
		 * The level has been won. Typically the level should be stopped when
		 * this event is received.
		 */
		void levelWon();

		/**
		 * The level has been lost. Typically the level should be stopped when
		 * this event is received.
		 */
		void levelLost();
	}
}
