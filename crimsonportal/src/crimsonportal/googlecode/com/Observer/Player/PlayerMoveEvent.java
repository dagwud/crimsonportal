/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.Observer.Player;

import crimsonportal.googlecode.com.ObjectModel.PlayerUnit;
import java.util.EventObject;

/**
 *
 * @author dagwud
 */
public class PlayerMoveEvent extends EventObject
{
    public PlayerMoveEvent(PlayerUnit playerToMove, double moveAmountX, double moveAmountY)
    {
        super(playerToMove);
        this.moveAmountX = moveAmountX;
        this.moveAmountY = moveAmountY;
    }
    
    public double getMoveAmountX()
    {
        return moveAmountX;
    }
    
    public double getMoveAmountY()
    {
        return moveAmountY;
    }
    
    private double moveAmountX;
    private double moveAmountY;
}
