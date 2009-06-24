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
    
    public boolean drawOnto(Graphics2D g) {
        double dblRadius = getPercentageComplete() * maxRadius;
        int radius = (int)dblRadius;
        if (radius > maxRadius) return false;
        Color originalColour = g.getColor();
            g.setColor(new Color(20, 20, 20, 200));
            g.fillOval(getCentreX() - (radius/2), getCentreY() - (radius/2), radius, radius);
        g.setColor(originalColour);
        return true;
    }
    
    @Override
    protected double getTotalAnimationTimeMS() {
        return 200;
    }
    
    protected int maxRadius;
}
