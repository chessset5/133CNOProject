package com.mycompany.a2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Observable;

import com.codename1.charts.models.Point;
import com.mycompany.a2.GameObjects.*;
import com.mycompany.a2.GameObjects.Fixed.*;
import com.mycompany.a2.GameObjects.Movable.*;

public class GameWorld extends Observable {
    private int lives;
    private int clock;
    private int winClock;
    private int numOfFlags;
    private int numOfSpiders;
    private int numOfFoodStations;
    private boolean soundOn;

    private int accelerationRate = 3;
    private int turnRate = 15;

    // Hash Keys for objects
    private String flagTag = "Flag";
    private String antTag = "Ant";
    private String spiderTag = "Spider";
    private String foodStationTag = "FoodStation";

    /**
     * Object to hold all the GameObjects
     * previously named gameObjects
     */
    Hashtable<String, GameObject> gameObjects = new Hashtable<String, GameObject>();

    // Init Methods

    public void init() {
        this.lives = 3;
        this.clock = 0;
        this.soundOn = false;
        this.initObjects();
        this.setChanged();
        this.notifyObservers(this);
    }

    private void initObjects() {
        // Flags
        this.gameObjects.put("Flag1", new Flag(new Point(50.0f, 50.0f)));
        this.gameObjects.put("Flag2", new Flag(new Point(350.0f, 550.0f)));
        this.gameObjects.put("Flag3", new Flag(new Point(400.0f, 900.0f)));
        this.gameObjects.put("Flag4", new Flag(new Point(700.0f, 200.0f)));
        this.gameObjects.get("Flag1").setName("Flag1");
        this.gameObjects.get("Flag2").setName("Flag2");
        this.gameObjects.get("Flag3").setName("Flag3");
        this.gameObjects.get("Flag4").setName("Flag4");

        this.numOfFlags = 4;

        // Ant
        // settings to Flag1 position, heading 0, and speed 10
        this.gameObjects.put(antTag, new Ant(gameObjects.get("Flag1").getLocation(), 0, 10));
        this.gameObjects.get(antTag).setName(antTag);

        // Spiders
        this.numOfSpiders = 3;
        for (int i = 1; i < this.numOfSpiders; i++) {
            this.gameObjects.put((spiderTag + i), new Spider());
            this.gameObjects.get((spiderTag + i)).setName((spiderTag + i));
        }

        // Food Stations
        this.numOfFoodStations = 0;
        for (int i = 1; i < 5; i++) {
            this.gameObjects.put((foodStationTag + i), new FoodStation());
            this.gameObjects.get((foodStationTag + i)).setName((foodStationTag + i));
            this.numOfFoodStations++;
        }
    }

