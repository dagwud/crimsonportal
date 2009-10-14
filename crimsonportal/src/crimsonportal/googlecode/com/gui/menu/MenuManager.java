/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.gui.menu;

import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

/**
 *
 * @author dagwud
 */
public class MenuManager {
    public MenuManager() {
        menuStack = new Vector<Menu>();
    }
    
    public void hideCurrentMenu() {
        panel.remove(menuStack.elementAt(menuStack.size() - 1));
        menuStack.removeElementAt(menuStack.size() - 1);
    }
    
    public void closeAllMenus() {
        while (menusOpen()) {
            hideCurrentMenu();
        }
    }
    
    public void openMenu(Menu menu) {
        panel.add(menu, 2);
        menuStack.add(menu);
    }
    
    public boolean menusOpen() {
        return (!menuStack.isEmpty());
    }
    
    public void setCanvas(JLayeredPane panel) {
        this.panel = panel;
    }
    
    public Vector<Menu> menuStack;
    protected JLayeredPane panel;
}
