package com.mycompany.a3.GameCommands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class CommandCollideWithFoodStation extends Command {
    // private static String commandName = "Command_Collide_With_Food_Station";
    private static String commandName = "Collide With Food Station";

    private GameWorld gameWorld;

    public CommandCollideWithFoodStation(GameWorld newGameWorld) {
        super(commandName);
        this.gameWorld = newGameWorld;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        gameWorld.collideFoodStation();
    }

}
