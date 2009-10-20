/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.gui.menu;

/**
 *
 * @author dagwud
 */
public class MenuItemFactory {
    public static MenuItem createMenuItem(MenuItemType type, Menu containerMenu) {
        switch (type) {
            case MainStart:
                return new MenuStartGame(containerMenu);
            case MainExit:
                return new MenuExit(containerMenu);
                
            default:
                throw new RuntimeException("Unknown menu type " + type.name());
        }
    }
    
    public enum MenuItemType {
        MainStart,
        MainExit
    }
    
    
    /** Menu Item Types **/
    static class MenuStartGame extends MenuItem {
        public MenuStartGame(Menu containerMenu) {
            super("Start Game", containerMenu);
        }
        
        public void processClick() {
            getContainerMenu().closeMenu();
        }
    }
    
    static class MenuExit extends MenuItem {
        public MenuExit(Menu containerMenu) {
            super("Exit", containerMenu);
        }

        public void processClick() {
            System.exit(0);
        }
    }
}
