package imageapp.image;

import java.awt.image.BufferedImage;

/**
 * Image class that converts BufferedImage to self-created image class, and do operations related to
 * an image.
 */
public class Image {
  private final int width;
  private final int height;
  private int[][] redPixels;
  private int[][] greenPixels;
  private int[][] bluePixels;

  /**
   * Construct an Image with BufferedImage.
   *
   * @param image BufferedImage
   */
  public Image(BufferedImage image) {
    this.width = image.getWidth();
    this.height = image.getHeight();
    this.redPixels = new int[width][height];
    this.greenPixels = new int[width][height];
    this.bluePixels = new int[width][height];
    fillChannels(image);
  }

  /**
   * Construct an empty Image with width and height.
   *
   * @param width set image width
   * @param height set image height
   */
  public Image(int width, int height) {
    this.width = width;
    this.height = height;
    this.redPixels = new int[width][height];
    this.greenPixels = new int[width][height];
    this.bluePixels = new int[width][height];
  }

  /**
   * Fill channels for Image.
   *
   * @param image BufferedImage
   */
  private void fillChannels(BufferedImage image) {
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        int rgb = image.getRGB(i, j);
        redPixels[i][j] = (rgb >> 16) & 0xff;
        greenPixels[i][j] = (rgb >> 8) & 0xff;
        bluePixels[i][j] = rgb & 0xff;
      }
    }
  }

  /**
   * Copy this image.
   *
   * @return copied image
   */
  public Image copy() {
    Image copiedImage = new Image(width, height);
    copiedImage.redPixels = this.redPixels;
    copiedImage.greenPixels = this.greenPixels;
    copiedImage.bluePixels = this.bluePixels;
    return copiedImage;
  }

  /**
   * Get width.
   *
   * @return width
   */
  public int getWidth() {
    return width;
  }

  /**
   * Get height.
   *
   * @return height
   */
  public int getHeight() {
    return height;
  }

  /**
   * Get red rgb.
   *
   * @param row image row
   * @param col image column
   * @return red rgb
   */
  public int getRed(int row, int col) {
    return redPixels[row][col];
  }

  /**
   * Get green rgb.
   *
   * @param row image row
   * @param col image column
   * @return green rgb
   */
  public int getGreen(int row, int col) {
    return greenPixels[row][col];
  }

  /**
   * Get blue rgb.
   *
   * @param row image row
   * @param col image column
   * @return blue rgb
   */
  public int getBlue(int row, int col) {
    return bluePixels[row][col];
  }

  /**
   * Set red rgb.
   *
   * @param row image row
   * @param col image column
   */
  public void setRed(int row, int col, int val) {
    int color = clamp(val);
    redPixels[row][col] = color;
  }

  /**
   * Set red rgb.
   *
   * @param row image row
   * @param col image column
   */
  public void setGreen(int row, int col, int val) {
    int color = clamp(val);
    greenPixels[row][col] = color;
  }

  /**
   * Set red rgb.
   *
   * @param row image row
   * @param col image column
   */
  public void setBlue(int row, int col, int val) {
    int color = clamp(val);
    bluePixels[row][col] = color;
  }

  /**
   * Convert Image to an BufferedImage.
   *
   * @return BufferedImage
   */
  public BufferedImage convertBufferedImage() {
    // Create an object of BufferedImage type and pass as parameter the width, height and
    // image type.TYPE_INT_RGB means that we are representing with 8-bit RGB color components
    // packed into integer pixels.
    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        int rgb = (redPixels[i][j] << 16) | (greenPixels[i][j] << 8) | bluePixels[i][j];
        bufferedImage.setRGB(i, j, rgb);
      }
    }
    return bufferedImage;
  }

  /**
   * Clamp.
   *
   * @param color rgb value of a channel
   * @return color rgb within range
   */
  private int clamp(int color) {
    color = (color <= 255) ? color : 255;
    color = (color >= 0) ? color : 0;
    return color;
  }
}
