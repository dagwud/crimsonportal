/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com;

import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author dagwud
 */
public class Sprite
{
    public Sprite(String URL)
    {
        spriteImage = generateValue(URL);
    }
    
    public ImageIcon generateValue(String URL)
    {
        try
        {
            return new ImageIcon(new URL(URL));
        }
        catch (MalformedURLException e)
        {
            return new ImageIcon();
        }
    }
    
    protected ImageIcon spriteImage;
}
