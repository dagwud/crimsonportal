/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

import crimsonportal.googlecode.com.Proxy.SpriteProxy;
import java.awt.Dimension;
import java.awt.Image;
import java.util.Random;

/**
 *
 * @author dagwud
 */
public class Map 
{
    public Map()
    {
        SpriteProxy spriteProxy = new SpriteProxy();
        bgImage = spriteProxy.get(GameState.landscapeName + ".jpg").toImage();
        this.height = bgImage.getHeight(null);
        this.width = bgImage.getWidth(null);
    }
    
    public int getWidth()
    {
        return width;
    }
    
    public int getHeight()
    {
        return height;
    }
    
    public Dimension getSize() 
    {
        return new Dimension(width, height);
    }
    
    protected void setWidth(int width)
    {
        this.width = width;
    }
    
    protected void setHeight(int height)
    {
        this.height = height;
    }
    
    public int getOffsetX()
    {
        return offsetX;
    }
    
    public int getOffsetY()
    {
        return offsetY;
    }
    
    protected void setOffsetX(int offsetX)
    {
        this.offsetX = offsetX;
    }
    
    protected void setOffsetY(int offsetY)
    {
        this.offsetY = offsetY;
    }
    
    public String getImageFilename()
    {
        return GameState.landscapeName + ".jpg";
    }
    
    public Image getBGImage()
    {
        return bgImage;
    }
    
    public Location getRandomLocation()
    {
        Random randomGenerator = new Random();
        
        double randomY = randomGenerator.nextDouble() * height;
        double randomX = randomGenerator.nextDouble() * width;
        
        Location loc = new Location(randomX, randomY);
        return loc;
    }
    
    public Location getCentreOfMap() {
        double posY = (double)height / 2.0;
        double posX = (double)width / 2.0;
        return new Location(posX, posY);
    }
    
    protected int width;
    protected int height;
    protected int offsetX;
    protected int offsetY;
    protected Image bgImage;
        
}
