package com.mycompany.a2.GameObjects.Fixed;

import com.codename1.charts.models.Point;
import com.mycompany.a2.GameObjects.GameObject;

public abstract class Fixed extends GameObject {

    /**
     * Fixed will have a random position
     */
    public Fixed() {
        super();
    }

    /**
     * Sets fixed position
     * 
     * @param firstLocation
     */
    public Fixed(Point firstLocation) {
        super();
        super.setLocation(firstLocation);
    }

    public abstract void tick();

    /**
     * Fixed objects will not move
     */
    @Override
    public boolean setLocation(Point newLocation) {
        return false;
    }

    @Override
    public String toString() {
        String parent = super.toString();
        return "" + parent + " size=" + this.getSize() + "";
    }

}
