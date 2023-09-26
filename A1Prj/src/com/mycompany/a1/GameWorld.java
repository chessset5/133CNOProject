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
    private int numOfFoodStations;

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

    public void initObjects() {
        this.gameObjects.put("Flag1", new Flag(new Point(50.0f, 50.0f)));
        this.gameObjects.put("Flag2", new Flag(new Point(350.0f, 550.0f)));
        this.gameObjects.put("Flag3", new Flag(new Point(400.0f, 900.0f)));
        this.gameObjects.put("Flag4", new Flag(new Point(700.0f, 200.0f)));
        this.gameObjects.get("Flag1").setName("Flag1");
        this.gameObjects.get("Flag2").setName("Flag2");
        this.gameObjects.get("Flag3").setName("Flag3");
        this.gameObjects.get("Flag4").setName("Flag4");

        this.numOfFlags = 4;

        this.gameObjects.put("Player", new Ant(gameObjects.get("Flag1").getLocation()));
        this.gameObjects.get("Player").setName("Player");

        for (int i = 1; i < 3; i++) {
            this.gameObjects.put(("Spider" + i), new Spider());
            this.gameObjects.get(("Spider" + i)).setName(("Spider" + i));

        }

        for (int i = 1; i < 5; i++) {
            this.gameObjects.put(("FoodStation" + i), new FoodStation());
            this.gameObjects.get(("FoodStation" + i)).setName(("FoodStation" + i));
        }
    }

    public void restartInitObjects() {
        this.lives -= 1;
        this.gameObjects.put("Flag1", new Flag(new Point(50.0f, 50.0f)));
        this.gameObjects.put("Flag2", new Flag(new Point(350.0f, 550.0f)));
        this.gameObjects.put("Flag3", new Flag(new Point(400.0f, 900.0f)));
        this.gameObjects.put("Flag4", new Flag(new Point(700.0f, 200.0f)));

        this.numOfFlags = 4;

        this.gameObjects.put("Player", new Ant(gameObjects.get("Flag1").getLocation()));

        for (int i = 1; i < 3; i++) {
            this.gameObjects.put(("Spider" + i), new Spider());
        }
    }

    // word states

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

    public boolean winCondition() {
        return ((Ant) this.gameObjects.get("Player")).getLastFlag() == this.numOfFlags;
    }

    // Game states

    public boolean looseCondition() {
        return this.lives == 0;
    }

    public boolean restartCondition() {
        return ((Ant) this.gameObjects.get("Player")).isAntDead()
    }

    public void win() {
        System.out.println(("Game over, you win!\n" +
                "Total time: " + clock));
    }

    public void loose() {
        System.out.println("Game over, you failed!");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }
        exit();
    }

    public void restart() {
        this.restartInitObjects();
    }

    // key presses //

    public void accelerate() {
        ((Ant) this.gameObjects.get("Player")).increaseSpeed(3);
    }

    public void brake() {
        ((Ant) this.gameObjects.get("Player")).increaseSpeed(-3);
    }

    public void left() {
        ((Ant) this.gameObjects.get("Player")).increaseHeading(-15);
    }

    public void right() {
        ((Ant) this.gameObjects.get("Player")).increaseHeading(15);
    }

    public void setFoodConusmptionRate() {
        ((Ant) this.gameObjects.get("Player")).setFoodConsumptionRate(1);
    }

    public void collideFlag1() {
        ((Ant) this.gameObjects.get("Player")).setNextFlag(1);
    }

    public void collideFlag2() {
        ((Ant) this.gameObjects.get("Player")).setNextFlag(2);
    }

    public void collideFlag3() {
        ((Ant) this.gameObjects.get("Player")).setNextFlag(3);
    }

    public void collideFlag4() {
        ((Ant) this.gameObjects.get("Player")).setNextFlag(4);
    }

    public void collideFoodStation() {
        for (GameObject go : gameObjects.values()) {
            if (go instanceof FoodStation) {
                if (((FoodStation) go).getCapcity() != 0) {
                    ((Ant) this.gameObjects.get("Player")).increaseFoodLevel(((FoodStation) go).getCapcity());
                }
            }
        }
        this.numOfFoodStations++;
        this.gameObjects.put(("FoodStation" + this.numOfFoodStations), new FoodStation());
    }

    public void collideSpider() {
        ((Ant) this.gameObjects.get("Player")).takeDamage(1);
    }

    public void tick() {
        for (GameObject go : gameObjects.values()) {
            go.tick();
        }
        if (this.winCondition()) {
            this.win();
        }
        if (this.looseCondition()) {
            this.loose();
        }
        if (this.restartCondition()) {
            this.restartInitObjects();
        }
    }

    public void display() {
        System.out.println("Lives: " + this.lives);
        System.out.println("Clock: " + this.clock);
        System.out.println("Ant's Highest Flag: " + ((Ant) this.gameObjects.get("Player")).getLastFlag());
        System.out.println("Ant's Current Food Level: " + ((Ant) this.gameObjects.get("Player")).getFoodLevel());
        System.out.println("Ant's Current Health Level: " + ((Ant) this.gameObjects.get("Player")).getHealthLevel());
        System.out.println("Current number of loaded objects: " + this.gameObjects.size());
    }

    public void map() {
        System.out.println("Displaying Map");
        for (GameObject go : gameObjects.values()) {
            go.toString();
        }
    }

    public void exit() {
    }

    public void confirm() {
    }

    public void deny() {
    }
}
