package com.mycompany.a1.GameObjects;

//Code Name One Libraries
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

// Java Libraries
import java.util.Random;
import java.lang.Math;

// My libraries
import com.mycompany.a1.GameWorld;

/**
 * GameObject. All the objects on the map
 */
public abstract class GameObject {

    protected Random rand = new Random();

    protected Integer ObjectSize;

    protected Point location;

    protected int myColor;

    // TODO: Impliment tick
    public abstract void tick();

    public GameObject() {
        // ObjectSize is between 10 and 50
        this.ObjectSize = rand.nextInt(41) + 10;
        this.location = new Point(rand.nextFloat() * 1000.0f, rand.nextFloat() * 1000.0f);
        GameObject.putInBounds(this);
        this.myColor = ColorUtil.BLUE;
    }

    public Point getLocation() {
        return location;
    }

    public boolean setLocation(Point newLocation) {
        location = newLocation;
        return true;
    }

    public int getColor() {
        return myColor;
    }

    public boolean setColor(int newColor) {
        myColor = newColor;
        return true;
    }

    // Class Statics //

    /**
     * Puts object in bounds if horribly out of bounds.
     * Should not be used for bump checking
     * 
     * @param GameObject
     */
    public static void putInBounds(GameObject go) {
        // TODO: Move to GameWorld.
        // This is more of a GameWorld responsibility than a GameObject
        // responsibility
        if (go.location.getX() > GameWorld.getWorldMax().getX()) {
            Integer tmpX = new Integer(Math.round(go.location.getX()) % Math.round(GameWorld.getWorldMax().getX()));
            go.location.setX((float) tmpX.doubleValue());
        } else if (go.location.getX() < GameWorld.getWorldMax().getX()) {
            while (go.location.getX() < GameWorld.getWorldMax().getX()) {
                go.location.setX(go.location.getX() + GameWorld.getWorldMax().getX());
            }
            Integer tmpX = new Integer(Math.round(go.location.getX()) % Math.round(GameWorld.getWorldMax().getX()));
            go.location.setX((float) tmpX.doubleValue());
        }
        if (go.location.getY() > GameWorld.getWorldMax().getY()) {
            Integer tmpX = new Integer(Math.round(go.location.getY()) % Math.round(GameWorld.getWorldMax().getY()));
            go.location.setY((float) tmpX.doubleValue());
        } else if (go.location.getY() < GameWorld.getWorldMax().getY()) {
            while (go.location.getY() < GameWorld.getWorldMax().getY()) {
                go.location.setY(go.location.getY() + GameWorld.getWorldMax().getY());
            }
            Integer tmpX = new Integer(Math.round(go.location.getY()) % Math.round(GameWorld.getWorldMax().getY()));
            go.location.setY((float) tmpX.doubleValue());
        }
    }

    public static boolean inBoundsCheck(GameObject go) {
        // TODO: Move to GameWorld.
        return GameObject.inBoundsCheck(go.getLocation());
    }

    public static boolean inBoundsCheck(Point pnt) {
        // TODO: Move to GameWorld.
        if (pnt.getX() > GameWorld.getWorldMax().getX()) {
            return true;
        } else if (pnt.getX() < GameWorld.getWorldMax().getX()) {
            return true;
        }
        if (pnt.getY() > GameWorld.getWorldMax().getY()) {
            return true;
        } else if (pnt.getY() < GameWorld.getWorldMax().getY()) {
            return true;
        }
        return false;
    }

}
