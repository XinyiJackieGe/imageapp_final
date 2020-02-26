package imageapp.operation.impl;

import java.awt.Color;
import imageapp.image.Image;
import imageapp.operation.Operation;

/** GenerateRainbowImage is an implementation of Operation on image. */
public class GenerateRainbowImage implements Operation {
  /** Params is a data container class to store parameters for generating rainbow image. */
  public static class Params {
    private final int stripeWidth;
    private final String direction;
    private final int width;
    private final int height;

    /**
     * Construct Params given parameters.
     *
     * @param stripeWidth stripe width input
     * @param direction "H" for horizontal and "V" for vertical
     * @param width image width
     * @param height image height
     */
    public Params(int stripeWidth, String direction, int width, int height) {
      this.stripeWidth = stripeWidth;
      this.direction = direction;
      this.width = width;
      this.height = height;
    }

    /**
     * Get stripeWidth.
     *
     * @return stripeWidth
     */
    public int getStripeWidth() {
      return stripeWidth;
    }

    /**
     * Get direction.
     *
     * @return direction
     */
    public String getDirection() {
      return direction;
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
      return generateRainbowImage(params);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  /**
   * Generate Rainbow stripes image.
   *
   * @param stripeAWidth width of a stripe
   * @param direction either "H" or "V"
   * @param imageWidth width of the created image
   * @param imageHeight height of the created image
   * @return new image
   */
  private Image generateRainbowImage(Params params) throws IllegalAccessException {
    int stripeWidth = params.getStripeWidth();
    String direction = params.getDirection();
    int width = params.getWidth();
    int height = params.getHeight();
    Image newImage = new Image(width, height);

    if (direction.equals("H")) {
      if (stripeWidth > width) {
        throw new IllegalAccessException("Stripe width is larger than image width!");
      }
      int loopCount = 0;
      for (int i = 0; i < width; i += stripeWidth) {
        Color color = getColor(loopCount);
        for (int m = 0; m < stripeWidth; m++) {
          for (int j = 0; j < height; j++) {
            newImage.setRed(j, i + m, color.getRed());
            newImage.setGreen(j, i + m, color.getGreen());
            newImage.setBlue(j, i + m, color.getBlue());
          }
        }
        loopCount++;
      }
    } else if (direction.equals("V")) {
      if (stripeWidth > height) {
        throw new IllegalAccessException("Stripe width is larger than image height!");
      }
      int loopCount = 0;
      for (int i = 0; i < height; i += stripeWidth) {
        Color color = getColor(loopCount);
        for (int m = 0; m < stripeWidth; m++) {
          for (int j = 0; j < width; j++) {
            newImage.setRed(i + m, j, color.getRed());
            newImage.setGreen(i + m, j, color.getGreen());
            newImage.setBlue(i + m, j, color.getBlue());
          }
        }
        loopCount++;
      }
    } else {
      throw new IllegalArgumentException("Direction could only be horizontal or vertical!");
    }

    return newImage;
  }

  /**
   * Get color given rainbow loop count.
   *
   * @param loopCount ranges from 0-6
   * @return Color
   */
  private Color getColor(int loopCount) {
    Color color = null;
    if (loopCount % 7 == 0) {
      color = Color.RED;
    } else if (loopCount % 7 == 1) {
      color = Color.ORANGE;
    } else if (loopCount % 7 == 2) {
      color = Color.YELLOW;
    } else if (loopCount % 7 == 3) {
      color = Color.GREEN;
    } else if (loopCount % 7 == 4) {
      color = Color.BLUE;
    } else if (loopCount % 7 == 5) {
      color = Color.CYAN;
    } else if (loopCount % 7 == 6) {
      color = Color.MAGENTA;
    }
    return color;
  }
}
