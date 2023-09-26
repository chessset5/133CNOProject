package com.mycompany.a1.GameObjects.Movable;

import java.util.PriorityQueue;

import com.codename1.charts.models.Point;
import com.mycompany.a1.Game;
import com.mycompany.a1.GameObjects.GameObject;

public abstract class Movable extends GameObject {

    protected int heading;
    protected int speed;
    protected int foodLevel;

    public Movable() {
        super();
    }

    public void tick() {
        this.move();
    }

    /**
     * Done in degrees with 0 being north or up,
     * 90 being right or east and so on.
     * Good job on not following convention CN1!
     * 
     * @return true, false
     *         <ul>
     *         <li>true could move in bounds</li>
     *         <li>false if would move out of bounds</li>
     *         <li>if speed is 0 and is inbounds, will return true</li>
     *         </ul>
     * 
     */
    public boolean move() {
        if (this.foodLevel == 0) {
            this.speed = 0;
        }
        int theta = 90 - heading;
        float delX = (float) Math.cos(Math.toRadians((double) theta));
        float delY = (float) Math.sin(Math.toRadians((double) theta));
        delX *= this.speed;
        delY *= this.speed;
        delX += this.getLocation().getX();
        delY += this.getLocation().getY();
        if (GameObject.inBoundsCheck(new Point(delX, delY)) == false) {
            return false;
        }
        this.setLocation(new Point(delX, delY));
        return true;
    }

    @Override
    public boolean setLocation(Point newLocation) {
        this.location = newLocation;
        GameObject.putInBounds(this);
        return true;
    }

    public boolean increaseSpeed(int inc) {
        this.speed += inc;
        return true;
    }

    /**
     * <ul>
     * <li>Positive values rotate clock wise, from north: right.</li>
     * <li>Negative values rotate counter clock wise, from north: left</li>
     * </ul>
     * 
     * @param inc
     * @return
     */
    public boolean increaseHeading(int inc) {
        this.heading += inc;
        this.heading %= 360;
        return true;
    }

    public boolean increaseFoodLevel(int inc) {
        this.foodLevel += inc;
        return true;
    }

    public int getFoodLevel() {
        return this.foodLevel;
    }

    public boolean resetSpeed() {
        this.speed = 0;
        return true;
    }

    @Override
    public String toString() {
        String parent = super.toString();
        return "" + parent + " heading=" + this.heading + " speed=" + this.speed + " size=" + this.ObjectSize + "";
    }

}
