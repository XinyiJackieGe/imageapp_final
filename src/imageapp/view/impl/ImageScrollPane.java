package imageapp.view.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import javax.swing.JScrollPane;

public class ImageScrollPane extends JScrollPane{
  private BufferedImage image;
  
  
  public ImageScrollPane(BufferedImage image) {
    this.image = image;
  }
  
  @Override
  public void paintComponent(Graphics g)
  {
    //g = image.createGraphics();
    //g.drawImage(image, 215, 15, (ImageObserver) this);
    //super.paintComponents(g); // don't forget this!
    g.setColor(Color.green);
    g.fillRect(0, 0, getWidth(), getHeight());
    g.setColor(Color.red);
    g.fillOval(getWidth()/4, getHeight()/4, getWidth()/2, getHeight()/2);
//      if (image != null)
//      {
//          g.drawImage(image, 0, 0, this); // or null as last, doesn't really matter
//      }
  }
}
