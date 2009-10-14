/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

import crimsonportal.googlecode.com.Debug;
import crimsonportal.googlecode.com.Utils;

/**
 *
 * @author jdevenish
 */
public class MovementHandlerStraightLine extends MovementHandler {
    public MovementHandlerStraightLine(double moveSpeed) {
        this.moveSpeed = moveSpeed;
    }
    
    public void move(Unit moveObject) {
        if (moveObject.gameState.getGameTime().isPaused()) {
            return;
        }
        move(moveObject, false);
    }
    
    protected static void move(Unit moveObject, boolean isRetreating)
    {
        if (Debug.checkFlag(Debug.flagKey.DISABLE_ENEMY_MOVEMENT)) return;
        GameObject target = moveObject.getStrategy().getTarget();
        Location targetLoc = target.getCentreOfObject();
        Location thisLoc = moveObject.getCentreOfObject();
        double diffY = targetLoc.getY() - thisLoc.getY();
        double diffX = targetLoc.getX() - thisLoc.getX();
        double moveAmount = moveObject.getMovementHandler().getMoveSpeed();
        // If not retreating, check that moving forward would actually mean getting
        // closer to the target:
        double distBeforeMove = Math.sqrt((diffY * diffY) + (diffX * diffX));
        if (!isRetreating) {
            if (moveObject.getRadius() + target.getRadius() >= distBeforeMove )
            {
                return;
            }
        }
        
        double moveAngleRadians = 0;
        if (diffX != 0)
        {
            moveAngleRadians = Math.atan2(diffY, diffX);
        }
        else
        {
            if (diffY > 0) 
            {
                moveAngleRadians = 0;
            }
            else if (diffY < 0)
            {
                moveAngleRadians = Math.PI; // Using PI is quicker than Math.toRadians(180);
            }
            else
            {
                // diffX = 0 and diffY = 0; Enemy is on top of player; don't move:
                return; 
            }
        }
        if (isRetreating) {
            // Retreat: Adjust moveAngleRadians by Math.PI (180deg) which will 
            // take the EnemyUnit away from its target:
            moveAngleRadians = moveAngleRadians + Math.PI; 
        }
        double moveX = (double) Math.round(moveAmount * Utils.cos(moveAngleRadians));
        double moveY = (double) Math.round(moveAmount * Utils.sin(moveAngleRadians));
        
        // Calculate how far from the target the enemyUnit will be after the move: 
        double newDiffY = targetLoc.getY() - (moveObject.getCentreOfObject().getY() + moveY);
        double newDiffX = targetLoc.getX() - (moveObject.getCentreOfObject().getX() + moveX);
        if (!isRetreating) {
            double distAfterMove = Math.sqrt((newDiffY * newDiffY) + (newDiffX * newDiffX));
            // If the EnemyUnit is trying to get to its target, ensure that it 
            // doesn't accidentally move further away:
            if ( distAfterMove >= distBeforeMove )
            {
                moveX = 0.0;
                moveY = 0.0;
            }
        }
        
        Location newLocation = new Location(
            moveObject.getCentreOfObject().getX() + moveX,
            moveObject.getCentreOfObject().getY() + moveY);
        moveObject.getMovementHandler().moveTo(moveObject, newLocation);
    }
}
