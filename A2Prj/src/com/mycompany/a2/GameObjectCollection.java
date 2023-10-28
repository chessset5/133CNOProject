package com.mycompany.a2;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import com.mycompany.a2.GameObjects.GameObject;
import com.mycompany.a2.Interfaces.ICollection;
import com.mycompany.a2.Interfaces.IIterator;

public class GameObjectCollection implements ICollection {
    // mostly from slides
    private Vector<GameObject> gameObjects;

    public GameObjectCollection() {
        gameObjects = new Vector<GameObject>();
    }

    public GameObjectCollection(Vector<GameObject> old) {
        gameObjects = new Vector<GameObject>();
    }

    @Override
    public void add(Object newObject) {
        gameObjects.addElement((GameObject) newObject);
    }

    @Override
    public IIterator getIterator() {
        return new MyItterator();
    }

    public void put(String name, GameObject gameObject) {
        // because I did everything as a hash I need to add this. RIP hash method...
        // name gets thrown out
        gameObjects.add(gameObject);
    }

    public int size() {
        return gameObjects.size();
    }

    private class MyItterator implements IIterator {
        private int index;

        public MyItterator() {
            index = -1;
        }

        @Override
        public boolean hasNext() {
            // assuming there is threading, using vector synchronization
            synchronized (gameObjects) {
                // from slides
                if (gameObjects.size() <= 0) {
                    return false;
                }
                if (index == (gameObjects.size() - 1)) {
                    return false;
                }
                return true;
            }
        }

        @Override
        public Object getNext() {
            // assuming safe threading
            synchronized (gameObjects) {
                // from slides
                index++;
                return gameObjects.elementAt(index);
            }
        }
    }

}
