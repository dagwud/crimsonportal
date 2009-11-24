/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

import crimsonportal.googlecode.com.Debug;

/**
 *
 * @author jdevenish
 */
public abstract class UnitWithWeapon extends Unit {
    public UnitWithWeapon() {
        super();
    }
    
    public UnitWithWeapon(Double radius, Location location, 
            Strategy strategy, GameState gameState, Weapon weapon) {
        super(radius, location, strategy, gameState);
        this.weapon = weapon;
        currentLevel = 1;
    }
    
    public Weapon getWeapon() {
        return weapon;
    }
    
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
    
    public double getExperience() {
        return experience;
    }
    
    public void setExperience(double experience) {
        this.experience = experience;
        while (this.experience >= getExperienceRequirementForNextLevel()) {
            // Level up:
            incrementLevel();
        }
    }
    
    public void incrementLevel() {
        this.currentLevel++;
        Debug.logLevelUp(gameState.getGameTime());
    }
    
    public int getLevel() {
        return currentLevel;
    }
    
    public abstract Double getExperienceRequirementForNextLevel();
    
    protected double experience;
    protected int currentLevel;
    protected static final int PRESET_ATTACKSPEED_SLOW = 1;
    protected static final int PRESET_ATTACKSPEED_MODERATE = 2;
    protected static final int PRESET_ATTACKSPEED_FAST = 3;
    protected static final int PRESET_ATTACKDAMAGE_ANNOY = 1;
    protected static final int PRESET_ATTACKDAMAGE_HIT = 2;
    protected static final int PRESET_ATTACKDAMAGE_HURT = 3;
    protected static final int PRESET_ATTACKDAMAGE_MAUL = 4;
    
    protected Weapon weapon;
}
