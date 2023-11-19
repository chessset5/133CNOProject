package com.mycompany.a3.GameObjects.Movable;

// Singleton Ant
public class SingleAnt {
    private static Ant singleAnt;

    private SingleAnt() {
    }

    public static Ant getAnt() {
        if (singleAnt == null) {
            singleAnt = new Ant();
        }
        return singleAnt;
    }
}
