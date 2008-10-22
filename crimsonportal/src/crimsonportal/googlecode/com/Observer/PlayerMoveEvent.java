/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.Observer;

import crimsonportal.googlecode.com.ObjectModel.PlayerUnit;

/**
 *
 * @author dagwud
 */
public class PlayerMoveEvent implements Event
{
    public PlayerMoveEvent(PlayerUnit playerToMove, double moveAmountX, double moveAmountY)
    {
        this.playerToMove = playerToMove;
        this.moveAmountX = moveAmountX;
        this.moveAmountY = moveAmountY;
    }
    
    public PlayerUnit getPlayerToMove()
    {
        return playerToMove;
    }
    
    public double getMoveAmountX()
    {
        return moveAmountX;
    }
    
    public double getMoveAmountY()
    {
        return moveAmountY;
    }
    
    private PlayerUnit playerToMove;
    private double moveAmountX;
    private double moveAmountY;
}
