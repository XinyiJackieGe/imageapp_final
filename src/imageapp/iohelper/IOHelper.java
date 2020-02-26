package imageapp.iohelper;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import imageapp.image.Image;

/** IOHelper contains operations to read files, load and save images. */
public class IOHelper {
  /**
   * Load image and return a new Image object.
   *
   * @param imagePath to read BufferedImage
   * @return new Image object
   * @throws IOException
   */
  public static Image loadImage(String imagePath) throws IOException {
    File inputImagePath = new File(imagePath);
    BufferedImage bufferedImage = ImageIO.read(inputImagePath);
    System.out.println("Reading complete.");
    return new Image(bufferedImage);
  }

  /**
   * Save image to image path.
   *
   * @param image Image object
   * @param imagePath to save a BufferedImage
   * @throws IOException
   */
  public static void saveImage(Image image, String imagePath) throws IOException {
    File outputFilePathBlur = new File(imagePath);
//    String folderPath = outputFilePathBlur.getParent();
//    if (folderPath != null && !folderPath.isEmpty()) {
//      File folder = new File(folderPath);
//      if (!folder.exists()) {
//        folder.mkdirs();
//      }
//    }
    String imageformat = imagePath.substring(imagePath.indexOf(".") + 1);
    BufferedImage buffedImage = image.convertBufferedImage();
    ImageIO.write(buffedImage, imageformat, outputFilePathBlur);
    System.out.println("Writing complete.");
  }


  /**
   * Read strings line by line to a string array.
   *
   * @param path to read lines
   * @return string array
   * @throws IOException
   */
  public static String[] readLines(String path) throws IOException {
    FileReader fileReader = new FileReader(path);
    BufferedReader bufferedReader = new BufferedReader(fileReader);
    List<String> lines = new ArrayList<String>();
    String line = null;

    while ((line = bufferedReader.readLine()) != null) {
      lines.add(line);
    }

    bufferedReader.close();
    return lines.toArray(new String[lines.size()]);
  }
}
