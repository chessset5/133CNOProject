package com.mycompany.a2.GameObjects.Movable;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.mycompany.a2.Interfaces.IFoodie;

public class Ant extends Movable implements IFoodie {

    private int maximumSpeed = 20;
    private int foodConsumptionRate;
    private int maxHealth = 10;
    private int healthLevel;
    private int lastFlagReached;
    /**
     * True if flag was already set this tick, false otherwise
     */
    private boolean flagTick = false;

    /**
     * True if damage was taken on this tick, false otherwise
     * TODO: decide if want to remove, two attackers same frame.
     */
    // private boolean damageTick = false;

    public Ant(Point firstLocation, int firstHeading, int firstSpeed) {
        super();
        this.setSize(5);
        this.setHealthLevel(10);
        // Ant Color is Turquoise? what ever this comes out to:
        this.setColor(ColorUtil.argb(255, 0, 255, 255));
        this.setLocation(firstLocation);
        this.setHeading(firstHeading);
        this.lastFlagReached = 1;
        this.setSpeed(firstSpeed);
        this.setFoodLevel(100);
        this.setFoodConsumption(0);
    }

    // getters setters //

    public int getHealthLevel() {
        return this.healthLevel;
    }

    /**
     * Health level can not be lower than 1 when set
     * 
     * @param newHealthLevel
     */
    private void setHealthLevel(int newHealthLevel) {
        if (newHealthLevel < 1) {
            this.healthLevel = 1;
        } else {
            this.healthLevel = newHealthLevel;
        }
    }

    /**
     * Increments health. Health can not fall below 0
     * 
     * @param inc
     * @return
     */
    private boolean incrementHealthLevel(int inc) {
        this.healthLevel += inc;
        if (this.healthLevel < 0) {
            this.healthLevel = 0;
        }
        return true;
    }

    // Flags and values to set consumption rate at tick time
    private int consumptionRateAtTick;
    private boolean foodConsumptionTick = false;

    /**
     * @see setFoodConsumptionRate()
     */
    public void setFoodConsumption(int newConsumptionRate) {
        this.setFoodConsumptionRate(newConsumptionRate);
    }

    /**
     * Sets new consumption rate next tick
     * 
     * @param newConsumptionRate
     */
    public void setFoodConsumptionRate(int newConsumptionRate) {
        // sets consumption ra
        this.consumptionRateAtTick = newConsumptionRate;
        if (this.consumptionRateAtTick < 1) {
            this.consumptionRateAtTick = 1;
        }
        this.foodConsumptionTick = true;
    }

    /**
     * 
     * @return last valid flag hit
     */
    public int getLastFlag() {
        return lastFlagReached;
    }

    /**
     * @param flag
     * @return
     * @true if new flag was set
     * @false if given flag was invalid
     */
    public boolean setNextFlag(int flag) {
        // will only activatte after tick, if tick activated then
        if (((flag - 1) != lastFlagReached) || (this.flagTick == false)) {
            return false;
        }
        lastFlagReached = flag;
        this.flagTick = false;
        return true;
    }

    // class overrides //

    @Override
    public void tick() {
        this.flagTick = true;
        // this.damageTick = true;

        if (this.foodConsumptionTick) {
            this.foodConsumptionRate = consumptionRateAtTick;
            this.foodConsumptionTick = false;
        }
        super.tick();
    }

    /**
     * ant can not change size
     */
    @Override
    public void setSize(Integer newSize) {
        return;
    }

    @Override
    public boolean move() {
        // Can not move when dead
        if (this.isAntDead()) {
            return false;
        }

        // Actual Max speed
        int tmpMaxSpeed = this.maximumSpeed * (this.healthLevel / this.maxHealth);
        if (this.getSpeed() > tmpMaxSpeed) {
            this.setSpeed(tmpMaxSpeed);
        }
        // calling parent method
        boolean answer = super.move();
        // if moved
        if (answer) {
            this.setFoodLevel(this.getFoodLevel() - this.foodConsumptionRate);
        }
        // this.setSpeed(tmpSpeed);
        return answer;
    }

    // class methods //

    /**
     * @return true if Ant is dead, false if otherwise.
     */
    public boolean isAntDead() {
        return this.healthLevel <= 0;
    }

    /**
     * @return true if Ant is starved, false if otherwise.
     * @note Ant is starved if it's food level is 0
     */
    public boolean isStarved() {
        return this.getFoodLevel() <= 0;
    }

    /**
     * Damages Ant and increases the red value of the Ant
     * 
     * @param damage
     * @return true if ant received damage
     * @note will only be damaged once per tick.
     */
    public boolean takeDamage(int damage) {
        // Will not take the same damage in one tick
        // currently one attacker.
        // TODO: change or remove later
        // if (this.damageTick == false) {
        // return false;
        // }
        this.incrementHealthLevel(-1 * damage);

        // adding red to visualize damage
        int inc = 255 / this.maxHealth;
        int red = ColorUtil.red(this.getColor()) + inc;
        int green = ColorUtil.green(this.getColor());
        int blue = ColorUtil.blue(this.getColor());
        if (red > 255) {
            red = 255;
        } else if (red < 0) {
            red = 0;
        }

        this.setColor(ColorUtil.argb(255, red, green, blue));

        // prevent double tick damage
        // this.damageTick = false;
        return true;
    }

    @Override
    public String toString() {
        String parent = super.toString();
        return "" + parent + "\nmaxSpeed=" + this.maximumSpeed
                + " foodConsumptionRate=" + this.foodConsumptionRate
                + "";
    }

    /**
     * Ant's speed will not be 0
     */
    @Override
    protected void setSpeed(int newSpeed) {
        if (newSpeed < 0) {
            newSpeed = 0;
        }
        super.setSpeed(newSpeed);
    }
}
