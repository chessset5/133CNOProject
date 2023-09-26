package com.mycompany.a1;

import java.util.Hashtable;

import com.codename1.charts.models.Point;
import com.mycompany.a1.GameObjects.GameObject;
import com.mycompany.a1.GameObjects.Fixed.Flag;
import com.mycompany.a1.GameObjects.Fixed.FoodStation;
import com.mycompany.a1.GameObjects.Movable.Ant;
import com.mycompany.a1.GameObjects.Movable.Spider;

public class GameWorld {
    private int lives;
    private int clock;
    private int numOfFlags;

    Hashtable<String, GameObject> gameObjects = new Hashtable<String, GameObject>();

    public void init() {
        // code here to create the
        // initial game objects/setup
        this.lives = 3;
        this.clock = 0;
    }

    // additional methods here to
    // manipulate world objects and
    // related game state data

    public void reset() {
        this.lives -= 1;

    }

    public void initObjects() {
        this.gameObjects.put("Flag1", new Flag(new Point(50.0f, 50.0f)));
        this.gameObjects.put("Flag2", new Flag(new Point(350.0f, 550.0f)));
        this.gameObjects.put("Flag3", new Flag(new Point(400.0f, 900.0f)));
        this.gameObjects.put("Flag4", new Flag(new Point(700.0f, 200.0f)));

        this.numOfFlags = 4;

        this.gameObjects.put("Player", new Ant(gameObjects.get("Flag1").getLocation()));

        for (int i = 1; i < 3; i++) {
            this.gameObjects.put(("Spider" + i), new Spider());
        }

        for (int i = 1; i < 5; i++) {
            this.gameObjects.put(("FoodStation" + i), new FoodStation());
        }

    }

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
        ((Ant) this.gameObjects.get("Player")).increaseSpeed(3);
    }

    public boolean winCondition() {
        return ((Ant) this.gameObjects.get("Player")).getLastFlag() == this.numOfFlags;
    }

    public void break(){
        // TODO: impliment
        return;
    }

public void left(){}
 public void right(){}
 public void setFoodConusmptionRate(){}
 public void colideFlag1();
 public void colideFlag2();
 public void colideFlag3();
 public void colideFlag4();
 public void colideFoodStation();
 public void colideSpider();
 public void tick();
 public void display();
 public void map();
 public void exit(); 
 public void confirm();
 public void deny();
}
