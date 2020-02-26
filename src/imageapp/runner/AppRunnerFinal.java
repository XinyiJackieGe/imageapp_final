package imageapp.runner;

import java.awt.Window;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import imageapp.operation.Operation;
import imageapp.operation.impl.Blur;
import imageapp.operation.impl.Dithering;
import imageapp.operation.impl.GenerateCheckBoardImage;
import imageapp.operation.impl.GenerateRainbowImage;
import imageapp.operation.impl.GreyScale;
import imageapp.operation.impl.Mosaic;
import imageapp.operation.impl.Sepia;
import imageapp.operation.impl.Sharpen;
import imageapp.view.View;
import imageapp.view.impl.JFrameViewImpl;

public class AppRunnerFinal {
  public static void main(String[] args) throws IOException, IllegalAccessException {
    //    ArrayList<String> operationList = new ArrayList<String>();
    //    operationList.add("blur");
    //    operationList.add("sharpen");
//    if (args.length != 1) {
//      throw new IllegalArgumentException("Wrong input argument!");
//    }

    // model
    Map<String, Operation> operationsByName = initializeOperations();

    // String commandsFilePath = args[0];
    // String[] commandLines = IOHelper.readLines(commandsFilePath);

    // executeCommands(operationsByName, commandLines);

    //System.out.println("Done. Bye!");

    // JFrameViewImpl.setDefaultLookAndFeelDecorated(false);
    View frame = new JFrameViewImpl();
    new Controller(operationsByName, frame);
    ((Window) frame).setVisible(true);
  }

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
}
