/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.gui.menu;

import crimsonportal.googlecode.com.Proxy.SpriteProxy;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.List;
import java.util.Vector;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 *
 * @author dagwud
 */
public abstract class Menu extends JPanel {
    protected Menu() {
        super();
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
    
    protected void addMenuItem(String menuTitle) {
        // Create a menu item and add it to the menu panel:
        JPanel pnlMenu = createMenuItem(menuTitle);
        menus.add(pnlMenu);
        pnlMenuContainer.add(pnlMenu);
        
        // Resize the menu panels:
        int h = MENU_HEIGHT * menus.size();
        int screenHeight = getHeight();
        forceSize(fillerTop, getWidth(), (screenHeight - h) / 2);
        forceSize(pnlMenuContainer, getWidth(), h);
        forceSize(fillerBottom, getWidth(), (screenHeight - h) / 2);
    }
    
    private JPanel createMenuItem(String menuTitle) {
        JPanel pnlMenuItem = new JPanel();
        forceSize(pnlMenuItem, getWidth(), MENU_HEIGHT);
        pnlMenuItem.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        pnlMenuItem.setOpaque(false);
        
        String menuSpriteFilename = "menu" + menuTitle.replaceAll(" ", "") + ".gif";
        ImageIcon menuSprite = spriteProxy.generateProxyObject(menuSpriteFilename).toImageIcon();
        JLabel lblMenuItem = new JLabel(menuSprite);
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
    
    private void forceSize(Container c, int width, int height) {
        Dimension size = new Dimension(width, height);
        c.setSize(size);
        c.setMinimumSize(size);
        c.setMaximumSize(size);
        c.setPreferredSize(size);
    }
    
    private List<JPanel> menus;
    private static final int MENU_HEIGHT = 60;
    private JPanel pnlMenuContainer;
    private JPanel fillerTop, fillerBottom;
    private SpriteProxy spriteProxy = new SpriteProxy();
}
