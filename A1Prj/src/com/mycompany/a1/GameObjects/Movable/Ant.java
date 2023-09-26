package com.mycompany.a1.GameObjects.Movable;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.mycompany.a1.GameObjects.GameObject;

public class Ant extends Movable {

    public Ant() {
        super();
        this.ObjectSize = 5;
        // Ant Color is RED
        this.myColor = ColorUtil.argb(255, 255, 0, 0);
    }

    public void setLocation(Point newLocation) {
        this.location = newLocation;
        GameObject.putInBounds(this);
    }
}
