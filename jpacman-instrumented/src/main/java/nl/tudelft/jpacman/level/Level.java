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
	public Level(Board b, List<NPC> ghosts, List<Square> startPositions, CollisionMap collisionMap) {Collect.Hit("Level.java","Level(Board b, List<NPC> ghosts, List<Square> startPositions, CollisionMap collisionMap)");assert b != null; Collect.Hit("Level.java","Level(Board b, List<NPC> ghosts, List<Square> startPositions, CollisionMap collisionMap)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Level.java|(2354,17,<94,2>,<94,19>)"); assert ghosts != null; Collect.Hit("Level.java","Level(Board b, List<NPC> ghosts, List<Square> startPositions, CollisionMap collisionMap)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Level.java|(2375,22,<95,2>,<95,24>)"); assert startPositions != null; Collect.Hit("Level.java","Level(Board b, List<NPC> ghosts, List<Square> startPositions, CollisionMap collisionMap)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Level.java|(2401,30,<96,2>,<96,32>)"); this.board = b; Collect.Hit("Level.java","Level(Board b, List<NPC> ghosts, List<Square> startPositions, CollisionMap collisionMap)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Level.java|(2437,15,<98,2>,<98,17>)"); this.inProgress = false; Collect.Hit("Level.java","Level(Board b, List<NPC> ghosts, List<Square> startPositions, CollisionMap collisionMap)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Level.java|(2456,24,<99,2>,<99,26>)"); this.npcs = new HashMap<>(); Collect.Hit("Level.java","Level(Board b, List<NPC> ghosts, List<Square> startPositions, CollisionMap collisionMap)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Level.java|(2484,28,<100,2>,<100,30>)"); for (NPC g : ghosts) {
			npcs.put(g, null);
		} Collect.Hit("Level.java","Level(Board b, List<NPC> ghosts, List<Square> startPositions, CollisionMap collisionMap)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Level.java|(2516,50,<101,2>,<103,3>)"); this.startSquares = startPositions; Collect.Hit("Level.java","Level(Board b, List<NPC> ghosts, List<Square> startPositions, CollisionMap collisionMap)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Level.java|(2570,35,<104,2>,<104,37>)"); this.startSquareIndex = 0; Collect.Hit("Level.java","Level(Board b, List<NPC> ghosts, List<Square> startPositions, CollisionMap collisionMap)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Level.java|(2609,26,<105,2>,<105,28>)"); this.players = new ArrayList<>(); Collect.Hit("Level.java","Level(Board b, List<NPC> ghosts, List<Square> startPositions, CollisionMap collisionMap)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Level.java|(2639,33,<106,2>,<106,35>)"); this.collisions = collisionMap; Collect.Hit("Level.java","Level(Board b, List<NPC> ghosts, List<Square> startPositions, CollisionMap collisionMap)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Level.java|(2676,31,<107,2>,<107,33>)"); this.observers = new HashSet<>(); Collect.Hit("Level.java","Level(Board b, List<NPC> ghosts, List<Square> startPositions, CollisionMap collisionMap)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Level.java|(2711,33,<108,2>,<108,35>)");}

	/**
	 * Adds an observer that will be notified when the level is won or lost.
	 * 
	 * @param observer
	 *            The observer that will be notified.
	 */
	public void addObserver(LevelObserver observer) {Collect.Hit("Level.java","addObserver(LevelObserver observer)");observers.add(observer); Collect.Hit("Level.java","addObserver(LevelObserver observer)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Level.java|(2972,24,<118,2>,<118,26>)");}

	/**
	 * Removes an observer if it was listed.
	 * 
	 * @param observer
	 *            The observer to be removed.
	 */
	public void removeObserver(LevelObserver observer) {Collect.Hit("Level.java","removeObserver(LevelObserver observer)");observers.remove(observer); Collect.Hit("Level.java","removeObserver(LevelObserver observer)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Level.java|(3187,27,<128,2>,<128,29>)");}

	/**
	 * Registers a player on this level, assigning him to a starting position. A
	 * player can only be registered once, registering a player again will have
	 * no effect.
	 * 
	 * @param p
	 *            The player to register.
	 */
	public void registerPlayer(Player p) {Collect.Hit("Level.java","registerPlayer(Player p)");assert p != null; Collect.Hit("Level.java","registerPlayer(Player p)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Level.java|(3510,17,<140,2>,<140,19>)"); assert !startSquares.isEmpty(); Collect.Hit("Level.java","registerPlayer(Player p)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Level.java|(3531,31,<141,2>,<141,33>)"); if (players.contains(p)) {
			return;
		} Collect.Hit("Level.java","registerPlayer(Player p)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Level.java|(3568,43,<143,2>,<145,3>)"); players.add(p); Collect.Hit("Level.java","registerPlayer(Player p)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Level.java|(3615,15,<146,2>,<146,17>)"); Square square = startSquares.get(startSquareIndex); Collect.Hit("Level.java","registerPlayer(Player p)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Level.java|(3634,51,<147,2>,<147,53>)"); p.occupy(square); Collect.Hit("Level.java","registerPlayer(Player p)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Level.java|(3689,17,<148,2>,<148,19>)"); startSquareIndex++; Collect.Hit("Level.java","registerPlayer(Player p)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Level.java|(3710,19,<149,2>,<149,21>)"); startSquareIndex %= startSquares.size(); Collect.Hit("Level.java","registerPlayer(Player p)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Level.java|(3733,40,<150,2>,<150,42>)");}

	/**
	 * Returns the board of this level.
	 * 
	 * @return The board of this level.
	 */
	public Board getBoard() {Collect.Hit("Level.java","getBoard()");return board; Collect.Hit("Level.java","getBoard()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Level.java|(3905,13,<159,2>,<159,15>)");}

	/**
	 * Moves the unit into the given direction if possible and handles all
	 * collisions.
	 * 
	 * @param unit
	 *            The unit to move.
	 * @param direction
	 *            The direction to move the unit in.
	 */
	public void move(Unit unit, Direction direction) {Collect.Hit("Level.java","move(Unit unit, Direction direction)");assert unit != null; Collect.Hit("Level.java","move(Unit unit, Direction direction)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Level.java|(4213,20,<172,2>,<172,22>)"); assert direction != null; Collect.Hit("Level.java","move(Unit unit, Direction direction)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Level.java|(4237,25,<173,2>,<173,27>)"); if (!isInProgress()) {
			return;
		} Collect.Hit("Level.java","move(Unit unit, Direction direction)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Level.java|(4268,39,<175,2>,<177,3>)"); synchronized (moveLock) {
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
		} Collect.Hit("Level.java","move(Unit unit, Direction direction)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Level.java|(4313,412,<179,2>,<192,3>)");}

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
		} Collect.Hit("Level.java","start()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Level.java|(4856,143,<200,2>,<207,3>)");}

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
		} Collect.Hit("Level.java","stop()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Level.java|(5150,121,<215,2>,<221,3>)");}

	/**
	 * Starts all NPC movement scheduling.
	 */
	private void startNPCs() {Collect.Hit("Level.java","startNPCs()");for (final NPC npc : npcs.keySet()) {
			ScheduledExecutorService service = Executors
					.newSingleThreadScheduledExecutor();
			service.schedule(new NpcMoveTask(service, npc),
					npc.getInterval() / 2, TimeUnit.MILLISECONDS);
			npcs.put(npc, service);
		} Collect.Hit("Level.java","startNPCs()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Level.java|(5363,267,<228,2>,<234,3>)");}

	/**
	 * Stops all NPC movement scheduling and interrupts any movements being
	 * executed.
	 */
	private void stopNPCs() {Collect.Hit("Level.java","stopNPCs()");for (Entry<NPC, ScheduledExecutorService> e : npcs.entrySet()) {
			e.getValue().shutdownNow();
		} Collect.Hit("Level.java","stopNPCs()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Level.java|(5769,101,<242,2>,<244,3>)");}

	/**
	 * Returns whether this level is in progress, i.e. whether moves can be made
	 * on the board.
	 * 
	 * @return <code>true</code> iff this level is in progress.
	 */
	public boolean isInProgress() {Collect.Hit("Level.java","isInProgress()");return inProgress; Collect.Hit("Level.java","isInProgress()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Level.java|(6092,18,<254,2>,<254,20>)");}

	/**
	 * Updates the observers about the state of this level.
	 */
	private void updateObservers() {Collect.Hit("Level.java","updateObservers()");if (!isAnyPlayerAlive()) {
			for (LevelObserver o : observers) {
				o.levelLost();
			}
		} Collect.Hit("Level.java","updateObservers()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Level.java|(6225,97,<261,2>,<265,3>)"); if (remainingPellets() == 0) {
			for (LevelObserver o : observers) {
				o.levelWon();
			}
		} Collect.Hit("Level.java","updateObservers()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Level.java|(6326,100,<266,2>,<270,3>)");}

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
		} Collect.Hit("Level.java","isAnyPlayerAlive()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Level.java|(6680,78,<281,2>,<285,3>)"); return false; Collect.Hit("Level.java","isAnyPlayerAlive()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Level.java|(6762,13,<286,2>,<286,15>)");}

	/**
	 * Counts the pellets remaining on the board.
	 * 
	 * @return The amount of pellets remaining on the board.
	 */
	public int remainingPellets() {Collect.Hit("Level.java","remainingPellets()");Board b = getBoard(); Collect.Hit("Level.java","remainingPellets()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Level.java|(6944,21,<295,2>,<295,23>)"); int pellets = 0; Collect.Hit("Level.java","remainingPellets()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Level.java|(6969,16,<296,2>,<296,18>)"); for (int x = 0; x < b.getWidth(); x++) {
			for (int y = 0; y < b.getHeight(); y++) {
				for (Unit u : b.squareAt(x, y).getOccupants()) {
					if (u instanceof Pellet) {
						pellets++;
					}
				}
			}
		} Collect.Hit("Level.java","remainingPellets()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Level.java|(6989,217,<297,2>,<305,3>)"); assert pellets >= 0; Collect.Hit("Level.java","remainingPellets()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Level.java|(7210,20,<306,2>,<306,22>)"); return pellets; Collect.Hit("Level.java","remainingPellets()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Level.java|(7234,15,<307,2>,<307,17>)");}

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
		NpcMoveTask(ScheduledExecutorService s, NPC n) {Collect.Hit("Level.java","NpcMoveTask(ScheduledExecutorService s, NPC n)");this.service = s; Collect.Hit("Level.java","NpcMoveTask(ScheduledExecutorService s, NPC n)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Level.java|(7822,17,<336,3>,<336,20>)"); this.npc = n; Collect.Hit("Level.java","NpcMoveTask(ScheduledExecutorService s, NPC n)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Level.java|(7844,13,<337,3>,<337,16>)");}

		@Override
		public void run() {Collect.Hit("Level.java","run()");Direction nextMove = npc.nextMove(); Collect.Hit("Level.java","run()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Level.java|(7905,36,<342,3>,<342,39>)"); if (nextMove != null) {
				move(npc, nextMove);
			} Collect.Hit("Level.java","run()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Level.java|(7946,55,<343,3>,<345,4>)"); long interval = npc.getInterval(); Collect.Hit("Level.java","run()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Level.java|(8006,34,<346,3>,<346,37>)"); service.schedule(this, interval, TimeUnit.MILLISECONDS); Collect.Hit("Level.java","run()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/Level.java|(8045,56,<347,3>,<347,59>)");}
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
