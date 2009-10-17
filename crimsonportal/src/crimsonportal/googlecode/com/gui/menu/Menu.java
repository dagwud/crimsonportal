/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.gui.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.List;
import java.util.Vector;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

/**
 *
 * @author dagwud
 */
public abstract class Menu extends JPanel {
    protected Menu() {
        super();
        setLayout(new GridLayout(3, 1));
        pnlMenuContainer = new JPanel();
        pnlMenuContainer.setLayout(new BoxLayout(pnlMenuContainer, BoxLayout.PAGE_AXIS));
        pnlMenuContainer.setBackground(Color.red);
        
        menus = new Vector<JPanel>();

        // Set up the invisible filler panels:
        fillerTop = new JPanel();
        fillerBottom = new JPanel();
        fillerTop.setOpaque(false);
        fillerBottom.setOpaque(false);
        
        add(fillerTop);
        add(pnlMenuContainer);
        add(fillerBottom);
        setSize(1024, 768);
        setOpaque(false);
        setVisible(true);
        //setSize(getGraphicsConfiguration().getBounds().width, 
        //        getGraphicsConfiguration().getBounds().height);
    }
    
    protected void addMenuItem(String menuTitle) {
        // Create a menu item and add it to the menu panel:
        JPanel pnlMenu = createMenuItem(menuTitle);
        menus.add(pnlMenu);
        pnlMenuContainer.add(pnlMenu);
        pnlMenuContainer.setSize(new Dimension(getWidth(), MENU_HEIGHT * menus.size()));
        
        // Resize the menu panels:
        int h = MENU_HEIGHT * menus.size();
        pnlMenuContainer.setSize(getWidth(), h);
        fillerBottom.setPreferredSize(new Dimension(getWidth(), getHeight() - (h / 2)));
        fillerTop.setPreferredSize(new Dimension(getWidth(), getHeight() - (h / 2)));
    }
    
    private JPanel createMenuItem(String menuTitle) {
        JPanel pnlMenuItem = new JPanel();
        pnlMenuItem.setSize(getWidth(), MENU_HEIGHT);
        pnlMenuItem.setMinimumSize(new Dimension(getWidth(), MENU_HEIGHT));
        pnlMenuItem.setMaximumSize(new Dimension(getWidth(), MENU_HEIGHT));
        pnlMenuItem.setPreferredSize(new Dimension(getWidth(), MENU_HEIGHT));
        pnlMenuItem.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        //pnlMenuItem.setOpaque(false);
        if (menus.size() == 1) {
            pnlMenuItem.setBackground(Color.green);
        } else pnlMenuItem.setBackground(Color.cyan);
        
        JLabel lblMenuItem = new JLabel(menuTitle);
        lblMenuItem.setForeground(Color.WHITE);
        lblMenuItem.setHorizontalAlignment(JLabel.CENTER);
        lblMenuItem.setVerticalAlignment(JLabel.CENTER);
        pnlMenuItem.add(lblMenuItem);
        
        return pnlMenuItem;
    }
    
    public void centreOn(JLayeredPane panel) {
        pnlMenuContainer.setLocation(0, (panel.getHeight() / 2) - (getHeight() / 2));
    }
    
    @Override
    public void paintComponent(Graphics g) {
        Color bgColour = new Color(0, 0, 0, 100);
        g.setColor(bgColour);
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }
    
    private List<JPanel> menus;
    private static final int MENU_HEIGHT = 30;
    private JPanel pnlMenuContainer;
    private JPanel fillerTop, fillerBottom;
}
