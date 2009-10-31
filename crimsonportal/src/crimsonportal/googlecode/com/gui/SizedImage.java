/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.JComponent;

/**
 *
 * @author dagwud
 */
public 

class SizedImage extends JComponent {
    public SizedImage(Image image) {
        this.image = image;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image == null) {
            return;
        }
        Insets insets = getInsets();
        int x = insets.left;
        int y = insets.top;

        int w = getWidth() - insets.left - insets.right;
        int h = getHeight() - insets.top - insets.bottom;

        int src_w = image.getWidth(null);
        int src_h = image.getHeight(null);

        double scale_x = ((double)w)/src_w;
        double scale_y = ((double)h)/src_h;

        double scale = Math.min(scale_x, scale_y);

        int dest_w = (int)(scale * src_w);
        int dest_h = (int)(scale * src_h);

        int dx = x;
        int dy = y;
        
        g.drawImage(image, dx, dy, dx+dest_w, dy+dest_h, 0, 0, src_w, src_h, null);
    }
    
    protected Image image;
}

