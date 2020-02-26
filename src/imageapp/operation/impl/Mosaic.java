package imageapp.operation.impl;

import java.util.Random;
import imageapp.image.Image;
import imageapp.operation.Operation;

/** Mosaic is an implementation of Operation on image. */
public class Mosaic implements Operation {
  /**
   * Create random pi
   * @param width
   * @param height
   * @return
   */
  private int[] createRandomPixels(int width, int height) {
    Random rand = new Random();
    int x = rand.nextInt(width);
    int y = rand.nextInt(height);
    return new int[] {x, y};
  }

  /**
   * Compute Euclidean distance between two pixels.
   *
   * @param pixelOne corrdinates int array
   * @param pixelTwo corrdinates int array
   * @return Euclidean distance
   */
  private double distance(int[] pixelOne, int[] pixelTwo) {
    return Math.sqrt(
        Math.pow((pixelOne[1] - pixelTwo[1]), 2) + Math.pow((pixelOne[0] - pixelTwo[0]), 2));
  }

  /**
   * Assign channel values.
   *
   * @param width image width
   * @param height image height
   * @param centroids random created centroids
   * @param image input image
   */
  private void assignChannelVals(int width, int height, int[][] centroids, int rand_num, Image image) {
    double max = Double.MAX_VALUE;
    double min = max;
    double distance = 0.0;

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        min = max;
        int idx = -1;
        for (int k = 0; k < rand_num; k++) {
          int[] centroid = centroids[k];
          distance = distance(new int[] {i, j}, centroid);
          if (distance < min) {
            min = distance;
            idx = k;
          }
        }

        int red = image.getRed(centroids[idx][0], centroids[idx][1]);
        int green = image.getGreen(centroids[idx][0], centroids[idx][1]);
        int blue = image.getBlue(centroids[idx][0], centroids[idx][1]);
        image.setRed(i, j, red);
        image.setGreen(i, j, green);
        image.setBlue(i, j, blue);
      }
    }
  }

  /**
   * Contruct mosaic effect on image.
   *
   * @param image input image
   */
  private void mosaic(Image image) {
    int width = image.getWidth();
    int height = image.getHeight();
    // Create random centroids.
    int rand_num = 4000;
    int[][] centroids = new int[rand_num][2];
    for (int i = 0; i < rand_num; i++) {
      int[] centroid = createRandomPixels(width, height);
      centroids[i][0] = centroid[0];
      centroids[i][1] = centroid[1];
    }
    // Assign channels for pixels.
    assignChannelVals(width, height, centroids, rand_num, image);
  }

  @Override
  public Image apply(Object input) {
    Image newImage = ((Image) input).copy();
    mosaic(newImage);
    return newImage;
  }
}
