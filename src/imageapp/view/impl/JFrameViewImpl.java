package imageapp.view.impl;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import javax.swing.border.TitledBorder;
import imageapp.image.Image;
import imageapp.view.View;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class JFrameViewImpl extends JFrame implements View {
  private JPanel contentPane;
  private JLabel stateLabel;
  private JMenuItem menuItemLoad;
  private JMenuItem menuItemSave;
  private JMenuItem menuItemBlur;
  private JMenuItem menuItemSharpen;
  private JMenuItem menuItemGreyScale;
  private JMenuItem menuItemSepia;
  private JMenuItem menuItemDither;
  private JMenuItem menuItemMosaic;
  private JMenuItem menuItemRainbow;
  private JMenuItem menuItemCheckBoard;
  private JTextField input;
  private JButton textButton;
  private ImageComponent imageComponent;
  private JScrollPane sp;

  public JFrameViewImpl() {
    setFont(new Font("Dialog", Font.PLAIN, 15));
    setTitle("Image App");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 680, 460);

    JMenuBar menuBar = new JMenuBar();
    setJMenuBar(menuBar);

    JMenu menuFile = new JMenu("File");
    menuFile.setBackground(Color.WHITE);
    menuBar.add(menuFile);

    menuItemLoad = new JMenuItem("Load");
    menuItemLoad.setActionCommand("Load");
    menuItemLoad.setBackground(Color.WHITE);
    menuFile.add(menuItemLoad);

    menuItemSave = new JMenuItem("Save");
    menuItemSave.setActionCommand("Save");
    menuItemSave.setBackground(Color.WHITE);
    menuFile.add(menuItemSave);

    JMenu menuOperation = new JMenu("Operation");
    menuOperation.setBackground(Color.WHITE);
    menuBar.add(menuOperation);

    menuItemBlur = new JMenuItem("Blur");
    menuItemBlur.setActionCommand("blur");
    menuOperation.add(menuItemBlur);

    menuItemSharpen = new JMenuItem("Sharpen");
    menuItemSharpen.setActionCommand("sharpen");
    menuOperation.add(menuItemSharpen);

    menuItemGreyScale = new JMenuItem("Grey Scale");
    menuItemGreyScale.setActionCommand("greyscale");
    menuOperation.add(menuItemGreyScale);

    menuItemSepia = new JMenuItem("Sepia");
    menuItemSepia.setActionCommand("sepia");
    menuOperation.add(menuItemSepia);

    menuItemDither = new JMenuItem("Dither");
    menuItemDither.setActionCommand("dithering");
    menuOperation.add(menuItemDither);

    menuItemMosaic = new JMenuItem("Mosaic");
    menuItemMosaic.setActionCommand("mosaic");
    menuOperation.add(menuItemMosaic);

    menuItemRainbow = new JMenuItem("Generate Rainbow Image");
    menuItemRainbow.setActionCommand("rainbow");
    menuOperation.add(menuItemRainbow);

    menuItemCheckBoard = new JMenuItem("Generate Check Board Image");
    menuItemCheckBoard.setActionCommand("checkboard");
    menuOperation.add(menuItemCheckBoard);
    
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    imageComponent = new ImageComponent();
    imageComponent.setBounds(210, 0, 450, 377);
    contentPane.add(imageComponent);
    imageComponent.setPreferredSize(new Dimension(1920, 1080));
    JScrollPane scrollPane = new JScrollPane(imageComponent);
    scrollPane.setBounds(210, 0, 450, 377);
    contentPane.add(scrollPane);
    

    stateLabel = new JLabel("CurrentState");
    stateLabel.setBounds(10, 100, 200, 20);
    contentPane.add(stateLabel);
    
    input = new JTextField();
    input.setText("Please Type Your Parameters Here.");
    input.setHorizontalAlignment(SwingConstants.LEFT);
    input.setBounds(6, 12, 150, 20);
    contentPane.add(input);
    input.setColumns(10);
    
    textButton = new JButton("Add Parameters");
    textButton.setBounds(10, 46, 117, 20);
    textButton.setActionCommand("Record");
    contentPane.add(textButton);
    
  }

  @Override
  public void showState(String state) {
    stateLabel.setText(state);
  }

  @Override
  public void displayImage(Image image) {
    BufferedImage bufferedImage = image.convertBufferedImage();
    imageComponent.setImage(bufferedImage);
    contentPane.repaint();
    
    //Graphics graphics = bufferedImage.createGraphics();
//    graphics.drawImage(bufferedImage, 215, 15, (ImageObserver) this);
//    imageScrollPane.paint(graphics);
    //imageScrollPane.paintComponents(graphics);
    
    // to display()
    //showState("Showing the image.");
  }

  @Override
  public void addActionListener(ActionListener listener) {
    menuItemLoad.addActionListener(listener);
    menuItemSave.addActionListener(listener);
    menuItemBlur.addActionListener(listener);
    menuItemSharpen.addActionListener(listener);
    menuItemGreyScale.addActionListener(listener);
    menuItemSepia.addActionListener(listener);
    menuItemDither.addActionListener(listener);
    menuItemMosaic.addActionListener(listener);
    menuItemRainbow.addActionListener(listener);
    menuItemCheckBoard.addActionListener(listener);
    textButton.addActionListener(listener);
  }

  @Override
  public String getInputString() {
    return input.getText().trim();
  }

  @Override
  public void clearInputString() {
    input.setText("Please Type Your Parameters Here.");
  }


}
