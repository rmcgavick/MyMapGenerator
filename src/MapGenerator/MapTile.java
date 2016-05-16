package MapGenerator;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class MapTile {

	private int tileImageType; 
	private String tileImageName;
	private boolean passable; // will need to implement this for pathfinding
	private int XPos;
	private int YPos;
	private int tileImageWidth;
	private int tileImageHeight;
	private BufferedImage tileImage;
	
	public MapTile(int x, int y, int mi) {
		tileImageType = mi; 
		tileImageName = setImageNameFromType(tileImageType);
		XPos = x;
		YPos = y;
		try {
			tileImage = ImageIO.read(this.getClass().getResource("/resources/" + tileImageName + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		tileImageWidth = tileImage.getWidth();
		tileImageHeight = tileImage.getHeight();
	}
	
	public int getXPos() {
		return XPos;
	}
	
	public int getYPos() {
		return YPos;
	}
	
	public BufferedImage getTileImage() {
		return tileImage;
	}
	
	public int getTileImageWidth() {
		return tileImageWidth;
	}

	public int getTileImageHeight() {
		return tileImageHeight;
	}	
	
	public int getTileImageType() {
		return tileImageType;
	}
	
	// will need getters/setters for passable
	
	// will need getters/setters for is "active" tile (mouse and/or keyboard listeners)
	
	// skeleton method to redraw tile to use another image.
	public void redrawTileImage(int newTileImage) {		
		tileImageType = newTileImage;
		tileImageName = setImageNameFromType(tileImageType);
		try {
			tileImage = ImageIO.read(this.getClass().getResource("/resources/" + tileImageName + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String setImageNameFromType(int type) {
		String temp;
		switch(type) {
		case 0:
			temp = "DeepWater";
			break;
		case 1:
			temp = "ShallowWater";
			break;
		case 2:
			temp = "Beach";
			break;
		case 3:
			temp = "Forest";
			break;
		case 4:
			temp = "Hills";
			break;
		default:
			temp = "Mountains";
			break;
		}
		return temp;
	}
	
	public String toString() {
		return "" + tileImageName;
	}
}
