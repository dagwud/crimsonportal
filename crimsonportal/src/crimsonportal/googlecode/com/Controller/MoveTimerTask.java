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
        int moveX = moveAmountX * controlledPlayer.getMoveSpeed();
        int moveY = moveAmountY * controlledPlayer.getMoveSpeed();
        if (moveX != 0 ^ moveY != 0)
        {
            // Increase movement speed when moving up, down, left or right
            // to counter the effect of faster movement when moving diagonally:
            moveX = moveX * 2;
            moveY = moveY * 2;
        }

        PlayerMoveEvent event = new PlayerMoveEvent(controlledPlayer, moveX, moveY);
        controller.notifyObservers(event);
    }

    protected int moveAmountX = 0;
    protected int moveAmountY = 0;
    private Controller controller;
    private PlayerUnit controlledPlayer;
}