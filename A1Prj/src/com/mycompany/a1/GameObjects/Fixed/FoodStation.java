package com.mycompany.a1.GameObjects.Fixed;

import com.codename1.charts.util.ColorUtil;

public class FoodStation extends Fixed {

    private int capacity;

    public FoodStation() {
        super();
        // Food Stations are Red
        this.setColor(ColorUtil.argb(255, 0, 255, 0));
        this.capacity = this.getSize();
    }

    public int getCapacity() {
        return this.capacity;
    }

    public int consume(int eaten) {
        if ((this.capacity - eaten) > 0) {
            this.capacity -= eaten;
            return eaten;
        } else {
            int tmp = new Integer(this.capacity);
            this.capacity = 0;
            return tmp;
        }
    }

    @Override
    public void tick() {
        // goes black if it has no capacity
        if (this.getCapacity() == 0) {
            this.setColor(ColorUtil.argb(0, 0, 0, 0));
        }
    }

    @Override
    public String toString() {
        String parent = super.toString();
        return "" + parent + " capacity=" + this.capacity + "";
    }

}
