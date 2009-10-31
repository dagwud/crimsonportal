/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.gui;

import crimsonportal.googlecode.com.ObjectModel.GameController;
import crimsonportal.googlecode.com.ObjectModel.GameState;
import crimsonportal.googlecode.com.ObjectModel.GameTimerTask;
import crimsonportal.googlecode.com.ObjectModel.PlayerUnit;
import crimsonportal.googlecode.com.Observer.GameState.GameStateChangedEvent;
import crimsonportal.googlecode.com.Observer.GameState.GameStateChangedObserver;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author dagwud
 */
public class InfoPanel extends JPanel implements GameStateChangedObserver
{
    public InfoPanel(GameController gameController, int width)
    {
        super();
        gameController.addObserver(this);
        
        setLayout(new FlowLayout(FlowLayout.LEFT, 5, 1));
        
        // Create the panel to hold the health bars:
        healthPanel = new StatPanel("health");
        add(healthPanel);
        
        // Create the panel to hold the armour bars:
        armourPanel = new StatPanel("armour", true);
        add(armourPanel);
        
        // Create the panel to hold the experience bars:
        experiencePanel = new StatPanel("experience", false, "1");
        add(experiencePanel);
        
        setDoubleBuffered(true);
        setOpaque(false);
        this.setSize(width, StatPanel.getIconSize() * 2);
        setVisible(true);
        
        this.gameController = gameController;
        this.gameController.addObserver(this);
    }
    
    public void update(GameStateChangedEvent event)
    {
        if (healthPanel == null || armourPanel == null) {
            // Constructor hasn't been called yet, cancel the update:
            return;
        }
        GameState gameState = (GameState)event.getSource();
        
        PlayerUnit player = gameState.getPlayers().next();
        double healthPerc = player.getHealth() / player.getDefaultHealth();
        healthPanel.setPercentage(healthPerc);
        
        double armourPerc = player.getArmourPercentage();
        armourPanel.setPercentage(armourPerc);
        
        double xpPerc = player.getExperience() / player.getExperienceRequirementForNextLevel();
        experiencePanel.setPercentage(xpPerc);
        
        if (player.getLevel() > playerLastLevel) {
            playerLastLevel = player.getLevel();
            // Show gui effect to indicate level up
            experiencePanel.setOverlayText("" + player.getLevel());
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        Color c = new Color(0, 0, 0, 200);
        g.setColor(c);
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }
    
    private GameTimerTask levelUpTimer;
    private StatPanel healthPanel;
    private StatPanel armourPanel;
    private StatPanel experiencePanel;
    private GameController gameController;
    private int playerLastLevel = 1;
}
