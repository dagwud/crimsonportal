/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.gui.menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.List;
import java.util.Vector;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author dagwud
 */
public abstract class Menu extends JPanel {
    protected Menu() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setVisible(true);
        setSize(1024, 768);
        //setSize(getGraphicsConfiguration().getBounds().width, 
        //        getGraphicsConfiguration().getBounds().height);
        setBackground(java.awt.Color.blue);
        setOpaque(false);
        pnlMenus = new Vector<JPanel>();
    }
    
    protected void addMenu(String menuTitle) {
        JPanel pnlMenu = new JPanel();
        pnlMenu.setSize(getWidth(), MENU_HEIGHT);
        pnlMenu.setMinimumSize(new Dimension(getWidth(), MENU_HEIGHT));
        pnlMenu.setPreferredSize(new Dimension(getWidth(), MENU_HEIGHT));
        pnlMenu.setAlignmentX(pnlMenu.CENTER_ALIGNMENT);
        //pnlMenu.setOpaque(false);
        //pnlMenu.setBackground(new java.awt.Color((pnlMenus.size()+1) * 50, 0, 0));
        JLabel lblMenu = new JLabel(menuTitle);
        lblMenu.setHorizontalAlignment(JLabel.CENTER);
        lblMenu.setVerticalAlignment(JLabel.CENTER);
        pnlMenu.add(lblMenu);
        pnlMenus.add(pnlMenu);
        add(pnlMenu);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        Color bgColour = new Color(0, 0, 0, 200);
        g.setColor(bgColour);
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }
    
    private List<JPanel> pnlMenus;
    private static final int MENU_HEIGHT = 30;
}
