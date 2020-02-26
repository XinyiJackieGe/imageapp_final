package imageapp.operation.impl;

import imageapp.image.Image;

/** AbstractFilter designs for Blur and Sharpen class to extend the command methods. */
public class AbstractFilter {
  /**
   * Filter an image.
   *
   * @param image given image to be filter
   * @param kernel for filter
   */
  protected void applyFilter(Image image, double[][] kernel) {
    int width = image.getWidth();
    int height = image.getHeight();
    int centerPos = kernel.length / 2;
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        int[] rGB = filterHelper(image, kernel, centerPos, i, j);
        image.setRed(i, j, rGB[0]);
        image.setGreen(i, j, rGB[1]);
        image.setBlue(i, j, rGB[2]);
      }
    }
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

  /**
   * Help to filter.
   *
   * @param image given image to be filter
   * @param kernel for filter
   * @param centerPos position at center of the image
   * @param x coordinate
   * @param y coordinate
   * @return
   */
  private int[] filterHelper(Image image, double[][] kernel, int centerPos, int x, int y) {
    double sumRed = 0.0;
    double sumGreen = 0.0;
    double sumBlue = 0.0;
    for (int i = -centerPos; i < centerPos + 1; i++) {
      for (int j = -centerPos; j < centerPos + 1; j++) {
        if ((x + i >= 0)
            && (x + i < image.getWidth())
            && (y + j >= 0)
            && (y + j < image.getHeight())) {
          int red = image.getRed(x + i, y + j);
          int green = image.getGreen(x + i, y + j);
          int blue = image.getBlue(x + i, y + j);
          sumRed += (red * kernel[centerPos + i][centerPos + j]);
          sumGreen += (green * kernel[centerPos + i][centerPos + j]);
          sumBlue += (blue * kernel[centerPos + i][centerPos + j]);
        }
      }
    }
    int[] rGB = new int[] {clamp((int) sumRed), clamp((int) sumGreen), clamp((int) sumBlue)};
    return rGB;
  }
}
