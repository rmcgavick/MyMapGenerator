package MapGenerator;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

public class GUI extends JFrame{
	
	// Set myMapSize to set the desired width of the map, the aspect ratio
	// should 
	private final static int myMapWidth = 26;
	private final static int myMapHeight = 16;
	private final static int tilePixelWidth = 60;
	private final static int tilePixelHeight = 60;
	
	private static void createGUI() {
		
		int y = (myMapHeight * tilePixelHeight) + 28;
		int x = (myMapWidth * tilePixelWidth) + 6;

		JFrame mapFrame = new JFrame("Riley's Map Generator");
		mapFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mapFrame.setPreferredSize(new Dimension(x, y));
		mapFrame.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		
		MapGen mg = new MapGen(myMapHeight, myMapWidth);
		mg.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		mg.setPreferredSize(new Dimension(x, y));
		mapFrame.getContentPane().add(mg);
		
		mapFrame.pack();
		mapFrame.setVisible(true);
		mapFrame.setResizable(false);
	}
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createGUI();
			}
		});
	}

}
