/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

/**
 *
 * @author jdevenish
 */
public class MovementHandlerScuttle extends MovementHandlerStraightLine {
    public MovementHandlerScuttle(double moveSpeed) {
        super(moveSpeed);
        throw new UnsupportedOperationException("MovementHandlerScuttle must be constructed using explicit constructor");
    }
    
    public MovementHandlerScuttle(double moveSpeed, double rotationSpeed) {
        super(moveSpeed);
        this.rotateSpeed = rotationSpeed;
    }
    
    @Override
    public void move(Unit moveObject) {
       // If the target is not in attacking range, rotate until the scuttler
       // is facing side-on to the target. If the scuttler is side-on, then move.
        GameObject target = moveObject.getStrategy().getTarget();
        
        // Determine the options for rotating; either rotate clockwise (+90 deg) 
        // or counter-clockwise (-90 deg)
        double rotationToTarget = moveObject.getRotation();
        double rotationCW = rotationToTarget + (Math.PI / 2.0);
        double rotationCCW = rotationToTarget - (Math.PI / 2.0);
        boolean closeEnoughToAttack = moveObject.testOverlapsWith(target);
        double desiredRotation = 0;
        if (closeEnoughToAttack) {
            // Rotate towards the player:
            desiredRotation = rotationToTarget;
        } else {
            // Decide whether to rotate clockwise or counterclockwise (whichever
            // is closer to the current rotation)
            if (Math.abs(rotationToTarget - rotationCW) < Math.abs(rotationToTarget - rotationCCW)) { 
                desiredRotation = rotationCW;
            } else {
                desiredRotation = rotationCCW;
            }
        }
        
        // If already facing side-on to target; move towards the target:
        if (Math.abs(rotationToTarget - desiredRotation) < moveObject.getMovementHandler().getMoveSpeed()) {
            moveObject.setRotation(desiredRotation);
            // If the scuttler is not already close enough to attack, move:
            if (! closeEnoughToAttack ) {
                super.move(moveObject);
            }
        }
        
        // Turn towards the desired rotation:
        double newRotation;
        if (moveObject.rotation > desiredRotation) {
            newRotation = moveObject.rotation - rotateSpeed;
        } else {
            newRotation = moveObject.rotation + rotateSpeed;
        }
        // If turning will bring the scuttler closer to it's desired rotation, 
        // rotate: (this prevents 'jittering')
        if (Math.abs(newRotation - desiredRotation) < Math.abs(moveObject.rotation - desiredRotation)) {
            moveObject.rotation = newRotation;
        }
    }
    
    protected double rotateSpeed;
}
