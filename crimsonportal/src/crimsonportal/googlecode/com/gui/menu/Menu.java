/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.gui.menu;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;
import java.util.Vector;
import javax.swing.BoxLayout;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 *
 * @author dagwud
 */
public abstract class Menu extends JPanel {
    protected Menu(MenuManager managingManager) {
        super();
        this.managingManager = managingManager;
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        pnlMenuContainer = new JPanel();
        pnlMenuContainer.setLayout(new BoxLayout(pnlMenuContainer, BoxLayout.PAGE_AXIS));
        pnlMenuContainer.setOpaque(false);
        
        menus = new Vector<JPanel>();

        // Set up the invisible filler panels:
        fillerTop = new JPanel();
        fillerBottom = new JPanel();
        fillerTop.setOpaque(false);
        fillerBottom.setOpaque(false);
        
        add(fillerTop);
        add(pnlMenuContainer);
        add(fillerBottom);
        //setSize(getGraphicsConfiguration().getBounds().width, 
        //        getGraphicsConfiguration().getBounds().height);
        forceSize(this, 1024, 768);
        setOpaque(false);
        setVisible(true);
        
        if (!true) {
            setBackground(Color.blue);
            setOpaque(true);
            pnlMenuContainer.setBackground(Color.red);
            pnlMenuContainer.setOpaque(true);
            fillerTop.setBackground(Color.green);
            fillerBottom.setBackground(Color.green);
            //fillerTop.setOpaque(true);
            //fillerBottom.setOpaque(true);
        }
    }
    
    protected void addMenuItem(MenuItemFactory.MenuItemType menuItemType) {
        MenuItem menuItem = MenuItemFactory.createMenuItem(menuItemType, this);
        menus.add(menuItem);
        pnlMenuContainer.add(menuItem);
        
        // Resize the menu panels:
        int h = MenuItem.MENUITEM_HEIGHT * menus.size();
        int screenHeight = getHeight();
        forceSize(fillerTop, getWidth(), (screenHeight - h) / 2);
        forceSize(pnlMenuContainer, getWidth(), h);
        forceSize(menuItem, getWidth(), h);
        forceSize(fillerBottom, getWidth(), (screenHeight - h) / 2);
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
    
    public void closeMenu() {
        managingManager.closeCurrentMenu();
    }
    
    public static void forceSize(Container c, int width, int height) {
        Dimension size = new Dimension(width, height);
        c.setSize(size);
        c.setMinimumSize(size);
        c.setMaximumSize(size);
        c.setPreferredSize(size);
    }
    
    private List<JPanel> menus;
    private JPanel pnlMenuContainer;
    private JPanel fillerTop, fillerBottom;
    private MenuManager managingManager;
}
