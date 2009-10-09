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
import crimsonportal.googlecode.com.Proxy.Sprite;
import crimsonportal.googlecode.com.Proxy.SpriteProxy;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
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
        
        setLayout(new BorderLayout());
        
        // Create the panel to hold the health bars:
        healthPanel = new HealthPanel();
        add(healthPanel, BorderLayout.WEST);
        
        setDoubleBuffered(true);
        setOpaque(false);
        this.setSize(width, 30);
        setVisible(true);
        
        this.gameController = gameController;
        this.gameController.addObserver(this);
    }
    
    public void update(GameStateChangedEvent event)
    {
        if (healthPanel == null) {
            // Constructor hasn't been called yet, cancel the update:
            return;
        }
        GameState gameState = (GameState)event.getSource();
        double health = gameState.getPlayers().next().getHealth();
        double healthPerc = health / gameState.getPlayers().next().getDefaultHealth();
        healthPanel.setHealthPercentage(healthPerc);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        Color c = new Color(0, 0, 0, 200);
        g.setColor(c);
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }
    
    private HealthPanel healthPanel;
    private GameController gameController;
    private SpriteProxy spriteProxy = new SpriteProxy();
    
    private class HealthPanel extends JPanel {
        public HealthPanel() {
            super(new FlowLayout(FlowLayout.LEFT, 1, 1));
            setOpaque(false);
            
            // Create the health icon:
            ImageIcon imgLabel = spriteProxy.generateProxyObject("health.gif").toImageIcon();
            add(new JLabel(imgLabel));

            // Create the health bars:
            lblHealthBar = new JLabel[NUMBER_HEALTH_BARS];
            ImageIcon imgHealthBad = spriteProxy.generateProxyObject("health_bad.gif").toImageIcon();
            ImageIcon imgHealthMiddle = spriteProxy.generateProxyObject("health_middle.gif").toImageIcon();
            ImageIcon imgHealthGood = spriteProxy.generateProxyObject("health_good.gif").toImageIcon();
            for (int h = 0; h < lblHealthBar.length; h++) {
                if (h <= (lblHealthBar.length / 3)) {
                    lblHealthBar[h] = new JLabel(imgHealthBad);
                } else if (h <= 2 * (lblHealthBar.length / 3)) {
                    lblHealthBar[h] = new JLabel(imgHealthMiddle);
                } else {
                    lblHealthBar[h] = new JLabel(imgHealthGood);
                }
                add(lblHealthBar[h]);
            }
        }
        
        public void setHealthPercentage(double healthPerc) {
            int healthBarsToDisplay = (int)Math.floor(healthPerc * lblHealthBar.length);
            for (int i = 0; i < lblHealthBar.length; i++) {
                lblHealthBar[i].setVisible(i <= healthBarsToDisplay);
            }
        }

        private JLabel[] lblHealthBar;
        private static final int NUMBER_HEALTH_BARS = 50;
    }
}
