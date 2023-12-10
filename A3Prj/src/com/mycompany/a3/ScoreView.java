package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;

public class ScoreView extends Container implements Observer {
    private GameWorld gameWorld;
    private Label timeData;
    private Label livesData;
    private Label flagData;
    private Label foodData;
    private Label healthData;
    private Label soundData;

    public ScoreView() {
        this.setLayout(new FlowLayout(Component.CENTER));

        // Time
        Label timeText = new Label("Time:");
        this.setLabel(timeText);
        this.timeData = new Label("1");
        this.setLabel(this.timeData);

        // Lives
        Label livesText = new Label("Lives Left:");
        this.setLabel(livesText);
        this.livesData = new Label("3");
        this.setLabel(this.livesData);

        // Flag
        Label flagsText = new Label("Last Flag Reached:");
        this.setLabel(flagsText);
        this.flagData = new Label("1");
        this.setLabel(this.flagData);

        // Food
        Label foodText = new Label("Food Level:");
        this.setLabel(foodText);
        this.foodData = new Label("100");
        this.setLabel(this.foodData);

        // health
        Label healthText = new Label("Health Level:");
        this.setLabel(healthText);
        this.healthData = new Label("100");
        this.setLabel(this.healthData);

        // Sound
        Label soundText = new Label("Sound:");
        this.setLabel(soundText);
        this.soundData = new Label("OFF");
        this.setLabel(this.soundData);
    }

    @Override
    public void update(Observable o, Object arg) {
        // code here to update labels from the game/ant state data
        gameWorld = (GameWorld) o;
        this.timeData.setText(" " + gameWorld.getclock());
        this.livesData.setText(" " + gameWorld.getLife());
        this.flagData.setText(" " + gameWorld.getCurFlag());
        this.foodData.setText(" " + gameWorld.getFood());
        this.healthData.setText(" " + gameWorld.getHealth());
        this.soundData.setText(" " + (gameWorld.getSound() ? "on" : "off"));
        this.soundData.getParent().revalidate();

        this.repaint();
    }

    public void setLabel(Label label) {
        label.getAllStyles().setFgColor(ColorUtil.BLUE);
        this.add(label);
    }

}