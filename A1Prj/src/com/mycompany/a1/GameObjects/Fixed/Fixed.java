package com.mycompany.a1.GameObjects.Fixed;

import com.codename1.charts.models.Point;
import com.mycompany.a1.GameObjects.GameObject;

public abstract class Fixed extends GameObject {

    public Fixed() {
        super();
    }

    @Override
    public boolean setLocation(Point newLocation) {
        return false;
    }

    public abstract void tick();
}
