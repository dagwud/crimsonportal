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
        
        // Create the panel to hold the health bars:
        pnlHealth = new JPanel(new FlowLayout(FlowLayout.LEFT, 1, 0));
        pnlHealth.setOpaque(false);
        
        // Create the health bars:
        lblHealthBar = new JLabel[10];
        ImageIcon imgHealth = spriteProxy.generateProxyObject("health_good.gif").toImageIcon();
        for (int h = 0; h < lblHealthBar.length; h++) {
            lblHealthBar[h] = new JLabel(imgHealth);
            pnlHealth.add(lblHealthBar[h]);
        }
        
        add(pnlHealth);
        
        setDoubleBuffered(true);
        setOpaque(false);
        this.setSize(width, 30);
        setVisible(true);
        
        this.gameController = gameController;
        this.gameController.addObserver(this);
    }
    
    public void update(GameStateChangedEvent event)
    {
        GameState gameState = (GameState)event.getSource();
        double health = gameState.getPlayers().next().getHealth();
        double healthPerc = health / gameState.getPlayers().next().getDefaultHealth();
        int healthBarsToDisplay = (int)Math.floor(healthPerc * lblHealthBar.length);
        for (int i = 0; i < lblHealthBar.length; i++) {
            lblHealthBar[i].setVisible(i <= healthBarsToDisplay);
        }
    }
    
    public void paintComponent(Graphics g) {
        Color c = new Color(0, 0, 0, 200);
        g.setColor(c);
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }
    
    private JPanel pnlHealth;
    private GameController gameController;
    private SpriteProxy spriteProxy = new SpriteProxy();
    private JLabel[] lblHealthBar;
}
