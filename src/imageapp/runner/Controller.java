package imageapp.runner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import imageapp.image.Image;
import imageapp.iohelper.IOHelper;
import imageapp.operation.Operation;
import imageapp.operation.impl.GenerateCheckBoardImage;
import imageapp.operation.impl.GenerateRainbowImage;
import imageapp.view.View;

public class Controller implements ActionListener {
  private Map<String, Operation> operationsByName;
  private View view;
  //private BufferedImage bufferedImage;
  private Image image;
  
  private String inputText;

  public Controller(Map<String, Operation> operationsByName, View view) {
    this.operationsByName = operationsByName;
    this.view = view;
    this.view.addActionListener(this);
    this.image = null;
  }

  public void actionPerformed(ActionEvent evt) {
    // If the user click the save menuitem, show the save dialog.
    String com = evt.getActionCommand();
    //image = null;
    if (com.equals("Save")) {
      saveMenu();
    } else if (com.equals("Load")) {
      loadMenu();
    } else if (com.equals("Record")) {
      inputText = view.getInputString();
      view.showState("Parameters: " + inputText);
      //clear input textfield
      view.clearInputString();
    } else {
      Operation operation = operationsByName.get(com);
//      if (operation == null) {
//        throw new IllegalArgumentException("Wrong operation name: " + com);
//      }

      Object input = null;
      
      if (com.equals("rainbow")) {
        String[] OperationParams = inputText.split(" ");
        if (OperationParams.length != 4) {
          throw new IllegalArgumentException("Incorrect Number of Parameters!");
        }
        input = getRainbowParams(OperationParams);
        
      } else if (com.equals("checkboard")) {
        String[] OperationParams = inputText.split(" ");
        if (OperationParams.length != 3) {
          throw new IllegalArgumentException("Incorrect Number of Parameters!");
        }
        input = getCheckBoardParams(OperationParams); 
        
      } else {
        System.out.println( this.image != null); 
        if (this.image == null) {
          throw new IllegalArgumentException("No image loaded!");
        }
        input = this.image;
      }

      this.image = operation.apply(input);
      view.showState(com + " image");
      view.displayImage(this.image);
    
      
      
    } 
  }

  private void loadMenu() {
    // create an object of JFileChooser class
    JFileChooser fileChooser =
        new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

    // resctrict the user to selec files of all types
    fileChooser.setAcceptAllFileFilterUsed(false);

    // set a title for the dialog
    fileChooser.setDialogTitle("Select a .txt file");

    // only allow files of image extension
    FileNameExtensionFilter restrict =
        new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp");
    fileChooser.addChoosableFileFilter(restrict);

    // invoke the showsOpenDialog function to show the save dialog
    int r = fileChooser.showOpenDialog(null);

    // if the user selects a file
    if (r == JFileChooser.APPROVE_OPTION) {
      // set the label to the path of the selected file
      String imagePath = fileChooser.getSelectedFile().getAbsolutePath();
      try {
        this.image = IOHelper.loadImage(imagePath);
        System.out.println(this.image != null);
        view.showState("Load " + imagePath);
        view.displayImage(image); 
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    // if the user cancelled the operation
    else {
      view.showState("the user cancelled the operation");
    }
  }

  private void saveMenu() {
    // create an object of JFileChooser class
    JFileChooser fileChooser =
        new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

    // resctrict the user to selec files of all types
    fileChooser.setAcceptAllFileFilterUsed(false);
    // only allow files of image extension
    FileNameExtensionFilter restrict =
        new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp");
    fileChooser.addChoosableFileFilter(restrict);

    // invoke the showsSaveDialog function to show the save dialog
    int r = fileChooser.showSaveDialog(null);

    // if the user selects a file
    if (r == JFileChooser.APPROVE_OPTION) {
      // set the label to the path of the selected file
      String imagePath = fileChooser.getSelectedFile().getAbsolutePath();
      try {
        IOHelper.saveImage(image, imagePath);
        view.showState("Save " + imagePath);
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    } else {
      // if the user cancelled the operation
      view.showState("the user cancelled the operation");
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
    int square = Integer.parseInt(OperationParams[0]);
    int width = Integer.parseInt(OperationParams[1]);
    int height = Integer.parseInt(OperationParams[2]);
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
    int stripeWidth = Integer.parseInt(OperationParams[0]);
    String direction = OperationParams[1];
    int width = Integer.parseInt(OperationParams[2]);
    int height = Integer.parseInt(OperationParams[3]);
    GenerateRainbowImage.Params params =
        new GenerateRainbowImage.Params(stripeWidth, direction, width, height);
    input = params;
    return input;
  }

}
