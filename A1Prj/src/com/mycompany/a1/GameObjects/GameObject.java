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

    private Random rand = new Random();

    private Integer size;

    private Point location;

    private int myColor;

    private String name;

    public GameObject() {
        // ObjectSize is between 10 and 50
        this.size = rand.nextInt(41) + 10;
        this.location = new Point(rand.nextFloat() * 1000.0f, rand.nextFloat() * 1000.0f);
        GameObject.putInBounds(this);
        this.myColor = ColorUtil.BLUE;
    }

    public GameObject(String name) {
        this();
        this.setName(name);
    }

    // Getters and Setters

    public Integer getSize() {
        return this.size;
    }

    protected void setSize(Integer newSize) {
        if (newSize < 1) {
            newSize = 1;
        } else if (newSize > 1000) {
            newSize = 1000;
        }
        this.size = newSize;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public Point getLocation() {
        return location;
    }

    protected boolean setLocation(Point newLocation) {
        location = newLocation;
        return true;
    }

    public int getColor() {
        return myColor;
    }

    protected boolean setColor(int newColor) {
        myColor = newColor;
        return true;
    }

    protected Random getRand() {
        return rand;
    }

    // class methods //

    public abstract void tick();

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
        // also clean this up, use mod to restrict the values.
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

    @Override
    public String toString() {
        double xRound = Math.round(this.location.getX() * 10.0f) / 10.0;
        double yRound = Math.round(this.location.getY() * 10.0f) / 10.0;
        return "" + name + " loc=" + xRound + "," + yRound +
                " color: " + "[" + ColorUtil.red(myColor) + ","
                + ColorUtil.green(myColor) + ","
                + ColorUtil.blue(myColor) + "]";
    }

}
