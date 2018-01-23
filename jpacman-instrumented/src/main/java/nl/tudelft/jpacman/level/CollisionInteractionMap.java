package nl.tudelft.jpacman.level;import coverageApi.Collect;import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nl.tudelft.jpacman.board.Unit; public class CollisionInteractionMap implements CollisionMap {

	/**
	 * The collection of collision handlers.
	 */
	private final Map<Class<? extends Unit>, Map<Class<? extends Unit>, CollisionHandler<?, ?>>> handlers;

	/**
	 * Creates a new, empty collision map.
	 */
	public CollisionInteractionMap() {Collect.Hit("CollisionInteractionMap.java","CollisionInteractionMap()");this.handlers = new HashMap<>(); Collect.Hit("CollisionInteractionMap.java","CollisionInteractionMap()", "626");}

	/**
	 * Adds a two-way collision interaction to this collection, i.e. the
	 * collision handler will be used for both C1 versus C2 and C2 versus C1.
	 * 
	 * @param <C1>
	 *            The collider type.
	 * @param <C2>
	 *            The collidee (unit that was moved into) type.
	 * 
	 * @param collider
	 *            The collider type.
	 * @param collidee
	 *            The collidee type.
	 * @param handler
	 *            The handler that handles the collision.
	 */
	public <C1 extends Unit, C2 extends Unit> void onCollision( Class<C1> collider, Class<C2> collidee, CollisionHandler<C1, C2> handler) {Collect.Hit("CollisionInteractionMap.java","onCollision( Class<C1> collider, Class<C2> collidee, CollisionHandler<C1, C2> handler)");onCollision(collider, collidee, true, handler); Collect.Hit("CollisionInteractionMap.java","onCollision( Class<C1> collider, Class<C2> collidee, CollisionHandler<C1, C2> handler)", "1296");}

	/**
	 * Adds a collision interaction to this collection.
	 * 
	 * @param <C1>
	 *            The collider type.
	 * @param <C2>
	 *            The collidee (unit that was moved into) type.
	 * 
	 * @param collider
	 *            The collider type.
	 * @param collidee
	 *            The collidee type.
	 * @param symetric
	 *            <code>true</code> if this collision is used for both
	 *            C1 against C2 and vice versa;
	 *            <code>false</code> if only for C1 against C2.
	 * @param handler
	 *            The handler that handles the collision.
	 */
	public <C1 extends Unit, C2 extends Unit> void onCollision(Class<C1> collider, Class<C2> collidee, boolean symetric, CollisionHandler<C1, C2> handler) {Collect.Hit("CollisionInteractionMap.java","onCollision(Class<C1> collider, Class<C2> collidee, boolean symetric, CollisionHandler<C1, C2> handler)");addHandler(collider, collidee, handler); Collect.Hit("CollisionInteractionMap.java","onCollision(Class<C1> collider, Class<C2> collidee, boolean symetric, CollisionHandler<C1, C2> handler)", "2103"); if (symetric) {
			addHandler(collidee, collider, new InverseCollisionHandler<>(
					handler));
		} Collect.Hit("CollisionInteractionMap.java","onCollision(Class<C1> collider, Class<C2> collidee, boolean symetric, CollisionHandler<C1, C2> handler)", "2147");}

	/**
	 * Adds the collision interaction..
	 * 
	 * @param collider
	 *            The collider type.
	 * @param collidee
	 *            The collidee type.
	 * @param handler
	 *            The handler that handles the collision.
	 */
	private void addHandler(Class<? extends Unit> collider, Class<? extends Unit> collidee, CollisionHandler<?, ?> handler) {Collect.Hit("CollisionInteractionMap.java","addHandler(Class<? extends Unit> collider, Class<? extends Unit> collidee, CollisionHandler<?, ?> handler)");if (!handlers.containsKey(collider)) {
			handlers.put(
					collider,
					new HashMap<>());
		} Collect.Hit("CollisionInteractionMap.java","addHandler(Class<? extends Unit> collider, Class<? extends Unit> collidee, CollisionHandler<?, ?> handler)", "2628"); Map<Class<? extends Unit>, CollisionHandler<?, ?>> map = handlers
				.get(collider); Collect.Hit("CollisionInteractionMap.java","addHandler(Class<? extends Unit> collider, Class<? extends Unit> collidee, CollisionHandler<?, ?> handler)", "2735"); map.put(collidee, handler); Collect.Hit("CollisionInteractionMap.java","addHandler(Class<? extends Unit> collider, Class<? extends Unit> collidee, CollisionHandler<?, ?> handler)", "2825");}

	/**
	 * Handles the collision between two colliding parties, if a suitable
	 * collision handler is listed.
	 * 
	 * @param <C1>
	 *            The collider type.
	 * @param <C2>
	 *            The collidee (unit that was moved into) type.
	 * 
	 * @param collider
	 *            The collider.
	 * @param collidee
	 *            The collidee.
	 */
	@Override
	public <C1 extends Unit, C2 extends Unit> void collide(C1 collider, C2 collidee) {Collect.Hit("CollisionInteractionMap.java","collide(C1 collider, C2 collidee)");Class<? extends Unit> colliderKey = getMostSpecificClass(handlers, collider.getClass()); Collect.Hit("CollisionInteractionMap.java","collide(C1 collider, C2 collidee)", "3323"); if (colliderKey == null) {
			return;
		} Collect.Hit("CollisionInteractionMap.java","collide(C1 collider, C2 collidee)", "3415"); Map<Class<? extends Unit>, CollisionHandler<?, ?>> map = handlers
				.get(colliderKey); Collect.Hit("CollisionInteractionMap.java","collide(C1 collider, C2 collidee)", "3464"); Class<? extends Unit> collideeKey = getMostSpecificClass(map,
				collidee.getClass()); Collect.Hit("CollisionInteractionMap.java","collide(C1 collider, C2 collidee)", "3557"); if (collideeKey == null) {
			return;
		} Collect.Hit("CollisionInteractionMap.java","collide(C1 collider, C2 collidee)", "3649"); CollisionHandler<C1, C2> collisionHandler = (CollisionHandler<C1, C2>) map
				.get(collideeKey); Collect.Hit("CollisionInteractionMap.java","collide(C1 collider, C2 collidee)", "3698"); if (collisionHandler == null) {
			return;
		} Collect.Hit("CollisionInteractionMap.java","collide(C1 collider, C2 collidee)", "3800"); collisionHandler.handleCollision(collider, collidee); Collect.Hit("CollisionInteractionMap.java","collide(C1 collider, C2 collidee)", "3854");}

	/**
	 * Figures out the most specific class that is listed in the map. I.e. if A
	 * extends B and B is listed while requesting A, then B will be returned.
	 * 
	 * @param map
	 *            The map with the key collection to find a matching class in.
	 * @param key
	 *            The class to search the most suitable key for.
	 * @return The most specific class from the key collection.
	 */
	private Class<? extends Unit> getMostSpecificClass(Map<Class<? extends Unit>, ?> map, Class<? extends Unit> key) {Collect.Hit("CollisionInteractionMap.java","getMostSpecificClass(Map<Class<? extends Unit>, ?> map, Class<? extends Unit> key)");List<Class<? extends Unit>> collideeInheritance = getInheritance(key); Collect.Hit("CollisionInteractionMap.java","getMostSpecificClass(Map<Class<? extends Unit>, ?> map, Class<? extends Unit> key)", "4440"); for (Class<? extends Unit> pointer : collideeInheritance) {
			if (map.containsKey(pointer)) {
				return pointer;
			}
		} Collect.Hit("CollisionInteractionMap.java","getMostSpecificClass(Map<Class<? extends Unit>, ?> map, Class<? extends Unit> key)", "4514"); Collect.Hit("CollisionInteractionMap.java","getMostSpecificClass(Map<Class<? extends Unit>, ?> map, Class<? extends Unit> key)", "4645");return null ; }

	/**
	 * Returns a list of all classes and interfaces the class inherits.
	 * 
	 * @param clazz
	 *            The class to create a list of super classes and interfaces
	 *            for.
	 * @return A list of all classes and interfaces the class inherits.
	 */
	private List<Class<? extends Unit>> getInheritance(Class<? extends Unit> clazz) {Collect.Hit("CollisionInteractionMap.java","getInheritance(Class<? extends Unit> clazz)");List<Class<? extends Unit>> found = new ArrayList<>(); Collect.Hit("CollisionInteractionMap.java","getInheritance(Class<? extends Unit> clazz)", "5020"); found.add(clazz); Collect.Hit("CollisionInteractionMap.java","getInheritance(Class<? extends Unit> clazz)", "5078"); int index = 0; Collect.Hit("CollisionInteractionMap.java","getInheritance(Class<? extends Unit> clazz)", "5101"); while (found.size() > index) {
			Class<?> current = found.get(index);
			Class<?> superClass = current.getSuperclass();
			if (superClass != null && Unit.class.isAssignableFrom(superClass)) {
				found.add((Class<? extends Unit>) superClass);
			}
			for (Class<?> classInterface : current.getInterfaces()) {
				if (Unit.class.isAssignableFrom(classInterface)) {
					found.add((Class<? extends Unit>) classInterface);
				}
			}
			index++;
		} Collect.Hit("CollisionInteractionMap.java","getInheritance(Class<? extends Unit> clazz)", "5119"); Collect.Hit("CollisionInteractionMap.java","getInheritance(Class<? extends Unit> clazz)", "5584");return found ; }

	/**
	 * Handles the collision between two colliding parties.
	 * 
	 * @author Michael de Jong
	 * 
	 * @param <C1>
	 *            The collider type.
	 * @param <C2>
	 *            The collidee type.
	 */
	public interface CollisionHandler<C1 extends Unit, C2 extends Unit> {

		/**
		 * Handles the collision between two colliding parties.
		 * 
		 * @param collider
		 *            The collider.
		 * @param collidee
		 *            The collidee.
		 */
		void handleCollision(C1 collider, C2 collidee);
	}

	/**
	 * An symmetrical copy of a collision hander.
	 * 
	 * @author Michael de Jong
	 * 
	 * @param <C1>
	 *            The collider type.
	 * @param <C2>
	 *            The collidee type.
	 */
	private static class InverseCollisionHandler<C1 extends Unit, C2 extends Unit> implements CollisionHandler<C1, C2> {

		/**
		 * The handler of this collision.
		 */
		private final CollisionHandler<C2, C1> handler;

		/**
		 * Creates a new collision handler.
		 * 
		 * @param handler
		 *            The symmetric handler for this collision.
		 */
		InverseCollisionHandler(CollisionHandler<C2, C1> handler) {Collect.Hit("CollisionInteractionMap.java","InverseCollisionHandler(CollisionHandler<C2, C1> handler)");this.handler = handler; Collect.Hit("CollisionInteractionMap.java","InverseCollisionHandler(CollisionHandler<C2, C1> handler)", "6773");}

		/**
		 * Handles this collision by flipping the collider and collidee, making
		 * it compatible with the initial collision.
		 */
		@Override
		public void handleCollision(C1 collider, C2 collidee) {Collect.Hit("CollisionInteractionMap.java","handleCollision(C1 collider, C2 collidee)");handler.handleCollision(collidee, collider); Collect.Hit("CollisionInteractionMap.java","handleCollision(C1 collider, C2 collidee)", "7017");}
	}

}
