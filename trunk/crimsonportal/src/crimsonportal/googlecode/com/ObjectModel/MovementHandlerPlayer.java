/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.ObjectModel;

import crimsonportal.googlecode.com.Debug;

/**
 *
 * @author jdevenish
 */
public class MovementHandlerPlayer extends MovementHandler {
    public MovementHandlerPlayer(PlayerUnit player) {
        setMoveSpeed(player.moveSpeed);
    }
    
    @Override
    public void move(Unit moveObject) {
        // Do nothing; player movements are done by calling the Player.moveTo() 
        // method directly
    }
    
    @Override
    public void moveTo(Unit moveObject, Location location)
    {
        if (location.equals(moveObject.location)) return;
        int heightFrom = moveObject.gameState.getTerrain().getHeightAt(moveObject.location, moveObject.gameState.getMap());
        int heightTo = moveObject.gameState.getTerrain().getHeightAt(location, moveObject.gameState.getMap());
        
        if (heightFrom < heightTo) {
            Debug.setFlag(Debug.flagKey.PLAYER_MOVEMENT_VERTICAL, Debug.flagValue.ASCENDING);
        }
        else if (heightFrom > heightTo) {
            Debug.setFlag(Debug.flagKey.PLAYER_MOVEMENT_VERTICAL, Debug.flagValue.DESCENDING);
        }
        else {
            Debug.setFlag(Debug.flagKey.PLAYER_MOVEMENT_VERTICAL, Debug.flagValue.LEVEL);
        }
        
        super.moveTo(moveObject, location);
    }
}
