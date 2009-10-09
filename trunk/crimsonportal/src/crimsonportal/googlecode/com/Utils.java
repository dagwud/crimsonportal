/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jdevenish
 */
public class Utils {
    private static Map<Double, Double> sinMap;
    private static Map<Double, Double> cosMap;
    
    private static void initSinMap(int sliceSize) {
        if (Math.floor(360.0 / (double)sliceSize) != 360.0 / (double)sliceSize) {
            throw new RuntimeException("Cannot initialise SinMap with slices of " + sliceSize + ": 360 cannot be divided by " + sliceSize + " without a remainder");
        }
        int mapSlices = 360 / sliceSize;
        System.out.println("Initialising SinMap with " + mapSlices + " keys, " + sliceSize + "degrees apart");
        sinMap = new HashMap<Double, Double>();
        for (int d = 0; d < mapSlices; d++) {
            double angle = d * sliceSize;
            sinMap.put(angle, Math.sin(Math.toRadians(angle)));
        }
    }
    
    private static void initCosMap(int sliceSize) {
        if (Math.floor(360.0 / (double)sliceSize) != 360.0 / (double)sliceSize) {
            throw new RuntimeException("Cannot initialise CosMap with slices of " + sliceSize + ": 360 cannot be divided by " + sliceSize + " without a remainder");
        }
        int mapSlices = 360 / sliceSize;
        System.out.println("Initialising CosMap with " + mapSlices + " keys, " + sliceSize + "degrees apart");
        cosMap = new HashMap<Double, Double>();
        for (int d = 0; d < mapSlices; d++) {
            double angle = d * sliceSize;
            cosMap.put(angle, Math.cos(Math.toRadians(angle)));
        }
    }
    
    public static double sin(double angleRadians) {
        if (sinMap == null) initSinMap(5);
        
        double angleDegrees = Math.toDegrees(angleRadians);
        
        // Round degrees to the nearest multiple of 10:
        angleDegrees = Math.round(angleDegrees / 10) * 10;
        while (angleDegrees >= 360) angleDegrees -= 360;
        
        Double ret = sinMap.get(angleDegrees);
        if (ret == null) {
            throw new RuntimeException("Sin of " + angleDegrees + " degrees is not in sinmap");
        }
        return ret;
    }
    
    public static double cos(double angleRadians) {
        if (cosMap == null) initCosMap(5);
        
        double angleDegrees = Math.toDegrees(angleRadians);
        
        // Round degrees to the nearest multiple of 10:
        angleDegrees = Math.round(angleDegrees / 10) * 10;
        while (angleDegrees >= 360) angleDegrees -= 360;
        
        Double ret = cosMap.get(angleDegrees);
        if (ret == null) {
            throw new RuntimeException("Cos of " + angleRadians + "rad (approx " + angleDegrees + "deg) is not in cosmap");
        }
        return ret;
    }
}
