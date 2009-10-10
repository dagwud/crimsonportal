/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel.Weapons;

/**
 *
 * @author dagwud
 */
public interface UnitWithArmour {
    public double getArmourPercentage();
    
    public void setArmourPercentage(double perc);
    
    public void reduceArmour(double percentagePoints);
    
    public double getArmourStrength();
    
    public void setArmourStrength(double armourStrength);
}
