package MapGenerator;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class MapTile {

	private String tileImageName;
	private int XPos;
	private int YPos;
	private int tileImageWidth;
	private int tileImageHeight;
	private BufferedImage tileImage;
	
	public MapTile(int x, int y, String mi) {
		tileImageName = mi;
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
	
	public String getTileImageName() {
		return tileImageName;
	}
	
	public String toString() {
		return "" + tileImageName;
	}
}
