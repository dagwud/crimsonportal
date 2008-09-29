/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.gui;

import crimsonportal.googlecode.com.ObjectModel.GameController;
import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author dagwud
 */
public class HUDPanel extends JPanel implements Observer
{
    public HUDPanel(GameController gameController)
    {
        super(new BorderLayout());
        lblHealth = new JLabel("Health: ");
        add(lblHealth, BorderLayout.WEST);
        
        lblEnemies = new JLabel("Enemies: ");
        add(lblEnemies, BorderLayout.EAST);
        
        gameController.addObserver(this);
    }
    
    public void update(Observable o, Object arg)
    {
        GameController gameController = (GameController)o;
        lblHealth.setText("Health: " + gameController.getGameState().getPlayers().next().getHealth());
        lblEnemies.setText("Enemies: " + gameController.getGameState().getNumEnemies());
    }
    
    private JLabel lblHealth;
    private JLabel lblEnemies;
}
