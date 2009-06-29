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
        Graphics2D target = getTargetGraphics2D();
        Color originalColour = target.getColor();
            target.setColor(new Color(255, 128, 64, 80));
            int startX = getCentreX() - radius,
                    startY = getCentreY() - radius;
            target.fillOval(startX, startY, radius * 2, radius * 2);
        target.setColor(originalColour);
    }
    
    @Override
    protected double getTotalAnimationTimeMS() {
        return 2000;
    }
    
    protected int maxRadius;
}
