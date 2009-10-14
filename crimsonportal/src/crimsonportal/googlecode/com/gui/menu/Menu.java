/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.gui.menu;

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
    }
}
