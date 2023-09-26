package com.mycompany.a1.GameObjects.Movable;

public class Spider extends Movable {

    public Spider() {
        super();
        // Spider speed is 5:10
        this.setSpeed(this.getRand().nextInt(6) + 5);
        this.setHeading(this.getRand().nextInt(360));
    }

    @Override
    public boolean move() {
        // random heading is -5:5
        int randomHeading = this.getRand().nextInt(11) - 5;
        this.setHeading(this.getHeading() + randomHeading);
        // calling parent method
        boolean answer = super.move();

        // if spider can not move, rotate it and try again next tick
        if (answer == false) {
            this.setHeading(this.getHeading() + 45);
        }
        return answer;
    }

}
