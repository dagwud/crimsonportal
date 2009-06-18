/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

import crimsonportal.googlecode.com.Proxy.SpriteProxy;
import java.awt.Dimension;
import java.awt.Image;

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
    
    protected int width;
    protected int height;
    protected int offsetX;
    protected int offsetY;
    protected Image bgImage;
        
}
