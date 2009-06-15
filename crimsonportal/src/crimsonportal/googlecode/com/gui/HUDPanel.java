/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.gui;

import crimsonportal.googlecode.com.ObjectModel.GameController;
import crimsonportal.googlecode.com.ObjectModel.GameState;
import crimsonportal.googlecode.com.ObjectModel.Location;
import crimsonportal.googlecode.com.Observer.GameState.GameStateChangedEvent;
import crimsonportal.googlecode.com.Observer.GameState.GameStateChangedObserver;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author dagwud
 */
public class HUDPanel extends JPanel implements GameStateChangedObserver
{
    public HUDPanel(GameController gameController, int width)
    {
        super(new BorderLayout());
        lblHealth = new JLabel("Health: ");
        lblHealth.setForeground(Color.white);
        add(lblHealth, BorderLayout.WEST);
        
        lblEnemies = new JLabel("Enemies: ");
        lblEnemies.setForeground(Color.white);
        add(lblEnemies, BorderLayout.EAST);
        
        setDoubleBuffered(true);
        setOpaque(false);
        this.setSize(width, 20);
        setVisible(true);
        
        this.gameController = gameController;
        this.gameController.addObserver(this);
    }
    
    public void update(GameStateChangedEvent event)
    {
        GameState gameState = (GameState)event.getSource();
        lblHealth.setText("Health: " + gameState.getPlayers().next().getHealth());
        lblEnemies.setText("Enemies: " + gameState.getNumEnemies());
        
        Location l = gameState.getPlayers().next().getCentreOfObject();
        int height = gameState.getTerrain().getHeightAt(l.getY(), l.getX(), 
                gameState.getMap());
        lblHealth.setText(lblHealth.getText() + "  [Height = " + height + " at " + l.getY() + "," + l.getX() + "]");
    }
    
    private JLabel lblHealth;
    private JLabel lblEnemies;
    private GameController gameController;
}
