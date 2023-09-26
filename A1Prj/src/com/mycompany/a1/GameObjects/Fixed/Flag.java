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
        this.setSize(10);
        // Flags are Orange
        this.setColor(ColorUtil.argb(255, 255, 165, 0));
        this.setLocation(startLocation);
        GameObject.putInBounds(this);
        this.flagNumber = Flag.sequenceNumber;
        Flag.sequenceNumber += 1;
    }

    public int getFlagNumber() {
        return flagNumber;
    }

    /**
     * Flag can not change color
     */
    @Override
    public boolean setColor(int newColor) {
        return false;
    }

    /**
     * Flag can not change size
     */
    @Override
    public void setSize(Integer newSize) {
        return;
    }

    @Override
    public String toString() {
        String parent = super.toString();
        return "" + parent + " seqNum=" + this.getFlagNumber() + "";
    }

}
