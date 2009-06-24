package crimsonportal.googlecode.com.gui.Animations;

import crimsonportal.googlecode.com.ObjectModel.Location;
import crimsonportal.googlecode.com.gui.Animation;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author jdevenish
 */
public class NukeAnimation extends Animation {
    public NukeAnimation(Location centreLocation, int maxRadius) {
        super(centreLocation);
        this.maxRadius = maxRadius;
    }
    
    public void drawAnimation() {
        double dblRadius = getPercentageComplete() * maxRadius;
        int radius = (int)dblRadius;
        if (radius > 0) { 
            System.out.print("");
        }
        Graphics2D targetGraphics = (Graphics2D)super.targetGraphics.getGraphics();
        Color originalColour = targetGraphics.getColor();
            targetGraphics.setColor(new Color(255, 128, 64, 80));
            int startX = getCentreX() - radius,
                    startY = getCentreY() - radius;
            targetGraphics.fillOval(startX, startY, radius * 2, radius * 2);
        targetGraphics.setColor(originalColour);
    }
    
    @Override
    protected double getTotalAnimationTimeMS() {
        return 200;
    }
    
    protected int maxRadius;
}
