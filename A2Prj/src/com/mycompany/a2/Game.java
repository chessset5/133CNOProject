package com.mycompany.a2;

import com.codename1.ui.Form;
import com.codename1.ui.Button;
import com.codename1.ui.Toolbar;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

import com.mycompany.a2.GameCommands.*;

public class Game extends Form {
    private GameWorld gw;
    private MapView mv; // new in A2
    private ScoreView sv; // new in A2

    public Game() {
        gw = new GameWorld(); // create “Observable” GameWorld
        mv = new MapView(); // create an “Observer” for the map
        sv = new ScoreView(); // create an “Observer” for the game/ant state data
        gw.addObserver(mv); // register the map observer
        gw.addObserver(sv); // register the score observer
        // code here to create Command objects for each command,
        // add commands to side menu and title bar area, bind commands to keys, create
        // control containers for the buttons, add buttons to the control containers,
        // add commands to the buttons, and add control containers, MapView, and
        // ScoreView to the form
        this.setLayout(new BorderLayout());
        this.add(BorderLayout.NORTH, sv);
        this.add(BorderLayout.CENTER, mv);

        // setup GUI
        this.toolBarMenu();
        this.buttonsLeft();
        this.buttonsRight();
        this.buttonsBottom();

        // set food consumption
        CommandSetFoodConsumption commandSetFoodConsumption = new CommandSetFoodConsumption(gw);
        this.addKeyListener('c', commandSetFoodConsumption);

        this.show();
        // ... // code here to query MapView’s width and height and set them as world’s
        // width and height

        int height = mv.getHeight();
        int width = mv.getWidth();
        GameWorld.setWorldMax(new Point((float) width, (float) height));
        System.out.println("GameWorld Height: " + GameWorld.getWorldMax().getY());
        System.out.println("GameWorld Width: " + GameWorld.getWorldMax().getX());

        gw.init(); // initialize world

    }

    private void toolBarMenu() {
        Toolbar toolbar = new Toolbar();
        this.setToolbar(toolbar);
        toolbar.setTitle("TheJourneyGame");

        // Sound check box
        CheckBox checkBox = new CheckBox();
        checkBox = setCheckBoxStyle(checkBox);

        // Sound command
        CommandSoundOn commandSound = new CommandSoundOn(gw);
        checkBox.setCommand(commandSound);
        toolbar.addComponentToSideMenu(checkBox);

        // Acceleration command
        CommandAccelerate commandAccelerate = new CommandAccelerate(gw);
        toolbar.addCommandToSideMenu(commandAccelerate);

        // About command
        CommandAboutInformation commandAboutInformation = new CommandAboutInformation();
        toolbar.addCommandToSideMenu(commandAboutInformation);

        // Help command
        CommandHelpInformation commandHelpInformation = new CommandHelpInformation();
        toolbar.addCommandToRightBar(commandHelpInformation);
        toolbar.addCommandToSideMenu(commandHelpInformation);

        // Exit command
        CommandExit commandExit = new CommandExit();
        toolbar.addCommandToSideMenu(commandExit);

    }

    private void buttonsLeft() {
        // stacked buttons vert
        Container leftButtons = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        leftButtons = setContainerStyle(leftButtons);

        // Accelerate command
        CommandAccelerate commandAccelerate = new CommandAccelerate(gw);
        Button accelerateButton = new Button(commandAccelerate);
        // applying style, top button
        accelerateButton = this.topGap(accelerateButton);
        accelerateButton = this.setMainButtonStyle(accelerateButton);
        leftButtons.add(accelerateButton);
        this.addKeyListener('a', commandAccelerate);

        // Left button
        CommandLeftTurn commandLeftTurn = new CommandLeftTurn(gw);
        Button leftTurnButton = new Button(commandLeftTurn);
        // applying style
        leftTurnButton = this.setMainButtonStyle(leftTurnButton);
        leftButtons.add(leftTurnButton);
        this.addKeyListener('l', commandLeftTurn);

        // add button container to GUI
        this.add(BorderLayout.WEST, leftButtons);
    }

    private void buttonsRight() {
        // stacked buttons vert
        Container rightButtons = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        rightButtons = setContainerStyle(rightButtons);

        // Break command
        CommandBreak commandBreak = new CommandBreak(gw);
        Button breakButton = new Button(commandBreak);
        // applying style, top button
        breakButton = this.topGap(breakButton);
        breakButton = this.setMainButtonStyle(breakButton);
        rightButtons.add(breakButton);
        this.addKeyListener('b', commandBreak);

        // Right button
        CommandRightTurn commandRightTurn = new CommandRightTurn(gw);
        Button rightTurnButton = new Button(commandRightTurn);
        // applying style
        rightTurnButton = this.setMainButtonStyle(rightTurnButton);
        rightButtons.add(rightTurnButton);
        this.addKeyListener('r', commandRightTurn);

        // add button container to GUI
        this.add(BorderLayout.EAST, rightButtons);
    }

    private void buttonsBottom() {
        // stacked buttons vert
        Container bottomButtons = new Container(new FlowLayout(Component.CENTER));
        bottomButtons = setContainerStyle(bottomButtons);

        // Collide With Flag command
        CommandCollideWithFlag commandCollideWithFlag = new CommandCollideWithFlag(gw);
        Button collideWithFlagButton = new Button(commandCollideWithFlag);
        // applying style
        collideWithFlagButton = this.setMainButtonStyle(collideWithFlagButton);
        bottomButtons.add(collideWithFlagButton);

        // Collide with Spider button
        CommandCollideWithSpider commandCollideWithSpider = new CommandCollideWithSpider(gw);
        Button collideWithSpiderButton = new Button(commandCollideWithSpider);
        // applying style
        collideWithSpiderButton = this.setMainButtonStyle(collideWithSpiderButton);
        bottomButtons.add(collideWithSpiderButton);
        this.addKeyListener('g', commandCollideWithSpider);

        // Collide with Food Station button
        CommandCollideWithFoodStation commandCollideWithFoodStation = new CommandCollideWithFoodStation(gw);
        Button CollideWithFoodStationButton = new Button(commandCollideWithFoodStation);
        // applying style
        CollideWithFoodStationButton = this.setMainButtonStyle(CollideWithFoodStationButton);
        bottomButtons.add(CollideWithFoodStationButton);
        this.addKeyListener('f', commandCollideWithFoodStation);

        // Tick button
        CommandTick commandTick = new CommandTick(gw);
        Button tickButton = new Button(commandTick);
        // applying style
        tickButton = this.setMainButtonStyle(tickButton);
        bottomButtons.add(tickButton);
        this.addKeyListener('t', commandTick);

        // add button container to GUI
        this.add(BorderLayout.SOUTH, bottomButtons);
    }

    private Container setContainerStyle(Container container) {
        container.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
        return container;
    }

    private Button topGap(Button button) {
        button.getAllStyles().setMarginTop(100);
        return button;
    }

    private Button setMainButtonStyle(Button button) {
        button.getAllStyles().setFgColor(ColorUtil.WHITE);
        button.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
        button.getAllStyles().setBgTransparency(255);
        button.getAllStyles().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));
        button.getAllStyles().setPadding(TOP, 5);
        button.getAllStyles().setPadding(BOTTOM, 5);
        return button;
    };

    private CheckBox setCheckBoxStyle(CheckBox checkBox) {
        // this might not be needed
        checkBox.getAllStyles().setBgTransparency(255);
        checkBox.getAllStyles().setBgColor(ColorUtil.WHITE);
        return checkBox;
    }

}
