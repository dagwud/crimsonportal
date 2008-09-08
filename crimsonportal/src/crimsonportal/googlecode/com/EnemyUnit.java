/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com;

/**
 *
 * @author dagwud
 */
public class EnemyUnit 
{
    public EnemyUnit(int attackDamage, int moveSpeed)
    {
        this.attackDamage = attackDamage;
        this.moveSpeed = moveSpeed;
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
        EnemyUnit e = new EnemyUnit(attackDamage, moveSpeed);
        return e;
    }
    
    protected int attackDamage;
    protected int moveSpeed;
}
