package com.mycompany.a1.GameObjects.Movable;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.mycompany.a1.Interfaces.IFoodie;

public class Ant extends Movable implements IFoodie {

    private int maximumSpeed = 20;
    private int foodConsumptionRate;
    private int maxHealth = 10;
    private int healthLevel = 10;
    private int lastFlagReached;
    private boolean flagTick = false;

    public Ant(Point firstLocation) {
        super();
        this.setSize(5);
        // Ant Color is Black... or white? what ever this is:
        this.setColor(ColorUtil.argb(255, 0, 0, 0));
        this.setLocation(firstLocation);
        this.setHeading(0);
        this.lastFlagReached = 1;
        this.setSpeed(maximumSpeed / 4);
        this.setFoodLevel(100);
    }

    // getters setters //

    public int getHealthLevel() {
        return this.healthLevel;
    }

    public void setFoodConsumption(int newConsumptionRate) {
        this.setFoodConsumptionRate(newConsumptionRate);
    }

    public void setFoodConsumptionRate(int newConsumptionRate) {
        this.foodConsumptionRate = newConsumptionRate;
    }

    public int getLastFlag() {
        return lastFlagReached;
    }

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
        super.tick();
        this.setFoodLevel(this.getFoodLevel() - this.foodConsumptionRate);
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
        if (this.isAntDead()) {
            return false;
        }
        int tmpSpeed = this.getSpeed(); // tmpSpeed to remove any sluggishness feelings while controlling the ant
        int tmpMaxSpeed = this.maximumSpeed * (this.healthLevel / this.maxHealth);
        if (this.getSpeed() > tmpMaxSpeed) {
            this.setSpeed(tmpMaxSpeed);
        }
        // calling parent method
        boolean answer = super.move();
        this.setSpeed(tmpSpeed);
        return answer;
    }

    // class methods //

    public boolean isAntDead() {
        return this.healthLevel == 0;
    }

    public boolean takeDamage(int damage) {
        this.healthLevel -= damage;
        if (this.healthLevel < 0) {
            this.healthLevel = 0;
        }

        // adding red to visualize damage
        int inc = 255 / this.maxHealth;
        int red = ColorUtil.red(this.getColor()) + inc;
        if (red > 255) {
            red = 255;
        } else if (red < 0) {
            red = 0;
        }
        this.setColor(ColorUtil.argb(255, red, 0, 0));

        return true;
    }

    @Override
    public String toString() {
        String parent = super.toString();
        return "" + parent + "\nmaxSpeed=" + this.maximumSpeed
                + " foodConsumptionRate=" + this.foodConsumptionRate
                + "";
    }
}
