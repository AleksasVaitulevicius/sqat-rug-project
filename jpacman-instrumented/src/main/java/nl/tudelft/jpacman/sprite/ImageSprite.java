package nl.tudelft.jpacman.sprite;import coverageApi.Collect;import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage; public class ImageSprite implements Sprite {

	/**
	 * Internal image.
	 */
	private final Image image;

	/**
	 * Creates a new sprite from an image.
	 * 
	 * @param img
	 *            The image to create a sprite from.
	 */
	public ImageSprite(Image img) {Collect.Hit("ImageSprite.java","ImageSprite(Image img)");this.image = img; Collect.Hit("ImageSprite.java","ImageSprite(Image img)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/ImageSprite.java|(630,17,<29,2>,<29,19>)");}

	@Override
	public void draw(Graphics g, int x, int y, int width, int height) {Collect.Hit("ImageSprite.java","draw(Graphics g, int x, int y, int width, int height)");g.drawImage(image, x, y, x + width, y + height, 0, 0,
				image.getWidth(null), image.getHeight(null), null); Collect.Hit("ImageSprite.java","draw(Graphics g, int x, int y, int width, int height)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/ImageSprite.java|(739,110,<34,2>,<35,55>)");}

	@Override
	public Sprite split(int x, int y, int width, int height) {Collect.Hit("ImageSprite.java","split(int x, int y, int width, int height)");if (withinImage(x, y) && withinImage(x + width - 1, y + height - 1)) {
			BufferedImage newImage = newImage(width, height);
			newImage.createGraphics().drawImage(image, 0, 0, width, height, x,
					y, x + width, y + height, null);
			return new ImageSprite(newImage);
		} Collect.Hit("ImageSprite.java","split(int x, int y, int width, int height)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/ImageSprite.java|(932,277,<40,2>,<45,3>)"); return new EmptySprite(); Collect.Hit("ImageSprite.java","split(int x, int y, int width, int height)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/ImageSprite.java|(1213,25,<46,2>,<46,27>)");}

	private boolean withinImage(int x, int y) {Collect.Hit("ImageSprite.java","withinImage(int x, int y)");return x < image.getWidth(null) && x >= 0 && y < image.getHeight(null)
				&& y >= 0; Collect.Hit("ImageSprite.java","withinImage(int x, int y)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/ImageSprite.java|(1294,86,<50,2>,<51,14>)");}

	/**
	 * Creates a new, empty image of the given width and height. Its
	 * transparency will be a bitmask, so no try ARGB image.
	 * 
	 * @param width
	 *            The width of the new image.
	 * @param height
	 *            The height of the new image.
	 * @return The new, empty image.
	 */
	private BufferedImage newImage(int width, int height) {Collect.Hit("ImageSprite.java","newImage(int width, int height)");GraphicsConfiguration gc = GraphicsEnvironment
				.getLocalGraphicsEnvironment().getDefaultScreenDevice()
				.getDefaultConfiguration(); Collect.Hit("ImageSprite.java","newImage(int width, int height)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/ImageSprite.java|(1753,140,<65,2>,<67,31>)"); return gc.createCompatibleImage(width, height, Transparency.BITMASK); Collect.Hit("ImageSprite.java","newImage(int width, int height)", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/ImageSprite.java|(1897,69,<68,2>,<68,71>)");}

	@Override
	public int getWidth() {Collect.Hit("ImageSprite.java","getWidth()");return image.getWidth(null); Collect.Hit("ImageSprite.java","getWidth()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/ImageSprite.java|(2014,28,<73,2>,<73,30>)");}

	@Override
	public int getHeight() {Collect.Hit("ImageSprite.java","getHeight()");return image.getHeight(null); Collect.Hit("ImageSprite.java","getHeight()", "|project://jpacman-framework/src/main/java/nl/tudelft/jpacman/sprite/ImageSprite.java|(2091,29,<78,2>,<78,31>)");}

}
