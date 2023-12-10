package com.mycompany.a3.GameObjects.Fixed;

// Code Name One libraries
import com.codename1.charts.util.ColorUtil;
import com.mycompany.a3.GameObjects.GameObject;
import com.codename1.charts.models.Point;

public class Flag extends Fixed {
    private static int sequenceNumber = 1;

    private int flagNumber;

    public Flag(Point startLocation) {
        // setting initial location
        // Flag can not have its location changed once set
        super(startLocation);
        // make sure flag is in bounds
        GameObject.putInBounds(this);

        this.setSize(10);
        // Flags are Orange
        this.setColor(ColorUtil.argb(255, 255, 165, 0));
        GameObject.putInBounds(this);

        // TODO: put it into static object to assign flag numbers
        // Insures that each flag is given different numbers (so long as not threaded)
        this.flagNumber = Flag.sequenceNumber;
        Flag.sequenceNumber += 1;
    }

    /**
     * @return flag number
     */
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

    /**
     * Does nothing during tick
     */
    @Override
    public void tick() {

    }

}
