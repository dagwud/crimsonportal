/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.Observer.Player.Move;

import crimsonportal.googlecode.com.Debug;
import crimsonportal.googlecode.com.ObjectModel.GameState;
import crimsonportal.googlecode.com.ObjectModel.Location;
import crimsonportal.googlecode.com.ObjectModel.Map;
import crimsonportal.googlecode.com.Observer.Player.Move.PlayerMoveEvent;
import crimsonportal.googlecode.com.ObjectModel.PlayerUnit;
import java.util.TimerTask;

/**
 *
 * @author dagwud
 */
class MoveTimerTask extends TimerTask
{
    public MoveTimerTask(PlayerUnit controlledPlayer, GameState gameState)
    {
        super();
        this.controlledPlayer = controlledPlayer;
        this.gameState = gameState;
    }

    public void run()
    {
        if (moveAmountX == 0 && moveAmountY == 0) {
            return;
        }
        
        double moveX = moveAmountX * controlledPlayer.getMovementHandler().getMoveSpeed();
        double moveY = moveAmountY * controlledPlayer.getMovementHandler().getMoveSpeed();
        if (moveX != 0 ^ moveY != 0)
        {
            // Increase movement speed when moving up, down, left or right
            // to counter the effect of faster movement when moving diagonally:
            moveX = moveX * 1.5;
            moveY = moveY * 1.5;
        }
        else
        {
            moveX = moveX * 1.25;
            moveY = moveY * 1.25;
        }

        // Adjust the movement amount to cater for the terrain gradient:
        double moveFromX = controlledPlayer.getCentreOfObject().getX();
        double moveFromY = controlledPlayer.getCentreOfObject().getY();
        Map map = gameState.getMap();
        
        PlayerMoveEvent event = new PlayerMoveEvent(controlledPlayer, gameState, moveX, moveY);
        if (controlledPlayer.countObservers() > 0)
        {
            Debug.logMethod("MoveTimerTask is notifying observers of player " + controlledPlayer);
            controlledPlayer.notifyObservers(event);
        }
        else
        {
            Debug.logWarning("Player controlled by MoveTimerTask has no associated observers");
        }
    }

    protected double moveAmountX = 0;
    protected double moveAmountY = 0;
    private PlayerUnit controlledPlayer;
    private GameState gameState;
}