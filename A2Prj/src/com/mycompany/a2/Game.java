package com.mycompany.a2;

import com.codename1.ui.Form;

import com.codename1.ui.events.ActionListener;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import java.lang.String;

import com.mycompany.a2.MapView;
import com.mycompany.a2.ScoreView;

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
        this.show();
        // ... // code here to query MapView’s width and height and set them as world’s
        // width and height
        gw.init(); // initialize world
    }
}
