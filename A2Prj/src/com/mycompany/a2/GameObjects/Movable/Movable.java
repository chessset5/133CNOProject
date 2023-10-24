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

    /**
     * Food Level can not be less than 0
     * 
     * @param newFoodLevel
     */
    protected void setFoodLevel(int newFoodLevel) {
        // food level can't be less than 0
        if (newFoodLevel < 0) {
            newFoodLevel = 0;
        }
        this.foodLevel = newFoodLevel;
    }

    /**
     * @return which way the object is facing, 0 North/Up, 90 East/Right, and so on.
     */
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
     * @note If Moveable object does not have a food level, ie 0 or less, the object
     *       will not move
     * 
     */
    public boolean move() {
        // Object can not move if:
        if (this.foodLevel <= 0) {
            return false;
        }

        int theta = 90 - heading;
        float delX = (float) Math.cos(Math.toRadians((double) theta));
        float delY = (float) Math.sin(Math.toRadians((double) theta));
        delX *= this.speed;
        delY *= this.speed;
        delX += this.getLocation().getX();
        delY += this.getLocation().getY();

        // Object will not move out of bounds
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
     * @return true
     */
    public boolean incrementHeading(int inc) {
        this.setHeading(this.heading + inc);
        return true;
    }

    /**
     * Increments Food level, can go up or down depending on positive or negative
     * increments
     * 
     * @param inc
     * @return true
     */
    public boolean incrementFoodLevel(int inc) {
        this.setFoodLevel(this.foodLevel + inc);
        return true;
    }

    /**
     * Sets speed to 0
     * 
     * @return True
     */
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