    private void restartInitObjects() {
        this.lives -= 1;

        // If there are no more lives left
        if (this.looseCondition()) {
            this.loose();
            return;
        }

        System.out.println("Resetting Game World");

        // TODO: Find out if this is needed. I believe it is not
        this.numOfFlags = 4;

        // Ant only resets
        // settings to Flag1 position, heading 0, and speed 10
        this.gameObjects.put(antTag, new Ant(gameObjects.get("Flag1").getLocation(), 0, 10));
        this.gameObjects.get(antTag).setName(antTag);

        this.hasChanged();
        this.notifyObservers(this);
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

    // Game states

    /**
     * @return True if win condition met,
     *         false otherwise
     */
    public boolean winCondition() {
        return ((Ant) this.gameObjects.get(antTag)).getLastFlag() == this.numOfFlags;
    }

    /**
     * @return True if loose condition met,
     *         false otherwise
     */
    public boolean looseCondition() {
        return this.lives == 0;
    }

    /**
     * @return True if restart condition met,
     *         false otherwise
     * @note restart condition is defined as the ant having 0 health or 0 food level
     */
    public boolean restartCondition() {
        if (((Ant) this.gameObjects.get(antTag)).isAntDead()) {
            System.out.println("\nAnt Died");
            return true;
        }
        if (((Ant) this.gameObjects.get(antTag)).isStarved()) {
            System.out.println("\nAnt starved");
            return true;
        }
        return false;
    }

    /**
     * Win Print out
     */
    public void win() {
        System.out.println(("\nGame over, you win!\n" +
                "Total time: " + winClock));
    }

    /**
     * True disables all keys but y.
     * Used for preventing the game from continuing once all lives are lost.
     */
    private boolean inLooseExit = false;

    /**
     * Loose Print Out
     */
    public void loose() {
        System.out.println("\nGame over, you failed!");
        // 3 second sleep
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        System.out.println("\nPlease press y to exit");
        this.inLooseExit = true;
        this.inExit = true;
    }

    /**
     * Restarts Game World without resetting game
     */
    public void restart() {
        this.restartInitObjects();
    }

    public void setSound(boolean sound) {
        this.soundOn = sound;
        this.setChanged();
        this.notifyObservers();
    }

    // key presses //

    /**
     * increases player ant's speed
     */
    public void accelerate() {
        System.out.println("accelerate");
        ((Ant) this.gameObjects.get(antTag)).increaseSpeed(accelerationRate);
    }

    /**
     * decreases player ant's speed to 0
     */
    public void brake() {
        System.out.println("braking");
        ((Ant) this.gameObjects.get(antTag)).increaseSpeed(-1 * accelerationRate);
    }

    /**
     * turn player ant left
     */
    public void left() {
        System.out.println("turned left");
        ((Ant) this.gameObjects.get(antTag)).incrementHeading(-1 * turnRate);
    }

    /**
     * turn player ant right
     */
    public void right() {
        System.out.println("turned right");
        ((Ant) this.gameObjects.get(antTag)).incrementHeading(turnRate);
    }

    /**
     * changes food consumption rate
     */
    public void setFoodConsumptionRate(int rate) {
        System.out.println("food consumption rate was set to " + rate);
        ((Ant) this.gameObjects.get(antTag)).setFoodConsumptionRate(rate);
    }

    public void collideFlag(int flagNum) {
        System.out.println("Flag" + ((Integer) flagNum).toString() + "was stepped over");
        ((Ant) this.gameObjects.get(antTag)).setNextFlag(flagNum);
    }

    /**
     * Boolean to prevent two different Food Stations from being consumed in the
     * same tick
     * Boolean Flag
     */
    private boolean foodTick = false;

    public void collideFoodStation() {
        System.out.println("Looking for food");

        // prevent two consumptions in one tick
        if (this.foodTick == false) {
            return;
        }
        int i = 1;
        while (((FoodStation) gameObjects.get(foodStationTag + i)).getCapacity() == 0) {
            i++;
        }
        ((Ant) this.gameObjects.get(antTag))
                .incrementFoodLevel(((FoodStation) gameObjects.get(foodStationTag + i))
                        .consume(((FoodStation) gameObjects.get(foodStationTag + i)).getCapacity()));
        this.numOfFoodStations++;
        this.gameObjects.put((foodStationTag + this.numOfFoodStations),
                new FoodStation((foodStationTag + this.numOfFoodStations)));
        this.foodTick = false;
    }

    public void collideSpider() {
        System.out.println("Look out for 8 legs");
        ((Ant) this.gameObjects.get(antTag)).takeDamage(1);
    }

    public void tick() {
        System.out.println("Time has elapsed");

        foodTick = true;
        for (GameObject go : gameObjects.values()) {
            go.tick();
        }
        if (this.winCondition()) {
            this.win();
        }
        if (this.restartCondition()) {
            this.restartInitObjects();
        }

        this.clock++;
    }

    public void display() {
        System.out.println();
        System.out.println("Lives: " + this.lives);
        System.out.println("Clock: " + this.clock);
        System.out.println("Ant's Highest Flag: " + ((Ant) this.gameObjects.get(antTag)).getLastFlag());
        System.out.println("Ant's Current Food Level: " + ((Ant) this.gameObjects.get(antTag)).getFoodLevel());
        System.out.println(
                "Ant's Current Health Level: " + ((Ant) this.gameObjects.get(antTag)).getHealthLevel());
        System.out.println("Current number of loaded objects: " + this.gameObjects.size());
    }

    public void map() {
        System.out.println("\nDisplaying Map");

        // Stupid way to get all the values to sort
        // TODO: redo this using maps or something,
        // there has to be a faster or cleaner way
        ArrayList<String> tmpList = new ArrayList<String>();
        for (GameObject go : this.gameObjects.values()) {
            tmpList.add(go.toString());
        }
        Collections.sort(tmpList);

        for (String tmp : tmpList) {
            System.out.println(tmp);
        }
    }

    // Exit condition //

    /**
     * Boolean for entering Y/N confirm
     * Boolean Flag
     * 
     * @True in Exit state
     * @False in normal state
     */
    private boolean inExit = false;

    public void exit() {
        if (this.inExit) {
            System.out.println("\nKey press denied\nIn exit mode.");
        }
        if (this.inLooseExit) {
            System.out.println("\nKey press denied\nPlease press y to exit.");
            this.inExit = true;
            this.inLooseExit = true;
            return;
        }

        this.inExit = true;
        System.out.println("\nDo you wish to exit?\ny/n");
    }

    public void confirm() {
        if (this.inExit) {
            System.exit(0);
        }
    }

    public void deny() {
        if (this.inExit) {
            System.out.println("\nn confirmed\nreturning to game world");
        }
        this.inExit = false;
    }

    /**
     * @return
     * @True if in exit state
     * @False if in normal state
     */
    public boolean inExit() {
        return this.inExit;
    }
}
