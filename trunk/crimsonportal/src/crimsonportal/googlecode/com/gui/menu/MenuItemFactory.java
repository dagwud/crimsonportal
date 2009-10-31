/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.gui.menu;

import crimsonportal.googlecode.com.ObjectModel.GameState;

/**
 *
 * @author dagwud
 */
public class MenuItemFactory {
    public static MenuItem createMenuItem(MenuItemType type, Menu containerMenu, GameState gameState) {
        switch (type) {
            case MainStart:
                return new MenuStartGame(containerMenu, gameState);
            case MainExit:
                return new MenuExit(containerMenu, gameState);
                
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
        public MenuStartGame(Menu containerMenu, GameState gameState) {
            super("Start Game", containerMenu, gameState);
        }
        
        public void processClick() {
            getContainerMenu().closeMenu();
        }
    }
    
    static class MenuExit extends MenuItem {
        public MenuExit(Menu containerMenu, GameState gameState) {
            super("Exit", containerMenu, gameState);
        }

        public void processClick() {
            getGameState().quit();
        }
    }
}
