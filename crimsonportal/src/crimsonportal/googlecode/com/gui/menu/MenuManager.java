/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.gui.menu;

import crimsonportal.googlecode.com.Observer.MenuExit.MenuListenerEvent;
import crimsonportal.googlecode.com.Observer.Observable;
import crimsonportal.googlecode.com.Observer.Observer;
import crimsonportal.googlecode.com.Observer.ObserverGroup;
import java.util.Vector;
import javax.swing.JLayeredPane;

/**
 *
 * @author dagwud
 */
public class MenuManager extends Thread
                         implements Observable<MenuListenerEvent> {
    public MenuManager() {
        menuStack = new Vector<Menu>();
        observers = new ObserverGroup<MenuListenerEvent>();
    }
    
    public void closeCurrentMenu() {
        panel.remove(menuStack.elementAt(menuStack.size() - 1));
        menuStack.removeElementAt(menuStack.size() - 1);
        if (menuStack.isEmpty()) {
            notifyObservers(new MenuListenerEvent(MenuListenerEvent.Type.MENU_CLOSED));
        }
    }
    
    public void closeAllMenus() {
        while (menusOpen()) {
            closeCurrentMenu();
        }
    }
    
    public void openMenu(MenuFactory.MenuType menuType) {
        Menu menu = new MenuFactory().createMenu(menuType, this);
        panel.add(menu, new Integer(2));
        menu.centreOn(panel);
        menuStack.add(menu);
        notifyObservers(new MenuListenerEvent(MenuListenerEvent.Type.MENU_OPEN));
    }
    
    public boolean menusOpen() {
        return (!menuStack.isEmpty());
    }
    
    public void setCanvas(JLayeredPane panel) {
        this.panel = panel;
    }
    
    public Vector<Menu> menuStack;
    protected JLayeredPane panel;

    private ObserverGroup<MenuListenerEvent> observers;
    public void notifyObservers(MenuListenerEvent event)
    {
        observers.notifyObservers(event);
    }

    public boolean addObserver(Observer<MenuListenerEvent> observer)
    {
        return observers.addObserver(observer);
    }

    public boolean removeObserver(Observer<MenuListenerEvent> observer)
    {
        return observers.removeObserver(observer);
    }

    public void removeAllObservers()
    {
        observers.removeAllObservers();
    }

    public int countObservers()
    {
        return observers.countObservers();
    }
}
