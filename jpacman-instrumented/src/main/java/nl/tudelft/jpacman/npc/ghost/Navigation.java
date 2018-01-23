package nl.tudelft.jpacman.npc.ghost;import coverageApi.Collect;import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.board.Unit; public final class Navigation {

	private Navigation() {Collect.Hit("Navigation.java","Navigation()");Collect.Hit("Navigation.java","Navigation()", "|project://sqat-analysis/src/sqat/series2/A1b_DynCov.rsc|(4826,34)");}
	
	/**
	 * Calculates the shortest path. This is done by BFS. This search ensures
	 * the traveller is allowed to occupy the squares on the way, or returns the
	 * shortest path to the square regardless of terrain if no traveller is
	 * specified.
	 * 
	 * @param from
	 *            The starting square.
	 * @param to
	 *            The destination.
	 * @param traveller
	 *            The traveller attempting to reach the destination. If
	 *            traveller is set to <code>null</code>, this method will ignore
	 *            terrain and find the shortest path whether it can actually be
	 *            reached or not.
	 * @return The shortest path to the destination or <code>null</code> if no
	 *         such path could be found. When the destination is the current
	 *         square, an empty list is returned.
	 */
	public static List<Direction> shortestPath(Square from, Square to, Unit traveller) {Collect.Hit("Navigation.java","shortestPath(Square from, Square to, Unit traveller)");if (from.equals(to)) {
			return new ArrayList<>();
		} Collect.Hit("Navigation.java","shortestPath(Square from, Square to, Unit traveller)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/npc/ghost/Navigation.java|(1380,57,<42,2>,<44,3>)"); List<Node> targets = new ArrayList<>(); Collect.Hit("Navigation.java","shortestPath(Square from, Square to, Unit traveller)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/npc/ghost/Navigation.java|(1443,39,<46,2>,<46,41>)"); Set<Square> visited = new HashSet<>(); Collect.Hit("Navigation.java","shortestPath(Square from, Square to, Unit traveller)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/npc/ghost/Navigation.java|(1486,38,<47,2>,<47,40>)"); targets.add(new Node(null, from, null)); Collect.Hit("Navigation.java","shortestPath(Square from, Square to, Unit traveller)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/npc/ghost/Navigation.java|(1528,40,<48,2>,<48,42>)"); while (!targets.isEmpty()) {
			Node n = targets.remove(0);
			Square s = n.getSquare();
			if (s.equals(to)) {
				return n.getPath();
			}
			visited.add(s);
			addNewTargets(traveller, targets, visited, n, s);
		} Collect.Hit("Navigation.java","shortestPath(Square from, Square to, Unit traveller)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/npc/ghost/Navigation.java|(1572,224,<49,2>,<57,3>)"); return null; Collect.Hit("Navigation.java","shortestPath(Square from, Square to, Unit traveller)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/npc/ghost/Navigation.java|(1800,12,<58,2>,<58,14>)");}

	private static void addNewTargets(Unit traveller, List<Node> targets, Set<Square> visited, Node n, Square s) {Collect.Hit("Navigation.java","addNewTargets(Unit traveller, List<Node> targets, Set<Square> visited, Node n, Square s)");for (Direction d : Direction.values()) {
			Square target = s.getSquareAt(d);
			if (!visited.contains(target)
					&& (traveller == null || target
							.isAccessibleTo(traveller))) {
				targets.add(new Node(d, target, n));
			}
		} Collect.Hit("Navigation.java","addNewTargets(Unit traveller, List<Node> targets, Set<Square> visited, Node n, Square s)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/npc/ghost/Navigation.java|(1935,242,<62,2>,<69,3>)");}

	/**
	 * Finds the nearest unit of the given type and returns its location. This
	 * method will perform a breadth first search starting from the given
	 * square.
	 * 
	 * @param type
	 *            The type of unit to search for.
	 * @param currentLocation
	 *            The starting location for the search.
	 * @return The nearest unit of the given type, or <code>null</code> if no
	 *         such unit could be found.
	 */
	public static Unit findNearest(Class<? extends Unit> type,	Square currentLocation) {Collect.Hit("Navigation.java","findNearest(Class<? extends Unit> type,Square currentLocation)");List<Square> toDo = new ArrayList<>(); Collect.Hit("Navigation.java","findNearest(Class<? extends Unit> type,Square currentLocation)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/npc/ghost/Navigation.java|(2716,38,<85,2>,<85,40>)"); Set<Square> visited = new HashSet<>(); Collect.Hit("Navigation.java","findNearest(Class<? extends Unit> type,Square currentLocation)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/npc/ghost/Navigation.java|(2758,38,<86,2>,<86,40>)"); toDo.add(currentLocation); Collect.Hit("Navigation.java","findNearest(Class<? extends Unit> type,Square currentLocation)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/npc/ghost/Navigation.java|(2802,26,<88,2>,<88,28>)"); while (!toDo.isEmpty()) {
			Square square = toDo.remove(0);
			Unit unit = findUnit(type, square);
			if (unit != null) {
				return unit;
			}
			visited.add(square);
			for (Direction d : Direction.values()) {
				Square newTarget = square.getSquareAt(d);
				if (!visited.contains(newTarget) && !toDo.contains(newTarget)) {
					toDo.add(newTarget);
				}
			}
		} Collect.Hit("Navigation.java","findNearest(Class<? extends Unit> type,Square currentLocation)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/npc/ghost/Navigation.java|(2834,381,<90,2>,<103,3>)"); return null; Collect.Hit("Navigation.java","findNearest(Class<? extends Unit> type,Square currentLocation)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/npc/ghost/Navigation.java|(3219,12,<104,2>,<104,14>)");}

	/**
	 * Determines whether a square has an occupant of a certain type.
	 * 
	 * @param type
	 *            The type to search for.
	 * @param square
	 *            The square to search.
	 * @return A unit of type T, iff such a unit occupies this square, or
	 *         <code>null</code> of none does.
	 */
	public static Unit findUnit(Class<? extends Unit> type, Square square) {Collect.Hit("Navigation.java","findUnit(Class<? extends Unit> type, Square square)");for (Unit u : square.getOccupants()) {
			if (type.isInstance(u)) {
				return u;
			}
		} Collect.Hit("Navigation.java","findUnit(Class<? extends Unit> type, Square square)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/npc/ghost/Navigation.java|(3633,94,<118,2>,<122,3>)"); return null; Collect.Hit("Navigation.java","findUnit(Class<? extends Unit> type, Square square)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/npc/ghost/Navigation.java|(3731,12,<123,2>,<123,14>)");}

	/**
	 * Helper class to keep track of the path.
	 * 
	 * @author Jeroen Roosen 
	 */
	private static final class Node {

		/**
		 * The direction for this node, which is <code>null</code> for the root
		 * node.
		 */
		private final Direction direction;

		/**
		 * The parent node, which is <code>null</code> for the root node.
		 */
		private final Node parent;

		/**
		 * The square associated with this node.
		 */
		private final Square square;

		/**
		 * Creates a new node.
		 * 
		 * @param d
		 *            The direction, which is <code>null</code> for the root
		 *            node.
		 * @param s
		 *            The square.
		 * @param p
		 *            The parent node, which is <code>null</code> for the root
		 *            node.
		 */
		Node(Direction d, Square s, Node p) {Collect.Hit("Navigation.java","Node(Direction d, Square s, Node p)");this.direction = d; Collect.Hit("Navigation.java","Node(Direction d, Square s, Node p)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/npc/ghost/Navigation.java|(4585,19,<162,3>,<162,22>)"); this.square = s; Collect.Hit("Navigation.java","Node(Direction d, Square s, Node p)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/npc/ghost/Navigation.java|(4609,16,<163,3>,<163,19>)"); this.parent = p; Collect.Hit("Navigation.java","Node(Direction d, Square s, Node p)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/npc/ghost/Navigation.java|(4630,16,<164,3>,<164,19>)");}

		/**
		 * @return The direction for this node, or <code>null</code> if this
		 *         node is a root node.
		 */
		private Direction getDirection() {Collect.Hit("Navigation.java","getDirection()");return direction; Collect.Hit("Navigation.java","getDirection()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/npc/ghost/Navigation.java|(4817,17,<172,3>,<172,20>)");}

		/**
		 * @return The square for this node.
		 */
		private Square getSquare() {Collect.Hit("Navigation.java","getSquare()");return square; Collect.Hit("Navigation.java","getSquare()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/npc/ghost/Navigation.java|(4932,14,<179,3>,<179,17>)");}

		/**
		 * @return The parent node, or <code>null</code> if this node is a root
		 *         node.
		 */
		private Node getParent() {Collect.Hit("Navigation.java","getParent()");return parent; Collect.Hit("Navigation.java","getParent()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/npc/ghost/Navigation.java|(5097,14,<187,3>,<187,17>)");}

		/**
		 * Returns the list of values from the root of the tree to this node.
		 * 
		 * @return The list of values from the root of the tree to this node.
		 */
		private List<Direction> getPath() {Collect.Hit("Navigation.java","getPath()");if (getParent() == null) {
				return new ArrayList<>();
			} Collect.Hit("Navigation.java","getPath()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/npc/ghost/Navigation.java|(5329,63,<196,3>,<198,4>)"); List<Direction> path = parent.getPath(); Collect.Hit("Navigation.java","getPath()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/npc/ghost/Navigation.java|(5397,40,<199,3>,<199,43>)"); path.add(getDirection()); Collect.Hit("Navigation.java","getPath()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/npc/ghost/Navigation.java|(5442,25,<200,3>,<200,28>)"); return path; Collect.Hit("Navigation.java","getPath()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/npc/ghost/Navigation.java|(5472,12,<201,3>,<201,15>)");}
	}
}
