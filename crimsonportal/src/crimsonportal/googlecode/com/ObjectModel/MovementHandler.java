/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

/**
 *
 * @author jdevenish
 */
public abstract class MovementHandler {
    public abstract void move(Unit moveObject);
    
    public double getMoveSpeed() {
        return moveSpeed;
    }
    
    public void setMoveSpeed(double moveSpeed) {
        this.moveSpeed = moveSpeed;
    }
    
    public void moveTo(Unit moveObject, Location newLocation) 
    {
        Location moveTo = moveObject.gameState.getTerrain().getMoveWithGradient(
                moveObject.getCentreOfObject(), 
                newLocation, moveObject.gameState.getMap());
        
        moveObject.setCentreOfObject(moveTo);
    }
    
    protected double moveSpeed;
}
