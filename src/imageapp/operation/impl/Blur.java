package imageapp.operation.impl;

import imageapp.image.Image;
import imageapp.operation.Operation;

/** Blur is an implementation of Operation on image. */
public class Blur extends AbstractFilter implements Operation {
  /**
   * Blur an image.
   *
   * @param image input newImage
   */
  private void blur(Image image) {
    double[][] kernel =
        new double[][] {
          {1.0 / 16, 1.0 / 8, 1.0 / 16},
          {1.0 / 8, 1.0 / 4, 1.0 / 8},
          {1.0 / 16, 1.0 / 8, 1.0 / 16}
        };
    applyFilter(image, kernel);
  }

  @Override
  public Image apply(Object input) {
    Image newImage = ((Image) input).copy();
    blur(newImage);
    return newImage;
  }
}
