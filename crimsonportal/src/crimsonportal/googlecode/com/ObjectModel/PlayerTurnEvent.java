/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

import java.util.EventObject;

/**
 *
 * @author dagwud
 */
public class PlayerTurnEvent extends EventObject
{
    public PlayerTurnEvent(PlayerUnit playerToMove, double newRotation)
    {
        super(playerToMove);
        this.newRotation = newRotation;
    }
    
    public PlayerTurnEvent(PlayerUnit playerToMove, Location turnTowardsLocation) 
    {
        super(playerToMove);
        double curX = playerToMove.getCentreOfObject().getX();
        double curY = playerToMove.getCentreOfObject().getY();
        
        double toX = turnTowardsLocation.getX();
        double toY = turnTowardsLocation.getY();
        
        double diffY = toY - curY;
        double diffX = toX - curX;
        
        double rotation = 0;
        rotation = Math.atan2(diffY, diffX);
        this.newRotation = rotation;
    }
    
    public double getRotation()
    {
        return newRotation;
    }
    
    private double newRotation;
}
