/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

/**
 *
 * @author dagwud
 */
public class Bullet extends GameObject
{
    public Bullet(double size, Location location, Strategy strategy, double moveSpeed, int attackDamage)
    {
        super(size, location);
        this.strategy = strategy;
        this.moveSpeed = moveSpeed;
        this.attackDamage = attackDamage;
    }
    
    public double getMoveSpeed()
    {
        return moveSpeed;
    }
    
    protected void setMoveSpeed(double moveSpeed)
    {
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
    
    public Strategy getStrategy()
    {
        return strategy;
    }
    
    protected void setStrategy(Strategy strategy)
    {
        this.strategy = strategy;
    }
    
    public String getSpriteFilename()
    {
        return "bullet.gif";
    }
        
    protected Strategy strategy;
    protected double moveSpeed;
    private int attackDamage;
}
