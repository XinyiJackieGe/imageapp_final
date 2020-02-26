package imageapp.operation;

import imageapp.image.Image;

/** Model interface that designs to do operations on an Image. */
public interface Operation {
  /**
   * Apply the operation on the given input image.
   *
   * @param input object an Image object if given or parameters for generating rainbow or check
   *     board images.
   * @return new Image object
   */
  Image apply(Object input);
}
