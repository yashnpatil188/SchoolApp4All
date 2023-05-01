package org.com.maauli;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

public class TextToImage {

	static int screenWidth;

	static int screenHeight;

	static int mainCentre;

	static Logger logger = Logger.getLogger(TextToImage.class.getName());

	static ResourceBundle bundle = ResourceBundle.getBundle("org.com.accesser.school");
	
	Common commonObj = new Common();
	
	private static final int IMG_WIDTH = 110;
	
	private static final int IMG_HEIGHT = 140;

	public void Text2Image(String text, String filename, String imagePath) {
	    try {
		    BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		    Graphics2D g2d = img.createGraphics();
		    Font font = new Font("MANGAL", Font.PLAIN, 24);
		    g2d.setFont(font);
		    FontMetrics fm = g2d.getFontMetrics();
		    int width = fm.stringWidth(text);
//		    int width = 300;
//		    int height = fm.getHeight();
		    int height = 30;
		    int y = 20;
			int yShift = 60;
		    g2d.dispose();
		    
//		    System.out.println(text + " : " + width+" : "+height);

		    img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		    g2d = img.createGraphics();
		    g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,
		        RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		        RenderingHints.VALUE_ANTIALIAS_ON);
		    g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
		        RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		    g2d.setRenderingHint(RenderingHints.KEY_DITHERING,
		        RenderingHints.VALUE_DITHER_ENABLE);
		    g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
		        RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
		        RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		    g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
		        RenderingHints.VALUE_RENDER_QUALITY);
		    g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
		        RenderingHints.VALUE_STROKE_PURE);
		    g2d.setFont(font);
		    fm = g2d.getFontMetrics();
		    g2d.setColor(Color.BLACK);
//		    g2d.drawString(text, 0, fm.getAscent());
		    g2d.drawString(text, 0, y); y+=yShift;
		    g2d.drawString(text, 0, y); y+=yShift;
		    g2d.dispose();
		    
			ImageIO.write(img, "png", new File(imagePath+Common.replaceCommaApostrophy(filename)+".jpg"));
		} catch (IOException e) {
			commonObj.logException(e);
		}
	}
}
