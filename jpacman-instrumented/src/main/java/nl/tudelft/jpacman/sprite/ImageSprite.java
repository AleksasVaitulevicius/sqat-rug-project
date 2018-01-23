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
	public ImageSprite(Image img) {Collect.Hit("ImageSprite.java","ImageSprite(Image img)");this.image = img; Collect.Hit("ImageSprite.java","ImageSprite(Image img)", "630");}

	@Override
	public void draw(Graphics g, int x, int y, int width, int height) {Collect.Hit("ImageSprite.java","draw(Graphics g, int x, int y, int width, int height)");g.drawImage(image, x, y, x + width, y + height, 0, 0,
				image.getWidth(null), image.getHeight(null), null); Collect.Hit("ImageSprite.java","draw(Graphics g, int x, int y, int width, int height)", "739");}

	@Override
	public Sprite split(int x, int y, int width, int height) {Collect.Hit("ImageSprite.java","split(int x, int y, int width, int height)");if (withinImage(x, y) && withinImage(x + width - 1, y + height - 1)) {
			BufferedImage newImage = newImage(width, height);
			newImage.createGraphics().drawImage(image, 0, 0, width, height, x,
					y, x + width, y + height, null);
			return new ImageSprite(newImage);
		} Collect.Hit("ImageSprite.java","split(int x, int y, int width, int height)", "932"); Collect.Hit("ImageSprite.java","split(int x, int y, int width, int height)", "1213");return new EmptySprite() ; }

	private boolean withinImage(int x, int y) {Collect.Hit("ImageSprite.java","withinImage(int x, int y)"); Collect.Hit("ImageSprite.java","withinImage(int x, int y)", "1294");return x < image.getWidth(null) && x >= 0 && y < image.getHeight(null)
				&& y >= 0 ; }

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
				.getDefaultConfiguration(); Collect.Hit("ImageSprite.java","newImage(int width, int height)", "1753"); Collect.Hit("ImageSprite.java","newImage(int width, int height)", "1897");return gc.createCompatibleImage(width, height, Transparency.BITMASK) ; }

	@Override
	public int getWidth() {Collect.Hit("ImageSprite.java","getWidth()"); Collect.Hit("ImageSprite.java","getWidth()", "2014");return image.getWidth(null) ; }

	@Override
	public int getHeight() {Collect.Hit("ImageSprite.java","getHeight()"); Collect.Hit("ImageSprite.java","getHeight()", "2091");return image.getHeight(null) ; }

}
