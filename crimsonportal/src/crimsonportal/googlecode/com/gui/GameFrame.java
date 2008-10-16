/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

/**
 *
 * @author dagwud
 */
public class GameFrame extends JFrame
{
    public GameFrame(GameCanvas canvas)
    {
        JLayeredPane layer = new JLayeredPane();
        layer.setLayout(new BorderLayout(600,800));
        layer.setPreferredSize(new Dimension(600, 800));
        
        JLabel background = new JLabel(new ImageIcon(canvas.spriteProxy.get("background.jpg").toImage()));
        layer.add(background);
        
        layer.add(new HUDPanel(canvas.getGameController()));
        layer.add(canvas);
        
        getContentPane().add(layer);   
        
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
