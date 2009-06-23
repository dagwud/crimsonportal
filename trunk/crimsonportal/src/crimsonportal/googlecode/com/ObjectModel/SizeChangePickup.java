/*
 * File          SizeChangePickup.java
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
 * Date         Jun 23, 2009
 *
 * History:
 *   Author      : jdevenish
 *   Date        : Jun 23, 2009
 *   Description : Created
 */

package crimsonportal.googlecode.com.ObjectModel;

/**
 *
 * @author jdevenish
 */
public class SizeChangePickup extends TimedPickup {
    public SizeChangePickup(Location location, GameTime expirationTime, double sizeMultiplier) {
        super(location, expirationTime);
        this.sizeMultiplier = sizeMultiplier;
    }
    
    @Override
    public void applyTo(GameTime expirationTime, Unit unit) {
        startExpirationTimer(expirationTime, unit);
        unit.setSize( unit.getSize() * sizeMultiplier );
    }
    
    @Override
    public void unapplyTo(Unit unit) {
        unit.setSize(unit.getSize() / sizeMultiplier);
    }

    @Override
    public String getSpriteFilename() {
        return "pickup.gif";
    }

    @Override
    public int getEffectDurationSeconds() {
        return EFFECT_DURATION_SECONDS;
    }
    
    @Override
    public String toString() {
        return "SizeChangePickup[x" + sizeMultiplier + " for " + effectDurationSeconds + "s]";
    }
    
    protected double sizeMultiplier;
    protected static final int EFFECT_DURATION_SECONDS = 10;
}
