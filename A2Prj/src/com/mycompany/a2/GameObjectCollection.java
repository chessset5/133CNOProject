package com.mycompany.a2;

import java.util.Vector;

import com.mycompany.a2.GameObjects.GameObject;
import com.mycompany.a2.Interfaces.ICollection;
import com.mycompany.a2.Interfaces.IIterator;

public class GameObjectCollection implements ICollection {
    private Vector<GameObject> gameObjects;

    public GameObjectCollection() {
        gameObjects = new Vector<GameObject>();
    }

    public GameObjectCollection(Vector<GameObject> old) {
        gameObjects = new Vector<GameObject>();
        gameObjects.addAll(old);
    }

    @Override
    public void add(Object newObject) {
        gameObjects.add((GameObject) newObject);
    }

    @Override
    public IIterator getIterator() {
        return new MyItterator();
    }

    private class MyItterator implements IIterator {
        private int index;

        public MyItterator() {
            index = 0;
        }

        @Override
        public boolean hasNext() {
            // assuming there is threading, using vector synchronization
            synchronized (gameObjects) {
                // index 0 items 0 -> false
                // index 0 items 1 -> true, etc
                return index < gameObjects.size();
            }
        }

        @Override
        public Object getNext() {
            // assuming safe threading
            synchronized (gameObjects) {
                if (this.hasNext()) {
                    return gameObjects.get(index);
                } else {
                    // if someone doesn't call next first
                    // Should I throw null instead?... no
                    throw new java.util.NoSuchElementException();
                }
            }
        }

    }
}
