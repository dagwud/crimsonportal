/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

import crimsonportal.googlecode.com.ObjectModel.EnemyUnits.EnemyUnitFlying;
import crimsonportal.googlecode.com.Utils;

/**
 *
 * @author jdevenish
 */
public class MovementHandlerFly extends MovementHandlerStraightLine {
    public MovementHandlerFly(int moveSpeed, double turnSpeed) {
        super(moveSpeed);
    }
    
    @Override
    public void move(Unit moveObject) {
        if (moveObject.gameState.getGameTime().isPaused()) {
            return;
        }
        EnemyUnitFlying f = (EnemyUnitFlying) moveObject;
        Location targetLoc = f.getStrategy().getTarget().getCentreOfObject();
        Location thisLoc = f.getCentreOfObject();
        double diffY = targetLoc.getY() - thisLoc.getY();
        double diffX = targetLoc.getX() - thisLoc.getX();
        double distBeforeMove = Math.sqrt((diffY * diffY) + (diffX * diffX));
        
        if ( distBeforeMove <= f.getStrategy().getTarget().getRadius() + 
                f.getRadius() + getMoveSpeed()) {
            // Turn to the target and stop moving:
            f.rotation = f.getRotation() - f.getShiftAngleRadians();
            f.setShiftAngleRadians(0);
            return;
        }
        
        if (f.isTurningClockwise()) {
            f.setShiftAngleRadians(f.getShiftAngleRadians() + f.getTurnSpeed());
            if (f.getShiftAngleRadians() > f.getMaxTurnRadians()) {
                f.setTurningClockwise(false);
            }
        } else {
            f.setShiftAngleRadians(f.getShiftAngleRadians() - f.getTurnSpeed());
            if (f.getShiftAngleRadians() < -f.getMaxTurnRadians()) {
                f.setTurningClockwise(true);
            }
        }
        
        double moveAmount = moveObject.getMovementHandler().getMoveSpeed();
        double moveX = (double) Math.round(moveAmount * Utils.cos(f.getRotation()));
        double moveY = (double) Math.round(moveAmount * Utils.sin(f.getRotation()));
        
        // Calculate how far from the target the enemyUnit will be after the move: 
        double newDiffY = targetLoc.getY() - (thisLoc.getY() + moveY);
        double newDiffX = targetLoc.getX() - (thisLoc.getX() + moveX);
        
        double distAfterMove = Math.sqrt((newDiffY * newDiffY) + (newDiffX * newDiffX));
        // Ensure that the flyer doesn't accidentally move further away:
        if ( distAfterMove >= distBeforeMove )
        {
            moveX = 0.0;
            moveY = 0.0;
        }
        // Ensure that the flyer doesn't overlap the target:
        if ( distAfterMove < f.getStrategy().getTarget().getRadius() + 
                f.getRadius()) {
            f.setRotation(f.getRotation() - f.getShiftAngleRadians());
            moveX = 0.0;
            moveY = 0.0;
        }
        
        Location newLocation = new Location(
            moveObject.getCentreOfObject().getX() + moveX,
            moveObject.getCentreOfObject().getY() + moveY);
        moveObject.getMovementHandler().moveTo(moveObject, newLocation);
    }
}
