package imageapp.operation.impl;

import imageapp.image.Image;

/** AbstractTransformation designs for GreySacle and Sepia to extends common methods. */
public class AbstractTransformation {
  /**
   * Transform color.
   *
   * @param image to be transformed.
   * @param transformationMat transformation matrix
   */
  protected void applyTransform(Image image, double[][] transformationMat) {
    for (int i = 0; i < image.getWidth(); i++) {
      for (int j = 0; j < image.getHeight(); j++) {
        int[] rGB = new int[] {image.getRed(i, j), image.getGreen(i, j), image.getBlue(i, j)};
        rGB = transformHelper(transformationMat, rGB);
        rGB[0] = clamp(rGB[0]);
        rGB[1] = clamp(rGB[1]);
        rGB[2] = clamp(rGB[2]);
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
   * Help to transform color.
   *
   * @param tranformationMat transformation matrix
   * @param rGB rgb color array
   * @return new rgb color array
   */
  private int[] transformHelper(double[][] tranformationMat, int[] rGB) {
    int[] rGBNew = new int[3];
    for (int i = 0; i < rGB.length; i++) {
      for (int j = 0; j < tranformationMat[0].length; j++) { // tranformationMat size
        rGBNew[i] += tranformationMat[i][j] * rGB[j];
      }
    }

    return rGBNew;
  }
}
