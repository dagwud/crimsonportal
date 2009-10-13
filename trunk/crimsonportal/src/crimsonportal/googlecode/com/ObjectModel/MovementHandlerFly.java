/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

import crimsonportal.googlecode.com.ObjectModel.EnemyUnits.EnemyUnitFly;
import crimsonportal.googlecode.com.Utils;

/**
 *
 * @author jdevenish
 */
public class MovementHandlerFly extends MovementHandlerStraightLine {
    public MovementHandlerFly(int moveSpeed, double turnSpeed) {
        super(moveSpeed);
        this.turnSpeed = turnSpeed;
    }
    
    @Override
    public void move(Unit moveObject) {
        EnemyUnitFly f = (EnemyUnitFly) moveObject;
        
        double baseAngleRadians = moveObject.getRotation();
        double moveAngle = baseAngleRadians;
        if (f.isTurningClockwise()) {
            moveAngle += f.getShiftAngleRadians();
            f.setShiftAngleRadians(f.getShiftAngleRadians() + turnSpeed);
            if (f.getShiftAngleRadians() > baseAngleRadians + maxTurnRadians) {
                f.setTurningClockwise(false);
                System.out.println("Turn - anticlock");
            }
        } else {
            moveAngle += f.getShiftAngleRadians();
            f.setShiftAngleRadians(f.getShiftAngleRadians() - turnSpeed);
            if (f.getShiftAngleRadians() < -(baseAngleRadians + maxTurnRadians)) {
                f.setTurningClockwise(true);
                System.out.println("Turn - clock");
            }
        }
        moveObject.setRotation(moveAngle);
        System.out.println("moveAngle = " + Utils.normaliseDegrees(Math.toDegrees(moveAngle)));
        super.move(moveObject); //TODO fix this - ignores direction faced by unit
        moveObject.rotation = baseAngleRadians;
    }
    
    double turnSpeed;
    double maxTurnRadians = Math.toRadians(45);
}
