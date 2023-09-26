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
        int tmpHeading = 90 - heading;
        float delX = (float) Math.cos(Math.toRadians((double) tmpHeading));
        float delY = (float) Math.sin(Math.toRadians((double) tmpHeading));
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

}
