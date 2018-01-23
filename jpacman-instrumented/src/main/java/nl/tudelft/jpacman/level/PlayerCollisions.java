package nl.tudelft.jpacman.level;import coverageApi.Collect;import nl.tudelft.jpacman.board.Unit;
import nl.tudelft.jpacman.npc.ghost.Ghost; public class PlayerCollisions implements CollisionMap {

	@Override
	public void collide(Unit mover, Unit collidedOn) {Collect.Hit("PlayerCollisions.java","collide(Unit mover, Unit collidedOn)");if (mover instanceof Player) {
			playerColliding((Player) mover, collidedOn);
		}
		else if (mover instanceof Ghost) {
			ghostColliding((Ghost) mover, collidedOn);
		}
		else if (mover instanceof Pellet) {
			pelletColliding((Pellet) mover, collidedOn);
		} Collect.Hit("PlayerCollisions.java","collide(Unit mover, Unit collidedOn)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/PlayerCollisions.java|(634,267,<22,2>,<30,3>)");}
	
	private void playerColliding(Player player, Unit collidedOn) {Collect.Hit("PlayerCollisions.java","playerColliding(Player player, Unit collidedOn)");if (collidedOn instanceof Ghost) {
			playerVersusGhost(player, (Ghost) collidedOn);
		} Collect.Hit("PlayerCollisions.java","playerColliding(Player player, Unit collidedOn)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/PlayerCollisions.java|(977,90,<34,2>,<36,3>)"); if (collidedOn instanceof Pellet) {
			playerVersusPellet(player, (Pellet) collidedOn);
		} Collect.Hit("PlayerCollisions.java","playerColliding(Player player, Unit collidedOn)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/PlayerCollisions.java|(1075,93,<38,2>,<40,3>)");}
	
	private void ghostColliding(Ghost ghost, Unit collidedOn) {Collect.Hit("PlayerCollisions.java","ghostColliding(Ghost ghost, Unit collidedOn)");if (collidedOn instanceof Player) {
			playerVersusGhost((Player) collidedOn, ghost);
		} Collect.Hit("PlayerCollisions.java","ghostColliding(Ghost ghost, Unit collidedOn)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/PlayerCollisions.java|(1243,91,<44,2>,<46,3>)");}
	
	private void pelletColliding(Pellet pellet, Unit collidedOn) {Collect.Hit("PlayerCollisions.java","pelletColliding(Pellet pellet, Unit collidedOn)");if (collidedOn instanceof Player) {
			playerVersusPellet((Player) collidedOn, pellet);
		} Collect.Hit("PlayerCollisions.java","pelletColliding(Pellet pellet, Unit collidedOn)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/PlayerCollisions.java|(1410,93,<50,2>,<52,3>)");}
	
	
	/**
	 * Actual case of player bumping into ghost or vice versa.
     *
     * @param player The player involved in the collision.
     * @param ghost The ghost involved in the collision.
	 */
	public void playerVersusGhost(Player player, Ghost ghost) {Collect.Hit("PlayerCollisions.java","playerVersusGhost(Player player, Ghost ghost)");player.setAlive(false); Collect.Hit("PlayerCollisions.java","playerVersusGhost(Player player, Ghost ghost)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/PlayerCollisions.java|(1780,23,<63,2>,<63,25>)");}
	
	/**
	 * Actual case of player consuming a pellet.
     *
     * @param player The player involved in the collision.
     * @param pellet The pellet involved in the collision.
	 */
	public void playerVersusPellet(Player player, Pellet pellet) {Collect.Hit("PlayerCollisions.java","playerVersusPellet(Player player, Pellet pellet)");pellet.leaveSquare(); Collect.Hit("PlayerCollisions.java","playerVersusPellet(Player player, Pellet pellet)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/PlayerCollisions.java|(2066,21,<73,2>,<73,23>)"); player.addPoints(pellet.getValue()); Collect.Hit("PlayerCollisions.java","playerVersusPellet(Player player, Pellet pellet)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/level/PlayerCollisions.java|(2091,36,<74,2>,<74,38>)");}

}
