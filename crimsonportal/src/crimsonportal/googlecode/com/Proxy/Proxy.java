/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.Proxy;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author dagwud
 */
public abstract class Proxy<ProxyObject>
{
    public Proxy()
    {
        cache = new HashMap<String, ProxyObject>();
    }
    
    public final ProxyObject get(String key)
    {
        if (cache.containsKey(key))
        {
            return cache.get(key);
        }
        ProxyObject object = generateProxyObject(key);
        cache.put(key, object);
        return object;
    }
    
    public abstract ProxyObject generateProxyObject(String key);
    
    private Map<String, ProxyObject> cache;
}
