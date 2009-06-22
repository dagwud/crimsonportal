/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author dagwud
 */
public abstract class Debug 
{
    public static final int DEBUG_VERBOSE = 4;
    public static final int DEBUG_ERRORS = 3;
    public static final int DEBUG_WARNINGS = 2;
    public static final int DEBUG_INFO = 1;
    public static final int DEBUG_NONE = 0;
    
    public static final int enabledLevel = DEBUG_WARNINGS;
    
    public static synchronized void logMethod(String string)
    {
        if (enabledLevel < DEBUG_VERBOSE)
        {
            return;
        }
        System.out.println(string);
    }
    
    public static synchronized void logWarning(String string)
    {
        if (enabledLevel < DEBUG_WARNINGS)
        {
            return;
        }
        System.err.println(string);
    }
    
    public static synchronized void print(String string)
    {
        if (enabledLevel < DEBUG_INFO) 
        {
            return;
        }
        System.out.print(string);
    }
    
    public static void setFlag(flagKey flag, flagValue value) {
        //if (getFlagValue(flag) != value) System.out.println(flag + " --> " + value);
        flags.put(flag, value);
    }
    
    public static flagValue getFlagValue(flagKey flag) {
        flagValue v = flags.get(flag);
        if (v == null) {
            v = Debug.flagValue.NOT_SET;
        }
        return v;
    }
    
    public static boolean checkFlag(flagKey flag) {
        flagValue v = getFlagValue(flag);
        if (v == flagValue.NOT_SET) return false;
        return (v == flagValue.TRUE);
    }
    
    protected static Map<flagKey, flagValue> flags;
    static {
        flags = new HashMap<Debug.flagKey, Debug.flagValue>();
    }
    
    public static enum flagKey {
        DISABLE_ENEMY_SPAWNING,
        PLAYER_MOVEMENT_VERTICAL
    };
    
    public static enum flagValue {
        TRUE,
        FALSE,
        
        ASCENDING,
        DESCENDING,
        LEVEL,
        
        NOT_SET
    };
}
