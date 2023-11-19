package com.mycompany.a2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
import java.util.Random;

import com.codename1.charts.models.Point;
import com.mycompany.a2.GameObjects.*;
import com.mycompany.a2.GameObjects.Fixed.*;
import com.mycompany.a2.GameObjects.Movable.*;
import com.mycompany.a2.Interfaces.IIterator;

public class GameWorld extends Observable {
    private int lives;
    private int clock;
    private int winClock;
    private int numOfFlags;
    private int numOfSpiders;
    private int numOfFoodStations;
    private boolean soundOn;
    private Random rand;

    // for display printout
    private String mapString;

    // Stuff for ant
    private Point firstFlagLocation;
    private int antFirstHeading;
    private int antFirstSpeed;

    private int accelerationRate = 3;
    private int turnRate = 15;

    // Hash Keys for objects
    private String flagTag = "Flag";
    private String antTag = "Ant";
    private String spiderTag = "Spider";
    private String foodStationTag = "FoodStation";

    /**
     * Object to hold all the GameObjects
     * previously Hashtable<String, GameObject> gameObjects = new Hashtable<String,
     * GameObject>();
     */
    private GameObjectCollection gameObjects;

    // Init Methods

    public void init() {
        this.rand = new Random();
        this.lives = 3;
        this.clock = 0;
        this.winClock = clock;
        this.soundOn = false;
        this.initObjects();
        this.updateMap();
        this.setChanged();
        this.notifyObservers(this);
    }

    private void initObjects() {
        gameObjects = new GameObjectCollection();

        // Flags
        this.numOfFlags = 4;
        for (Integer i = 0; i < this.numOfFlags; i++) {
            Point point = this.randomPoint();
            if (i == 0) {
                firstFlagLocation = point;
            }
            GameObject flag = new Flag(point);
            flag.setName(flagTag + i);
            this.gameObjects.add(flag);
        }

        // Ant
        // settings to Flag1 position, heading 0, and speed 10
        SingleAnt.getAnt().setLocation(firstFlagLocation);
        SingleAnt.getAnt().setHeading(antFirstHeading);
        SingleAnt.getAnt().setSpeed(antFirstSpeed);
        SingleAnt.getAnt().setName(antTag);
        this.collideFlag(1);

        // Spiders
        this.numOfSpiders = 3;
        for (int i = 1; i < this.numOfSpiders; i++) {
            GameObject gameObject = new Spider();
            gameObject.setName(spiderTag + i);
            this.gameObjects.add(gameObject);
        }

        // Food Stations
        this.numOfFoodStations = 5;
        for (int i = 1; i < this.numOfFoodStations; i++) {
            GameObject gameObject = new FoodStation();
            gameObject.setName(foodStationTag + i);
            this.gameObjects.add(gameObject);
        }

        tick();
    }

    private void restartInitObjects() {
        if (this.lives > 0) {
            this.lives -= 1;
        }

        // If there are no more lives left
        if (this.looseCondition()) {
            this.loose();
            return;
        }

        // Ant only resets
        // settings to Flag1 position, heading 0, and speed 10
        SingleAnt.getAnt().resetAnt();
        SingleAnt.getAnt().setLocation(firstFlagLocation);
        SingleAnt.getAnt().setHeading(antFirstHeading);
        SingleAnt.getAnt().setSpeed(antFirstSpeed);
        this.collideFlag(1);

        this.hasChanged();
        this.notifyObservers(this);
        System.out.println("World Rest");
    }

    // getters
    public int getLife() {
        return this.lives;
    }

    public int getclock() {
        return this.clock;
    }

    public int getCurFlag() {
        return SingleAnt.getAnt().getLastFlag();
    }

    public int getFood() {
        return SingleAnt.getAnt().getFoodLevel();
    }

    public int getHealth() {
        return SingleAnt.getAnt().getHealthLevel();
    }

