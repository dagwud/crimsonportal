/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com;

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
}
