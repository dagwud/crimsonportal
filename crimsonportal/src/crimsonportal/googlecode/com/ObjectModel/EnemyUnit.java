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
    public EnemyUnit(int size, int attackDamage, int moveSpeed, Location location, Location target)
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
        EnemyUnit e = new EnemyUnit(size, attackDamage, moveSpeed, getLocation(), getStrategy().getTarget());
        return e;
    }
    
    protected void move()
    {
        double diffY = getStrategy().getTarget().getY() - this.getLocation().getY();
        double diffX = getStrategy().getTarget().getX() - this.getLocation().getX();
        
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
        int moveX = (int) ( Math.round(moveSpeed * Math.cos(moveAngleRadians)) );
        int moveY = (int) ( Math.round(moveSpeed * Math.sin(moveAngleRadians)) );
                
        getLocation().setX( getLocation().getX() + moveX);
        getLocation().setY( getLocation().getY() + moveY);
    }
    
    private int attackDamage;
    private int moveSpeed;
    private int size;
}