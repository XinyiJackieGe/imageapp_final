package imageapp.operation.impl;

import imageapp.image.Image;
import imageapp.operation.Operation;

/** Sharpen is an implementation of Operation on image. */
public class Sharpen extends AbstractFilter implements Operation {
  /**
   * Sharpen an image.
   *
   * @param image input newImage
   */
  private void sharpen(Image image) {
    double[][] kernel =
        new double[][] {
          {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8},
          {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
          {-1.0 / 8, 1.0 / 4, 1.0, 1 / 4, -1.0 / 8},
          {-1.0 / 8, 1.0 / 4, 1.0 / 4, 1.0 / 4, -1.0 / 8},
          {-1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8, -1.0 / 8}
        };
    applyFilter(image, kernel);
  }

  @Override
  public Image apply(Object input) {
    Image newImage = ((Image) input).copy();
    sharpen(newImage);
    return newImage;
  }
}
