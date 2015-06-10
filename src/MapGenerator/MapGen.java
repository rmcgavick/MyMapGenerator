package MapGenerator;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

public class MapGen extends JPanel {

	protected int x; // map width
	protected int y; // map height
	protected MapTile[][] map;
	protected JLabel[][] mapTiles;
	
	// this method takes one int - the desired map width, and the
	// height is calculated using a 16:10 aspect ratio
	public MapGen(int ms) {
		x = ms;
		y = (int)(16.0/10.0) * ms;
		map = new MapTile[x][y];
		mapTiles = randomizeMap(x, y);
	}
	
	protected JLabel[][] randomizeMap(int width, int height) {
		String c = "";
		JLabel[][] mt = new JLabel[height][width];
		
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				Random rn = new Random();
				int n = rn.nextInt((5 - 0) + 1) + 0;
				//System.out.println("n = " + n);
				switch(n) {
					case 0: c = "DeepWater"; 
								break;
					case 1: c = "ShallowWater"; 
								break;
					case 2: c = "Beach"; 
								break;
					case 3: c = "Forest"; 
								break;
					case 4: c = "Hills";  
								break;
					case 5: c = "Mountains"; 
								break;
				}
				//System.out.println("c = " + c);
				map[i][j] = new MapTile(i, j, c);
				mt[i][j] = new JLabel(new ImageIcon(map[i][j].getTileImage()));
				add(mt[i][j]);
			}
		}
		return mt;
	}
	
	// this method was for testing before the GUI was finished
	// it is now deprecated
	protected static String printMap(MapTile[][] m) {
		String mapString = "";
		for(int i = 0; i < m.length; i++) {
			for(int j = 0; j < m.length; j++) {
				mapString += (m[i][j] + " ");
			}
			mapString += ('\n');
		}
		return mapString;
	}
}