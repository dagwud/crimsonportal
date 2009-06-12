/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.gui;

import crimsonportal.googlecode.com.ObjectModel.Map;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author dagwud
 */
public class GameFrame extends JFrame
{
    public GameFrame(GameCanvas canvas, Map map)
    {
        JLabel background = new JLabel(new ImageIcon(canvas.spriteProxy.get("terrain.jpg").toImage()));
        background.setSize(map.getSize());
        background.setOpaque(true);
        background.setLocation(0, 0);
        getLayeredPane().add(background, new Integer(0));
        
        getLayeredPane().add(canvas, new Integer(1));
        
        HUDPanel hud = new HUDPanel(canvas.getGameController(), map.getWidth());
        // Position the HUD:
        hud.setLocation(0, background.getHeight() - hud.getHeight());
        getLayeredPane().add(hud, new Integer(2));
        
        setSize(background.getSize());
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
