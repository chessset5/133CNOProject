package com.mycompany.a2.GameCommands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class CommandAccelerate extends Command {
    // private static String commandName = "Command_Accelerate";
    private static String commandName = "Accelerate";

    private GameWorld gameWorld;

    public CommandAccelerate(GameWorld newGameWorld) {
        super(commandName);
        this.gameWorld = newGameWorld;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        gameWorld.accelerate();
    }
}