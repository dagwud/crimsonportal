/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

/**
 *
 * @author dagwud
 */
public class EnemyUnit extends Unit
{
    public EnemyUnit(int size, int attackDamage, int moveSpeed, Location location, GameObject target)
    {
        super(size, location, new Strategy(target));
        this.attackDamage = attackDamage;
        this.moveSpeed = moveSpeed;
    }
    
    public int getAttackDamage()
    {
        return attackDamage;
    }
    
    protected void setAttackDamage(int attackDamage)
    {
        this.attackDamage = attackDamage;
    }
    
    public int getMoveSpeed()
    {
        return moveSpeed;
    }
    
    protected void setMoveSpeed(int moveSpeed)
    {
        this.moveSpeed = moveSpeed;
    }
    
    public void attack(PlayerUnit player)
    {
        player.setHealth(player.getHealth() - attackDamage);
    }
    
    @Override
    public EnemyUnit clone()
    {
        EnemyUnit e = new EnemyUnit(size, attackDamage, moveSpeed, getCentreOfObject(), getStrategy().getTarget());
        return e;
    }
    
    protected void move()
    {
        double diffY = getStrategy().getTarget().getCentreOfObject().getY() - this.getCentreOfObject().getY();
        double diffX = getStrategy().getTarget().getCentreOfObject().getX() - this.getCentreOfObject().getX();
        double moveAmount = moveSpeed;
        double distBeforeMove = Math.sqrt((diffY * diffY) + (diffX * diffX));
        if (getSize() / 2.0 + getStrategy().getTarget().getSize() / 2.0 >= distBeforeMove )
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
        
        getCentreOfObject().setX( getCentreOfObject().getX() + moveX);
        getCentreOfObject().setY( getCentreOfObject().getY() + moveY);
    }
    
     // This moves to EnemyUnit.getRotation:
    
    @Override 
    public double getRotation()
    {
        double distY = getCentreOfObject().getY() - getStrategy().getTarget().getCentreOfObject().getY();
        double distX = getCentreOfObject().getX() - getStrategy().getTarget().getCentreOfObject().getX();
        double rotate = Math.toRadians(180) + Math.atan2(distY, distX);
        return rotate;
    }
    
    public String getSpriteFilename()
    {
        return "enemy.gif";
    }
    private int attackDamage;
    private int moveSpeed;
    private int size;
}
