package com.mycompany.a2.GameCommands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class CommandBreak extends Command {
    // private static String commandName = "Command_Break";
    private static String commandName = "Break";

    private GameWorld gameWorld;

    public CommandBreak(GameWorld newGameWorld) {
        super(commandName);
        this.gameWorld = newGameWorld;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        gameWorld.brake();
    }
}
