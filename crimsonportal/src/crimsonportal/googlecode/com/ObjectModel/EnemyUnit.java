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
    public EnemyUnit(int attackDamage, int moveSpeed, Location location, Location target)
    {
        super(location, new Strategy(target));
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
        EnemyUnit e = new EnemyUnit(attackDamage, moveSpeed, getLocation(), getStrategy().getTarget());
        return e;
    }
    
    protected void move()
    {
        double diffY = this.getLocation().getY() - getStrategy().getTarget().getY();
        double diffX = this.getLocation().getX() - getStrategy().getTarget().getX();
        
        double moveAngle = Math.toRadians(0);
        if (diffX != 0)
        {
            moveAngle = Math.atan2(diffY, diffX);
        }
        else
        {
            if (diffY > 0) 
            {
                moveAngle = Math.toRadians(0);
            }
            else if (diffY < 0)
            {
                moveAngle = Math.toRadians(180);
            }
            else
            {
                return;
            }
        }
        int moveX = (int) ( Math.round(moveSpeed * Math.cos(moveAngle)) );
        int moveY = (int) ( Math.round(moveSpeed * Math.sin(moveAngle)) );
                
        getLocation().setX( getLocation().getX() - moveX);
        getLocation().setY( getLocation().getY() - moveY);
    }
    
    private int attackDamage;
    private int moveSpeed;
}
