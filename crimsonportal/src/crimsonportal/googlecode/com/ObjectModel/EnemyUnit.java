/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

import crimsonportal.googlecode.com.Debug;
import crimsonportal.googlecode.com.Factories.EnemyUnitFactory;

/**
 *
 * @author dagwud
 */
public abstract class EnemyUnit extends UnitWithWeapon
{
    protected EnemyUnit() {
        super();
    }
    
    protected EnemyUnit(Double size, Location location, GameState gameState, 
            double startingHealth, int moveSpeed, GameObject target)
    {
        super(size, location, new Strategy(target), 
                gameState, null);
        this.getMovementHandler().moveSpeed = moveSpeed;
        this.health = startingHealth;
        weapon = getDefaultWeapon();
    }
    
    public abstract Weapon getDefaultWeapon();
    
    @Override
    public abstract EnemyUnit clone();
    
    protected void move() {
        getMovementHandler().move(this);
    }
    
    public void attack(Unit unitToAttack) {
        if (getWeapon() == null) {
            throw new RuntimeException(getClass().getName() + " does not have weapon set");
        }

        long timeSinceLastAttackMS = System.currentTimeMillis() - lastAttackTime;
        double attackDurationMS = 1000.0 / getWeapon().getAttackSpeed();
        if (timeSinceLastAttackMS >= attackDurationMS)
        {
            lastAttackTime = System.currentTimeMillis();
            int damage = getWeapon().getAttackDamage();
            unitToAttack.setHealth(unitToAttack.getHealth() - damage);
            if (Debug.checkFlag(Debug.flagKey.LOGATTACKDAMAGE)) {
                Debug.logAttack(this.getEnemyTypeEnum(), damage);
            }
        }
    }
    
    @Override 
    public double getRotation()
    {
        double distY = getCentreOfObject().getY() - getStrategy().getTarget().getCentreOfObject().getY();
        double distX = getCentreOfObject().getX() - getStrategy().getTarget().getCentreOfObject().getX();
        double rotation2 = Math.atan2(distY, distX) + Math.PI; // Using PI is quicker than Math.toRadians(180);
        return rotation2;
    }
    
    @Override
    public double getExperienceValueToKiller()
    {
        double scaleDamage = 1;
        double scaleAttackSpeed = 1;
        double scaleMoveSpeed = 1;
        if (getWeapon() != null) {
            scaleDamage = getWeapon().getAttackDamage();
            scaleAttackSpeed = getWeapon().getAttackSpeed();
        } else {
            scaleDamage = getDefaultWeapon().getAttackDamage();
            scaleAttackSpeed = getDefaultWeapon().getAttackSpeed();
        }
        scaleMoveSpeed = getMovementHandler().getMoveSpeed();
        
        return scaleDamage * scaleAttackSpeed * scaleMoveSpeed;
    }

    @Override
    public Double getExperienceRequirementForNextLevel()
    {
        return null;
    }
    
    public abstract String getSpriteFilename();
    
    public abstract EnemyUnitFactory.enemyType getEnemyTypeEnum();
    
    private long lastAttackTime;

    protected static final int PRESET_MOVESPEED_AMBLE = 1;
    protected static final int PRESET_MOVESPEED_WALK = 2;
    protected static final int PRESET_MOVESPEED_TROT = 3;
    protected static final int PRESET_MOVESPEED_RACE = 4;
    protected static final double PRESET_SIZE_TINY = 10d;
    protected static final double PRESET_SIZE_SMALL = 14d;
    protected static final double PRESET_SIZE_MEDIUM = 15d;
    protected static final double PRESET_SIZE_LARGE = 20d;
    protected static final double PRESET_SIZE_HUGE = 35d;
}
