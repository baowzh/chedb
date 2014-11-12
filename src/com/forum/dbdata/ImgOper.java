package com.forum.dbdata;


import java.awt.Canvas;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.AreaAveragingScaleFilter;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;


public class ImgOper {

    public static BufferedImage createZoomSizeImage(BufferedImage image,double zoomRatio)  
    {       
    	int width = new Double(image.getWidth(null) * zoomRatio).intValue();     
    	int height = new Double(image.getHeight(null) * zoomRatio).intValue();     
    	AreaAveragingScaleFilter areaAveragingScaleFilter = new AreaAveragingScaleFilter(width,height);     
    	FilteredImageSource filteredImageSource = new FilteredImageSource(image.getSource(),areaAveragingScaleFilter);     
    	BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);     
    	Graphics graphics = bufferedImage.createGraphics();     
    	
    	Component component = new Canvas();     
    	graphics.drawImage(component.createImage(filteredImageSource),0,0,null);     

   		return bufferedImage;  
    }   
    
}
