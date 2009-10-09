/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.Proxy;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author dagwud
 */
public class Sprite implements ProxyObject
{
    public Sprite(String filename)
    {
        try
        {
            img = ImageIO.read(new File(filename));
        }
        catch (IOException e)
        {
            System.err.println("Unable to load sprite file \"" + filename + "\": " + e.getMessage());
        }
    }
    
    public BufferedImage toImage()
    {
        return img;
    }
    
    public ImageIcon toImageIcon() {
        return new ImageIcon(img);
    }
    
    BufferedImage img;
}
