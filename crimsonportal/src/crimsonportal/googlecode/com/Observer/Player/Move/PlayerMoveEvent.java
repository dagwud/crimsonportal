/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.Observer.Player.Move;

import crimsonportal.googlecode.com.ObjectModel.GameState;
import crimsonportal.googlecode.com.ObjectModel.PlayerUnit;
import java.util.EventObject;

/**
 *
 * @author dagwud
 */
public class PlayerMoveEvent extends EventObject
{
    public PlayerMoveEvent(PlayerUnit playerToMove, GameState gameState, double moveAmountX, double moveAmountY)
    {
        super(playerToMove);
        this.gameState = gameState;
        this.moveAmountX = moveAmountX;
        this.moveAmountY = moveAmountY;
    }
    
    public GameState getGameState() 
    {
        return gameState;
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
    private GameState gameState;
}
