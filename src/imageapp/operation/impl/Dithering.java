package imageapp.operation.impl;

import imageapp.image.Image;
import imageapp.operation.Operation;

/** Dithering is an implementation of Operation on image. */
public class Dithering implements Operation {
  /**
   * Dither an image.
   *
   * @param image Image object
   * @return newImage
   */
  public Image dithering(Image image) {
    // first apply greyscale
    GreyScale greyScale = new GreyScale();
    Image newImage = greyScale.apply(image);

    // second, dithering it
    int height = image.getHeight();
    int width = image.getWidth();
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        int[] ditheringColor = ditheringHelper(newImage, i, j);

        // on the right
        if (i >= 0 && i < width && j + 1 >= 0 && j + 1 < height) {
          newImage.setRed(i, j + 1, ditheringColor[0] + newImage.getRed(i, j + 1));
          newImage.setGreen(i, j + 1, ditheringColor[0] + newImage.getGreen(i, j + 1));
          newImage.setBlue(i, j + 1, ditheringColor[0] + newImage.getBlue(i, j + 1));
        }
        // on the next-row-left
        if (i + 1 >= 0 && i + 1 < width && j - 1 >= 0 && j - 1 < height) {
          newImage.setRed(i + 1, j - 1, ditheringColor[1] + newImage.getRed(i + 1, j - 1));
          newImage.setGreen(i + 1, j - 1, ditheringColor[1] + newImage.getGreen(i + 1, j - 1));
          newImage.setBlue(i + 1, j - 1, ditheringColor[1] + newImage.getBlue(i + 1, j - 1));
        }
        // below in next row
        if (i + 1 >= 0 && i + 1 < width && j >= 0 && j < height) {
          newImage.setRed(i + 1, j, ditheringColor[2] + newImage.getRed(i + 1, j));
          newImage.setGreen(i + 1, j, ditheringColor[2] + newImage.getGreen(i + 1, j));
          newImage.setBlue(i + 1, j, ditheringColor[2] + newImage.getBlue(i + 1, j));
        }
        // on the next-row-right
        if (i + 1 >= 0 && i + 1 < width && j + 1 >= 0 && j + 1 < height) {
          newImage.setRed(i + 1, j + 1, ditheringColor[3] + newImage.getRed(i + 1, j + 1));
          newImage.setGreen(i + 1, j + 1, ditheringColor[3] + newImage.getGreen(i + 1, j + 1));
          newImage.setBlue(i + 1, j + 1, ditheringColor[3] + newImage.getBlue(i + 1, j + 1));
        }
      }
    }
    return newImage;
  }

  /**
   * Dithering helper.
   *
   * @param newImage Image object
   * @param x coordinate
   * @param y coordinate
   * @return ditheringColor int array
   */
  private int[] ditheringHelper(Image newImage, int x, int y) {
    int[] ditheringColor = new int[4];
    int newColor = -1;
    int oldColor = newImage.getRed(x, y);

    int distance1 = Math.abs(0 - oldColor);
    int distance2 = Math.abs(255 - oldColor);
    if (distance1 <= distance2) {
      newColor = 0;
    } else {
      newColor = 255;
    }
    newImage.setBlue(x, y, newColor);
    newImage.setGreen(x, y, newColor);
    newImage.setRed(x, y, newColor);

    int error = oldColor - newColor;

    ditheringColor[0] = (int) (7 / 16.0 * error);
    ditheringColor[1] = (int) (3 / 16.0 * error);
    ditheringColor[2] = (int) (5 / 16.0 * error);
    ditheringColor[3] = (int) (1 / 16.0 * error);

    return ditheringColor;
  }

  @Override
  public Image apply(Object input) {
    Image newImage = ((Image) input).copy();
    newImage = dithering(newImage);
    return newImage;
  }
}
