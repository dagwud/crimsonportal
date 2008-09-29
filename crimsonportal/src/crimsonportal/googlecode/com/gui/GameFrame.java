/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.gui;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

/**
 *
 * @author dagwud
 */
public class GameFrame extends JFrame implements KeyListener
{
    public GameFrame(GameCanvas canvas)
    {
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(new HUDPanel(canvas.getGameController()), BorderLayout.SOUTH);
        getContentPane().add(canvas, BorderLayout.CENTER);
        
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        addKeyListener(canvas.getGameController());
    }
    
    public void keyReleased(KeyEvent e) {}
    
    public void keyPressed(KeyEvent e) {}
    
    public void keyTyped(KeyEvent e)
    {
        switch (e.getKeyChar())
        {
            case 'w':
                
                break;
        }
    }
}
