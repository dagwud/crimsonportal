/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.gui.menu;

import crimsonportal.googlecode.com.Proxy.SpriteProxy;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author dagwud
 */
public abstract class MenuItem extends JPanel implements MouseListener
{
    public MenuItem(String menuText, Menu containerMenu) {
        super();
        this.menuText = menuText;
        this.containerMenu = containerMenu;
        
        setAlignmentX(JPanel.CENTER_ALIGNMENT);
        setOpaque(false);
        
        ImageIcon menuSprite = getIconInactive();
        lblMenuItem = new JLabel(menuSprite);
        lblMenuItem.setText(menuText);
        lblMenuItem.setForeground(getLabelColourInactive());
        lblMenuItem.setHorizontalTextPosition(JLabel.CENTER);
        lblMenuItem.setVerticalAlignment(JLabel.CENTER);
        add(lblMenuItem);
        // Create a menu item and add it to the menu panel:
        lblMenuItem.addMouseListener(this);
        setVisible(true);
    }
    
    public String getText() {
        return menuText;
    }
    
    public Color getLabelColourInactive() {
        return Color.WHITE;
    }
    
    public Color getLabelColourActive() {
        return Color.LIGHT_GRAY;
    }
    
    public ImageIcon getIconInactive() {
        String menuSpriteFilename = "button_background.gif";
        return spriteProxy.generateProxyObject(menuSpriteFilename).toImageIcon();
    }
    
    public ImageIcon getIconActive() {
        String menuSpriteFilename = "button_background_a.gif";
        return spriteProxy.generateProxyObject(menuSpriteFilename).toImageIcon();
    }
    
    public Menu getContainerMenu() {
        return containerMenu;
    }
    
    public abstract void processClick();
    
    //public abstract String getBaseMenuSpriteName();
    
    public void mouseClicked(MouseEvent e)
    {
        processClick();
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {
        lblMenuItem.setIcon(getIconActive());
        lblMenuItem.setForeground(getLabelColourActive());
    }
    public void mouseExited(MouseEvent e) {
        lblMenuItem.setIcon(getIconInactive());
        lblMenuItem.setForeground(getLabelColourInactive());
    }

    private String menuText;
    public static final int MENUITEM_HEIGHT = 60;
    private SpriteProxy spriteProxy = new SpriteProxy();
    private JLabel lblMenuItem;
    private Menu containerMenu;
}
