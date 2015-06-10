package MapGenerator;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

public class GUI extends JFrame{
	
	// Set myMapSize to set the desired width of the map, the aspect ratio
	// should 
	private final static int myMapSize = 18; 
	private final static double aspectRatio = (16.0/10.0);
	private final static int tilePixelWidth = 60;
	private final static int tilePixelHeight = 60;
	
	private static void createGUI() {
		int x = (int)((myMapSize * aspectRatio) * tilePixelHeight) + 6;
		int y = (myMapSize * tilePixelWidth) + 6;
		System.out.println("Aspect ratio = " + aspectRatio);
		System.out.println("X and Y values for this map: " + x + ", " + y);
		JFrame mapFrame = new JFrame("Riley's Map Generator");
		mapFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mapFrame.setPreferredSize(new Dimension(x, y));
		
		MapGen mg = new MapGen(myMapSize);
		mg.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
		mg.setSize(x, y);
		mapFrame.getContentPane().add(mg);
		System.out.println(mg.getSize());
		mapFrame.setSize(x, y);
		System.out.println(mapFrame.getSize());
		
		mapFrame.pack();
		mapFrame.setVisible(true);
		//mapFrame.setResizable(false);
	}
	
	// simple randomize method, just uses random number generator

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createGUI();
			}
		});

	}

}
