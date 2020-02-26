package imageapp.operation.impl;

import imageapp.image.Image;
import imageapp.operation.Operation;

/** GenerateCheckBoardImage is an implementation of Operation on image. */
public class GenerateCheckBoardImage implements Operation {
  /** Params is a data container class to store parameters for generating check board image. */
  public static class Params {
    private final int square;
    private final int width;
    private final int height;

    /**
     * Construct Params given parameters.
     *
     * @param square square size
     * @param width image width
     * @param height image height
     */
    public Params(int square, int width, int height) {
      this.square = square;
      this.width = width;
      this.height = height;
    }

    /**
     * Get square size.
     *
     * @return square
     */
    public int getSquare() {
      return square;
    }

    /**
     * Get image width.
     *
     * @return width
     */
    public int getWidth() {
      return width;
    }

    /**
     * Get image height.
     *
     * @return height
     */
    public int getHeight() {
      return height;
    }
  }

  @Override
  public Image apply(Object input) {
    Params params = (Params) input;
    try {
      return generateCheckBoardImage(params);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  /**
   * Generate Check board image.
   *
   * @param width width of the created image
   * @param height height of the created image
   * @param square the size of a square
   * @return new image
   */
  private Image generateCheckBoardImage(Params params) throws IllegalAccessException {
    int square = params.getSquare();
    int width = params.getWidth();
    int height = params.getHeight();
    Image newImage = new Image(width, height);

    if (square > Math.min(width, height)) {
      throw new IllegalAccessException("Square size in incorrect. Please reduce its value!");
    }
    if (width % square != 0 || height % square != 0) {
      throw new IllegalAccessException(
          "Square size in incorrect. " + "Please let width and height be divisible by square!");
    }
    int[] rGb;
    for (int i = 0; i < width; i += square) {
      for (int j = 0; j < height; j += square) {
        if ((i + j) % 2 == 0) {
          rGb = new int[] {255, 255, 255};
        } else {
          rGb = new int[] {0, 0, 0};
        }
        for (int m = 0; m < square; m++) {
          for (int n = 0; n < square; n++) {
            newImage.setRed(i + m, j + n, rGb[0]);
            newImage.setGreen(i + m, j + n, rGb[1]);
            newImage.setBlue(i + m, j + n, rGb[2]);
          }
        }
      }
    }
    return newImage;
  }
}
