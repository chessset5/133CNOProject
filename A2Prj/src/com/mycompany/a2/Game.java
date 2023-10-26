package com.mycompany.a2;

import com.codename1.ui.Form;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
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

        this.show();
        // ... // code here to query MapView’s width and height and set them as world’s
        // width and height

        gw.init(); // initialize world
    }

    private void initNORTHToolBar() {
        Toolbar toolBar = new Toolbar();
        this.setToolbar(toolBar);

        toolBar.setTitle("TheJourneyGame");

        // add commands
    };

    private Button setStyle(Button button) {
        button.getAllStyles().setBgTransparency(255);
        button.getAllStyles().setPadding(TOP, 5);
        button.getAllStyles().setPadding(BOTTOM, 5);
        button.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
        button.getAllStyles().setFgColor(ColorUtil.WHITE);
        button.getAllStyles().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));
        return button;
    };
}
