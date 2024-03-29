/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.gui;

import crimsonportal.googlecode.com.ObjectModel.Map;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JFrame;

/**
 *
 * @author dagwud
 */
public class GameFrame extends JFrame
{
    public GameFrame(GameCanvas canvas, Map map)
    {
        this.canvas = canvas;
        this.map = map;
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        setUndecorated(true);
        getLayeredPane().add(canvas, new Integer(1));
        
        // Set up the size of the GUI:
        canvas.setPreferredSize(getGraphicsConfiguration().getBounds().getSize());
        canvas.setSize(canvas.getPreferredSize());
        
        // Create and position the HUD:
        HUDPanel hud = new HUDPanel(canvas.getGameController(), map.getWidth());
        hud.setLocation(0, canvas.getHeight() - hud.getHeight());
        getLayeredPane().add(hud, new Integer(2));
        
        // Create and position the Info panel:
        InfoPanel pnlInfo = new InfoPanel(canvas.getGameController(), map.getWidth());
        pnlInfo.setLocation(0, 0);
        getLayeredPane().add(pnlInfo, new Integer(2));
        
        getLayeredPane().setPreferredSize(canvas.getPreferredSize());
        setVisible(true);
        pack();
    }
    
    public void initBGImage()
    {
        bgImage = canvas.spriteProxy.get(map.getImageFilename()).toImage();
        //Graphics2D g2 = (Graphics2D)canvas.getGraphics();
        //g2.drawImage(bgImage, 0, 0, null);
        //setSize(bgImage.getWidth(null), bgImage.getHeight(null));
    }
    
    protected GameCanvas canvas;
    protected Map map;
    public Image bgImage;
}
