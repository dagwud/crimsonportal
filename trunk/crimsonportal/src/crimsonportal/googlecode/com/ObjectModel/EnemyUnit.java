/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

import crimsonportal.googlecode.com.Debug;

/**
 *
 * @author dagwud
 */
public abstract class EnemyUnit extends Unit
{
    protected EnemyUnit(double size, double startingHealth, int attackDamage, double attackSpeed, int moveSpeed, Location location, GameObject target, GameState gameState)
    {
        super(size, location, new Strategy(target), gameState);
        this.attackDamage = attackDamage;
        this.moveSpeed = moveSpeed;
        this.attackSpeed = attackSpeed;
        this.health = startingHealth;
    }
    
    public int getAttackDamage()
    {
        return attackDamage;
    }
    
    protected void setAttackDamage(int attackDamage)
    {
        this.attackDamage = attackDamage;
    }
    
    public double getAttackSpeed()
    {
        return attackSpeed;
    }
    
    protected void setAttackSpeed(double attackSpeed)
    {
        this.attackSpeed = attackSpeed;
    }
    
    public void attack(PlayerUnit player)
    {
        long timeSinceLastAttackMS = System.currentTimeMillis() - lastAttackTime;
        double attackDurationMS = 1000.0 / attackSpeed;
        if (timeSinceLastAttackMS >= attackDurationMS)
        {
            lastAttackTime = System.currentTimeMillis();
            player.setHealth(player.getHealth() - attackDamage);
        }
    }
    
    @Override
    public abstract EnemyUnit clone();
    
    protected void move()
    {
        if (Debug.checkFlag(Debug.flagKey.DISABLE_ENEMY_MOVEMENT)) return;
        Location targetLoc = getStrategy().getTarget().getCentreOfObject();
        Location thisLoc = getCentreOfObject();
        double diffY = targetLoc.getY() - thisLoc.getY();
        double diffX = targetLoc.getX() - thisLoc.getX();
        double moveAmount = moveSpeed;
        double distBeforeMove = Math.sqrt((diffY * diffY) + (diffX * diffX));
        if (getRadius() + getStrategy().getTarget().getRadius() >= distBeforeMove )
        {
            return;
        }
        
        double moveAngleRadians = Math.toRadians(0);
        if (diffX != 0)
        {
            moveAngleRadians = Math.atan2(diffY, diffX);
        }
        else
        {
            if (diffY > 0) 
            {
                moveAngleRadians = Math.toRadians(0);
            }
            else if (diffY < 0)
            {
                moveAngleRadians = Math.toRadians(180);
            }
            else
            {
                // diffX = 0 and diffY = 0; Enemy is on top of player; don't move:
                return; 
            }
        }
        double moveX = (double) Math.round(moveAmount * Math.cos(moveAngleRadians));
        double moveY = (double) Math.round(moveAmount * Math.sin(moveAngleRadians));
        
        // Calculate how far from the target the enemyUnit will be after the move: 
        double newDiffY = getStrategy().getTarget().getCentreOfObject().getY() - (this.getCentreOfObject().getY() + moveY);
        double newDiffX = getStrategy().getTarget().getCentreOfObject().getX() - (this.getCentreOfObject().getX() + moveX);
        double distAfterMove = Math.sqrt((newDiffY * newDiffY) + (newDiffX * newDiffX));
        if ( distAfterMove >= distBeforeMove )
        {
            moveX = 0.0;
            moveY = 0.0;
        }
        
        Location newLocation = new Location(
            getCentreOfObject().getX() + moveX,
            getCentreOfObject().getY() + moveY);
        moveTo(newLocation);
    }
    
    @Override 
    public double getRotation()
    {
        double distY = getCentreOfObject().getY() - getStrategy().getTarget().getCentreOfObject().getY();
        double distX = getCentreOfObject().getX() - getStrategy().getTarget().getCentreOfObject().getX();
        double rotate = Math.toRadians(180) + Math.atan2(distY, distX);
        return rotate;
    }
    
    public abstract String getSpriteFilename();
            
    protected int attackDamage;
    protected double attackSpeed;
    private long lastAttackTime;
    
    protected static final int PRESET_MOVESPEED_AMBLE = 1;
    protected static final int PRESET_MOVESPEED_WALK = 2;
    protected static final int PRESET_MOVESPEED_TROT = 3;
    protected static final int PRESET_MOVESPEED_RACE = 4;
    protected static final int PRESET_ATTACKSPEED_SLOW = 1;
    protected static final int PRESET_ATTACKSPEED_MODERATE = 2;
    protected static final int PRESET_ATTACKSPEED_FAST = 3;
    protected static final int PRESET_ATTACKDAMAGE_ANNOY = 1;
    protected static final int PRESET_ATTACKDAMAGE_HIT = 2;
    protected static final int PRESET_ATTACKDAMAGE_HURT = 3;
    protected static final int PRESET_ATTACKDAMAGE_MAUL = 4;
    protected static final double PRESET_SIZE_TINY = 10d;
    protected static final double PRESET_SIZE_SMALL = 14d;
    protected static final double PRESET_SIZE_MEDIUM = 15d;
    protected static final double PRESET_SIZE_LARGE = 20d;
    protected static final double PRESET_SIZE_HUGE = 35d;
    
}
