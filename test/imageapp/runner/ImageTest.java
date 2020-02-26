package imageapp.runner;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;
import imageapp.image.Image;
import imageapp.iohelper.IOHelper;
import imageapp.operation.Operation;
import imageapp.operation.impl.Blur;
import imageapp.operation.impl.Dithering;
import imageapp.operation.impl.GenerateCheckBoardImage;
import imageapp.operation.impl.GenerateRainbowImage;
import imageapp.operation.impl.GreyScale;
import imageapp.operation.impl.Sepia;
import imageapp.operation.impl.Sharpen;

/** Junit tests for imageapp. */
public class ImageTest {
  public static final double DELTA = 1e-13;

  @Test
  public void createImage() throws IOException {
    // test image method create a image
    Image newImage = new Image(500, 800);
    assertEquals(800, newImage.getHeight());
    assertEquals(500, newImage.getWidth());
  }

  @Test
  public void test1() throws IOException {
    // test image method getHeight()
    File inputFilePath = new File("res/vaultboy.jpg");
    BufferedImage bufferedImage = ImageIO.read(inputFilePath);
    System.out.println("Reading complete.");

    Image newImage = new Image(bufferedImage);
    assertEquals(705, newImage.getHeight());
    assertEquals(873, newImage.getWidth());
  }

  @Test
  public void test2() throws IOException {
    // test image method setRed(), setBlue(), setGreen()
    // and getBlue(), getRed(), getGreen()
    Image newImage = new Image(500, 800);
    newImage.setBlue(3, 5, 15);
    newImage.setRed(13, 55, 150);
    newImage.setGreen(55, 200, 100);

    assertEquals(15, newImage.getBlue(3, 5));
    assertEquals(150, newImage.getRed(13, 55));
    assertEquals(100, newImage.getGreen(55, 200));
  }

  @Test
  public void copyImage() throws IOException {
    File inputFilePath = new File("res/vaultboy.jpg");
    BufferedImage bufferedImage = ImageIO.read(inputFilePath);
    System.out.println("Reading complete.");
    Image newImage = new Image(bufferedImage);
    Image copyImage = newImage.copy();

    assertEquals(copyImage.getHeight(), newImage.getHeight());
    assertEquals(copyImage.getWidth(), newImage.getWidth());
    assertEquals(copyImage.getBlue(3, 5), newImage.getBlue(3, 5));
    assertEquals(copyImage.getRed(10, 25), newImage.getRed(10, 25));
    assertEquals(copyImage.getGreen(210, 77), newImage.getGreen(210, 77));
  }

