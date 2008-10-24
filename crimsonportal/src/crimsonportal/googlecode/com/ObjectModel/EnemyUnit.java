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
        double moveX = (double) Math.round(moveSpeed * Math.cos(moveAngleRadians));
        double moveY = (double) Math.round(moveSpeed * Math.sin(moveAngleRadians));
                
        getLocation().setX( getLocation().getX() + moveX);
        getLocation().setY( getLocation().getY() + moveY);
    }
    
     // This moves to EnemyUnit.getRotation:
    
    @Override 
    public double getRotation()
    {
        double distY = getLocation().getY() + (getSize() / 2) - getStrategy().getTarget().getY();
        double distX = getLocation().getX() + (getSize() / 2) - getStrategy().getTarget().getX();
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
