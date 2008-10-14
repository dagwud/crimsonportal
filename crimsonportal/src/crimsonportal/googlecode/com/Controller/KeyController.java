/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.Controller;

import crimsonportal.googlecode.com.ObjectModel.PlayerUnit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author dagwud
 */
public class KeyController implements KeyListener
{
    public KeyController(PlayerUnit controlledPlayer)
    {
        moveTimer = new MoveTimer(controlledPlayer);
    }
    
    public void keyPressed(KeyEvent e) 
    {
        int key = e.getKeyCode();
        
        switch (key)
        {
            case KeyEvent.VK_UP:
                moveTimer.setMovementY(-1);
                break;
                
            case KeyEvent.VK_DOWN:
                moveTimer.setMovementY(1);
                break;
                
            case KeyEvent.VK_LEFT:
                moveTimer.setMovementX(-1);
                break;
            
            case KeyEvent.VK_RIGHT:
                moveTimer.setMovementX(1);
                break;
        }
    }
    
    public void keyReleased(KeyEvent e) 
    {
        int key = e.getKeyCode();
        
        switch (key)
        {
            case KeyEvent.VK_UP:
                moveTimer.setMovementY(0);
                break;
                
            case KeyEvent.VK_DOWN:
                moveTimer.setMovementY(0);
                break;
                
            case KeyEvent.VK_LEFT:
                moveTimer.setMovementX(0);
                break;
            
            case KeyEvent.VK_RIGHT:
                moveTimer.setMovementX(0);
                break;
        }
    }
    
    public void keyTyped(KeyEvent e) {}
    
    private MoveTimer moveTimer;
}
