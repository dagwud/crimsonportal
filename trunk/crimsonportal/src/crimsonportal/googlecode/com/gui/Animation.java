/*
 * File          Animation.java
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

package crimsonportal.googlecode.com.gui;

import crimsonportal.googlecode.com.ObjectModel.Location;
import java.awt.Graphics2D;

/**
 *
 * @author jdevenish
 */
public abstract class Animation extends Thread {
    public Animation(Location centreLocation) {
        this.centreLocation = centreLocation;
        startTimer = System.currentTimeMillis();
    }
    
    public abstract void drawAnimation();
    
    protected int getCentreX() {
        return ((int) Math.floor(centreLocation.getX()));
    }
    
    protected int getCentreY() {
        return ((int) Math.floor(centreLocation.getY()));
    }
    
    protected double getXScale(Graphics2D g) {
        return xScale;
    }
    
    protected double getYScale(Graphics2D g) {
        return yScale;
    }
    
    protected long getElapsedTimeMS() {
        long elapsedTime = System.currentTimeMillis() - startTimer;
        return elapsedTime;
    }
    
    protected double getPercentageComplete() {
        double elapsedTimeMS = getElapsedTimeMS();
        double perc = elapsedTimeMS / getTotalAnimationTimeMS();
        return perc;
    }
    
    protected final void setTargetGraphics2D(Graphics2D targetGraphics) {
        this.targetGraphics = targetGraphics;
    }
    
    public final boolean isAnimationComplete() {
        return (getPercentageComplete() >= 1);
    }
    
    public final boolean hasStarted() {
        return (targetGraphics != null);
    }
    
    @Override
    public final void run()
    {
        while ( !isAnimationComplete() ) {
            drawAnimation();
            try {
                Thread.sleep(100); // avoid monopolising the processor
            }
            catch (InterruptedException e) {} // ignore
        }
    }
    
    public Graphics2D getTargetGraphics2D() {
        return targetGraphics;
    }
    
    protected abstract double getTotalAnimationTimeMS();
    
    private long startTimer;
    private Location centreLocation;
    private double xScale, yScale;
    private Graphics2D targetGraphics;
}
