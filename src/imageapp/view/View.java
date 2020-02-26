package imageapp.view;

import java.awt.event.ActionListener;
import imageapp.image.Image;


public interface View {
  //JLabel getStateLabel();
  void showState(String state);
  
  /**
   * Get the string from the text field and return it
   */
  String getInputString();
  
  /**
   * Clear the text field. Note that a more general "setInputString" would work for this purpose but
   * would be incorrect. This is because the text field is not set programmatically in general but
   * input by the user.
   */
  void clearInputString();
  void displayImage(Image image);
  void addActionListener(ActionListener listener);
}
