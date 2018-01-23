package nl.tudelft.jpacman.sprite;import coverageApi.Collect;import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import nl.tudelft.jpacman.PacmanConfigurationException;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.npc.ghost.GhostColor; public class PacManSprites extends SpriteStore {

	/**
	 * The sprite files are vertically stacked series for each direction, this
	 * array denotes the order.
	 */
	private static final Direction[] DIRECTIONS = { Direction.NORTH,
			Direction.EAST, Direction.SOUTH, Direction.WEST };

	/**
	 * The image size in pixels.
	 */
	private static final int SPRITE_SIZE = 16;

	/**
	 * The amount of frames in the pacman animation.
	 */
	private static final int PACMAN_ANIMATION_FRAMES = 4;

	/**
	 * The amount of frames in the pacman dying animation.
	 */
	private static final int PACMAN_DEATH_FRAMES = 11;
	
	/**
	 * The amount of frames in the ghost animation.
	 */
	private static final int GHOST_ANIMATION_FRAMES = 2;

	/**
	 * The delay between frames.
	 */
	private static final int ANIMATION_DELAY = 200;

	/**
	 * @return A map of animated Pac-Man sprites for all directions.
	 */
	public Map<Direction, Sprite> getPacmanSprites() {Collect.Hit("PacManSprites.java","getPacmanSprites()");return directionSprite("/sprite/pacman.png", PACMAN_ANIMATION_FRAMES); Collect.Hit("PacManSprites.java","getPacmanSprites()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/PacManSprites.java|(1349,70,<54,2>,<54,72>)");}

	/**
	 * @return The animation of a dying Pac-Man.
	 */
	public AnimatedSprite getPacManDeathAnimation() {Collect.Hit("PacManSprites.java","getPacManDeathAnimation()");String resource = "/sprite/dead.png"; Collect.Hit("PacManSprites.java","getPacManDeathAnimation()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/PacManSprites.java|(1540,37,<61,2>,<61,39>)"); Sprite baseImage = loadSprite(resource); Collect.Hit("PacManSprites.java","getPacManDeathAnimation()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/PacManSprites.java|(1583,40,<63,2>,<63,42>)"); AnimatedSprite animation = createAnimatedSprite(baseImage, PACMAN_DEATH_FRAMES,
				ANIMATION_DELAY, false); Collect.Hit("PacManSprites.java","getPacManDeathAnimation()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/PacManSprites.java|(1627,109,<64,2>,<65,28>)"); animation.setAnimating(false); Collect.Hit("PacManSprites.java","getPacManDeathAnimation()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/PacManSprites.java|(1740,30,<66,2>,<66,32>)"); return animation; Collect.Hit("PacManSprites.java","getPacManDeathAnimation()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/PacManSprites.java|(1776,17,<68,2>,<68,19>)");}

	/**
	 * Returns a new map with animations for all directions.
	 * 
	 * @param resource
	 *            The resource name of the sprite.
	 * @param frames
	 *            The number of frames in this sprite.
	 * @return The animated sprite facing the given direction.
	 */
	private Map<Direction, Sprite> directionSprite(String resource, int frames) {Collect.Hit("PacManSprites.java","directionSprite(String resource, int frames)");Map<Direction, Sprite> sprite = new HashMap<>(); Collect.Hit("PacManSprites.java","directionSprite(String resource, int frames)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/PacManSprites.java|(2163,48,<81,2>,<81,50>)"); Sprite baseImage = loadSprite(resource); Collect.Hit("PacManSprites.java","directionSprite(String resource, int frames)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/PacManSprites.java|(2217,40,<83,2>,<83,42>)"); for (int i = 0; i < DIRECTIONS.length; i++) {
			Sprite directionSprite = baseImage.split(0, i * SPRITE_SIZE, frames
					* SPRITE_SIZE, SPRITE_SIZE);
			AnimatedSprite animation = createAnimatedSprite(directionSprite,
					frames, ANIMATION_DELAY, true);
			animation.setAnimating(true);
			sprite.put(DIRECTIONS[i], animation);
		} Collect.Hit("PacManSprites.java","directionSprite(String resource, int frames)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/PacManSprites.java|(2261,340,<84,2>,<91,3>)"); return sprite; Collect.Hit("PacManSprites.java","directionSprite(String resource, int frames)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/PacManSprites.java|(2607,14,<93,2>,<93,16>)");}

	/**
	 * Returns a map of animated ghost sprites for all directions.
	 * 
	 * @param color
	 *            The colour of the ghost.
	 * @return The Sprite for the ghost.
	 */
	public Map<Direction, Sprite> getGhostSprite(GhostColor color) {Collect.Hit("PacManSprites.java","getGhostSprite(GhostColor color)");assert color != null; Collect.Hit("PacManSprites.java","getGhostSprite(GhostColor color)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/PacManSprites.java|(2879,21,<104,2>,<104,23>)"); String resource = "/sprite/ghost_" + color.name().toLowerCase()
				+ ".png"; Collect.Hit("PacManSprites.java","getGhostSprite(GhostColor color)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/PacManSprites.java|(2906,78,<106,2>,<107,13>)"); return directionSprite(resource, GHOST_ANIMATION_FRAMES); Collect.Hit("PacManSprites.java","getGhostSprite(GhostColor color)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/PacManSprites.java|(2988,57,<108,2>,<108,59>)");}

	/**
	 * @return The sprite for the wall.
	 */
	public Sprite getWallSprite() {Collect.Hit("PacManSprites.java","getWallSprite()");return loadSprite("/sprite/wall.png"); Collect.Hit("PacManSprites.java","getWallSprite()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/PacManSprites.java|(3139,38,<115,2>,<115,40>)");}

	/**
	 * @return The sprite for the ground.
	 */
	public Sprite getGroundSprite() {Collect.Hit("PacManSprites.java","getGroundSprite()");return loadSprite("/sprite/floor.png"); Collect.Hit("PacManSprites.java","getGroundSprite()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/PacManSprites.java|(3275,39,<122,2>,<122,41>)");}

	/**
	 * @return The sprite for the
	 */
	public Sprite getPelletSprite() {Collect.Hit("PacManSprites.java","getPelletSprite()");return loadSprite("/sprite/pellet.png"); Collect.Hit("PacManSprites.java","getPelletSprite()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/PacManSprites.java|(3404,40,<129,2>,<129,42>)");}

	/**
	 * Overloads the default sprite loading, ignoring the exception. This class
	 * assumes all sprites are provided, hence the exception will be thrown as a
	 * {@link RuntimeException}.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public Sprite loadSprite(String resource) {Collect.Hit("PacManSprites.java","loadSprite(String resource)");try {
			return super.loadSprite(resource);
		} catch (IOException e) {
			throw new PacmanConfigurationException("Unable to load sprite: " + resource, e);
		} Collect.Hit("PacManSprites.java","loadSprite(String resource)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/PacManSprites.java|(3737,163,<141,2>,<145,3>)");}
}
