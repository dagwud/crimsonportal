/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.Observer.MoveTimer;

import crimsonportal.googlecode.com.Debug;
import crimsonportal.googlecode.com.Observer.Player.PlayerMoveEvent;
import crimsonportal.googlecode.com.ObjectModel.PlayerUnit;
import java.util.TimerTask;

/**
 *
 * @author dagwud
 */
class MoveTimerTask extends TimerTask
{
    public MoveTimerTask(PlayerUnit controlledPlayer)
    {
        super();
        this.controlledPlayer = controlledPlayer;
    }

    public void run()
    {
        double moveX = moveAmountX * controlledPlayer.getMoveSpeed();
        double moveY = moveAmountY * controlledPlayer.getMoveSpeed();
        if (moveX != 0 ^ moveY != 0)
        {
            // Increase movement speed when moving up, down, left or right
            // to counter the effect of faster movement when moving diagonally:
            moveX = moveX * 1.5;
            moveY = moveY * 1.5;
        }
        else
        {
            moveX = moveX * 1.4;
            moveY = moveY * 1.4;
        }

        PlayerMoveEvent event = new PlayerMoveEvent(controlledPlayer, moveX, moveY);
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
}