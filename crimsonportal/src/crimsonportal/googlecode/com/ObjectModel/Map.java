/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

/**
 *
 * @author dagwud
 */
public class Map 
{
    public Map(int width, int height)
    {
        this.width = width;
        this.height = height;
    }
    
    public Map(int width, int height, int offsetX, int offsetY)
    {
        this.width = width;
        this.height = height;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }
    
    public int getWidth()
    {
        return width;
    }
    
    public int getHeight()
    {
        return height;
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
    
    protected int width;
    protected int height;
    protected int offsetX;
    protected int offsetY;
}
