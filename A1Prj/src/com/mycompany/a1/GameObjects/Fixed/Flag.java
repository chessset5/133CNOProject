package com.mycompany.a1.GameObjects.Fixed;

// Code Name One libraries
import com.codename1.charts.util.ColorUtil;
import com.mycompany.a1.GameObjects.GameObject;
import com.codename1.charts.models.Point;

public class Flag extends Fixed {
    private static int sequenceNumber = 1;

    private int flagNumber;

    public Flag(Point startLocation) {
        super();
        this.ObjectSize = 10;
        // Flags are Orange
        this.myColor = ColorUtil.argb(255, 255, 165, 0);
        this.location = startLocation;
        GameObject.putInBounds(this);
        this.flagNumber = Flag.sequenceNumber;
        Flag.sequenceNumber += 1;
    }

    public int getFlagNumber() {
        return flagNumber;
    }

    @Override
    public boolean setColor(int newColor) {
        return false;
    }

}
