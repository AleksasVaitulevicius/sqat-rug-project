package nl.tudelft.jpacman.npc.ghost;import coverageApi.Collect;import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.board.Unit; public final class Navigation {

	private Navigation() {Collect.Hit("Navigation.java","Navigation()");Collect.Hit("Navigation.java","Navigation()", "4828");}
	
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
		} Collect.Hit("Navigation.java","shortestPath(Square from, Square to, Unit traveller)", "1380"); List<Node> targets = new ArrayList<>(); Collect.Hit("Navigation.java","shortestPath(Square from, Square to, Unit traveller)", "1443"); Set<Square> visited = new HashSet<>(); Collect.Hit("Navigation.java","shortestPath(Square from, Square to, Unit traveller)", "1486"); targets.add(new Node(null, from, null)); Collect.Hit("Navigation.java","shortestPath(Square from, Square to, Unit traveller)", "1528"); while (!targets.isEmpty()) {
			Node n = targets.remove(0);
			Square s = n.getSquare();
			if (s.equals(to)) {
				return n.getPath();
			}
			visited.add(s);
			addNewTargets(traveller, targets, visited, n, s);
		} Collect.Hit("Navigation.java","shortestPath(Square from, Square to, Unit traveller)", "1572"); Collect.Hit("Navigation.java","shortestPath(Square from, Square to, Unit traveller)", "1800");return null ; }

	private static void addNewTargets(Unit traveller, List<Node> targets, Set<Square> visited, Node n, Square s) {Collect.Hit("Navigation.java","addNewTargets(Unit traveller, List<Node> targets, Set<Square> visited, Node n, Square s)");for (Direction d : Direction.values()) {
			Square target = s.getSquareAt(d);
			if (!visited.contains(target)
					&& (traveller == null || target
							.isAccessibleTo(traveller))) {
				targets.add(new Node(d, target, n));
			}
		} Collect.Hit("Navigation.java","addNewTargets(Unit traveller, List<Node> targets, Set<Square> visited, Node n, Square s)", "1935");}

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
	public static Unit findNearest(Class<? extends Unit> type,	Square currentLocation) {Collect.Hit("Navigation.java","findNearest(Class<? extends Unit> type,Square currentLocation)");List<Square> toDo = new ArrayList<>(); Collect.Hit("Navigation.java","findNearest(Class<? extends Unit> type,Square currentLocation)", "2716"); Set<Square> visited = new HashSet<>(); Collect.Hit("Navigation.java","findNearest(Class<? extends Unit> type,Square currentLocation)", "2758"); toDo.add(currentLocation); Collect.Hit("Navigation.java","findNearest(Class<? extends Unit> type,Square currentLocation)", "2802"); while (!toDo.isEmpty()) {
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
		} Collect.Hit("Navigation.java","findNearest(Class<? extends Unit> type,Square currentLocation)", "2834"); Collect.Hit("Navigation.java","findNearest(Class<? extends Unit> type,Square currentLocation)", "3219");return null ; }

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
		} Collect.Hit("Navigation.java","findUnit(Class<? extends Unit> type, Square square)", "3633"); Collect.Hit("Navigation.java","findUnit(Class<? extends Unit> type, Square square)", "3731");return null ; }

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
		Node(Direction d, Square s, Node p) {Collect.Hit("Navigation.java","Node(Direction d, Square s, Node p)");this.direction = d; Collect.Hit("Navigation.java","Node(Direction d, Square s, Node p)", "4585"); this.square = s; Collect.Hit("Navigation.java","Node(Direction d, Square s, Node p)", "4609"); this.parent = p; Collect.Hit("Navigation.java","Node(Direction d, Square s, Node p)", "4630");}

		/**
		 * @return The direction for this node, or <code>null</code> if this
		 *         node is a root node.
		 */
		private Direction getDirection() {Collect.Hit("Navigation.java","getDirection()"); Collect.Hit("Navigation.java","getDirection()", "4817");return direction ; }

		/**
		 * @return The square for this node.
		 */
		private Square getSquare() {Collect.Hit("Navigation.java","getSquare()"); Collect.Hit("Navigation.java","getSquare()", "4932");return square ; }

		/**
		 * @return The parent node, or <code>null</code> if this node is a root
		 *         node.
		 */
		private Node getParent() {Collect.Hit("Navigation.java","getParent()"); Collect.Hit("Navigation.java","getParent()", "5097");return parent ; }

		/**
		 * Returns the list of values from the root of the tree to this node.
		 * 
		 * @return The list of values from the root of the tree to this node.
		 */
		private List<Direction> getPath() {Collect.Hit("Navigation.java","getPath()");if (getParent() == null) {
				return new ArrayList<>();
			} Collect.Hit("Navigation.java","getPath()", "5329"); List<Direction> path = parent.getPath(); Collect.Hit("Navigation.java","getPath()", "5397"); path.add(getDirection()); Collect.Hit("Navigation.java","getPath()", "5442"); Collect.Hit("Navigation.java","getPath()", "5472");return path ; }
	}
}
