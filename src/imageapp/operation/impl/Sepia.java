package imageapp.operation.impl;

import imageapp.image.Image;
import imageapp.operation.Operation;

/** Sepia is an implementation of Operation on image. */
public class Sepia extends AbstractTransformation implements Operation {
  /**
   * Adjust an image to Sepia tone.
   *
   * @param image Image object
   */
  private void sepia(Image image) {
    double[][] transformationMat =
        new double[][] {
          {0.393, 0.769, 0.189},
          {0.349, 0.686, 0.168},
          {0.272, 0.534, 0.131}
        };
    applyTransform(image, transformationMat);
  }

  @Override
  public Image apply(Object input) {
    Image newImage = ((Image) input).copy();
    sepia(newImage);
 
    return newImage;
  }
}
