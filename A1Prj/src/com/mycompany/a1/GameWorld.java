package com.mycompany.a1;

import com.codename1.charts.models.Point;
import com.mycompany.a1.GameObjects.GameObject;

public class GameWorld {
    public void init() {
        // code here to create the
        // initial game objects/setup
    }
    // additional methods here to
    // manipulate world objects and
    // related game state data

    /**
     * Bottom Left position
     * Min position for bottom and left
     * 
     * @return bottom left pos
     */
    public static Point getWorldMin() {
        return new Point(0.0f, 0.0f);
    }

    /**
     * Top Right Border
     * Max position for top and right
     * 
     * @return Top Right Position
     */
    public static Point getWorldMax() {
        return new Point(1000.0f, 1000.0f);
    }

    public void accelerate() {
        // TODO: Implement

    }
}
