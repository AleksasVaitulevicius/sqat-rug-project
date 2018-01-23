package nl.tudelft.jpacman.level;import coverageApi.Collect;import nl.tudelft.jpacman.board.Unit;
import nl.tudelft.jpacman.level.CollisionInteractionMap.CollisionHandler;
import nl.tudelft.jpacman.npc.ghost.Ghost; public class DefaultPlayerInteractionMap implements CollisionMap {

	private final CollisionMap collisions = defaultCollisions();

	@Override
	public void collide(Unit mover, Unit movedInto) {Collect.Hit("DefaultPlayerInteractionMap.java","collide(Unit mover, Unit movedInto)");collisions.collide(mover, movedInto); Collect.Hit("DefaultPlayerInteractionMap.java","collide(Unit mover, Unit movedInto)", "732");}

	/**
	 * Creates the default collisions Player-Ghost and Player-Pellet.
	 * 
	 * @return The collision map containing collisions for Player-Ghost and
	 *         Player-Pellet.
	 */
	private static CollisionInteractionMap defaultCollisions() {Collect.Hit("DefaultPlayerInteractionMap.java","defaultCollisions()");CollisionInteractionMap collisionMap = new CollisionInteractionMap(); Collect.Hit("DefaultPlayerInteractionMap.java","defaultCollisions()", "1030"); collisionMap.onCollision(Player.class, Ghost.class,
				new CollisionHandler<Player, Ghost>() {

					@Override
					public void handleCollision(Player player, Ghost ghost) {Collect.Hit("DefaultPlayerInteractionMap.java","handleCollision(Player player, Ghost ghost)");player.setAlive(false); Collect.Hit("DefaultPlayerInteractionMap.java","handleCollision(Player player, Ghost ghost)", "1291");}
				}); Collect.Hit("DefaultPlayerInteractionMap.java","defaultCollisions()", "1105"); collisionMap.onCollision(Player.class, Pellet.class,
				new CollisionHandler<Player, Pellet>() {

					@Override
					public void handleCollision(Player player, Pellet pellet) {Collect.Hit("DefaultPlayerInteractionMap.java","handleCollision(Player player, Pellet pellet)");pellet.leaveSquare(); Collect.Hit("DefaultPlayerInteractionMap.java","handleCollision(Player player, Pellet pellet)", "1527"); player.addPoints(pellet.getValue()); Collect.Hit("DefaultPlayerInteractionMap.java","handleCollision(Player player, Pellet pellet)", "1556");}
				}); Collect.Hit("DefaultPlayerInteractionMap.java","defaultCollisions()", "1337"); Collect.Hit("DefaultPlayerInteractionMap.java","defaultCollisions()", "1613");return collisionMap ; }
}
