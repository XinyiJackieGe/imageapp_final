package imageapp.operation.impl;

import imageapp.image.Image;
import imageapp.operation.Operation;

/** GreyScale is an implementation of Operation on image. */
public class GreyScale extends AbstractTransformation implements Operation {
  /**
   * Adjust an image to grey scale.
   *
   * @param image Image object
   */
  private void greyScale(Image image) {
    double[][] transformationMat =
        new double[][] {
          {0.2126, 0.7152, 0.0722},
          {0.2126, 0.7152, 0.0722},
          {0.2126, 0.7152, 0.0722}
        };
    applyTransform(image, transformationMat);
  }

  @Override
  public Image apply(Object input) {
    Image newImage = ((Image) input).copy();
    greyScale(newImage);

    return newImage;
  }
}
