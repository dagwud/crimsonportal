/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crimsonportal.googlecode.com.gui;

import crimsonportal.googlecode.com.gui.*;
import crimsonportal.googlecode.com.Proxy.SpriteProxy;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 *
 * @author dagwud
 */
public class StatPanel extends JPanel
{
    public StatPanel(String statname) {
        this(statname, false);
    }
    
    public StatPanel(String statname, boolean barsReversed) {
        this(statname, barsReversed, null);
    }
    
    public StatPanel(String statname, boolean barsReversed, String overlayText) {
        super(new FlowLayout(FlowLayout.LEFT, 1, 1));
        initPanel(statname, barsReversed, overlayText);
    }
    
    public void initPanel(String statname, boolean barsReversed, String overlayText)
    {
        setOpaque(false);

        // Create the icon:
        pnlIcon = new JPanel();
        Image imgLabel = spriteProxy.generateProxyObject(statname + ".gif").toImage();
        JComponent imgIcon = new SizedImage(imgLabel);
        imgIcon.setMinimumSize(new Dimension(ICON_SIZE, ICON_SIZE));
        imgIcon.setMaximumSize(new Dimension(ICON_SIZE, ICON_SIZE));
        imgIcon.setPreferredSize(new Dimension(ICON_SIZE, ICON_SIZE));
        imgIcon.setSize(new Dimension(ICON_SIZE, ICON_SIZE));
        
        // Create the overlay, if appropriate:
        if (overlayText != null) {
            lblOverlay = new JLabel(overlayText);
            lblOverlay.setForeground(Color.white);
            pnlIcon.add(lblOverlay, pnlIcon.getComponentCount());
        }
        pnlIcon.add(imgIcon, pnlIcon.getComponentCount());
        
        Dimension s = new Dimension(16,16);
        pnlIcon.setMinimumSize(s);
        pnlIcon.setMaximumSize(s);
        pnlIcon.setPreferredSize(s);
        pnlIcon.setSize(s);
        pnlIcon.setOpaque(false);
        add(pnlIcon);
        
        // Load the bar images:
        ImageIcon imgBarBad;
        ImageIcon imgBarMiddle;
        ImageIcon imgBarGood;
        ImageIcon imgBarEmpty = spriteProxy.generateProxyObject("stat_empty.gif").toImageIcon();
        if (barsReversed) {
            imgBarBad = spriteProxy.generateProxyObject("stat_bad.gif").toImageIcon();
            imgBarMiddle = spriteProxy.generateProxyObject("stat_middle.gif").toImageIcon();
            imgBarGood = spriteProxy.generateProxyObject("stat_good.gif").toImageIcon();
        } else {
            imgBarBad = spriteProxy.generateProxyObject("stat_good.gif").toImageIcon();
            imgBarMiddle = spriteProxy.generateProxyObject("stat_middle.gif").toImageIcon();
            imgBarGood = spriteProxy.generateProxyObject("stat_bad.gif").toImageIcon();
        }
        
        // Create the bars:
        lblBar = new JLabel[NUMBER_BARS];
        for (int h = 0; h < lblBar.length; h++)
        {
            if (h <= (lblBar.length / 3))
            {
                lblBar[h] = new JLabel(imgBarBad);
            }
            else if (h <= 2 * (lblBar.length / 3))
            {
                lblBar[h] = new JLabel(imgBarMiddle);
            }
            else
            {
                lblBar[h] = new JLabel(imgBarGood);
            }
            lblBar[h].setDisabledIcon(imgBarEmpty);
            lblBar[h].setEnabled(false);
            add(lblBar[h]);
        }
    }

    public void setPercentage(double percentage)
    {
        int barsToDisplay = (int) Math.floor(percentage * lblBar.length);
        for (int i = 0; i < lblBar.length; i++)
        {
            lblBar[i].setEnabled(i <= barsToDisplay);
            lblBar[i].setToolTipText((int)(percentage * 100) + "%");
        }
    }
    
    public void setOverlayText(String text) {
        lblOverlay.setText(text);
    }
    
    public static int getIconSize() {
        return ICON_SIZE;
    }
    
    private JLabel[] lblBar;
    private static final int NUMBER_BARS = 50;
    private static final int ICON_SIZE = 15;
    private static SpriteProxy spriteProxy = new SpriteProxy();
    private JLabel lblOverlay;
    private JPanel pnlIcon;
}
