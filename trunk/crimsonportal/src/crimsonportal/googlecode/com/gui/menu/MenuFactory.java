/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crimsonportal.googlecode.com.gui.menu;

/**
 *
 * @author dagwud
 */
public class MenuFactory {
    public Menu createMenu(MenuType menuType, MenuManager managingManager) {
        switch (menuType) {
            case Main:
                return new MenuMainMenu(managingManager);
                
            default:
                throw new IllegalArgumentException("MenuType " + menuType.name() + " unknown");
        }
    }
    
    public enum MenuType {
        Main
    }
    
    class MenuMainMenu extends Menu {
        public MenuMainMenu(MenuManager managingManager) {
            super(managingManager);
            addMenuItem(MenuItemFactory.MenuItemType.MainStart);
            addMenuItem(MenuItemFactory.MenuItemType.MainExit);
        }
    }
}
