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
            Strategy strategy, GameState gameState, Weapon weapon) {
        super(radius, location, strategy, gameState);
        this.weapon = weapon;
    }
    
    public Weapon getWeapon() {
        return weapon;
    }
    
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
    
    protected static final int PRESET_ATTACKSPEED_SLOW = 1;
    protected static final int PRESET_ATTACKSPEED_MODERATE = 2;
    protected static final int PRESET_ATTACKSPEED_FAST = 3;
    protected static final int PRESET_ATTACKDAMAGE_ANNOY = 1;
    protected static final int PRESET_ATTACKDAMAGE_HIT = 2;
    protected static final int PRESET_ATTACKDAMAGE_HURT = 3;
    protected static final int PRESET_ATTACKDAMAGE_MAUL = 4;
    
    protected Weapon weapon;
}
