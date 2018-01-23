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
	public Map<Direction, Sprite> getPacmanSprites() {Collect.Hit("PacManSprites.java","getPacmanSprites()"); Collect.Hit("PacManSprites.java","getPacmanSprites()", "1349");return directionSprite("/sprite/pacman.png", PACMAN_ANIMATION_FRAMES) ; }

	/**
	 * @return The animation of a dying Pac-Man.
	 */
	public AnimatedSprite getPacManDeathAnimation() {Collect.Hit("PacManSprites.java","getPacManDeathAnimation()");String resource = "/sprite/dead.png"; Collect.Hit("PacManSprites.java","getPacManDeathAnimation()", "1540"); Sprite baseImage = loadSprite(resource); Collect.Hit("PacManSprites.java","getPacManDeathAnimation()", "1583"); AnimatedSprite animation = createAnimatedSprite(baseImage, PACMAN_DEATH_FRAMES,
				ANIMATION_DELAY, false); Collect.Hit("PacManSprites.java","getPacManDeathAnimation()", "1627"); animation.setAnimating(false); Collect.Hit("PacManSprites.java","getPacManDeathAnimation()", "1740"); Collect.Hit("PacManSprites.java","getPacManDeathAnimation()", "1776");return animation ; }

	/**
	 * Returns a new map with animations for all directions.
	 * 
	 * @param resource
	 *            The resource name of the sprite.
	 * @param frames
	 *            The number of frames in this sprite.
	 * @return The animated sprite facing the given direction.
	 */
	private Map<Direction, Sprite> directionSprite(String resource, int frames) {Collect.Hit("PacManSprites.java","directionSprite(String resource, int frames)");Map<Direction, Sprite> sprite = new HashMap<>(); Collect.Hit("PacManSprites.java","directionSprite(String resource, int frames)", "2163"); Sprite baseImage = loadSprite(resource); Collect.Hit("PacManSprites.java","directionSprite(String resource, int frames)", "2217"); for (int i = 0; i < DIRECTIONS.length; i++) {
			Sprite directionSprite = baseImage.split(0, i * SPRITE_SIZE, frames
					* SPRITE_SIZE, SPRITE_SIZE);
			AnimatedSprite animation = createAnimatedSprite(directionSprite,
					frames, ANIMATION_DELAY, true);
			animation.setAnimating(true);
			sprite.put(DIRECTIONS[i], animation);
		} Collect.Hit("PacManSprites.java","directionSprite(String resource, int frames)", "2261"); Collect.Hit("PacManSprites.java","directionSprite(String resource, int frames)", "2607");return sprite ; }

	/**
	 * Returns a map of animated ghost sprites for all directions.
	 * 
	 * @param color
	 *            The colour of the ghost.
	 * @return The Sprite for the ghost.
	 */
	public Map<Direction, Sprite> getGhostSprite(GhostColor color) {Collect.Hit("PacManSprites.java","getGhostSprite(GhostColor color)");assert color != null; Collect.Hit("PacManSprites.java","getGhostSprite(GhostColor color)", "2879"); String resource = "/sprite/ghost_" + color.name().toLowerCase()
				+ ".png"; Collect.Hit("PacManSprites.java","getGhostSprite(GhostColor color)", "2906"); Collect.Hit("PacManSprites.java","getGhostSprite(GhostColor color)", "2988");return directionSprite(resource, GHOST_ANIMATION_FRAMES) ; }

	/**
	 * @return The sprite for the wall.
	 */
	public Sprite getWallSprite() {Collect.Hit("PacManSprites.java","getWallSprite()"); Collect.Hit("PacManSprites.java","getWallSprite()", "3139");return loadSprite("/sprite/wall.png") ; }

	/**
	 * @return The sprite for the ground.
	 */
	public Sprite getGroundSprite() {Collect.Hit("PacManSprites.java","getGroundSprite()"); Collect.Hit("PacManSprites.java","getGroundSprite()", "3275");return loadSprite("/sprite/floor.png") ; }

	/**
	 * @return The sprite for the
	 */
	public Sprite getPelletSprite() {Collect.Hit("PacManSprites.java","getPelletSprite()"); Collect.Hit("PacManSprites.java","getPelletSprite()", "3404");return loadSprite("/sprite/pellet.png") ; }

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
		} Collect.Hit("PacManSprites.java","loadSprite(String resource)", "3737");}
}