    public boolean getSound() {
        return soundOn;
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

    private static Point maxBounds = new Point(1000.0f, 1000.0f);

    /**
     * Top Right Border
     * Max position for top and right
     * 
     * @return Top Right Position
     */
    public static Point getWorldMax() {
        return maxBounds;
    }

    public static Point setWorldMax(Point newMax) {
        GameWorld.maxBounds = newMax;
        return GameWorld.maxBounds;
    }

    // Random point for flag because it didn't have the functionality
    private Point randomPoint() {
        return new Point(this.rand.nextFloat(), this.rand.nextFloat());
    }

    private Point randomPoint(float xmin, float xmax, float ymin, float ymax) {
        float x = xmin + (this.rand.nextFloat() * (xmax - xmin));
        float y = ymin + (this.rand.nextFloat() * (ymax - ymin));
        return new Point(x, y);
    }

    // Game states

    /**
     * @return True if win condition met,
     *         false otherwise
     */
    public boolean winCondition() {
        boolean didWin = SingleAnt.getAnt().getLastFlag() == this.numOfFlags;
        if (didWin && (winClock == 0)) {
            winClock = clock;
        }
        return didWin;
    }

    /**
     * @return True if loose condition met,
     *         false otherwise
     */
    public boolean looseCondition() {
        return this.lives <= 0;
    }

    /**
     * @return True if restart condition met,
     *         false otherwise
     * @note restart condition is defined as the ant having 0 health or 0 food level
     */
    public boolean restartCondition() {
        if (SingleAnt.getAnt().isAntDead()) {
            System.out.println("\nAnt Died");
            return true;
        }
        if (SingleAnt.getAnt().isStarved()) {
            System.out.println("\nAnt starved");
            return true;
        }
        return false;
    }

    /**
     * Win Print out
     */
    public String win() {
        String retString = ("\nGame over, you win!\n" +
                "Total time: " + winClock + '\n');
        System.out.println(retString);
        return retString;
    }

    /**
     * True disables all keys but y.
     * Used for preventing the game from continuing once all lives are lost.
     */
    // private boolean inLooseExit = false;

    /**
     * Loose Print Out
     */
    public String loose() {
        String retString = "\nGame over, you failed!" + '\n';
        System.out.println(retString);
        // System.out.println("\nPlease press y to exit");
        // this.inLooseExit = true;
        this.inExit = true;
        return retString;
    }

    /**
     * Restarts Game World without resetting game
     */
    public String restart() {
        this.restartInitObjects();
        String retString = "\nResetting Game World\n";
        System.out.println(retString);
        return retString;
    }

    public void setSound(boolean sound) {
        this.soundOn = sound;
        System.out.println("Sound is " + (soundOn ? "on" : "off"));

        this.setChanged();
        this.notifyObservers();
    }

    // key presses //

    /**
     * increases player ant's speed
     */
    public void accelerate() {
        System.out.println("accelerate");
        SingleAnt.getAnt().increaseSpeed(accelerationRate);
    }

    /**
     * decreases player ant's speed to 0
     */
    public void brake() {
        System.out.println("braking");
        SingleAnt.getAnt().increaseSpeed(-1 * accelerationRate);
    }

    /**
     * turn player ant left
     */
    public void left() {
        System.out.println("turned left");
        SingleAnt.getAnt().incrementHeading(-1 * turnRate);
    }

    /**
     * turn player ant right
     */
    public void right() {
        System.out.println("turned right");
        SingleAnt.getAnt().incrementHeading(turnRate);
    }

    /**
     * changes food consumption rate
     */
    public void setFoodConsumptionRate(int rate) {
        System.out.println("food consumption rate was set to " + rate);
        SingleAnt.getAnt().setFoodConsumptionRate(rate);

        this.setChanged();
        this.notifyObservers();
    }

    public void collideFlag(int flagNum) {
        System.out.println("Flag " + ((Integer) flagNum).toString() + " was stepped over");
        SingleAnt.getAnt().setNextFlag(flagNum);

        this.setChanged();
        this.notifyObservers();
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

        FoodStation lastStation = null;

        IIterator iteratorGameObject = gameObjects.getIterator();
        while (iteratorGameObject.hasNext()) {
            GameObject gO = (GameObject) iteratorGameObject.getNext();
            if (gO instanceof FoodStation) {
                if (((FoodStation) gO).getCapacity() != 0) {
                    // found a food station with food
                    SingleAnt.getAnt()
                            .incrementFoodLevel(((FoodStation) gO)
                                    .consume(((FoodStation) gO).getCapacity()));
                    lastStation = (FoodStation) gO;
                    break;
                }
            }
        }

        // If the last station visited ran out of food, add new station
        if (lastStation != null) {
            // There was a station with food
            if (lastStation.getCapacity() == 0) {
                // All its food was consumed
                this.numOfFoodStations++;
                this.gameObjects.put((foodStationTag + this.numOfFoodStations),
                        new FoodStation((foodStationTag + this.numOfFoodStations)));

            }
        }
        this.foodTick = false;

        this.setChanged();
        this.notifyObservers();
    }

    public void collideSpider() {
        System.out.println("Look out for 8 legs");
        SingleAnt.getAnt().takeDamage(1);

        this.setChanged();
        this.notifyObservers();
    }

    public void tick() {
        System.out.println("Time has elapsed");

        foodTick = true;

        SingleAnt.getAnt().tick();

        if (this.winCondition()) {
            this.win();
            this.updateMap();
            return;
        }
        if (this.looseCondition()) {
            this.loose();
            this.updateMap();
            return;
        }
        if (this.restartCondition()) {
            this.restartInitObjects();
        }

        IIterator iteratorGameObject = gameObjects.getIterator();
        while (iteratorGameObject.hasNext()) {
            GameObject gameObject = (GameObject) iteratorGameObject.getNext();
            gameObject.tick();
        }

        this.clock++;
        this.updateMap();
        this.setChanged();
        this.notifyObservers();
    }

    public void display() {
        System.out.println();
        System.out.println("Lives: " + this.lives);
        System.out.println("Clock: " + this.clock);
        System.out.println("Ant's Highest Flag: " + SingleAnt.getAnt().getLastFlag());
        System.out.println("Ant's Current Food Level: " + SingleAnt.getAnt().getFoodLevel());
        System.out.println(
                "Ant's Current Health Level: " + SingleAnt.getAnt().getHealthLevel());
        System.out.println("Current number of loaded objects: " + this.gameObjects.size());
    }

    public void map() {
        // System.out.println("\nDisplaying Map");

        this.mapString = "";

        if (this.winCondition()) {
            this.mapString += this.win() + "\n\n";
        }
        if (this.looseCondition()) {
            this.mapString += this.loose() + "\n\n";
        }
        if (this.restartCondition() && !this.looseCondition()) {
            this.mapString += this.restart() + "\n\n";
        }

        // Stupid way to get all the values to sort
        // there has to be a faster or cleaner way
        ArrayList<String> tmpList = new ArrayList<String>();

        IIterator iteratorGameObject = gameObjects.getIterator();
        while (iteratorGameObject.hasNext()) {
            GameObject gameObject = (GameObject) iteratorGameObject.getNext();
            tmpList.add(gameObject.toString());
        }

        // everything should be in order already but just in case
        Collections.sort(tmpList);
        // Put ant on top
        tmpList.add(0, SingleAnt.getAnt().toString());

        // previously printed to consol, now adding to string
        for (String tmp : tmpList) {
            // System.out.println(tmp);
            mapString += tmp + "\n";
        }

        setChanged();
        notifyObservers();
    }

    public void updateMap() {
        this.map();
    }

    public String getMap() {
        return this.mapString;
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
        System.out.println("this exit method is deprecated");
        // if (this.inExit) {
        // System.out.println("\nKey press denied\nIn exit mode.");
        // }
        // if (this.inLooseExit) {
        // System.out.println("\nKey press denied\nPlease press y to exit.");
        // this.inExit = true;
        // this.inLooseExit = true;
        // return;
        // }

        // this.inExit = true;
        // System.out.println("\nDo you wish to exit?\ny/n");

    }

    public void confirm() {
        System.out.println("this exit method is deprecated");
        // if (this.inExit) {
        // System.exit(0);
        // }
    }

    public void deny() {
        System.out.println("this exit method is deprecated");
        // if (this.inExit) {
        // System.out.println("\nn confirmed\nreturning to game world");
        // }
        // this.inExit = false;
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
