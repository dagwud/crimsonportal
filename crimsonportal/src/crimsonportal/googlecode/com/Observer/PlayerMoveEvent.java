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
    public PlayerMoveEvent(PlayerUnit playerToMove, int moveAmountX, int moveAmountY)
    {
        this.playerToMove = playerToMove;
        this.moveAmountX = moveAmountX;
        this.moveAmountY = moveAmountY;
    }
    
    public PlayerUnit getPlayerToMove()
    {
        return playerToMove;
    }
    
    public int getMoveAmountX()
    {
        return moveAmountX;
    }
    
    public int getMoveAmountY()
    {
        return moveAmountY;
    }
    
    private PlayerUnit playerToMove;
    private int moveAmountX;
    private int moveAmountY;
}
