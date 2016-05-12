package MapGenerator;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

public class GUI extends JFrame{
	
	// Set myMapSize to set the desired width and height of the map
	private final static int myMapWidth = 80;
	private final static int myMapHeight = 50;
	private final static int tilePixelWidth = 20;
	private final static int tilePixelHeight = 20;
	private final static int heightOffset = 28; //Offsets to account for the window border
	private final static int widthOffset = 6;
	private final static int octaves = 8; // set number of octaves to generate Perlin noise with (higher = more smooth maps)
	private final static float persistence = 0.8f; // set persistence (higher = more variance among tiles)
	
	private static void createGUI() {
		
		int y = (myMapHeight * tilePixelHeight) + heightOffset;
		int x = (myMapWidth * tilePixelWidth) + widthOffset;

		JFrame mapFrame = new JFrame("Riley's Map Generator");
		mapFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mapFrame.setResizable(true);
		mapFrame.setPreferredSize(new Dimension(x, y));
		mapFrame.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		
		MapGen mg = new MapGen(myMapHeight, myMapWidth, octaves, persistence);
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
