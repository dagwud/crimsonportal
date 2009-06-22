/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.Proxy;

/**
 *
 * @author dagwud
 */
public class SpriteProxy extends Proxy<Sprite>
{
    public Sprite generateProxyObject(String key)
    {
        return new Sprite("images/" + key);
    }
}
