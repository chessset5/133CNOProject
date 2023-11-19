package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;

public class MapView extends Container implements Observer {
    private TextArea printOutArea;
    private String map;
    private static GameWorld gameWorld;

    public MapView() {
        this.setLayout(new BorderLayout());
        // make it red... I hate that there is no .RED
        this.getAllStyles().setBorder(Border.createLineBorder(10, ColorUtil.rgb(255, 0, 0)));

        printOutArea = new TextArea();
        printOutArea.setEditable(false);
        this.add(BorderLayout.CENTER, printOutArea);
    }

    public void update(Observable o, Object arg) {
        // code here to call the method in GameWorld (Observable) that output the
        // game object information to the console

        gameWorld = (GameWorld) o;
        this.map = gameWorld.getMap();
        this.printOutArea.setText(this.map);
        this.repaint();

        // gameWorld.updateMap();
    }
}