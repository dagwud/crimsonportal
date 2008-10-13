/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import ObjectModel.crimsonportal.googlecode.com.PlayerUnit;
import java.util.TimerTask;

/**
 *
 * @author dagwud
 */
class MoveTimerTask extends TimerTask
{
    public MoveTimerTask(PlayerUnit playerToMove)
    {
        super();
        this.controlledPlayer = playerToMove;
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

        controlledPlayer.getLocation().setX(controlledPlayer.getLocation().getX() + moveX);
        controlledPlayer.getLocation().setY(controlledPlayer.getLocation().getY() + moveY);
    }

    protected int moveAmountX = 0;
    protected int moveAmountY = 0;
    private PlayerUnit controlledPlayer;
}