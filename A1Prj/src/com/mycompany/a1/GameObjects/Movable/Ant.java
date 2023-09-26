package com.mycompany.a1.GameObjects.Movable;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.mycompany.a1.Interfaces.IFoodie;

public class Ant extends Movable implements IFoodie {

    protected int maximumSpeed = 20;
    protected int foodConsumptionRate;
    protected int maxHealth = 10;
    protected int healthLevel = 10;
    protected int lastFlagReached;

    public Ant(Point firstLocation) {
        super();
        this.ObjectSize = 5;
        // Ant Color is Black... or white? what ever this is:
        this.myColor = ColorUtil.argb(255, 0, 0, 0);
        this.setLocation(firstLocation);
        this.heading = 0;
        this.lastFlagReached = 1;
        this.speed = maximumSpeed / 4;
    }

    public void setFoodConsumption(int newConsumptionRate) {
        this.setFoodConsumptionRate(newConsumptionRate);
    }

    public void setFoodConsumptionRate(int newConsumptionRate) {
        this.foodConsumptionRate = newConsumptionRate;
    }

    public void tick() {
        super.tick();
        this.foodLevel -= this.foodConsumptionRate;
    }

    @Override
    public boolean move() {
        if (this.isAntDead()) {
            return false;
        }
        int tmpSpeed = this.speed; // tmpSpeed to remove any sluggishness feelings while controlling the ant
        int tmpMaxSpeed = this.maximumSpeed * (this.healthLevel / this.maxHealth);
        if (this.speed > tmpMaxSpeed) {
            this.speed = tmpMaxSpeed;
        }
        // calling parent method
        boolean answer = super.move();
        this.speed = tmpSpeed;
        return answer;
    }

    public boolean isAntDead() {
        return this.healthLevel == 0;
    }

    public int getLastFlag() {
        return lastFlagReached;
    }

    public boolean setNextFlag(int flag) {
        if ((flag - 1) != lastFlagReached) {
            return false;
        }
        lastFlagReached = flag;
        return true;
    }

    public boolean takeDamage(int damage) {
        this.healthLevel -= damage;
        if (this.healthLevel < 0) {
            this.healthLevel = 0;
        }

        // adding red to visualize damage
        int inc = 255 / this.maxHealth;
        int red = ColorUtil.red(this.myColor) + inc;
        if (red > 255) {
            red = 255;
        } else if (red < 0) {
            red = 0;
        }
        this.myColor = ColorUtil.argb(255, red, 0, 0);

        return true;
    }

    public int getHealthLevel() {
        return this.healthLevel;
    }

    @Override
    public String toString() {
        String parent = super.toString();
        return "" + parent + "\nmaxSpeed=" + this.maximumSpeed
                + " foodConsumptionRate=" + this.foodConsumptionRate
                + "";
    }
}
