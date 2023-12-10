package com.mycompany.a3.GameObjects.Fixed;

import com.codename1.charts.util.ColorUtil;
import com.mycompany.a3.GameObjects.GameObject;

public class FoodStation extends Fixed {

    private int capacity;

    public FoodStation() {
        super();
        // Food Stations are Red
        this.setColor(ColorUtil.argb(255, 0, 255, 0));
        this.capacity = this.getSize();
    }

    public FoodStation(String name, Integer i) {
        this();
        this.setName(GameObject.key(name, i));
    }

    public int getCapacity() {
        return this.capacity;
    }

    public int consume(int eaten) {
        if ((this.capacity - eaten) > 0) {
            this.capacity -= eaten;
            return eaten;
        } else {
            // no capacity left
            int tmp = new Integer(this.capacity);
            this.capacity = 0;
            // set food station to black if no capacity
            this.setColor(ColorUtil.argb(0, 0, 0, 0));
            return tmp;
        }

    }

    @Override
    public String toString() {
        String parent = super.toString();
        return "" + parent + " capacity=" + this.capacity + "";
    }

    @Override
    public void tick() {
        // ensuring that object goes to black if capacity is drained
        if (this.capacity <= 0) {
            this.capacity = 0;
            this.setColor(ColorUtil.argb(0, 0, 0, 0));
        }
    }

    /**
     * returns s00i
     * string + 3 left padded 0's if less than 4
     * Used for key if using hashmap
     * 
     * @param s foo, bar
     * @param i 1, 1111
     * @return foo001, bar1111
     */
    public static String name(String s, Integer i) {
        return GameObject.name(s, i);
    }

    /**
     * Returns the key value for FoodStation.
     * FoodStation has a different format due to map
     * 
     * @param s
     * @param i
     * @return
     */
    public static String key(String s, Integer i) {
        return GameObject.name(s, i);
    }

}
