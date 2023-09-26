package com.mycompany.a1.GameObjects.Movable;

public class Spider extends Movable {

    public Spider() {
        super();
        // Spider speed is 5:10
        this.speed = this.rand.nextInt(6) + 5;
        this.heading = this.rand.nextInt(360);
    }

    @Override
    public boolean move() {
        // random heading is -5:5
        int randomHeading = this.rand.nextInt(11) - 5;
        this.heading += randomHeading;
        // calling parent method
        boolean answer = super.move();

        // if spider can not move, rotate it and try again next tick
        if (answer == false) {
            this.heading += 45;
            this.heading %= 360;
        }
        return answer;
    }

}
