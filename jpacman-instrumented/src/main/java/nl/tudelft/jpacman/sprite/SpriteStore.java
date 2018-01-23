package nl.tudelft.jpacman.sprite;import coverageApi.Collect;import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO; public class SpriteStore {
	
	/**
	 * We only need to load images once, so we keep track
	 * of them in a hash map.
	 */
	private final Map<String, Sprite> spriteMap;
	
	/**
	 * Create a new sprite store.
	 */
	public SpriteStore() {Collect.Hit("SpriteStore.java","SpriteStore()");spriteMap = new HashMap<>(); Collect.Hit("SpriteStore.java","SpriteStore()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/SpriteStore.java|(546,28,<28,2>,<28,30>)");}
	
	/**
	 * Loads a sprite from a resource on the class path.
	 * Sprites are loaded once, and then stored in the store
	 * so that they can be efficiently retrieved.
	 * 
	 * @param resource
	 *            The resource path.
	 * @return The sprite for the resource.
	 * @throws IOException
	 *             When the resource could not be loaded.
	 */
	public Sprite loadSprite(String resource) throws IOException {Collect.Hit("SpriteStore.java","loadSprite(String resource)");Sprite result = spriteMap.get(resource); Collect.Hit("SpriteStore.java","loadSprite(String resource)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/SpriteStore.java|(1009,40,<43,2>,<43,42>)"); if (result == null) {
			result = loadSpriteFromResource(resource);
			spriteMap.put(resource, result);
		} Collect.Hit("SpriteStore.java","loadSprite(String resource)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/SpriteStore.java|(1053,110,<44,2>,<47,3>)"); return result; Collect.Hit("SpriteStore.java","loadSprite(String resource)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/SpriteStore.java|(1167,14,<48,2>,<48,16>)");}

	/**
	 * Loads a sprite from a resource on the class path.
	 * 
	 * @param resource
	 *            The resource path.
	 * @return A new sprite for the resource.
	 * @throws IOException
	 *             When the resource could not be loaded.
	 */
	private Sprite loadSpriteFromResource(String resource) throws IOException {Collect.Hit("SpriteStore.java","loadSpriteFromResource(String resource)");try (InputStream input = SpriteStore.class.getResourceAsStream(resource)) {
			if (input == null) {
				throw new IOException("Unable to load " + resource
					+ ", resource does not exist.");
			}
			BufferedImage image = ImageIO.read(input);
			return new ImageSprite(image);
		} Collect.Hit("SpriteStore.java","loadSpriteFromResource(String resource)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/SpriteStore.java|(1523,288,<61,2>,<68,3>)");}

	/**
	 * Creates a new {@link AnimatedSprite} from a base image.
	 * 
	 * @param baseImage
	 *            The base image to convert into an animation.
	 * @param frames
	 *            The amount of frames of the animation.
	 * @param delay
	 *            The delay between frames.
	 * @param loop
	 *            Whether this sprite is a looping animation or not.
	 * @return The animated sprite.
	 */
	public AnimatedSprite createAnimatedSprite(Sprite baseImage, int frames, int delay, boolean loop) {Collect.Hit("SpriteStore.java","createAnimatedSprite(Sprite baseImage, int frames, int delay, boolean loop)");assert baseImage != null; Collect.Hit("SpriteStore.java","createAnimatedSprite(Sprite baseImage, int frames, int delay, boolean loop)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/SpriteStore.java|(2337,25,<85,2>,<85,27>)"); assert frames > 0; Collect.Hit("SpriteStore.java","createAnimatedSprite(Sprite baseImage, int frames, int delay, boolean loop)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/SpriteStore.java|(2366,18,<86,2>,<86,20>)"); int frameWidth = baseImage.getWidth() / frames; Collect.Hit("SpriteStore.java","createAnimatedSprite(Sprite baseImage, int frames, int delay, boolean loop)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/SpriteStore.java|(2390,47,<88,2>,<88,49>)"); Sprite[] animation = new Sprite[frames]; Collect.Hit("SpriteStore.java","createAnimatedSprite(Sprite baseImage, int frames, int delay, boolean loop)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/SpriteStore.java|(2443,40,<90,2>,<90,42>)"); for (int i = 0; i < frames; i++) {
			animation[i] = baseImage.split(i * frameWidth, 0, frameWidth,
					baseImage.getHeight());
		} Collect.Hit("SpriteStore.java","createAnimatedSprite(Sprite baseImage, int frames, int delay, boolean loop)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/SpriteStore.java|(2487,135,<91,2>,<94,3>)"); return new AnimatedSprite(animation, delay, loop); Collect.Hit("SpriteStore.java","createAnimatedSprite(Sprite baseImage, int frames, int delay, boolean loop)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/SpriteStore.java|(2628,50,<96,2>,<96,52>)");}

}
