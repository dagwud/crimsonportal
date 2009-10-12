/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

import crimsonportal.googlecode.com.Debug;
import crimsonportal.googlecode.com.Utils;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
        
        // If using attack-and-retreat movement style, check that the required
        // methods have been overridden:
        if (getMovementType() == MovementType.MOVEMENT_ATTACKANDRETREAT) {
            if (getAttackAndRetreat_RetreatDistance() == null) {
                throw new RuntimeException("Enemy has momentType = MOVEMENT_ATTACKANDRETREAT but has not overridden getAttackAndRetreat_RetreatDistance() method");
            }
        }
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
        if (getMovementType() == MovementType.MOVEMENT_ATTACKANDRETREAT) {
            if (attackAndRetreat_retreatProgress >= 0) {
                // Retreating; don't allow an attack
                return;
            } else {
                // Start retreat (after this attack):
                attackAndRetreat_retreatProgress = getAttackAndRetreat_RetreatDistance(); 
            }
        }
        
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
    
    public abstract MovementType getMovementType();
    
    protected void move() {
        MovementType movetype = getMovementType();
        switch (movetype) {
            case MOVEMENT_STRAIGHTLINE:
                moveStraightLine();
                break;
                
            case MOVEMENT_ATTACKANDRETREAT:
                moveAttackAndRetreat();
                break;
                
            default:
                throw new RuntimeException("Unknown movement type " + movetype);
        }
    }
    
    private void moveStraightLine() {
        moveStraightLine(false);
    }
    
    private void moveStraightLine(boolean isRetreating)
    {
        if (Debug.checkFlag(Debug.flagKey.DISABLE_ENEMY_MOVEMENT)) return;
        Location targetLoc = getStrategy().getTarget().getCentreOfObject();
        Location thisLoc = getCentreOfObject();
        double diffY = targetLoc.getY() - thisLoc.getY();
        double diffX = targetLoc.getX() - thisLoc.getX();
        double moveAmount = moveSpeed;
        // If not retreating, check that moving forward would actually mean getting
        // closer to the target:
        double distBeforeMove = Math.sqrt((diffY * diffY) + (diffX * diffX));
        if (!isRetreating) {
            if (getRadius() + getStrategy().getTarget().getRadius() >= distBeforeMove )
            {
                return;
            }
        }
        
        double moveAngleRadians = 0;
        if (diffX != 0)
        {
            moveAngleRadians = Math.atan2(diffY, diffX);
        }
        else
        {
            if (diffY > 0) 
            {
                moveAngleRadians = 0;
            }
            else if (diffY < 0)
            {
                moveAngleRadians = Math.PI; // Using PI is quicker than Math.toRadians(180);
            }
            else
            {
                // diffX = 0 and diffY = 0; Enemy is on top of player; don't move:
                return; 
            }
        }
        if (isRetreating) {
            // Retreat: Adjust moveAngleRadians by Math.PI (180deg) which will 
            // take the EnemyUnit away from its target:
            moveAngleRadians = moveAngleRadians + Math.PI; 
        }
        double moveX = (double) Math.round(moveAmount * Utils.cos(moveAngleRadians));
        double moveY = (double) Math.round(moveAmount * Utils.sin(moveAngleRadians));
        
        // Calculate how far from the target the enemyUnit will be after the move: 
        double newDiffY = targetLoc.getY() - (this.getCentreOfObject().getY() + moveY);
        double newDiffX = targetLoc.getX() - (this.getCentreOfObject().getX() + moveX);
        if (!isRetreating) {
            double distAfterMove = Math.sqrt((newDiffY * newDiffY) + (newDiffX * newDiffX));
            // If the EnemyUnit is trying to get to its target, ensure that it 
            // doesn't accidentally move further away:
            if ( distAfterMove >= distBeforeMove )
            {
                moveX = 0.0;
                moveY = 0.0;
            }
        }
        
        Location newLocation = new Location(
            getCentreOfObject().getX() + moveX,
            getCentreOfObject().getY() + moveY);
        moveTo(newLocation);
    }
    
    private void moveAttackAndRetreat() {
        boolean isRetreating = false;
        if (attackAndRetreat_retreatProgress >= 0) {
            // Retreating:
            isRetreating = true;
            attackAndRetreat_retreatProgress--;
            if (attackAndRetreat_retreatProgress <= 0) {
                // Stop retreating:
                attackAndRetreat_retreatProgress = -1; // Start attack
                isRetreating = false;
            }
        }
        moveStraightLine(isRetreating);
    }
    
    @Override 
    public double getRotation()
    {
        double distY = getCentreOfObject().getY() - getStrategy().getTarget().getCentreOfObject().getY();
        double distX = getCentreOfObject().getX() - getStrategy().getTarget().getCentreOfObject().getX();
        double rotation = Math.atan2(distY, distX) + Math.PI; // Using PI is quicker than Math.toRadians(180);
        return rotation;
    }
    
    protected Integer getAttackAndRetreat_RetreatDistance() {
        return null;
    }
    
    public abstract String getSpriteFilename();
            
    protected int attackDamage;
    protected double attackSpeed;
    private long lastAttackTime;
    
    private int attackAndRetreat_retreatProgress = 0;
    
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
    public static enum MovementType {MOVEMENT_STRAIGHTLINE, MOVEMENT_ATTACKANDRETREAT};
}
