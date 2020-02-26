package imageapp.runner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import imageapp.image.Image;
import imageapp.iohelper.IOHelper;
import imageapp.operation.Operation;
import imageapp.operation.impl.Blur;
import imageapp.operation.impl.Dithering;
import imageapp.operation.impl.GenerateCheckBoardImage;
import imageapp.operation.impl.GenerateRainbowImage;
import imageapp.operation.impl.GreyScale;
import imageapp.operation.impl.Mosaic;
import imageapp.operation.impl.Sepia;
import imageapp.operation.impl.Sharpen;

/** AppRunner that runs the imageapp. */
public class AppRunner {
  /**
   * Main method as the controller of the imageapp.
   *
   * @param args input txt file for recording clien's commands
   * @throws IOException if file path is incorrect
   * @throws IllegalAccessException
   */
  public static void main(String[] args) throws IOException, IllegalAccessException {
    if (args.length != 1) {
      throw new IllegalArgumentException("Wrong input argument!");
    }

    Map<String, Operation> operationsByName = initializeOperations();

    String commandsFilePath = args[0];
    String[] commandLines = IOHelper.readLines(commandsFilePath);

    executeCommands(operationsByName, commandLines);

    System.out.println("Done. Bye!");
  }

  /**
   * Initialize a map of operations. Key is the name of an operation and value is a new instance of
   * the operation.
   *
   * @return hash map of operations
   */
  private static Map<String, Operation> initializeOperations() {
    Map<String, Operation> operationsByName = new HashMap<>();
    operationsByName.put("blur", new Blur());
    operationsByName.put("sharpen", new Sharpen());
    operationsByName.put("greyscale", new GreyScale());
    operationsByName.put("sepia", new Sepia());
    operationsByName.put("rainbow", new GenerateRainbowImage());
    operationsByName.put("checkboard", new GenerateCheckBoardImage());
    operationsByName.put("dithering", new Dithering());
    operationsByName.put("mosaic", new Mosaic());
    return operationsByName;
  }

  /**
   * Execute commands from the input .txt file.
   *
   * @param operationsByName hash map of operations.
   * @param commandLines string array to store commands.
   * @throws IOException
   */
  private static void executeCommands(
      Map<String, Operation> operationsByName, String[] commandLines) throws IOException {
    Image image = null;

    for (String line : commandLines) {
      line = line.trim();
      if (line.startsWith("load")) {
        String imagePath = line.split(" ")[1];
        image = IOHelper.loadImage(imagePath);
      } else if (line.startsWith("save")) {
        String imagePath = line.split(" ")[1];
        IOHelper.saveImage(image, imagePath);
      } else {
        String[] OperationParams = line.split(" ");
        String operationName = OperationParams[0];
        Operation operation = operationsByName.get(operationName);
        if (operation == null) {
          throw new IllegalArgumentException("Wrong operation name: " + operationName);
        }

        Object input = null;
        if (operationName.equals("rainbow")) {
          input = getRainbowParams(OperationParams);
        } else if (operationName.equals("checkboard")) {
          input = getCheckBoardParams(OperationParams);
        } else {
          if (image == null) {
            throw new IllegalArgumentException("No image loaded!");
          }
          input = image;
        }

        image = operation.apply(input);
      }
    }
  }

  /**
   * Get check board parameters.
   *
   * @param OperationParams string array of parameters from a command line
   * @return input GenerateCheckBoardImage.Params object
   */
  private static Object getCheckBoardParams(String[] OperationParams) {
    Object input;
    int square = Integer.parseInt(OperationParams[1]);
    int width = Integer.parseInt(OperationParams[2]);
    int height = Integer.parseInt(OperationParams[3]);
    GenerateCheckBoardImage.Params params =
        new GenerateCheckBoardImage.Params(square, width, height);
    input = params;
    return input;
  }

  /**
   * Get rainbow parameters.
   *
   * @param OperationParams string array of parameters from a command line
   * @return input GenerateCheckBoardImage.Params object
   */
  private static Object getRainbowParams(String[] OperationParams) {
    Object input;
    int stripeWidth = Integer.parseInt(OperationParams[1]);
    String direction = OperationParams[2];
    int width = Integer.parseInt(OperationParams[3]);
    int height = Integer.parseInt(OperationParams[4]);
    GenerateRainbowImage.Params params =
        new GenerateRainbowImage.Params(stripeWidth, direction, width, height);
    input = params;
    return input;
  }
}
