/*
 * File          NukeAnimation.java
 *
 * Copyright (C) IST Data 2008, all rights reserved.
 *
 * E-mail        data@ist.co.za
 * Address       Garstfontein Road 221 Menlyn, Pretoria South Africa
 * Tel           +27 (0) 12 426 7200
 * Fax           +27 (0) 12 365 1467
 *
 * Project      Network Inventory Management System
 * Author       jdevenish
 * Date         Jun 24, 2009
 *
 * History:
 *   Author      : jdevenish
 *   Date        : Jun 24, 2009
 *   Description : Created
 */

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
