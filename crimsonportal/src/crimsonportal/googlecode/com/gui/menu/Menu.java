/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.gui.menu;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author dagwud
 */
public abstract class Menu extends JPanel {
    protected Menu() {
        super();
        setVisible(true);
        setSize(1024, 768);
        //setSize(getGraphicsConfiguration().getBounds().width, 
        //        getGraphicsConfiguration().getBounds().height);
        setBackground(java.awt.Color.blue);
        setOpaque(false);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        Color bgColour = new Color(0, 0, 0, 200);
        g.setColor(bgColour);
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }
}
