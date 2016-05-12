package MapGenerator;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

public class MapGen extends JPanel {

	protected int x; // height of map in number of tiles
	protected int y; // width of map in number of tiles
	protected int octave;
	protected float persistence;
	protected MapTile[][] tileGrid;
	protected JLabel[][] mapTiles;
	
	public MapGen(int mapWidth, int mapHeight, int oct, float p) {
		x = mapWidth;
		y = mapHeight;
		octave = oct;
		persistence = p;
		tileGrid = new MapTile[x][y];
		mapTiles = generateRandomMap(tileGrid, generatePerlinNoise(generateWhiteNoise(x,y),octave,persistence));
		
		
//		mapTiles = randomizeMap(x, y);			
		// testing
		//generatePerlinNoise(generateWhiteNoise(x,y),7);
	}
	
	protected JLabel[][] generateRandomMap(MapTile[][] map, float[][] perlinNoise) {
		int width = perlinNoise.length;
		int height = perlinNoise[0].length;
		
		String tile = "";
		JLabel[][] tiledMap = new JLabel[width][height];
		
		for (int i=0; i<width; i++) {
			for (int j=0; j<height; j++) {
				//int n = Math.round(perlinNoise[i][j]);
				
				if(perlinNoise[i][j]<=0.3) {
					tile = "DeepWater"; 
				}
				else if(perlinNoise[i][j]<=0.4) {
					tile = "ShallowWater";
				}
				else if(perlinNoise[i][j]<=0.5) {
					tile = "Beach";
				}
				else if(perlinNoise[i][j]<=0.65) {
					tile = "Forest";
				}
				else if(perlinNoise[i][j]<=0.75) {
					tile = "Hills";
				}
				else {
					tile = "Mountains";
				}

				map[i][j] = new MapTile(i, j, tile);
				tiledMap[i][j] = new JLabel(new ImageIcon(map[i][j].getTileImage()));
				add(tiledMap[i][j]);
			}
		}
		return tiledMap;
	}
	
	protected float[][] generatePerlinNoise(float[][] baseNoise, int octaveCount, float persistence) {
		int width = baseNoise.length;
		int height = baseNoise[0].length;
		float[][][] smoothNoise = new float[octaveCount][][]; 	// an array (of size octaveCount) of 2d arrays (containing
																// the different "smoothNoise" 2d arrays of varying smoothness)		
		// generate smooth noise of different octaves
		for (int i=0; i<octaveCount; i++) {
			smoothNoise[i] = generateSmoothNoise(baseNoise, i);
		}
		
		float[][] perlinNoise = new float[width][height];
		float amplitude = 1.0f;
		float totalAmplitude = 0.0f;
		
		// blend the noise (different octaves) together
		for (int oct= octaveCount-1; oct>=0; oct--) {
			amplitude *= persistence;
			totalAmplitude += amplitude;
			
			for (int i=0; i<width; i++) {
				for(int j=0; j<height; j++) {
					perlinNoise[i][j] += smoothNoise[oct][i][j] * amplitude; 
				}
			}
		}
		
		// normalize the perlinNoise 2d array
		for (int i=0; i<width; i++) {
			for(int j=0; j<height; j++) {
				perlinNoise[i][j] /= totalAmplitude;				
					System.out.println(perlinNoise[i][j]);				
			}
		}
		return perlinNoise;
	}
	
	// Generate array of size width*height with random values between 0 and 1
	protected float[][] generateWhiteNoise(int width, int height) {
		Random rn = new Random(); // random seed
		float[][] noise = new float[width][height];
		
		for (int i=0; i<width; i++) {
			for (int j=0; j<height; j++) {
				noise[i][j] = (float)rn.nextFloat() % 1;
				//System.out.println(noise[i][j]);
			}
		}		
		return noise;
	}
	
	// method to generate the nth Octave (An Octave is an array of the same size as the 
	// desired final product, but each Octave has a different "smoothness". To achieve
	// the final array of Perlin/Simplex noise, we must blend all Octaves from 0 to n)
	protected float[][] generateSmoothNoise(float[][] baseNoise, int octave) {
		int width = baseNoise.length;
		int height = baseNoise[0].length;		
		float[][] smoothNoise = new float[width][height];
		
		int samplePeriod = (int)Math.pow(2, octave); // 2^n
		float sampleFrequency = 1.0f / samplePeriod;
		
		for (int i=0; i<width; i++) {
			// find the vertical sampling indices
			int i0 = (i / samplePeriod) * samplePeriod;
			int i1 = (i0 + samplePeriod) % width; // to account for wrap around
			float horiz_blend = (i - i0) * sampleFrequency;
			
			for (int j=0; j<height; j++) {
				int j0 = (j / samplePeriod) * samplePeriod;
				int j1 = (j0 + samplePeriod) % height; // wrap around
				float vert_blend = (j - j0) * sampleFrequency;
				
				// blend top corners
				float top = interpolate(baseNoise[i0][j0], baseNoise[i1][j0], horiz_blend);
				// blend bottom corners
				float bottom = interpolate(baseNoise[i0][j1], baseNoise[i1][j1], horiz_blend);
				// blend the rest of the 2d array
				smoothNoise[i][j] = interpolate(top, bottom, vert_blend);
				//System.out.println(smoothNoise[i][j]);
			}
		}
		return smoothNoise;
	}
	
	// calculates linear interpolation between two values. The closer alpha is to 0, the closer the resulting value
	// will be to x0; The closer slpha is to 1, the closer the resulting value will be to x1.
	protected float interpolate(float x0, float x1, float alpha) {
		return x0 * (1 - alpha) + alpha * x1;
	}
	
	// first attempt at a more realistic randomizeMap method -- depricated after implementing Simplex Noise algorithm 5/12
//	protected JLabel[][] randomizeMap(int width, int height) {
//		String c = "";
//		JLabel[][] mt = new JLabel[height][width];
//		Random rn = new Random();
//		int n = rn.nextInt((5 - 0) + 1) + 0;
//		int[] m = new int[width];
//		
//		for(int i = 0; i < height; i++) {			
//			for(int j = 0; j < width; j++) {
//				
//				if(i == 0) {
//					n = rn.nextInt(((n+1) - (n-1)) + 1) + (n-1);
//					m[j] = n;
//					//System.out.println(m[j]);
//				} else {
//					n = rn.nextInt(((m[j]+1) - (m[j]-1)) + 1) + (m[j]-1);
//					m[j] = n;
//				}
//						switch(n) {
//							case 0: c = "DeepWater"; 
//									break;
//							case 1: c = "ShallowWater"; 
//									break;
//							case 2: c = "Beach"; 
//									break;
//							case 3: c = "Forest"; 
//									break;
//							case 4: c = "Hills";  
//									break;
//							case 5: c = "Mountains"; 
//									break;
//						}
//					map[i][j] = new MapTile(i, j, c);
//					mt[i][j] = new JLabel(new ImageIcon(map[i][j].getTileImage()));
//					add(mt[i][j]);
//				
//			}
//
//		}
//		return mt;
//	}
	
	// this method was for testing before the GUI was finished
	// it is now deprecated
//	protected static String printMap(MapTile[][] m) {
//		String mapString = "";
//		for(int i = 0; i < m.length; i++) {
//			for(int j = 0; j < m.length; j++) {
//				mapString += (m[i][j] + " ");
//			}
//			mapString += ('\n');
//		}
//		return mapString;
//	}
}
