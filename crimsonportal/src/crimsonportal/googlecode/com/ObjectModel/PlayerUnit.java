/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

import crimsonportal.googlecode.com.GameSettings.ObjectSizes;

/**
 *
 * @author dagwud
 */
public class PlayerUnit extends Unit
{
    public PlayerUnit(Location location, int moveSpeed)
    {
        super(ObjectSizes.PLAYER_SIZE, location, new Strategy(new Location(0, 0)));
        this.moveSpeed = moveSpeed;
    }
    
    public PlayerUnit(Location location, int moveSpeed, Weapon weapon)
    {
        super(ObjectSizes.PLAYER_SIZE, location, new Strategy(new Location(0, 0)));
        this.weapon = weapon;
        this.moveSpeed = moveSpeed;
    }
    
    public Weapon getWeapon()
    {
        return weapon;
    }
    
    protected void setWeapon(Weapon weapon)
    {
        this.weapon = weapon;
    }
    
    public int getMoveSpeed()
    {
        return moveSpeed;
    }
    
    protected void setMoveSpeed(int moveSpeed)
    {
        this.moveSpeed = moveSpeed;
    }
    
    @Override
    public PlayerUnit clone()
    {
        PlayerUnit p = new PlayerUnit(getLocation(), moveSpeed, weapon);
        return p;
    }
    
    public String getSpriteFilename()
    {
        return "player.gif";
    }
    
    private Weapon weapon;
    private int moveSpeed;
}
