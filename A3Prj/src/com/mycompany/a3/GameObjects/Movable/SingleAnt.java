package com.mycompany.a3.GameObjects.Movable;

// Singleton Ant
public class SingleAnt {
    private static Ant singleAnt;

    private SingleAnt() {
    }

    /**
     * Returns Ant. The same Ant, every time. All the time.
     * @return
     */
    public static Ant getAnt() {
        if (singleAnt == null) {
            singleAnt = new Ant();
        }
        return singleAnt;
    }

    /**
     * short hand for SingleAnt.getAnt()
     * @return
     */
    public static Ant g(){
        return getAnt();
    }
}
