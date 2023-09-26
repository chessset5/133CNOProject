package com.mycompany.a1.GameObjects.Movable;

import com.codename1.charts.models.Point;
import com.mycompany.a1.GameObjects.GameObject;

public abstract class Movable extends GameObject {

    private int heading;
    private int speed;
    private int foodLevel;

    // getters setters //
    protected int getSpeed() {
        return this.speed;
    }

    protected void setSpeed(int newSpeed) {
        this.speed = newSpeed;
    }

    public int getFoodLevel() {
        return this.foodLevel;
    }

    protected void setFoodLevel(int newFoodLevel) {
        if (newFoodLevel < 0) {
            newFoodLevel = 0;
        }
        this.foodLevel = newFoodLevel;
    }

    protected int getHeading() {
        return this.heading;
    }

    public void setHeading(int degrees) {
        // accounting for negative numbers
        degrees %= 360;
        degrees += 360;
        degrees %= 360;
        this.heading = degrees;
    }

    @Override
    public boolean setLocation(Point newLocation) {
        this.setLocation(newLocation);
        GameObject.putInBounds(this);
        return true;
    }

    // movable methods //

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
        this.setHeading(this.heading + inc);
        return true;
    }

    public boolean increaseFoodLevel(int inc) {
        this.setFoodLevel(this.foodLevel + inc);
        return true;
    }

    public boolean resetSpeed() {
        this.speed = 0;
        return true;
    }

    @Override
    public String toString() {
        String parent = super.toString();
        return "" + parent + " heading=" + this.heading + " speed=" + this.speed + " size=" + this.getSize() + "";
    }

}
