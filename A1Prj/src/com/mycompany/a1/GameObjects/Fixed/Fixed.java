package com.mycompany.a1.GameObjects.Fixed;

import com.codename1.charts.models.Point;
import com.mycompany.a1.GameObjects.GameObject;

public abstract class Fixed extends GameObject {

    public Fixed() {
        super();
    }

    public Fixed(Point firstLocation) {
        super();
        super.setLocation(firstLocation);
    }

    /**
     * Fixed objects can not move
     */
    @Override
    public boolean setLocation(Point newLocation) {
        return false;
    }

    public void tick() {

    }

    @Override
    public String toString() {
        String parent = super.toString();
        return "" + parent + " size=" + this.getSize() + "";
    }

}
