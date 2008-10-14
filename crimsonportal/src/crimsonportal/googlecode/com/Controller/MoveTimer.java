/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.Controller;

import crimsonportal.googlecode.com.ObjectModel.PlayerUnit;
import java.util.Timer;

/**
 *
 * @author dagwud
 */
class MoveTimer extends Timer
    {
        public MoveTimer(PlayerUnit playerToMove)
        {
            super();
            timerTask = new MoveTimerTask(playerToMove);
            this.scheduleAtFixedRate(timerTask, 100, 100);
        }
        
        public void setMovementX(int moveAmountX)
        {
            timerTask.moveAmountX = moveAmountX;
        }
        
        public void setMovementY(int moveAmountY)
        {
            timerTask.moveAmountY = moveAmountY;
        }
        
        private MoveTimerTask timerTask;
    }
