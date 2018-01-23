package nl.tudelft.jpacman.level;import coverageApi.Collect;import nl.tudelft.jpacman.board.Unit;
import nl.tudelft.jpacman.level.CollisionInteractionMap.CollisionHandler;
import nl.tudelft.jpacman.npc.ghost.Ghost; public class DefaultPlayerInteractionMap implements CollisionMap {

	private final CollisionMap collisions = defaultCollisions();

	@Override
	public void collide(Unit mover, Unit movedInto) {Collect.Hit("DefaultPlayerInteractionMap.java","collide(Unit mover, Unit movedInto)");collisions.collide(mover, movedInto); Collect.Hit("DefaultPlayerInteractionMap.java","collide(Unit mover, Unit movedInto)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/DefaultPlayerInteractionMap.java|(732,37,<24,2>,<24,39>)");}

	/**
	 * Creates the default collisions Player-Ghost and Player-Pellet.
	 * 
	 * @return The collision map containing collisions for Player-Ghost and
	 *         Player-Pellet.
	 */
	private static CollisionInteractionMap defaultCollisions() {Collect.Hit("DefaultPlayerInteractionMap.java","defaultCollisions()");CollisionInteractionMap collisionMap = new CollisionInteractionMap(); Collect.Hit("DefaultPlayerInteractionMap.java","defaultCollisions()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/DefaultPlayerInteractionMap.java|(1030,69,<34,2>,<34,71>)"); collisionMap.onCollision(Player.class, Ghost.class,
				new CollisionHandler<Player, Ghost>() {

					@Override
					public void handleCollision(Player player, Ghost ghost) {Collect.Hit("DefaultPlayerInteractionMap.java","handleCollision(Player player, Ghost ghost)");player.setAlive(false); Collect.Hit("DefaultPlayerInteractionMap.java","handleCollision(Player player, Ghost ghost)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/DefaultPlayerInteractionMap.java|(1291,23,<41,6>,<41,29>)");}
				}); Collect.Hit("DefaultPlayerInteractionMap.java","defaultCollisions()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/DefaultPlayerInteractionMap.java|(1105,226,<36,2>,<43,7>)"); collisionMap.onCollision(Player.class, Pellet.class,
				new CollisionHandler<Player, Pellet>() {

					@Override
					public void handleCollision(Player player, Pellet pellet) {Collect.Hit("DefaultPlayerInteractionMap.java","handleCollision(Player player, Pellet pellet)");pellet.leaveSquare(); Collect.Hit("DefaultPlayerInteractionMap.java","handleCollision(Player player, Pellet pellet)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/DefaultPlayerInteractionMap.java|(1527,21,<50,6>,<50,27>)"); player.addPoints(pellet.getValue()); Collect.Hit("DefaultPlayerInteractionMap.java","handleCollision(Player player, Pellet pellet)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/DefaultPlayerInteractionMap.java|(1556,36,<51,6>,<51,42>)");}
				}); Collect.Hit("DefaultPlayerInteractionMap.java","defaultCollisions()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/DefaultPlayerInteractionMap.java|(1337,272,<45,2>,<53,7>)"); return collisionMap; Collect.Hit("DefaultPlayerInteractionMap.java","defaultCollisions()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/DefaultPlayerInteractionMap.java|(1613,20,<54,2>,<54,22>)");}
}
