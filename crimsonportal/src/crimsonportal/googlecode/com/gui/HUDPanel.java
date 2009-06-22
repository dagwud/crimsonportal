/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.gui;

import crimsonportal.googlecode.com.Debug;
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
        gameController.addObserver(this);
        
        lblHealth = new JLabel("Health: ");
        lblHealth.setForeground(Color.white);
        add(lblHealth, BorderLayout.WEST);
        
        lblEnemies = new JLabel("Enemies: ");
        lblEnemies.setForeground(Color.white);
        add(lblEnemies, BorderLayout.EAST);
        
        lblDebug = new JLabel("[DEBUG]");
        lblDebug.setForeground(Color.WHITE);
        add(lblDebug, BorderLayout.SOUTH);
        
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
        int height = gameState.getTerrain().getHeightAt(l, gameState.getMap());
        
        switch (Debug.getFlagValue(Debug.flagKey.PLAYER_MOVEMENT_VERTICAL)) {
            case ASCENDING:
                lblDebug.setForeground(Color.GREEN);
                break;
                
            case DESCENDING:
                lblDebug.setForeground(Color.RED);
                break;
                
            case LEVEL:
                lblDebug.setForeground(Color.GRAY);
                break;
        }
        lblDebug.setText("[Height = " + height + " at " + l.getY() + "," + l.getX() + "]");
    }
    
    private JLabel lblHealth;
    private JLabel lblEnemies;
    private JLabel lblDebug;
    private GameController gameController;
}
