/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com;

import java.util.Map;

/**
 *
 * @author dagwud
 */
public class SpriteProxy
{
    public SpriteProxy()
    {
        super();
    }
    
    public Sprite get(String URL)
    {
        if (loadedSprites.containsKey(URL))
        {
            return loadedSprites.get(URL);
        }
        
        Sprite s = new Sprite(URL);
        loadedSprites.put(URL, s);
        return s;
    }
    
    protected Map<String, Sprite> loadedSprites;
}
