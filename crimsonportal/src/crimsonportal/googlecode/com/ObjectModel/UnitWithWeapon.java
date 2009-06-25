/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

/**
 *
 * @author jdevenish
 */
public abstract class UnitWithWeapon extends Unit {
    public UnitWithWeapon(double radius, Location location, 
            Strategy strategy, GameState gameState) {
        super(radius, location, strategy, gameState);
    }
    
    public Weapon getWeapon() {
        return weapon;
    }
    
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
    
    private Weapon weapon;
}
