/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.gui;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author dagwud
 */
public class GameFrame extends JFrame
{
    public GameFrame(GameCanvas canvas)
    {
        JLabel background = new JLabel(new ImageIcon(canvas.spriteProxy.get("background.jpg").toImage()));
        background.setSize(new Dimension(800, 600));
        background.setOpaque(true);
        getLayeredPane().add(background, new Integer(0));
        
        getLayeredPane().add(canvas, new Integer(1));
        
        HUDPanel hud = new HUDPanel(canvas.getGameController());
        hud.setSize(800, 200);
        // Position the HUD:
        hud.setLocation(0, background.getHeight() - hud.getHeight());
        getLayeredPane().add(hud, new Integer(2));
        
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