  @Test
  public void testBlur() throws IOException {
    Image newImage = new Image(3, 3);
    Operation blur = new Blur();
    for (int i = 0; i < newImage.getHeight(); i++) {
      for (int j = 0; j < newImage.getWidth(); j++) {
        newImage.setBlue(i, j, 10);
        newImage.setGreen(i, j, 10);
        newImage.setRed(i, j, 10);
      }
    }

    Image imageBlur = blur.apply(newImage);
    double[][] matrix =
        new double[][] {
          {5.0, 6.0, 5.0},
          {6.0, 8.0, 6.0},
          {5.0, 6.0, 4.0}
        };

    double[][] redMatrix = new double[3][3];
    double[][] blueMatrix = new double[3][3];
    double[][] greenMatrix = new double[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        redMatrix[i][j] = imageBlur.getRed(i, j);
        blueMatrix[i][j] = imageBlur.getBlue(i, j);
        greenMatrix[i][j] = imageBlur.getGreen(i, j);
      }
    }
    assertArrayEquals(matrix, redMatrix);
    assertArrayEquals(matrix, blueMatrix);
    assertArrayEquals(matrix, greenMatrix);
  }

  @Test
  public void testSharpen() throws IOException {
    Image newImage = new Image(3, 3);
    Operation sharpen = new Sharpen();
    for (int i = 0; i < newImage.getHeight(); i++) {
      for (int j = 0; j < newImage.getWidth(); j++) {
        newImage.setBlue(i, j, 5);
        newImage.setGreen(i, j, 5);
        newImage.setRed(i, j, 5);
      }
    }

    Image imageSharpen = sharpen.apply(newImage);
    double[][] matrix =
        new double[][] {
          {4.0, 7.0, 6.0},
          {8.0, 15.0, 12.0},
          {6.0, 13.0, 11.0}
        };

    double[][] redMatrix = new double[3][3];
    double[][] blueMatrix = new double[3][3];
    double[][] greenMatrix = new double[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        redMatrix[i][j] = imageSharpen.getRed(i, j);
        blueMatrix[i][j] = imageSharpen.getBlue(i, j);
        greenMatrix[i][j] = imageSharpen.getGreen(i, j);
      }
    }
    assertArrayEquals(matrix, redMatrix);
    assertArrayEquals(matrix, blueMatrix);
    assertArrayEquals(matrix, greenMatrix);
  }

  @Test
  public void testGreyScale() throws IOException {
    Image newImage = new Image(3, 3);
    Operation greyScale = new GreyScale();
    for (int i = 0; i < newImage.getHeight(); i++) {
      for (int j = 0; j < newImage.getWidth(); j++) {
        newImage.setBlue(i, j, 20);
        newImage.setGreen(i, j, 20);
        newImage.setRed(i, j, 20);
      }
    }

    Image imageGreyScale = greyScale.apply(newImage);
    double[][] matrix =
        new double[][] {
          {19.0, 19.0, 19.0},
          {19.0, 19.0, 19.0},
          {19.0, 19.0, 19.0}
        };

    double[][] redMatrix = new double[3][3];
    double[][] blueMatrix = new double[3][3];
    double[][] greenMatrix = new double[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        redMatrix[i][j] = imageGreyScale.getRed(i, j);
        blueMatrix[i][j] = imageGreyScale.getBlue(i, j);
        greenMatrix[i][j] = imageGreyScale.getGreen(i, j);
      }
    }
    assertArrayEquals(matrix, redMatrix);
    assertArrayEquals(matrix, blueMatrix);
    assertArrayEquals(matrix, greenMatrix);
  }

  @Test
  public void testSepia() throws IOException {
    Image newImage = new Image(3, 3);
    Operation sepia = new Sepia();
    for (int i = 0; i < newImage.getHeight(); i++) {
      for (int j = 0; j < newImage.getWidth(); j++) {
        newImage.setBlue(i, j, 20);
        newImage.setGreen(i, j, 20);
        newImage.setRed(i, j, 20);
      }
    }

    Image imageSepia = sepia.apply(newImage);
    double[][] expRedMatrix =
        new double[][] {
          {25.0, 25.0, 25.0},
          {25.0, 25.0, 25.0},
          {25.0, 25.0, 25.0}
        };

    double[][] expGreenMatrix =
        new double[][] {
          {22.0, 22.0, 22.0},
          {22.0, 22.0, 22.0},
          {22.0, 22.0, 22.0}
        };

    double[][] expBlueMatrix =
        new double[][] {
          {17.0, 17.0, 17.0},
          {17.0, 17.0, 17.0},
          {17.0, 17.0, 17.0}
        };

    double[][] redMatrix = new double[3][3];
    double[][] blueMatrix = new double[3][3];
    double[][] greenMatrix = new double[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        redMatrix[i][j] = imageSepia.getRed(i, j);
        blueMatrix[i][j] = imageSepia.getBlue(i, j);
        greenMatrix[i][j] = imageSepia.getGreen(i, j);
      }
    }
    assertArrayEquals(expRedMatrix, redMatrix);
    assertArrayEquals(expBlueMatrix, blueMatrix);
    assertArrayEquals(expGreenMatrix, greenMatrix);
  }

  @Test
  public void testRainbow() throws IOException {
    Operation rainbow = new GenerateRainbowImage();
    Object input;
    GenerateRainbowImage.Params params = new GenerateRainbowImage.Params(1, "H", 3, 3);
    input = params;

    Image imageRainbow = rainbow.apply(input);
    double[][] expRedMatrix =
        new double[][] {
          {255.0, 255.0, 255.0},
          {255.0, 255.0, 255.0},
          {255.0, 255.0, 255.0}
        };

    double[][] expGreenMatrix =
        new double[][] {
          {0.0, 200.0, 255.0},
          {0.0, 200.0, 255.0},
          {0.0, 200.0, 255.0}
        };

    double[][] expBlueMatrix =
        new double[][] {
          {0.0, 0.0, 0.0},
          {0.0, 0.0, 0.0},
          {0.0, 0.0, 0.0}
        };

    double[][] redMatrix = new double[3][3];
    double[][] blueMatrix = new double[3][3];
    double[][] greenMatrix = new double[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        redMatrix[i][j] = imageRainbow.getRed(i, j);
        blueMatrix[i][j] = imageRainbow.getBlue(i, j);
        greenMatrix[i][j] = imageRainbow.getGreen(i, j);
      }
    }
    assertArrayEquals(expRedMatrix, redMatrix);
    assertArrayEquals(expBlueMatrix, blueMatrix);
    assertArrayEquals(expGreenMatrix, greenMatrix);
  }

  @Rule public ExpectedException thrown = ExpectedException.none();

  @Test
  public void invalidInputRainbowStripeWidth() throws IllegalArgumentException {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("Stripe width is larger than image width!");
    Operation rainbow = new GenerateRainbowImage();
    Object input;
    GenerateRainbowImage.Params params = new GenerateRainbowImage.Params(5, "H", 3, 3);
    input = params;
    rainbow.apply(input);
  }

  @Test
  public void invalidInputRainbowStripeHeight() throws IllegalArgumentException {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("Stripe width is larger than image height!");
    Operation rainbow = new GenerateRainbowImage();
    Object input;
    GenerateRainbowImage.Params params = new GenerateRainbowImage.Params(5, "V", 3, 3);
    input = params;
    rainbow.apply(input);
  }

  @Test
  public void invalidInputRainbowDirection() throws IllegalArgumentException {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("Direction could only be horizontal or vertical!");
    Operation rainbow = new GenerateRainbowImage();
    Object input;
    GenerateRainbowImage.Params params = new GenerateRainbowImage.Params(1, "A", 3, 3);
    input = params;
    rainbow.apply(input);
  }

  @Test
  public void testCheckBoard() throws IOException {
    Operation checkboard = new GenerateCheckBoardImage();
    Object input;
    GenerateCheckBoardImage.Params params = new GenerateCheckBoardImage.Params(1, 3, 3);
    input = params;

    Image imageCheckBoard = checkboard.apply(input);
    double[][] expRedMatrix =
        new double[][] {
          {255.0, 0.0, 255.0},
          {0.0, 255.0, 0.0},
          {255.0, 0.0, 255.0}
        };

    double[][] expGreenMatrix =
        new double[][] {{255.0, 0.0, 255.0}, {0.0, 255.0, 0.0}, {255.0, 0.0, 255.0}};

    double[][] expBlueMatrix =
        new double[][] {{255.0, 0.0, 255.0}, {0.0, 255.0, 0.0}, {255.0, 0.0, 255.0}};

    double[][] redMatrix = new double[3][3];
    double[][] blueMatrix = new double[3][3];
    double[][] greenMatrix = new double[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        redMatrix[i][j] = imageCheckBoard.getRed(i, j);
        blueMatrix[i][j] = imageCheckBoard.getBlue(i, j);
        greenMatrix[i][j] = imageCheckBoard.getGreen(i, j);
      }
    }
    assertArrayEquals(expRedMatrix, redMatrix);
    assertArrayEquals(expBlueMatrix, blueMatrix);
    assertArrayEquals(expGreenMatrix, greenMatrix);
  }

  @Test
  public void invalidInputCheckBoardSquare() throws IllegalArgumentException {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("Square size in incorrect. Please reduce its value!");
    Operation checkboard = new GenerateCheckBoardImage();
    Object input;
    GenerateCheckBoardImage.Params params = new GenerateCheckBoardImage.Params(5, 3, 3);
    input = params;
    checkboard.apply(input);
  }

  @Test
  public void invalidInputCheckBoardSquareDivide() throws IllegalArgumentException {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage(
        "Square size in incorrect. Please let width and height be divisible by square!");
    Operation checkboard = new GenerateCheckBoardImage();
    Object input;
    GenerateCheckBoardImage.Params params = new GenerateCheckBoardImage.Params(3, 5, 5);
    input = params;
    checkboard.apply(input);
  }

  @Test
  public void testDither() throws IOException {
    Image newImage = new Image(3, 3);
    Operation dither = new Dithering();
    for (int i = 0; i < newImage.getHeight(); i++) {
      for (int j = 0; j < newImage.getWidth(); j++) {
        newImage.setBlue(i, j, 20);
        newImage.setGreen(i, j, 20);
        newImage.setRed(i, j, 20);
      }
    }

    Image imageDither = dither.apply(newImage);
    double[][] matrix =
        new double[][] {
          {0.0, 0.0, 0.0},
          {0.0, 0.0, 0.0},
          {0.0, 0.0, 0.0}
        };

    double[][] redMatrix = new double[3][3];
    double[][] blueMatrix = new double[3][3];
    double[][] greenMatrix = new double[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        redMatrix[i][j] = imageDither.getRed(i, j);
        blueMatrix[i][j] = imageDither.getBlue(i, j);
        greenMatrix[i][j] = imageDither.getGreen(i, j);
      }
    }
    assertArrayEquals(matrix, redMatrix);
    assertArrayEquals(matrix, blueMatrix);
    assertArrayEquals(matrix, greenMatrix);
  }

  @Test
  public void testReadLines() throws IOException {
    String commandsFilePath = "res/test_readlines.txt";
    String[] commandLines = IOHelper.readLines(commandsFilePath);
    assertEquals("aaa", commandLines[0]);
    assertEquals("bbb", commandLines[1]);
    assertEquals("ccc", commandLines[2]);
  }

  /**
   * This method test two "2 dimensional" arrays to see if they are the same size and if the items
   * inside are the same.
   *
   * @param expected The expected 2 dimensional array.
   * @param actual The actual 2 dimensional array.
   */
  public static void assertArrayEquals(double[][] expected, double[][] actual) {

    // We test to see if the first dimension is the same.
    if (expected.length != actual.length) {
      System.out.println(". The array lengths of the first dimensions aren't the same.");
      return;
    }

    // We test every array inside the 'outer' array.
    for (int i = 0; i < expected.length; i++) {
      // Can also use (with JUnit 4.3, but never tried
      // it) assertArrayEquals(actual, expected);
      assertTrue(
          "Array no." + i + " in expected and actual aren't the same.",
          Arrays.equals(expected[i], actual[i]));
    }
  }
}
