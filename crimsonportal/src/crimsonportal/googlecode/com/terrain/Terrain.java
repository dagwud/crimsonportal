/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crimsonportal.googlecode.com.terrain;

import crimsonportal.googlecode.com.ObjectModel.Location;
import crimsonportal.googlecode.com.ObjectModel.Map;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

/**
 *
 * @author dagwud
 */
public class Terrain {
    protected byte[][] data;
    protected int height;
    protected int width;
    
    public Terrain(int height, int width) {
        this.height = height;
        this.width = width;
        data = new byte[height][width];
    }
    
    public void loadTerrain(String filename) throws FileNotFoundException,
                                                    InvalidTerrainException,
                                                    IOException {
        if (loadTestTerrain) {
            System.out.println("NOTE: LOADING TEST TERRAIN");
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int h = (int)(((double)x / (double)width) * 256.0);
                    h = (int)(double)(-1.0/300.0 * y * y + 250.0);
                    h = (int)(((double)y / (double)height) * 256.0);
                    h = (x < width / 2) ? 0 : 127;
                    data[y][x] = (byte)h;
                }
            }
        }
        else {
            FileInputStream fstream = new FileInputStream(new File(filename));
            for (int y = height - 1; y >= 0; y--) {
                int bytesRead = fstream.read(data[y]);
                if (bytesRead == -1) {
                    throw new InvalidTerrainException("Premature end of data");
                }
            }
        }
        
        highestX = 0; highestY = 0;
        int lowestX = 0; int lowestY = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (getHeightAt(y, x) >= getHeightAt(highestY,highestX)) {
                    highestY = y; highestX = x;
                }
                if (getHeightAt(y, x) < getHeightAt(lowestY,lowestX)) {
                    lowestY = y; lowestX = x;
                }
            }
        }
        System.out.println("Peak is " + getHeightAt(highestY, highestX) +" at " + highestY + "/" + height + ", " + highestX + "/" + width);
        System.out.println("Trough is " + getHeightAt(lowestY, lowestX) +" at " + lowestY + "/" + height + ", " + lowestX + "/" + width);
    }
    
    protected int getHeightAt(int y, int x) {
        if (y < 0 || y >= height) {
            throw new IllegalArgumentException("Y-value " + y + " exceeds terrain range 0-" + (height - 1));
        }
        if (x < 0 || x >= width) {
            throw new IllegalArgumentException("X-value " + x + " exceeds terrain range 0-" + (width - 1));
        }
        return unsignedByteToInt(data[y][x]);
    }
    
    public int getHeightAt(double y, double x, int mapHeight, int mapWidth) {
        if (y < 0) {
            System.out.println("Y-value " + y + " exceeds given map range 0-" + (mapHeight - 1));
            y = 0;
        }
        if (y >= mapHeight) {
            System.out.println("Y-value " + y + " exceeds given map range 0-" + (mapHeight - 1));
            y = mapHeight - 1;
        }
        if (x < 0) {
            System.out.println("X-value " + x + " exceeds given map range 0-" + (mapWidth - 1));
            x = 0;
        }
        if (x >= mapWidth) {
            System.out.println("X-value " + x + " exceeds given map range 0-" + (mapWidth - 1));
            x = mapWidth - 1;
        }
        double yPerc = y / (double)mapHeight;
        double xPerc = x / (double)mapWidth;
        double yRelative = yPerc * (double)height;
        double xRelative = xPerc * (double)width;
        int yIndex = (int)Math.round(yRelative);
        int xIndex = (int)Math.round(xRelative);
        return getHeightAt(yIndex, xIndex);
    }
    
    public int getPeakHeight() {
        return unsignedByteToInt(data[highestY][highestX]);
    }
    
    public int getHeightAt(double y, double x, Map map) {
        return getHeightAt(y, x, map.getHeight(), map.getWidth());
    }
    protected static int unsignedByteToInt(byte b) {
        return (int) (b & 0xFF);
    }
    
    public Location getMoveWithGradient(Location fromPoint, Location toPoint, Map map)
    {
        double toY = toPoint.getY(), fromY = fromPoint.getY(),
                toX = toPoint.getX(), fromX = fromPoint.getX();
        double heightFrom = getHeightAt(fromY, fromX, map);
        double heightTo = getHeightAt(toY, toX, map);
        double heightDiff = Math.abs((heightTo - heightFrom)) * HEIGHT_SCALE;
        
        boolean isAscending = (heightFrom < heightTo);
        double dist = Math.sqrt((toY - fromY)*(toY - fromY) + (toX - fromX) * (toX - fromX));
        
        if (Double.isNaN(dist) || dist == 0.0) {
            // not moving:
            return toPoint;
        }
        
        double newDist;
        
        System.out.println("height " + heightFrom + " -> " + heightTo);
        if (isAscending) {

            System.out.println("ascending");
            double gradient = Math.tan( heightDiff / dist );

            double h = dist * Math.sin(gradient);
            newDist = Math.sqrt( (dist * dist) - (h * h) );
        }
        else {
            // Descending:
            System.out.println("descending");
            newDist = Math.sqrt( (dist * dist) - (heightDiff * heightDiff) );
            if (Double.isNaN(newDist) || newDist < HEIGHT_SCALE) {
                // heightDiff^2 and dist^2 are too close together; move dist:
                newDist = dist;
            }
        }
        System.out.println("dist " + dist + "  heightDiff " + heightDiff);
        System.out.println("newDist is " + newDist);

        // Resolve newDist to X and Y components:
        double moveY = toY - fromY, moveX = toX - fromX;
        double turn = Math.asin( moveY / dist );
        if (moveX < 0) {
            turn = Math.PI - turn;
        }

        double sinTurn;
        if (turn == Math.toRadians(90)) {
            sinTurn = 1; // Java doesn't do sin(90) very well
        } else {
            sinTurn = Math.sin(turn);
        }

        double sin90MinusTurn;
        if (turn == 0) {
            sin90MinusTurn = 1; // Java doesn't do sin(90) very well
        } else {
            sin90MinusTurn = Math.sin(Math.toRadians(90) - turn);
        }

        // Calculate the new distance to be moved in X and Y directions:
        double newDistY = (newDist * sinTurn) / sin90;
        double newDistX = (newDist * sin90MinusTurn) / sin90;
        if (moveY > 0) {
            if (moveX < 0) newDistX = -newDistX;
        } else {
            newDistY = -newDistY;
        }
        
        // Determine the new moveTo position:
        double turnDeg = cleanDegrees(Math.toDegrees(turn));
        if (turnDeg >= 0 && turnDeg < 180) {
            // 0-180deg: Move south
            toY = fromY + newDistY;
        } else {
            // 180-360deg: Move north
            toY = fromY - newDistY;
        }
        if (turnDeg >= 90 && turnDeg < 180) {
            // 90-180deg: Move west
            toX = fromX - newDistX;
        } else {
            // 180-90deg: Move east
            toX = fromX + newDistX;
        }
        
        return new Location(toX, toY);
    }
    
    protected static String byteToHex(byte b) {
        int i = b & 0xFF;
        return Integer.toHexString(i).toUpperCase();
    }
    
    protected static double cleanDegrees(double degrees) {
        double deg = degrees;
        while (deg < 0) deg += 360;
        while (deg >= 360) deg -= 360;
        return deg;   
    }
    
    public Location convertTerrainToMapLocation(int y, int x, Map map) {
        double yPerc = (double)y / (double)height;
        double xPerc = (double)x / (double)width;
        System.out.println("Perc: " + yPerc + "  " + xPerc);
        double yRelative = yPerc * (double)map.getHeight();
        double xRelative = xPerc * (double)map.getWidth();
        int yIndex = (int)Math.floor(yRelative);
        int xIndex = (int)Math.floor(xRelative);
        return new Location(xIndex, yIndex);
    }
    
    private boolean loadTestTerrain = !true;
    public int highestY = 0, highestX = 0;
    static final double sin90 = 1; // Constant
    static final double const180deg = Math.toRadians(180.0);
    static final double const90deg = Math.toRadians(90.0);
    static final double HEIGHT_SCALE = 0.5;
}
