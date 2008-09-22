/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com;

/**
 *
 * @author dagwud
 */
public class EnemyUnit extends Unit
{
    public EnemyUnit(int attackDamage, int moveSpeed, Location location, Location target)
    {
        super(location);
        this.attackDamage = attackDamage;
        this.moveSpeed = moveSpeed;
        this.strategy = new Strategy(target);
    }
    
    public int getAttackDamage()
    {
        return attackDamage;
    }
    
    public void setAttackDamage(int attackDamage)
    {
        this.attackDamage = attackDamage;
    }
    
    public int getMoveSpeed()
    {
        return moveSpeed;
    }
    
    public void setMoveSpeed(int moveSpeed)
    {
        this.moveSpeed = moveSpeed;
    }
    
    @Override
    public EnemyUnit clone()
    {
        EnemyUnit e = new EnemyUnit(attackDamage, moveSpeed, location, strategy.target);
        return e;
    }
    
    public void move()
    {
        if (location.x < strategy.target.x)
        {
            location.x++;
        }
        else if (location.x > strategy.target.x)
        {
            location.x--;
        }
        
        if (location.y < strategy.target.y)
        {
            location.y++;
        }
        else if (location.y > strategy.target.y)
        {
            location.y--;
        }
    }
    
    protected int attackDamage;
    protected int moveSpeed;
}
