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
    
    public void move()
    {
        double moveAmount = moveSpeed;
        double moveAngleRadians = getRotation();
        double moveX = (double) Math.round(moveAmount * Math.cos(moveAngleRadians));
        double moveY = (double) Math.round(moveAmount * Math.sin(moveAngleRadians));
        
        getCentreOfObject().setX( getCentreOfObject().getX() + moveX);
        getCentreOfObject().setY( getCentreOfObject().getY() + moveY);
    }
    
    @Override
    public double getRotation()
    {
        if (rotation == 0)
        {
            double diffY = getStrategy().getTarget().getCentreOfObject().getY()
                    - this.getCentreOfObject().getY();
            double diffX = getStrategy().getTarget().getCentreOfObject().getX() 
                    - this.getCentreOfObject().getX();
            rotation = Math.atan2(diffY, diffX);
        }
        return rotation;
    }
        
    public Bullet clone()
    {
        Bullet bullet = new Bullet(size, location.clone(), 
                strategy.clone(), moveSpeed, attackDamage);
        return bullet;
    }
    
    protected Strategy strategy;
    protected double moveSpeed;
    private int attackDamage;

    void attack(EnemyUnit enemy)
    {
        int health = enemy.getHealth();
        health = health - attackDamage;
        enemy.setHealth(health);
    }
}
