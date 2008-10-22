/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.Controller;

import crimsonportal.googlecode.com.Observer.PlayerMoveEvent;
import crimsonportal.googlecode.com.ObjectModel.PlayerUnit;
import java.util.TimerTask;

/**
 *
 * @author dagwud
 */
class MoveTimerTask extends TimerTask
{
    public MoveTimerTask(Controller controller, PlayerUnit controlledPlayer)
    {
        super();
        this.controller = controller;
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
        controller.notifyObservers(event);
    }

    protected double moveAmountX = 0;
    protected double moveAmountY = 0;
    private Controller controller;
    private PlayerUnit controlledPlayer;
}