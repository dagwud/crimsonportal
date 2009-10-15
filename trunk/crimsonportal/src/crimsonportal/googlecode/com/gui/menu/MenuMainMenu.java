/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.gui.menu;

/**
 *
 * @author dagwud
 */
public class MenuMainMenu extends Menu {
    public MenuMainMenu() {
        super();
    }
    
    public static Menu createMenu() {
        if (instance == null) {
            instance = new MenuMainMenu();
        }
        return instance;
    }
    
    private static MenuMainMenu instance;
}
